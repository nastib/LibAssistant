package com.javafx.libassistant.ui.listbook;

import com.javafx.libassistant.beans.Book;
import com.javafx.libassistant.database.DatabaseHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListBookController implements Initializable {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final ObservableList<Book> list = FXCollections.observableArrayList();
    private Book book = new Book();
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Book> listBookTable;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> idCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, String> publisherCol;
    @FXML
    private TableColumn<Book, BooleanProperty> availabilityCol;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        InitCol();
        loadData();
    }    

    private void InitCol() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }
    
    private void loadData(){
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM BOOK";
        try{
            ResultSet result = handler.getStatement().executeQuery(query);
            while(result.next()){
                String title = result.getString("title");
                String id = result.getString("id");
                String author = result.getString("author");
                String publ = result.getString("publisher");
                Boolean avail = result.getBoolean("isAvail");
                
                book = new Book(title, id, author, publ, avail);
                list.add(book);
                log.log(Level.INFO, "Select succefull ".concat(book.toString()));
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait(); 
            
        } 
        listBookTable.getItems().setAll(list);
        
    }
    

}
