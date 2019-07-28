// A Java program for a Server 
import java.net.*; 
import java.io.*; 

/*  
public class EchoMultiServer {
    private ServerSocket serverSocket;
 
    public void start(int port) {
        serverSocket = new ServerSocket(port);
        while (true)
            new EchoClientHandler(serverSocket.accept()).start();
    }
 
    public void stop() {
        serverSocket.close();
    }
 
    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
 
        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
 
        public void run() {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
              new InputStreamReader(clientSocket.getInputStream()));
             
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("bye");
                    break;
                }
                out.println(inputLine);
            }
 
            in.close();
            out.close();
            clientSocket.close();
    }
}
*/




public class Server 
{ 
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null;
    private DataOutputStream out 	 = 	null;	
    
    public void start(int port) {
        serverSocket = new ServerSocket(port);
	this.socket = serverSocket;
	System.out.println("Server Started");
    }



    // constructor with port 
    public Server(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream( new BufferedInputStream(socket.getInputStream())); 
			//out = new DataOutputStream( new BufferedOutputStream(socket.getOutputStream()));
	    out    = new DataOutputStream(socket.getOutputStream());
            String line = ""; 
  
            while (!line.equals("Over")) 
            { 
                try
                { 
                    line = in.readUTF(); 
		    if(line == null) { 
			System.out.println("GOT EOF, Closing COnnection");
		    	break;
		    } 
                    System.out.println(line); 
		    out.writeUTF(line); 
		    System.out.println("Server Sent!");
                }
		/* 
		catch(EOFException e)
		{
			System.out.println("EOF");
			break;
		}
		*/
                
		catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
		
            } 
            System.out.println("Closing connection"); 
  
            socket.close(); 
            in.close(); 
	    out.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  

    public static void main(String args[]) 
    { 
        Server server = new Server(5000); 
    } 
} 
