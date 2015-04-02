package com.team1.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.InvalidKeyException;
import java.util.LinkedList;

import com.team1.authentication.Authentication;
import com.team1.formatting.queries.Query;

/**
 * Represents a TCP server. This class will listen for connections on a given
 * port. Once a client connects to this port, the server will create a new
 * thread to handle the client's requests.
 * 
 * @author Alex Anderson
 */

public class TCPServer implements Runnable
{
    protected static boolean isAuthorized(Query query)
    {
        synchronized(authen)
        {
            return authen.authenticate(query) != 0;
        }
    }
    
    public TCPServer(int port) throws IOException
    {
        ss = new ServerSocket(port);
    }

    @Override
    public void run()
    {
        try
        {
            ss.setSoTimeout(1); // wait for clients for 1ms

            // keep looking for clients until this thread is interrupted
            while(true)
            {
                try
                {
                    Socket client = ss.accept();
                    Thread t = new ResponseThread(client, this);
                    t.start();
                    clients.add(t);
                    System.out.println("Spawned client thread");
                }
                catch(SocketTimeoutException e)
                {
                    // No connections have occurred in the last 1ms
                }
                catch(InvalidKeyException | InterruptedException e)
                {
                    // TODO: There isn't much to do for this except place it in a log file
                }

                try
                {
                    Thread.sleep(10); // sleep for 10ms
                }
                catch(InterruptedException e)
                {
                    break; // Time to stop listening for clients
                }

                // remove any old threads which were handling clients
                cleanClientThreads();
            }

            // clean up my mess
            ss.close();
            stopClientThreads();
        }
        catch(IOException e)
        {
            // Something really bad must have happened...
            e.printStackTrace();
        }
    }

    // called by client threads after they have finished
    protected void requestClientRemoval(Thread client)
    {
        synchronized(remCli)
        {
            remCli.add(client);
        }
    }

    // removes old client threads from the server's list
    private void cleanClientThreads()
    {
        synchronized(remCli)
        {
            if(remCli.size() > 0)
            {
                for(Thread t : remCli)
                {
                    clients.remove(t);
                }
            }
        }
    }

    // interrupts all active client threads
    private void stopClientThreads()
    {
        cleanClientThreads();
        for(Thread thread : clients)
            thread.interrupt();
    }

    private ServerSocket ss;
    private LinkedList<Thread> clients = new LinkedList<>();
    private LinkedList<Thread> remCli = new LinkedList<>();
    
    private static Authentication authen = new Authentication(null);
}
