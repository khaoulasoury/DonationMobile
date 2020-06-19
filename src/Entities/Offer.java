
package Entities;


public class Offer 
{
    private int id ; 
    private String post ; 
    private String description ;
    private String type ; 
    private String image ; 
    private String conditionOffer;
    private String dateAjout ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConditionOffer() {
        return conditionOffer;
    }

    public void setConditionOffer(String conditionOffer) {
        this.conditionOffer = conditionOffer;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Offer() {
    }

    @Override
    public String toString() {
        return "Offer{" + "id=" + id + ", post=" + post + ", description=" + description + ", type=" + type + ", image=" + image + ", conditionOffer=" + conditionOffer + ", dateAjout=" + dateAjout + '}';
    }
    
    
    
    
    
}
