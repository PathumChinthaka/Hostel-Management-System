package lk.ijse.hostelManagementSystem.utill;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public static AnchorPane pane;

    public static void navigate(Routes route ,AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route){

            case User:
                window.setTitle("User Dashboard");
                initUI("UserDashboard.fxml");
                window.setWidth(1000.0);
                window.setHeight(600.0);
                window.centerOnScreen();
                window.setResizable(false);
                break;

            case Addstudent:
                window.setTitle("Add Student");
                initUI("ManageStudentForm.fxml");
                window.setWidth(1000.0);
                window.setHeight(600.0);
                window.setResizable(false);
                window.centerOnScreen();
                break;

            case AddRooms:
                window.setTitle("Manage Rooms");
                initUI("ManageRoomForm.fxml");
                window.setWidth(1000.0);
                window.setHeight(600.0);
                window.setResizable(false);
                window.centerOnScreen();
                break;

            case Reservation:
                window.setTitle("Manage Reservation");
                initUI("RoomReservationForm.fxml");
                window.setWidth(1000.0);
                window.setHeight(600.0);
                window.setResizable(false);
                window.centerOnScreen();
                break;

            case ManageUser:
                window.setTitle("Manage Users");
                initUI("ManageUserForm.fxml");
                window.setWidth(636.0);
                window.setHeight(450.0);
                window.setResizable(false);
                window.centerOnScreen();
                break;
        }
    }
    public static void initUI(String location) throws IOException {
       Navigation.pane.getChildren().add(FXMLLoader.
               load((Navigation.class.getResource("/lk/ijse/hostelManagementSystem/view/" + location))));
    }

}

