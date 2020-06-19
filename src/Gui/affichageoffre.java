
package Gui;

import Entities.Offre;
import Services.ServicesOffre;
import com.codename1.components.ImageViewer;
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
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class affichageoffre extends BaseForm{

    
    Form f;
    ImageViewer ip;
    List<Offre> lse = new ArrayList();
    ArrayList<Offre> form;
    Image videImg;

    public affichageoffre(Resources res) {
        super("Donation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Donation");
        getContentPane().setScrollVisible(false);


        //tb.addSearchCommand(e -> {});
        tb.addCommandToOverflowMenu("Add Action", null, (ei) -> {
            addAction w = new addAction(res);
            w.show();
        });
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

        rbs[0].setSelected(true);
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
        all.getSelectedStyle().setBackgroundGradientStartColor(0x07909b);
        RadioButton featured = RadioButton.createToggle("Events", barGroup);
        featured.setUIID("SelectBar");
        featured.addActionListener(e ->
                new Events(res).show());
        RadioButton popular = RadioButton.createToggle("Volunteers", barGroup);
        popular.setUIID("SelectBar");
        popular.addActionListener(e ->
                new ListOffers(res).show());
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

        Slider slider = new Slider();
        slider.setPreferredSize(new Dimension(256, 2));
        add(slider);
     
         getToolbar().addCommandToOverflowMenu("Display Offre",videImg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {


                    affichageoffre add=new affichageoffre(res);
                    add.show();

            }
        });
        Image back;
       

              

             
        lse = new ServicesOffre().getAllOffres();
        for (int i = 0; i < lse.size(); i++) {
        
           // addItem(lse.get(i));
         
         ServicesOffre prodServ=new ServicesOffre();
              Button participer = new Button("delete");
add(participer);
        ArrayList<Offre> comms = prodServ.getAllOffres();
        
            for(int k= comms.size() - 1; k >= 0; k--){
                
                Container commz = new Container(BoxLayout.x());
                Offre currcomm = comms.get(k);
               participer.getAllStyles().setPadding(0, 0, 16, 16);
         participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           
                           Offre t = new Offre();
                      
                                    int kh=currcomm.getId();
                                     System.out.println(kh);
                    ConnectionRequest con = new ConnectionRequest();
             con.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/offre/DeleteOffreMobile/"+kh);
                            System.out.println("The order has been canceled.");

con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    
 
                                }
                                
                            
                            });
          break;
                        }
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                ServicesOffre sc=new ServicesOffre();
        ArrayList<Offre> v =sc.getAllOffres();
            System.out.println("display Offre");
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l2 = new Label("type:"+lse.get(i).getType());
        Label l = new Label("contact:" +lse.get(i).getContact());
        Label lq=new Label("state: "+lse.get(i).getState());
        l.getAllStyles().setFgColor(0x000000);
        l2.getAllStyles().setFgColor(0x000000);
        lq.getAllStyles().setFgColor(0x000000);
        c2.add(l);
        c2.add(l2);
        c2.add(lq);
        c1.add(c2);
        refreshTheme();

      add(c1);
            
                   
                
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
   

    

  /*  public void addItem(Offre e) {

        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                ServicesOffre sc=new ServicesOffre();
        ArrayList<Offre> v =sc.getAllOffres();
            System.out.println("display Offre");
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l2 = new Label("type:"+e.getType());
        Label l = new Label("contact:" +e.getContact());
        Label lq=new Label("state: "+e.getState());
        l.getAllStyles().setFgColor(0x000000);
        l2.getAllStyles().setFgColor(0x000000);
        lq.getAllStyles().setFgColor(0x000000);
        c2.add(l);
        c2.add(l2);
        c2.add(lq);
        c1.add(c2);
        refreshTheme();

      add(c1);

    
}*/
}
   

