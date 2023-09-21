package hibernate;

import hibernate.domain.Event;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EntityMangerDemo {

    static EntityManager em;
    public static void main(String[] args) {
        // Получаем фабрику менеджеров сущностей
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        // Из фабрики создаем EntityManager
        em = factory.createEntityManager();

        Event event = findById(2);
        System.out.println(event);

        Event newEvent = add(new Event("Rock Concert","Berlin"));
        //System.out.println(newEvent);
        //event.setCity("Praga");
        //event = update(event);
        //System.out.println(event);

        //delete(event);
        System.out.println(findAll());
    }

    static List<Event> findAll(){
        return em.createQuery("FROM Event", Event.class).getResultList();
    }
    static Event findById(int id){
        return em.find(Event.class, id);
    }
    static Event add(Event event) {
        // Открываем транзакцию
        em.getTransaction().begin();
        // Create (сохраняем в базе данных, и благодаря этому сущность
        // становится управляемой Hibernate и заносится в контекст постоянства)
        em.persist(event);
        // Подтверждаем транзакцию
        em.getTransaction().commit();
        return event;
    }

    static Event update(Event event) {
        em.getTransaction().begin();
        em.merge(event);
        em.getTransaction().commit();

        return event;
    }

    static void delete(Event event) {
        em.getTransaction().begin();
        em.remove(event);
        em.getTransaction().commit();

    }
}
