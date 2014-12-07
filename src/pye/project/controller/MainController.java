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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.Optional.empty;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pye.project.model.Reservation;
import pye.project.model.ReservationManager;
import pye.project.model.Restaurant;
import pye.project.model.SearchEngine;

/**
 *
 * @author Ambrin
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane contentPane;
    @FXML
    public ComboBox locationComboBox;
    @FXML
    public ComboBox partysizeComboBox;
    @FXML
    public ComboBox hrComboBox;
    @FXML
    public ComboBox minComboBox;
    @FXML
    public ComboBox amorpmComboBox;
    @FXML
    public ComboBox cuisineComboBox;
    @FXML
    public ComboBox priceratingComboBox;
    @FXML
    public TextField lastNameLookupText;
    @FXML
    public TextField confirmationLookupText;
    @FXML
    public Label systemFeedbackLookupReservation;
    @FXML
    public Label confLabel;

    @FXML
    private void searchButtonAction(ActionEvent event) throws Exception {
        String city = locationSelected();
        String cuisine = cuisineSelected();
        int time = timeSelected();
        int price = priceratingSelected();
        int seats = partySizeSelected();

        openResults(event);
        List <Restaurant> list = SearchEngine.searchRestaurants(city, cuisine, time, price, seats);{
        for (Restaurant d : list); 
        }   

       /** if (price == 0) {
            if (cuisine == "Select Cuisine") {
                System.out.println("price is zero and cusine is null 1,3,5"); //worked
            } 
        else {
                System.out.println("price is zero and cuisine is not null 1,2,3,5");
        }

        } else { 
            if (cuisine == "Select Cuisine") {
                System.out.println("price is nonzero and cusine is null 1,3,4,5"); //worked
                List <Restaurant> list = SearchEngine.searchRestaurants(city, time, price, seats);{
                for (Restaurant d : list); 
                }    
                //System.out.println(list2.toString()); 
            }    else  {
                System.out.println("price is nonzero and cusine is not null all else do 4(1, 2, 3, 4, 5"); //worked
                //List <Restaurant> list = SearchEngine.searchRestaurants(city, cuisine, time, price, seats);{
		//for (Restaurant d : list) ;
                }
            
        }*/
    }

    @FXML
    private void lookupButtonAction(ActionEvent event) throws Exception {
        systemFeedbackLookupReservation.setText(null);
        if (lastNameLookupText.getText() != null && !lastNameLookupText.getText().isEmpty()) {
            String lastName = lastNameLookupText.getText();
            System.out.println(lastName);
            String ConfNumber = confirmationLookupText.getText();
            int ConfirmationNumber = Integer.parseInt(ConfNumber);
            System.out.println(ConfirmationNumber);

            //TESTING:
            //Reservation res = ReservationManager.displayReservation(ConfirmationNumber, lastName);
            //System.out.println(res.ConfirmationNumber+", "+res.CreatedBy.getLastName()+","+res.CreatedBy.getFirstName()+","+res.restaurant);
            /**
             * System.out.println("Confirmation Number: " +
             * res.ConfirmationNumber + "/n"); if (res.restaurant == null) {
             * res.restaurant.setName("Max's");
             * System.out.println(res.restaurant.getName()); } else {
             * System.out.println("Restaurant: " + res.restaurant.getName() +
             * "/n"); }
             *
             * System.out.println("Location: "+res.restaurant.getCity());
             *
             * //if(res.restaurant.getPhoneNumber() == null){ //
             * res.restaurant.setPhoneNumber("123-456-7891");
             * System.out.println("Phone Number: 123-456-7891");
             *
             * System.out.println("Reservation Time: " + res.getReqTime() +
             * "/n"); System.out.println("Reservation Date: " + res.getReqDate()
             * + "/n"); System.out.println("Reservation made by: " +
             * res.getCreatedBy().getFirstName() + " " +
             * res.getCreatedBy().getLastName() + "/n");
             */
            openReservation(event);
            //results.reservationContentPane.confLabel.setText(res.ConfirmationNumber);
            //results.reservationContentPane.
            // and so on

        } else {
            systemFeedbackLookupReservation.setText("Please fill provide Last Name and Confirmation Number.");
        }
    }

    public void openResults(Event event) {
        try {
            URL url = getClass().getResource("/pye/project/SearchResult.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane results = (AnchorPane) fxmlLoader.load(url.openStream());
            contentPane.getChildren().clear();
            contentPane.getChildren().add(results);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openReservation(Event event) {
        try {
            URL url = getClass().getResource("/pye/project/ReservationLookup.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane results = (AnchorPane) fxmlLoader.load(url.openStream());
            contentPane.getChildren().clear();
            contentPane.getChildren().add(results);
            //results.getChildren().
            //results.reservationContentPane.getChildren().confLabel.setText("Boo!");
            //confLabel.setText(res.ConfirmationNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String locationSelected() {
           String locationSelected = locationComboBox.getValue().toString();
           System.out.println(locationSelected);    
           return locationSelected;
    }

    private String cuisineSelected() {
        String cuisineSelected = cuisineComboBox.getValue().toString();
        System.out.println(cuisineSelected);
        return cuisineSelected;
    }

    private int timeSelected() throws ParseException {
        String hrSelected = hrComboBox.getValue().toString();
        System.out.println(hrSelected);

        String minSelected = minComboBox.getValue().toString();
        System.out.println(minSelected);

        String amorpmSelected = amorpmComboBox.getValue().toString();
        System.out.println(amorpmSelected);

        String temptime = hrSelected + ":" + minSelected + ":" + "00 " + amorpmSelected;
        System.out.println(temptime);

        String timestring = temptime;
        Date timeformat = (Date) new SimpleDateFormat("hh:mm:ss aa").parse(timestring);
        String temptime2 = new SimpleDateFormat("Hmmss").format(timeformat);
        int timeSelected = Integer.parseInt(temptime2);
        System.out.println(timeSelected);
        return timeSelected;

    }

    private int priceratingSelected() {
        int priceratingSelected = priceratingComboBox.getValue().toString().length();
        System.out.println(priceratingSelected);
        
        if (priceratingSelected > 5)  {
            return 0;
        }
        else {
            return priceratingSelected;
        }        
    }

    private int partySizeSelected() {
        String partySizeSelected = partysizeComboBox.getValue().toString();
        int seatsRequested = Integer.parseInt(partySizeSelected);
        System.out.println(seatsRequested);
        return seatsRequested;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<String> locationlist = null;
        // TODO
        locationComboBox.getItems().addAll(
                "Select City",
                "Cupertino",
                "Mountain View",
                "Santa Clara",
                "San Francisco",
                "San Jose",
                "Sunnyvale");
        locationComboBox.setValue("Select City");

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

        hrComboBox.getItems().addAll(
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
        hrComboBox.setValue("6");

        minComboBox.getItems().addAll(
                "00",
                "30");
        minComboBox.setValue("00");

        amorpmComboBox.getItems().addAll(
                "AM",
                "PM");
        amorpmComboBox.setValue("PM");

        cuisineComboBox.getItems().addAll(
                "Select Cuisine",
                "American",
                "Chinese",
                "French",
                "Italian",
                "Indian",
                "Mexican",
                "Pakistani",
                "Persian",
                "Vietnamese");
        cuisineComboBox.setValue("Select Cuisine");

        priceratingComboBox.getItems().addAll(
                "Select Price",
                "$",
                "$$",
                "$$$",
                "$$$$");
        priceratingComboBox.setValue("Select Price");

    }

}
