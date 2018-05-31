package service;

import java.net.*;
import java.util.*;

import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;


public class Client extends JFrame implements ActionListener 
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton qt;
	private JPanel pan1,pan2;
	private Socket socket;
	DataOutputStream out;
	DataInputStream in;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    
	public Client() 
	{
		
		qt = new JButton("Quitter");
		
		qt.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan1.setLayout(new GridLayout(4,2));
		
	
		pan2.add(qt);
		add(pan1,BorderLayout.CENTER);
		add(pan2,BorderLayout.SOUTH);
		setTitle("client");
		setSize(500,200);
		setLocationRelativeTo(null);
		setVisible(true);
		try
		{
		socket= new Socket("127.0.0.1",40000);
		out = new DataOutputStream(socket.getOutputStream());
	    in = new DataInputStream(socket.getInputStream());
	    oos=new ObjectOutputStream(socket.getOutputStream());;
	    ois=new ObjectInputStream(socket.getInputStream());
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	public void actionPerformed(ActionEvent e)
	
	
	{
		if (e.getSource()==qt)
		{
			try
			{
			 oos.writeObject("fin");
	    	 oos.flush();
	    	 socket.close();
			dispose();
			System.exit(0);
		    }
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			
		}
		
		 
		
	}
	public static void main(String args[])
	{
		new Client();
	 
     }
}