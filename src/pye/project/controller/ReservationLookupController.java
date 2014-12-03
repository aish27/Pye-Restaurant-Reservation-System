/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pye.project.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class ReservationLookupController implements Initializable {

    @FXML
    private AnchorPane reservationContentPane;

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
    


        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize
        (URL url, ResourceBundle rb) {
        // TODO
        }

}
