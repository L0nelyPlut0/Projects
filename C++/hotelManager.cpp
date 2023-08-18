#include <iostream>
#include <string.h>
#include <stdio.h>
#define max 100
using std::cin, std::cout, std::endl;

// Class Customer displaying customer details
class Customer
{
public:
    char name[100]; // max input for customer inputed things
    char address[100];
    char phone[12];
    char from_date[20];
    char to_date[20];
    float payment_advance;
    int booking_id;
};

class Room // room class for infomration about rooms avaliable
{
public:
    char type;
    char stype;
    char ac;
    int roomNumber;
    int rent;
    int status;

    class Customer cust; // extra part of class for adding rooms to customer data
    class Room addRoom(int);
    void searchRoom(int);
    void deleteRoom(int);
    void displayRoom(Room);
};

// Global Declarations
class Room rooms[max]; // max rooms set
int numCount = 0;

Room Room::addRoom(int rno) // function adding a room to the list of rooms at hotel
{
    // function where hotel manager can add all their rooms and their features
    class Room room;
    room.roomNumber = rno; // rno  = room number
    cout << "\nType AC/Non-AC (AC/No AC) = ('A'/'N') : ";
    cin >> room.ac;
    cout << "\nType Comfort (Super/Normal) = ('S'/'N') : ";
    cin >> room.type;
    cout << "\nType Size (Big/Small)= ('B'/'S'): ";
    cin >> room.stype;
    cout << "\nDaily Rent : ";
    cin >> room.rent;
    room.status = 0;

    cout << "\n Room Added Successfully!";
    getchar();
    return room;
}
void Room::searchRoom(int rno) // searching to see if a room exists, uses rno to check
{
    int i, found = 0;
    for (i = 0; i < numCount; i++)
    {
        if (rooms[i].roomNumber == rno)
        {
            found = 1;
            break;
        }
    }
    if (found == 1)
    {
        cout << "Room Details\n";
        if (rooms[i].status == 1) // checks if room is reserved
        {
            cout << "\nRoom is Reserved";
        }
        else
        {
            cout << "\nRoom is available";
        }
        displayRoom(rooms[i]); // shows details of found room
        getchar();
    }
    else
    {
        cout << "\nRoom not found"; // prints if room not found
        getchar();
    }
}

void Room::displayRoom(Room tempRoom) // function for a temp room to be created
{
    cout << "\nRoom Number: \t" << tempRoom.roomNumber;
    cout << "\nType AC/Non-AC (A/N) " << tempRoom.ac;
    cout << "\nType Comfort (S/N) " << tempRoom.type;
    cout << "\nType Size (B/S) " << tempRoom.stype;
    cout << "\nRent: " << tempRoom.rent;
}

// hotel management class
class HotelMgnt : protected Room // class for manager to use to manage all the rooms they have
{
public:
    void checkIn();
    void getAvailRoom();
    void searchCustomer(char *);
    void checkOut(int);
    void guestSummaryReport();
};

void HotelMgnt::guestSummaryReport() // summary report for all guests in hotel currently
{

    if (numCount == 0)
    {
        cout << "\n No Guest in Hotel !!";
    }
    for (int i = 0; i < numCount; i++)
    {
        if (rooms[i].status == 1) // if room is occupied print out the customers details
        {
            cout << "\n Customer First Name : " << rooms[i].cust.name;
            cout << "\n Room Number : " << rooms[i].roomNumber;
            cout << "\n Address (only city) : " << rooms[i].cust.address;
            cout << "\n Phone : " << rooms[i].cust.phone;
            cout << "\n---------------------------------------";
        }
    }

    getchar();
}

// hotel management reservation of room
void HotelMgnt::checkIn() // function to book out rooms
{
    int i, found = 0, rno;

    class Room room;
    cout << "\nEnter Room number : "; // loops through and checks if entered room number exists, then if not taken it books room
    cin >> rno;
    for (i = 0; i < numCount; i++)
    {
        if (rooms[i].roomNumber == rno)
        {
            found = 1;
            break;
        }
    }
    if (found == 1) // if room found and is taken prints out room is already booked
    {
        if (rooms[i].status == 1)
        {
            cout << "\nRoom is already Booked";
            getchar();
            return;
        }

        // all output and input to assigned a customer to a room, fills in all their details
        cout << "\nEnter booking id: ";
        cin >> rooms[i].cust.booking_id;

        cout << "\nEnter Customer Name (First Name): ";
        cin >> rooms[i].cust.name;

        cout << "\nEnter Address (only city): ";
        cin >> rooms[i].cust.address;

        cout << "\nEnter Phone: ";
        cin >> rooms[i].cust.phone;

        cout << "\nEnter Check-In Date: ";
        cin >> rooms[i].cust.from_date;

        cout << "\nEnter Check-Out Date: ";
        cin >> rooms[i].cust.to_date;

        cout << "\nEnter Advance Payment: ";
        cin >> rooms[i].cust.payment_advance;

        rooms[i].status = 1;

        cout << "\n Customer Checked-in Successfully.\n";
        getchar();
    }
}

// hotel management shows available rooms
void HotelMgnt::getAvailRoom() // checks to see whic rooms out of total rooms are avalible
{
    int i, found = 0;
    for (i = 0; i < numCount; i++)
    {
        if (rooms[i].status == 0) // if room is avalible print it out with display room funct
        {
            displayRoom(rooms[i]);
            cout << "\n\nPress enter for next room";
            found = 1;
            getchar();
        }
    }
    if (found == 0) // if found is 0 then room is already taken
    {
        cout << "\nAll rooms are reserved";
        getchar();
    }
}

