/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeragente;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author alan2
 */
public class PrimerAgente extends Agent {

    public void setup() {
        System.out.println("Agente iniciado");
        
        final frame1 f=new frame1();
        f.setVisible(true);
                
        addBehaviour(new TickerBehaviour(this, 360000) {
            protected void onTick() {
                System.out.println("leer timeline");
                Analizador a1=new Analizador(getTimeline());        
                f.actualizar();
            }
        });       
    }

    public String getTimeline() {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();

        } catch (TwitterException ex) {
            Logger.getLogger(PrimerAgente.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Showing home timeline.");
        String contenido = "";
        for (Status status : statuses) {
            contenido += status.getUser().getName() + ":" + status.getText() + "\n";
        }
        System.out.println("----------------------------------------------");
        System.out.println(contenido);
        System.out.println("----------------------------------------------");
        return contenido;
    }
}
