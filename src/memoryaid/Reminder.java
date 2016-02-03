/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import java.time.LocalDate;


/**
 *
 * @author madhaviunnam
 */
public class Reminder {

    private int rId;
    private String reminderName;
    private String reminderType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reminderTime;
    private String repeatNone;
    private String repeatMon;
    private String repeatTue; 
    private String repeatWed;
    private String repeatThu;
    private String repeatFri;
    private String repeatSat;
    private String repeatSun;
    
    private String reminderStatus;

//    public Reminder(int rId, String reminderName, String reminderType, LocalDate startDate, LocalDate endDate, String reminderTime, String repeatOn, String reminderStatus) {
//        this.rId = rId;
//        this.reminderName = reminderName;
//        this.reminderType = reminderType;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.reminderTime = reminderTime;
//        this.repeatOn = repeatOn;
//        this.reminderStatus = reminderStatus;
//    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getReminderStatus() {
        return reminderStatus;
    }

    public void setReminderStatus(String reminderStatus) {
        this.reminderStatus = reminderStatus;
    }
    
    public String getReminderName() {
        return reminderName;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public String getReminderType() {
        return reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }
    
    public String getRepeatMon() {
        return repeatMon;
    }

    public void setRepeatMon(String repeatMon) {
        this.repeatMon = repeatMon;
    }

    public String getRepeatTue() {
        return repeatTue;
    }

    public void setRepeatTue(String repeatTue) {
        this.repeatTue = repeatTue;
    }

    public String getRepeatWed() {
        return repeatWed;
    }

    public void setRepeatWed(String repeatWed) {
        this.repeatWed = repeatWed;
    }

    public String getRepeatThu() {
        return repeatThu;
    }

    public void setRepeatThu(String repeatThu) {
        this.repeatThu = repeatThu;
    }

    public String getRepeatFri() {
        return repeatFri;
    }

    public void setRepeatFri(String repeatFri) {
        this.repeatFri = repeatFri;
    }

    public String getRepeatSat() {
        return repeatSat;
    }

    public void setRepeatSat(String repeatSat) {
        this.repeatSat = repeatSat;
    }

    public String getRepeatSun() {
        return repeatSun;
    }

    public void setRepeatSun(String repeatSun) {
        this.repeatSun = repeatSun;
    }

    public String getRepeatNone() {
        return repeatNone;
    }

    public void setRepeatNone(String repeatNone) {
        this.repeatNone = repeatNone;
    }

   
}
