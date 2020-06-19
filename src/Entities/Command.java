
package Entities;

import java.util.Date;
import Entities.Product;

public class Command {
    private int user,Id_Command,Quantity_Product,Paid;
    private Date Date_Command;
private int Id_Product;
    public Command() {
    }

    public Command(int Paid) {
        this.Paid = Paid;
    }

    

    public Command( int Quantity_Product, int Paid) {
      
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
    }

    

    public Command(int user,  int Quantity_Product, int Paid, Date Date_Command) {
        this.user = user;
     
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
        this.Date_Command = Date_Command;
    }

    public Command(int user, int Id_Command,  int Quantity_Product, int Paid, Date Date_Command) {
        this.user = user;
        this.Id_Command = Id_Command;
        
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
        this.Date_Command = Date_Command;
    }

    public Command(int Quantity_Product, int Paid, Date Date_Command) {
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
        this.Date_Command = Date_Command;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getId_Command() {
        return Id_Command;
    }

    public void setId_Command(int Id_Command) {
        this.Id_Command = Id_Command;
    }

    public int getId_Product() {
        return Id_Product;
    }

    public void setId_Product(int Id_Product) {
        this.Id_Product = Id_Product;
    }

    public Command(int Id_Command, int Quantity_Product, int Paid, int Id_Product) {
        this.Id_Command = Id_Command;
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
        this.Id_Product = Id_Product;
    }

    public Command(int user, int Id_Command, int Quantity_Product, int Paid, Date Date_Command, int Id_Product) {
        this.user = user;
        this.Id_Command = Id_Command;
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
        this.Date_Command = Date_Command;
        this.Id_Product = Id_Product;
    }

  

    public int getQuantity_Product() {
        return Quantity_Product;
    }

    public void setQuantity_Product(int Quantity_Product) {
        this.Quantity_Product = Quantity_Product;
    }

    public int getPaid() {
        return Paid;
    }

    public void setPaid(int Paid) {
        this.Paid = Paid;
    }

    public Date getDate_Command() {
        return Date_Command;
    }

    public void setDate_Command(Date Date_Command) {
        this.Date_Command = Date_Command;
    }

    @Override
    public String toString() {
        return "Command{" + "user=" + user + ", Id_Command=" + Id_Command + ", Quantity_Product=" + Quantity_Product + ", Paid=" + Paid + ", Date_Command=" + Date_Command + ", Id_Product=" + Id_Product + '}';
    }
     
  
    
}
