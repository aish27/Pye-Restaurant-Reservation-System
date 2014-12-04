package pye.project.model;


import java.util.ArrayList;



/** */
public class SearchEngine
{

    static Reservation searchReservation(int ConfirmationNumber) throws Exception {
        DataAccess dao = new DataAccess();
        dao.searchReservation(ConfirmationNumber);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    
    }



    public int id;
    
    /**  Searches the database and returns all the restaurants that fit the required parameters
    */
    public static ArrayList<Restaurant> searchRestaurants(String city, String state, int openTime, int endTime, int requiredNumberOfSeats)
    {
    //use the parameters to search the database and return an arraylist of 
        
        // Call DataAccess and Match Method Signitures
        return null;
    }
    
    /**
     *
     * @param ConfirmationNumber
     * @param lastName
     * @return
     * @throws Exception
     */
    static Reservation searchReservation(int ConfirmationNumber, String lastName) throws Exception 
    {
        
        DataAccess dao = new DataAccess();
        dao.searchReservation(ConfirmationNumber, lastName);
        // Call DataAccess and Match Method Signitures
                
        return null;        
    }
    
     public static Restaurant searchRestaurant(int id)
    {
    //Return the restaurant whose id number is 'id'
        
        // Call DataAccess and Match Method Signitures
        return null;
    }
    
}
