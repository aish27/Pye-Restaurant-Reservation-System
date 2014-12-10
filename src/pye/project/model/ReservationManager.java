package pye.project.model;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the system that manages all the reservations and
 * restaurants.
 *
 * @author Gaurav Desai, Aishwarya Venketeswaran, Aayush Neupane and Ambrin Ali
 */
public class ReservationManager
{

    /**
     * The reservation manager stores the restaurant that is currently selected.
     *
     */
    static Restaurant selectedRestaurant;

    /**
     * The id is the id of the reservation manager in the database.
     *
     */
    static int id = 0;

    /**
     * Adds a restaurant to the database.
     *
     * @param phoneNumber the phone number of the restaurant
     * @param city the city in which the restaurant is located
     * @param state the state in which the restaurant is located
     * @param zipcode the zipcode of the restaurant
     * @param openTime the time at which the restaurant opens
     * @param closeTime the time at which the restaurant closes
     * @param cuisine the cuisine of the restaurant
     * @param name the name of the restaurant
     * @param price the average price of the restaurant
     * @param totalNumberOfSeats the total number of seats in the restaurant
     * @param managerEmail the email address of the manager
     */
    public static void addRestaurant(int phoneNumber, String city, String state, int zipcode, int openTime, int closeTime, String cuisine, String name, int price, int totalNumberOfSeats, String managerEmail)
    {
        Restaurant aNewRestaurant = new Restaurant(id++, phoneNumber, city, state, zipcode, openTime, closeTime, cuisine, name, price, totalNumberOfSeats, managerEmail);
    }

    /**
     * Updates a restaurant's information to the database.
     *
     * @param id the restaurant's id in the database
     * @param phoneNumber the phone number of the restaurant
     * @param city the city in which the restaurant is located
     * @param state the state in which the restaurant is located
     * @param zipcode the zipcode of the restaurant
     * @param startTime the time at which the restaurant opens
     * @param endTime the time at which the restaurant closes
     * @param cuisine the cuisine of the restaurant
     * @param name the name of the restaurant
     * @param price the average price of the restaurant
     * @param totalNumberOfSeats the total number of seats in the restaurant
     * @param managerEmail the email address of the manager
     */
    public static String updateRestaurantInformation(int id, int phoneNumber, String city, String state, int zipcode, int startTime, int endTime, String cuisine, String name, int price, int totalNumberOfSeats, String managerEmail, String restaurantName)
    {
        Restaurant toBeChanged = SearchEngine.searchRestaurant(id);
        if (toBeChanged == null)
        {
            return "Restaurant not found";
        }
        if (phoneNumber == -1)
        {
            phoneNumber = toBeChanged.getPhoneNumber();
        }
        if (city == null)
        {
            city = toBeChanged.getCity();
        }
        if (state == null)
        {
            state = toBeChanged.getState();
        }
        if (zipcode == -1)
        {
            zipcode = toBeChanged.getZipcode();
        }
        if (startTime == -1)
        {
            startTime = toBeChanged.getOpenTime();
        }
        if (endTime == -1)
        {
            endTime = toBeChanged.getCloseTime();
        }
        if (cuisine == null)
        {
            cuisine = toBeChanged.getCuisine();
        }
        if (name == null)
        {
            name = toBeChanged.getName();
        }
        if (price == -1)
        {
            price = toBeChanged.getPrice();
        }
        if (totalNumberOfSeats == -1)
        {
            totalNumberOfSeats = toBeChanged.getPhoneNumber();
        }
        if (managerEmail == null)
        {
            managerEmail = toBeChanged.getManagerEmailAddress();
        }

        toBeChanged.updateRestaurant(phoneNumber, city, state, zipcode, startTime, endTime, cuisine, name, price, totalNumberOfSeats, managerEmail);
        return "Changes have been made to the restaurant's profile";
    }

    /**
     * Displays a restaurant's information
     *
     * @param Restaurantid the id of the restaurant
     * @return the Restaurant that was requested
     */
    public static Restaurant displayRestaurant(int Restaurantid)
    {
        return SearchEngine.searchRestaurant(id);
    }

    /**
     * Returns all the restaurants that match the parameters
     *
     * @param city the city in which the restaurant is located
     * @param cuisine the cuisine of the restaurant
     * @param time the required time
     * @param price the average price of the restaurant
     * @param seats the number of required seats
     * @return a List containing all the restaurant that match the parameters
     */
    public static List<Restaurant> searchDatabaseForRestaurants(String city, String cuisine, int time, int price, int seats)
    {
        return SearchEngine.searchRestaurants(city, cuisine, time, price, seats);
    }

    /**
     * Returns all the restaurants that match the parameters
     *
     * @param city the city in which the restaurant is located
     * @param cuisine the cuisine of the restaurant
     * @param time the required time
     * @param seats the number of required seats
     * @return a List containing all the restaurant that match the parameters
     */
    public static List<Restaurant> searchDatabaseForRestaurants(String city, String cuisine, int time, int seats)
    {
        return SearchEngine.searchRestaurants(city, cuisine, time, seats);
    }

