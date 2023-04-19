package lk.ijse.hostelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class User {
    @Id
    private String user_Id;
    private String date;
    private String user_Name;
    private String password;
}
