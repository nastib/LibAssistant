
package com.javafx.libassistant.ui.addmember;

import com.javafx.libassistant.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddMemberController implements Initializable {

    private String query;
    private DatabaseHandler handler;
    
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton cancel;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         handler = DatabaseHandler.getInstance();
    }    

    @FXML
    private Boolean saveAction(ActionEvent event) {
        String memberID = id.getText();
        String memberName = name.getText();
        String memberMobile = mobile.getText();
        String memberEmail = email.getText();

        
        if(memberID.isEmpty() || memberName.isEmpty()|| memberMobile.isEmpty()||memberEmail.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires !");
            alert.showAndWait();
        } else {
            query = "INSERT INTO MEMBER VALUES ("
                     +"'"+memberID+"',"
                     +"'"+memberName+"',"
                     +"'"+memberMobile +"',"
                     +"'"+memberEmail
                     +"' )";
            
            System.out.println(query);
            try {
                handler.getStatement().execute(query);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, query.concat(" INSERT SUCCEFULL"));
                return true;
            } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                  
            } finally {}
        }
        return false; 
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void validateField(KeyEvent event) {
                   Alert alert = new Alert(AlertType.WARNING);
           alert.setHeaderText(null);
           alert.setContentText("Saisie du champ est obligatoir");
           alert.showAndWait();
    }
    
}
