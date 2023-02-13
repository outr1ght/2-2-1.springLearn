package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("Audi", 1);
      user1.setCarInfo(car1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car car2 = new Car("Mercedez", 2);
      user2.setCarInfo(car2);


      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCarInfo());
         System.out.println();
      }


      List<User> users1 = new ArrayList<>();
      users1.add(userService.getUserByCarModelAndSeries(car2));
      System.out.println(users1);

      context.close();
   }
}
