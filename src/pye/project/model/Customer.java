package pye.project.model;

import java.util.ArrayList;

/**
 * This particular class represents a customer.
 *
 * @author Gaurav Desai, Aishwarya Venketeswaran, Aauysh Neupane and Ambrin Ali
 */
public class Customer extends Person
{
    
/** Constructor for Customer.
 * A Customer object is a Person object. Hence, Customer extends Person. 
 * 
 * @param personID represents the ID for the database.
 * @param lastName the last name of the person
 * @param firstName the first name of the person. 
 * @param email the email address of the person. 
 */
    public Customer(int personID, String lastName, String firstName,
            String email)
    {
        super(personID, lastName, firstName, email);
    }

 /** Allows a Customer to share the reservation with other people.
  * 
  * @param emailAddresses the email addresses to which the email about the 
  * reservation has to be sent.
  * @param type the type of email that has to be sent. The type determines the 
  * type of information in the email.
  */
    public void shareReservation(ArrayList<String> emailAddresses, String type)
    {
        //EmailSender.sendMail(emailAddresses,null, "Participant");
        // use the email sender to share the reservation with others
    }
}
