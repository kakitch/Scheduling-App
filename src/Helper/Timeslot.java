/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Model.Appointment;
import java.text.ParseException;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author knfab
 */
public class Timeslot {
    
    private LocalTime twentyfour;
    private String twelve;

    public Timeslot(LocalTime twentyfour, String twelve) {
        this.twentyfour = twentyfour;
        this.twelve = twelve;
    }

    public LocalTime getTwentyfour() {
        return twentyfour;
    }

    public void setTwentyfour(LocalTime twentyfour) {
        this.twentyfour = twentyfour;
    }

    public String getTwelve() {
        return twelve;
    }

    public void setTwelve(String twelve) {
        this.twelve = twelve;
    }
    
    public static ObservableList<Timeslot> getTimeslots() throws ParseException {
        ObservableList<Timeslot> slots = FXCollections.observableArrayList();
        
        LocalTime localTime = LocalTime.of(0, 0);
        int i = 0;
        while(i < 96){
           String lt = localTime.toString();
           SimpleDateFormat SDF = new SimpleDateFormat("HH:mm");
           SimpleDateFormat ltSDF = new SimpleDateFormat("hh:mm a");
           Date time = SDF.parse(lt);
           String newTime = ltSDF.format(time);
           Timeslot slot = new Timeslot(localTime, newTime);
           slots.add(slot);
           localTime = localTime.plusMinutes(15);
           i++;
        }
        return slots;        
    }
    
//    public static Timeslot getTimeslot(String time) throws ParseException{
//        ObservableList<Timeslot> slots = Timeslot.getTimeslots();
//        
//        int size = slots.size();
//        int i = 0;
//        while(i <= size){
//            boolean match = slots.contains();
//            if(match = true){
//                ts = slots.get(i);
//            }
//            else{}
//            i++;
//        }
//        return ts;
//    }
    
    @Override
    public String toString(){
        return(twelve);
    }
    
}
