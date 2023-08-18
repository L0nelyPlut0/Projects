# file to take a txt file and convert text inside into hashed values 

import hashlib
import random

# method that hashed values in a text file and outputs them as a txt file 
def hash_passwords(input_filename, output_filename):
    # try open txt file, throw exception if it fails
    try:
        passFile = open(input_filename, "r")
    except:
        print("Error trying to open file. Please try again.")
        return

    # array of all hashing algorithms used 
    algorithms = [
        hashlib.md5,
        hashlib.sha1,
        hashlib.sha256,
        hashlib.sha512
    ]
    
    # open the soon to be outputed hashed values file 
    with open(output_filename, "w") as output_file:
        for password in passFile:
            encPass = password.strip().encode("utf-8")
            
            #for each password in the input file randomly select a hashing algorithm and hash the password 
            chosen_index = random.randint(0, len(algorithms) - 1)
            chosen_algorithm = algorithms[chosen_index]
            hashed_value = chosen_algorithm(encPass).hexdigest()

            # output the hashed value to a new line of the output file 
            output_file.write(hashed_value+"\n")

    print("Hashed values have been saved to", output_filename)

# main function to run, uses 1 txt file to turn them into hashed values 
if __name__ == "__main__":
    input_filename = "10-million-password-list-top-1000000.txt"
    output_filename = "hashed_values.txt"
    hash_passwords(input_filename, output_filename)
