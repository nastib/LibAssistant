
package com.javafx.libassistant.ui.listmember;

import com.javafx.libassistant.beans.Member;
import com.javafx.libassistant.database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListMemberController implements Initializable {
    private final ObservableList<Member> list = FXCollections.observableArrayList();
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private  Member member = new Member();
    
    @FXML
    private TableView<Member> listMemberTable;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> idCol;
    @FXML
    private TableColumn<Member, String> mobileCol;
    @FXML
    private TableColumn<Member, String> emailCol;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        initCol();
        LoadData();
        
        
    }    
    
    private void initCol(){
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void LoadData() {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM MEMBER";
        try{
            ResultSet result = handler.getStatement().executeQuery(query);
            while(result.next()){
                String name = result.getString("name");
                String id = result.getString("id");
                String mobile = result.getString("mobile");
                String email = result.getString("email");
                
                member = new Member(name,id, mobile, email);
                list.add(member);
                log.log(Level.INFO, "Select succefull ".concat(member.toString()));
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait(); 
            
        } 
        listMemberTable.getItems().setAll(list);
        
    }        
}

