public class User implements EducationalUser {
    // attributes
        protected String userName;
        protected String password;
        protected String userID;
        protected String fullName;
        protected String email;

    // constructors
    public User() {}

    public User(String userName, String password, String userID, String fullName, String email) {
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
