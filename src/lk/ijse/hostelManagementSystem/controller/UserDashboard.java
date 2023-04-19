package lk.ijse.hostelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelManagementSystem.utill.Navigation;
import lk.ijse.hostelManagementSystem.utill.Routes;

import java.io.IOException;

public class UserDashboard {
    public AnchorPane pane;

    public void btnManageStudent(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Addstudent,pane);
    }

    public void btnManageRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AddRooms,pane);
    }

    public void btnManageReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Reservation,pane);
    }

    public void btnUserManage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ManageUser,pane);
    }
}
