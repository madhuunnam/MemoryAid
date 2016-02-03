package memoryaid;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class AlarmTimer extends Application{

    static int counter = 0;
    static HashMap<String, String[]> reminders;
    
    static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    static Date date;
            
    public AlarmTimer(){
       updateReminders();
       TimerTask timerTask;
        timerTask = new TimerTask() {
            
            AudioStream audioStream = null;
            
            @Override
            public void run() {
                
                if(isAlarmActive()){
                    System.out.println("TimerTask executing counter is: " + counter);
                    counter++;//increments the counter
                    executeReminderPopUp();

                    date = new Date();
                    System.out.println(dateFormat.format(date));
                }
            }

           private void executeReminderPopUp() {
               
               
                JFrame parent = new JFrame();
                JPanel panel = new JPanel();
                
                JButton button = new JButton();
                JLabel jLabel1 = new JLabel();
                JLabel jLabel2 = new JLabel();
                JLabel jLabel3 = new JLabel();
                JLabel jLabel4 = new JLabel();
                JButton stopAlarmButton = new JButton();
                
                jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                jLabel1.setText("Medication Reminder");

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/newPictures/med1.PNG"))); // NOI18N

                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/newPictures/med2.PNG"))); // NOI18N

                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/newPictures/alarm.PNG"))); // NOI18N

                
               
               try {
                   String gongFile = "/Users/madhaviunnam/NetBeansProjects/MemoryAid/src/Sound/Alarm.wav";
                   InputStream in = new FileInputStream(gongFile);
                   // create an audiostream from the inputstream
                   audioStream = new AudioStream(in);
                   // play the audio clip with the audioplayer class
                   AudioPlayer.player.start(audioStream);
               } catch (Exception ex) {
                   Logger.getLogger(AlarmTimer.class.getName()).log(Level.SEVERE, null, ex);
               }
                
                
                stopAlarmButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/done_alarm.PNG"))); // NOI18N
                stopAlarmButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        stopAlarmButtonActionPerformed(evt);
                    }

                    private void stopAlarmButtonActionPerformed(ActionEvent evt) {
                        AudioPlayer.player.stop(audioStream);
                    }
                });

//                button.setText("Click me to show dialog!");
                //panel.setLayout();
                panel.add(jLabel1);
                panel.add(jLabel2);
                panel.add(jLabel3);
                panel.add(jLabel4);
                panel.add(stopAlarmButton);
//                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                parent.setBounds(0,0,300,300);
                parent.getContentPane().add(panel);
                parent.pack();
                parent.setVisible(true);

                button.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        String name = JOptionPane.showInputDialog(parent,
                                "What is your name?", null);
                    }
                });
           }

           private boolean isAlarmActive() {
               boolean result = false;
               try {
                   DbConnection dbConn = new DbConnection();
                   Connection conn = dbConn.connect();

                   String sql = "select reminderTime from reminders";
                   PreparedStatement pstmt = conn.prepareStatement(sql);
                   ResultSet rs =pstmt.executeQuery();
                   
                   Date currentDate = new Date();
                   
                   
                   SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                   System.out.println("SDF Current Date"+ sdf.format(currentDate));
                   String formattedDateString = sdf.format(currentDate);
                   
                   String currentTime = formattedDateString.substring(0, 5);
                   System.out.println("SDF Current Time"+ currentTime);
                   while(rs.next()){
                       
                       String retrievedTime =  rs.getString("reminderTime");
                       retrievedTime = retrievedTime.substring(0,5);
                       
                       if(retrievedTime.equals(currentTime)){
                           System.out.println("Matched the Alarm");
                           result = true;
                           break;
                       }
                   
                   }
                   
                   
               } catch (Exception e) {
                   e.printStackTrace();

               }
               
               return result;
               
           }
        };
        
        

        Timer timer = new Timer("MyTimer");//create a new Timer

        timer.scheduleAtFixedRate(timerTask, 10000, 10000);//this line starts the timer at the same time its executed
//        timer.scheduleAtFixedRate(timerTask2, 30, 600000);//this line starts the timer at the same time its executed
    }
    
    private static void updateReminders(){
        // DB Connection
//        DbConnection1 db = new DbConnection1();
//        Connection conn = db.connect();
        System.out.println("In UPDATE REMINDERS");
//        String SQL = "SELECT * FROM `reminders` WHERE startdate <= curdate() and enddate >= curdate() order by reminderTime asc";
//        try{
//            ResultSet rs = conn.createStatement().executeQuery(SQL);
//             reminders = db.dbSelect(rs);
//            
//        }
//        catch(SQLException e){
//            System.out.println("Error in Reminders");
//        }
//        finally{
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(AlarmTimer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AlarmPopUp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}