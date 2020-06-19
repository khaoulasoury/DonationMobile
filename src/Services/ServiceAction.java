/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.*;
import Entities.Action;
import Utils.MaConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Tarek
 */
public class ServiceAction {
    
    public ArrayList<Action> Actions;
    public static ServiceAction instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ServiceAction() {
        req = new ConnectionRequest();
    }
    
    public static ServiceAction getInstance() {
        if (instance == null) {
            instance = new ServiceAction();
        }
        return instance;
    }
    
    public ArrayList<Action> parseActions(String jsonText){
        try {
            Actions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)PostsListJson.get("root");
            for(Map<String,Object> obj : list){
                Action p = new Action();
                float idAction = Float.parseFloat(obj.get("idAction").toString());
                p.setId_Action((int)idAction);
                float nbV = Float.parseFloat(obj.get("nbvAction").toString());
                p.setNbV_Action((int)nbV);
                p.setName_Action(obj.get("nameAction").toString());
                p.setDate_Action(obj.get("dateAction").toString());
                p.setImage_name(obj.get("imageName").toString());
                p.setDescription(obj.get("description").toString());
                p.setLocation_Action(obj.get("locationAction").toString());
                //System.out.println("obj : "+obj);
                Actions.add(p);
            }
        } catch (IOException ex) {
        }
        return Actions;
    }
    
    public ArrayList<Action> getAllActions(){
        String url = MaConnection.BASE_URL+"Donation/"+"getAllActions";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Actions = parseActions(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Actions;
    }
    /*public void Add(Action act) {

        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/donationWEB/web/app_dev.php/addblog?id="+act.getId_Association()+"&nameAction="+act.getName_Action()+"&locationAction="+act.getLocation_Action()+"&imageName="+act.getImage_name();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addArgument("nameAction", act.getName_Action());
        con.addArgument("dateAction", act.getDate_Action());
        con.addArgument("imageName", act.getImage_name());
        con.addArgument("locationAction", act.getLocation_Action());
        con.addArgument("description", act.getDescription());

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }*/
    
    public boolean addAction(Action t) {
        String url = MaConnection.BASE_URL + "Donation/add/" + t.getName_Action()+ "/" + t.getDate_Action()+ "/" + t.getLocation_Action()+ "/" + t.getNbV_Action()+ "/" + t.getDescription()+ "/" + t.getImage_name();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean deleteAction(int id) {
        String url = MaConnection.BASE_URL + "Donation/deleteActionMobile/" + id ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
