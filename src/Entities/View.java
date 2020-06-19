
package Entities;


public class View {

    private int Id_view, Id_user;
    private String Text;
    private int Id_event;

    public View() {
    }

    public View(int Id_view, int Id_user, String Text, int Id_event) {
        this.Id_view = Id_view;
        this.Id_user = Id_user;
        this.Text = Text;
        this.Id_event = Id_event;
    }

    public View(int Id_user, String Text, int Id_event) {
        this.Id_user = Id_user;
        this.Text = Text;
        this.Id_event = Id_event;
    }

    public View(String Text, int Id_event) {
        this.Text = Text;
        this.Id_event = Id_event;
    }

    public View(String Text) {
        this.Text = Text;
    }

    public int getId_view() {
        return Id_view;
    }

    public void setId_view(int Id_view) {
        this.Id_view = Id_view;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public int getId_event() {
        return Id_event;
    }

    public void setId_event(int Id_event) {
        this.Id_event = Id_event;
    }

    @Override
    public String toString() {
        return "View{" + "Id_view=" + Id_view + ", Id_user=" + Id_user + ", Text=" + Text + ", Id_event=" + Id_event + '}';
    }

    
}
