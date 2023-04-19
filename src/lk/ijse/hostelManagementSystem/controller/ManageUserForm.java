package lk.ijse.hostelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelManagementSystem.dao.UserDao;
import lk.ijse.hostelManagementSystem.entity.User;
import lk.ijse.hostelManagementSystem.utill.Navigation;
import lk.ijse.hostelManagementSystem.utill.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageUserForm {

        @FXML
        private TableColumn<?, ?> culmDate;

        @FXML
        private TableColumn<?, ?> culmPassword;

        @FXML
        private TableColumn<?, ?> culmUseerName;

        @FXML
        private TableColumn<?, ?> culmUserId;

        @FXML
        private Label lblDate;

        @FXML
        private AnchorPane pane2;

        @FXML
        private TableView<User> tblUsers;

        @FXML
        private TextField txtPassword;

        @FXML
        private TextField txtSearchUser;

        @FXML
        private TextField txtUserId;

        @FXML
        private TextField txtUserName;

        UserDao userDao=new UserDao();

        public void initialize(){
            setDate();
            setcellValues();
            setTableData();

        }


        public void addUser(){
            String user_Id = txtUserId.getText();
            String date = lblDate.getText();
            String user_Name = txtUserName.getText();
            String password = txtPassword.getText();

            User user=new User(user_Id,date,user_Name,password);
            userDao.saveUserData(user);

        }

        public void setcellValues(){
            culmUserId.setCellValueFactory(new PropertyValueFactory<>("user_Id"));
            culmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            culmUseerName.setCellValueFactory(new PropertyValueFactory<>("user_Name"));
            culmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        }

        public void setTableData(){
            tblUsers.getItems().clear();
            List<User>userList=new ArrayList<>();
            try {
                userList=userDao.allUserDetails();
                for(User user:userList){
                    tblUsers.getItems().add(user);
                }
                tblUsers.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        void btnAddUser(ActionEvent event) {

            if(txtUserId.getText().isEmpty()||!txtUserId.getText().matches("^[A-Z 0123456789]+$")) {
                new Alert(Alert.AlertType.ERROR, "Please Check UserID Again").show();
            }else if (txtUserName.getText().isEmpty()||!txtUserName.getText().matches("^[A-Za-z]+$")){
                new Alert(Alert.AlertType.ERROR, "Please Check UserName Again").show();
            }else if(txtPassword.getText().isEmpty()||!txtPassword.getText().matches("^[A-Za-z 0123456789]+$")){
                new Alert(Alert.AlertType.ERROR, "Please Check Password Again").show();
            }else if(txtSearchUser.getText().isEmpty()||!txtSearchUser.getText().matches("^[A-Za-z]+$")){
                new Alert(Alert.AlertType.ERROR, "Please Search User Again").show();
            }else {
                addUser();
            }
        }

        @FXML
        void btnDeleteUSer(ActionEvent event) {

        }

        @FXML
        void btnUpdateUser(ActionEvent event) {

        }

    public void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void btnGoBack(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.User,pane2);
    }
}

