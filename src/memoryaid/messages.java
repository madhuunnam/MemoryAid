/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Yatish
 */
public class messages {
    private String Name;
    private String Text;
    
      public messages(String Name, String Text) {
        this.Name = Name;
        this.Text = Text;
             
    }

    messages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 public String getNameType() {
        return Name;
    }

   
    public String getTextType() {
        return Text;
    }
    
   
    
    
    
}
