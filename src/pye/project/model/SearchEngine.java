package pye.project.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  */
public class SearchEngine {
    private static Object dao;

    static Reservation searchReservation(int ConfirmationNumber, String lastName) {
        DataAccess dao = null;
        try {
            dao = new DataAccess();
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reservation res = null;
        try {
            res = dao.searchReservation(ConfirmationNumber, lastName);
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Search: " + res.ConfirmationNumber);
        // Call DataAccess and Match Method Signitures

        return res;
    }

    /**public static ArrayList<Restaurant> searchRestaurants(String city, int time, int seats) {
        DataAccess dao = null;
        try {
            dao = new DataAccess();
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Restaurant> list = null;
        try {
            //System.out.println(city+","+cuisine+","+time+","+price+","+","+seats);
            list = dao.searchRestaurants(city, time, seats);
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Restaurant d : list) {
            System.out.println(list.toString());
        }

        return list;
    }*/
    
    /**public static ArrayList<Restaurant> searchRestaurants(String city, int time, int price, int seats) {
        DataAccess dao = null;
        try {
            dao = new DataAccess();
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Restaurant> list = null;
        try {
            //System.out.println(city+","+cuisine+","+time+","+price+","+","+seats);
            list = dao.searchRestaurants(city, time, price, seats);
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Restaurant d : list) {
            System.out.println(list.toString());
        }

        return list;
    }*/
        
       /** public static ArrayList<Restaurant> searchRestaurants(String city, String cuisine, int time, int seats) {
        DataAccess dao = null;
        try {
            dao = new DataAccess();
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Restaurant> list = null;
        try {
            //System.out.println(city+","+cuisine+","+time+","+price+","+","+seats);
            list = dao.searchRestaurants(city, time, seats);
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Restaurant d : list) {
            System.out.println(list.toString());
        }

        return list;
    }*/
        


    /**
     * Searches the database and returns all the restaurants that fit the
     * required parameters
     */
    // public List<Restaurant> searchRestaurants(String reqLocation, String reqCuisine, int reqTime,
    //        int reqPprice, int reqSeats)
    //
 public static ArrayList<Restaurant> searchRestaurants(String city, String cuisine, int time, int price, int seats) {
    //use the parameters to search the database and return an arraylist of 
        DataAccess dao = null;
        try {
            dao = new DataAccess();
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Restaurant> list = null;
        try {
            System.out.println(city+","+cuisine+","+time+","+price+","+","+seats);
            list = dao.searchRestaurants(city, cuisine, time, price, seats);
        } catch (Exception ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Restaurant d : list) {
            System.out.println(list.toString());
        }

        return list;
    }
    
    /**public List<Restaurant> searchRestaurants(String city, String cuisine, int time, int price, int seats) throws SQLException {
        dao = new DataAccess();
        List <Restaurant> list = dao.searchRestaurants(city, cuisine, time, price, seats);
        for (Restaurant d : list); 
    }*/

    /**
     *
     * @param ConfirmationNumber
     * @param lastName
     * @return
     * @throws Exception
     */
    public static Reservation searchReservation(int ConfirmationNumber) throws Exception {

        DataAccess dao = new DataAccess();
        Reservation res = dao.searchReservation(ConfirmationNumber);
        System.out.println("Search: " + res.ConfirmationNumber);
        // Call DataAccess and Match Method Signitures

        return res;

    }

    public static Restaurant searchRestaurant(int id) {
    //Return the restaurant whose id number is 'id'

        // Call DataAccess and Match Method Signitures
        return null;
    }



}
