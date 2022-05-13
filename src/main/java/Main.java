import BusinessLayer.DeliveryService;
import BusinessLayer.UserBLL;
import PresentationLayer.Controller;
import PresentationLayer.Login;
import PresentationLayer.MessagePopUp;

public class Main {
    public static void main(String[] args) {
        UserBLL userBLL = new UserBLL();
        Login login = new Login();
        MessagePopUp message = new MessagePopUp();
        DeliveryService deliveryService = new DeliveryService();
        Controller controller = new Controller(userBLL, login, message, deliveryService);
    }
}
