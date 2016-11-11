package ru.stqa.course.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.course.mantis.model.UserData;
import ru.stqa.course.mantis.model.Users;

import java.util.List;

/**
 * Created by Оля on 30.10.2016.
 */
public class DbHelper {

  public final SessionFactory sessionFactory;

  public DbHelper(ApplicationManager app) {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where id > 1").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

}
