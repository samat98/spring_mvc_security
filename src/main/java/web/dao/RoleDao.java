package web.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDao {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    public List<Role> getRolesList() {
        Session session = factoryBean.getObject().getCurrentSession();

        return session.createQuery("from Role").getResultList();
    }

    public Role getRoleById(Long id) {
        Session session = factoryBean.getObject().getCurrentSession();
        return session.find(Role.class, id);
    }
}
