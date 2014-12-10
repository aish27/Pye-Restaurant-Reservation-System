package pye.project.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * This particular class represents a restaurant
 *
 * @author Gaurav Desai, Aishwarya Venketeswaran, Aauysh Neupane and Ambrin Ali
 */
public class Restaurant
{

    /**
     * Represents the id of the restaurant in the database
     *
     */
    private int id;

    /**
     * Represents the phone number of the restaurant
     *
     */
    private int phoneNumber;

    /**
     * Represents the city in which the restaurant is situated
     *
     */
    private String city;

    /**
     * Represents the state in which the restaurant is situated
     *
     */
    private String state;

    /**
     * Represents the zipcode in which the restaurant is situated
     *
     */
    private int zipcode;

    /**
     * Represents the time at which the restaurant opens
     *
     */
    private int openTime;

    /**
     * Represents the time at which the restaurant closes
     *
     */
    private int closeTime;

    /**
     * Represents the cuisine of the restaurant
     *
     */
    private String cuisine;

    /**
     * Represents the name of the restaurant
     *
     */
    private String name;

    /**
     * Represents the average price of the restaurant
     *
     */
    private int price;

    /**
     * Represents the total number of seats in the restaurant
     *
     */
    private int totalNumberOfSeats;

    /**
     * Represents the email address of the manager of the restaurant
     *
     */
    private String managerEmailAddress;

    /**
     * Represents the calendar of the restaurant
     *
     */
    private RestaurantCalendar thisRestaurantSchedule;

    /**
     * Constructor of a Restaurant object
     *
     * @param id the id of this restaurant
     * @param phoneNumber the phone number of this restaurant
     * @param city the city of this restaurant
     * @param state the state of this restaurant
     * @param zipcode the zipcode of this restaurant
     * @param openTime the opening of this restaurant
     * @param closeTime the closing time of this restaurant
     * @param cuisine the cuisine of this restaurant
     * @param name the name of this restaurant
     * @param price the average price of items in this restaurant
     * @param totalNumberOfSeats the total number of seats in this restaurant
     * @param managerEmail the manager's email address
     */
    public Restaurant(int id, int phoneNumber, String city, String state, int zipcode, int openTime, int closeTime, String cuisine, String name, int price, int totalNumberOfSeats, String managerEmail)
    {

        this.id = id;
        this.cuisine = cuisine;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.name = name;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.totalNumberOfSeats = totalNumberOfSeats;
        this.managerEmailAddress = managerEmail;
        this.thisRestaurantSchedule = new RestaurantCalendar(openTime, closeTime, totalNumberOfSeats);
    }

    /**
     * Returns the id of this restaurant
     *
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Returns the phone number of this restaurant
     *
     * @return the phoneNumber
     */
    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Returns the city of this restaurant
     *
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Returns the state of this restaurant
     *
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * Returns the zipcode of this restaurant
     *
     * @return the zipcode
     */
    public int getZipcode()
    {
        return zipcode;
    }

    /**
     * Returns the opening time of this restaurant
     *
     * @return the openTime
     */
    public int getOpenTime()
    {
        return openTime;
    }

    /**
     * Returns the closing time of this restaurant
     *
     * @return the closeTime
     */
    public int getCloseTime()
    {
        return closeTime;
    }

    /**
     * Returns the cuisine of this restaurant
     *
     * @return the cuisine
     */
    public String getCuisine()
    {
        return cuisine;
    }

    /**
     * Returns the name of this restaurant
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the average price of this restaurant
     *
     * @return the price
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Returns the total number of seats of this restaurant
     *
     * @return the totalNumberOfSeats
     */
    public int getTotalNumberOfSeats()
    {
        return totalNumberOfSeats;
    }

    /**
     * Returns the manager's email address
     *
     * @return the managerEmailAddress
     */
    public String getManagerEmailAddress()
    {
        return managerEmailAddress;
    }

    /**
     * Returns the calendar of this restaurant
     *
     * @return the thisRestaurantSchedule
     */
    public RestaurantCalendar getThisRestaurantSchedule()
    {
        return thisRestaurantSchedule;
    }

    /**
     * Sets the id of this restaurant
     *
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Sets the phone number of this restaurant
     *
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the city of this restaurant
     *
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Sets the state of this restaurant
     *
     * @param state the state to set
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Sets the zipcode of this restaurant
     *
     * @param zipcode the zipcode to set
     */
    public void setZipcode(int zipcode)
    {
        this.zipcode = zipcode;
    }

    /**
     * Sets the opening time of this restaurant
     *
     * @param openTime the openTime to set
     */
    public void setOpenTime(int openTime)
    {
        this.openTime = openTime;
    }

    /**
     * Sets the closing time of this restaurant
     *
     * @param closeTime the closeTime to set
     */
    public void setCloseTime(int closeTime)
    {
        this.closeTime = closeTime;
    }

    /**
     * Sets the cuisine of this restaurant
     *
     * @param cuisine the cuisine to set
     */
    public void setCuisine(String cuisine)
    {
        this.cuisine = cuisine;
    }

    /**
     * Sets the name of this restaurant
     *
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the average price of this restaurant
     *
     * @param price the price to set
     */
    public void setPrice(int price)
    {
        this.price = price;
    }

    /**
     * Sets the total number of seats of this restaurant
     *
     * @param totalNumberOfSeats the totalNumberOfSeats to set
     */
    public void setTotalNumberOfSeats(int totalNumberOfSeats)
    {
        this.totalNumberOfSeats = totalNumberOfSeats;
    }

    /**
     * Sets the manager's email address
     *
     * @param managerEmailAddress the managerEmailAddress to set
     */
    public void setManagerEmailAddress(String managerEmailAddress)
    {
        this.managerEmailAddress = managerEmailAddress;
    }

    /**
     * Updates the restaurant information
     *
     * @param phoneNumber the phone number of this restaurant
     * @param city the city of this restaurant
     * @param state the state of this restaurant
     * @param zipcode the zipcode of this restaurant
     * @param cuisine the cuisine of this restaurant
     * @param name the name of this restaurant
     * @param price the average price of items in this restaurant
     * @param totalNumberOfSeats the total number of seats in this restaurant
     * @param managerEmail the manager's email address
     */
    public void updateRestaurant(int phoneNumber, String city, String state, int zipcode, int startTime, int endTime, String cuisine, String name, int price, int totalNumberOfSeats, String managerEmail)
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

    /**
     * Converts the restaurant object to a string
     *
     * @return a string representation of the restaurant object
     */
    public String toString()
    {
        return String
                .format("Restaurant [id=%d, Name=%s, From %d to %d, Cuisine = %s, Price = %d, Total seats = %d, Email = %s]",
                        id, name, openTime, closeTime, cuisine, price, totalNumberOfSeats, managerEmailAddress);
    }
}
