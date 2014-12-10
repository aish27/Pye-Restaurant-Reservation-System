package pye.project.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.io.*;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import pye.project.model.Person;
import pye.project.model.Reservation;

/**
 *
 * @author Aayush Neupane
 *
 */
public class DataAccess {

    private Connection myConn;

    /**
     * Default constructor that gets database connection
     *
     * @throws Exception
     */
    public DataAccess() throws Exception {

        // get db properties
        Properties props = new Properties();
        props.load(new FileInputStream("demo.properties"));

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");

        // connect to database
        myConn = DriverManager.getConnection(dburl, user, password);

        System.out.println("DB connection successful to: " + dburl);
    }

    /**
     * Displays all the Reservations. Probably wont be used in the program
     *
     * @return an arraylist of the Reservation
     * @throws Exception
     */
    public List<Reservation> getAllReservations() throws Exception {
        List<Reservation> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from reservations");

            while (myRs.next()) {
                Reservation tempReservation = convertRowToReservation(myRs);
                list.add(tempReservation);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    /**
     * Search the database based on lastname and confirmation number. Since the
     * reservation database doesnt keep a hold of the lastname value directly,
     * we cannot search for both at the same time.
     *
     * This first searches if the conf num is available or not. Then creates a
     * reservation object based on the confirmation number. From the reservation
     * object, it gets the 'createdby' person object. If the person's lastname
     * and param lastname are same, it returns the reservation. else, null.
     *
     * @param ConfirmationNumber
     * @param lastName
     * @return arraylist of Reservation
     * @throws Exception
     */
    public Reservation searchReservation(int ConfirmationNumber, String lastName) throws Exception {

        Reservation tempReservation = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.prepareStatement("select * from reservations where confirmation = ?");

            myStmt.setInt(1, ConfirmationNumber);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                tempReservation = convertRowToReservation(myRs);
            }
            //System.out.println(lastName);
            Person p = tempReservation.getCreatedBy();
            System.out.println(p.getLastName() + "sysline3");
            if (tempReservation.getCreatedBy().getLastName().equalsIgnoreCase(lastName)) {
                return tempReservation;
            } else {
                return null;
            }
        } finally {
            close(myStmt, myRs);
        }
    }
    
    /**
	 * Converts Time object from database to an int.
	 * Parse the object as string,
	 * parse the string's first two as hour,
	 * parse the string's second two as min.
	 * 
	 * 
	 * @param reqTime - Time Object from database
	 * @return hour*100 + min
	 */
	private int timeToInt(Time reqTime) 
	{
		String temp = reqTime.toString();
		int hour = Integer.parseInt(temp.substring(0, 2));
		int min = Integer.parseInt(temp.substring(3,4));

		return (hour*10000 + min*100);
	}

	/**
	 * Converts an integer of xxxx type to Time object
	 * @param intTime of length 4
	 * @return Time object (hh:mm)
	 */

	private Time intToTime(int intTime)
	{
		int hour = intTime / 10000;
		int min = intTime % 10000;
		@SuppressWarnings("deprecation")
		Time t = new Time(hour, min, 0);
		return t;
	}

    /**
     * Adds a reservation object to the database
     *
     * @param person - Use getId to get the person object's id
     * @param date - Date Object
     * @param time - time int which is converted to time object in database
     * @param partySize - int number of Person
     * @param conf - unique int
     * @param restaurant - use getRestaurantId to get the rest. objects' id
     * @throws SQLException - error
     */
    public void AddReservation(Person person, Date date, int time, int partySize, int conf, Restaurant restaurant) throws SQLException {
        //Statement myStmt = null;
        int PersonID = person.getId();
        int RestaurantID = restaurant.getId();
        String sql = "INSERT INTO reservations "
                + "(PersonID, ResDate, ResTime, party, confirmation, RestaurantID) VALUES "
                + "(?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = myConn.prepareStatement(sql);
        stmt.setInt(1, PersonID);
        stmt.setDate(2, date);
        stmt.setTime(3, intToTime(time)); //need to convert int time to time(time)
        stmt.setInt(4, partySize);
        stmt.setInt(5, conf);
        stmt.setInt(6, RestaurantID);
        int count = stmt.executeUpdate();
        if (count != 0) {
            System.out.println("Added new Reservation");
        }
    }

