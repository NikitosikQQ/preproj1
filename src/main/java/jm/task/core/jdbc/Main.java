package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nikita", "Akimov", (byte) 22);
        userService.saveUser("Alex", "Markin", (byte) 20);
        userService.saveUser("Artem", "Spichkin", (byte) 17);
        userService.saveUser("Maria", "Sidorova", (byte) 20);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
