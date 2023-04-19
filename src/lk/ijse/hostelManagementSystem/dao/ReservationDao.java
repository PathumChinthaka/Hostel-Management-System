package lk.ijse.hostelManagementSystem.dao;

import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Rooms;
import lk.ijse.hostelManagementSystem.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDao {

    Session session= FactoryConfiguration.getInstance().getSession();

    public List<Reservation> getAllReservationDetails(){
        Session session1= FactoryConfiguration.getInstance().getSession();
        String hql="From Reservation";
        Query query=session1.createQuery(hql);
        List result=query.getResultList();
        System.out.println("Select Quary Okey....................................");
        return result;
    }

    public void addReservationData(Reservation reservation){
        Transaction transaction=session.beginTransaction();
        try {
            session.save(reservation);
            transaction.commit();
            System.out.println("Reservation Data Added......");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("naaaaaaaa......");

        }
    }
}
