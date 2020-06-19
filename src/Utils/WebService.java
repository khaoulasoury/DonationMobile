/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;



import Entities.Application;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.intelligence.MyApplication;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Amin
 */
public class WebService {
    static Map h;
    static String status ="";
    static int c ;
    static String lg ;
    
    public static Map<String, Object> getResponse(String url){
        url = "http://127.0.0.1:8000/Donation"+url;
        
        ConnectionRequest r = new ConnectionRequest();
      
        r.setUrl(url);
        r.setPost(false);
       
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
       
        return h; 
    }
   
     
    public void addEvent(Application e){

        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/Donation/addapp";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);

     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("motivation", e.getMotivation());
     con.addArgument("cv", e.getCv());
     con.addArgument("date", e.getDisponibleDate());
     con.addArgument("user", e.getUser()+"");
     con.addArgument("offer", e.getOffer().getId()+"");


        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void delete(int id){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/Donation/delapp/"+id;
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");

    
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void editEvent(Application e){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/Donation/editapp";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("motivation", e.getMotivation());
     con.addArgument("cv", e.getCv());
     con.addArgument("id", e.getId()+"");
     con.addArgument("date", e.getDisponibleDate());
     con.addArgument("user", e.getUser()+"");
     con.addArgument("offer", e.getOffer().getId()+"");
    
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public int count(){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "/count";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        c = Integer.parseInt(s);
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return c ; 
    }
    
    
    
}
