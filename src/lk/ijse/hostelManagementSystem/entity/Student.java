package lk.ijse.hostelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Student {
    @Id
    private String student_Id;
    private String student_Name;
    private String address;
    private String student_Dob;
    private String gender;
    private int contact_No;
    @OneToMany(
            mappedBy = "student",
            targetEntity = Reservation.class,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}
    )
    private List<Reservation> reservationList=new ArrayList<>();

}
