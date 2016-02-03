package memoryaid;

import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yatish
 */
public class CalendarIndex {
    
    
    private SimpleStringProperty eventId;
    private SimpleStringProperty eventDateId;
   
     public CalendarIndex(String EventId, String EventDateId) {
         this.eventId = new SimpleStringProperty(EventId);
         this.eventDateId = new SimpleStringProperty(EventDateId);        
                } 
public String getEventId() {
        System.out.println("Printing get RID" + eventId.get());
        return eventId.get();
    }

    

    public void setEventId(String EventId) {
        this.eventId.set(EventId);
    }

   
    public String getEventDateId() {
        System.out.println("Printing get RID" + eventId.get());
        return eventDateId.get();
    }

    public void setEventDateId(String EventDateId) {
        this.eventDateId.set(EventDateId);
    }
     
    
    
}
