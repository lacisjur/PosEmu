/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.pashabank.posemu;

/**
 *
 * @author rsamadov
 */

public class Threading extends Thread{
    
   private Thread t;
   private String threadName;
   public String status="Begining";
   
    Threading(String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
    public void run() {
      System.out.println("Running " +  threadName );
      try {       
            System.out.println("Started");
            this.status="Started";
            Thread.sleep(5000);                                    
            System.out.println("Finished");
            this.status="Finished";
         }
      catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();         
      }
   }   
      
}