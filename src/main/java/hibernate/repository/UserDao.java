package hibernate.repository;

import entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();
    User getById(int id);
    void edit(User user);
    void delete(User user);
    void Insert(User user);
}
