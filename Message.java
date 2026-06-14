 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rc;
 
import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException;
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
   //part 3: five parallel arrays
   public static String[]sentMessages = new String[100];
   public static String[] disregardedMessage = new String[100];
   public static String[]storedMessage = new String[100];
   public static String[]messageHash = new String[100];
   public static String[]messageID_list = new String[100];
   public static String[] allRecipients = new String[100];
   public static String[] allMessageTexts= new String[100];
   public static int sentCount= 0;
      public static int disregradedCount = 0;
              public static int StoredCount = 0;
         public static int totalCount  = 0; 
         
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
       messageHash[totalCount]= createMessageHash();
       messageID_list[totalCount]= messageID;
       allRecipients[totalCount]= recipient;
       allMessageTexts[totalCount]= messageText;
       totalCount++;
       
       if (choice == 1) {
            totalMessages++;
            sentMessages[sentCount]= messageText;
           sentCount++;
           return "Message successfully sent.";
       }
       else if (choice ==2){
           storedMessage[StoredCount]= messageText;
           StoredCount++;
           
           return "Press 0 to delete the messages.";
       }
       else {
           disregardedMessage[disregradedCount]= messageText;
           disregradedCount++;
           return "Message discarded.";
       }
       
   }
   public String printMessages(){
       return "Message ID: " + messageID
       + "\nMessage Hash:" + createMessageHash()
       +"\nRecipient:"+ recipient
       + "\nMessage:" + messageText;
   }
   public static int returnTotalMessages(){
       return totalMessages;
   }
    /**
            * Reads JSON file and loads stored messages into arrays
            * Uses BufferedReader to read the file line by line
            * @param filename the name of JSON  file
            */
   public static void readJSONFILE(String filename){
       try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          
          String line="";
          String currentID= "";
          String currentRecipient="";
          String currentMessage="";
          String currentHash="";
          
          while ((line = reader.readLine())!= null){
              line = line.trim();
              if(line.contains("\"messageID\"")){
                  currentID = line.split(":")[1].trim()
                  .replace("\"","")
                  .replace(",","");
              }
              if(line.contains("\recipient\"")) {
       currentRecipient = line.split(":")[1].trim()
               .replace("\"","")
               .replace(",","");
              }
              if(line.contains("\"messageText\"")){
                      currentMessage = line.substring(line.indexOf(":")+1).trim()
                  .replace("\"","")
                  .replace(",","");
              }
              if(line.contains("\"messageHash\"")){
                      currentHash = line.substring(line.indexOf(":")+1).trim()
                  .replace("\"","")
                  .replace(",","");
          }
               if(line.contains("}")){
                   if(!currentMessage.isEmpty()){
       storedMessage[StoredCount] = currentMessage; 
       messageHash[totalCount]= currentHash;
       messageID_list[totalCount]= currentID;
       allRecipients[totalCount]= currentRecipient;
       allMessageTexts[totalCount]= currentMessage;
       totalCount++;
       currentID= "";
       currentRecipient="";
       currentMessage="";
       currentHash="";
                   }
               }
          }
          }
           
      
        catch (Exception e){
           System.out.println("Could not read JSON file:"+ e.getMessage());
       }
   }
       /**
  * Display sender and recipient of all stored messages
  * @return formattedString of all stored messages
  */
   public static String displaySenderRecipient(){
       if (StoredCount==0){
           return "No stored message found.";
       }
       StringBuilder sb = new StringBuilder();
       for(int i = 0; i < totalCount; i++){
       for (int j = 0;j <StoredCount;j++){
           if(allMessageTexts[i].equals(storedMessage[j])){
               sb.append("Recipient:").append("allRecipients[i]")
              .append("|  Message:").append(allMessageTexts[i])
              .append("|n");
           }
       }
       
   }
       return sb.toString().trim();
   }
   // Display the longest stored message
   public static String displayLongestMessage(){
       if (StoredCount==0){
           return "No stored messages found.";
       }
       StringBuilder sb = new StringBuilder()  ;
       for (int i = 0; i < totalCount;i++){
       for (int j = 0; j< StoredCount;j++){
           if(allMessageTexts[i] != null && storedMessage[j] != null
                   && allMessageTexts[i].equals(storedMessage[j])){
              sb.append("Recipient:").append(allRecipients[i])
              .append("| Message:").append(allMessageTexts[i])
              .append("\n");
           } 
       }
   }
        return sb.toString().trim(); 
       
   }
  public static String searchByMessageID(String id) {
      for(int i = 0 ; i < totalCount; i++) {
          if(messageID_list[i] != null && messageID_list[i].equals(id)) {
          return "Recipient:"+ allRecipients[i] + "\nMessge:"+ allMessageTexts[i];
      }
          
      }
      return "Message not found";
  }
  public static String searchByRecipient(String rec){
      StringBuilder sb = new StringBuilder(); 
      for (int i = 0; i < totalCount; i++) {
       if (allRecipients[i] != null && allRecipients[i].equals(rec)){
          sb.append(allMessageTexts[i]).append("\n");
      }
       if (sb.length()==0){
           return "No messages found for recipient:" + rec;
       }
      }
      return sb.toString().trim();
  }
  public static String deleteByHash(String hash) {
      for (int i = 0; i < totalCount; i++) {
          if (messageHash[i] != null && messageHash[i].equals(hash)){
              String deleted = allMessageTexts[i];
       messageHash[i] = null;
       messageID_list[i]= null;
       allRecipients[i]= null;
       allMessageTexts[i]= null;
       return "Message: \"" + deleted + "\" successfully deleted.";
              
          }
      }
      return "Hash not found. No message deleted.";
  }
  public static String displayReport() {
  if ( totalCount == 0)  {
      return " Message: Recipient: Message:";
  } 
  StringBuilder sb = new StringBuilder();
  for (int i = 0; i < totalCount; i++) {
      if (messageHash[i] != null){
  
  sb.append("----Message Hash:").append( messageHash[i]).append("\n");
  sb.append("----Recipient:").append( allRecipients[i]).append("\n");
  sb.append("----Message:").append( allMessageTexts[i]).append("\n");
  sb.append("------------------------\n");
  }
      
  } 
  return sb.toString().trim();
}
  public String getMessageID(){ return messageID;}
  public String getRecipient(){ return recipient;}
  public String getMessageTexts(){ return messageText;}
}
