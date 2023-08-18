Massey Special Permission portfolio-

The files in this portfolio are to demonstrate my pervious knowledge for programming. I have completed CS50 earlier this year (2022) and have attached a few of the labs and problem set projects from that course in this portfolio. I have also added a description of each project as well as its usage if you would like to run it yourself.
NOTE: These projects were written in VS code on Linux and BASH used as the terminal. A few system commands may not work on your OS (eg: os.system('clear')).

The projects in this portfolio are as follows:

        - tournament.py:
            - About:
                - This project runs a simulation of the FIFA world cup using csv files
                   with real world data on different countries winning statistics. 
                   The program simulates 1000 tournaments and then displays the % chance
                    each country is likely to win the 
                   tournament.
            - Usage:
                - Run program in terminal with 'python tournament.py 2018m.csv' (there 
                    are 2 csv files in the project folder, one for men and one for 
                    womans football). Argc is needed to run the program, otherwise you
                     will be prompted to input it again and again until you input the
                      correct information.

        - dna.py:
            - About:
                - This program will simulate a DNA sequence check (like what police
                 do) counting the repeats of a STR for the found dna sample and try 
                 to match it to a DNA database containing a list of (made-up) people 
                 and a segment of the STRs. Once the database is checked if a match is
                  found the program with display the matched name, if not then the 
                  program will output that no match has been found in the database.
            - Usage:
                - This project has 2 extra folders containing a few different sized csv
                 sequence and database files which are both required after the file name
                  to run the program.
                - Enter this into the terminal to run the program: 'python dna.py 
                  databases/small.csv sequences/2.txt' (there are many different files,
                   so other combinations of sequences and database files work)

        - runner game (folder) and RunningMan.py files:
            - About:
                - This program is called 'RunningMan!' and it was the first 2D game I
                  created using pygame. I wanted to practice my problem solving by
                   creating a shot little game, and it liked this project a lot.
                - This folder contains 2 versions of the same game: 
                 'RunningManJustOOP.py' and 'RunningMan(OOPandRedundantCode).py'
                - There are 2 files because this project was cleaned up a little bit
                 after it was first made. The OOPandRedundantCode file was made first,
                  it includes all of the code used before many of the features were 
                  changed to classes and functions. The JustOOP file is the program 
                  with none of the redundant code, just the classes and their functions
                   to run it. Feel free to look at both, there are many comments talking
                    about why some code was changed and turned into OOP.
                - There are also graphics and audio files for the art and sound design
                 of this game, these files are in their respective folders in the 
                 project.
            Usage:
                - This game will need pygame to run.
                - To run the game enter 'pygame RunningManJustOOP.py' and the game 
                window will open, taking you to the start screen.
                - From there follow the game instructions and press the SPACEBAR, the
                 player will start running to the right and you need to jump the enemies.
                - You will get a game over screen if an enemy hits the player, and your 
                score will be displayed.

        - inheritance.c:
            -About:
                - This program simulates the inheritance of blood types in a family of 3
                 generations and then displays the output to the user. Using linked list
                  in C to move from grandparent, parent and child to generate an 
                  inherited blood type based on the 2 parents alleles to simulate the 
                  passing of a blood type.
            - Usage:
                -Run in the terminal ./inheritance after compilation.

        - runoff.c:
            - About:
                - This program simulates a run off election using candidates which the
                 user inputs. It then asks for the amount of voters in the election and
                  will run through all the inputted candidates for each voter and ask
                   for the voters preference on the candidates. It will then calculate
                    the winner of the election (unless there is a draw, it will the output
                     a draw) and output the winner.
            - Usage:
                - Enter './runoff' into the terminal along with the candidate names for
                 the election as argc. eg: './runoff Tom Bob Nick'. Then you will be
                  prompted to enter your number of voters and each voter will then get
                   to type the name of their preferred candidate in descending order.

        - caesar.c:
            - About:
                - This program is a simple one which scrambles a word based on a user
                 inputted number key. This moves all of the letters of your inputted 
                 phrase either forward or backward in the alphabet (as you can input 
                 positive or negative numbers) and then prints out the coded message- 
                 producing a phrase or a larger amount of text which can only be 
                 de-scrambled if the reader knows the inputted user key.
            - Usage:
                - This program will takes in user number key first, then it prompts for
                 plaintext and will out put the scrambled text.
                - enter into terminal after compilation './caesar 3' and your scrambled
                 text will be displayed int the terminal.
                - eg: key = 3, plaintext = 'Hello, World!", and scrambled output = 
                'Khoor, Zruog!"

        - scrabble.c:
            - About:
                - This was one of the very first problem sets I completed for CS50. It 
                is a simple program that takes in 2 words from 2 players. It then scores
                 them against the letter scoring in the game 'Scrabble' and displays the
                  winner with the most points. In this program there is error checking
                   to ensure that the that the players are entering single words only.
            - Usage:
             -  run './scrabble' in the terminal after compilation. Then enter the word
              for player 1 followed by a word for player 2 and the winner (or a tie) will
               be displayed.

        - hotelManager.cpp:
            - About:
                - This is a software program that manages rooms and customers of a hotel. 
                It is made with a lot of OOP and many different classes and functions for 
                different tasks and features a manager would need to run a hotel. Rooms can be 
                added and their features can be set, customers can then check in and out of 
                created rooms.
                - The manager can also search for rooms and see if another customer has 
                already booked it out.
                - There is a main menu and submenus based off of user inputted choice.
                - This software is what I wanted to make to improve my knowledge of C++
                 and OOP and it was a great way to learn more about them.
            - Usage:
                - This software is very easy to use as it has a clear menu with a list 
                of options to select to navigate through the management system.
                - run the file by entering './hotelManager' into the terminal after 
                compilation.
                - Then you need to add a new room to your hotel before booking in 
                customers, this can be done by entering '1' to enter the 'manage rooms'
                 menu, then adding a room using the displayed menu number and following the
                  prompts to describe the features of the room.
                - once at least one room has been added the other options of booking
                 customers, or searching rooms will be accessible from the main menu.
