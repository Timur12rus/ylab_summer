import model.User;
import ui.MenuConsole;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class Application {
    public static void main(String[] args) {
        MenuConsole console = new MenuConsole();
        console.showMenu();
    }
}