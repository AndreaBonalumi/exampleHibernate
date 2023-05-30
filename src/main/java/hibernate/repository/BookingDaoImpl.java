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
    @SuppressWarnings("unchecked")
    public List<Booking> getByIdUser(int id) {
        try {
            String JPQL = "from Booking b join fetch b.idCar join b.idUser where b.idUser = :id";
            return session.createQuery(JPQL).setParameter("id", id).getResultList();
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
