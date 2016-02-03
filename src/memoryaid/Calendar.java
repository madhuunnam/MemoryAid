/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yatish
 */
package memoryaid;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

public class Calendar {
     private String Event;
    private String EventDate;
     
    public Calendar(String Event, String EventDate) {
        this.Event = Event;
        this.EventDate = EventDate;
             
    }
    Calendar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public String Event() {
        return Event;
    }
   
    public String getDateType() {
        return EventDate;
    }
    
    
    
    

    
    
}