    /**
     * Add a person person to the database
     *
     * @param person object
     * @throws SQLException
     */
    public void AddPerson(Person person) throws SQLException {
        //Statement myStmt = null;
        int PersonID = person.getId();
        String last_name = person.getLastName();
        String first_name = person.getFirstName();
        String email = person.getEmail();
        String sql = "INSERT INTO person "
                + "(PersonID, last_name, first_name, email) VALUES "
                + "(?, ?, ?, ?)";
        PreparedStatement stmt = myConn.prepareStatement(sql);
        stmt.setInt(1, PersonID);
        stmt.setString(2, last_name);
        stmt.setString(3, first_name);
        stmt.setString(4, email);
        int count = stmt.executeUpdate();
        if (count != 0) {
            System.out.println("Added new Person");
        }
    }

    /**
     * Convert a row from database to a Reservation Object.
     *
     * @param myRs - Row from database that needs to be converted
     * @return Reservation Object
     * @throws SQLException
     */
    private Reservation convertRowToReservation(ResultSet myRs) throws SQLException {

        int ReservationID = myRs.getInt("ReservationsID");
        int PersonID = myRs.getInt("PersonID");
        Date reqDate = myRs.getDate("ResDate");
        Time reqTime = myRs.getTime("ResTime");
        int party = myRs.getInt("party");
        int conf = myRs.getInt("confirmation");
        int RestaurantID = myRs.getInt("RestaurantID");

        int intTime = timeToInt(reqTime);

        Person CreatedBy = getPerson(PersonID);
        Restaurant restaurant = getRestaurant(RestaurantID);

        Reservation tempReservation = new Reservation(ReservationID, true, intTime, reqDate, conf, party, CreatedBy, restaurant);

        return tempReservation;
    }

    
	public ArrayList<Restaurant> searchRestaurants(String reqLocation, String reqCuisine, int reqTime,
			int reqPrice, int reqSeats) throws Exception {
		ArrayList<Restaurant> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		//check for any empty or null values before sending it to database
		if (reqLocation.length() == 0)
			reqLocation += "%";
		if (reqCuisine.length() == 0)
			reqCuisine += "%";
		if (reqTime == 0)
			reqTime += 200000;				// set default for 8PM

		try {
			myStmt = myConn.prepareStatement("select * from restaurants where "
					+ "LocationCity like ? AND "
					+ "cuisine like ? AND "
					+ "openTime <= ? AND CloseTime >= ? AND "
					+ "price >= ? AND "
					+ "seats >= ?");

			myStmt.setString(1, reqLocation);
			myStmt.setString(2, reqCuisine);
			myStmt.setInt(3, reqTime);
			myStmt.setInt(4, reqTime);
			myStmt.setInt(5, reqPrice);
			myStmt.setInt(6, reqSeats);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Restaurant tempRestaurant = convertRowToRestaurant(myRs);
				list.add(tempRestaurant);
			}

			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	/**
	 * Search for the restaurant based on the given parameters.
	 * Since giving an empty value to database creates problem, we check 
	 * if the values are empty before sending them to the database. 
	 * For empty values, we either use wildcards or give a default value. 
	 * 
	 * @param reqLocation
	 * @param reqCuisine
	 * @param reqTime
	 * @param reqSeats
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Restaurant> searchRestaurants(String reqLocation, String reqCuisine, int reqTime,
			int reqSeats) throws Exception {
		ArrayList<Restaurant> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		//check for any empty or null values before sending it to database
		if (reqLocation.length() == 0)
			reqLocation += "%";
		if (reqCuisine.length() == 0)
			reqCuisine += "%";
		if (reqTime == 0)
			reqTime += 200000;				// set default for 8PM

		try {
			myStmt = myConn.prepareStatement("select * from restaurants where "
					+ "LocationCity like ? AND "
					+ "cuisine like ? AND "
					+ "openTime <= ? AND CloseTime >= ? AND "
					+ "seats >= ?");

			myStmt.setString(1, reqLocation);
			myStmt.setString(2, reqCuisine);
			myStmt.setInt(3, reqTime);
			myStmt.setInt(4, reqTime);
			myStmt.setInt(5, reqSeats);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Restaurant tempRestaurant = convertRowToRestaurant(myRs);
				list.add(tempRestaurant);
			}

			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	/**
	 * Search for the restaurant based on the given parameters.
	 * Since giving an empty value to database creates problem, we check 
	 * if the values are empty before sending them to the database. 
	 * For empty values, we either use wildcards or give a default value. 
	 * @param reqLocation
	 * @param reqTime
	 * @param reqPrice
	 * @param reqSeats
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Restaurant> searchRestaurants(String reqLocation, int reqTime,
			int reqPrice, int reqSeats) throws Exception {
		ArrayList<Restaurant> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		//check for any empty or null values before sending it to database
		if (reqLocation.length() == 0)
			reqLocation += "%";
		if (reqTime == 0)
			reqTime += 200000;				// set default for 8PM

		try {
			myStmt = myConn.prepareStatement("select * from restaurants where "
					+ "LocationCity like ? AND "
					+ "openTime <= ? AND CloseTime >= ? AND "
					+ "price >= ? AND "
					+ "seats >= ?");

			myStmt.setString(1, reqLocation);
			myStmt.setInt(2, reqTime);
			myStmt.setInt(3, reqTime);
			myStmt.setInt(4, reqPrice);
			myStmt.setInt(5, reqSeats);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Restaurant tempRestaurant = convertRowToRestaurant(myRs);
				list.add(tempRestaurant);
			}

			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	/**
	 * Search for the restaurant based on the given parameters.
	 * Since giving an empty value to database creates problem, we check 
	 * if the values are empty before sending them to the database. 
	 * For empty values, we either use wildcards or give a default value. 
	 * @param reqLocation
	 * @param reqTime
	 * @param reqSeats
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Restaurant> searchRestaurants(String reqLocation, int reqTime
			,int reqSeats) throws Exception {
		ArrayList<Restaurant> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		//check for any empty or null values before sending it to database
		if (reqLocation.length() == 0)
			reqLocation += "%";
		if (reqTime == 0)
			reqTime += 200000;				// set default for 8PM

		try {
			myStmt = myConn.prepareStatement("select * from restaurants where "
					+ "LocationCity like ? AND "
					+ "openTime <= ? AND CloseTime >= ? AND "
					+ "seats >= ?");

			myStmt.setString(1, reqLocation);
			myStmt.setInt(2, reqTime);
			myStmt.setInt(3, reqTime);
			myStmt.setInt(4, reqSeats);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Restaurant tempRestaurant = convertRowToRestaurant(myRs);
				list.add(tempRestaurant);
			}

			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}

    /**
     * Get a row matching a personID from database
     *
     * @param PersonID
     * @return Person Object
     * @throws SQLException
     */
    private Person getPerson(int PersonID) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        Person tempPerson = null;
        String lastName, firstName, email;

        myStmt = myConn.prepareStatement("select * from person where PersonID = ?");
        myStmt.setInt(1, PersonID);
        myRs = myStmt.executeQuery();

        while (myRs.next()) {
            lastName = myRs.getString("last_name");
            firstName = myRs.getString("first_name");
            email = myRs.getString("email");

            tempPerson = new Person(PersonID, lastName, firstName, email);
        }

        return tempPerson;
    }

