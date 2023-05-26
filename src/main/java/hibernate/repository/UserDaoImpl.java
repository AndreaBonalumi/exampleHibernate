package hibernate.repository;

import entity.User;
import hibernateConf.HibernateConf;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDaoImpl implements UserDao {

    Session session = HibernateConf.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    EntityManager entityManager;
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        try {
            String JPQL = "select a from User a";
            return entityManager.createQuery(JPQL).getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getById(int id) {
        try {
            String JPQL = "select a from User a where a.id = :id";
            return (User) entityManager.createQuery(JPQL).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void edit(User user) {

        try {
            session.merge(user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User user) {
        try {
            session.delete(user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void Insert(User user) {
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
