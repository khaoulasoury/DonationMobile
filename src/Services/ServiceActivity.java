package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Activity;
import Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ServiceActivity {


    public ArrayList<Activity> Activity;

    public static ServiceActivity instance=null;
    public boolean resultOK;
    public ConnectionRequest req;

    public ServiceActivity() {
        req = new ConnectionRequest();
    }

    public static ServiceActivity getInstance() {
        if (instance == null) {
            instance = new ServiceActivity();
        }
        return instance;
    }

    public boolean addTask(Activity t) {
        String url = Statics.BASE_URL + "/tasks/new?name=" + t.getNameAc() + "&duration=" + t.getDuration()+"&description="+t.getDescriptionAc()+"&type="+t.getTypeAc();
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
    public ArrayList<Activity> parseTasks(String jsonText){
        try {
            Activity=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Activity t = new Activity();

                float id = Float.parseFloat(obj.get("idAc").toString());
                t.setIdAc((int)id);
                t.setNameAc(obj.get("nameAc").toString());
                t.setDuration(((int)Float.parseFloat(obj.get("duration").toString())));
                t.setTypeAc(obj.get("typeAc").toString());
                t.setDescriptionAc(obj.get("descriptionAc").toString());

                Activity.add(t);
            }


        } catch (IOException ex) {

        }
        return Activity;
    }


    public ArrayList<Activity> getAllTasks(){
        String url = Statics.BASE_URL+"/tasks/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Activity = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Activity;
    }

}
