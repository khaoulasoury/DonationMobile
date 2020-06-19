
package Services;

import Entities.Application;
import Entities.Notification;
import Entities.Offer;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class OfferService {
    
     public  ArrayList<Offer> getListsEvents(Map m){
        ArrayList<Offer> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("offer");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Offer p = new Offer();
            Double ll = (Double) f.get("idOffer");
            
            
            p.setId(ll.intValue());
           
            p.setConditionOffer((String)f.get("conditionOffer"));
            p.setImage((String)f.get("image"));
            p.setType((String)f.get("type"));
          
            p.setDescription((String)f.get("description"));
            p.setPost((String)f.get("post"));
           
            Map map1 = ((Map) f.get("dateAjout"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            p.setDateAjout(s1);
            /**
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());**/
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
     public  ArrayList<Application> getListsApp(Map m){
        ArrayList<Application> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("application");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Application p = new Application();
            Double ll = (Double) f.get("id");
            
            
            p.setId(ll.intValue());
           
            p.setCv((String)f.get("cv"));
            p.setMotivation((String)f.get("motivation"));
            
           
            Map map1 = ((Map) f.get("disponibleDate"));
            Map map2 = ((Map) f.get("offer"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            p.setDisponibleDate(s1);
            Offer of = new Offer();
            Double ld = (Double) map2.get("idOffer");
            
            
            of.setId(ld.intValue());
           
            of.setConditionOffer((String)map2.get("conditionOffer"));
            of.setImage((String)map2.get("image"));
            of.setType((String)map2.get("type"));
          
            of.setDescription((String)map2.get("description"));
            of.setPost((String)map2.get("post"));
           
            Map map3 = ((Map) map2.get("dateAjout"));
            Date date2 = new Date((((Double)map3.get("timestamp")).longValue()*1000)); 
           
            String s2 = formatter.format(date1);
            of.setDateAjout(s2);
            p.setOffer(of);
            
            
            /**
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());**/
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
     public  ArrayList<Notification> getListsnotif(Map m){
        ArrayList<Notification> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("notifcations");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Notification p = new Notification();
            Double ll = (Double) f.get("id");
            
            
            p.setId(ll.intValue());
           
            p.setMessage((String)f.get("message"));
           
            
           
            Map map1 = ((Map) f.get("date"));
           
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            p.setDate(s1);
            
            
            
            /**
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());**/
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
     

}
