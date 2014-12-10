package pye.project.model;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * This email sender uses Gmail's smtp to send email. 
 * The calling program needs to send (ArrayList, Restaurant obj) as arguments. 
 * @author Aayush Neupane
 *
 */
public class EmailSender {
	
    private String emailAddressTo = new String();
    private String msgSubject = new String();
    private String msgText = new String();

    final String USER_NAME = "rssemail2014@gmail.com";   //User name of the Goole(gmail) account
    final String PASSSWORD = "CS151_NAMENOTFOUND";  //Password of the Goole(gmail) account
    final String FROM_ADDRESS = "rssemail2014@gmail.com";  //From addresss
 
    public EmailSender() {
    }

    /**
     * Send email to the email addresses in list of email
     * Get the info about the reservation from r
     * Sends two diff types of emails based on 'type' 
     * @param listOfEmails - arraylist of emails
     * @param r - reservation object
     */
	public void sendMail(ArrayList<String> listOfEmails, String email, String type, Reservation r) 
	{
		String msg, emailAddresTo, msgSubject;
		String first = r.CreatedBy.getFirstName();
		String last =  r.CreatedBy.getLastName();
		String location = r.restaurant.getCity();
		Date date = r.getReqDate();
		String name = r.restaurant.getName();
		int time = r.getReqTime();
		
		//send mail if manager
		if (type.equalsIgnoreCase("Manager"))
		{
			if (email != null)
				{
					msg = "You have a new Reservation for: \n" 
							+ "  Name: " + first + " " + last 
							+ ", Date: " + date 
							+ ", At: "+ time 
							+ ", For party of: "+ r.getPartySize();
					this.emailAddressTo = email;
					this.msgSubject = "You have a new reservation!";
					this.msgText = msg;
					sendEmailMessage();
				}
		}
		//send mail if not manager aka customer
		else
		{
			if ((listOfEmails.size()) != 0 && (r != null))
		    	for (int num = 0; num < listOfEmails.size(); num++)
		    	{
			    	msg = "Hi! \n" 
			    				+ first + " shared a reservation with you" 
			    				+ " for " + date 
			    				+ " at " + name 
			    				+ ", " + location 
			    				+ " at " + time +  "."
			    				+ " See you there!\n";
			        this.emailAddressTo = listOfEmails.get(num);
			        this.msgSubject = first + " shared a Reservation with you.";
			        
			        this.msgText = msg;
			        sendEmailMessage();
		    	}
		}
	}
    private void sendEmailMessage() {
     
     //Create email sending properties
     Properties props = new Properties();
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");
  
    Session session = Session.getInstance(props,
    new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(USER_NAME, PASSSWORD);
   }
    });

  try {

     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress(FROM_ADDRESS)); //Set from address of the email
     message.setContent(msgText,"text/html"); //set content type of the email
     
    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailAddressTo)); //Set email recipient
    
    message.setSubject(msgSubject); //Set email message subject
    Transport.send(message); //Send email message

   System.out.println("sent email successfully!");

  } catch (MessagingException e) {
       throw new RuntimeException(e);
  }
    }

    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    public void setSubject(String subject) {
        this.msgSubject = subject;
    }

    public void setMessageText(String msgText) {
        this.msgText = msgText;
    }
   
    public static void main(String[] args) throws Exception {
        EmailSender es = new EmailSender();
        DataAccess dao = new DataAccess();
        Reservation r;
        ArrayList<String> emails = new ArrayList<String>();
        emails.add("rssemail2014@gmail.com"); //sends email to self as test
		try {
			r = dao.getReservation(1);
	//		es.sendMail(emails, null, "Customer", r); //send email as customer
			es.sendMail(null,"rssemail2014@gmail.com", "Manager", r); //send email as manager
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
      }

    


  
}