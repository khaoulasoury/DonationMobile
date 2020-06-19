
package Entities;

public class Notification {
    private int id ; 
    private String message ; 
    private String Date ; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public Notification() {
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", message=" + message + ", Date=" + Date + '}';
    }
    
    
    
    
}
