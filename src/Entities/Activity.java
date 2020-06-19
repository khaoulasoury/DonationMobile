package Entities;


public class Activity {
    private Integer idAc;
    private String nameEV;
    private String nameAc;
    private double duration;
    private String descriptionAc;
    private String typeAc;

    public Activity(Integer idAc, String nameEV, String nameAc, double duration, String descriptionAc, String typeAc) {
        this.idAc = idAc;
        this.nameEV = nameEV;
        this.nameAc = nameAc;
        this.duration = duration;
        this.descriptionAc = descriptionAc;
        this.typeAc = typeAc;
    }

    public Activity(String nameAc, double duration, String descriptionAc, String typeAc) {
        this.nameAc = nameAc;
        this.duration = duration;
        this.descriptionAc = descriptionAc;
        this.typeAc = typeAc;
    }
    public Activity() { }

    public Integer getIdAc() { return idAc; }

    public String getNameEV() { return nameEV; }

    public String getNameAc() { return nameAc; }

    public double getDuration() { return duration; }

    public String getDescriptionAc() { return descriptionAc; }

    public String getTypeAc() { return typeAc; }

    public void setIdAc(Integer idAc) { this.idAc = idAc; }

    public void setNameEV(String nameEV) { this.nameEV = nameEV; }

    public void setNameAc(String nameAc) { this.nameAc = nameAc; }

    public void setDuration(double duration) { this.duration = duration; }

    public void setDescriptionAc(String descriptionAc) { this.descriptionAc = descriptionAc; }

    public void setTypeAc(String typeAc) { this.typeAc = typeAc; }

    @Override
    public String toString() {
        return "Activity{" +
                "nameAc='" + nameAc + '\'' +
                ", duration=" + duration +
                ", typeAc='" + typeAc + '\'' +
                ", descriptionAc='" + descriptionAc + '\'' +
                '}';
    }

    public Activity(Integer idAc, String nameAc, double duration, String descriptionAc, String typeAc) {
        this.idAc = idAc;
        this.nameAc = nameAc;
        this.duration = duration;
        this.descriptionAc = descriptionAc;
        this.typeAc = typeAc;
    }

}
