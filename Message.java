/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rc;

/**
 *
 * @author User
 */
public class Message {
    
    private String messageID;
    private int messageNumber;
   private String recipient; 
   private  String messageText;
   
   static int totalMessages = 0;
   
   public Message(String messageID,int messageNumber,String recipient,String messageText) {
       
       this.messageID = messageID;
       this.messageNumber = messageNumber;
       this.recipient = recipient;
       this.messageText = messageText;
   }
   
   public boolean checkMeesageID(){
       return messageID.length() <= 10;
   }
   public boolean checkRecipientCell() {
       return recipient.startsWith("+27")
       && recipient.length()==12;
   }
   public String createMessageHash(){
       String[] words = messageText.split(" ");
       String firstWord = words[0];
       String lastWord = words[words.length-1];
       lastWord = lastWord.replace("?", "");
       return messageID.substring(0, 2)
       + ":"
       + messageNumber 
       + ":" 
       + firstWord.toUpperCase() 
       + lastWord.toUpperCase();
   }
   public String sentMessage(int choice) {
       if (choice == 1) {
           totalMessages++;
           return "Message successfully sent.";
       }
       else if (choice ==2){
           return "Press 0 to delete the messages.";
       }
       else {
           // Handle all invalid pr discribed choices
           return "Message discarded.";
       }
/** Formats and outputs the message dara. */ 
       // Combine thr ID and message hash 
   }
    // combines the recipient and text body
   public String printMessages(){
       return "Message ID: " + messageID
           /** Returns the total global message count. */
       + "\nMessage Hash:" + createMessageHash()
       +"\nRecipient:"+ recipient
       + "\nMessage:" + messageText;
   }
   public static int returnTotalMessages(){
       return totalMessages;
   }
    
}
