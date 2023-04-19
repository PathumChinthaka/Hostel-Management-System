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
public class Rooms {
    @Id()
    private String room_Id;
    private String roomType_Id;
    private double key_Money;
    private int room_Qty;
    @OneToMany(
            mappedBy = "rooms",
            targetEntity = Reservation.class,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}
    )
    private List<Reservation> reservations=new ArrayList<>();
}
