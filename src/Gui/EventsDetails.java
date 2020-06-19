package Gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.contacts.Contact;
import com.codename1.contacts.ContactsManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import Entities.Event;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventsDetails extends Form {
    private Resources theme;
    private Events eventsPage;
    private ImageViewer poster;
    private SimpleDateFormat dateFormatter;
    private Label dateTimeStart;
    private SpanLabel description;
    private SpanLabel na;
    private SpanLabel ty;
    private SpanLabel eq;
    private Location currLocation;
    private BrowserComponent browser;
    private ShareButton shareBtn;
    private Dialog contactsList;
    public EventsDetails(Resources theme, Events eventsPage, Event event) {
        super(event.getNameEv(), BoxLayout.y());
        this.theme = theme;
        this.eventsPage = eventsPage;
        this.getToolbar().setBackCommand("Back", e -> {
            eventsPage.showBack();
        });
        poster = new ImageViewer(URLImage.createCachedImage("ei", "http://localhost/donationWEB/web/img/events/" + event.getPoster(), Image.createImage(1280, 720), URLImage.FLAG_RESIZE_SCALE_TO_FILL));
        na = new SpanLabel("Event Name: " + event.getNameEv());
        ty = new SpanLabel("Event Type: " + event.getTypeEv());
        eq = new SpanLabel("Event Equipements: " + event.getEquipementEv());
        description = new SpanLabel("Description: " + event.getDescriptionEv());
        dateFormatter = new SimpleDateFormat("dd-MM HH:mm");
        dateTimeStart = new Label("Date: " + dateFormatter.format(event.getDateEv()));
        currLocation = LocationManager.getLocationManager().getCurrentLocationSync();
        browser = new BrowserComponent();
        browser.setURL("http://localhost/donationWEB/web/mapsmobile.html");
        browser.addWebEventListener(BrowserComponent.onLoad, e -> {
            browser.execute("initDisplay(" + event.getLocationLatitude() + "," + event.getLocationLongitude() + "," + currLocation.getLatitude() + "," + currLocation.getLongitude() + ")");
        });
        shareBtn = new ShareButton();
        shareBtn.setText("Share Event");
        shareBtn.setTextToShare("Invitation To " + event.getNameEv() + " , Please Visit: http://localhost/donationWEB/web/app.php/events/" + event.getIdEv());
        this.getToolbar().addMaterialCommandToOverflowMenu("Invite Friends", FontImage.MATERIAL_SCREEN_SHARE, e -> {
            contactsList = new Dialog("Pick Friends", BoxLayout.y());
            contactsList.setScrollable(true);
            Contact[] contacts = ContactsManager.getContacts(true, true, false, true, false, false);
            List<String> selectedContacts = new ArrayList<>();
            for (Contact contact : contacts) {
                MultiButton entry = new MultiButton(contact.getDisplayName());
                entry.setCheckBox(true);
                entry.setTextLine2(contact.getPrimaryPhoneNumber());
                entry.addPointerReleasedListener(ev -> {
                    if (entry.isSelected()) {
                        selectedContacts.add(contact.getPrimaryPhoneNumber());
                    }
                    else {
                        selectedContacts.remove(contact.getPrimaryPhoneNumber());
                    }
                });
                contactsList.add(entry);
            }
            Button confirmBtn = new Button("Confirm");
            confirmBtn.addActionListener(ev -> {
                selectedContacts.forEach((String phoneNumber) -> {
                    try {
                        Display.getInstance().sendSMS(phoneNumber, "Invitation To " + event.getNameEv() + " , Please Visit: http://localhost/donationWEB/web/app.php/events/" + event.getIdEv(), false);
                    } catch (IOException ex) {}
                });
                contactsList.dispose();
            });
            Button exitBtn = new Button("Cancel");
            exitBtn.addActionListener(ev -> {
                contactsList.dispose();
            });
            contactsList.addComponent(confirmBtn);
            contactsList.addComponent(exitBtn);
            contactsList.show(100, 100, 10, 10);
        });
        this.addAll(poster,na,ty,eq,description, dateTimeStart, browser, shareBtn);
    }
}
