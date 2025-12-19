package educational;

public abstract class User implements EducationalUser {

    protected int userID;
    protected String userName;
    protected String password;
    protected String fName;
    protected String lName;
    protected String email;

    public abstract int getUserID();

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fName + " " + lName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    @Override
    public boolean logout() {
        return true;
    }
}
