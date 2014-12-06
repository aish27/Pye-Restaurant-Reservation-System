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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class BookReservationController implements Initializable {

    @FXML
    private AnchorPane contentPane;
    @FXML
    public Label selectedRestaurantName;
    @FXML
    public Label selectedRestaurantLocation;
    @FXML
    public Label selectedRestaurantPhoneNumber;
    @FXML
    public ComboBox selectedHour;
    @FXML
    public ComboBox selectedMin;
    @FXML
    public DatePicker selectedDate;

    @FXML
    private void bookButtonAction(ActionEvent event) {
        System.out.println("Book my Reservation button clicked.");
        //makeBooking(event);
    }

    /**
     *
     * @param event
     */
    private void makeBoooking(ActionEvent event) {
        try {
            URL url = getClass().getResource("/pye/project/SucessPage.fxml");
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
