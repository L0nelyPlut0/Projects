#include <ctype.h>
#include <cs50.h>
#include <stdio.h>
#include <string.h>
//# include <ctype.h>= use int isupper/lower(char c);

int compute_score(string word);
// Points assigned to each letter of the alphabet
int POINTS[] = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

int main(void)
{
    // Get input words from both players
    string word1 = get_string("Player 1: ");
    string word2 = get_string("Player 2: ");

    // Score both words
    int score1 = compute_score(word1);
    int score2 = compute_score(word2);

    // TODO: Print the winner
    if (score1 > score2)
    {
        printf("Player 1 wins!\n");
    }
    else if (score2 > score1)
    {
        printf("Player 2 wins!\n");
    }
    else
    {
        printf("Tied!\n");
    }
}

int compute_score(string word)
{
    // TODO: Compute and return score for string

    // starting score at 0
    int score = 0;
    // working out player 1 and 2s word lengths with a for loop
    for (int i = 0; i < strlen(word); i++)
    {
        // score= 0 + however many points of what ever word[array] totals to - 65.
        // word[i] gives index of word according to ascii letters.
        // ONLY APPLIES TO UPPER CASE LETTERS!!!
        if (isupper(word[i]))
        //(word[i] > 65 && word[i] <90)!! long version without is upper function function
        {
            // if word ascii array index is between the numbers (uppercase numbers on ascii chart)
            // does this if letter is in upper case only.
            score = score + POINTS[word[i] - 65];
        }
        else if (islower(word[i]))
        {
            score = score + POINTS[word[i] - 97];
        }
        else
        {
            score = score + 0;
        }
    } // then need to return the score
    return score;
}