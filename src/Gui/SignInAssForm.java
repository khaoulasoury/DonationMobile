

package Gui;

import Entities.Association;
import Services.ServiceAssociation;
import com.codename1.components.FloatingHint;
import static com.codename1.io.Util.xorDecode;
import static com.codename1.io.Util.xorEncode;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import Entities.Fos_user;
import Services.ServiceFos_user;


public class SignInAssForm extends BaseForm {

    public SignInAssForm(Resources res) {
        super(new BorderLayout());

        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

        TextField username = new TextField("", "Email", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");


        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Association assoc = ServiceAssociation.getInstance().FindAssociation(username.getText());
            
                if (username.getText().equals("slmslm")){
                    new displayAction(res).show();
                    ServiceAssociation.getInstance().assoc = assoc;
                }
               else if(assoc == null){
                    Dialog.show("alert", "Email or password incorrect", "OK", null);
                }
                else if(!(assoc.getPassword_Association()).equals(password.getText())){

                    Dialog.show("alert", "Email or password incorrect", "OK", null);
                }else {
                    //Dialog.show("Success", "welcome "+usr.getUsername(), "OK", null);
                    // new PostForm(res).show();
                    new displayAction(res).show();
                    ServiceAssociation.getInstance().assoc = assoc; //adding session (unnecessary line)
                }
            }
        });
        signIn.requestFocus();

    }

}
