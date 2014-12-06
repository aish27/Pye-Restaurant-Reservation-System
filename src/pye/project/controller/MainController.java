/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pye.project.controller;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
    //private Reservation res;

    @FXML
    private void searchButtonAction(ActionEvent event) {
        //openResults(event);
        systemFeedbackLookupReservation.setText(null);
        //ComboBox.GetItemText
        if (locationComboBox.getValue().toString() != null && !partysizeComboBox.getValue().toString().isEmpty()) {

            System.out.println("Search Results!");

            String locationSelected = locationComboBox.getValue().toString();
            System.out.println(locationSelected);

            String partySizeSelected = partysizeComboBox.getValue().toString();
            System.out.println(partySizeSelected);

            //@aayush, party size returns zero
            //  add date picker
            String hrSelected = hrComboBox.getValue().toString();
            System.out.println(hrSelected);

            String minSelected = minComboBox.getValue().toString();
            System.out.println(minSelected);

            String amorpmSelected = amorpmComboBox.getValue().toString();
            System.out.println(amorpmSelected);

            int temptesttime = 1500;
            System.out.println(temptesttime);

            //Format the Date and Time
            String cuisineSelected = cuisineComboBox.getValue().toString();
            System.out.println(cuisineSelected);

            String priceratingSelected = priceratingComboBox.getValue().toString();
            System.out.println(priceratingSelected);

            //convert values
            String city = locationSelected;
            String cuisine = cuisineSelected;
            int time = 150000;
            int price = 5;
            int seats = 1;
            int conf = (int) (Math.random() * 10000);;
            Date date = new Date(2014, 12, 05);

            ArrayList<Restaurant> list = SearchEngine.searchRestaurants(city, cuisine, time, price, seats);

        } else {
            systemFeedbackLookupReservation.setText("Please complete the form.");
        }
    }

    @FXML
    private void lookupButtonAction(ActionEvent event) throws Exception {
        System.out.println("Reservation Lookup");
        systemFeedbackLookupReservation.setText(null);
        if (lastNameLookupText.getText() != null && !lastNameLookupText.getText().isEmpty()) {
            System.out.println("Reservation Lookup");
            String lastName = lastNameLookupText.getText();
            System.out.println(lastName);
            String ConfNumber = confirmationLookupText.getText();
            int ConfirmationNumber = Integer.parseInt(ConfNumber);
            //convert String to Int
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<String> locationlist = null;
        // TODO
        locationComboBox.getItems().addAll(
                "Cupertino",
                "Mountain View",
                "Santa Clara",
                "San Francisco",
                "San Jose",
                "Sunnyvale");

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

        minComboBox.getItems().addAll(
                "00",
                "30");

        amorpmComboBox.getItems().addAll(
                "AM",
                "PM");

        cuisineComboBox.getItems().addAll(
                "American",
                "Chinese",
                "French",
                "Italian",
                "Indian",
                "Mexican",
                "Pakistani",
                "Persian",
                "Vietnamese");

        priceratingComboBox.getItems().addAll(
                "$",
                "$$",
                "$$$",
                "$$$$",
                "$$$$$");

    }
}
