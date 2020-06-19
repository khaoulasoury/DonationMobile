
package Services;

import java.util.*;
import com.codename1.ui.AutoCompleteTextField;
import Entities.Offre;
import Utils.MaConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class ServicesOffre {
    public ArrayList<Offre> Offres;
    public static ServicesOffre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ServicesOffre() {
        req = new ConnectionRequest();
    }
    
    public static ServicesOffre getInstance() {
        if (instance == null) {
            instance = new ServicesOffre();
        }
        return instance;
    }
    
    
    public ArrayList<Offre> parseOffres(String jsonText){
        try {
             Offres=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)PostsListJson.get("root");
            for(Map<String,Object> obj : list){
                Offre p = new Offre();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setContact(obj.get("contact").toString());
                p.setType(obj.get("type").toString());
                p.setState(obj.get("state").toString());
                
                Offres.add(p);
            }
        } catch (IOException ex) {
        }
        return Offres;
    }
    ArrayList<Offre> lOffres = new ArrayList();
    public ArrayList<Offre> getAllOffres(){
ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/offre/ListeOffresall");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicesOffre ser = new ServicesOffre();
                lOffres = ser.parseOffres(new String(cnx.getResponseData()));
                cnx.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return lOffres;
    }

    
         
       public boolean addOffre(Offre t) {
       ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/offre/AddOffreDbMobile?contact="+t.getContact()+"&state="+t.getState()+"&type="+t.getType());
          
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cnx.getResponseCode() == 200; //Code HTTP 200 OK
                cnx.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return resultOK;
    }
    public boolean deleteOffre(int id) {
   ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/offre/DeleteOffreMobile/"+id);
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cnx.getResponseCode() == 200; //Code HTTP 200 OK
                cnx.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return resultOK;
    }
    
  
    public boolean updateOffre(Offre t) {
        String url = MaConnection.BASE_URL + "Donation/update/" + t.getType()+ "/" + t.getContact()+ "/" + t.getState();
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
