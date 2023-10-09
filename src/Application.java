import service.UserService;

public class Application {
    public static void main(String[] args) {
        UserService service = new UserService();
        service.start();
    }
}