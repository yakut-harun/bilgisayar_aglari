import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Router3 {
	  
	   private static ServerSocket serverSocket; 
	   private static InetAddress host;
	   private static final int PORT = 1003; 
	   private static int PORT4 ;
	   
	   private static Socket link2 = null;
	   public static void main(String[] args)  
	   {  
		  System.out.println("Port açılıyor...");
	      {  
	          try  
	          {  
	              host = InetAddress.getLocalHost();
	          }  
	          catch(Exception uhEx)  
	          {  
	              System.out.println("Host ID not found!");  
	              System.exit(1);  
	          }  
	          
	           
	      }
	      try  
	      {  
	    	  Random r=new Random();
	          int a=r.nextInt(2);
	     		 
	     		 if (a==0) {
	     			 PORT4=1004;
                                 System.out.println("router4 bağlandı");
	     		 }
	     		 else if(a==1) {
	     			 PORT4=1005;
                                 System.out.println("router5 bağlandı");
	     		 }
	    	  serverSocket = new ServerSocket(PORT);
	         link2 = new Socket(host,PORT4);
	         
	      }  
	      catch(IOException ioEx)  
	      {  
	         System.out.println(  
	                         "Router Port'a BAĞLANMADI.");  
	         System.exit(1);  
	      }  
	    
	      handleClient();  
	      
	   }    
	   private static String handleClient()  
	   {  
	      Socket link = null;                        
	      String str2 = null;
	      try  
	      {  
	         link = serverSocket.accept();            
	         
	         Scanner input =  
	            new Scanner(link.getInputStream());   
	         PrintWriter output =  
	              new PrintWriter(  
	                 link.getOutputStream(),true);      
	         
	           String message = input.nextLine(); 
	           
	           
	           Scanner input2 =  
		                new Scanner(link2.getInputStream());      
	   
	         PrintWriter output2 =  
	            new PrintWriter(  
	               link2.getOutputStream(),true);       
	        
	         while (!message.equals("***CLOSE***")){  
	        	 System.out.println("message from sender "+message);
	        	 
	        	 
	        	 Random randomGenerator = new Random();
		            int randomInt = randomGenerator.nextInt(100);
		            System.out.println("Generated random number for the packet is: "+randomInt);
                            
		            if (randomInt>1){
		            	output2.println(message);
		            
	        	 String str=input2.nextLine();
	        	 System.out.println("message from receiver: "+str);
	        	 output.println(str);
		            }
		            else
		            {
		            	output.println(str2);
		            }
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
	                        "\n* Closing connections (Router side)*");  
	             link.close();  
	             link2.close();
	          }  
	          catch(IOException ioEx)  
	          {  
	              System.out.println(  
	                            "Unable to disconnect!");  
	            System.exit(1);  
	          }  
	       }
		return null;  
	   }  
}
