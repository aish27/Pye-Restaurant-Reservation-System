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
    private int startTime;

    /**
     *      */
    private int endTime;

    /**
     *      */
    private String cuisine;

    /**
     *      */
    private String name;

    /**
     *      */
    private int priceLow;

    /**
     *      */
    private int priceHigh;

    /**
     *      */
    private int totalNumberOfSeats;

    /**
     *      */
    private String ManagerEmailAddress;

    public Restaurant(int id, int phoneNumber, String city, String state, int zipcode, int startTime, int endTime, String cuisine, String name, int priceLow, int priceHigh, int totalNumberOfSeats, String managerEmail) {
        
        this.id = id;
        this.cuisine = cuisine;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.priceHigh = priceHigh;
        this.priceLow = priceLow;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.totalNumberOfSeats = totalNumberOfSeats;
        this.ManagerEmailAddress = managerEmail;

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
     * @return the startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public int getEndTime() {
        return endTime;
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
     * @return the priceLow
     */
    public int getPriceLow() {
        return priceLow;
    }

    /**
     * @return the priceHigh
     */
    public int getPriceHigh() {
        return priceHigh;
    }

    /**
     * @return the totalNumberOfSeats
     */
    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    /**
     * @return the ManagerEmailAddress
     */
    public String getManagerEmailAddress() {
        return ManagerEmailAddress;
    }

}
