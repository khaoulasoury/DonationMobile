package Gui;

import Entities.Action;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.rest.Rest;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import Entities.Event;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;

public class Events extends BaseForm {
    private Resources theme;
    private Home homePage;
    private String searchText = "";
    private boolean joinedOnly = false;
    private Container categoryBtnsList;
    private ButtonGroup categoryBtnsGroup;
    private Container sortByContainer;
    private Label sortByText;
    private ComboBox<String> sortBy;
    private DateTimeFormatter dateFormatter;
    private Container eventsContainer;
    private ActionListener joinListener;
    private ActionListener leaveListener;

    public Events(Resources theme) {
        super("Donation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Donation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(theme);
        //tb.addSearchCommand(e -> {});
        tb.addCommandToOverflowMenu("Add Activity", null, (ei) -> {
            Addactivity w = new Addactivity(theme);
            w.show();
        });
        tb.addCommandToOverflowMenu("Display Activity", null, (ei) -> {
            Activities w = new Activities();
            w.show();
        });
        Tabs swipe = new Tabs();


        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("donation.jpg"), spacer2, "", "", "Bless Others With Your Gift ");
        addTab(swipe, theme.getImage("donation.jpg"), spacer1 , "", "", "Bless Others With Your Gift ");

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
                new displayAction(theme).show());
        all.getSelectedStyle().setBackgroundGradientStartColor(0x07909b);
        RadioButton featured = RadioButton.createToggle("Events", barGroup);
        featured.setUIID("SelectBar");
        featured.addActionListener(e ->
                new Events(theme).show());
        RadioButton popular = RadioButton.createToggle("Volunteers", barGroup);
        popular.setUIID("SelectBar");
        popular.addActionListener(e ->
                new ListOffers(theme).show());
        RadioButton myFavorite = RadioButton.createToggle("Products", barGroup);
        myFavorite.setUIID("SelectBar");
        myFavorite.addActionListener(e ->
                new AffichageProduct(theme).show());
        RadioButton aws = RadioButton.createToggle("Posts", barGroup);
        aws.setUIID("SelectBar");
        aws.addActionListener(e ->
                new PostForm(theme).show());
        Label arrow = new Label(theme.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(5, all, featured, popular, myFavorite,aws),
                FlowLayout.encloseBottom(arrow)
        ));

        featured.setSelected(true);
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
        
        this.theme = theme;
        this.homePage = new Home(theme);
        this.getToolbar().setBackCommand("Back", e -> {
            homePage.showBack();
        });
        this.getToolbar().addSearchCommand(e -> {
            searchText = (String) e.getSource();
            feedEventsContainer();
        });
        categoryBtnsGroup = new ButtonGroup();


        categoryBtnsGroup.addActionListener(ev -> {
            feedEventsContainer();
        });
        sortByText = new Label("Sort by: ");
        sortBy = new ComboBox<>("None", "Date", "Distance", "Price");
        sortBy.addSelectionListener((int oldSelected, int newSelected) -> {
            if (sortBy.getSelectedItem().equals("Location") && LocationManager.getLocationManager().getStatus() != LocationManager.AVAILABLE) {
                ToastBar.Status toast = ToastBar.getInstance().createStatus();
                toast.setExpires(500);
                toast.setMessage("Location Service Is Unavailable, Please Enable It!");
                toast.show();
                sortBy.setSelectedIndex(oldSelected);
            }
            else if (oldSelected != newSelected) {
                feedEventsContainer();
            }
        });
        sortByContainer = new Container(new FlowLayout(RIGHT));
        sortByContainer.addAll(sortByText, sortBy);
        eventsContainer = new Container(BoxLayout.y());
        eventsContainer.setScrollableY(true);
        feedEventsContainer();
        this.add( eventsContainer);
    }

    public Container generateEventContainer(Event event) {
        Container eventContainer = new Container(BoxLayout.x());
        Button actionBtn = new Button();
        EventsDetails detailsPage = new EventsDetails(theme, (Events) this, event);
        NavigationCommand detailsCmd = new NavigationCommand("Details", FontImage.createMaterial(FontImage.MATERIAL_ADD_TO_QUEUE, actionBtn.getStyle()).toImage());
        detailsCmd.setNextForm(detailsPage);
        Button detailsBtn = new Button(detailsCmd);

        joinListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int responseCode = Rest
                        .post("http://localhost/donationWEB/web/app_dev.php/Donation/{idEv}")
                        .pathParam("id_event", event.getIdEv().toString())
                        .getAsString()
                        .getResponseCode();
                if (responseCode == 200) {
                    LocalNotification notification = new LocalNotification();
                    notification.setId(event.getIdEv().toString());
                    notification.setAlertTitle("Event Reminder");
                    notification.setAlertBody(event.getNameEv() + " will start in 1 hour");
                    notification.setAlertSound("/notification_sound_events.mp3");
                    Display.getInstance().scheduleLocalNotification(notification, System.currentTimeMillis() + event.getDateEv().getTime() - 3600000, LocalNotification.REPEAT_NONE);
                    ((Button) actionEvent.getComponent()).setText("Leave");
                    ((Button) actionEvent.getComponent()).removeActionListener(joinListener);
                    ((Button) actionEvent.getComponent()).addActionListener(leaveListener);
                }
            }

        };
        leaveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int responseCode = Rest
                        .post("http://localhost/donationWeb/web/app_dev.php/Donation/{idEv}")
                        .pathParam("id_event", event.getIdEv().toString())
                        .getAsString().getResponseCode();

            }
        };

        Container rightColumn = new Container(BoxLayout.y());
        Container commandsRow = new Container(BoxLayout.x());
        commandsRow.addAll(actionBtn, detailsBtn);
        rightColumn.addAll(new Label(event.getNameEv()), commandsRow);
        eventContainer.addAll(new ImageViewer(URLImage.createCachedImage("ei", "http://localhost/donationWEB/web/img/events/" + event.getPoster(),  Image.createImage(160, 120), URLImage.FLAG_RESIZE_SCALE_TO_FILL)), rightColumn);
        //poster = new ImageViewer(URLImage.createCachedImage("ei", "http://localhost/donationWEB/web/img/events/" + event.getPoster(), Image.createImage(1280, 720), URLImage.FLAG_RESIZE_SCALE_TO_FILL));

        return eventContainer;

    }
    public List<Event> fetchEvents() {
        List<Event> events = fetchEventsData();

       if (sortBy.getSelectedItem().equals("Distance") && LocationManager.getLocationManager().getStatus() == LocationManager.AVAILABLE) {
            Location currLocation = LocationManager.getLocationManager().getCurrentLocationSync();
            events.sort((Event e1, Event e2) -> {
                Location e1Location = new Location(e1.getLocationLatitude(), e1.getLocationLongitude());
                Location e2Location = new Location(e2.getLocationLatitude(), e2.getLocationLongitude());
                return Double.compare(e1Location.getDistanceTo(currLocation), e2Location.getDistanceTo(currLocation));
            });
        }

        return events;
    }
    public void feedEventsContainer() {
        //System.out.println(fetchEvents());
        eventsContainer.removeAll();
        fetchEvents().stream().map(this::generateEventContainer).forEach(component -> {
            eventsContainer.add(component);
        });
        eventsContainer.revalidate();
    }
    public List<Event> fetchEventsData() {
        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        List<Event> eventList = new ArrayList<>();
        ((List<Map<String, Object>>) Rest
            .get("http://localhost/donationWeb/web/app_dev.php/Donation/get")
            .acceptJson()
            .getAsJsonMap()
            .getResponseData()
            .get("root"))
            .forEach(item -> {
                Event event = new Event(
                        ((Integer) item.get("idEV")),
                        (String) item.get("nameEv"),
                        (Double) item.get("locationLongitude"),
                        (Double) item.get("locationLatitude"),
                        Date.from(ZonedDateTime.parse((String) item.get("dateEv"), dateFormatter).toInstant()),
                        (String) item.get("descriptionEv"),
                        (String) item.get("equipementEv"),
                        (String) item.get("poster"),
                        (String) item.get("typeEv")

                );
                eventList.add(event);
        });

        return eventList;
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

    private void addButton(Action a , Image img, String title, boolean liked, String likeCount, int commentCount) {
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
