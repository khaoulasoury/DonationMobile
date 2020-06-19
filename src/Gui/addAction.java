
package Gui;

import Entities.Action;
import Services.ServiceAction;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Tarek
 */
public class addAction extends BaseForm{
    private String im ;
     public addAction(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        //TextField id = new TextField("", "ID", 20, TextField.NUMERIC); 
        TextField name = new TextField("", "Name", 20, TextField.ANY);
        TextField date = new TextField("", "Date", 20, TextField.ANY);
        TextField location = new TextField("", "Location", 20, TextField.ANY);
        TextField nbv = new TextField("", "NbV", 20, TextField.NUMERIC);
        TextField description = new TextField("", "Description", 20, TextField.ANY);
        TextField image = new TextField("", "Image", 20, TextField.ANY);
        
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
        Button img = new Button("Upload",icone);
        img.addActionListener((e)->{
            
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want

                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    im =fileNameInServer ;
                    image.setText(im);
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch ( IOException ex) {
                    
                }

        });

        //TextField image = new TextField("", "Image", 20, TextField.ANY);
        //TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        //TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        //id.setSingleLineTextArea(false);
        name.setSingleLineTextArea(false);
        date.setSingleLineTextArea(false);
        location.setSingleLineTextArea(false);
        nbv.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
        image.setSingleLineTextArea(false);
        Button next = new Button("Next");
        next.getAllStyles().setPadding(0, 0, 16, 16);
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
         next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                if ((tfName.getText().length()==0)||(tfStatus.getText().length()==0))
//                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
//                else
//                {
                    try {
                        Action t = new Action();

                        if (image.getText()=="") {
                            t = new Action(15,name.getText(), date.getText(), location.getText(), Integer.parseInt(nbv.getText()), description.getText(), "5e8563e9429d0_single_blog_2.png");                           
                        }else{
                            t = new Action(15,name.getText(), date.getText(), location.getText(), Integer.parseInt(nbv.getText()), description.getText(),image.getText());                    
                        }
                         if( ServiceAction.getInstance().addAction(t))
                        {
                            displayAction c2 = new displayAction(res);
                            c2.show();
                            
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
//            }
        });
        
        
        Container content = BoxLayout.encloseY(
                new Label("Add Action", "LogoLabel"),
               // new FloatingHint(id),
                //createLineSeparator(),
                new FloatingHint(name),
                createLineSeparator(),
                new FloatingHint(date),
                createLineSeparator(),
                new FloatingHint(location),
                createLineSeparator(),
                new FloatingHint(nbv),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(image),
                //new FloatingHint(image),
                createLineSeparator()
        );
        Container r = new Container(BoxLayout.x());
        r.addAll(next,img);
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                r,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        
    }
    
}
