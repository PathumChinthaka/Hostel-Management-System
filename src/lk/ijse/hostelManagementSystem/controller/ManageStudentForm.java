package lk.ijse.hostelManagementSystem.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelManagementSystem.dao.StudentDao;
import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.utill.Navigation;
import lk.ijse.hostelManagementSystem.utill.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageStudentForm {

    public TextField txtStudentId;
    public AnchorPane pane;
    @FXML
    private TableColumn<String, Student> culm_Address;

    @FXML
    private TableColumn<String, Student> culm_Dob;

    @FXML
    private TableColumn<String, Student> culmStudent_Id;

    @FXML
    private TableColumn<String, Student> culm_Sname;

    @FXML
    private TableColumn<String, Student> culmGender;

    @FXML
    private TableColumn<Integer, Student> culmContact;

    @FXML
    private TableView<Student> tblStudent;

    public TextField txtSid;

    public TextField txtContact1;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtSearchStudent;

    @FXML
    private TextField txtStudentName;

    StudentDao studentDao = new StudentDao();
    Reservation reservation=new Reservation();
    List<Reservation>reservationList=new ArrayList<>();


    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        setTableData();
    }

    public void addStudent() {

        String student_Id = txtStudentId.getText();
        String student_Name = txtStudentName.getText();
        String address = txtAddress.getText();
        String student_Dob = txtDob.getText();
        String gender = txtGender.getText();
        int contact_No = Integer.parseInt(txtContact1.getText());
        reservationList.add(reservation);
        Student student = new Student(student_Id, student_Name, address, student_Dob, gender, contact_No,reservationList);
        studentDao.saveStudentDetails(student);
        System.out.println("Student kenek Yawuwa");
        setTableData();

    }

    public void setCellValue() {

        culmStudent_Id.setCellValueFactory(new PropertyValueFactory<>("student_Id"));
        culm_Sname.setCellValueFactory(new PropertyValueFactory<>("student_Name"));
        culm_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        culm_Dob.setCellValueFactory(new PropertyValueFactory<>("student_Dob"));
        culmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        culmContact.setCellValueFactory(new PropertyValueFactory<>("contact_No"));
    }

    public void setTableData() {

        tblStudent.getItems().clear();
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = studentDao.getAllStudents();
            for (Student student : studentList) {
                tblStudent.getItems().add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddStudent(javafx.event.ActionEvent actionEvent) {

        if(txtStudentId.getText().isEmpty()||!txtStudentId.getText().matches("^[A-Z 0-9]+$")) {
            new Alert(Alert.AlertType.ERROR, "Please Check StudentID Again").show();
        }else if (txtStudentName.getText().isEmpty()||!txtStudentName.getText().matches("^[A-Za-z]+$")){
            new Alert(Alert.AlertType.ERROR, "Please Check StudentName Again").show();
        }else if(txtAddress.getText().isEmpty()||!txtAddress.getText().matches("^[A-Za-z 0-9]+$")){
            new Alert(Alert.AlertType.ERROR, "Please Check Address Again").show();
//        }else if(txtDob.getText().isEmpty()||!txtDob.getText(){
//            new Alert(Alert.AlertType.ERROR, "Please Check Dob Again").show();
        }else if(txtGender.getText().isEmpty()||!txtGender.getText().matches("^[A-Za-z]+$")){
            new Alert(Alert.AlertType.ERROR, "Please Check StudentGender Again").show();
        }else if(txtContact1.getText().isEmpty()||!txtContact1.getText().matches("^[0123456789]+$")){
            new Alert(Alert.AlertType.ERROR, "Please Check ContactNo Again").show();
        }else {
            addStudent();
        }
    }

    public void updateStudentDetail() {
        String sId = txtStudentId.getText();
        String sName = txtStudentName.getText();
        String sAddress = txtAddress.getText();
        String sDob = txtDob.getText();
        String sGender = txtGender.getText();
        int sContact = Integer.parseInt(txtContact1.getText());
        Student student = new Student(sId, sName, sAddress, sDob, sGender, sContact,reservationList);
        studentDao.updateStudentData(student);
    }

    public void btnUpdateStudent(ActionEvent actionEvent) {
        updateStudentDetail();
        tblStudent.getItems().clear();
        setTableData();
    }

    public void deleteStudentData() {
        String sId = txtStudentId.getText();
        String sName = txtStudentName.getText();
        String sAddress = txtAddress.getText();
        String sDob = txtDob.getText();
        String sGender = txtGender.getText();
        int sContact = Integer.parseInt(txtContact1.getText());
        Student student1 = new Student(sId, sName, sAddress, sDob, sGender, sContact,reservationList);
        studentDao.deleteStudentDetail(student1);
    }

    public void btnDeleteStudent(ActionEvent actionEvent) {
        deleteStudentData();
        tblStudent.getItems().clear();
        setTableData();
    }

    public void searchStudentData() {
        tblStudent.getItems().clear();
        String student_Name = txtSearchStudent.getText();
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = studentDao.searchStudentDetail(student_Name);
            for (Student student1 : studentList) {
                tblStudent.getItems().add(student1);
            }
            tblStudent.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSearchStudent(ActionEvent actionEvent) {
        searchStudentData();
        tblStudent.getItems().clear();
        setTableData();
    }

    public void getSelectedRaw() {
        ObservableList<Student> studentObservableList;
        studentObservableList = tblStudent.getSelectionModel().getSelectedItems();
        txtStudentId.setText(studentObservableList.get(0).getStudent_Id());
        txtStudentName.setText(studentObservableList.get(0).getStudent_Name());
        txtAddress.setText(studentObservableList.get(0).getAddress());
        txtDob.setText(studentObservableList.get(0).getStudent_Dob());
        txtGender.setText(studentObservableList.get(0).getGender());
        txtContact1.setText(String.valueOf(studentObservableList.get(0).getContact_No()));
    }

    public void museClickedEvent(MouseEvent mouseEvent) {
        getSelectedRaw();
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.User, pane);
    }
}
