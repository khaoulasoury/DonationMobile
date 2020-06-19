/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

//import java.sql.Timestamp;

import java.util.Date;




/**
 *
 * @author Tarek
 */
public class Association {
    private int Id_Association;
    private String nom_Association;
    private String Objectif_Association;
    private String Email_Association;
    private String Password_Association;
    private String Address_Association;
    private String Type_Association;
    private String Description_Association;
    private String image_name;
    private String redeem;
    private Date Date_inscrit;

    public Association() {
    }
    

    public Association(String nom_Association, String Objectif_Association, String Email_Association, String Password_Association, String Address_Association, String Type_Association, String Description_Association, String image_name) {
        this.nom_Association = nom_Association;
        this.Objectif_Association = Objectif_Association;
        this.Email_Association = Email_Association;
        this.Password_Association = Password_Association;
        this.Address_Association = Address_Association;
        this.Type_Association = Type_Association;
        this.Description_Association = Description_Association;
        this.image_name = image_name;
    }
    

    public Association(int Id_Association, String nom_Association, String Object_Association, String Email_Association,String Password_Association, String Address_Association, String Type_Association, String Description_Association,String image_name) {
        this.Id_Association = Id_Association;
        this.nom_Association = nom_Association;
        this.Objectif_Association = Object_Association;
        this.Email_Association = Email_Association;
        this.Password_Association=Password_Association;
        this.Address_Association = Address_Association;
        this.Type_Association = Type_Association;
        this.Description_Association = Description_Association;
        this.image_name=image_name;
    }

    public String getPassword_Association() {
        return Password_Association;
    }

    public void setPassword_Association(String Password_Association) {
        this.Password_Association = Password_Association;
    }
    
    public String getImage_name() {
        return image_name;
    }
    
    
    public int getId_Association() {
        return Id_Association;
    }

    public void setId_Association(int Id_Association) {
        this.Id_Association = Id_Association;
    }

    public String getEmail_Association() {
        return Email_Association;
    }

    public void setEmail_Association(String Email_Association) {
        this.Email_Association = Email_Association;
    }

    public String getAddress_Association() {
        return Address_Association;
    }

    public void setAddress_Association(String Address_Association) {
        this.Address_Association = Address_Association;
    }

    public String getType_Association() {
        return Type_Association;
    }

    public void setType_Association(String Type_Association) {
        this.Type_Association = Type_Association;
    }

    public String getDescription_Association() {
        return Description_Association;
    }

    public void setDescription_Association(String Description_Association) {
        this.Description_Association = Description_Association;
    }

    public String getNom_Association() {
        return nom_Association;
    }

    public void setNom_Association(String nom_Association) {
        this.nom_Association = nom_Association;
    }

    public String getObjectif_Association() {
        return Objectif_Association;
    }

    public void setObjectif_Association(String Objectif_Association) {
        this.Objectif_Association = Objectif_Association;
    }

   

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Date getDate_inscrit() {
        return Date_inscrit;
    }

    public void setDate_inscrit(Date Date_inscrit) {
        this.Date_inscrit = Date_inscrit;
    }

    @Override
    public String toString() {
        return "Association{" + "Id_Association=" + Id_Association + ", nom_Association=" + nom_Association + ", Objectif_Association=" + Objectif_Association + ", Email_Association=" + Email_Association + ", Address_Association=" + Address_Association + ", Type_Association=" + Type_Association + ", Description_Association=" + Description_Association + ", image_name=" + image_name + ", Date_inscrit=" + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Association other = (Association) obj;
        if (this.Id_Association != other.Id_Association) {
            return false;
        }
        return true;
    }
    
    
    
}

