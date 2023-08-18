# simple file used to start wire shark capture, and then print out where the captured ip addresses are coming from 

from geolite2 import geolite2
import socket
import subprocess

# cmd use for ubuntu, may be different depending on your os
# ctrl + c to stop capture 
cmd = ["sudo", "tshark", "-i", "wlp2s0", "-T", "fields", "-e", "ip.src", "-Y", "udp or tcp"]

process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
my_ip = socket.gethostbyname(socket.gethostname())
reader = geolite2.reader()

def get_ip_location(ip):
    location = reader.get(ip)
    
    try:
        country = location["country"]["names"]["en"]
    except:
        country = "Unknown"

    try:
        subdivision = location["subdivisions"][0]["names"]["en"]
    except:
        subdivision = "Unknown"    

    try:
        city = location["city"]["names"]["en"]
    except:
        city = "Unknown"
    
    return country, subdivision, city

try:
    for line in iter(process.stdout.readline, b""):
        src_ip = line.strip().decode("utf-8")

        if src_ip == my_ip:
            continue

        try:
            country, sub, city = get_ip_location(src_ip)
            print("Source IP: " + src_ip)
            print("Location: " + country + ", " + sub + ", " + city)
            print("-" * 50)
        except:
            print("Source IP: " + src_ip)
            print("Location: Not found")
            print("-" * 50)
except KeyboardInterrupt:
    print("Stopping capture.")
    process.kill()
finally:
    geolite2.close()
