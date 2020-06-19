package Gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Entities.Activity;
import Services.ServiceActivity;
import com.codename1.ui.util.Resources;


public class Addactivity extends Form{
    Form current;
    private Home homePage;
    private Activities Page;
    private Resources theme;

    public Addactivity( Resources theme) {
        this.homePage = new Home(theme);
        this.getToolbar().setBackCommand("Back", e -> {
            homePage.showBack();
        });

        setTitle("Add a new Activity");
        setLayout(BoxLayout.y());

        TextField tfName = new TextField("","Activity Name");
        TextField tfduration= new TextField("", "Duration");
        TextField tfType = new TextField("","Activity Type");
        TextField tfdescription= new TextField("", "Activity Description");


        Button btnValider = new Button("Add Activity");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfduration.getText().length()==0)||(tfdescription.getText().length()==0)||(tfType.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Activity t = new Activity(tfName.getText(),Float.parseFloat(tfduration.getText()),tfdescription.getText(),tfType.getText());
                        if( ServiceActivity.getInstance().addTask(t)) {
                            Page = new Activities();
                            Page.show();
                            Dialog.show("Success", "Connection accepted", new Command("OK"));

                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfName,tfduration,tfType,tfdescription,btnValider);
      //  btnValider.addActionListener(e-> new Activities().show());
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }


}
