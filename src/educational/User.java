package educational;

public class User implements EducationalUser {
    // attributes
    protected String userName;
    protected String password;
    protected static int userID;
    protected String fName;
    protected String lName;
    protected String email;

    // constructors
    public User() {}

    public User(String userName, String password, String fName, String lName , String email) {
        this.userName = userName;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    // getter & setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }
    public String getFullName() {
        return fName + " "  + lName;
    }
    public void setfName(String fName) {this.fName = fName;}
    public void setlName(String lName) {this.lName = lName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public static void setUserID(int userID) {User.userID = userID;}
    public String getfName() {return fName;}
    public String getlName() {return lName;}

    // methods
    public boolean login(String username, String password){
        return this.userName.equals(username) && this.password.equals(password);
    }
    public boolean logout(){
        return true;
    }

//    public void viewProfile(){
//        System.out.println("Full Name: " + getFullName());
//        System.out.println("User Name: " + this.userName);
//        System.out.println("User ID: " + this.userID);
//        System.out.println("Email: " + this.email);
//    }

}