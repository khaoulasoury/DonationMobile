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
import com.codename1.util.StringUtil;
import Entities.View;
import Utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hatem
 */
public class ServiceView {
    public ArrayList<View> views;
    public View view;
    
    public static ServiceView instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceView() {
         req = new ConnectionRequest();
    }

    public static ServiceView getInstance() {
        if (instance == null) {
            instance = new ServiceView();
        }
        return instance;
    }

    public boolean addViews(View v) {
        String url = Static.BASE_URL + "/view/new?text=" + v.getText()+ "&user=" + ServiceFos_user.getInstance().user.getId()
                + "&event=" + v.getId_event(); 
                
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

    public ArrayList<View> parseViews(String jsonText){
        try {
            views=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                View v = new View();
                float id = Float.parseFloat(obj.get("idView").toString());
                v.setId_view((int)id);
                
                String unholyText = obj.get("idUser").toString();
                String demiHolyText=""; 
                for(int i = 4; i<8 ; i++){
                    demiHolyText = demiHolyText + unholyText.charAt(i);
                }
                String holyText = StringUtil.replaceAll(demiHolyText, ",", "");
                v.setId_user(((int)Float.parseFloat(holyText)));
                
                v.setText(obj.get("text").toString());

                float idEvent= Float.parseFloat(obj.get("idEvent").toString());
                v.setId_event((int)idEvent);

                views.add(v);
            }

            
        } catch (IOException ex) {
            
        }
        return views;
    }
    
    public ArrayList<View> getAllViews(){
        String url = Static.BASE_URL+"/view/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                views = parseViews(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return views;
    }
    
    public View FindView(int id){
        String url = Static.BASE_URL+"/view/finde/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                views = parseViews(new String(req.getResponseData()));
                req.removeResponseListener(this);
                try{
                    view = views.get(views.size() - 1);
                }catch(Exception e){
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                return view;
    }
    
    public void deleteView(int idview) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Static.BASE_URL+"/view/delete/" + idview;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
