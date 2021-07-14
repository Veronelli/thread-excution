/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author facu
 */
public class main {
    
    //Server custom settings
    private static final int LOCAL_UDP_PORT = 17584;
    private static DatagramSocket server = null;
    
    private static int counter = 0;//??
    
    public static void main(String [] args){
        
        //Configuring Thread 
        // https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html
        
        
        var threadPoolExecutor = new ThreadPoolExecutor(3/*Threads*/, 5, 0L, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(5)/*Array queue*/, new ThreadPoolExecutor.AbortPolicy());

        
        try{            
            server = new DatagramSocket(LOCAL_UDP_PORT);
            
            while(true){
                byte buffer[] = new byte[512];
                
                //Invoke DatagramPacket object
                var datagramPacket = new DatagramPacket(buffer, buffer.length);
                server.receive(datagramPacket);
                
                threadPoolExecutor.execute(new DataProcessor(server, datagramPacket));
                
            }
            
            
        }
        catch(Exception e){
            System.out.println("Overflow in queue");
            
        }
        
    }
}
