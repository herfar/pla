package jm.task.core.jdbc.dao;
import org.hibernate.Transaction;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception SQLException) {
            if (transaction != null) {
                transaction.rollback();
            }
            SQLException.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("drop table if exists Users").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception SQLException) {
            if (transaction != null) {
                transaction.rollback();
            }
            SQLException.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("delete from Users where id = " + id).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception SQLException) {
            if (transaction != null) {
                transaction.rollback();
            }
            SQLException.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            users = session.createQuery("from User").getResultList();
        } catch (Exception SQLException) {
            if (transaction != null) {
                transaction.rollback();
            }
            SQLException.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
             NativeQuery query = session.createSQLQuery("truncate table Users");
             query.executeUpdate();
             transaction.commit();
        } catch (Exception SQLException) {
            if (transaction != null) {
                transaction.rollback();
            }
            SQLException.printStackTrace();
        }

    }
}
