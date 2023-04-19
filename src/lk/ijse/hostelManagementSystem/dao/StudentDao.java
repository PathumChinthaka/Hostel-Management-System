package lk.ijse.hostelManagementSystem.dao;

import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDao {

    Session session= FactoryConfiguration.getInstance().getSession();

    public List<Student> getAllStudents(){
        Session session1= FactoryConfiguration.getInstance().getSession();
        String hql = "FROM Student";
        Query query = session1.createQuery(hql);
        List results = query.getResultList();
        return results;
    }

    public Integer saveStudentDetails(Student student){
        Transaction transaction=session.beginTransaction();

        try {
            session.save(student);
            transaction.commit();
            System.out.println("Student Saved Successfully .........................");
            return 1;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Student Not Saved");
            return -1;
        }

    }

    public void updateStudentData(Student student){

        String hql="UPDATE Student set student_Name = :newName , address = :newAddress ," +
                "student_Dob = :newDob, contact_No = :newContact WHERE student_Id = :StudentId ";

        Query query=session.createQuery(hql);
        query.setParameter("newName",student.getStudent_Name());
        query.setParameter("newAddress",student.getAddress());
        query.setParameter("newDob",student.getStudent_Dob());
        query.setParameter("newContact",student.getContact_No());
        query.setParameter("StudentId",student.getStudent_Id());

        Transaction transaction=session.beginTransaction();
        try {
            int result= query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deleteStudentDetail(Student student1){

        String hql="DELETE Student WHERE student_Id = :Studentid ";

        Query query=session.createQuery(hql);
        query.setParameter("Studentid",student1.getStudent_Id());

        Transaction transaction=session.beginTransaction();
        try {
            int result= query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Student> searchStudentDetail(String student_Name){

        String hql="FROM Student WHERE student_Name = :Sname";
        Query query=session.createQuery(hql);
        query.setParameter("Sname",student_Name);
        List result=query.getResultList();
        return result;
    }
}
