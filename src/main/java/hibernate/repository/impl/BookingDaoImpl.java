package hibernate.repository.impl;

import entity.Booking;
import hibernate.repository.BookingDao;
import hibernateConf.HibernateConf;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookingDaoImpl implements BookingDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getAll() {
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            String JPQL = "FROM Booking";
            return session.createQuery(JPQL).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getAllByUserId(int id) {
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            String JPQL = "from Booking b where b.user.id = :id";
            return session.createQuery(JPQL).setParameter("id", id).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Booking getById(int id) {
        try(Session session = HibernateConf.getSessionFactory().openSession()) {
            String JPQL = "from Booking where id=:id";
            return (Booking) session.createQuery(JPQL).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void insert(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
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

        try (Session session = HibernateConf.getSessionFactory().openSession()) {
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

        try (Session session = HibernateConf.getSessionFactory().openSession()) {
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
