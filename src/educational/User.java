package educational;

public abstract class User implements EducationalUser {
    // attributes
    protected String userName;
    protected String password;
    protected  int userID;
    protected String fName;
    protected String lName;
    protected String email;

    // constructors
    public User() {}

    public User(String userName, String password, String fName, String lName, String email) {
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
        return fName + " " + lName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public  void setUserID(int userID) {
        this.userID = userID;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // methods
    public abstract boolean login(String username ,String password);
//    public abstract void signUp(String username, String password, String fName, String lName, String email);

    public boolean logout() {
        return true;
    }

    // view profile
    public void viewProfile() {
        System.out.println("Full Name: " + getFullName());
        System.out.println("User Name: " + this.userName);
        System.out.println("User ID: " + this.userID);
        System.out.println("Email: " + this.email);
    }
}
