package lk.ijse.BO.Impl;

import lk.ijse.BO.Student_CourseBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.Impl.Student_CourseDAO;
import lk.ijse.DTO.PaymentDTO;
import lk.ijse.DTO.Student_CourseDTO;
import lk.ijse.Entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Student_CourseBOImpl implements Student_CourseBO {
   Student_CourseDAO studentCourseDAO = (Student_CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Student_Course);

   @Override
    public boolean save(Student_CourseDTO dto) throws Exception {
       return studentCourseDAO.save(new Student_Course(
               dto.getStudent_course_id(),
               new Student(
                       dto.getStudent().getStu_id(),
                       dto.getStudent().getStu_name(),
                       dto.getStudent().getStu_phone(),
                       dto.getStudent().getStu_email(),
                       dto.getStudent().getStu_address(),
                       new ArrayList<>(),
                       new User()
               ),
               new Course(
                       dto.getCourse().getCourse_id(),
                       dto.getCourse().getCourse_name(),
                       dto.getCourse().getDuration(),
                       dto.getCourse().getCourse_fee(),
                       new ArrayList<>()
               ),
               dto.getRegistration_date(),
               new ArrayList<>()
       ));
   }

    @Override
    public boolean update(Student_CourseDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String ID) throws Exception {
        return false;
    }
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return studentCourseDAO.generateNextId();
    }
}
