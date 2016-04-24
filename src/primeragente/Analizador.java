/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeragente;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author alan2
 */
public class Analizador {

    Analizador() {        
    }

    public void hacer_cuentas() {
        System.out.println("------------------------------------------------");
        String contenido="<html lang=\"es\"><head><meta charset=\"utf-8\"></head><body><table border=\"1\"><tr><th>Hashtag</th><th>Frecuencia</th></tr>";
        for (Object key : hashtags.keySet()) {                        
            hashtag h1=(hashtag)hashtags.get(key);
            contenido+="<tr><td>"+key+"</td><td>"+h1.aparicion+"/"+total+"</td></tr>";
            //System.out.println("hashtag: " + key + " veces: " + h1.aparicion+"/"+total);
        }
        contenido+="</table></body></html>";
        EsribirArchivo ea=new EsribirArchivo();
        ea.Escribir(contenido, "C:\\Users\\alan2\\Desktop\\Reporte.html");
    }

    class hashtag {

        String texto;
        int aparicion;

        private hashtag(String hashTag, int aparicion) {
            this.texto = hashTag;
            this.aparicion = aparicion;
        }
    }

    /**
     *
     */
    public static Hashtable hashtags = new Hashtable();
    public static int total=0;
    Analizador(String timeline) {
        iniciar(timeline);           
    }

    void iniciar(String timeline) {
        String tweets[] = timeline.split("\n");
        for (int i = 0; i < tweets.length; i++) {
            String tw = tweets[i];
            String hashTag = "";
            boolean hash = false;
            for (int j = 0; j < tw.length(); j++) {
                if (tw.charAt(j) == '#') {
                    hash = true;
                }
                if (hash && tw.charAt(j) != ' '&& tw.charAt(j) != ',') {
                    hashTag += tw.charAt(j);
                } else {
                    if (hash) {
                        hashTag=hashTag.toLowerCase();
                        if(hashtags.containsKey(hashTag)){
                            hashtag h1=(hashtag)hashtags.remove(hashTag);
                            h1.aparicion++;
                            hashtags.put(hashTag, h1);
                        }else{
                            hashtags.put(hashTag,new hashtag(hashTag,1));
                        }                  
                        total++;
                        System.out.println("total: "+total);
                    }
                    hashTag="";
                    hash = false;

                }
            }
        }
    }

}
