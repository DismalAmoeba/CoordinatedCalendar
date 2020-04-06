package calendarproject;

import java.util.Arrays;
import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class DataHandler {
    DataHandler use = new DataHandler();

    public static ArrayList<String> eventList = new ArrayList<String>();
    
    public static void addToList(int type, int userType, int userID, int year, int month, int day, String eventName, int startTime, int endTime) throws IOException{
        //the first constructor: type is to differenciate if an event happens once or on a certain interval
        //0 for one time
        //1 for weekly
        //2 for monthly
        //3 for yearly
        //and most likely 4 will be a custom option
        
        //for usertype, 0 is regular user, 1 is group leader
        
        eventList.add(type + "," + userType + "," + userID + "," + year + "," + month + "," + day + "," + eventName + "," + startTime + "," + endTime);
        //Reminder: The calendar counts January as month 0, and December as month 11
        write();
        //When you put an event in, it writes it to a text file. 
        System.out.println(Arrays.toString(eventList.toArray()));
        //for testing purposes
    }
    
    public static void write() throws IOException{
            try
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter("calendar.txt"));
                for (String str : eventList)
                {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
            }
            catch (IOException e)
            {
                System.out.println("");
            }
                
        }
    }
