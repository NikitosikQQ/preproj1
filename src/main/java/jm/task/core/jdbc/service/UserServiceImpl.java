package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserServiceImpl implements UserService {

    // private final Connection connection;
    private final SessionFactory sessionFactory;

    public UserServiceImpl() {
        //  this.connection = getBaseConnectionJDBC();
        this.sessionFactory = createSessionFactory(getMySqlConfigurationHibernate());
    }

    public void createUsersTable() throws SQLException {
        // UserDao dao = new UserDaoJDBCImpl(connection);
        // dao.createUsersTable();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDao hiberDao = new UserDaoHibernateImpl(session);
        hiberDao.createUsersTable();
        transaction.commit();
        session.close();

    }

    public void dropUsersTable() throws SQLException {
        //   UserDao dao = new UserDaoJDBCImpl(connection);
        //  dao.dropUsersTable();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDao hiberDao = new UserDaoHibernateImpl(session);
        hiberDao.dropUsersTable();
        transaction.commit();
        session.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //    UserDao dao = new UserDaoJDBCImpl(connection);
        //   dao.saveUser(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDao hiberDao = new UserDaoHibernateImpl(session);
        hiberDao.saveUser(name, lastName, age);
        transaction.commit();
        session.close();
        System.out.println("User с именем - " + name + " добавлен в базу данных");

    }

    public void removeUserById(long id) throws SQLException {
        //  UserDao dao = new UserDaoJDBCImpl(connection);
        //  dao.removeUserById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDao hiberDao = new UserDaoHibernateImpl(session);
        hiberDao.removeUserById(id);
        transaction.commit();
        System.out.println("Пользователь под id = " + id + " удален");
        session.close();
    }

    public List<User> getAllUsers() throws SQLException {
        // UserDao dao = new UserDaoJDBCImpl(connection);
        //   return dao.getAllUsers();
        Session session = sessionFactory.openSession();
        UserDao hiberDao = new UserDaoHibernateImpl(session);
        List<User> users = hiberDao.getAllUsers();
        session.close();
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        //  UserDao dao = new UserDaoJDBCImpl(connection);
        //  dao.cleanUsersTable();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDao hiberDao = new UserDaoHibernateImpl(session);
        hiberDao.cleanUsersTable();
        transaction.commit();
        session.close();
    }
}
