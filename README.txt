Welcome to my projects repo! 
These were made from mid 2022 until currently August 2023 (I will likely add more as I make more).

The projects here are sorted into language:

        - Android (Java):
                - Workout app:
                        - This was made in semester break of 2023, to help me better prepare for my app development course in semester 2 of 2023.
                        - A app that shows the user some workouts. The user can click on workouts and the details will be displayed along with a                                   stopwatch fragment the user can time themself with.
                        - this app works well on both phones and tablets and the display adjusts to each accordingly. 

        - C++:
                - hotelManager.cpp:
                        - This was made mid 2022, to help me better understand c++ and a small amount of OOP. 
                        - This is a terminal based c++ file that allows the user to manage a hotel. The user can set up rooms, their cost and set                                   different options for each room. Different rooms can be set as booked out, and the user can also search if a specific room is                             free. 
                        - This project has an extra custom header file called 'hotleManager.h' to allow for some extra methods to work.

        - C:
                - caesar.c:
                        - This file was made while I was studying cs50 in mid 2022.
                        - This file scrambles a word based on a user inputted number key. This moves all of the letters of                                                        your inputted phrase either forward or backward in the alphabet (as you can input positive or negative numbers) and then                                 prints out the coded message.
                         - eg: 
                                - enter into terminal after compilation './caesar 3' and your scrambled text will be displayed int the terminal.
                                        - key = 3, plaintext = 'Hello, World!", and scrambled output = 'Khoor, Zruog!"

                - runoff.c:
                        - This was also made mid 2022 when I was studying cs50. 
                        - This file simulates a run off election using candidates which the user inputs. It then asks for the amount of voters in the                              election and will run through all the inputted candidates for each voter and ask for the voters preference on the candidates.                            It will then calculate the winner of the election (unless there is a draw, it will the output a draw) and output the winner.

                - scrabble.c:
                        - This was also made mid 2022 while I was studying cs50. 
                        - This file takes in 2 words from 2 players. It then scores them against the letter scoring in the game 'Scrabble' and displays                             the winner with the most points. In this program there is error checking to ensure that the that the players are entering                                 single words only.

        - CyberSecurity:
                - This folder is a collection of scripts made to help me learn about some cyber security things in July of 2023.

                - PortScanner:
                        - This python script is a port scanner which takes in an ip address, the ports wanted to scan, and the time limit for each port.
                        - It goes through all the specified ports in the range and checks if they are open. Printing any open ports out to the terminal.
                        - This script has been made to run a little faster using mutlithreading as when I was not using it and scanning a larger port                               range it was a little slower on my laptop.

                - WireSharkGeoTracker:
                        - This is a simple python script that uses packet capturing in WireShark to get the source location of packets captured and                                prints them out to the terminal as they are captured.

                - dictPasswordCracker:
                        - This is a folder with a 2 scripts and 2 txt files.
                        - The txt files are a list of 10 million most common passwords and a list of hashed values for these passwords.
                        - The hashed_values.txt can be remade again using hashMaker.py. Which takes in the 10-million-password-list-top-100000000.txt                              and uses a few different hashing algorithms to hash them all randomly each time.
                        - dictCracker.py cn then be run, this takes a random hashed value from hashed_values.txt and un-hasehes them, then compares                                them to the list of 10 million passwords until a match is found. Printing out the password, the hashing algorithm used and the                           hash value. 
                

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

