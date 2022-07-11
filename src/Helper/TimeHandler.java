/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author knfab
 */
public abstract class TimeHandler {
    
    /**
     * Takes input of date time set as UTC and converts to system default
     * @return 
     */

    
    public static Timestamp UTCtimeStamp(){
        ZoneId zone = ZoneId.of("UTC");
        Instant instant = Instant.now();
        //System.out.println("TimeHandler.UTCtimeStamp instant: " + instant);
        ZonedDateTime zdt= instant.atZone(zone);
        //System.out.println("TimeHandler.UTCtimeStamp timestamp: " + zdt);
        Timestamp timestamp2 = Timestamp.valueOf(zdt.toLocalDateTime());
        //System.out.println("TimeHandler.UTCtimeStamp timestamp2: " + timestamp2);
        
        return timestamp2;
    }
    
    public static Timestamp sysToUTC(LocalTime time, LocalDate date){
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zdt =ZonedDateTime.of(date, time, zone);
        //System.out.println("TimeHandler.sysToUTC zdt: " + zdt); 
        zdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        //System.out.println("TimeHandler.sysToUTC zdt: " + zdt);        
        Timestamp ts = Timestamp.valueOf(zdt.toLocalDateTime());
        //System.out.println("TimeHandler.sysToUTC timestamp: " + ts);
        return ts;
    }
    
    
    public static Timestamp UTCtoSys(Timestamp timestamp){
        ZoneId zone = ZoneId.systemDefault();
        //System.out.println("TimeHandler.UTCtoSys incoming timestamp: " + timestamp);
        ZonedDateTime zdt =ZonedDateTime.of(timestamp.toLocalDateTime(), ZoneId.of("UTC"));
        //System.out.println("TimeHandler.UTCtoSys  UTC zdt: " + zdt); 
        zdt = zdt.withZoneSameInstant(zone);
        //System.out.println("TimeHandler.UTCtoSys Sys zdt: " + zdt);        
        Timestamp ts = Timestamp.valueOf(zdt.toLocalDateTime());
        //System.out.println("TimeHandler.UTCtoSys outgoing timestamp: " + ts);
        return ts;
    }
    
    public static ZonedDateTime businessStart(LocalDate date){
        LocalTime time = LocalTime.of(8, 0);
        ZonedDateTime zdt = ZonedDateTime.of(date , time ,ZoneId.of("US/Eastern"));
        return zdt;
    }
    
    public static ZonedDateTime businessEnd(LocalDate date){
        LocalTime time = LocalTime.of(22, 0);
        ZonedDateTime zdt = ZonedDateTime.of(date , time ,ZoneId.of("US/Eastern"));
        return zdt;
    }
    
    public static ZonedDateTime toEST(LocalTime time, LocalDate date, ZoneId zone){
        ZonedDateTime zdt = ZonedDateTime.of(date , time ,zone);
        zdt = zdt.withZoneSameInstant(ZoneId.of("US/Eastern"));
        
        return zdt;
    }
    
                
    public static boolean compareToBusinessHours(LocalTime start, LocalTime end, LocalDate date){
        boolean response = true;
        
        ZoneId zone = ZoneId.systemDefault();
        
        //System.out.println(zone);
        
                
        ZonedDateTime bs = TimeHandler.businessStart(date);
        ZonedDateTime be = TimeHandler.businessEnd(date);
        
        ZonedDateTime as = TimeHandler.toEST(start, date ,zone);
        ZonedDateTime ae = TimeHandler.toEST(end, date, zone);
        
//        System.out.println(bs + "\n");
//        System.out.println(be + "\n");
//        System.out.println(as + "\n");
//        System.out.println(ae + "\n");


        if((as.isBefore(bs) || as.isAfter(be) || ae.isBefore(bs) || ae.isAfter(be)) == true){
           response = false; 
        }
        return response;
    }
    
    public static String reformatTimestamp(Timestamp timestamp){
        LocalDateTime ldt = timestamp.toLocalDateTime();
        DateTimeFormatter Format = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
        String output = Format.format(ldt);
        return output;
    }
}
        