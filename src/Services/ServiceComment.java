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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import Entities.Comment;
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
public class ServiceComment {
    public ArrayList<Comment> comments;
    
    public static ServiceComment instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceComment() {
         req = new ConnectionRequest();
    }

    public static ServiceComment getInstance() {
        if (instance == null) {
            instance = new ServiceComment();
        }
        return instance;
    }

    public boolean addComment(Comment u) {
        String url = Static.BASE_URL + "/comment/new?text=" + u.getText_comment()+ "&user=" + ServiceFos_user.getInstance().user.getId()
                 + "&view=" + String.valueOf(u.getId_view());
                
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

    public ArrayList<Comment> parseComments(String jsonText){
        try {
            comments=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            Date dateobj = new Date();
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Comment u = new Comment();
                float id = Float.parseFloat(obj.get("idComment").toString());
                u.setId_comment((int)id);
                u.setText_comment(obj.get("textComment").toString());
                //u.setId_user(((int)Float.parseFloat(obj.get("idUser").toString())));
                
                String unholyText = obj.get("idUser").toString();
                String demiHolyText=""; 
                for(int i = 4; i<9 ; i++){
                    demiHolyText = demiHolyText + unholyText.charAt(i);
                }
                String holyText = StringUtil.replaceAll(demiHolyText, ",", "");
                u.setId_user(((int)Float.parseFloat(holyText)));
                
                
                //u.setId_view(((int)Float.parseFloat(obj.get("idView").toString())));
                
                String unholyText2 = obj.get("idView").toString();
                String demiHolyText2=""; 
                for(int i = 8; i<13 ; i++){
                    demiHolyText2 = demiHolyText2 + unholyText2.charAt(i);
                }
                String holyText2 = StringUtil.replaceAll(demiHolyText2, ",", "");
                u.setId_view(((int)Float.parseFloat(holyText2)));
                
                u.setDate(dateobj);

                comments.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
        return comments;
    }
    
    public ArrayList<Comment> getAllComments(){
        String url = Static.BASE_URL+"/comment/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comments = parseComments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }
    
    public void deleteComment(int idcomment) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Static.BASE_URL+"/comment/delete/" + idcomment;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
