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
import static java.util.Collections.list;
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
    private ComboBox hrComboBox;
    @FXML
    private ComboBox minComboBox;
    @FXML
    private ComboBox amorpmComboBox;
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
    public Reservation res = null;

    @FXML
    public ArrayList<Restaurant> searchResults = null;

    @FXML
    private void searchButtonAction(ActionEvent event) throws Exception {
        String city = locationSelected();
        String cuisine = cuisineSelected();
        int time = timeSelected();
        int price = priceratingSelected();
        int seats = partySizeSelected();

        /**
         * searchResults = SearchEngine.searchRestaurants(city, cuisine, time,
         * price, seats); { for (Restaurant d : searchResults){
         * System.out.println(d.getName()); }
         *
         * }
         */
        //System.out.println(searchresults.get(1));
        if (price == 0) {
            if (cuisine == "Select Cuisine") {
                System.out.println("price is zero and cusine is null 1,3,5");
                searchResults = (ArrayList<Restaurant>) SearchEngine.searchRestaurants(city, time, seats);
                {
                    for (Restaurant d : searchResults) {
                        System.out.println(d.getName());
                    }
                }
            }

        } else {
            if (cuisine == "Select Cuisine") {
                System.out.println("pric is nonzero and cusine is null 1,3,4,5"); //worked List 
                searchResults = (ArrayList<Restaurant>) SearchEngine.searchRestaurants(city, time, price, seats);
                {
                    for (Restaurant d : searchResults) {
                        System.out.println(d.getName());
                    }
                }
            } else {
                //System.out.println("price is nonzero and cusine is not null all else do 4(1, 2, 3, 4, 5");
                searchResults = SearchEngine.searchRestaurants(city, cuisine, time, price, seats);
                {
                    for (Restaurant d : searchResults) {
                        System.out.println(d.getName());
                    }
                }
            } //worked 
        }
        openResults(event);

    }



@FXML
        private void lookupButtonAction
        (ActionEvent event) throws Exception {
            systemFeedbackLookupReservation.setText(null);
            if (lastNameLookupText.getText() != null && !lastNameLookupText.getText().isEmpty()) {
                String lastName = lastNameLookupText.getText();
                System.out.println(lastName);
                String ConfNumber = confirmationLookupText.getText();
                int ConfirmationNumber = Integer.parseInt(ConfNumber);
                System.out.println(ConfirmationNumber);

                //TESTING:
                res = ReservationManager.displayReservation(ConfirmationNumber, lastName);
                System.out.println("From MainController.java");
                System.out.println(res.ConfirmationNumber + ", " + res.CreatedBy.getLastName() + "," + res.CreatedBy.getFirstName() + "," + res.restaurant);
                /**
                 * System.out.println("Confirmation Number: " +
                 * res.ConfirmationNumber + "/n"); if (res.restaurant == null) {
                 * res.restaurant.setName("Max's");
                 * System.out.println(res.restaurant.getName()); } else {
                 * System.out.println("Restaurant: " + res.restaurant.getName()
                 * + "/n"); }
                 *
                 * System.out.println("Location: "+res.restaurant.getCity());
                 *
                 * //if(res.restaurant.getPhoneNumber() == null){ //
                 * res.restaurant.setPhoneNumber("123-456-7891");
                 * System.out.println("Phone Number: 123-456-7891");
                 *
                 * System.out.println("Reservation Time: " + res.getReqTime() +
                 * "/n"); System.out.println("Reservation Date: " +
                 * res.getReqDate() + "/n"); System.out.println("Reservation
                 * made by: " + res.getCreatedBy().getFirstName() + " " +
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

            SearchResultController sCtl = (SearchResultController) fxmlLoader.getController();
            if (sCtl != null) {
                sCtl.setRestaurantListFromParent(searchResults);
            }

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

            ReservationLookupController rCtl = fxmlLoader.<ReservationLookupController>getController();
            rCtl.setReservationFromParent(res);

            if (res == null) {
                System.out.println("res is null in main");
            }

            if (rCtl == null) {
                System.out.println("rCtl is null in main");
            }

            // I think we can pass the params from the parent this way
            //Label cLbl = (Label)results.lookup("#confLabel");
            //cLbl.setText(null);
            // but 
            //results.setUserData(res);
            //contentPane.getChildren().getClass().get
            //results.getChildren().
            //results.getChildren().confLabel.setText("Boo!");
            //confLabel.setText(res.ConfirmationNumber);
            //Controller myController = fxmlLoader.getController();
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

        if (priceratingSelected > 5) {
            return 0;
        } else {
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
                "0",
                "$",
                "$$",
                "$$$",
                "$$$$");
        priceratingComboBox.setValue("0");

    }

}
