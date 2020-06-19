package Entities;

import java.util.Date;

public class Event {
    private Integer idEv;
    private String nameEv;
    private double locationLongitude;
    private double locationLatitude;
    private Date dateEv;
    private String descriptionEv;
    private String equipementEv;
    private String poster;
    private String typeEv;

    public Event(Integer idEv) {
        this();
        this.idEv = idEv;
    }

    public Event() {

    }

    public Event(Integer idEv, String nameEv, double locationLongitude, double locationLatitude,
                 Date dateEv, String descriptionEv, String equipementEv, String poster, String typeEv) {
        this.idEv = idEv;
        this.nameEv = nameEv;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.dateEv = dateEv;
        this.descriptionEv = descriptionEv;
        this.equipementEv = equipementEv;
        this.poster = poster;
        this.typeEv = typeEv;
    }

    public Integer getIdEv() { return idEv; }

    public String getNameEv() { return nameEv; }

    public double getLocationLongitude() { return locationLongitude; }

    public double getLocationLatitude() { return locationLatitude; }

    public Date getDateEv() { return dateEv; }

    public String getDescriptionEv() { return descriptionEv; }

    public String getEquipementEv() { return equipementEv; }

    public String getPoster() { return poster; }

    public String getTypeEv() { return typeEv; }

    public void setIdEv(Integer idEv) { this.idEv = idEv; }

    public void setNameEv(String nameEv) { this.nameEv = nameEv; }

    public void setLocationLongitude(double locationLongitude) { this.locationLongitude = locationLongitude; }

    public void setLocationLatitude(double locationLatitude) { this.locationLatitude = locationLatitude; }

    public void setDateEv(Date dateEv) { this.dateEv = dateEv; }

    public void setDescriptionEv(String descriptionEv) { this.descriptionEv = descriptionEv; }

    public void setEquipementEv(String equipementEv) { this.equipementEv = equipementEv; }

    public void setPoster(String poster) { this.poster = poster; }

    public void setTypeEv(String typeEv) { this.typeEv = typeEv; }
}
