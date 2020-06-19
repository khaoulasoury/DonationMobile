
package Entities;

public class Application {
    
    private int id ; 
    private String motivation ; 
    private String disponibleDate ;
    private String cv ;
    private int user ; 
    private Offer offer ; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getDisponibleDate() {
        return disponibleDate;
    }

    public void setDisponibleDate(String disponibleDate) {
        this.disponibleDate = disponibleDate;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Application() {
    }

    @Override
    public String toString() {
        return "Application{" + "id=" + id + ", motivation=" + motivation + ", disponibleDate=" + disponibleDate + ", cv=" + cv + ", user=" + user + ", offer=" + offer + '}';
    }

    
    
    
    
}
