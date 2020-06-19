/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Fos_user;
import Utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hatem
 */
public class ServiceFos_user {
    public ArrayList<Fos_user> users, usersById;
    public Fos_user user, userById;
    
    public static ServiceFos_user instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceFos_user() {
         req = new ConnectionRequest();
    }

    public static ServiceFos_user getInstance() {
        if (instance == null) {
            instance = new ServiceFos_user();
        }
        return instance;
    }

    public boolean addUsers(Fos_user u) {
        String url = Static.BASE_URL + "/users/new?username=" + u.getUsername() + "&email=" + u.getEmail() 
                 + "&password=" + u.getPassword() + "&tel=" + u.getTel()
                 + "&address=" + u.getAddress();
                
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

    public ArrayList<Fos_user> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Fos_user u = new Fos_user();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                u.setTel(((int)Float.parseFloat(obj.get("tel").toString())));
                u.setAddress(obj.get("address").toString());

                users.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
    
    public ArrayList<Fos_user> getAllUsers(){
        String url = Static.BASE_URL+"/users/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
    public Fos_user FindUser(String email){
        String url = Static.BASE_URL+"/users/finde/"+email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
                try{
                    user = users.get(0);
                }catch(Exception e){
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                return user;
    }
    
    public Fos_user FindUserId(int id){
        String url = Static.BASE_URL+"/users/findd/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                usersById = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
                try{
                    userById = usersById.get(0);
                }catch(Exception e){
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                return userById;
    }
}
