/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pye.project.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pye.project.model.Person;
import pye.project.model.Reservation;
import pye.project.model.ReservationManager;
import pye.project.model.Restaurant;
import pye.project.model.SearchEngine;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class BookReservationController implements Initializable {

    @FXML
    private AnchorPane contentPane;
    
    /**@FXML
    private AnchorPane controlsPane;
    @FXML
    public Button bookButton;*/
    @FXML
    public Label selectedRestaurantName;
    @FXML
    public Label selectedRestaurantLocation;
    @FXML
    public Label selectedRestaurantPhoneNumber;
    @FXML
    public ComboBox partysizeComboBox;
    @FXML
    public ComboBox hrsComboBox;

    @FXML
    public ComboBox minsComboBox;
    @FXML
    public ComboBox amorpmsComboBox;
    @FXML
    public DatePicker selectedDate;
    @FXML
    public TextField firstNameText;
    @FXML
    public TextField lastNameText;
    @FXML
    public TextField emailText;
    @FXML
    public Person user = null;
    

    @FXML
    private void bookNowButtonAction(ActionEvent event) {
        System.out.println("Book my Reservation button clicked.");
        
        String email = "ambrin@foo.com";
        /**int reqTime = ''; 
        Date reqDate = '';
        int ConfirmationNumber = "";
        int PartySize = "5";
        Person CreatedBy = " "; 
        
        Person user = ReservationManager.checkPerson(email);
        if user != null;{
            bookRes = ReservationManager.bookReservationInSystem(reqTime, null, ConfirmationNumber, PartySize, Person CreatedBy);
            {
                
            }
        }
        else {
            //then add person to database
            //get ID
            //make reservation  
        }*/
        
        
   
    }

    /**
     *
     * @param event
     */
    
    
    
    
    private int timeSelected() throws ParseException {
        String hrsSelected = hrsComboBox.getValue().toString();
        System.out.println(hrsSelected);

        String minsSelected = minsComboBox.getValue().toString();
        System.out.println(minsSelected);

        String amorpmsSelected = amorpmsComboBox.getValue().toString();
        System.out.println(amorpmsSelected);

        String temptime = hrsSelected + ":" + minsSelected + ":" + "00 " + amorpmsSelected;
        System.out.println(temptime);

        String timestring = temptime;
        Date timeformat = (Date) new SimpleDateFormat("hh:mm:ss aa").parse(timestring);
        String temptime2 = new SimpleDateFormat("Hmmss").format(timeformat);
        int timeSelected = Integer.parseInt(temptime2);
        System.out.println(timeSelected);
        return timeSelected;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        hrsComboBox.getItems().addAll(
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6");
        hrsComboBox.setValue("6");

        minsComboBox.getItems().addAll(
                "00",
                "30");
        minsComboBox.setValue("00");

        amorpmsComboBox.getItems().addAll(
                "AM",
                "PM");
        amorpmsComboBox.setValue("PM");
        
        partysizeComboBox.getItems().addAll(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10");
        partysizeComboBox.setValue("1");

        
    
        
    }

}
