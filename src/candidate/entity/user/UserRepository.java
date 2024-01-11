package candidate.entity.user;


import candidate.entity.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserTableCallbacks {
    private Connection connection;

    @Override
    public boolean createUserTable() {
        connection = DBConnection.makeConnection();
        boolean status;
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query =
                    "CREATE TABLE IF NOT EXISTS users " +
                            "(id int(11) NOT NULL AUTO_INCREMENT," +
                            "firstname varchar(255), " +
                            "lastname varchar(255), " +
                            "username varchar(255), " +
                            "password varchar(255), " +
                            "created_date TIMESTAMP default CURRENT_TIMESTAMP NOT NULL," +
                            "PRIMARY KEY(id), " +
                            "UNIQUE (password))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            status = true;
        } catch (SQLException e) {
            status = false;
            System.out.println("Exception: " + e.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public User insertUser(User user) {
        connection = DBConnection.makeConnection();
        User insertedUser = null;
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "INSERT INTO users(firstname,lastname,username,password) " +
                    "VALUES(?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                query = "SELECT * FROM users WHERE id='" + id + "'";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                insertedUser = new User();
                while (resultSet.next()) {
                    insertedUser.setId(resultSet.getInt(1));
                    insertedUser.setFirstname(resultSet.getString(2));
                    insertedUser.setLastname(resultSet.getString(3));
                    insertedUser.setUsername(resultSet.getString(4));
                    insertedUser.setPassword(resultSet.getString(5));
                    insertedUser.setCreatedDateTime(resultSet.getTimestamp(6).toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return insertedUser;
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "UPDATE users SET firstname='" + user.getFirstname() +
                    "',lastname='" + user.getLastname() +
                    "',username='" + user.getUsername() +
                    "',password='" + user.getPassword() +
                    "' WHERE id='" + user.getId() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            updatedUser = user;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return updatedUser;
    }

    @Override
    public boolean deleteUser(Integer id) {
        connection = DBConnection.makeConnection();
        boolean status = false;
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "DELETE FROM users WHERE id='" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            status = preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public User getUser(Integer id) {
        User user = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "SELECT * FROM users WHERE id='" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime());
                list.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public boolean checkUser(String username, String password) {
        boolean status;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            status = false;
            System.out.println(e.getLocalizedMessage());
        }
        return status;
    }
}
