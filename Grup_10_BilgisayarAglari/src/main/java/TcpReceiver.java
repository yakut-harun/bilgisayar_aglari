import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class TcpReceiver {
	private static ServerSocket serverSocket;  
	   private static final int PORT = 1009; 
	   
	   public static void main(String[] args)  
	   {  
	      System.out.println("port acılıyor...");  
	      try  
	      {  
	         serverSocket = new ServerSocket(PORT); 
	      }  
	      catch(IOException ioEx)  
	      {  
	         System.out.println(  
	                         "Unable to attach to port for receiver!");  
	         System.exit(1);  
	      }  
	     
	         handleRouter();  
	       
	   }    
	   private static void handleRouter()  
	   {  
	      Socket link = null;                          
	      try  
	      {  
	         link = serverSocket.accept();            
	         Scanner input =  
	            new Scanner(link.getInputStream()); 
	         PrintWriter output =  
	              new PrintWriter(  
	                 link.getOutputStream(),true);      
	         int numMessages = 0;  
	           String message = input.nextLine();
	           
	         while (!message.equals("***CLOSE***")) 
	         
	         {  
	        	 output.println("ACK"+numMessages);
	            numMessages++; 
	            System.out.println(numMessages + ":" + message);
                     
	            message = input.nextLine();
	            
	           
	         
	         }  
	        
	       }  

	       catch(IOException ioEx)  
	       {  
	           ioEx.printStackTrace();  
	       }    
	       finally  
	       {  
	          try  
	          {  
	             System.out.println(  
	                        "\n* Closing connections (Receiver side)*");  
	             link.close();                      
	          }  
	          catch(IOException ioEx)  
	          {  
	              System.out.println(  
	                            "Unable to disconnect!");  
	            System.exit(1);  
	          }  
	       } 
	   }  

}
