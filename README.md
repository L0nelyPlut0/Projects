Welcome to my projects repo!

These were made from mid 2022 until currently August 2023 (I will likely add more as I make more).

The projects here are sorted into language:


# Android (Java):

##Workout app:
This was made in the semester break of 2023, to help me learn Android development.
Displays workouts, details, and stopwatch.
Works well on phones and tablets, adapting to each screen.


# C++:

## hotelManager.cpp:
This was made mid 2022, to help me better understand C++ and a small amount of OOP.
This is a terminal-based C++ file that allows the user to manage a hotel.
Different rooms can be set as booked out, and the user can also search if a specific room is free.


# C:
## caesar.c:
This file was made while I was studying cs50 in mid 2022.
This file scrambles a word based on a user-inputted number key.
This moves all of the letters of your inputted phrase either forward or backward in the alphabet,
then prints out the coded message.
Example:
Enter into the terminal after compilation './caesar 3' and scrambled text will be in the terminal.
Key = 3, plaintext = 'Hello, World!", and scrambled output = 'Khoor, Zruog!"

## runoff.c:
This was also made mid 2022 when I was studying cs50.
This file simulates a runoff election using candidates which the user inputs. It then asks for the amount
of voters in the election and will run through all the inputted candidates for each voter and ask for the
voter's preference on the candidates. It will then calculate the winner of the electionand output the winner.

## scrabble.c:
This was also made mid 2022 while I was studying cs50.
This file takes in 2 words from 2 players. It then scores them against the letter scoring in the game 'Scrabble'
and displays the winner with the most points.
There is error checking to ensure that the players are entering single words only.


# CyberSecurity:
This folder is a collection of scripts made to help me learn about some cybersecurity things in July of 2023.

## PortScanner:
This python script is a port scanner which takes in an IP address, the ports wanted to scan, and the time limit for each port.
It goes through all the specified ports in the range and checks if they are open. Printing any open ports out to the terminal.
This script has been made to run a little faster using multithreading.

## WireSharkGeoTracker:
This is a simple python script that uses packet capturing in WireShark to get the source location of packets captured.
It prints out source locations from a captiured IP address to the terminal.

## dictPasswordCracker:
This is a folder with 2 scripts and 2 txt files.
The txt files are a list of 10 million most common passwords and a list of hashed values for these passwords.
The hashed_values.txt can be remade again using hashMaker.py. Which takes in 10-million-password-list-top-100000000.txt and
uses different hashing algorithms to hash them all randomly each time.
dictCracker.py can then be run, this takes a random hashed value from hashed_values.txt and un-hashes them,
then compares them to the list of passwords. Printing out the password, the hashing algorithm used, and the hash value.


# Java:
These 2 folders are from mid 2023, when I was studying OOP at the university.

## ComputerStoreSwingGUI:
This folder is a GUI for a computer store using swing.
The GUI is different for managers and salespersons, allowing managers to have more functions in actually changing things.
A list of devices is displayed in a table and the user can click on them to view that specific device.
The user can sort the table by device type and category, or choose to display different categories and types based on selected filters.

## LibraryCatalogue:
This folder is a terminal-based Java program that displays a list of items (movies, books, and journals) from a supplied txt file.
The user can navigate through the menu by typing in commands. The list can be sorted based on rating and title.
The user can search for a specific title, rent it out, return it, and rate it.


# Python:

## Runner game - pygames intro:
This folder is a simple side-scrolling runner game I made towards the end of 2022 to help me learn Python and some Pygame.
This project was really helpful in me learning Python and OOP, and it was fun to make.
The game opens on a start screen where the user is prompted to press the spacebar.
Once spacebar is pressed, the game starts, and the music starts, the character runs, enemies run from the right.
The player needs to press the spacebar to jump over the enemies and to increase their score.
Once the player hits an enemy, the game ends, and the final score is displayed.

## tournament:
  This project was made while I was studying cs50 in 2022.
  This project runs a simulation of the FIFA World Cup using CSV files with real-world data.
  The program simulates 1000 tournaments and then displays the % chance each country is likely to win the tournament.
