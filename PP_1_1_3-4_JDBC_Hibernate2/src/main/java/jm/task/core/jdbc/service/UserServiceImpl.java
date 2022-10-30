package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoHibernateImpl node = new UserDaoHibernateImpl();
    public void createUsersTable() throws SQLException {
        node.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        node.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        node.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        node.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return node.getAllUsers();
    }

    public void cleanUsersTable() {
        node.cleanUsersTable();
    }
}
