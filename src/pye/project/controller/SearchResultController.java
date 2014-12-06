/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pye.project.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pye.project.model.Restaurant;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class SearchResultController implements Initializable {

    @FXML
    TableView<ObservableIssue> searchResultTable;
    @FXML
    TableColumn<ObservableIssue, String> colRestaurant;
    @FXML
    TableColumn<ObservableIssue, String> colCuisine;
    @FXML
    TableColumn<ObservableIssue, String> colAddress;
    @FXML
    TableColumn<ObservableIssue, String> colPrice;
    @FXML
    TableColumn<ObservableIssue, String> colSelect;

    public TableView getTableView() {
        //ObservableList<Restaurant> data = FXCollections<Restaurant> sequence(
        //                new Restaurant("Hello"),
        //                TableView < Restaurant > tableView = new TableView<Restaurant>());
        //tableView.setItems(data);

        TableView<Restaurant> tableView = new TableView<>();
        
        
        colRestaurant = new TableColumn("Restaurant");
        colRestaurant.setText("dog");

        return tableView;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private static class ObservableIssue {

        public ObservableIssue() {
        }
    }

}
