
package Gui;

import Entities.Application;
import Entities.Notification;
import Services.OfferService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.intelligence.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import Utils.WebService;


public class ListNotifications extends BaseForm{
    
    public ListNotifications(Resources res){
        setName("Liste des Notifications");
        setTitle("Liste des Notifcations");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           ListOffers lo = new ListOffers(res);
           lo.show();
        });
          
         getToolbar().addCommandToRightSideMenu("Liste des offres", icon, (e) -> {
           ListOffers lo = new ListOffers(res);
           lo.show();
           
        });
            
             
             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  
         
        });
              
            
             WebService ws = new WebService();
    OfferService ds = new OfferService();
    Map x = ws.getResponse("/ListNotif");
    ArrayList<Notification> listevents = ds.getListsnotif(x);
             for (Notification e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            EncodedImage encoded = null;
            Label a = new Label(e.getMessage());
            Label b = new Label("Le : "+e.getDate());
            

           
            
            photos.add(a);
            photos.add(b);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                
                
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            a.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                   
                   
                   
                       
                    

                }
            });
            
        }
        show();
    }
        
    
    
}
