

package Gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import Entities.Comment;
import Entities.Fos_user;
import Entities.View;
import Services.ServiceComment;
import Services.ServiceFos_user;
import Services.ServiceImage;
import Services.ServiceView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class PostForm extends BaseForm {
    private String im ;
    public Entities.Image image;
    public View view;
    EncodedImage encImg = EncodedImage.createFromImage(Image.createImage(750, 750/3, 0xffff0000), true);
    EncodedImage encImg2 = EncodedImage.createFromImage(Image.createImage(450, 450/3, 0xffff0000), true);
    Image imge;
    ImageViewer imgV;

    public PostForm(Resources res) {
        super("Donation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        //tb.addSearchCommand(e -> {});
        tb.addCommandToOverflowMenu("Add Activity", null, (ei) -> {
            Addactivity w = new Addactivity(res);
            w.show();
        });
        tb.addCommandToOverflowMenu("Display Activity", null, (ei) -> {
            Activities w = new Activities();
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
        
        aws.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        TextField tfText = new TextField("","Your Text Here...");
        tfText.getAllStyles().setFgColor(0x00000);
        Button img = new Button("Uplaod Your Image");
        Button btnValider = new Button("Post");
        Container images = new Container(BoxLayout.xCenter());
        
        
        Container post = BoxLayout.encloseY(
                new FloatingHint(tfText),
                createLineSeparator(),
                img,
                images,
                createLineSeparator(),
                btnValider
        );
        
        img.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
               try {
                String fileNameInServer = "";
                MultipartRequest cr = new MultipartRequest();
                String filepath = Capture.capturePhoto(-1, -1);
                cr.setUrl("http://localhost/uploadimage.php");
                cr.setPost(true);
                String mime = "image/jpeg";
                cr.addData("file", filepath, mime);
                String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                cr.setFilename("file", out + ".jpg");//any unique name you want
                fileNameInServer += out + ".jpg";
                System.err.println("path2 =" + fileNameInServer);
                im = fileNameInServer ;
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                images.removeAll();
                imgV = new ImageViewer();
                String url = "http://localhost/imageBonPlan/" + im;
                //System.out.println(url);
                imge=URLImage.createToStorage(encImg2, url,url);
                imgV = new ImageViewer(imge);
                images.add(imgV);
                
                } catch (IOException ex) {
                }
            }
            });
        
        add(post);
        //action on posting
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    
                    //Integer.parseInt(tfTel.getText()) fi blaset 2 dyn
                    View v = new View(tfText.getText(),2);
                    if(StringUtil.replaceAll(tfText.getText()," ","").equals("") && im == null){
                        Dialog.show("ERROR", "Please write something or upload an image before posting", "OK", null);
                    }else if(!StringUtil.replaceAll(tfText.getText()," ","").equals("") && im == null){
                        //System.out.println("slm1");
                        ServiceView.getInstance().addViews(v);
                        new PostForm(res).show();
                    }else if(im != null){
                        //System.out.println("slm2");
                        ServiceView.getInstance().addViews(v);
                        ArrayList<View> lv = ServiceView.getInstance().getAllViews();
                        int idv = lv.get(lv.size() - 1).getId_view();
                        image = new Entities.Image(idv, im);
                        ServiceImage.getInstance().addImage(image);
                        im=null;
                        images.removeAll();
                        new PostForm(res).show(); 
                    }
                    else
                        Dialog.show("ERROR", "Server error", "OK", null);
                    tfText.setText(null);  
                }
                });
        
        Container c = new Container(BoxLayout.y());
        ArrayList<Fos_user> users = ServiceFos_user.getInstance().getAllUsers();
        ArrayList<View> v = ServiceView.getInstance().getAllViews();
        
        //*****Posting section*****
        for(int i= v.size() - 1; i >= 0; i--){
            Container cdel = new Container(new BorderLayout());
            Button del1 = new Button(res.getImage("cross.png").scaled(50, 50), "Delete");

            Container c1 = new Container(BoxLayout.y());
            View currview = v.get(i);
            Label userNamePost = new Label();
            Label userTextPost = new Label();
            
            //System.out.println(v.get(i).getText().substring(0,1).toUpperCase());
            Fos_user usrById = ServiceFos_user.getInstance().FindUserId(v.get(i).getId_user());
            userNamePost.setText(ServiceFos_user.getInstance().userById.getUsername().substring(0,1).toUpperCase() + ServiceFos_user.getInstance().userById.getUsername().substring(1));
            userNamePost.getAllStyles().setFgColor(0x0227f5);
            if(!v.get(i).getText().equals("")){
                userTextPost.setText(v.get(i).getText().substring(0,1).toUpperCase() + v.get(i).getText().substring(1));
            }
            userTextPost.getAllStyles().setFgColor(0x000000);
            if (v.get(i).getId_user() != ServiceFos_user.getInstance().user.getId()){
                //System.out.println(v.get(i));
                //System.out.println(ServiceFos_user.getInstance().user.getId());
                c1.add(userNamePost);
            }
            else{
                cdel.add(BorderLayout.WEST, userNamePost);
                cdel.add(BorderLayout.EAST, del1);
                c1.add(cdel);
                //*****Post delete listener*****
                        del1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                                if(Dialog.show("ERROR", "Are you sure you want to delete the post?", "OK", "Cancel")){
                                    ServiceView.getInstance().deleteView(currview.getId_view());
                                    new PostForm(res).show();
                                }
                            }
                            });
            }
            c1.add(userTextPost);
            Container c2 = new Container(BoxLayout.x());//images
            Container c3 = new Container(BoxLayout.y());
            Container c4 = new Container(BoxLayout.y());//comments
            c1.setUIID("slm");
            c1.getAllStyles().setBgColor(0xbdbab1);
            int idv = v.get(i).getId_view();
            TextField tfComment = new TextField("","Your Comment Here...");
            tfComment.getAllStyles().setFgColor(0x00000);
            Button btnComment = new Button("Comment");
            for(Entities.Image imgg : ServiceImage.getInstance().getAllImages()){
                if(imgg.getId_view() == v.get(i).getId_view()){
                    //System.out.println(imgg.getName());
                    imgV = new ImageViewer();
                    String url = "http://localhost/imageBonPlan/" + imgg.getName();
                    //System.out.println(url);
                    imge=URLImage.createToStorage(encImg, url,url);
                    imgV = new ImageViewer(imge);
                    c2.add(imgV);
                }  
            }
            c1.add(c2);
            c.add(c1);
            c3.addAll(tfComment,btnComment);
            //action on commenting
            btnComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    
                    //Integer.parseInt(tfTel.getText()) fi blaset 2 dyn
                    Comment com = new Comment(tfComment.getText(), idv);
                    //addButton(res.getImage("cross.png"), "Delete", false, 0, 0);
                    if(StringUtil.replaceAll(tfComment.getText()," ","").equals("")){
                        Dialog.show("ERROR", "Please write something before commenting", "OK", null);
                    }else if( ServiceComment.getInstance().addComment(com) ){
                        new PostForm(res).show();
                    } 
                    else
                        Dialog.show("ERROR", "Server error", "OK", null);
                    tfComment.setText(null);
                }
                });
            ArrayList<Comment> comms = ServiceComment.getInstance().getAllComments();
            ComponentGroup commzl = new ComponentGroup();

            for(int k= comms.size() - 1; k >= 0; k--){
                Container commz = new Container(new BorderLayout());
                Comment currcomm = comms.get(k);
                if(v.get(i).getId_view() == comms.get(k).getId_view()){
                    //System.out.println("comm: " + comms.get(k).getText_comment());
                    Button del = new Button(res.getImage("cross.png").scaled(50, 50), "Delete");
                    
                    if(comms.get(k).getId_user() == ServiceFos_user.getInstance().user.getId()){
                        SpanLabel lbl = new SpanLabel(comms.get(k).getText_comment().substring(0,1).toUpperCase()+ comms.get(k).getText_comment().substring(1));
                        lbl.getTextAllStyles().setFgColor(0x000000);
                        //*****commentary section*****
                        commz.add(BorderLayout.WEST, lbl);
                        commz.add(BorderLayout.EAST, del);
                        commzl.add(commz);

                        //Comment deletelistener
                        del.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                                if(Dialog.show("ERROR", "Are you sure?", "OK", "Cancel")){
                                    ServiceComment.getInstance().deleteComment(currcomm.getId_comment());
                                    new PostForm(res).show();
                                }
                            }
                            });
                        }
                    else{
                        //*****commentary section(no delete button)*****
                        SpanLabel lbl = new SpanLabel(comms.get(k).getText_comment().substring(0,1).toUpperCase()+ comms.get(k).getText_comment().substring(1));
                        lbl.getTextAllStyles().setFgColor(0x000000);
                        commz.add(BorderLayout.WEST, lbl);                    
                        commzl.add(commz);
                        }
                                            
                }
                
            }
            c3.add(commzl);
            c.add(c3);
        }
        
        add(c);
        //System.out.println(ServiceView.getInstance().getAllViews().get(0).getText());
//        addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
//        addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
//        addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
//        addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
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
    
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
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
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
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
