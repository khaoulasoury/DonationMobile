
package Gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import Entities.Command;
import Entities.Product;
import Services.ServiceCommand;
import Services.ServiceProduct;
import java.util.ArrayList;
import java.util.List;



public class AffichageCommand  extends BaseForm{
    
    Form f;
    ImageViewer ip;
    List<Command> lse = new ArrayList();
    ArrayList<Command> form;
    Image videImg;

    public AffichageCommand() {
       super("Commands", BoxLayout.y());
        Resources res = UIManager.initFirstTheme("/theme");
        Toolbar tb = getToolbar();
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
          

    
        Slider slider = new Slider();
        slider.setPreferredSize(new Dimension(256, 2));
        add(slider);
     
         getToolbar().addCommandToOverflowMenu("Display Command",videImg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {


                    AffichageCommand add=new AffichageCommand();
                    add.show();

                    
                }

        });
        Image back;

            AffichageProduct myp = new AffichageProduct(res);
                myp.showBack();



             
        lse = new ServiceCommand().getListCommands();
        for (int i = 0; i < lse.size(); i++) {
        
            addItem(lse.get(i));
         
        
              Button participer = new Button("Command N:"+lse.get(i).getId_Command());
     
             add(participer);
            
            
                   
                
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
   

    

    public void addItem(Command e) {

        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          ServiceProduct sp=new ServiceProduct();
                ServiceCommand sc=new ServiceCommand();
        ArrayList<Product> Products = sp.getListProducts();
        ArrayList<Command> v =sc.getListCommands();
            System.out.println("display command");
          Label nomproduct = new Label();
           for(int j=Products.size()-1;j>=0;j--){
                
                 //System.out.println(i);
              System.out.println(e.getId_Product());
                System.out.println(Products.get(j).getId_Product());
                System.out.println("");
                if (e.getId_Product()==Products.get(j).getId_Product()){
                    
                        nomproduct.setText("Product'name: "+Products.get(j).getName_Product());
                
                }
    }
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l2 = new Label("paid:"+e.getPaid());
        Label l = new Label("date:" +e.getDate_Command());
        Label lq=new Label("Quantity: "+e.getQuantity_Product()+"items");
        l.getAllStyles().setFgColor(0x000000);
        l2.getAllStyles().setFgColor(0x000000);
        lq.getAllStyles().setFgColor(0x000000);
        nomproduct.getAllStyles().setFgColor(0x000000);
        c2.add(l);
   
        c2.add(l2);
        c2.add(lq);
        c2.add(nomproduct);
        c1.add(c2);
        refreshTheme();

      add(c1);

    
}}
