package lesson6_hw.spring;
import lesson6_hw.User;
import lesson6_hw.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDAOSpringDemo {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDAO userDAO = context.getBean(UserDAO.class);
        User user = userDAO.findById(1);
        System.out.println("Find by id: " + user);

        User userByName = userDAO.findByName("Lena");
        System.out.println("Find by name: " + userByName);

        User newUser = new User(null, "Dima", "Smith", "sm123");
        userDAO.add(newUser);
        System.out.println("User added");

        User updatedUser = new User(1, "Lena", "Sidan", "123gg");
        userDAO.update(updatedUser);
        System.out.println("User updated");

        userDAO.deleteById(1);
        System.out.println("User deleted");


    }
}
