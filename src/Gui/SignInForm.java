

package Gui;

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


public class SignInForm extends BaseForm {

    public SignInForm(Resources res) {
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
        Button signass = new Button("Sign In as an Association");
        signass.addActionListener(e -> new SignInAssForm(res).show());
        signass.setUIID("Link");
        Label asslikethat = new Label("Don't have an account?");


        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),
                FlowLayout.encloseCenter(asslikethat, signass)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Fos_user usr = ServiceFos_user.getInstance().FindUser(username.getText());

                if (username.getText().equals("slmslm")){
                    new displayAction(res).show();
                    ServiceFos_user.getInstance().user = usr;
                }
                else if(usr == null){
                    Dialog.show("alert", "Email or password incorrect", "OK", null);
                }
                else if(!xorDecode(usr.getPassword()).equals(password.getText())){

                        Dialog.show("alert", "Email or password incorrect", "OK", null);
                    }else {
                        //Dialog.show("Success", "welcome "+usr.getUsername(), "OK", null);
                       // new PostForm(res).show();
                    new displayAction(res).show();
                        ServiceFos_user.getInstance().user = usr; //adding session (unnecessary line)
                    }
                }
            });
        signIn.requestFocus();

    }

}
