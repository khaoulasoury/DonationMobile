
package Gui;

import Entities.Application;
import Entities.Offer;
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


public class ListApplications extends BaseForm{
    
    public ListApplications(Resources res){
        setName("Liste des applications");
        setTitle("Liste des applications");
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
         getToolbar().addCommandToRightSideMenu("Mes Notifications", icon, (e) -> {
             ListNotifications la = new ListNotifications(res);
           la.show();
           
        });
            
             
             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  
         
        });
            
             WebService ws = new WebService();
    OfferService ds = new OfferService();
    Map x = ws.getResponse("/listapp/"+MyApplication.id);
    ArrayList<Application> listevents = ds.getListsApp(x);
             for (Application e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label("Poste : "+e.getOffer().getPost());
            Label b = new Label("Le : "+e.getDisponibleDate());


            imv = new ImageViewer(URLImage.createCachedImage("ei", "http://localhost/donationWEB/web/uploads/" + e.getOffer().getImage(), Image.createImage(1280, 720), URLImage.FLAG_RESIZE_SCALE_TO_FILL));

                 //img = URLImage.createToStorage(encoded, e.getCv(), "http://127.0.0.1:8000/Upload/" + e.getCv());

            photos.add(imv);
            photos.add(a);
            photos.add(b);

            add(photos);
            
            a.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                   
                   
                    ApplicationDetails.e = e;
                    ApplicationDetails ee = new ApplicationDetails(res);
                    ee.show();
                            
                       
                    

                }
            });
            
        }
        show();
    }
    
}
