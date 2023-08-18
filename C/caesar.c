#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, string argv[])
{
    // checks if argc is 2, if not exit program and print error message
    if (argc != 2)
    {
        printf("Usage: ./caesar key\n");
        return 1;
    }

    // a for loop that goes through each array part in argv[1] to see if its a digit.
    // If not a digit exit program and print error message.
    for (int i = 0; i < strlen(argv[1]); i++)
    {
        if (!isdigit(argv[1][i]))
        {
        printf("Usage: ./caesar key\n");
        return 1;
        }
    }

    // converts argv[1] into an int, instead of a string.
    int key = (atoi(argv[1]));

    string plaintext = get_string("Plaintext: ");

    printf("ciphertext: ");

    //for loop to convert plain text into cipher text.
    for (int j = 0; j < strlen(plaintext); j++)
    {
        if (isupper(plaintext[j]))
        {
            printf("%c", (plaintext[j] - 65 + key) % 26 + 65);
        }
        else if (islower(plaintext[j]))
        {
            printf("%c", (plaintext[j] - 97 + key) % 26 + 97);
        }
        else
        {
            printf("%c", plaintext[j]);
        }
    }
        printf("\n");
}