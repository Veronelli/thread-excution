/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author facu
 */
public class DataProcessor implements Runnable {

    //\\Variables
    private DatagramSocket server;
    private DatagramPacket datagramPacket;
    private static int counter;
    
    //Contructor
    public DataProcessor(DatagramSocket server, DatagramPacket datagramPacket) {
        super();
        this.server = server;
        this.datagramPacket = datagramPacket;
        
    }
    
    //Method 
    public void run(){
        try{
            //Get informaction
            String input = new String(datagramPacket.getData()).trim();
            System.out.println(counter + ": " + input);
            
            counter++;
            
            Thread.sleep(10000);
            
            //Prepare to send in a packet
            byte[] confirmacion = "Confirmacion!".getBytes();
            DatagramPacket packet = new DatagramPacket(confirmacion, confirmacion.length, datagramPacket.getSocketAddress());
            
            //Send packet
            server.send(packet);
            
        }catch(Exception e){
            System.out.println("Overflow in array memory");
            
        }
        
    }
    
}
