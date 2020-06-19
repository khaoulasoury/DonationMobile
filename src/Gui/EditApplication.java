
package Gui;

import Entities.Application;
import static Gui.AddApplication.o;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.intelligence.MyApplication;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Utils.WebService;

/**
 *
 * @author Amin
 */
public class EditApplication extends BaseForm{
    public static Application e ; 
    private String im ="";
    public EditApplication(Resources res){
        setName("Edit");
        setTitle("Edit");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
         FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
           ApplicationDetails.e = e ;
           ApplicationDetails ea = new ApplicationDetails(res);
           ea.show();
           
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
            
            TextField b = new TextField();
            b.setText(e.getMotivation());
            Label c = new Label("Date Disponibilité ");
            Picker datePicker = new Picker();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
             try {
           datePicker.setDate(df.parse(e.getDisponibleDate()));         
       } catch (ParseException ex) {
           
       }  
            datePicker.setType(Display.PICKER_TYPE_DATE);
            Button img = new Button("Ajouter une image",icone);
            Button a = new Button("Modifier ");
            
            
            photos.add(b);
            photos.add(c);
            photos.add(datePicker);
            photos.add(img);
            photos.add(a);
            
           
            add(photos);
            
            img.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ev) {
                   
                    

                   try {
                        
                          
                     String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost:88/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want

                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    im =fileNameInServer ;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                }
                }
            });
            a.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ev) {
                     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateobj = new Date();
                Date da = datePicker.getDate();
                String st = df.format(dateobj);
                String st1 = df.format(da);
               Date dnow = new Date(); 
               Date dc = new Date();              
       try {
           dnow = df.parse(st);
           dc = df.parse(st1);          
       } catch (ParseException ex) {
           
       }                 
                   if(b.getText().equals("") || ((int)(dnow.getTime()- dc.getTime()) > 0)){
                    Dialog.show("Erreur", "Vérifiez vos informations", "Ok", null);
                   }else{
                       
                   if(!im.equals("")){
                   
                   e.setCv(im);}
                   e.setMotivation(b.getText());
                   
                   e.setDisponibleDate(st1);
                   ws.editEvent(e);
                   ListApplications la = new ListApplications(res);
                   la.show();
                   }
                   
                }
            });
            
        
        show();
        
    }
    
}
