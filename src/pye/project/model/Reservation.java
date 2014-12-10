package pye.project.model;

import java.util.Date;

/**This particular class represents a reservation.
 * 
 * @author Gaurav Desai, Aishwarya Venketeswaran, Aauysh Neupane and Ambrin Ali 
 */
public class Reservation
{

    /**Represents the reservation id of the reservation in the database.
     * 
     */
    public int id;

    /** Represents the confirmation status of the reservation.
     *    
     */
    public Boolean Confirmed;

    /**Represents the time of this reservation. 
     *      
     */
    private int reqTime;

    /**Represents the date of this reservation. 
     *      
     */
    private Date reqDate;

    /**Represents the confirmation number assigned to this reservation. 
     *      
     */
    public int ConfirmationNumber;

    /**Represents the party size of this reservation. 
     *      
     */
    private int PartySize;

    /**Represents the person who created this reservation. 
     *     
     */
    public Person CreatedBy;

    /** Represents the restaurant in which this reservation has been made. 
     * 
     */
    public Restaurant restaurant;

   /**Constructor for reservation.
     * 
     * @param id the reservation id of the reservation
     * @param confirmed the confirmation status 
     * @param reqTime the time of the reservation as an integer
     * @param reqDate the date of the reservation
     * @param ConfirmationNumber the confirmation number of this reservation
     * @param restaurant the restaurant in which the reservation is made
     * @param PartySize the party size of the reservation
     * @param CreatedBy the person who created the reservation
     */
    public Reservation(int id, Boolean confirmed, int reqTime, Date reqDate, int ConfirmationNumber, int PartySize, Person CreatedBy, Restaurant restaurant)
    {
        this.id = id;
        this.Confirmed = confirmed;
        this.reqTime = reqTime;
        this.reqDate = reqDate;
        this.ConfirmationNumber = ConfirmationNumber;
        this.PartySize = PartySize;
        this.CreatedBy = CreatedBy;
        this.restaurant = restaurant;

        //add it to the database
    }

    /**Constructor for reservation.
     * 
     * @param ReservationID the reservation id of the reservation
     * @param confirmed the confirmation status 
     * @param intTime the time of the reservation as an integer
     * @param reqDate the date of the reservation
     * @param confirmationNumber the confirmation number of this reservation
     * @param restaurant the restaurant in which the reservation is made
     * @param partySize the party size of the reservation
     * @param CreatedBy the person who created the reservation
     */
    Reservation(int ReservationID, boolean confirmed, int reqTime, Date reqDate, int ConfirmationNumber, int PartySize, Person CreatedBy)
    {
        this.id = ReservationID;
        this.Confirmed = confirmed;
        this.reqTime = reqTime;
        this.reqDate = reqDate;
        this.ConfirmationNumber = ConfirmationNumber;
        this.PartySize = PartySize;
        this.CreatedBy = CreatedBy;
        //this.restaurant = restaurant;
    }

    /**Constructor for reservation.
     * 
     * @param ReservationID the reservation id of the reservation
     * @param confirmed the confirmation status 
     * @param intTime the time of the reservation as an integer
     * @param reqDate the date of the reservation
     * @param confirmationNumber the confirmation number of this reservation
     * @param restaurant the restaurant in which the reservation is made
     * @param partySize the party size of the reservation
     * @param CreatedBy the person who created the reservation
     */
    public Reservation(int ReservationID, boolean confirmed, int intTime, java.sql.Date reqDate, int confirmationNumber, int partySize, Person CreatedBy, Restaurant restaurant)
    {
        this.id = ReservationID;
        this.Confirmed = confirmed;
        this.reqTime = intTime;
        this.reqDate = reqDate;
        this.ConfirmationNumber = confirmationNumber;
        this.PartySize = partySize;
        this.CreatedBy = CreatedBy;
        this.restaurant = restaurant;
    }


    /**Confirms the reservation. 
     * 
     */
    public void confirmReservation()
    {
        this.Confirmed = true;
    }

    /**Returns the confirmation status. 
     * 
     * @return the Confirmed status 
     */
    public Boolean getConfirmationStatus()
    {
        return Confirmed;
    }


    /**Returns the Person who created the reservation
     * 
     * @return the Person object that created the reservation.
     */
    public Person getCreatedPerson()
    {
        return CreatedBy;
    }


    /**Retursn the party size of this reservation.
     * 
     * @return the PartySize
     */
    public int getPartySize()
    {
        return PartySize;
    }

    /**Returns the time of this reservation.
     * 
     * @return the reqTime
     */
    public int getReqTime()
    {
        return reqTime;
    }

    /** Returns the date of this reservation.
     * 
     * @return the reqDate
     */
    public Date getReqDate()
    {
        return reqDate;
    }

    /** Sets the party size of this reservation.
     * 
     * @param newPartySize the party size
     */
    public void setPartySize(int newPartySize)
    {
        PartySize = newPartySize;
    }

    /** Sets the time of this reservation.
     * 
     * @param reqTime the reqTime to set
     */
    public void setReqTime(int reqTime)
    {
        this.reqTime = reqTime;
    }

    /** Sets the date of this reservation.
     * 
     * @param reqDate the reqDate to set
     */
    public void setReqDate(Date reqDate)
    {
        this.reqDate = reqDate;
    }

    /** Converts the reservation into a String.
     * 
     * @return String reservation
     */
    @Override
    public String toString()
    {
        return String.format("Reservation [id=%d, Requested Time=%d, Requested Date=%s, Conf #=%d, Party size=%d, Created By=%s]",
                        id, reqTime, reqDate, ConfirmationNumber, PartySize, CreatedBy.getFirstName());
    }

}
