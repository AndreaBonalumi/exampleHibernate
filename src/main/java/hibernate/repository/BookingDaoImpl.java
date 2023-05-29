package hibernate.repository;

import entity.Booking;
import hibernateConf.HibernateConf;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.List;

public class BookingDaoImpl implements BookingDao{
    Session session;
    public BookingDaoImpl() { session = HibernateConf.getSessionFactory().openSession();}
    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getAll() {
        try {
            String JPQL = "FROM Booking";
            return session.createQuery(JPQL).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Booking getById(int id) {
        try {
            String JPQL = "FROM Booking WHERE Booking.id = :id";
            return (Booking) session.createQuery(JPQL).setParameter("id", id).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(Booking booking) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(booking);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Booking booking) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(booking);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Booking booking) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.merge(booking);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
