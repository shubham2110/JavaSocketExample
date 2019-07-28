// A Java program for a Server 
import java.net.*; 
import java.io.*; 

public class Server 
{ 
	private Socket          socket   = null; 
	private ServerSocket    server   = null; 
	private DataInputStream in       =  null;
	private DataOutputStream out 	 = 	null;	
	
	public void start(int port) 
	{
	   try
	   {
		server = new ServerSocket(port);
		System.out.println("Server Started");
		while (true)
		{
			// Handle each client request
			new ClientHandler(server.accept()).start();

		}
	   }
	   catch(Exception e)
	   {
		System.out.println("Exception while Creating New Socket");
	   }
	}
    


	public void stop() 
	{
	   try{
        	server.close();
	   }
	   catch(Exception e)
	   {
		System.out.println("Exception while Creating New Socket");
		
	   }
    	}

   	public Server(int port)
	{
		this.start(port);

	}


	// One class just for STDOUT thread 
	
	


	// One class just for STDIN thread


	// One class for hnadling clients
	private static class ClientHandler extends Thread 
	{
        	private Socket clientSocket;
        	private PrintWriter out;
        	private BufferedReader in;
 
        	public ClientHandler(Socket socket) 
		{
            		this.clientSocket = socket;
        	}
 
        	public void run() 
		{
			try{
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
            		String inputLine;
				//System.out.println("helo");
			
            		while ((inputLine = in.readLine()) != null) 
			{
                		if (".".equals(inputLine)) 
				{
                    		out.println("bye");
                    		break;
                		}
                		out.println(inputLine);
            		}
 
            		in.close();
            		out.close();
            		clientSocket.close();
            		

			/*
			while (!inputLine.equals("Over")) 
            		{ 
				try
				{ 
                    			inputLine = in.readUTF(); 
		    			if(inputLine == null) { 
						System.out.println("GOT EOF, Closing COnnection");
		    				break;
		    			} 
                			if ("Over".equals(inputLine)) 
					{
                    				System.out.println("Got Closing Signal, CLosing Connection");
                    				break;
                			}
					System.out.println(inputLine); 
		    			//out.writeUTF(line);
					out.writeUTF(inputLine);
                		} 
				catch(EOFException e)
				{
					System.out.println("EOF");
					break;
				}
				catch(IOException i) 
                		{ 
                    			System.out.println(i); 
                		} 
		
			} 
            		
			System.out.println("Closing connection"); 
            		
			in.close();
            		out.close();
            		clientSocket.close();
			*/

			} // FOr IO Exception
			catch(IOException i) 
                	{ 
                    		System.out.println(i); 
                	} 
		
  
		}
	
	}

   


    public static void main(String args[]) 
    { 
        Server server = new Server(5000); 

    } 
} 
