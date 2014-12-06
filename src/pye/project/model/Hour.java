package pye.project.model;


import java.util.ArrayList;

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
    private final int hourOfDay;
    private final AllSeatsInARestaurant seats;
    
    public Hour(int hour, int totalSeatsInRestaurant)
    {
        hourOfDay = hour;
        seats = new AllSeatsInARestaurant(totalSeatsInRestaurant);
    }
}
