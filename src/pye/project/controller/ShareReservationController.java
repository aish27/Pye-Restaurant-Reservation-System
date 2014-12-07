/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pye.project.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pye.project.model.DataAccess;
import pye.project.model.EmailSender;
import pye.project.model.Reservation;

/**
 * FXML Controller class
 *
 * @author Ambrin
 */
public class ShareReservationController implements Initializable {
    
    @FXML
    public TextField email1;
    @FXML
    public TextField email2;
    @FXML
    public TextField email3;
    @FXML
    public TextField email4;
    @FXML
    public TextField email5;
    
    @FXML
    private void shareButtonAction(ActionEvent event) throws Exception {
        
                

    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
