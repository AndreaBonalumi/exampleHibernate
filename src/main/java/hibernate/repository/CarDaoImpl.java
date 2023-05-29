package hibernate.repository;

import entity.Car;
import hibernateConf.HibernateConf;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDaoImpl implements CarDao {
    private final Session session;

    public CarDaoImpl() {
        session = HibernateConf.getSessionFactory().openSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getAll() {
        try {
            String JPQL = "FROM Car";
            return session.createQuery(JPQL).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Car getById(int id) {
        try {
            String JPQL = "FROM Car WHERE id = :id";
            return (Car) session.createQuery(JPQL).setParameter("id", id).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(Car car) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Car car) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Car car) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