    /**
     * Returns all the restaurants that match the parameters
     *
     * @param city the city in which the restaurant is located
     * @param time the required time
     * @param price the average price of the restaurant
     * @param seats the number of required seats
     * @return a List containing all the restaurant that match the parameters
     */
    public static List<Restaurant> searchDatabaseForRestaurants(String city, int time, int price, int seats)
    {
        return SearchEngine.searchRestaurants(city, time, price, seats);
    }

    /**
     * Returns all the restaurants that match the parameters
     *
     * @param city the city in which the restaurant is located
     * @param time the required time
     * @param seats the number of required seats
     * @return a List containing all the restaurant that match the parameters
     */
    public static List<Restaurant> searchDatabaseForRestaurants(String city, int time, int seats)
    {
        return SearchEngine.searchRestaurants(city, time, seats);
    }

    /**
     * Creates a reservation in the database
     *
     * @param reqTime the time of the reservation
     * @param reqDate the date of the reservation
     * @param ConfirmationNumber the confirmation number of the reservation
     * @param PartySize the party size of the reservation
     * @param CreatedBy the person who created the reservation
     */
    public static void bookReservationInSystem(int reqTime, Date reqDate, int ConfirmationNumber, int PartySize, Person CreatedBy)
    {
        Reservation aReservation = new Reservation(id++, false, reqTime, reqDate, ConfirmationNumber, PartySize, CreatedBy);
        RestaurantCalendar someCalender = selectedRestaurant.getThisRestaurantSchedule();
        someCalender.addReservation(aReservation);
        //Find the restaurant manager's email address
        String ManagerEmail = selectedRestaurant.getManagerEmailAddress();
        //send a confirmation to the restaurant manager
        //EmailSender.sendMail(null, ManagerEmail, "Manager"); 
        selectedRestaurant.getThisRestaurantSchedule().reserveSeats(reqDate, reqTime, PartySize);
    }

    /**
     * Displays a reservation
     *
     * @param ConfirmationNumber the confirmation number associated with the
     * reservation
     * @param lastName the last name of the person who created the reservation
     * @return the reservation that was requested
     */
    public static Reservation displayReservation(int ConfirmationNumber, String lastName)
    {
        try
        {
            return SearchEngine.searchReservation(ConfirmationNumber, lastName);
        } catch (Exception ex)
        {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Searches for a person in the database
     *
     * @param email the email address of the person
     * @return the requested Person object
     */
    public static Person checkPerson(String email)
    {
        Person p = null;
        p = SearchEngine.checkPerson(email);
        return p;
    }

    /**
     * Sets the selected restaurant
     *
     * @param selected the restaurant to be selected
     */
    private void setSelectedRestaurant(Restaurant selected)
    {
        selectedRestaurant = selected;
    }

    /**
     * Displays a reservation.
     *
     * @param ConfirmationNumber the confirmation number of the reservation
     * @return the reservation
     */
    public static Reservation displayReservation(int ConfirmationNumber)
    {
        try
        {
            return SearchEngine.searchReservation(ConfirmationNumber);
        } catch (Exception ex)
        {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Updates a reservation. If the reservation cannot be updated, returns the
     * error
     *
     * @param ConfirmationNumber the confirmation error of the reservation
     * @param reqTime the new time of the reservation
     * @param reqDate the new date of the reservation
     * @param partySize the new party size of the reservation
     * @return a string explaining whether the reservation was updated or the
     * error
     */
    public static String updateReservationInSystem(int ConfirmationNumber, int reqTime, Date reqDate, int partySize)
    {
        Reservation theReservation = null;
        try
        {
            theReservation = SearchEngine.searchReservation(ConfirmationNumber);
        } catch (Exception ex)
        {
            Logger.getLogger(ReservationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (theReservation != null)
        {
            RestaurantCalendar someCalender = selectedRestaurant.getThisRestaurantSchedule();
            if (reqTime == -1)
            {
                reqTime = theReservation.getReqTime();
            }
            if (reqDate == null)
            {
                reqDate = theReservation.getReqDate();
            }
            if (partySize == -1)
            {
                partySize = theReservation.getPartySize();
            }
            someCalender.updateReservation(theReservation, reqTime, reqDate, partySize);
            return "The requested reservation has been cancelled.";
        } else
        {
            return "The reservation does not exist.";
        }
    }

    /**
     * Cancels a reservation
     *
     * @param ConfirmationNumber the confirmation number of the reservation
     * @return a String explaining whether the reservation was canceled or the
     * cancellation error
     * @throws Exception
     */
    public static String cancelReservationInSystem(int ConfirmationNumber) throws Exception
    {
        Reservation theReservation = SearchEngine.searchReservation(ConfirmationNumber);
        if (theReservation != null)
        {
            RestaurantCalendar someCalender = selectedRestaurant.getThisRestaurantSchedule();
            someCalender.deleteReservation(theReservation);
            //theReservation.
            return "The requested reservation has been cancelled.";
        } else
        {
            return "The reservation does not exist.";
        }
    }

}
