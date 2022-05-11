import BusinessLayer.DeliveryService;
import BusinessLayer.UserBLL;
import PresentationLayer.Controller;
import PresentationLayer.Login;

public class Main {
    public static void main(String[] args) {
        UserBLL userBLL = new UserBLL();
        Login login = new Login();
        DeliveryService deliveryService = new DeliveryService();
        Controller controller = new Controller(userBLL, login, deliveryService);
    }
}
