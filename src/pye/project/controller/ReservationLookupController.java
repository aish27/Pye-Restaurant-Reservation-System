/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pye.project.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pye.project.model.Reservation;
import pye.project.model.ReservationManager;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class ReservationLookupController implements Initializable {

    @FXML
    private AnchorPane reservationContentPane;

    @FXML
    private Label confLabel;

    @FXML
    private Reservation res;

    @FXML
    private Label locationLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label reservationTimeLabel;
    @FXML
    private Label restaurantNameLabel;
    @FXML
    private Label reservationDateLabel;
    @FXML
    private Label reservationMadeLabel;
    @FXML
    private Label partySizeLabel;

    @FXML
    private void cancelReservationButtonAction(ActionEvent event) {
        System.out.println("Cancel Popup");
        openCancelReservation(event);
    }

    @FXML
    private void editReservationButtonAction(ActionEvent event) {
        System.out.println("Load Booking Page!");
        openEditReservation(event);
    }

    @FXML
    private void shareReservationButtonAction(ActionEvent event) {
        System.out.println("Share Popup");
        openShareReservation(event);
    }

    @FXML
    private void goBackButtonAction(ActionEvent event) {
        System.out.println("Previous Screen");
    }

    public void openShareReservation(Event event) {
        try {
            URL url = getClass().getResource("/pye/project/ShareReservation.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane results = (AnchorPane) fxmlLoader.load(url.openStream());
            reservationContentPane.getChildren().clear();
            reservationContentPane.getChildren().add(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCancelReservation(Event event) {
        try {
            URL url = getClass().getResource("/pye/project/CancelReservation.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane results = (AnchorPane) fxmlLoader.load(url.openStream());
            reservationContentPane.getChildren().clear();
            reservationContentPane.getChildren().add(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openEditReservation(Event event) {
        try {
            URL url = getClass().getResource("/pye/project/EditReservation.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane results = (AnchorPane) fxmlLoader.load(url.openStream());
            reservationContentPane.getChildren().clear();
            reservationContentPane.getChildren().add(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showReservationDetails(Reservation res) {
        if (res != null) {
            //Reservation res2 = ReservationManager.displayReservation(ConfirmationNumber, lastName);
            res = ReservationManager.displayReservation(1111, "Doe");
            //confLabel.setText((res.ConfirmationNumber));
            confLabel.setText("Help!");
            //
            //restaurantNameLabel.setText("My Rest. Name");
            //locationLabel.setText("Located At");
            //phoneLabel.setText("000-000-0000");
            //reservationTimeLabel.setText("12:00 PM");
            //reservationDateLabel.setText("Dec. 10, 2014");
            //reservationMadeLabel.setText("Ambrin");
            //partySizeLabel.setText("4");
        } else {
            res = ReservationManager.displayReservation(1111, "Doe");

            // comment this out later after it all works
            System.out.println("Confirmation Number: " + res.ConfirmationNumber + "\n");
            if (res.restaurant == null) {
                res.restaurant.setName("Max's");
                System.out.println(res.restaurant.getName());
            } else {
                System.out.println("Restaurant: " + res.restaurant.getName() + "\n");
            }

            System.out.println("Location: " + res.restaurant.getCity());

            //if(res.restaurant.getPhoneNumber() == null){
            //res.restaurant.setPhoneNumber(1234567891);
            System.out.println(res.restaurant.getPhoneNumber());

            System.out.println("Reservation Time: " + res.getReqTime() + "\n");
            System.out.println("Reservation Date: " + res.getReqDate() + "\n");
            System.out.println("Reservation made by: " + res.getCreatedBy().getFirstName() + " " + res.getCreatedBy().getLastName() + "/n");
            System.out.println("Party Size: " + res.getPartySize() + "\n");

             //BUG HERE party size returns 0
            //confLabel.setText(res.ConfirmationNumber);
            confLabel.setText(String.valueOf(res.ConfirmationNumber));
            restaurantNameLabel.setText(res.restaurant.getName());
            locationLabel.setText(res.restaurant.getCity());
            phoneLabel.setText(String.valueOf(res.restaurant.getPhoneNumber()));

            reservationTimeLabel.setText(String.valueOf(res.getReqTime()));
            //reservationDateLabel.setText(res.getReqDate());
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String tmpDate = df.format(res.getReqDate());
            reservationDateLabel.setText(tmpDate);
            String tmpName = res.getCreatedBy().getFirstName() + " " + res.getCreatedBy().getLastName();
            reservationMadeLabel.setText(tmpName);
            partySizeLabel.setText(String.valueOf(res.getPartySize()));
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //confLabel.setText("ConfNumber");
        showReservationDetails(res);
    }

}
