import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from PurchaseList pl, Student s, Course c where c.name = pl.courseName and s.name = pl.studentName";

        Query query = session.createQuery(hql);

        List<Object[]> entityList = query.getResultList();

        for (Object[] list : entityList) {
            PurchaseList pl = (PurchaseList) list[0];
            Student s = (Student) list[1];
            Course c = (Course) list[2];


            LinkedPurchaseList lpl = new LinkedPurchaseList();
            lpl.setCourseId(c.getId());
            lpl.setStudentId(s.getId());
            lpl.setPrice(pl.getPrice());
            lpl.setSubscriptionDate(pl.getSubscriptionDate());

            session.persist(lpl);
        }

        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
