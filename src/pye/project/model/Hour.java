package pye.project.model;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aishwarya
 */
public class Hour 
{
    private int hourOfDay;
    private AllSeatsInARestaurant seats;
    
    public Hour(int hour, int totalSeatsInRestaurant)
    {
        hourOfDay = hour;
        seats = new AllSeatsInARestaurant(totalSeatsInRestaurant);
    }
}