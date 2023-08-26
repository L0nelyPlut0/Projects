/* class for both manager and salesperson, they are both the same, only the role is different,
I didn't feel the need to make a superclass 'StaffMember' and have two subclasses: 'Salesperson' 
and 'Manager' because there is next to no difference and in my implementation they don't inherit or 
do anything too special */

public class StaffMember {
    // data fields to store staff member's specific username, password and their role
    private String userName;
    private String password;
    private String role;

    // constructor for staff member, setting username, password and role inside constructor
    StaffMember(String userName, String password, String role) {
        setUsername(userName);
        setPassword(password);
        setRole(role);
    }

    // setter method for username with error checking 
    public void setUsername(String userName) {

        if (userName.equals(null) || userName.equals("")) {
            System.out.println("Error, username can not be null. ");
            System.exit(1);
        }
        else {
        this.userName = userName;
        }
    }

    // getter method for username 
    public String getUserName() {
        return userName;
    }

    // setter method for password with error checking
    public void setPassword(String password) {

        if (password.equals(null) || password.equals("")) {
            System.out.println("Error, password can not be null. ");
        }
        else {
        this.password = password;
        }
    }

    // getter for password 
    public String getPassword() {
        return password;
    }


    // setter for role with error checking
    public void setRole(String role) {

        if (role.equals(null) || role.equals("")){
            System.out.println("Error, role can not be null. ");
        }
        else {
        this.role = role;
        }
    }

    // getter method for role
    public String getRole() {
        return role;
    }

    // method to check if the role is equal to manager, returns a bool indicating if yes or no
    public boolean isManager() {
        return getRole().equals("Manager");
    }

}
