package lk.ijse.BO.Impl;

import lk.ijse.BO.PaymentBO;
import lk.ijse.DTO.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    @Override
    public boolean save(PaymentDTO dto) throws Exception {
        return false;
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
        return "";
    }

    @Override
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
