package lesson6_hw;

import java.sql.SQLException;

public class UserDAODemo {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAO(MyDataSource.getDataSource());
        User user =userDAO.findById(1);
        System.out.println(user);
        User userByName = userDAO.findByName("Lena");
        System.out.println(userByName);
    }
}
