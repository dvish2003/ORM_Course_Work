package lk.ijse.DAO.Impl.Custom;

import lk.ijse.DAO.Impl.PaymentDAO;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Payment;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment entity) throws Exception {
Session session = FactoryConfiguration.getInstance().getSession();
Transaction tx = session.beginTransaction();
session.save(entity);
tx.commit();
session.close();
return true;
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Payment payment;
        try {
            payment = session.createQuery(
                            "FROM Payment p WHERE p.student_course.student_course_id = :studentCourseId", Payment.class)
                    .setParameter("studentCourseId", id)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
        return payment;
    }
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";

        try {
            Object item = session.createQuery("SELECT pay_id FROM Payment ORDER BY pay_id DESC")
                    .setMaxResults(1)
                    .uniqueResult();

            if (item != null) {
                String itemCode = item.toString();

                if (itemCode.startsWith("PAY") && itemCode.length() > 3) {
                    int idNum = Integer.parseInt(itemCode.substring(3));
                    nextId = "PAY" + String.format("%03d", ++idNum);
                } else {
                    nextId = "PAY001";
                }
            } else {
                nextId = "PAY001";
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return nextId;
    }

    @Override
    public List<String> getIds() {
        return List.of();
    }
}
