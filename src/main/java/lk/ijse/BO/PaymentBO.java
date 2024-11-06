package lk.ijse.BO;

import lk.ijse.DTO.CourseDTO;
import lk.ijse.DTO.PaymentDTO;
import lk.ijse.DTO.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public boolean save(PaymentDTO dto) throws Exception;

    public boolean update(PaymentDTO dto) throws Exception;

    public boolean delete(String ID)throws Exception;
    public String generateNextId() throws SQLException, ClassNotFoundException;
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException;
}
