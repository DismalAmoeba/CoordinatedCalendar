package calendarproject;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JInternalFrame;

public final class CalendarProject extends JFrame {
    
    //Height and width used by main window size
    private static final int HEIGHT = 500;
    private static final int WIDTH = 800;

protected Component test(){
    MonthlyCalendar.run();
    JInternalFrame test = new JInternalFrame("yes",false,false,false,false);
    test.add(MonthlyCalendar.run());
    test.setSize(500,500);
    test.setVisible(true);
    return test;
}

public CalendarProject()
{
    //basic stuff for the window
    setTitle("Calendar Project");
    setSize(WIDTH,HEIGHT);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    Container cont = getContentPane();
    Component panel2 = test();
    cont.add(panel2);
}

    public static void main(String[] args){
        //Actually run the program
        CalendarProject yeet = new CalendarProject();
        
    }}