package lk.ijse.DAO.Impl;

import lk.ijse.DAO.UserDAO;
import lk.ijse.Entity.User;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
        }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(User entity) throws Exception {
        return false;
    }
}
