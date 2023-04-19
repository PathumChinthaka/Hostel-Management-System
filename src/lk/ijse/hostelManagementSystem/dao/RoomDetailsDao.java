package lk.ijse.hostelManagementSystem.dao;

import lk.ijse.hostelManagementSystem.entity.Rooms;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDetailsDao {

    Session session= FactoryConfiguration.getInstance().getSession();

    public List<Rooms> getAllRoomDetails(){
        Session session1= FactoryConfiguration.getInstance().getSession();

        String hql="From Rooms";
        Query query=session1.createQuery(hql);
        List result=query.getResultList();
        return result;
    }

    public Integer addRoomData(Rooms rooms){

        Transaction transaction=session.beginTransaction();
        try {
            session.save(rooms);
            transaction.commit();
            System.out.println("Room Data Added......");
            return 1;
        } catch (Exception e) {
            transaction.rollback();
            return -1;
        }
    }

    public void updateRoomDetail(Rooms rooms1){

        String hql="UPDATE Rooms set key_Money = :newKm , room_Qty = :newQty ," +
                " roomType_Id = :Roomid WHERE room_Id = :roomID ";
        Query query=session.createQuery(hql);
        query.setParameter("newKm",rooms1.getKey_Money());
        query.setParameter("newQty",rooms1.getRoom_Qty());
        query.setParameter("Roomid",rooms1.getRoomType_Id());
        query.setParameter("roomID",rooms1.getRoom_Id());

        Transaction transaction=session.beginTransaction();
        try {
            int result= query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deleteRoomDetail(Rooms rooms2){

        String hql="DELETE Rooms WHERE room_Id = :Roomid ";
        Query query=session.createQuery(hql);
        query.setParameter("Roomid",rooms2.getRoom_Id());
        Transaction transaction=session.beginTransaction();
        try {
            int result= query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Rooms> searchRoomDetails(String roomid){
        String hql="FROM Rooms WHERE room_Id = :srchRoomId ";
        Query query=session.createQuery(hql);
        query.setParameter("srchRoomId",roomid);
        List result=query.getResultList();
        return result;
    }

}
