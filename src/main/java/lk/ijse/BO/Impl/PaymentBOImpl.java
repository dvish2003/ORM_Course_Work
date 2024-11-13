package lk.ijse.BO.Impl;

import lk.ijse.BO.PaymentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.Impl.PaymentDAO;
import lk.ijse.DTO.PaymentDTO;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Payment;
import lk.ijse.Entity.Student;
import lk.ijse.Entity.Student_Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Payment);
    @Override
    public boolean save(PaymentDTO dto) throws Exception {
        return paymentDAO.save(new Payment(
                dto.getPay_id(),
                dto.getPay_date(),
                dto.getPay_amount(),
                new Student_Course(
                dto.getStudentCourse().getStudent_course_id(),
                new Student(),
                new Course(),
                dto.getStudentCourse().getRegistration_date(),
                new ArrayList<>()
                )));
    }

    @Override
    public boolean update(PaymentDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String ID) throws Exception {
        return false;
    }
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNextId();
    }

    @Override
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
