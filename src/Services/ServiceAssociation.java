
package Services;



import java.util.*;
import Entities.Association;
import com.codename1.util.StringUtil;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Utils.MaConnection;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tarek
 */
public class ServiceAssociation {

    public ArrayList<Association> Associations, assocs;
    public Association assoc;
    private ConnectionRequest req;
    public boolean resultOK;


    public static ServiceAssociation instance=null;

    public ServiceAssociation() {
        req = new ConnectionRequest();
    }

    public static ServiceAssociation getInstance() {
        if (instance == null) {
            instance = new ServiceAssociation();
        }
        return instance;
    }
    public ArrayList<Association> parseAssociations(String jsonText){
        try {
            Associations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> PostsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)PostsListJson.get("root");
            for(Map<String,Object> obj : list){
                Association p = new Association();
                float id = Float.parseFloat(obj.get("idAssociation").toString());
                p.setId_Association((int)id);
                p.setNom_Association(obj.get("nomAssociation").toString());
                p.setEmail_Association(obj.get("emailAssociation").toString());
                p.setObjectif_Association(obj.get("objectifAssociation").toString());
                p.setPassword_Association(obj.get("passwordAssociation").toString());
                p.setAddress_Association(obj.get("addressAssociation").toString());
                p.setDescription_Association(obj.get("descriptionAssociation").toString());
                p.setImage_name(obj.get("imageName").toString());

                System.out.println("obj : "+p);
                Associations.add(p);
            }
        } catch (IOException ex) {
        }
        return Associations;
    }
    public ArrayList<Association> getAllAssociations(){
        String url = MaConnection.BASE_URL+"Donation/"+"getAllAssociations";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Associations = parseAssociations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Associations;
    }


    public boolean addAssociation(Association t) {
        String url = MaConnection.BASE_URL + "Donation/addAsso/" + t.getNom_Association()+ "/" + t.getObjectif_Association()+ "/" + t.getEmail_Association()+ "/" + t.getPassword_Association()+ "/" + t.getAddress_Association()+ "/" + t.getType_Association()+ "/" + t.getDescription_Association()+ "/" + t.getImage_name();
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
    public boolean updateAssociation(Association t) {
        String url = MaConnection.BASE_URL + "Donation/editAsso/" + t.getId_Association()+ "/" + t.getNom_Association()+ "/" + t.getObjectif_Association()+ "/" + t.getEmail_Association()+ "/" + t.getPassword_Association()+ "/" + t.getAddress_Association()+ "/" + t.getType_Association()+ "/" + t.getDescription_Association()+ "/" + t.getImage_name();
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

    public ArrayList<Association> getbyId(int id){
        String url = MaConnection.BASE_URL+"Donation/"+"getbyIdMobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Associations = parseAssociations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Associations;
    }
    public boolean deleteAssociation(int id) {
        String url = MaConnection.BASE_URL + "Donation/deleteAssociationMobile/" + id ;
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

    public Association FindAssociation(String email){
        String url = MaConnection.BASE_URL+"Donation/findd/"+email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                assocs = parseAssociations(new String(req.getResponseData()));
                req.removeResponseListener(this);
                try{
                    assoc = assocs.get(0);
                }catch(Exception e){
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return assoc;
    }

    public ArrayList<Association> parseListEvenementsJson(String json) {

        ArrayList<Association> listassociation = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                Association e = new Association();
                float idAssociation = Float.parseFloat(obj.get("idAssociation").toString());
                e.setId_Association((int) idAssociation);
                e.setNom_Association(obj.get("nomAssociation").toString());



                listassociation.add(e);
            }
        } catch (IOException ex) {
        }
        return listassociation;

    }



    ArrayList<Association> listassociation = new ArrayList();



    public ArrayList<Association> getListAssociations() {
        ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/products/ListeAssociationall");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAssociation ser = new ServiceAssociation();
                listassociation = ser.parseListEvenementsJson(new String(cnx.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return listassociation;
    }
}