// hotel management shows all persons that have booked room
void HotelMgnt::searchCustomer(char *pname) // function to look for a customer, using a pointer to their name
{
    int i, found = 0;
    for (i = 0; i < numCount; i++)
    {
        if (rooms[i].status == 1 && strcmp(rooms[i].cust.name, pname) == 0) // if room is taken and the room name matches customer name inputed print out that room number and persons name
        {
            cout << "\nCustomer Name: " << rooms[i].cust.name;
            cout << "\nRoom Number: " << rooms[i].roomNumber;

            cout << "\n\nPress enter for next record";
            found = 1;
            getchar();
        }
    }
    if (found == 0) // else if its not matched person isnt found
    {
        cout << "\nPerson not found.";
        getchar();
    }
}

// hotel managemt generates the bill of the expenses
void HotelMgnt::checkOut(int roomNum)
{
    int i, found = 0, days, rno;
    float billAmount = 0; // starts bill at 0
    for (i = 0; i < numCount; i++)
    {
        if (rooms[i].status == 1 && rooms[i].roomNumber == roomNum) // if room is taken and the current room number matches the room number looked for
        {
            rno = rooms[i].roomNumber;
            found = 1;
            getchar();
            break;
        }
    }
    if (found == 1)
    {
        cout << "\nEnter Number of Days:\t";
        cin >> days;
        billAmount = days * rooms[i].rent; // calculates amount customer has to pay with manager inputed prices

        // displays output for customer bill, neatly formatted
        cout << "\n\t######## CheckOut Details ########\n";
        cout << "\nCustomer Name : " << rooms[i].cust.name;
        cout << "\nRoom Number : " << rooms[i].roomNumber;
        cout << "\nAddress : " << rooms[i].cust.address;
        cout << "\nPhone : " << rooms[i].cust.phone;
        cout << "\nTotal Amount Due : " << billAmount << " /";
        cout << "\nAdvance Paid: " << rooms[i].cust.payment_advance << " /";
        cout << "\n*** Total Payable: " << billAmount - rooms[i].cust.payment_advance << "/ only";

        rooms[i].status = 0; // sets their room to empty again becuase they've checked out
    }
    getchar();
}

// managing rooms (adding and searching available rooms)
void manageRooms()
{
    class Room room;
    int opt, rno, i, flag = 0;
    char ch;
    do
    {
        system("clear");
        cout << "\n### Manage Rooms ###";
        cout << "\n1. Add Room";
        cout << "\n2. Search Room";
        cout << "\n3. Back to Main Menu";
        cout << "\n\nEnter Option: ";
        cin >> opt;

        // switch statement
        switch (opt) // used to check which option is chosen
        {
        case 1:
            cout << "\nEnter Room Number: ";
            cin >> rno;
            i = 0;
            for (i = 0; i < numCount; i++)
            {
                if (rooms[i].roomNumber == rno)
                {
                    flag = 1;
                }
            }
            if (flag == 1)
            {
                cout << "\nRoom Number is Present.\nPlease enter unique Number";
                flag = 0; // checks if room exisits
                getchar();
            }
            else
            {
                rooms[numCount] = room.addRoom(rno); // if not it adds the room to total room count
                numCount++;
            }
            break;
        case 2:
            cout << "\nEnter room number: ";
            cin >> rno;
            room.searchRoom(rno);
            break;
        case 3:
            // nothing to do, just takes user back to main menu
            break;
        default:
            cout << "\nPlease Enter correct option";
            break;
        }
    } while (opt != 3); // loop through until user selects 3
}
using std::cin, std::cout, std::endl;
int main()
{
    // setting up main function variables and classes to be used
    class HotelMgnt hm;
    int i, j, opt, rno;
    char ch;
    char pname[100];

    system("clear");

    do
    {
        system("clear"); // main menus displayed
        cout << "######## Hotel Management for the OverLook Hotel#########\n";
        cout << "\n1. Manage Rooms";
        cout << "\n2. Check-In Room";
        cout << "\n3. Available Rooms";
        cout << "\n4. Search Customer";
        cout << "\n5. Check-Out Room";
        cout << "\n6. Guest Summary Report";
        cout << "\n7. Exit";
        cout << "\n\nEnter Option: ";
        cin >> opt;  // takes in user choice
        switch (opt) // switches through choice and executes the correct function
        {
        case 1:
            manageRooms();
            break;
        case 2:
            if (numCount == 0)
            {
                cout << "\nRooms data is not available.\nPlease add the rooms first.";
                getchar();
            }
            else
                hm.checkIn();
            break;
        case 3:
            if (numCount == 0)
            {
                cout << "\nRooms data is not available.\nPlease add the rooms first.";
                getchar();
            }
            else
                hm.getAvailRoom();
            break;
        case 4:
            if (numCount == 0)
            {
                cout << "\nRooms are not available.\nPlease add the rooms first.";
                getchar();
            }
            else
            {
                cout << "Enter Customer Name: ";
                cin >> pname;
                hm.searchCustomer(pname);
            }
            break;
        case 5:
            if (numCount == 0)
            {
                cout << "\nRooms are not available.\nPlease add the rooms first.";
                getchar();
            }
            else
            {
                cout << "Enter Room Number : ";
                cin >> rno;
                hm.checkOut(rno);
            }
            break;
        case 6:
            hm.guestSummaryReport();
            break;
        case 7:
            cout << "\nTHANK YOU! FOR USING THE OVERLOOK HOTEL SOFTWARE\n";
            break;
        default:
            cout << "\nPlease Enter correct option";
            break;
        }
    } while (opt != 7); // which choice is not exit option

    getchar();
}