
package Gui;

import Entities.Action;
import Services.ServiceAction;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;


public class displayAction extends BaseForm {
    private ServiceAction SA;
    private ArrayList<Action> actions;

    public displayAction(Resources res) {
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
        addShowListener(e -> {
            arrow.setVisible(false);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            arrow.setVisible(false);
        });
         SA = new ServiceAction();
        actions = new ArrayList<>();
        actions = SA.getAllActions();
        for(int i=0;i<actions.size();i++)
        {
        String urlimage = "http://localhost/donationWEB/web/uploads/associations/"+actions.get(i).getImage_name();
        EncodedImage enc3 = EncodedImage.createFromImage(res.getImage("dog.jpg"), false);
        Image image = URLImage.createToStorage(enc3,"actions"+actions.get(i).getId_Action()+".png", urlimage);
        addButton(actions.get(i),image,actions.get(i).getName_Action()+"\n"+actions.get(i).getDescription(), false, actions.get(i).getDate_Action(), actions.get(i).getNbV_Action());
        }
        arrow.setVisible(false);
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        arrow.setVisible(false);
        
    }
    
private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);


        Label comments = new Label(commentsStr);

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
   private void addButton(Action a ,Image img, String title, boolean liked, String likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       //cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + "", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       Label delete = new Label("Delete","NewsBottomLine");
       delete.addPointerPressedListener((e)->{
            if(ServiceAction.getInstance().deleteAction(a.getId_Action())){
            }
        });
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments ,delete)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
