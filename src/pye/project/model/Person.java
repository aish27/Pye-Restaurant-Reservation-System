package pye.project.model;

/**
 * This particular class represents a person.
 *
 * @author Gaurav Desai, Aishwarya Venketeswaran, Aauysh Neupane and Ambrin Ali
 */
public class Person
{

    /**
     * Represents the if of a person object in the database.
     */
    private int id;

    /**
     * Represents the first name of a person.
     */
    private String firstName;

    /**
     * Represents the last name of a person.
     */
    private String lastName;

    /**
     * Represents the email address of a person.
     */
    private String email;

    /**Constructor of a Person object. 
     * 
     * @param personID thee id of the object used in the database.
     * @param lastName the last name of a person.
     * @param firstName the first name of a person.
     * @param email the email address of a person.
     */
    public Person(int personID, String lastName, String firstName, String email)
    {
        super();
        this.id = personID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    /** Returns the id of this person object.
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /** Returns the first name of this person.
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /** Returns the last name of this person.
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /** Returns the email address of this person.
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

}
