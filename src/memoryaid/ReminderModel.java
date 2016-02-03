/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author madhaviunnam
 */
public class ReminderModel {
    
    private SimpleStringProperty reminderId;
    private SimpleStringProperty reminderName;
    private SimpleStringProperty reminderType;
    private SimpleStringProperty startDate;
    private SimpleStringProperty endDate;
    private SimpleStringProperty reminderTime;
    private SimpleStringProperty repeatMon;
    private SimpleStringProperty repeatTue; 
    private SimpleStringProperty repeatWed;
    private SimpleStringProperty repeatThu;
    private SimpleStringProperty repeatFri;
    private SimpleStringProperty repeatSat;
    private SimpleStringProperty repeatSun;
    
    private SimpleStringProperty reminderStatus;
    
    public ReminderModel(String reminderId, String reminderName, String reminderType, String startDate, String endDate,String reminderTime) {
         this.reminderId = new SimpleStringProperty(reminderId);
         this.reminderName = new SimpleStringProperty(reminderName);
         this.reminderType = new SimpleStringProperty(reminderType);
         this.startDate = new SimpleStringProperty(startDate);
         this.endDate = new SimpleStringProperty(endDate);
         this.reminderTime = new SimpleStringProperty(reminderTime);
            
    } 

    public String getReminderId() {
        System.out.println("Printing get RID" + reminderId.get());
        return reminderId.get();
    }

    public void setReminderId(String rId) {
        System.out.println("Printing set RID" + rId);
        this.reminderId.set(rId); 
    }

    public String getReminderName() {
        System.out.println("Printing get reminderName" + reminderName.get());
        return reminderName.get();
    }

    public void setReminderName(String reminderName) {
        System.out.println("Printing set reminderName" + reminderName);
        this.reminderName.set(reminderName); 
    }

    public String getReminderType() {
        return reminderType.get();
    }

    public void setReminderType(String reminderType) {
        this.reminderType.set(reminderType);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public String getReminderTime() {
        return reminderTime.get();
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime.set(reminderTime); 
    }

    public String getRepeatMon() {
        return repeatMon.get();
    }

    public void setRepeatMon(String repeatMon) {
        this.repeatMon.set(repeatMon); 
    }

    public String getRepeatTue() {
        return repeatTue.get();
    }

    public void setRepeatTue(String repeatTue) {
        this.repeatTue.set(repeatTue); 
    }

    public String getRepeatWed() {
        return repeatWed.get();
    }

    public void setRepeatWed(String repeatWed) {
        this.repeatWed.set(repeatWed); 
    }

    public String getRepeatThu() {
        return repeatThu.get();
    }

    public void setRepeatThu(String repeatThu) {
        this.repeatThu.set(repeatThu); 
    }

    public String getRepeatFri() {
        return repeatFri.get();
    }

    public void setRepeatFri(String repeatFri) {
        this.repeatFri.set(repeatFri);
    }

    public String getRepeatSat() {
        return repeatSat.get();
    }

    public void setRepeatSat(String repeatSat) {
        this.repeatSat.set(repeatSat); ;
    }

    public String getRepeatSun() {
        return repeatSun.get();
    }

    public void setRepeatSun(String repeatSun) {
        this.repeatSun.set(repeatSun);
    }

    public String getReminderStatus() {
        return reminderStatus.get();
    }

    public void setReminderStatus(String reminderStatus) {
        this.reminderStatus.set(reminderStatus);
    }
    
    
    
}
