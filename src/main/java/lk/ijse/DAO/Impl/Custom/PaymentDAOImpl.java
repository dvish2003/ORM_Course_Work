package lk.ijse.DAO.Impl.Custom;

import lk.ijse.DAO.Impl.PaymentDAO;
import lk.ijse.Entity.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String ID) throws Exception {
        return false;
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Payment searchByID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getIds() {
        return List.of();
    }
}
