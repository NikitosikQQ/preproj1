package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.executor.Executor;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getBaseConnectionJDBC;

public class UserDaoJDBCImpl implements UserDao {

    private Executor executor;
    private final Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = getBaseConnectionJDBC();
        this.executor = new Executor(connection);
    }


    public void createUsersTable() throws SQLException {
        executor.execUpdate("CREATE TABLE IF NOT EXISTS user_table (id INT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(255) NOT NULL," +
                " last_name VARCHAR(255) NOT NULL," +
                " age INT NOT NULL," +
                " PRIMARY KEY (id ));");

    }

    public void dropUsersTable() throws SQLException {
        try {
            executor.execUpdate("drop table user_table;");
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        executor.execUpdate("insert into user_table (name, last_name, age) values ('" + name + "', " +
                "'" + lastName + "', " +
                "'" + age + "');");
    }

    public void removeUserById(long id) throws SQLException {
        executor.execUpdate("DELETE FROM user_table WHERE id = " + id + ";");
    }

    public List<User> getAllUsers() throws SQLException {
        return executor.execQuery("SELECT * FROM user_table", result -> {
            List<User> allUsers = new ArrayList<>();
            while (result.next()) {
                User user = new User(result.getString(2), result.getString(3), result.getByte(4));
                user.setId(result.getLong(1));
                allUsers.add(user);
            }
            return allUsers;
        });
    }

    public void cleanUsersTable() throws SQLException {
        executor.execUpdate("DELETE FROM user_table;");
    }
}
