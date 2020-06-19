
package Entities;

public class Action {
    private int Id_Action;
    private int Id_Association;
    private String Name_Action;
    private String Date_Action;
    private String Location_Action;
    private int NbV_Action;
    private String TypeV_Action;
    private String Material_Action;
    private String description;
    private String image_name;

    public Action() {
    }

    public Action(int Id_Association, String Name_Action, String Date_Action, String Location_Action, int NbV_Action, String TypeV_Action, String Material_Action, String description, String image_name) {
        this.Id_Association = Id_Association;
        this.Name_Action = Name_Action;
        this.Date_Action = Date_Action;
        this.Location_Action = Location_Action;
        this.NbV_Action = NbV_Action;
        this.TypeV_Action = TypeV_Action;
        this.Material_Action = Material_Action;
        this.description = description;
        this.image_name = image_name;
    }

    public Action(int Id_Association, String Name_Action, String Date_Action, String Location_Action, int NbV_Action, String description, String image_name) {
        this.Id_Association = Id_Association;
        this.Name_Action = Name_Action;
        this.Date_Action = Date_Action;
        this.Location_Action = Location_Action;
        this.NbV_Action = NbV_Action;
        this.description = description;
        this.image_name = image_name;
    }

    public Action(int Id_Action, int Id_Association, String Name_Action, String Date_Action, String Location_Action, int NbV_Action, String TypeV_Action, String Material_Action, String description, String image_name) {
        this.Id_Action = Id_Action;
        this.Id_Association = Id_Association;
        this.Name_Action = Name_Action;
        this.Date_Action = Date_Action;
        this.Location_Action = Location_Action;
        this.NbV_Action = NbV_Action;
        this.TypeV_Action = TypeV_Action;
        this.Material_Action = Material_Action;
        this.description = description;
        this.image_name = image_name;
    }

    public Action( String Name_Action, String Date_Action, String Location_Action, int NbV_Action, String description, String image_name) {
        this.Name_Action = Name_Action;
        this.Date_Action = Date_Action;
        this.Location_Action = Location_Action;
        this.NbV_Action = NbV_Action;
        this.description = description;
        this.image_name = image_name;
    }

   

    public int getId_Action() {
        return Id_Action;
    }

    public void setId_Action(int Id_Action) {
        this.Id_Action = Id_Action;
    }

    public int getId_Association() {
        return Id_Association;
    }

    public void setId_Association(int Id_Association) {
        this.Id_Association = Id_Association;
    }

    public String getName_Action() {
        return Name_Action;
    }

    public void setName_Action(String Name_Action) {
        this.Name_Action = Name_Action;
    }

    public String getDate_Action() {
        return Date_Action;
    }

    public void setDate_Action(String Date_Action) {
        this.Date_Action = Date_Action;
    }

    public String getLocation_Action() {
        return Location_Action;
    }

    public void setLocation_Action(String Location_Action) {
        this.Location_Action = Location_Action;
    }

    public int getNbV_Action() {
        return NbV_Action;
    }

    public void setNbV_Action(int NbV_Action) {
        this.NbV_Action = NbV_Action;
    }

    public String getTypeV_Action() {
        return TypeV_Action;
    }

    public void setTypeV_Action(String TypeV_Action) {
        this.TypeV_Action = TypeV_Action;
    }

    public String getMaterial_Action() {
        return Material_Action;
    }

    public void setMaterial_Action(String Material_Action) {
        this.Material_Action = Material_Action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    @Override
    public String toString() {
        return "Action{" + "Id_Action=" + Id_Action + ", Id_Association=" + Id_Association + ", Name_Action=" + Name_Action + ", Date_Action=" + Date_Action + ", Location_Action=" + Location_Action + ", NbV_Action=" + NbV_Action + ", TypeV_Action=" + TypeV_Action + ", Material_Action=" + Material_Action + ", description=" + description + ", image_name=" + image_name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.Id_Action;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if (this.Id_Action != other.Id_Action) {
            return false;
        }
        return true;
    }
    
    
}
