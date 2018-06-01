package dao;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import entities.Personnage;
import entities.Profil;

public class TestMethodeServeur extends Thread {

	  
    @Override
    public void run() {
    	PersonnageMetier.initiliseMap();
    	PersonnageMetier personnageMetier = new PersonnageMetier();
    	
    	for(java.util.Map.Entry<String, Calendar> entry : PersonnageMetier.getMap().entrySet()) {
    	    String pseudo =  entry.getKey();
    	    Calendar date =  entry.getValue();
    	    if(date.getTimeInMillis() - Calendar.getInstance().getTimeInMillis() >= 50000 ) {
    	    	System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
    	    	//personnageMetier.UpdateCapaciter(pseudo, false);
    	    }
    	}
    	
    	
    }

     
/*inal ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS)*/

}
