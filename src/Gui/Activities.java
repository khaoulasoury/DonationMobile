package Gui;


import com.codename1.io.ConnectionRequest;
import com.codename1.components.ImageViewer;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import Services.ServiceActivity;
import Entities.Activity;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.time.format.DateTimeFormatter;



public class Activities extends Form  {
    private Home homePage;
    private Resources theme;
    private Container eventsContainer;
    private String searchText = "";
    private boolean joinedOnly = false;
    private Container categoryBtnsList;
    private ButtonGroup categoryBtnsGroup;
    private Container sortByContainer;
    private Label sortByText;
    private ComboBox<String> sortBy;
    private DateTimeFormatter dateFormatter;
    private ActionListener joinListener;
    private ActionListener leaveListener;
    private Activity quest;
    private Home Page;
    public Activities()  {

        getToolbar().setBackCommand("Back", e -> {
           new Events(theme).show();

        });

        setTitle("List Activities");
        Container a = new Container(BoxLayout.x());
        Button Ajout = new Button("Add");
        Ajout.addActionListener(ee->{
            new Addactivity(theme).show();
        });

        Container optt = new Container(BoxLayout.x());
        optt.add(Ajout);
        a.add(optt);
       // SpanLabel sp = new SpanLabel();
        //sp.setText("Event Name: " + ServiceActivity.getInstance().toString());

       // sp.setText(ServiceActivity.getInstance().getAllTasks().toString());
        //add(sp);
        for (Activity q : ServiceActivity.getInstance().getAllTasks()) {
            System.out.println(q.getIdAc());
            add(addActivity(q));
        }
        this.getToolbar().addSearchCommand(e -> {
            searchText = (String) e.getSource();

        });
    }
        public Container addActivity (Activity quest)
        {

            Container holder = new Container(BoxLayout.x());
            Container details = new Container(BoxLayout.y());
            Label lbTitre = new Label("Nom : "+quest.getNameAc());
            Label lbDesc  = new Label("Description : "+quest.getDescriptionAc());
            ImageViewer image = new ImageViewer(Image.createImage(10, 200, 0xff0000ff));
            holder.add(image);
            details.add(lbTitre);
            details.add(lbDesc);
            holder.add(details);

            Button update = new Button("update");
            update.addActionListener(ee->{
               new updateActivity(quest).show();
            });

            Button delete = new Button("delete");
            delete.addActionListener(ee->{
                ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
                String Url = "http://localhost/taz/DeleteQ.php";// création de l'URL
                con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
                con.setPost(false);
                con.addArgument("id_ac",Integer.toString(quest.getIdAc()));
                con.addResponseListener((v) -> {
                    String str = new String(con.getResponseData());//Récupération de la réponse du serveur
                    System.out.println(str);//Affichage de la réponse serveur sur la console
                });
                NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
                new Activities().show();
            });

            Container opt = new Container(BoxLayout.x());
            opt.addAll(update,delete);
            details.add(opt);
            return holder ;
        }
        }
