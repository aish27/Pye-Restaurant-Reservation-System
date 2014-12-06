package pye.project.model;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;


public class DataAccessTester {
	public static void main(String[] args) throws Exception {
		
		DataAccess dao = new DataAccess();
		//Restaurant Vars
		String city = "";
		String cuisine = "mexican";
		int time = 150000;
		int price = 5;
		int seats = 1;
		int conf = (int) (Math.random() * 10000);;
		Date date = new Date (2011, 01, 03);
		
		//Person Vars
		int PersonID = dao.getLargestPersonID();
		String first = "jeremy";
		String last = "lin";
		String email = "j@l.com";
		
		// Create a new Person Object
		 Person person = new Person (++PersonID, last, first, email);
		
		// Print the person Object
		 System.out.println(person.toString());
		
		// Search and print for the restaurant based on the vars above
		 List <Restaurant> list = dao.searchRestaurants(city, cuisine, time, price, seats);
		 for (Restaurant d : list) 
			 System.out.println(list.toString());
		
		System.out.println("Choose your restaurant based on ID: ");
		Scanner in = new Scanner (System.in);
		int i = in.nextInt();
		System.out.println(i);
		dao.AddPerson(person);
		dao.AddReservation(person, date, time, seats, conf, list.get(i-1));
		
		/*
		Reservation search =  dao.searchReservation(last, 1223);
		if (search != null)
			System.out.println(search.toString());
		else
			System.out.println("Reservation doesnt exist");
		*/
	}

}
