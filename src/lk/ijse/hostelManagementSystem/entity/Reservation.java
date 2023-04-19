package lk.ijse.hostelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Reservation {
    @Id
    private String res_Id;
    private String date;
    private String student_Id;
    private String roomType_Id;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_Id")
    @ToString.Exclude
    private Rooms rooms;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_Name")
    @ToString.Exclude
    private Student student;
}
