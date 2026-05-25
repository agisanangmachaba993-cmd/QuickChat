/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rc;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Main {
    // step:1 Main entry point for Quickchat application
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        System.out.println("Enter username:");
        String username = input.nextLine();
        
        System.out.println("Enter password:");
        String password = input.nextLine();
        
        boolean loginStatus = login.loginUser(username, password);
        if (loginStatus){
            // step 2: Check login status before sending messages
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
                    // step 3: validate the message length does not exceed 250 characters
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
                    // step 4:Check if the recipent cell phone number is valid
                    System.out.println("Cell phone number is incorrectly formatted");
                    i--;
                    continue;
                }
                System.out.println("\nChoose option:");
                System.out.println("1. Send Message");
                System.out.println("2.Store Message");
                System.out.println("3.Disregard Message");
                
                int choice = input.nextInt();
                // step 5: capture user menu choice for message handling
                input.nextLine();
                
                System.out.println(message.sentMessage(choice));
                messages[i] = message;
                System.out.println(message.printMessages());
                }
            System.out.println("\nTotal messages sent:" + Message.returnTotalMessages());
            }
        }
}  
