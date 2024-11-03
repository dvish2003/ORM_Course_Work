package lk.ijse;

import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        pp p = new pp(1,"ss",12);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();
    }
}
