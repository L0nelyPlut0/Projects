import argparse
import socket
import concurrent.futures
from datetime import datetime

def scan_port(target, port, timeout):
    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.settimeout(timeout)
            result = s.connect_ex((target, port))
            if result == 0:
                print(f"Port {port} is open")
    except:
        pass

def main():
    parser = argparse.ArgumentParser(description="Simple port scanner")
    parser.add_argument("host", type=str, help="Target host to scan")
    parser.add_argument("-p", "--ports", type=str, default="1-65535", help="Port range to scan (e.g., '1-1024')")
    parser.add_argument("-t", "--timeout", type=float, default=1.0, help="Connection timeout in seconds")
    args = parser.parse_args()

    try:
        target = socket.gethostbyname(args.host)
    except socket.gaierror:
        print("Hostname could not be resolved.")
        return

    print("-" * 50)
    print(f"Scanning Target: {target}")
    print(f"Scanning started at: {datetime.now()}")
    print("-" * 50)

    port_range = map(int, args.ports.split("-"))
    start_port, end_port = sorted(port_range)

    with concurrent.futures.ThreadPoolExecutor() as executor:
        for port in range(start_port, end_port + 1):
            executor.submit(scan_port, target, port, args.timeout)

    print("-" * 50)
    print("Scanning finished at:", datetime.now())
    print("-" * 50)

if __name__ == "__main__":
    main()
