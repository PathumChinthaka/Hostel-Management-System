package lk.ijse.hostelManagementSystem.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.hostelManagementSystem.utill.Navigation;
import lk.ijse.hostelManagementSystem.utill.Routes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LoginInterface {

    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label lblTime;
    public Label lblDate;
    public AnchorPane pane;
    public Label lblpassword;

    public void initialize() {
        setDateAndTime();
    }

    private void User() throws IOException {
        String username = txtUserName.getText();
        String password=txtPassword.getText();

        if(username.equals("") && password.equals("")){

            new Alert(Alert.AlertType.CONFIRMATION,"Welocme "+txtUserName.getText()+ " Have a Great Day").show();

            Navigation.navigate(Routes.User,pane);

        }else {
            new Alert(Alert.AlertType.ERROR," ooPs ! Invalid UserName Or Password").show();
        }
    }

    public void loginAction(ActionEvent actionEvent) throws IOException {
        User();
    }

    private void setDateAndTime() {

        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd \t hh:mm:ss a");
                    lblDate.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void btnShowpassword(ActionEvent actionEvent) {
        lblpassword.setText(txtPassword.getText());
        txtPassword.clear();
    }
}
