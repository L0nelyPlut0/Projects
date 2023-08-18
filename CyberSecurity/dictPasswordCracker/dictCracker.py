#file that uses a random hashed value from hashed_values.txt and cracks the password

import random
import hashlib

# method to check if the passed hashed value is a valid password 
def crackHash(hashed_password):
    try:    # open file where all passwords are stored 
        with open("10-million-password-list-top-1000000.txt", "r", encoding="utf-8") as passFile:
            for line in passFile:
                password = line.strip()
                encPass = password.encode("utf-8")

                # dehash using some different hashing algorithms 
                md5_digest = hashlib.md5(encPass).hexdigest()
                sha1_digest = hashlib.sha1(encPass).hexdigest()
                sha256_digest = hashlib.sha256(encPass).hexdigest()
                sha512_digest = hashlib.sha512(encPass).hexdigest()

                # if the unhashed algorithm matches any password in the txt file print it out to the terminal 
                if any(digest == hashed_password for digest in (md5_digest, sha1_digest, sha256_digest, sha512_digest)):
                    print("Password found:")
                    # print out the password
                    print(f"\tPassword: {password}")
                    # print out the hashing algorithm used 
                    print(f"\tHashed using: {'MD5' if hashed_password == md5_digest else 'SHA-1' if hashed_password == sha1_digest else 'SHA-256' if hashed_password == sha256_digest else 'SHA-512' if hashed_password == sha512_digest else 'Bcrypt'}")
                    # print out the hashed value 
                    print(f"\tHashed value: {hashed_password}")
                    return 
            # password not found if no results are returned 
            print("Password not found in the list.")
    # throw error if file can't be opened 
    except FileNotFoundError:
        print("Error trying to open file. Please try again.")

# main method where hashed value is gotten and check to see if it is a password 
if __name__ == "__main__":
    # open hash value txt file 
    with open("hashed_values.txt", "r") as hash_file:
        hashed_values = hash_file.readlines()
    
    # generate random number up to 10 million 
    random_line_number = random.randint(0, len(hashed_values) - 1)  # Generate a random line number index
    # use generated random number as hash value to be cracked 
    random_hashed_value = hashed_values[random_line_number].strip()
    
    # call crackHash on selected hash value
    crackHash(random_hashed_value)
