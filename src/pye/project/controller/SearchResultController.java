/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pye.project.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pye.project.model.Restaurant;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class SearchResultController implements Initializable {

    private ArrayList<Restaurant> restaurantList;
    
    @FXML
    private AnchorPane searchContentPane;
    @FXML
    Button bookButton;
    
    @FXML
    TableView<Restaurant> searchResultTable;
    @FXML
    TableColumn<Restaurant, String> colRestaurant;
    @FXML
    TableColumn<Restaurant, String> colCuisine;
    @FXML
    TableColumn<Restaurant, String> colAddress;
    @FXML
    TableColumn<Restaurant, String> colPrice;
    @FXML
    TableColumn<Restaurant, String> colOpenTime; 
    @FXML
    TableColumn<Restaurant, Boolean> colSelect;
           

    ObservableList<Restaurant> obsRestaurantList;

    public SearchResultController() {
        this.obsRestaurantList = FXCollections.observableArrayList();
    }

    /**
     *
     * @param list
     */
    public void setRestaurantListFromParent(ArrayList<Restaurant> list) {
        this.restaurantList = list;

        obsRestaurantList.addAll(restaurantList);

        for (Iterator<Restaurant> it = obsRestaurantList.iterator(); it.hasNext();) {
            Restaurant r = it.next();
        }

    }


    public static void refresh_table(TableView table) {
        for (int i = 0; i < table.getColumns().size(); i++) {
            ((TableColumn) (table.getColumns().get(i))).setVisible(false);
            ((TableColumn) (table.getColumns().get(i))).setVisible(true);
        }
    }
    
    // OPTION 1
    //If something is selected and button clicked
    //then get id from its array list
    
    
    // Option 2
    // Add button to column 
    // on button click get id from its array
    
    // Option 3
    // Add Button/Checkbox to list and attach Array ID to the item
    

    
    /**private void restaurantSelected(Event event){
        try {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    @FXML
    private void bookReservationButtonAction(ActionEvent event) {
        bookReservation(event);
    }
    
    public void bookReservation(Event event) {
        try {
            URL url = getClass().getResource("/pye/project/BookReservation.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane results = (AnchorPane) fxmlLoader.load(url.openStream());
            searchContentPane.getChildren().clear();
            searchContentPane.getChildren().add(results);
            bookButton.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colRestaurant = new TableColumn<>("Name");
        colRestaurant.setMinWidth(310);
        colRestaurant.setCellValueFactory(new PropertyValueFactory<>("name"));

        colCuisine = new TableColumn<>("Cuisine");
        colCuisine.setMinWidth(120);
        colCuisine.setCellValueFactory(new PropertyValueFactory<>("cuisine"));

        colAddress = new TableColumn<>("City");
        colAddress.setMinWidth(110);
        colAddress.setCellValueFactory(new PropertyValueFactory<>("city"));

        colPrice = new TableColumn<>("Price");
        colPrice.setMinWidth(80);
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colOpenTime = new TableColumn<>("OpenTime");
        colOpenTime.setMinWidth(110);
        colOpenTime.setCellValueFactory(new PropertyValueFactory<>("openTime"));
        
        colSelect = new TableColumn<>("Select");
        colSelect.setMinWidth(100);
        colSelect.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        searchResultTable.setItems(obsRestaurantList); //Creates my table

        searchResultTable.getColumns().addAll(colRestaurant, colCuisine, colAddress, colPrice, colOpenTime, colSelect);//Adds my Content
 

    }

}
