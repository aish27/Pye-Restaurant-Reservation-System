package pye.project.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//import sun.util.calendar.JulianCalendar;


/** */
public class RestaurantCalendar extends GregorianCalendar
{
    /** */
    private int id;
    
    /** */
    private int month;
    
    /** */
   // private DayOfMonth day;
    
    /** */
  //  private Hour hours;
    
    public RestaurantCalendar(int startTimeOfRestaurant, int closeTimeOfRestaurant, int totalSeatsInRestaurant)
    {
     //  day = new DayOfMonth(startTimeOfRestaurant, closeTimeOfRestaurant, totalSeatsInRestaurant);
    }
    
    /** */
    public ArrayList<Integer> availableTimeSlots(int time_low, int time_high)
    {
        ArrayList<Integer> allSlots = new ArrayList<Integer>();
        //search the database
        //find this restaurant
        //return time slots in this particular range
        
        return allSlots;
    }
    
    /** */
    public int availableSeats(Date reqDate, int reqTime)
    {
    //Go to database.
    //For this restaurant, return the number of seats aailable at this particular time
        return 0;
    }
    
    /*
    /** */
    /*public int getTotalSeats()
    {
    //Go to database.
    //For this restaurant, return the totalNumberOfSeats at this particular time
        return 0;
    }*/
    
    public void reserveSeats(Date reqDate, int reqTime, int partySize)
    {
        // in the databse, reduce or increase the number of available seats at this particular time
    }
    
    
    public boolean checkIfAvailabilableAt(Date reqDate, int reqTime, int PartySize)
    {
        return (availableSeats(reqDate,reqTime)  - PartySize) > 0;
    }
    
    public void addReservation(Reservation someReservation)
    {
        reserveSeats(someReservation.getReqDate(),someReservation.getReqTime(), someReservation.getPartySize());
        //update the reservation database
    }

    public void deleteReservation(Reservation someReservation)
    {
        reserveSeats(someReservation.getReqDate(),someReservation.getReqTime(), (-1) * someReservation.getPartySize());
        //update the reservation in the database
    }
    
    public void updateReservation(Reservation theReservation, int reqTime, Date reqDate, int partySize)
    {
            
        //increase the seats in the old time
        reserveSeats(theReservation.getReqDate(),theReservation.getReqTime(),(-1) * theReservation.getPartySize());
        theReservation.setPartySize(partySize);
        theReservation.setReqTime(reqTime);
        theReservation.setReqDate(reqDate);
        // update the database with the new one.
        reserveSeats(reqDate,reqTime,partySize); 
    }

}
