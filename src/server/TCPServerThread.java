/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    
    
    private void getMetrix(String[] metrix, int socot, int sodong,String clientEncryptedText){
        for(int i = socot  -1 ; i >= 0 ; i--){
 //                  System.out.println("can reach here " + i);
                        metrix[i] = clientEncryptedText.substring((sodong*(i)),(sodong*(i+1)));

//                    System.out.println("dong " + i + " = "+ metrix[i]);
//                    String temp1 = metrix[i]; 
//                    String temp2 = rs;
//                    rs = temp1 + " " + temp2;
                }/// Trans to Metrix[]
    }
    
    
    private int checkOriginal(  String[] keyList ){
         for(int i = 1 ; i < keyList.length ; i ++){
                    if( Integer.parseInt(keyList[i]) <= Integer.parseInt(keyList[i-1])){
                        return 1;  
                    }
                }/// kiem tra co phai nguyen ban khong
         return 0;
    }
    
    private String[] convertAdvanced(String[] metrix, int socot, String[] keyList ){
        String[] tempMetrix = new String[socot]; 

        for(int i = 0 ; i < metrix.length ; i++){
                        System.out.println("i = " + i +"/ temp = " + (Integer.parseInt(keyList[i])-1));
                        
                        tempMetrix[Integer.parseInt(keyList[i])-1] = metrix[i];
        }
        return tempMetrix;

        
    }
    public void run(){
        try {
            while(true){
                System.out.println("ON RUNGINGGGG.....");

                String rawInput = in.nextLine().trim();
                Scanner sc1 = new Scanner(rawInput);
                sc1.useDelimiter("#");
                
                String stringKey = sc1.next();
                int clientKey = stringKey.length() - stringKey.replace(",", "").length() +1;
                System.out.println("KEY = " +clientKey + " / " + stringKey );
                String clientEncryptedText = sc1.next();
                
                int sodong = clientEncryptedText.length()/clientKey;
                int socot = clientKey;
                
                String[] metrix = new String[socot];

                String rs= "";
                

                List listCharacter = new ArrayList();
                 List<Integer> listAmountCharacter = new ArrayList();
                ///////////////////////////////// Finish decleared
               System.out.println("socot va sodong: " + socot + " / " + sodong );
               
               getMetrix( metrix,  socot, sodong,clientEncryptedText);
               

                String[] keyList = stringKey.split(",");/// get keylist
                System.out.println("KEYLIST = " +Arrays.toString(keyList) );
                
                int flag= checkOriginal(keyList);
               
                System.out.println("rsLIST RS = " + Arrays.toString(metrix));

                if(flag == 1){
                    metrix = convertAdvanced( metrix,  socot,  keyList );
                }    
                
                
                System.out.println("rsLIST RS = " + Arrays.toString(metrix));
                
                

                
                rs = "";
                int maxAmount = 0;
                Integer semiMax = 0;
                for(int i = 0 ; i <  sodong; i ++){
                    for(int j = 0 ; j <  socot; j ++){
                        System.out.print(metrix[j].charAt(i) +"  " ); //+ "  "
                        char tempx =metrix[j].charAt(i);

                        rs += tempx;
                            
                            if( !listCharacter.contains(tempx) && tempx != '@'){
                                listCharacter.add(tempx);
 
                                Integer amount = clientEncryptedText.length() - clientEncryptedText.replace(Character.toString(tempx), "").length();
                                listAmountCharacter.add(amount);
                                
                                if(listCharacter.size() == 1){
                                    maxAmount  = amount;
                                            
                                }else if(maxAmount < amount) {
                                    semiMax = maxAmount;
                                    maxAmount = amount;
                                }else if(semiMax < amount){// if(listCharacter.size() == 2){
                                    semiMax = amount;
                                }
                                
                                System.out.println("temp [" + listCharacter.size() + "] = " + tempx + " amount = " +  amount );
                            }
                         
                    }
                    rs+=" ";
                    System.out.println("");
                } //FOR CONVERT INPUT
                rs = rs.replace("@", "").replace(" ","");
         
                String rsSemiMax =" /Amount Appeared: " + semiMax + " /Second most occurring characters : " ;
                System.out.println("semiMax Amount = " + semiMax );
                
                for (int i = 0; i < listAmountCharacter.size(); i++) {
                    if(listAmountCharacter.get(i) == semiMax) rsSemiMax += listCharacter.get(i) +" ";
                }
                
                System.out.println("seMax = " + rsSemiMax);
                
                clientEncryptedText = clientEncryptedText.toUpperCase();

    //            out.println("Key = "+ clientKey+ " Text = " +clientEncryptedText  + " / rs = "+ rs ); //+" rslength = " + rs.length()

               out.println("Text = "+ rs +  rsSemiMax   ); //+" rslength = " + rs.length()
      
                        
                        
                        
                        
                
              
                
                
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
