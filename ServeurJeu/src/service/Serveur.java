package service;

import java.net.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import dao.*;
import entities.Capaciter;
import entities.Personnage;
import entities.Profil;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Serveur extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea zonerecep;
	private JButton qt;
	private JPanel pan1, pan2;
	private String host = "192.168.43.129";

	public Serveur() {
		zonerecep = new JTextArea(15, 40);
		qt = new JButton("Quitter");
		setTitle(" Serveur Multiclients");
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan1.add(new JScrollPane(zonerecep));
		qt.addActionListener(this);
		pan2.add(qt);
		add(pan1, BorderLayout.CENTER);
		add(pan2, BorderLayout.SOUTH);
		setSize(500, 600);
		setLocationRelativeTo(null);
		setVisible(true);

		try {
			ServerSocket serv = new ServerSocket(40000, 50, InetAddress.getByName(host));
			zonerecep.append("Le Serveur a demarre " + "\n");
			int numclient = 1;
			while (true) {
				Socket socket = serv.accept();
				InetAddress adr = socket.getInetAddress();
				String ipclient = adr.getHostAddress();
				String nomclient = adr.getHostName();
				zonerecep.append("client n°:" + numclient + " adresse ip:" + ipclient + "\n");
				zonerecep.append("nom machine cliente: " + nomclient + "\n");
				Service s = new Service(socket);
				TestMethodeServeur methodeServeur  = new TestMethodeServeur() ;
				methodeServeur.start();
				s.start();
				numclient++;
			}

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	// class interne
	class Service extends Thread {
		private Socket socket;
		private Message message;
		private PersonnageMetier personnageMetier;

		public Service(Socket socket) {
			personnageMetier = new PersonnageMetier();
			this.socket = socket;

		}

		public void run() {
			try {
				// zonerecep.append("test 1");
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				// zonerecep.append("test 2");
				String message;

				// zonerecep.append("test 3");
				do {

					message = (String) ois.readObject();
					// zonerecep.append("test 4");
					zonerecep.append("mode en cours d\'expoitation!!!: " + message + "\n");
					System.out.println("le type" + "salut");
					switch (message) {
					case "1":
						Personnage creerPersonnage = (Personnage) ois.readObject();
						creerPersonnage.getPseudo();
						// zonerecep.append(" " + creerPersonnage.getPseudo() + "\n");
						// zonerecep.append(" " + creerPersonnage.getProfil().getProfil() + "\n");

						personnageMetier.creerPersonnage(creerPersonnage);
						oos.writeObject(" OK on a cree le profil :" + creerPersonnage.getPseudo());
						break;
					case "2":
						zonerecep.append(" Connection recue \n");
						List<Personnage> listeperCinnecter = new ArrayList<Personnage>();
						listeperCinnecter = (List<Personnage>) personnageMetier.getPersonnageConnecter();
						oos.writeObject(listeperCinnecter);
						oos.flush();

					case "3":
						String pseudo = (String) ois.readObject();
						// String pseudo = "LABO";
						boolean resutalt = personnageMetier.authentification(pseudo);
						zonerecep.append(resutalt + "\n");
						// System.out.println(resutalt);
						oos.writeObject(resutalt);
					case "4":
						//zonerecep.append(" ok \n");
						String personnageChercher = (String) ois.readObject();
						Personnage personnage2 = new Personnage();
						zonerecep.append(" " + personnageChercher + "\n");
						personnage2.setPseudo(personnageChercher);
						zonerecep.append(" " + personnage2.getPseudo() + "\n");
						Personnage personnage = personnageMetier.getPersonnage(personnage2);
						oos.writeObject(personnage);
						oos.flush();
						break;
					case "5":
						
						 String pseudo2 =  (String) ois.readObject();
						 System.out.println(pseudo2);
						 personnageMetier.UpdateCapaciter(pseudo2,true);
						 System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");

					case "6":

						List<Profil> listeProfil = new ArrayList<Profil>();
						listeProfil = (List<Profil>) personnageMetier.listerProfils();
						oos.writeObject(listeProfil);
						oos.flush();
						break;
					case "7":
						String pseudoDeconnecter = (String) ois.readObject();
						personnageMetier.decoonecter(pseudoDeconnecter);

						break;
					case "fin":
						zonerecep.append("Connexion terminee!!! pour un personnage" + "\n");
						oos.flush();
						break;
					default:
						System.out.println("Aucune Action correspondante");
						break;
					}
				} while (true);
			}

			catch (Exception ex) {
				System.out.println("****" + ex.getMessage());
			}
		}// fin classe interne

	}

	public void actionPerformed(ActionEvent e) {
		dispose();
		System.exit(0);
	}

	public static void main(String args[]) {
		new Serveur();
		System.out.println("ok");
		final Runnable task = new Runnable() {
            
	        @Override
	        public void run() {
	        	PersonnageMetier.initiliseMap();
	        	PersonnageMetier personnageMetier = new PersonnageMetier();
	        	
	        	for(java.util.Map.Entry<String, Calendar> entry : PersonnageMetier.getMap().entrySet()) {
	        	    String pseudo =  entry.getKey();
	        	    Calendar date =  entry.getValue();
	        	    if(Calendar.getInstance().getTimeInMillis() - date.getTimeInMillis() >= 5 )
	        	    	personnageMetier.UpdateCapaciter(pseudo, false);
	        	}
	        	
	        	
	        }
	    };
	         
	    final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	        executor.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
	}

}
