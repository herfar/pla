package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;
public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();
        userDao.saveUser("in", "var", (byte)18);
        userDao.saveUser("vano", "fak", (byte)28);
        userDao.saveUser("keril", "hello", (byte)30);

        userDao.removeUserById(2);

    }
}
