package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.createSessionFactory;
import static jm.task.core.jdbc.util.Util.getMySqlConfigurationHibernate;

public class UserDaoHibernateImpl implements UserDao {
    private Session session;

    public UserDaoHibernateImpl() {
    }

    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }


    @Override
    public void createUsersTable() {
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS user_table (id INT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(255) NOT NULL," +
                " last_name VARCHAR(45) NOT NULL," +
                " age INT NOT NULL," +
                " PRIMARY KEY (id ));").executeUpdate();
    }

    @Override
    public void dropUsersTable() {
        try {
            session.createSQLQuery("DROP TABLE user_table;").executeUpdate();
        } catch (HibernateException e) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.save(new User(name, lastName, age));
    }

    @Override
    public void removeUserById(long id) {
        session.delete(session.load(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = session.createCriteria(User.class).list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session.createQuery("DELETE FROM user_table").executeUpdate();

    }
}
