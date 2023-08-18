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

        - Java
                - These 2 folders are from mid 2023, when I was studying OOP at univerity.

                - ComputerStoreSwingGUI:
                        - This folder is a GUI for a computer store using swing.
                        - When run it propts the user to log in (there is not create user functionality currently, just already created users: 'm1'                                'm2', 'p1', p2', 'p3'. The passwords are the user names respectively).
                        - The gui is different for managers and salespersons, allowing for managers to have more functions in actually changing things.
                        - A list of deveices is displayed in a table and the user can click on them to view that specific device.
                        - If the user is a manager ('m1' or 'm2) they can edit specific devices by clicking on them in the table, or they can add a new                            device which is added to the end of the table.
                        - The user can sort the table by device type and category, or choose to display different categories and types based on selected                           filters. 

                - LibraryCatalogue:
                        - This folder is a terminal based Java program which displays a list of items (movies, books and journals) from a supplied txt                             file.
                        - The user can naviagate through the menu by typing in commands.
                        - The list can be sorted based on rating, and title.
                        - The user can search for a specific title, rent it out, return it and rate it.


        - Networking:
                - This folder contains a sub net network configuration made in GNS3.
                - It shows a subnetted network with 4 routers, 4 subnets all with different user capacity and 4 VPCs in each subnet. 
                - All routers can ping eachother, as well as all VPCs. 
                - This project was made to help me get a better understanding of IP addresses, allocating them and how to subnet and IP address. 


        - Python:
                - Runner game- pygames intro:
                        - This folder is a simiple side scrolling runner game I made towards the end of 2022 to help me learn Python and some pygames.
                        - This project was really helpful in me learning Python and OOP, and it was fun to make.
                        - The game opens on a start screen where the user is prompted to press spacebar.
                        - Once spacebar is pressed the game starts and the music starts, the character starts running across the screen and enemies move                           towards them from the left.
                        - The player needs to press spacebar to jump over the enemies and to increase their score. 
                        - Once the player hits an enemy the game ends and the final score is displayed. 
                        - The game can be replayed and the player can try and beat their last score. 

                - tournament:
                        - This project was made while I was studying cs50 in 2022.
                        - This project runs a simulation of the FIFA world cup using csv files with real world data on different countries winning
                          statistics. 
                        - The program simulates 1000 tournaments and then displays the % chance each country is likely to win the tournament.
