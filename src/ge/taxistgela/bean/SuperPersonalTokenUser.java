package ge.taxistgela.bean;

/**
 * Created by GIO on 6/27/2015.
 */
public abstract class SuperPersonalTokenUser extends SuperDaoUser {

    protected String token;
    private String firstName;
    private String lastName;
    private Gender gender;

    public synchronized String getFirstName() {
        return firstName;
    }

    public synchronized void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public synchronized String getLastName() {
        return lastName;
    }

    public synchronized void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public synchronized Gender getGender() {
        return gender;
    }

    public synchronized void setGender(Gender gender) {
        this.gender = gender;
    }

    public abstract String getToken();

}
