package lk.ijse.hostelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelManagementSystem.dao.ReservationDao;
import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Rooms;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.utill.Navigation;
import lk.ijse.hostelManagementSystem.utill.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageReservationForm {

    public Label lblDate;

    @FXML
    private TableColumn<?, ?> culmReservationId;

    @FXML
    private TableColumn<?, ?> culmDate;

    @FXML
    private TableColumn<?, ?> culmRoomTypeId;

    @FXML
    private TableColumn<?, ?> culmStatus;

    @FXML
    private TableColumn<?, ?> culmStudentId;

    @FXML
    private TableColumn<?, ?> culmRoomId;

    @FXML
    private Label lblId;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Reservation> tblRoomReservation;

    @FXML
    private TextField txtReservationId;

    @FXML
    private TextField txtRoomtypeId;

    @FXML
    private TextField txtSearchReser;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtStudentId;

    ReservationDao reservationDao=new ReservationDao();
    Student student=new Student();
    Rooms rooms=new Rooms();
    ManageStudentForm manageStudentForm=new ManageStudentForm();
    ManageRoomsForm manageRoomsForm=new ManageRoomsForm();


    public void initialize() {
        setDate();
        setTableData();
        setCellValue();
    }


    public void addReservationDetail(){
        String res_Id = txtReservationId.getText();
        String date=lblDate.getText();
        String student_Id = txtStudentId.getText();
        String roomType_Id = txtRoomtypeId.getText();
        String status = txtStatus.getText();
        rooms=manageRoomsForm.reservation.getRooms();
        student=manageStudentForm.reservation.getStudent();
        Reservation reservation=new Reservation(res_Id,date,student_Id,roomType_Id,status,rooms,student);
        reservationDao.addReservationData(reservation);
        setTableData();
    }

    public void setCellValue() {
        culmReservationId.setCellValueFactory(new PropertyValueFactory<>("res_Id"));
        culmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        culmStudentId.setCellValueFactory(new PropertyValueFactory<>("student_Id"));
        culmRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomType_Id"));
        culmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        culmRoomId.setCellValueFactory(new PropertyValueFactory<>("rooms"));
    }

    public void setTableData(){
        tblRoomReservation.getItems().clear();
        List<Reservation> reservations=new ArrayList<>();
        try {
            reservations=reservationDao.getAllReservationDetails();
            for(Reservation reservation:reservations){
                tblRoomReservation.getItems().add(reservation);
            }
            tblRoomReservation.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddRoomData(ActionEvent event) {

        if(txtReservationId.getText().isEmpty()||!txtReservationId.getText().matches("^[A-Z 0-9]+$")) {
            new Alert(Alert.AlertType.ERROR, "Please Check ReservationID Again").show();
        }else if (txtStudentId.getText().isEmpty()||!txtStudentId.getText().matches("^[A-Z 0-9]+$")){
            new Alert(Alert.AlertType.ERROR, "Please Check studentID Again").show();
        }else if(txtRoomtypeId.getText().isEmpty()||!txtRoomtypeId.getText().matches("^[A-Za-z 0123456789]+$")){
            new Alert(Alert.AlertType.ERROR, "Please Check roomtypeId Again").show();
        }else if(txtStatus.getText().isEmpty()||!txtStatus.getText().matches("^[A-Za-z]+$")){
            new Alert(Alert.AlertType.ERROR, "Please Check status Again").show();
        }else {
            addReservationDetail();
        }
    }

    @FXML
    void btnDashboard(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.User,pane);
    }

    @FXML
    void btnDeleteRoomData(ActionEvent event) {

    }

    @FXML
    void btnSearchReser(ActionEvent event) {

    }

    @FXML
    void btnUpdateRoomData(ActionEvent event) {

    }

    @FXML
    void museClickedEvent(MouseEvent event) {

    }

    public void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

}
