public class NotificationFactory {

   // Method to create notification objects based on the type
   public Notification createNotification(String channel) {
      if (channel == null || channel.isEmpty()) {
         return null;
      }
      if (channel.equalsIgnoreCase("SMS")) {
         return new SMSNotification();
      } else if (channel.equalsIgnoreCase("EMAIL")) {
         return new EmailNotification();
      }
      return null;
   }
}
