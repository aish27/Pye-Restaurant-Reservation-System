package pye.project.model;


import java.util.ArrayList;
import java.util.Date;


/** */
public class ReservationManager
{
    static Restaurant selectedRestaurant;
    static int id = 0; 
    
    /** */
    public static void addRestaurant(int phoneNumber, String city, String state, int zipcode, int openTime, int closeTime, String cuisine, String name, int price,int totalNumberOfSeats, String managerEmail)
    {
        Restaurant aNewRestaurant = new Restaurant(id++,phoneNumber,city, state,zipcode, openTime,closeTime,cuisine, name,price, totalNumberOfSeats, managerEmail);
    }
    
        /** */
    public static String updateRestaurantInformation(int id, int phoneNumber, String city, String state, int zipcode, int startTime, int endTime, String cuisine, String name, int price,int totalNumberOfSeats, String managerEmail, String restaurantName)
    {
        Restaurant toBeChanged = SearchEngine.searchRestaurant(id);
        if(toBeChanged == null)
            return "Restaurant not found";
        if(phoneNumber == -1) phoneNumber = toBeChanged.getPhoneNumber();
        if(city == null) city = toBeChanged.getCity();
        if(state == null) state = toBeChanged.getState();
        if(zipcode == -1) zipcode = toBeChanged.getZipcode();
        if(startTime == -1) startTime = toBeChanged.getOpenTime();
        if(endTime == -1) endTime = toBeChanged.getCloseTime();
        if(cuisine ==null) cuisine = toBeChanged.getCuisine();
        if(name == null) name = toBeChanged.getName();
        if(price == -1) price = toBeChanged.getPrice();
        if(totalNumberOfSeats == -1) totalNumberOfSeats = toBeChanged.getPhoneNumber();
        if(managerEmail == null) managerEmail = toBeChanged.getManagerEmailAddress();
        
        toBeChanged.updateRestaurant(phoneNumber, city, state, zipcode, startTime,endTime,  cuisine, name, price,totalNumberOfSeats, managerEmail);
        return "Changes have been made to the restaurant's profile";
    }
    
    public static Restaurant displayRestaurant(int Restaurantid)
    {
        return SearchEngine.searchRestaurant(id);
    }
    
    /** */
    public static ArrayList<Restaurant> searchDatabaseForRestaurants(String city, String state, int openTime, int endTime, int requiredNumberOfSeats)
    {
        return SearchEngine.searchRestaurants(city, state, openTime, endTime, requiredNumberOfSeats);
    }
    

    
    /** */
    public static void bookReservationInSystem(int reqTime, Date reqDate,int ConfirmationNumber, int PartySize, Person CreatedBy)
    {
        // make the reservation and add it to the database (inside the reservation class)
        Reservation aReservation = new Reservation (id++, false,reqTime,reqDate,ConfirmationNumber,PartySize,CreatedBy);
        RestaurantCalendar someCalender = selectedRestaurant.getThisRestaurantSchedule();
        someCalender.addReservation(aReservation);
        //Find the restaurant manager's email address
        String ManagerEmail = selectedRestaurant.getManagerEmailAddress();
        //send a confirmation to the restaurant manager
        EmailSender.sendMail(null, ManagerEmail, "Manager"); 
        selectedRestaurant.getThisRestaurantSchedule().reserveSeats(reqDate,reqTime,PartySize);
        
    }
    
    private void setSelectedRestaurant(Restaurant selected)
    {
        selectedRestaurant = selected;
    }
    
    public static Reservation displayReservation(int ConfirmationNumber, String lastName) throws Exception
    {
        return SearchEngine.searchReservation(ConfirmationNumber, lastName);
    }
    
    /** Updates or cancels a reservation. If the reservation cannot be updated, returns the error
     */
    public static String updateReservationInSystem(int ConfirmationNumber, int reqTime, Date reqDate, int partySize) throws Exception
    { 
        Reservation theReservation = SearchEngine.searchReservation(ConfirmationNumber);
        if(theReservation != null)
        {
            RestaurantCalendar someCalender = selectedRestaurant.getThisRestaurantSchedule();
            if(reqTime == -1) reqTime = theReservation.getReqTime();
            if(reqDate == null) reqDate = theReservation.getReqDate();
            if(partySize == -1) partySize = theReservation.getPartySize();
            someCalender.updateReservation(theReservation,reqTime, reqDate,partySize);
            return "The requested reservation has been cancelled.";
        }
        else return "The reservation does not exist.";  
    }
    
     public static String cancelReservationInSystem(int ConfirmationNumber) throws Exception
    { 
        Reservation theReservation = SearchEngine.searchReservation(ConfirmationNumber);
        if(theReservation != null)
        {
            RestaurantCalendar someCalender = selectedRestaurant.getThisRestaurantSchedule();
            someCalender.deleteReservation(theReservation);
            return "The requested reservation has been cancelled.";
        }
        else return "The reservation does not exist.";  
    }

    public void displayReservation(String ConfirmationNumber, String lastName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    
}
