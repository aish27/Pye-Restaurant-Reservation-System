package pye.project.model;

import java.util.Date;



/** */
public class Reservation
{
    /** */
    public int id;
    
    /** */
    public Boolean Confirmed;
    
    /** */
    private int reqTime;
    
    /** */
    private Date reqDate;
    
    /** */
    public int ConfirmationNumber;
    
    /** */
    private int PartySize;
    
    /** */
    public Person CreatedBy;
    
    public Restaurant restaurant;

    public Reservation(int id, Boolean Confirmed, int reqTime, Date reqDate, int ConfirmationNumber, int PartySize, Person CreatedBy, Restaurant restaurant) {
        this.id = id;
        this.Confirmed = Confirmed;
        this.reqTime = reqTime;
        this.reqDate = reqDate;
        this.ConfirmationNumber = ConfirmationNumber;
        this.PartySize = PartySize;
        this.CreatedBy = CreatedBy;
        this.restaurant = restaurant;
        
        //add it to the database
    }

    Reservation(int i, boolean b, int reqTime, Date reqDate, int ConfirmationNumber, int PartySize, Person CreatedBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reservation(int ReservationID, boolean b, int intTime, java.sql.Date reqDate, int conf, int party, Person CreatedBy, Restaurant restaurant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    /** */
    public void setReservation(Boolean Confirmed, int reqTime, Date reqDate, int RestaurantId, int ConfirmationNumber, int PartySize, Person CreatedBy)
    {
    //change the object as required.
    // save the change into the database
    }
    
    /** */
    public void confirmReservation()
    {
        this.Confirmed = true;
    }
    
    /** */
    public Boolean getConfirmationStatus()
    {
        return Confirmed;
    }
    
    /** */
    public Person getCreatedPerson()
    {
        return  CreatedBy;
    }

    /**
     * @return the PartySize
     */
    public int getPartySize() {
        return PartySize;
    }

    /**
     * @return the reqTime
     */
    public int getReqTime() {
        return reqTime;
    }

    /**
     * @return the reqDate
     */
    public Date getReqDate() {
        return reqDate;
    }
    
    public void setPartySize(int newPartySize) {
        PartySize = newPartySize;
        
        //change this in the database
    }

    /**
     * @param reqTime the reqTime to set
     */
    public void setReqTime(int reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * @param reqDate the reqDate to set
     */
    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }
    
    /**
     * 
     * @return Person object
     */
    public Person getCreatedBy()
    {
    	return CreatedBy;
    }
    
	@Override
	public String toString() {
		return String
				.format("Reservation [id=%d, Requested Time=%d, Requested Date=%s, Conf #=%d, Party size=%d, Created By=%s]",
						id, reqTime, reqDate, ConfirmationNumber, PartySize, CreatedBy.getFirstName());
	}

}