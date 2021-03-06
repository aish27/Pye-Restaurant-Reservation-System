package pye.project.model;
import java.util.ArrayList;

public class AllSeatsInARestaurant
{
    /** */
    public int id;
    
    /** */
    public int[] seats;
    
    /** */
    public int nextAvailableSeat;
    
    
    public AllSeatsInARestaurant(int totalSeatsInRestaurant)
    {
       seats = new int[totalSeatsInRestaurant]; 
    }
    
    /**
     * @return  arraylist of all the seats in this particular restaurant
     */
    public int[] returnSeats(String name)
    {
        //search the database and get all its seats
        return seats;
    }
    
    public void newReservationMade(int reservationId, int partySize)
    {
        for(int i = 0; i< partySize; i++)
        {
            seats[nextAvailableSeat] = reservationId;
            nextAvailableSeat++;
        }
    }
}
