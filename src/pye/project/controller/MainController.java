/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pye.project.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
    private void searchButtonAction(ActionEvent event) {
        System.out.println("Search Results!");
        openResults(event);
    }

    @FXML    
    private void lookupButtonAction(ActionEvent event) {
        System.out.println("Reservation Lookup");
        openReservation(event);
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
