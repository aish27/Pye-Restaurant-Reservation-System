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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class CancelReservationController implements Initializable {

    //@FXML
    public Label cancelResLabel;

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        System.out.println("Update and Mark my Cancelation");
        // @aayush
        // here we want to set the confrmation number to -1
        // this is a terrible hack 
        // ReservationManager.cancelReservationInSystem(ConfirmationNumber);
        cancelResChanger(event);

    }

    @FXML
    private void noButtonAction(ActionEvent event) {
        System.out.println("Exit, without canceling.");
    }

    public void cancelResChanger(Event event) {
        cancelResLabel.setText("Your Reservation Successfully Deleted!");
        //cancelButton.hide();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
