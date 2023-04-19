package lk.ijse.hostelManagementSystem.dao;

import lk.ijse.hostelManagementSystem.entity.User;
import lk.ijse.hostelManagementSystem.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDao {
    Session session= FactoryConfiguration.getInstance().getSession();

    public List<User> allUserDetails(){
        String hql="FROM User";
        Query query=session.createQuery(hql);
        List result=query.getResultList();
        return result;
    }

    public void saveUserData(User user){
        Transaction transaction=session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            System.out.println("User Data Added...");
        } catch (Exception e) {
            System.out.println("User Not Added...");
            transaction.rollback();
        }
    }
}
