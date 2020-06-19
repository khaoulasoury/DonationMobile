
package Gui;

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import Utils.WebService;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Amin
 */
public class OfferDetails extends BaseForm{
    public static Offer e ; 
    
    public OfferDetails(Resources res){
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           ListOffers lo = new ListOffers(res);
           lo.show();
        });
          getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, es -> {
              AddApplication.o = e ;           
              AddApplication ad = new AddApplication(res);
              ad.show();
        });
         getToolbar().addCommandToRightSideMenu("Liste des offres", icon, (e) -> {
            ListOffers lo = new ListOffers(res);
           lo.show();
           
        });
            getToolbar().addCommandToRightSideMenu("Liste de mes applications", icons, (e) -> {
                ListApplications la = new ListApplications(res);
                la.show();
             
        });
            getToolbar().addCommandToRightSideMenu("Mes Notifications", icon, (e) -> {
             ListNotifications la = new ListNotifications(res);
           la.show();
           
        });
             
             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  
         
        });
            
             WebService ws = new WebService();
    
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label("Titre : "+e.getPost());
            Label c = new Label("Type : "+e.getType());
            Label b = new Label("Date publication : "+e.getDateAjout());
            Label d = new Label("Préférences : "+e.getConditionOffer());
            Label f = new Label("Description : "+e.getDescription());



        imv = new ImageViewer(URLImage.createCachedImage("ei", "http://localhost/donationWEB/web/uploads/" + e.getImage(), Image.createImage(1280, 720), URLImage.FLAG_RESIZE_SCALE_TO_FILL));
         //   img = URLImage.createToStorage(encoded, e.getImage(), " http://localhost/donationWEB/web/uploads/" + e.getImage());
       // poster = new ImageViewer(URLImage.createCachedImage("ei", "http://localhost/donationWEB/web/img/events/" + event.getPoster(), Image.createImage(1280, 720), URLImage.FLAG_RESIZE_SCALE_TO_FILL));

            photos.add(imv);


            photos.add(a);
            photos.add(c);
            photos.add(b);
            photos.add(d);
            photos.add(f);
            add(photos);
            
            a.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                   
                    
                            
                       
                    

                }
            });
            
        
        show();
    }
}
