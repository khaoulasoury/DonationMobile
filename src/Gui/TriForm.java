
package Gui;


import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import Services.ServiceProduct;
import java.io.IOException;


public class TriForm extends BaseForm{
     
        Form f;
    
    
     private Form current;
    private Resources theme;

    
    
     Button btnrech;
      TextField rtitre;
       SpanLabel lb;
        Button btnaff;
       
   public TriForm() {

             super("Newsfeed", BoxLayout.y());
        Resources res = UIManager.initFirstTheme("/theme");
          
         // Toolbar tb = new Toolbar(true);
           Toolbar tb = getToolbar();
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
          
        
        Tabs swipe = new Tabs();
 
        Label spacer1 = new Label();
        Label spacer2 = new Label();
     
         addTab(swipe, res.getImage("donation.jpg"), spacer1, "  ", "", " ");
        //addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
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

       myFavorite.setSelected(true);
        arrow.setVisible(false);
        
           
                 

          btnrech=new Button("Search");
         rtitre = new TextField("","Name");
         rtitre.getAllStyles().setFgColor(0x000000);
          lb = new SpanLabel("");
          
         
         
        add(rtitre);
        
        add(btnrech);
         add(lb);
         
        Image back;


          
        btnrech.addActionListener((e)->{
        
        if(rtitre.getText().equalsIgnoreCase("") ){
            
            
             Dialog.show("alert","Please, try to fill the text field title !!", "ok", null);
                 ;}
                         else{
        ServiceProduct ser=new ServiceProduct();
        lb.setText(ser.Recherche(rtitre.getText()).toString());
        }});
        

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
      

   /* public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }*/
    
    
}
