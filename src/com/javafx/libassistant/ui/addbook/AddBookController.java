package com.javafx.libassistant.ui.addbook;

import com.javafx.libassistant.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 * @author ADMIN
 */
public class AddBookController implements Initializable {
    
    private Label label;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField publisher;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton cancel;
    
    DatabaseHandler databaseHandler;
    String query;
    @FXML
    private AnchorPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler =  DatabaseHandler.getInstance();
    }    

    @FXML
    private Boolean saveAction(ActionEvent event) {
        String bookID = id.getText();
        String bookTitle = title.getText();
        String bookAuthor = author.getText();
        String bookPublisher = publisher.getText();
        Boolean bookisAvail = true;

        
        if(bookID.isEmpty() || bookTitle.isEmpty()|| bookAuthor.isEmpty()||bookPublisher.isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires !");
            alert.showAndWait();
        } else {
            query = "INSERT INTO BOOK VALUES ("
                     +"'"+bookID+"',"
                     +"'"+bookTitle+"',"
                     +"'"+bookAuthor +"',"
                     +"'"+bookPublisher+"',"
                     +""+ bookisAvail 
                     +" )";
            
            System.out.println(query);
            try {
                databaseHandler.getStatement().execute(query);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, query.concat(" INSERT SUCCEFULL"));
                return true;
            } catch (SQLException e) {
                    Alert alert = new Alert(AlertType.ERROR);
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

    
}
