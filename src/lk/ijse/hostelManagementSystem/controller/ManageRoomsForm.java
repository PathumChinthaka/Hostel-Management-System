package lk.ijse.hostelManagementSystem.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelManagementSystem.dao.RoomDetailsDao;
import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Rooms;
import lk.ijse.hostelManagementSystem.utill.Navigation;
import lk.ijse.hostelManagementSystem.utill.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageRoomsForm {

    @FXML
        private TableColumn<?, ?> culmKeyMoney;

        @FXML
        private TableColumn<?, ?> culmRoomQty;

        @FXML
        private TableColumn<?, ?> culmRoomType;

        @FXML
        private TableColumn<?, ?> culmRoomid;

        @FXML
        private TableColumn<?, ?> culmRoomId;

        @FXML
        private Label lblId;

        @FXML
        private TableView<Rooms> tblRoomDetails;

        @FXML
        private TextField txtKeyMoney;

        @FXML
        private TextField txtRoomQty;

        @FXML
        private TextField txtRoomtype;

        @FXML
        private TextField txtSearchRoom;

        public TextField txtRoomId;


    public AnchorPane pane;

        RoomDetailsDao roomDetailsDao=new RoomDetailsDao();
        Reservation reservation=new Reservation();
        List<Reservation>reservationList=new ArrayList<>();


    public void initialize(){
            setCellValue();
            setTableData();
        }

        public void addRoomDetails(){
            String room_Id = txtRoomId.getText();
            String roomType_Id = txtRoomtype.getText();
            double key_Money = Double.parseDouble(txtKeyMoney.getText());
            int room_Qty = Integer.parseInt(txtRoomQty.getText());
            reservationList.add(reservation);
            Rooms rooms=new Rooms(room_Id,roomType_Id,key_Money,room_Qty,reservationList);
            roomDetailsDao.addRoomData(rooms);
            System.out.println("Room Detail Sent");
            setTableData();

        }

        public void setCellValue() {
            culmRoomId.setCellValueFactory(new PropertyValueFactory<>("room_Id"));
            culmRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType_Id"));
            culmKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_Money"));
            culmRoomQty.setCellValueFactory(new PropertyValueFactory<>("room_Qty"));
        }

        public void setTableData(){
            tblRoomDetails.getItems().clear();
            List<Rooms> roomsList=new ArrayList<>();
            try {
                roomsList=roomDetailsDao.getAllRoomDetails();
                for(Rooms rooms:roomsList){
                    tblRoomDetails.getItems().add(rooms);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        void btnAddRoomData(ActionEvent event) {

            if(txtRoomId.getText().isEmpty()||!txtRoomId.getText().matches("^[A-Z 0-9]+$")) {
                new Alert(Alert.AlertType.ERROR, "Please Check RoomID Again").show();
            }else if (txtRoomtype.getText().isEmpty()||!txtRoomtype.getText().matches("^[A-Za-z 0-9]+$")){
                new Alert(Alert.AlertType.ERROR, "Please Check RoomTypeID Again").show();
            }else if(txtKeyMoney.getText().isEmpty()||!txtKeyMoney.getText().matches("^[A-Za-z 0.0-9]+$")){
                new Alert(Alert.AlertType.ERROR, "Please Check KeyMoney Again").show();
            }else if(txtRoomQty.getText().isEmpty()||!txtRoomQty.getText().matches("^[0123456789]+$")){
                new Alert(Alert.AlertType.ERROR, "Please Check RoomQty Again").show();
            }else {
                addRoomDetails();
            }

        }

        public void updateRoomDetail(){
            String roomId = txtRoomId.getText();
            String roomTypeId = txtRoomtype.getText();
            double keyMoney = Double.parseDouble(txtKeyMoney.getText());
            int roomQty = Integer.parseInt(txtRoomQty.getText());
            Rooms rooms1=new Rooms(roomId, roomTypeId,keyMoney,roomQty,reservationList);
            new RoomDetailsDao().updateRoomDetail(rooms1);
        }

        public void deleteRoomData(){
            String roomId = txtRoomId.getText();
            String roomTypeId = txtRoomtype.getText();
            double keyMoney = Double.parseDouble(txtKeyMoney.getText());
            int roomQty = Integer.parseInt(txtRoomQty.getText());
            Rooms rooms2=new Rooms(roomId,roomTypeId,keyMoney,roomQty,reservationList);
            new RoomDetailsDao().deleteRoomDetail(rooms2);
        }


        @FXML
        void btnUpdateRoomData(ActionEvent event) {
            updateRoomDetail();
            tblRoomDetails.getItems().clear();
            setTableData();
        }

        @FXML
        void btnDeleteRoomData(ActionEvent event) {
            deleteRoomData();
            tblRoomDetails.getItems().clear();
            setTableData();
        }

        public void searchRoomData(){
            tblRoomDetails.getItems().clear();
            String roomid = txtSearchRoom.getText();
            List<Rooms> roomsList=new ArrayList<>();
            try {
                roomsList=roomDetailsDao.searchRoomDetails(roomid);
                for(Rooms rooms:roomsList){
                    tblRoomDetails.getItems().add(rooms);
                }
                tblRoomDetails.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        void btnSearchRoom(ActionEvent event) {
           searchRoomData();
        }

        public void getSelectedRow(){
            ObservableList<Rooms>roomsObservableList;
            roomsObservableList=tblRoomDetails.getSelectionModel().getSelectedItems();
            txtRoomId.setText(roomsObservableList.get(0).getRoom_Id());
            txtRoomtype.setText(roomsObservableList.get(0).getRoomType_Id());
            txtKeyMoney.setText(String.valueOf(roomsObservableList.get(0).getKey_Money()));
            txtRoomQty.setText(String.valueOf(roomsObservableList.get(0).getRoom_Qty()));
        }

        public void museClickedEvent(MouseEvent mouseEvent) {
       getSelectedRow();
    }

        @FXML
        void btnDashboard(ActionEvent event) throws IOException {
            Navigation.navigate(Routes.User,pane);
        }


}
