/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rc;
import java.util.Scanner;

/**
 *part 1 ,2 and 3 functionality
 * @author User
 */
public class Main {
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        System.out.println("Enter username:");
        String username = input.nextLine();
        
        System.out.println("Enter password:");
        String password = input.nextLine();
        
        boolean loginStatus = login.loginUser(username, password);
        if (loginStatus){
            System.out.println("\nHow many messages would you like to send?");
            int numberOfMessages = input.nextInt();
            input.nextLine();
            
            Message[] messages = new Message[numberOfMessages]; 
            for (int i = 0; i < numberOfMessages; i++){
                System.out.println("\nEnter message ID:");
                String messageID = input.nextLine();
                
                System.out.println("Enter recipient number:");
                String recipient = input.nextLine();
                
                System.out.println("Enter message:");
                String messageText = input.nextLine();
                if(messageText.length() > 250){
                   System.out.println("Message exceds 250 charaters by" +(messageText.length()-250));
                   i--;
                   continue;
                }
                Message message = new Message(messageID, i+ 1, recipient,messageText);
                if(!message.checkMeesageID()){
                    System.out.println("Message ID is incorrectly formatted.");
                    i--;
                    continue;
                }
                if (!message.checkRecipientCell()){
                    System.out.println("Cell phone number is incorrectly formatted");
                    i--;
                    continue;
                }
                System.out.println("\nChoose option:");
                System.out.println("1. Send Message");
                System.out.println("2.Store Message");
                System.out.println("3.Disregard Message");
                
                int choice = input.nextInt();
                input.nextLine();
                
                System.out.println(message.sentMessage(choice));
                messages[i] = message;
                System.out.println(message.printMessages());
                }
            System.out.println("\nTotal messages sent:" + Message.returnTotalMessages());
            
        
    // part 3 - Read Message MENU
    boolean running = true;
    while (running){
   System.out.println("\n====Choose option====");
   System.out.println("1. Display sender and recipient of all stored messages");
                System.out.println("2.Display the longest Stored Message");
                System.out.println("3.Search for message ID");
                System.out.println("4. Search for message by recipient");
                System.out.println("5. Delete a message using message Hash");
                System.out.println("6. Display full message report");
                System.out.println("0. Exit");
                System.out.println("Choose option:");   
    int menuChoice = Integer.parseInt(input.nextLine());
    
    
    switch (menuChoice){
        case 1 :
            System.out.println(Message.displaySenderRecipient());
            break;
        case 2:
            System.out.println(Message.displayLongestMessage());
            break;
        case 3 :
            System.out.println("Enter message ID to search:");
            String searchID = input.nextLine();
            System.out.println(Message.searchByMessageID(searchID));
            break;
        case 4:
            System.out.println("Enter recipient number to search:");
            String searchRec = input.nextLine();
            System.out.println(Message.searchByRecipient(searchRec));
            break;
        case 5:
            System.out.println("Enter message hash to delete:");
            String hashToDelete = input.nextLine();
            System.out.println(Message.deleteByHash(hashToDelete));
            break;
        case 6:
            System.out.println(Message.displayReport());
            break;
        case 0:
            running = false;
            System.out.println("Goodbye!");
            break;
        default:
            System.out.println("Invalid option. Please try again.");
    }
}
        } else {
          System.out.println("Username or password incorrect, please try again.");  
} 
        input.close();
 }
}
