package service;

import java.net.*;
import java.util.*;

import javax.swing.*;
import dao.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Serveur extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea zonerecep;
	 private JButton qt;
	 private JPanel pan1,pan2;
	 private String host = "127.0.0.1";

	 public Serveur()
	 {
	     zonerecep=new JTextArea(15,40);
	    qt=new JButton("Quitter");
	    setTitle(" Serveur Multiclients");
	    pan1=new JPanel();
	    pan2=new JPanel();
	    pan1.add(new JScrollPane(zonerecep));
	    qt.addActionListener(this);
	    pan2.add(qt);
	    add(pan1,BorderLayout.CENTER);
	    add(pan2,BorderLayout.SOUTH);
	    setSize(500,600);
	    setLocationRelativeTo(null);
	    setVisible(true);


	     try
	    {
	        ServerSocket serv = new ServerSocket(40000,50,InetAddress.getByName(host));
	        zonerecep.append("Le Serveur a demarre "+"\n");
	        int numclient=1;
	        while(true)
	        {
	            Socket socket=serv.accept();
	            InetAddress adr = socket.getInetAddress();
	            String ipclient = adr.getHostAddress();
	            String nomclient= adr.getHostName();
	            zonerecep.append("client n°:"+numclient+" adresse ip:"+ipclient+"\n");
	            zonerecep.append("nom machine cliente: "+nomclient+"\n");
	            Service s = new Service(socket);
	            s.start();
	            numclient++;
	        }

	    }
	    catch(IOException ex)
	    {
	        System.out.println(ex.getMessage());
	    }
	 }

	 //class interne
	    class Service extends Thread
	    {
	       private  Socket socket;
	       private PersonnageMetier personnageMetier;
	    
	        public Service(Socket socket)
	        {
	        	personnageMetier = new PersonnageMetier();
	            this.socket=socket;
	            	   
	        }
	        public void run()
	        {
	            try
	            {
	           ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	           ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
	           DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	           DataInputStream in = new DataInputStream(socket.getInputStream());
	       	
	           String mode;
	           do
	           {
	        	    
	            mode =(String)ois.readObject();
	             zonerecep.append("mode en cours d\'expoitation!!!: "+mode+"\n");
	         	switch (mode) {
				case "ajoutAgence":
					
					break;
				case "ajoutOperation":
					
					break;
				case "fin":
					zonerecep.append("Connexion terminee!!! pour un client"
							+ "\n");
					oos.flush();
					break;
				default:
					System.out.println("Aucune Action correspondante");
					break;
				}
			} while (true);
	            
	           }
	             
	            catch(Exception ex)
	            {
	                System.out.println("****"+ex.getMessage());
	            }    
       }//fin classe interne

	        }
	  public void actionPerformed(ActionEvent e)
	    {
	        dispose();
	        System.exit(0);
	    }
	    public static void main(String args[])
	    {
	        new Serveur();
	    }
	
}
