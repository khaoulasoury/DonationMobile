package Entities;



public class Product {
     private int Id_Product,Quantity_Total,Quantity_Remaining,Id_admin;
     private String Name_Product,Description_Product;
     private float Price_Product;
private int Id_Association;
    public Product(int Id_Product, int Quantity_Total, int Quantity_Remaining, int Id_admin, String Name_Product, String Description_Product, float Price_Product) {
        this.Id_Product = Id_Product;
        this.Quantity_Total = Quantity_Total;
        this.Quantity_Remaining = Quantity_Remaining;
        this.Id_admin = Id_admin;
        this.Name_Product = Name_Product;
        this.Description_Product = Description_Product;
        this.Price_Product = Price_Product;
    }

    public Product() {
    }

    public Product(int Quantity_Total, int Quantity_Remaining, int Id_admin, String Name_Product, String Description_Product, float Price_Product) {
        this.Quantity_Total = Quantity_Total;
        this.Quantity_Remaining = Quantity_Remaining;
        this.Id_admin = Id_admin;
       
        this.Name_Product = Name_Product;
        this.Description_Product = Description_Product;
        this.Price_Product = Price_Product;
    }

    @Override
    public String toString() {
        return "Product{" + "Id_Product=" + Id_Product + ", Quantity_Total=" + Quantity_Total + ", Quantity_Remaining=" + Quantity_Remaining + ", Id_admin=" + Id_admin + ", Id_Association=" + Id_Association + ", Name_Product=" + Name_Product + ", Description_Product=" + Description_Product + ", Price_Product=" + Price_Product + '}';
    }
     

    public int getId_Product() {
        return Id_Product;
    }

    public void setId_Product(int Id_Product) {
        this.Id_Product = Id_Product;
    }

    public int getQuantity_Total() {
        return Quantity_Total;
    }

    public void setQuantity_Total(int Quantity_Total) {
        this.Quantity_Total = Quantity_Total;
    }

    public int getQuantity_Remaining() {
        return Quantity_Remaining;
    }

    public void setQuantity_Remaining(int Quantity_Remaining) {
        this.Quantity_Remaining = Quantity_Remaining;
    }

    public int getId_admin() {
        return Id_admin;
    }

    public void setId_admin(int Id_admin) {
        this.Id_admin = Id_admin;
    }

    public int getId_Association() {
        return Id_Association;
    }

    public void setId_Association(int Id_Association) {
        this.Id_Association = Id_Association;
    }

    public Product(int Id_Product, int Quantity_Total, int Quantity_Remaining, int Id_admin, String Name_Product, String Description_Product, float Price_Product, int Id_Association) {
        this.Id_Product = Id_Product;
        this.Quantity_Total = Quantity_Total;
        this.Quantity_Remaining = Quantity_Remaining;
        this.Id_admin = Id_admin;
        this.Name_Product = Name_Product;
        this.Description_Product = Description_Product;
        this.Price_Product = Price_Product;
        this.Id_Association = Id_Association;
    }

    public Product(int Quantity_Total, int Quantity_Remaining, int Id_admin, String Name_Product, String Description_Product, float Price_Product, int Id_Association) {
        this.Quantity_Total = Quantity_Total;
        this.Quantity_Remaining = Quantity_Remaining;
        this.Id_admin = Id_admin;
        this.Name_Product = Name_Product;
        this.Description_Product = Description_Product;
        this.Price_Product = Price_Product;
        this.Id_Association = Id_Association;
    }

   

    public String getName_Product() {
        return Name_Product;
    }

    public void setName_Product(String Name_Product) {
        this.Name_Product = Name_Product;
    }

    public String getDescription_Product() {
        return Description_Product;
    }

    public void setDescription_Product(String Description_Product) {
        this.Description_Product = Description_Product;
    }

    public float getPrice_Product() {
        return Price_Product;
    }

    public void setPrice_Product(float Price_Product) {
        this.Price_Product = Price_Product;
    }
     
}
