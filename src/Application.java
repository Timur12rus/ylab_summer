import model.User;
import ui.MenuConsole;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class Application {
    public static void main(String[] args) {
        MenuConsole console = new MenuConsole();
        console.showMenu();

//        Map<String, User> userMap = new HashMap<>();
//        userMap.put("login1", new User("login1", "password", 10));
//        userMap.put("login2", new User("login2", "password", 10));
//        userMap.put("login3", new User("login3", "password", 10));
//        userMap.put("login4", new User("login4", "password", 10));
//
//        System.out.println(userMap.get("login1"));
//
//        userMap.put("login2", new User("log2", "pas2", 100));
//        System.out.println(userMap);
    }
}