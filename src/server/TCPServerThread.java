/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author PC



import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class TCPServerThread implements Runnable{
    private Scanner in = null;
    private PrintWriter out = null;
    private Socket socket;
    private String name;
    
    public TCPServerThread (Socket socket, String name) throws IOException{
        System.out.println("hiiiiii");
        this.socket = socket;
        this.name = name;
        this.in = new Scanner(this.socket.getInputStream());
        this.out = new PrintWriter(this.socket.getOutputStream(),true);
        new Thread(this).start();
    }
    
    public void run(){
        try {
            while(true){
                System.out.println("ON RUNGINGGGG.....");

                String chuoi = in.nextLine().trim();
//                if (chuoi== null){
//                    System.out.println("NULL CMNR");
//                }else{
//                    System.out.println("NOT NULL <3");
//                }
                Scanner sc1 = new Scanner(chuoi);
                sc1.useDelimiter("#");
                int clientKey = sc1.nextInt();
                String clientEncryptedText = sc1.next();
                
                
                int sodong = clientEncryptedText.length()/clientKey;
                int socot = clientKey;
                
                String[] tempMetrix = new String[socot];//= clientEncryptedText.split(" ");   
                String rs= "";
                
               System.out.println("socot va sodong: " + socot + " / " + sodong );
               
               
               
                for(int i = socot  -1 ; i >= 0 ; i--){
                    System.out.println("can reach here " + i);
                        tempMetrix[i] = clientEncryptedText.substring((sodong*(i)),(sodong*(i+1)));



                    System.out.println("dong " + i + " = "+ tempMetrix[i]);
                    String temp1 = tempMetrix[i]; 
                    String temp2 = rs;
                    rs = temp1 + " " + temp2;
                }/// Trans to Metrix[]
                
            
            rs = "";
            for(int i = 0 ; i <  sodong; i ++){
                for(int j = 0 ; j <  socot; j ++){
                    System.out.print( tempMetrix[j].charAt(i) +"  " ); //+ "  "
                    rs += tempMetrix[j].charAt(i);
                }
                System.out.println("");
            } //FOR CONVERT INPUT
            rs = rs.replace("@", "");
            
                
                
                
                
                clientEncryptedText = clientEncryptedText.toUpperCase();

                out.println("Key = "+ clientKey+ " Text = " +clientEncryptedText  + " / rs = "+ rs ); //+" rslength = " + rs.length()

                      
                        
                        
                        
                        
                
              
                
                
            }
        } catch (Exception e) {
            System.out.println("LOIIII2" + e.toString());
        }finally{
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("CANT NOT CLOSE SOCKET");
            }
        }
    }
}
