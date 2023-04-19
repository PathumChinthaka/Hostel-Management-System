package lk.ijse.hostelManagementSystem.utill;

import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Rooms;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {

        Configuration configuration = new Configuration();

        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("Hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(Student.class)
                .addAnnotatedClass(Rooms.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() {

        return (factoryConfiguration == null) ? new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
