package pye.project.model;


import java.util.ArrayList;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Restaurant.java
//  @ Date : 11/26/2014
//  @ Author : 
//
//

/**
 *  */
public class Restaurant {

    /**
     *      */
    private int id;

    /**
     *      */
    private String phoneNumber;

    /**
     *      */
    private String city;

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
      

    public Restaurant(int id, String phoneNumber, String city, int startTime, int endTime, String cuisine, String name, int price,int totalNumberOfSeats, String managerEmail) {
        
        this.id = id;
        this.cuisine = cuisine;
        this.openTime = startTime;
        this.closeTime = endTime;
        this.name = name;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.totalNumberOfSeats = totalNumberOfSeats;
        this.managerEmailAddress = managerEmail;
           
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
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
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
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
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
    
    public void updateRestaurant(String phoneNumber, String city, int startTime, int endTime, String cuisine, String name, int price,int totalNumberOfSeats, String managerEmail)
    {
        this.setCity(city);
        this.setCloseTime(closeTime);
        this.setCuisine(cuisine);
        this.setManagerEmailAddress(managerEmailAddress);
        this.setName(name);
        this.setOpenTime(openTime);
        this.setPrice(price);
        this.setTotalNumberOfSeats(totalNumberOfSeats);
    } 

}
