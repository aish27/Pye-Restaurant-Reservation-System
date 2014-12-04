package pye.project.model;


import java.util.ArrayList;






/** */
public class EmailSender
{
    /** */
    public int id;
    
    /** */
    public String fromEmailAddress;
    
    /* Sends emails to all the required email addresses
    */
    public static void sendMail(ArrayList<String> emailAddresses, String emailAddressOfOnePerson, String type)
    {
    //Types can be manager, reservationCreator and Participant. 
    //The text in the email will be different depending on the type
    }
}
