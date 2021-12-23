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
        System.out.println("hii1");
        this.socket = socket;
        this.name = name;
        this.in = new Scanner(this.socket.getInputStream());
        this.out = new PrintWriter(this.socket.getOutputStream(),true);
        new Thread(this).start();
    }
    
    public void run(){
        try {
            while(true){
                String chuoi = in.nextLine().trim();
//                if (chuoi== null){
//                    System.out.println("NULL CMNR");
//                }else{
//                    System.out.println("NOT NULL <3");
//                }
                Scanner sc1 = new Scanner(chuoi);
                sc1.useDelimiter("#");
                String clientKey = sc1.next();
                String clientEncryptedText = sc1.next();

               // if( ClientKey.equals("truyenchuoi")){
                      clientEncryptedText = clientEncryptedText.toUpperCase();
        //              System.out.println("chuoi" + ClientEncryptedText);
                      out.println("Key = "+ clientKey+ " Text = " +clientEncryptedText);
//                }
//                
//                else{
//                    Scanner scanner2 = new Scanner(ClientEncryptedText);
//                     scanner2.useDelimiter("@");
//                     int so1 = scanner2.nextInt();
//                     String pheptoan = scanner2.next();
//                     int so2 = scanner2.nextInt();
//                     
//                     
//                     
//                     if(pheptoan.equals("+"))
//                         out.println(so1 + so2);
//                     else if(pheptoan.equals("-"))
//                         out.println(so1 - so2);
//                     else if(pheptoan.equals("*"))
//                         out.println(so1 * so2);
//                     else if(pheptoan.equals("/"))
//                         out.println((float) so1/so2 + "");
//                   
//                 
//                }
//                
                        
                        
                        
                        
                
              
                
                
            }
        } catch (Exception e) {
            System.out.println("LOIIII2" + e.toString());
        }finally{
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }
}