    /**
     * get Restaurant's info based on the restaurantID from the database. The
     * method creates a temp restaurant object based on the database values.
     *
     * @param RestaurantID
     * @return Restaurant Object
     * @throws SQLException
     */
    private Restaurant getRestaurant(int RestaurantID) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        Restaurant tempRestaurant = null;
        String city, state, email, cuisine, name;
        int phoneNum, zip, startTime, endTime, price, seats;

        myStmt = myConn.prepareStatement("select * from restaurants where RestaurantID = ?");
        myStmt.setInt(1, RestaurantID);
        myRs = myStmt.executeQuery();

        while (myRs.next()) {
            name = myRs.getString("name");
            city = myRs.getString("LocationCity");
            state = myRs.getString("LocationState");
            cuisine = myRs.getString("cuisine");
            startTime = timeToInt(myRs.getTime("OpenTime"));
            endTime = timeToInt(myRs.getTime("CloseTime"));
            email = myRs.getString("email");
            price = myRs.getInt("price");
            seats = myRs.getInt("seats");
            phoneNum = myRs.getInt("phone"); //need to change string phonenum to long in database

            tempRestaurant = new Restaurant(RestaurantID, phoneNum, city, state, 00000, startTime, endTime, cuisine, name, price, seats, email);
        }

        return tempRestaurant;
    }

    /**
     * Convert a given database row into a restaurant object.
     *
     * @param myRs
     * @return Restaurant Object
     * @throws SQLException
     */
    private Restaurant convertRowToRestaurant(ResultSet myRs) throws SQLException {
        int RestaurantID = myRs.getInt("RestaurantID");
        Restaurant restaurant = getRestaurant(RestaurantID);

        return restaurant;
    }

    /**
     * Convert a given database row into a person object
     *
     * @param myRs
     * @return Person object
     * @throws SQLException
     */
    private Person convertRowToPerson(ResultSet myRs) throws SQLException {
        int PersonID = myRs.getInt("PersonID");
        Person person = getPerson(PersonID);

        return person;
    }

    /**
     * Get the largest id that the person table is using so we can insert next
     * person's info on the row after this.
     *
     * @return PersonID (largest)
     * @throws SQLException
     */
    int getLargestPersonID() throws SQLException {
        int PersonID = 0;
        String sql = "select * from person ORDER BY PersonID DESC LIMIT 0,1";
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        myStmt = myConn.prepareStatement(sql);
        myRs = myStmt.executeQuery();
        while (myRs.next()) {
            PersonID = myRs.getInt("PersonID");
        }

        return PersonID;
    }

    /**
     * Close the database connection
     *
     * @param myConn
     * @param myStmt
     * @param myRs
     * @throws SQLException
     */
    private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {

        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {

        }

        if (myConn != null) {
            myConn.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

    Reservation searchReservation(int ConfirmationNumber) {

        Reservation tempReservation = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            try {
                myStmt = myConn.prepareStatement("select * from reservations where confirmation = ?");
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                myStmt.setInt(1, ConfirmationNumber);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                myRs = myStmt.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                while (myRs.next()) {
                    tempReservation = convertRowToReservation(myRs);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            return tempReservation;
        } finally {
            try {
                close(myStmt, myRs);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Reservation getReservation(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Person checkPersonbyEmail(String email) {
        
        Person tempPerson = null;
        try {
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            String lastName, firstName;

            try {
                myStmt = myConn.prepareStatement("select * from person where email = ?");
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                myStmt.setString(1, email);
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                myRs = myStmt.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (myRs.next()) {
                lastName = myRs.getString("last_name");
                firstName = myRs.getString("first_name");
                email = myRs.getString("email");
                String pId = myRs.getString("PersonID");

                tempPerson = new Person(Integer.parseInt(pId), lastName, firstName, email);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempPerson;
    }

    /**
     * Reservation searchReservation(int ConfirmationNumber) throws SQLException
     * { Reservation tempReservation = null; PreparedStatement myStmt = null;
     * ResultSet myRs = null;
     *
     * try { myStmt = myConn.prepareStatement("select * from reservations where
     * confirmation = ?");
     *
     * myStmt.setInt(1, ConfirmationNumber); myRs = myStmt.executeQuery();
     *
     * while (myRs.next()) { tempReservation = convertRowToReservation(myRs); }
     * return tempReservation; } finally { close(myStmt, myRs); }
     *
     * }
     */
}
/**
 * This class consists of the methods that are used to talk to the database 
 * and exchange data between the controller and the database.
 * 
 * @author Aayush Neupane
 */
public class DataAccess {

	private Connection myConn;

	/**
	 * Default constructor that gets database connection
	 * @throws Exception
	 */
	public DataAccess() {

		// get db properties
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("demo.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");

		// connect to database
		try {
			myConn = DriverManager.getConnection(dburl, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("DB connection successful to: " + dburl);
	}

	/**
	 * Displays all the Reservations. Probably wont be used in the program
	 * 
	 * @return an arraylist of the Reservation
	 * @throws Exception
	 */
	public List<Reservation> getAllReservations()
	{
		List<Reservation> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from reservations");

			while (myRs.next()) {
				Reservation tempReservation = convertRowToReservation(myRs);
				list.add(tempReservation);
			}		
		} catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			close(myStmt, myRs);
		}
		return list;
	}

	/**
	 * Search the database based on lastname and confirmation number.
	 * Since the reservation database doesnt keep a hold of the lastname value directly,
	 * we cannot search for both at the same time.  
	 * 
	 * This first searches if the conf num is available or not. Then creates a reservation object
	 * based on the confirmation number. From the reservation object, 
	 * it gets the 'createdby' person object. If the person's lastname and param lastname are same, it 
	 * returns the reservation. else, null.
	 * @param lastName, confirmation number
	 * @return arraylist of Reservation if conf exists. null otherwise
	 * @throws Exception
	 */

	public Reservation searchReservation(String lastName, int conf)
	{
		Reservation tempReservation = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		if (conf > 0)
			try {
				myStmt = myConn.prepareStatement("select * from reservations where confirmation = ?");

				myStmt.setInt(1, conf);
				myRs = myStmt.executeQuery();

				while (myRs.next()) {
					tempReservation= convertRowToReservation(myRs);
				}
				//tempReservation stays null if the conf # doesnt exist. return null.
				if (tempReservation != null)
				{
					Person p = tempReservation.getCreatedPerson();
					System.out.println(p.toString());
					if (lastName.length() == 0 || lastName == null)
						return tempReservation; //actual value
					else if ((tempReservation.getCreatedPerson().getLastName()).equalsIgnoreCase(lastName))
						return tempReservation; //actual value
					else
						return tempReservation; //null value
				}
				else return tempReservation; //null
			} catch (SQLException e){
				e.printStackTrace();
			}
		finally {
			close(myStmt, myRs);
		}
		else
			return tempReservation; //null
		return tempReservation;			//because eclipse is stupid and doenst see the 5 return statements above
	}
	/**
	 * Adds a reservation object to the database
	 * @param person - Use getId to get the person object's id
	 * @param date - Date Object
	 * @param time - time int which is converted to time object in database
	 * @param partySize - int number of Person
	 * @param conf - unique int 
	 * @param restaurant - use getRestaurantId to get the rest. objects' id
	 * @throws SQLException - error
	 */

	public Reservation addReservation(Person person, Date date, int time, int partySize, int conf, int RestaurantID)
	{
		int PersonID = person.getId();
		Reservation reservation = null;
		try
		{
			String sql = "INSERT INTO reservations "
					+ "(PersonID, ResDate, ResTime, party, confirmation, RestaurantID) VALUES "
					+ "(?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = myConn.prepareStatement(sql);
			stmt.setInt(1, PersonID);
			stmt.setDate(2, javaToSqlDate(date));
			stmt.setTime(3, intToTime(time)); //need to convert int time to time(time)
			stmt.setInt(4, partySize);
			stmt.setInt(5, conf);
			stmt.setInt(6, RestaurantID);
			int count = stmt.executeUpdate();
			if (count != 0)
				System.out.println("Added new Reservation");
			int id = getLargestReservationsID();						//get the largest availabe ID from database
			reservation = new Reservation(id, time, date, conf, partySize, person, getRestaurant(RestaurantID));
		}catch (SQLException e){
			e.printStackTrace();
		}
		return reservation;

	}

	/**
	 * Add a person person to the database
	 * @param person object
	 * @throws SQLException
	 */

	public void addPerson(Person person)
	{
		int PersonID = person.getId();
		String last_name = person.getLastName();
		String first_name = person.getFirstName();
		String email = person.getEmail();
		try{
			String sql = "INSERT INTO person "
					+ "(PersonID, last_name, first_name, email) VALUES "
					+ "(?, ?, ?, ?)";
			PreparedStatement stmt = myConn.prepareStatement(sql);
			stmt.setInt(1, PersonID);
			stmt.setString(2, last_name);
			stmt.setString(3, first_name);
			stmt.setString(4, email);
			int count = stmt.executeUpdate();
			if (count != 0)
				System.out.println("Added new Person");
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Search for the restaurant based on the given parameters.
	 * Since giving an empty value to database creates problem, we check 
	 * if the values are empty before sending them to the database. 
	 * For empty values, we either use wildcards or give a default value. 
	 * @param reqLocation
	 * @param reqCuisine
	 * @param reqTime
	 * @param reqPprice
	 * @param reqSeats
	 * @return List of Restaurants found
	 * @throws Exception
	 */

	public ArrayList<Restaurant> searchRestaurants(String reqLocation, String reqCuisine, int reqTime,
			int reqPrice, int reqSeats)
			{
		ArrayList<Restaurant> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int defaultTime = 200000;

		//check for any empty or null values before sending it to database
		if (reqLocation.length() == 0)
			reqLocation += "%";
		if (reqCuisine.length() == 0)
			reqCuisine += "%";

		if (reqTime == 0)
			reqTime += defaultTime;				// set default for 8PM

		try {
			myStmt = myConn.prepareStatement("select * from restaurants where "
					+ "LocationCity like ? AND "
					+ "cuisine like ? AND "
					+ "openTime <= ? AND CloseTime >= ? AND "
					+ "price >= ? AND "
					+ "seats >= ?");

			myStmt.setString(1, reqLocation);
			myStmt.setString(2, reqCuisine);
			myStmt.setInt(3, reqTime);
			myStmt.setInt(4, reqTime);
			myStmt.setInt(5, reqPrice);
			myStmt.setInt(6, reqSeats);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Restaurant tempRestaurant = convertRowToRestaurant(myRs);
				list.add(tempRestaurant);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			close(myStmt, myRs);
		}
		return list;
			}
	/**
	 * Search for the restaurant based on the given parameters.
	 * Since giving an empty value to database creates problem, we check 
	 * if the values are empty before sending them to the database. 
	 * For empty values, we either use wildcards or give a default value. 
	 * 
	 * @param reqLocation
	 * @param reqCuisine
	 * @param reqTime
	 * @param reqSeats
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Restaurant> searchRestaurants(String reqLocation, String reqCuisine, int reqTime,
			int reqSeats)
			{
		ArrayList<Restaurant> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		//check for any empty or null values before sending it to database
		if (reqLocation.length() == 0)
			reqLocation += "%";
		if (reqCuisine.length() == 0)
			reqCuisine += "%";
		if (reqTime == 0)
			reqTime += 200000;				// set default for 8PM

		try {
			myStmt = myConn.prepareStatement("select * from restaurants where "
					+ "LocationCity like ? AND "
					+ "cuisine like ? AND "
					+ "openTime <= ? AND CloseTime >= ? AND "
					+ "seats >= ?");

			myStmt.setString(1, reqLocation);
			myStmt.setString(2, reqCuisine);
			myStmt.setInt(3, reqTime);
			myStmt.setInt(4, reqTime);
			myStmt.setInt(5, reqSeats);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Restaurant tempRestaurant = convertRowToRestaurant(myRs);
				list.add(tempRestaurant);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			close(myStmt, myRs);
		}
		return list;
			}
	/**
	 * Search for the restaurant based on the given parameters.
	 * Since giving an empty value to database creates problem, we check 
	 * if the values are empty before sending them to the database. 
	 * For empty values, we either use wildcards or give a default value. 
	 * @param reqLocation
	 * @param reqTime
	 * @param reqPrice
	 * @param reqSeats
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Restaurant> searchRestaurants(String reqLocation, int reqTime,
			int reqPrice, int reqSeats)
			{
		ArrayList<Restaurant> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		//check for any empty or null values before sending it to database
		if (reqLocation.length() == 0)
			reqLocation += "%";
		if (reqTime == 0)
			reqTime += 200000;				// set default for 8PM

		try {
			myStmt = myConn.prepareStatement("select * from restaurants where "
					+ "LocationCity like ? AND "
					+ "openTime <= ? AND CloseTime >= ? AND "
					+ "price >= ? AND "
					+ "seats >= ?");

			myStmt.setString(1, reqLocation);
			myStmt.setInt(2, reqTime);
			myStmt.setInt(3, reqTime);
			myStmt.setInt(4, reqPrice);
			myStmt.setInt(5, reqSeats);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Restaurant tempRestaurant = convertRowToRestaurant(myRs);
				list.add(tempRestaurant);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			close(myStmt, myRs);
		}
		return list;
			}
	/**
	 * Search for the restaurant based on the given parameters.
	 * Since giving an empty value to database creates problem, we check 
	 * if the values are empty before sending them to the database. 
	 * For empty values, we either use wildcards or give a default value. 
	 * @param reqLocation
	 * @param reqTime
	 * @param reqSeats
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Restaurant> searchRestaurants(String reqLocation, int reqTime, int reqSeats) 
	{
		ArrayList<Restaurant> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		//check for any empty or null values before sending it to database
		if (reqLocation.length() == 0)
			reqLocation += "%";
		if (reqTime == 0)
			reqTime += 200000;				// set default for 8PM

		try {
			myStmt = myConn.prepareStatement("select * from restaurants where "
					+ "LocationCity like ? AND "
					+ "openTime <= ? AND CloseTime >= ? AND "
					+ "seats >= ?");

			myStmt.setString(1, reqLocation);
			myStmt.setInt(2, reqTime);
			myStmt.setInt(3, reqTime);
			myStmt.setInt(4, reqSeats);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Restaurant tempRestaurant = convertRowToRestaurant(myRs);
				list.add(tempRestaurant);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			close(myStmt, myRs);
		}
		return list;
	}

	/**
	 * Converts Time object from database to an int.
	 * Parse the object as string,
	 * parse the string's first two as hour,
	 * parse the string's second two as min.
	 * 
	 * 
	 * @param reqTime - Time Object from database
	 * @return hour*100 + min
	 */
	private int timeToInt(Time reqTime) 
	{
		String temp = reqTime.toString();
		int hour = Integer.parseInt(temp.substring(0, 2));
		int min = Integer.parseInt(temp.substring(3,4));

		return (hour*10000 + min*100);
	}

	/**
	 * Converts an integer of xxxx type to Time object
	 * @param intTime of length 4
	 * @return Time object (hh:mm)
	 */
	private Time intToTime(int intTime)
	{
		int hour = intTime / 10000;
		int min = intTime % 10000;
		@SuppressWarnings("deprecation")
		Time t = new Time(hour, min, 0);
		return t;
	}

	/**
	 * Get a row matching a personID from database and populate 
	 * lastname, firstname, email for person object
	 * 
	 * @param PersonID 
	 * @return Person Object
	 * @throws SQLException
	 */
	private Person getPerson(int PersonID)
	{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Person tempPerson = null;
		String lastName, firstName, email;
		try{
			myStmt = myConn.prepareStatement("select * from person where PersonID = ?");
			myStmt.setInt(1, PersonID);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				lastName = myRs.getString("last_name");
				firstName = myRs.getString("first_name");
				email = myRs.getString("email");

				tempPerson = new Person(PersonID, lastName, firstName, email);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tempPerson;
	}

	/**
	 * get Restaurant's info based on the restaurantID from the database.
	 * The method creates a temp restaurant object based on the database values.
	 * @param RestaurantID
	 * @return Restaurant Object
	 * @throws SQLException
	 */
	Restaurant getRestaurant(int RestaurantID)
	{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Restaurant tempRestaurant = null;
		String city, email, cuisine, name;
		String phoneNum;
		int startTime, endTime, price, seats;
		try{
			myStmt = myConn.prepareStatement("select * from restaurants where RestaurantID = ?");
			myStmt.setInt(1, RestaurantID);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				name = myRs.getString("name");
				city = myRs.getString("LocationCity");
				cuisine = myRs.getString("cuisine");
				startTime = timeToInt(myRs.getTime("OpenTime"));
				endTime = timeToInt(myRs.getTime("CloseTime"));
				email = myRs.getString("email");
				price = myRs.getInt("price");
				seats = myRs.getInt("seats");
				phoneNum = myRs.getString("phone"); //need to change string phonenum to long in database

				tempRestaurant = new Restaurant(RestaurantID, phoneNum, city, startTime, endTime, cuisine, name, price, seats, email);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tempRestaurant;
	}

	/**
	 * Gets the fields from Reservation database using ReservationID 
	 * and populates time, date, party, confirmation number etc. using those values
	 * 
	 * @param ReservationsID 
	 * @return Reservation Object
	 */
	Reservation getReservation(int ReservationsID)
	{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Reservation tempReservation = null;
		int time, party, PersonID, confirmation, RestaurantID;
		Date date;
		Restaurant restaurant;
		Person person;
		try{
			myStmt = myConn.prepareStatement("select * from reservations where ReservationsID = ?");
			myStmt.setInt(1, ReservationsID);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				PersonID = myRs.getInt("PersonID");
				date = myRs.getDate("ResDate");
				time = timeToInt(myRs.getTime("ResTime"));
				party = myRs.getInt("party");
				confirmation = myRs.getInt("confirmation");
				RestaurantID = myRs.getInt("RestaurantID"); //need to change string phonenum to long in database
				restaurant = getRestaurant(RestaurantID);
				person = getPerson(PersonID);
				tempReservation = new Reservation(ReservationsID, time, date, confirmation, party, person, restaurant);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tempReservation;
	}


	/**
	 * Convert a row from database to a Reservation Object.
	 * @param myRs - Row from database that needs to be converted
	 * @return Reservation Object
	 * @throws SQLException
	 */

	private Reservation convertRowToReservation(ResultSet myRs)
	{
		Reservation reservation = null;
		try{
			int ReservationsID = myRs.getInt("ReservationsID");
			reservation = getReservation(ReservationsID);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return reservation;
	}

	/**
	 * Convert a given database row into a restaurant object.
	 * @param myRs
	 * @return Restaurant Object
	 * @throws SQLException
	 */

	private Restaurant convertRowToRestaurant(ResultSet myRs)
	{
		Restaurant restaurant = null;
		try{
			int RestaurantID = myRs.getInt("RestaurantID");
			restaurant = getRestaurant(RestaurantID);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return restaurant;
	}

	/**
	 * Convert a given database row into a person object
	 * Method gets a PersonID and uses that to get rest of the data
	 * to create the object. 
	 * @param myRs - row of database
	 * @return Person object
	 * @throws SQLException
	 */

	@SuppressWarnings("unused")
	private Person convertRowToPerson(ResultSet myRs)
	{
		Person person = null;
		try{
			int PersonID = myRs.getInt("PersonID");		
			person = getPerson(PersonID);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return person;
	}

	/**
	 * Get the largest id that the person table is using
	 * so we can insert next person's info on the row after this.
	 * @return PersonID (largest)
	 * @throws SQLException
	 */
	int getLargestPersonID()
	{
		int PersonID = 0;
		String sql = "select * from person ORDER BY PersonID DESC LIMIT 0,1";
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try{
			myStmt = myConn.prepareStatement(sql);
			myRs = myStmt.executeQuery();
			while (myRs.next())
				PersonID = myRs.getInt("PersonID");
		}catch (SQLException e){
			e.printStackTrace();
		}
		return PersonID;
	}
	/**
	 * Get the largest id that a reservation table is using
	 * so we can insert next reservation's info on the row after this. 
	 * @return ReservationsID(largest)
	 * @throws SQLException
	 */
	int getLargestReservationsID()
	{
		int ReservationsID = 0;
		String sql = "select * from reservations ORDER BY ReservationsID DESC LIMIT 0,1";
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try{
			myStmt = myConn.prepareStatement(sql);
			myRs = myStmt.executeQuery();
			while (myRs.next())
				ReservationsID = myRs.getInt("ReservationsID");
		}catch (SQLException e){
			e.printStackTrace();
		}
		return ReservationsID;
	}

	/**
	 * Close the database connection
	 * @param myConn
	 * @param myStmt
	 * @param myRs
	 * @throws SQLException
	 */
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
	{
		if (myRs != null) {
			try {
				myRs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (myStmt != null) {	//nothing
		}

		if (myConn != null) {
			try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Close the database connection using given params
	 * @param myStmt
	 * @param myRs
	 * @throws SQLException
	 */
	private void close(Statement myStmt, ResultSet myRs)
	{
		close(null, myStmt, myRs);		
	}

	/**
	 * Delete reservation based on lastname and confirmation number. 
	 * @param lastName - persons last name 
	 * @param confirmation - reservation confirmation number.
	 * @return true if deleted, false if not. 
	 * @throws Exception
	 */
	boolean deleteReservation(String lastName, int confirmation)
	{
		PreparedStatement myStmt = null;
		boolean status = false;
		int count;
		Reservation toBeDeleted = searchReservation(lastName, confirmation);
		try{
			if (toBeDeleted != null)
			{
				//	myStmt = myConn.prepareStatement("delete from reservations where confirmation = ? limit 1");
				myStmt = myConn.prepareStatement(" UPDATE reservations"
						+ " SET confirmation = ?"
						+ " WHERE confirmation = ?");
				myStmt.setInt(1, confirmation * -1);
				myStmt.setInt(2, confirmation);
				count = myStmt.executeUpdate();

				if (count != 0)
				{
					System.out.println("a");
					status = true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * Change existing reservation to the new time, date, and size values.
	 * Program checks if the params are null or they exist or not. 
	 * If the passed reservation doesnt exist, returns false.
	 * If params time, date, size dont exist, use the existing values. 
	 * @param reservation
	 * @param time
	 * @param date
	 * @param size
	 * @return true if succesfully modifed, false otherwise
	 * @throws Exception
	 */
	public boolean modifyReservation(Reservation reservation, int time, Date date, int size)
	{
		PreparedStatement myStmt = null;
		boolean status = false;
		int count;

		if (reservation != null)
		{
			if (time == 0)
				time += reservation.getReqTime();		//if time isnt passed, use existing value
			if (date == null)
				date = reservation.getReqDate(); //if date is null, use existing value
			if (size == 0)
				size = 1;					//if size isnt passed, use a default 1 value.
			try{
				myStmt = myConn.prepareStatement(" UPDATE reservations"
						+ " SET ResDate = ?"
						+ ", ResTime = ?"
						+ ", party = ?"
						+ " WHERE ReservationsID = ?");
				myStmt.setDate(1, javaToSqlDate(date));
				myStmt.setTime(2, intToTime(time));
				myStmt.setInt(3, size);
				myStmt.setInt(4, reservation.getId());
				count = myStmt.executeUpdate();

				if (count != 0)
				{
					System.out.println("modified");
					status = true;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return status;
	}

	/**
	 * Convert a java.util.date to a java.sql.Date. 
	 * <p>
	 * This method is used instead of java.sql.Date because all the other Date references 
	 * are java.util.Date. Using a convertion method prevents passed data mismatch
	 * 
	 * @param date
	 * @return java.sql.date value
	 */
	private java.sql.Date javaToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
}
