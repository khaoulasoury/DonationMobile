

package Gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.regex.RE;
import Entities.Fos_user;
import Services.ServiceFos_user;
import org.mindrot.jbcrypt.BCrypt;


public class SignUpForm extends BaseForm {
    public Fos_user u;
    public static SignUpForm instance=null;


    
    public boolean validateEmailAddress(String emailAddress) {
    RE pattern = new RE("^[(a-zA-Z-0-9-\\\\_\\\\+\\\\.)]+@[(a-z-A-z)]+\\\\.[(a-zA-z)]{2,3}$$");
        return pattern.match(emailAddress);
    }

    public SignUpForm(Resources res) {
        super(new BorderLayout());

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField username = new TextField("", "* Username", 20, TextField.ANY);
        TextField email = new TextField("", "* E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "* Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "* Confirm Password", 20, TextField.PASSWORD);
        TextField tel = new TextField("", "* Telephone", 20, TextField.PHONENUMBER);
        TextField address = new TextField("", "Address", 20, TextField.ANY);
        tel.setSingleLineTextArea(false);
        address.setSingleLineTextArea(false);
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        Button signI = new Button("Sign Up");
        signI.addActionListener(e -> new addAssociation(res).show());
        signI.setUIID("Link");
        Label yourare = new Label("you are an Association?");


        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                new FloatingHint(tel),
                createLineSeparator(),
                new FloatingHint(address),
                createLineSeparator()

        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(yourare, signI)
        ));
        next.requestFocus();
        next.addActionListener(e -> {
            if (username.getText().equals("") || email.getText().equals("") || password.getText().equals("") || confirmPassword.getText().equals("") || tel.getText().equals(""))
                Dialog.show("alert", "Please Fill all the obligatory fields", "OK", null);
            else if(password.getText().length() < 5){
                Dialog.show("alert", "Password has to have 5 or more caracters", "OK", null);
                password.setText("");
                confirmPassword.setText("");
            }
            else if(!email.getText().contains("@") || !email.getText().contains(".")){
                Dialog.show("alert", "Wrong email pattern", "OK", null);
            }
            else if(!password.getText().equals(confirmPassword.getText())){
                Dialog.show("alert", "Password mismatch", "OK", null);
                password.setText("");
                confirmPassword.setText("");
            }   
            else if(tel.getText().equals("")){
                Dialog.show("alert", "Please Fill all the obligatory fields", "OK", null);
            }   
            else if(tel.getText().length() < 8){
                Dialog.show("alert", "Please verify telephone number", "OK", null);
            }   
            
            else{
               String hashed = BCrypt.hashpw(password.getText(), BCrypt.gensalt(5));
                String hashedd = "$2y" + hashed.substring(3);
                System.out.println("hashed is: " + hashed);
                u = new Fos_user(username.getText(),email.getText(),hashedd,Integer.parseInt(tel.getText()),address.getText());
                System.out.println(u.toString());
                if( ServiceFos_user.getInstance().addUsers(u)){
                    Dialog.show("Success","User added",new Command("OK"));
                   // new SignInForm(res).show();
                    new displayAction(res).show();
                }
                else
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                
            }
        });
    }
    
}
