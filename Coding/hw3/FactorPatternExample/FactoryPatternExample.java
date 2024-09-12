public class FactoryPatternExample {

   public static void main(String[] args) {
      NotificationFactory factory = new NotificationFactory();

      // Get an object of SMS Notification and call its notify method
      Notification notification1 = factory.createNotification("SMS");
      notification1.notifyUser();

      // Get an object of Email Notification and call its notify method
      Notification notification2 = factory.createNotification("EMAIL");
      notification2.notifyUser();

   }
}
