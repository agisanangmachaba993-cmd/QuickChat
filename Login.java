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
public class Login {
    // global variables
    
    String Username;
    String password;
    String cellphonenumber;
    //check Username validation
    
    public boolean checkUsername(String Username){
        if (Username== null){
        return false;
        }
        return Username.contains("_")&& Username.length() <=5;
    }
    
    // check password
    public boolean checkPasswordComplexity(String password){
        if (password==null){
            return false;
        
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        
        for (char c : password.toCharArray()){
            if (Character.isUpperCase(c)){
                hasCapital =true;
            }
            if (Character.isDigit(c)){
                hasNumber = true;
                
            }
            
        }
        return hasCapital && hasNumber && password.length() >=8;
    }
    
    // check cellphone number
    
    public boolean checkCellPhoneNumber(String cellphonenumber){
        if (cellphonenumber==null){
            return false;
        }
        return cellphonenumber.startsWith("+")&& cellphonenumber.length() >= 10;
    }
    
    // Register user
   public String registerUser(String Username,String password,String cellphonenumber){
       if (!checkUsername(Username)){
           return "Username is not correctly formatted,please ensure that your Username contains an undercore and is no more than 5 characters in length.";
       }
       if (!checkPasswordComplexity(password)){
           return "Password is not correctly formatted,please ensure that the password contains at least 8 charaters, a capital letter and a number.";
           
   }
       
       if (!checkCellPhoneNumber(cellphonenumber)){
           return "Cell phone number inncorrectly formatted or does not contain international code.";
       }
       this.Username= Username;
       this.password= password;
       this.cellphonenumber= cellphonenumber;
       
       return "User is registered successfully";
   } 
   
   // login check
   public boolean loginUser(String Username,String password){
       if (this.Username==null || this.password==null){
           return false;
       }
       return this.Username.equals(Username) && this.password.equals(password);
       
   }
   
   // login message validatin
   public String returnLoginStatus(boolean status){
       if (status){
           return "Welcome"+ this.Username + "it is great to see you again.";
       } else {
           return "Username or password incorrect, please try again.";
       }
   
   }
}



class Main {
    public static void main(String[] arg){
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        // ARRAY input
        String[] userDetails = new String[3];
        
        System.out.println("Welcome to Quickchat.");
        
        // ======= REGISTER =======
        System.out.println("Enter Username:");
        userDetails[0]= input.nextLine();
        
        System.out.println("Enter password");
        userDetails[1]= input.nextLine();
        
        System.out.println("Enter cellphonenumber");
        userDetails[2]= input.nextLine();
        
        String message = login.registerUser(userDetails[0],userDetails[1],userDetails[2]);
        
        System.out.println(message);
        
        // ======= LOGIN=======
        System.out.println("\nLogin now:");
        
        System.out.println("Enter Username:");
        String loginUsername =input.nextLine();
        
        System.out.println("password:");
        String loginpassword = input.nextLine();
        
        boolean status = login.loginUser(loginUsername, loginpassword);
    }
    
    
}
