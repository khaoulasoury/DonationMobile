
package Gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import Entities.Command;

import Services.ServiceCommand;

import java.util.ArrayList;


public class AddCommand  extends BaseForm  {
     Form f;

    com.codename1.io.File file;
    String fileName;
    Image videImg;
//    public static int idUser = User.user.getId();

    TextField tf_nom;
    TextField tf_desc;
    TextField tf_effectif;
    TextField tf_domaine;
   // Label lblemailtest;
    Label testVide;
      
         LocalNotification n;
    public AddCommand(Resources res)  {
        super("Donation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        //tb.addSearchCommand(e -> {});




        Tabs swipe = new Tabs();


        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("donation.jpg"), spacer2, "", "", "Bless Others With Your Gift ");
        addTab(swipe, res.getImage("donation.jpg"), spacer1 , "", "", "Bless Others With Your Gift ");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

AffichageProduct afp=new AffichageProduct(res);
//        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });


        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Actions", barGroup);
        all.setUIID("SelectBar");
        all.addActionListener(e ->
                new displayAction(res).show());
        RadioButton featured = RadioButton.createToggle("Events", barGroup);
        featured.setUIID("SelectBar");
        featured.addActionListener(e ->
                new Events(res).show());
        RadioButton popular = RadioButton.createToggle("Volunteers", barGroup);
        popular.addActionListener(e ->
                new ListOffers(res).show());
        popular.setUIID("SelectBar");
        popular.getSelectedStyle().setBackgroundGradientStartColor(0x07909b);
        RadioButton myFavorite = RadioButton.createToggle("Products", barGroup);
        myFavorite.setUIID("SelectBar");
        myFavorite.addActionListener(e ->
                new AffichageProduct(res).show());
        RadioButton aws = RadioButton.createToggle("Posts", barGroup);
        aws.setUIID("SelectBar");
        aws.addActionListener(e ->
                new PostForm(res).show());
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(5, all, featured, popular, myFavorite,aws),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        ServiceCommand es = new ServiceCommand();
      
        Resources theme = UIManager.initFirstTheme("/theme");
        
        Image back;
   
         getToolbar().addCommandToOverflowMenu("Display Command",videImg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {


                    AffichageCommand add=new AffichageCommand();
                    add.show();

            }
        });
        
       
      
        


       
        ServiceCommand prodServ = new ServiceCommand();

        //-------------------------------Components-------------------------------------
        tf_nom = new TextField("", "quantity", 20, TextArea.ANY);
        tf_desc = new TextField("", "paid:0 if you don't paid 😉😉 1 if you paid", 20, TextArea.ANY);
       
       // tf_effectif=new TextField("", "date", 20, TextArea.ANY);
        
         
       tf_nom.getAllStyles().setFgColor(0x000000);
          tf_desc.getAllStyles().setFgColor(0x000000);

        
        add(tf_nom);
        add(tf_desc);
     
        Button add = new Button("Add");
        Button nonparticiper = new Button("Remove Command");
             add(nonparticiper);
            nonparticiper.setVisible(false);


        add.addActionListener(new ActionListener() {
            private String textAttachmentUri;
            private String imageAttachmentUri;
            
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                          
                try {  
                Command e = new Command();
             if (Integer.parseInt(tf_nom.getText())<0  ||Integer.parseInt(tf_desc.getText())<0
                     ){   
                     Dialog.show("ERROR", "Please write something positive", "OK", null);
                     
}else{
                 if(Integer.parseInt(tf_desc.getText())!=1||Integer.parseInt(tf_desc.getText())<=0)
                     {Dialog.show("ERROR", "Please write something correct", "OK", null);
                     }
                 else{
                e.setQuantity_Product(Integer.parseInt(tf_nom.getText()));
                
                e.setPaid(Integer.parseInt(tf_desc.getText()));
               
                 e.setId_Product(afp.sproduct.prod.getId_Product()+939);
                   prodServ.ParticipCommand(e);
                   nonparticiper.setVisible(true);
                     add.setVisible(false);
                     //  SMSAPI sms = new SMSAPI("hello  "+tf_nom.getText()+"  successful purchase  ", "+21628183067");

            
}}
            
                 
                            
             
             } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }


           }
            
        });

        
        add(add);
           
             ServiceCommand sc=new ServiceCommand();
                

        ArrayList<Command> comms = sc.getListCommands();
        
            for(int k= comms.size() - 1; k >= 0; k--){
                
                Container commz = new Container(BoxLayout.x());
                Command currcomm = comms.get(k);

      nonparticiper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                                if(Dialog.show("delete", "Are you sure?", "OK", "Cancel")){
                                    //System.out.println(currcomm.getId_Command()); 
                                    int kh=currcomm.getId_Command()+1;
                                     System.out.println(kh);
                    ConnectionRequest con = new ConnectionRequest();
             con.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/products/DeleteCommandeMobile/"+kh);
                            System.out.println("The order has been canceled.");
                            ToastBar.showMessage("The order has been canceled",FontImage.MATERIAL_DONE);

                        
                    
                    
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    nonparticiper.setVisible(false);
                    add.setVisible(true);
 
                                }
                                
                            }
                            });
          break;
                        }
                        
    }
     private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }

}

