/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author PC
 */
public class TCPServer {

    static final int PORT = 1234;
    private ServerSocket server = null;
    
    public TCPServer(){
        try {
            System.out.println("starting");
            server = new ServerSocket(PORT);
            
        } catch (Exception e) {
        }
    }
    public void turnoff(){
        try {
            server.close();
        } catch (Exception e) {
        }
    }
    
    public void turnOn(){
        new TCPServer().action();
    }
    public void action(){
        Socket socket = null;
        int i = 0;
        System.out.println("Serverlistenning.....");
        try {
            while((socket = server.accept()) != null){
                System.out.println("HEREEEEEEEE");
                new TCPServerThread(socket, "Client#" +i);
                System.out.println("Thread for Client generating...."+i++);
            }
        } catch (Exception e) {
            System.out.println("loi:" + e.toString());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TCPServer().action();
    }
    
}
