// A Java program for a Client 
import java.net.*; 
import java.io.*; 
  
public class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null;
   // private DataInputStream  inputfromserver = null;
   //private DataOutputStream out     = null; 
    private PrintWriter out;
    private BufferedReader in;
  
    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new PrintWriter(socket.getOutputStream()); 
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = ""; 
	String serverinput="";
        // keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
            try
            { 
                line = input.readLine(); 
		if(line == null)
		{	
			System.out.println("Got EOF, Closing COnnection");
			break;
		}
                out.println(line);
		System.out.println(in.readLine());
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 5000); 
    } 
} 
