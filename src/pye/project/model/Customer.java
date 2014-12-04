package pye.project.model;


import java.util.ArrayList;




/** */
public class Customer extends Person
{

    public Customer(int id, String lastName, String firstName, String email) {
        super(id, lastName, firstName, email);
    }
    /** */
    public void shareReservation(ArrayList<String> emailAddresses, String type)
    {
        EmailSender.sendMail(emailAddresses,null, "Participant");
        // use the email sender to share the reservation with others
    }
}
