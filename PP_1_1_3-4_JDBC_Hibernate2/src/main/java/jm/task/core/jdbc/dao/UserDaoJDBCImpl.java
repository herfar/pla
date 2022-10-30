package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() throws SQLException {
        try (Statement s = Util.getConnection().createStatement()) {
            s.execute("create table if not exists Users (id INT PRIMARY KEY AUTO_INCREMENT, name varchar(30), lastName varchar(30), age int)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() throws SQLException {
        try (Statement s = Util.getConnection().createStatement()) {
            s.execute("drop table if exists Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("insert into Users values (1, ?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement s = Util.getConnection().createStatement()) {
            s.execute("delete from Users where id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from Users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
