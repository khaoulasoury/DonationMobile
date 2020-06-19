
package Gui;

import Entities.Activity;
import Services.ServiceActivity;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.io.ConnectionRequest;

public class updateActivity extends Form {
    Form current;
    private Home homePage;
    private Activities Page;


    public updateActivity(Activity quest ) {

        this.getToolbar().setBackCommand("Back", e -> {
            homePage.showBack();
        });

        setTitle("Update an Activity");
        setLayout(BoxLayout.y());

        TextField tfName = new TextField(quest.getNameAc(),"Activity Name");
        TextField tfduration= new TextField (String.valueOf(quest.getDuration()), "Duration");
        TextField tfType = new TextField(quest.getTypeAc(),"Activity Type");
        TextField tfdescription= new TextField(quest.getDescriptionAc(), "Activity Description");


        Button btnValider = new Button("Modify Activity");

        btnValider.addActionListener(e-> {
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            String Url = "http://localhost/taz/UpdateProfile.php";// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.setPost(false);
        con.addArgument("id_ac",Integer.toString(quest.getIdAc()));
        con.addArgument("Name_ac",tfName.getText());
        con.addArgument("Duration",tfduration.getText());
        con.addArgument("Description_ac",tfType.getText());
        con.addArgument("type_ac",tfdescription.getText());
        con.addResponseListener((v) -> {
                String str = new String(con.getResponseData());//Récupération de la réponse du serveur
                System.out.println(str);//Affichage de la réponse serveur sur la console

            });
        NetworkManager.getInstance().addToQueueAndWait(con);
            ToastBar.showMessage("update complete", FontImage.MATERIAL_INFO);
            Page = new Activities();
            Page.show();

        });

        addAll(tfName,tfduration,tfType,tfdescription,btnValider);


    }


}