package Services;



import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import Entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class ServiceProduct {
   public  Product prod=new Product();
   public void settid(int id){
   prod.setId_Product(id);
   }
    public int gettid(){
   return prod.getId_Product();
   }
    public ArrayList<Product> parseListEvenementsJson(String json) {
        
        ArrayList<Product> listProducts = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                Product e = new Product();
              
                 float idProduct = Float.parseFloat(obj.get("idProduct").toString());
                     e.setId_Product((int) idProduct);
                     e.setName_Product(obj.get("nameProduct").toString());

                        e.setDescription_Product(obj.get("descriptionProduct").toString());
                        float qte = Float.parseFloat(obj.get("quantityRemaining").toString());
                        e.setQuantity_Remaining((int) qte);
                      float qteTotal = Float.parseFloat(obj.get("quantityTotal").toString());
                     e.setQuantity_Total((int) qteTotal);
                      float Price = Float.parseFloat(obj.get("priceProduct").toString());
                     e.setPrice_Product((float) Price);
                      String unholyText = obj.get("idAssociation").toString();
                String demiHolyText=""; 
                for(int i = 15; i<21 ; i++){
                    demiHolyText = demiHolyText + unholyText.charAt(i);
                }
                String holyText = StringUtil.replaceAll(demiHolyText, ",", "");
                e.setId_Association(((int)Float.parseFloat(holyText)));
               
                listProducts.add(e);
            }
        } catch (IOException ex) {
        }
        return listProducts;
    
    }
    
    

    ArrayList<Product> listProducts = new ArrayList();
  

    
    public ArrayList<Product> getListProducts() {
        ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/products/ListeProductsall");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduct ser = new ServiceProduct();
                listProducts = ser.parseListEvenementsJson(new String(cnx.getResponseData()));
                cnx.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return listProducts;
    }
    
       public ArrayList<Product> Recherche(String nameProduct){        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl( "http://localhost/donationWEB/web/app_dev.php/Donation/donationm/products/findMobile/"+nameProduct);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduct ser = new ServiceProduct();
                listProducts = ser.parseListEvenementsJson(new String(con.getResponseData()));
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProducts;
    }
    
       public ArrayList<Product> getListProducttrie() {
        ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/products/triMobile");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduct ser = new ServiceProduct();
                listProducts = ser.parseListEvenementsJson(new String(cnx.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return listProducts;
    }
 public  Product find(int id){        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl( "http://localhost/donationWEB/web/app_dev.php/Donation/donationm/products/findProductMobile/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduct ser = new ServiceProduct();
                listProducts = ser.parseListEvenementsJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProducts.get(0);
    }
  public ArrayList<Product> getListProducttriebyprix() {
        ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/products/tribyprixMobile");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduct ser = new ServiceProduct();
                listProducts = ser.parseListEvenementsJson(new String(cnx.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return listProducts;
    }
  public ArrayList<Product> getListProducttriebyqte() {
        ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/Donation/donationm/products/tribyqteMobile");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduct ser = new ServiceProduct();
                listProducts = ser.parseListEvenementsJson(new String(cnx.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return listProducts;
    }
}