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
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class CancelReservationController implements Initializable {

    @FXML
    public Label cancelResLabel;
    @FXML
    private AnchorPane reservationContentPane;
    @FXML
    private Button cancelButton;
    @FXML
    private Button noButton;

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        cancelResChanger(event);
    }

    @FXML
    private void noButtonAction(ActionEvent event) {
        cancelButton.setVisible(false);
        noButton.setVisible(false);
        cancelResLabel.setText(" ");
    }

    public void cancelResChanger(Event event) {
        cancelResLabel.setText("Your Reservation Successfully Deleted!");
        cancelButton.setVisible(false);
        noButton.setVisible(false);
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
