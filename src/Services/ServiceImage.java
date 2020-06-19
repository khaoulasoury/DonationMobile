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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import Entities.Image;
import Utils.Static;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hatem
 */
public class ServiceImage extends Form{
    public ArrayList<Image> images;
    public Image image;
    
    public static ServiceImage instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceImage() {
         req = new ConnectionRequest();
    }

    public static ServiceImage getInstance() {
        if (instance == null) {
            instance = new ServiceImage();
        }
        return instance;
    }

    public boolean addImage(Image u) {
        String url = Static.BASE_URL + "/image/new?view=" + u.getId_view()+ "&name=" + u.getName();
                
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

    public ArrayList<Image> parseImages(String jsonText){
        try {
            images=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            Date dateobj = new Date();
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Image u = new Image();
                float id = Float.parseFloat(obj.get("idImage").toString());
                u.setId_image((int)id);
                String unholyText = obj.get("idView").toString();
                String demiHolyText=""; 
                for(int i = 8; i<13 ; i++){
                    demiHolyText = demiHolyText + unholyText.charAt(i);
                }
                String holyText = StringUtil.replaceAll(demiHolyText, ",", "");
                u.setId_view(((int)Float.parseFloat(holyText)));
                u.setName(obj.get("name").toString());

                images.add(u);
            }
        } catch (IOException ex) {
            
        }
        return images;
    }
    
    public ArrayList<Image> getAllImages(){
        String url = Static.BASE_URL+"/image/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                images = parseImages(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return images;
    }
    
    public Image FindImage(String name){
        String url = Static.BASE_URL+"/image/finde/"+name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                images = parseImages(new String(req.getResponseData()));
                req.removeResponseListener(this);
                try{
                    image = images.get(images.size() - 1);
                }catch(Exception e){
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                return image;
    }
}
