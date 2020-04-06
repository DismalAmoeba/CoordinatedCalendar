package calendarproject;

import java.util.LinkedList;

public class DataHandler {
    public static LinkedList eventList = new LinkedList();
    
    public static void addToList(int type, int userType, int userID, int year, int month, int day, String eventName, int startTime, int endTime){
        //the first constructor: type is to differenciate if an event happens once or on a certain interval
        //0 for one time
        //1 for weekly
        //2 for monthly
        //3 for yearly
        //and most likely 4 will be a custom option
        
        //for usertype, 0 is regular user, 1 is group leader
        
        eventList.add(type + "," + userType + "," + userID + "," + year + "," + month + "," + day + "," + eventName + "," + startTime + "," + endTime);
    }
}