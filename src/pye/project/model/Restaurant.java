package pye.project.model;

import java.util.ArrayList;
import java.util.Date;


/**
 *  */
public class Restaurant {

    /**
     *      */
    private int id;

    /**
     *      */
    private int phoneNumber;

    /**
     *      */
    private String city;

    /**
     *      */
    private String state;

    /**
     *      */
    private int zipcode;

    /**
     *      */
    private int openTime;

    /**
     *      */
    private int closeTime;

    /**
     *      */
    private String cuisine;

    /**
     *      */
    private String name;

    /**
     *      */
    private int price;

    /**
     *      */
    private int totalNumberOfSeats;

    /**
     *      */
    private String managerEmailAddress;
    
    
    private RestaurantCalendar thisRestaurantSchedule;
    

    public Restaurant(int id, int phoneNumber, String city, String state, int zipcode, int startTime, int endTime, String cuisine, String name, int price,int totalNumberOfSeats, String managerEmail) {
        
        this.id = id;
        this.cuisine = cuisine;
        this.openTime = startTime;
        this.closeTime = endTime;
        this.name = name;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.totalNumberOfSeats = totalNumberOfSeats;
        this.managerEmailAddress = managerEmail;
        this.thisRestaurantSchedule = new RestaurantCalendar(openTime,closeTime,totalNumberOfSeats);
        
        //add this restaurant to the database
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the phoneNumber
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @return the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * @return the openTime
     */
    public int getOpenTime() {
        return openTime;
    }

    /**
     * @return the closeTime
     */
    public int getCloseTime() {
        return closeTime;
    }

    /**
     * @return the cuisine
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return the totalNumberOfSeats
     */
    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    /**
     * @return the managerEmailAddress
     */
    public String getManagerEmailAddress() {
        return managerEmailAddress;
    }

    /**
     * @return the thisRestaurantSchedule
     */
    public RestaurantCalendar getThisRestaurantSchedule() {
        return thisRestaurantSchedule;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    
    //make changes in database everytime a set is called 
    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @param openTime the openTime to set
     */
    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    /**
     * @param closeTime the closeTime to set
     */
    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * @param cuisine the cuisine to set
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @param totalNumberOfSeats the totalNumberOfSeats to set
     */
    public void setTotalNumberOfSeats(int totalNumberOfSeats) {
        this.totalNumberOfSeats = totalNumberOfSeats;
    }

    /**
     * @param managerEmailAddress the managerEmailAddress to set
     */
    public void setManagerEmailAddress(String managerEmailAddress) {
        this.managerEmailAddress = managerEmailAddress;
    }

    /**
     * @param thisRestaurantSchedule the thisRestaurantSchedule to set
     */
    public void setThisRestaurantSchedule(RestaurantCalendar thisRestaurantSchedule) {
        this.thisRestaurantSchedule = thisRestaurantSchedule;
    }
    
    public void updateRestaurant(int phoneNumber, String city, String state, int zipcode, int startTime, int endTime, String cuisine, String name, int price,int totalNumberOfSeats, String managerEmail)
    {
        this.setCity(city);
        this.setCloseTime(closeTime);
        this.setCuisine(cuisine);
        this.setManagerEmailAddress(managerEmailAddress);
        this.setName(name);
        this.setOpenTime(openTime);
        this.setPhoneNumber(phoneNumber);
        this.setPrice(price);
        this.setState(state);
        this.setTotalNumberOfSeats(totalNumberOfSeats);
        this.setZipcode(zipcode);
    }
	public String toString() {
		return String
				.format("Restaurant [id=%d, Name=%s, From %d to %d, Cuisine = %s, Price = %d, Total seats = %d, Email = %s]",
						id, name, openTime, closeTime, cuisine, price, totalNumberOfSeats, managerEmailAddress);
	}
}
