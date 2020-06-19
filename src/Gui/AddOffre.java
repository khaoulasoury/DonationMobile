
package Gui;
import Entities.Offre;
import Services.ServicesOffre;

import com.codename1.components.FloatingHint;

import com.codename1.ui.Button;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Oussama
 */
public class AddOffre extends BaseForm{
    Image videImg;
    private String im ;
    
     public AddOffre(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        ServicesOffre prodServ=new ServicesOffre();
        TextField contact = new TextField("", "contact", 20, TextField.ANY);
        TextField type = new TextField("", "type", 20, TextField.ANY);
        TextField state = new TextField("", "state", 20, TextField.ANY);
        
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
        
        //TextField image = new TextField("", "Image", 20, TextField.ANY);
        //TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        //TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        //id.setSingleLineTextArea(false);
        contact.setSingleLineTextArea(false);
        type.setSingleLineTextArea(false);
        state.setSingleLineTextArea(false);

        Button next = new Button("Next");
        next.getAllStyles().setPadding(0, 0, 16, 16);
         next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           
                           Offre t = new Offre();
                           t.setContact(contact.getText());
                           t.setState(state.getText());
                           t.setType(type.getText());
                        System.out.println(t);
            
                   prodServ.addOffre(t);
  

        
         
                    
                }
//            }
        });
          getToolbar().addCommandToOverflowMenu("Display Offre",videImg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {


                    affichageoffre add=new affichageoffre(res);
                    add.show();

            }
        });



        Container content = BoxLayout.encloseY(
                new Label("Add Offer", "LogoLabel"),
               // new FloatingHint(id),
                //createLineSeparator(),
                new FloatingHint(contact),
                createLineSeparator(),
                new FloatingHint(type),
                createLineSeparator(),
                new FloatingHint(state),
                createLineSeparator()
             
               
                
        );
        Container r = new Container(BoxLayout.x());
        r.addAll(next);
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                r
        ));
    }
}
