
package Gui;

import Entities.Application;
import static Gui.OfferDetails.e;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
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
import Utils.WebService;
import com.codename1.ui.util.Resources;


public class ApplicationDetails extends BaseForm{
    public static Application e ; 
    
    public ApplicationDetails(Resources res){setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           ListApplications la = new ListApplications(res);
           la.show();
        });
          
         getToolbar().addCommandToRightSideMenu("Liste des offres", icon, (e) -> {
           ListOffers lo = new ListOffers(res);
           lo.show();
           
           
        });
         getToolbar().addCommandToRightSideMenu("Mes Notifications", icon, (e) -> {
             ListNotifications la = new ListNotifications(res);
           la.show();
           
        });
            getToolbar().addCommandToRightSideMenu("Liste de mes applications", icons, (e) -> {
                
             ListApplications la = new ListApplications(res);
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
            Label a = new Label("Titre : "+e.getOffer().getPost());
            Label c = new Label("Disponible le : "+e.getDisponibleDate());
            Label b = new Label("Motivation : "+e.getMotivation());
            Button bu = new Button("Modifier");
            Button bt = new Button("Supprimer");
            
            


           // img = URLImage.createToStorage(encoded, e.getCv(), "http://localhost/donationWEB/web/uploads/" + e.getCv());
        imv = new ImageViewer(URLImage.createCachedImage("ei", "http://localhost/donationWEB/web/uploads/" + e.getCv(), Image.createImage(1280, 720), URLImage.FLAG_RESIZE_SCALE_TO_FILL));


            photos.add(imv);
            photos.add(a);
            photos.add(c);
            photos.add(b);
           photos.add(bu);
           photos.add(bt);
            add(photos);
            
            bu.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                   
                    EditApplication.e = e;
                          EditApplication ea = new EditApplication(res);
                          ea.show();
                       
                    

                }
            });
             bt.addActionListener(new ActionListener(){
                 WebService ds = new WebService();
                @Override
                public void actionPerformed(ActionEvent evt) {

                   ws.delete(e.getId());
                   ListApplications la = new ListApplications(res);
                   la.show();


                }
            });
            
        
        show();
    }
    
}
