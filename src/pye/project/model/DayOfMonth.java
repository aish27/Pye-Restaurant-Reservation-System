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
public class DayOfMonth 
{
    
    private int startTime;
    private int closeTime;
    
    private ArrayList<Hour> operatingHoursOfRestaurant;
    
    public DayOfMonth(int startTimeOfRestaurant, int closeTimeOfRestaurant, int totalSeatsInRestaurant)
    {
        startTime = startTimeOfRestaurant;
        closeTime = closeTimeOfRestaurant;
        operatingHoursOfRestaurant = new ArrayList<Hour>();
        
        for(int i = startTime; i<closeTime; i+= 100)
        {
            operatingHoursOfRestaurant.add(new Hour(i, totalSeatsInRestaurant));
        }
    }
}
