package web.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private LocalSessionFactoryBean sessionFactoryBean;
    private Session session;

    @Autowired
    public void setSession(LocalSessionFactoryBean sessionFactoryBean) {
        this.sessionFactoryBean = sessionFactoryBean;
    }

    @Override
    public void saveUser(User user) {
        session = sessionFactoryBean.getObject().getCurrentSession();
        session.save(user);
    }

    @Override
    public void updateUser(User user) {
        session = sessionFactoryBean.getObject().getCurrentSession();
        session.save(user);
    }

    @Override
    public User findUser(Long id) {
        session = sessionFactoryBean.getObject().getCurrentSession();
        return session.find(User.class, id);
    }

    @Override
    public User findUserByName(String name) {
        session = sessionFactoryBean.getObject().getCurrentSession();
        TypedQuery<User> query = session.createQuery("FROM User u where u.username = :name");
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        session = sessionFactoryBean.getObject().getCurrentSession();
        return (List<User>) session.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        session = sessionFactoryBean.getObject().getCurrentSession();
        User user = session.find(User.class, id);
        session.remove(user);
    }
}
