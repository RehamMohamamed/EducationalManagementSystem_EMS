package educational;

public class User implements EducationalUser {
    // attributes
        protected String userName;
        protected String password;
        protected int userID;
        protected String fullName;
        protected String email;

    // constructors
    public User() {}

    public User(String userName, String password, int userID, String fullName, String email) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        this.fullName = fullName;
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
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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


    // methods
    public boolean login(String username, String password){
        return this.userName.equals(username) && this.password.equals(password);
    }
    public boolean logout(){
        return true;
    }

    public void viewProfile(){
        System.out.println("Full Name: " + this.fullName);
        System.out.println("User Name: " + this.userName);
        System.out.println("User ID: " + this.userID);
        System.out.println("Email: " + this.email);
    }

}
