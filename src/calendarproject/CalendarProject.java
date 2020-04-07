package calendarproject;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JInternalFrame;

public final class CalendarProject extends JFrame {
    
    //Height and width used by main window size
    private static final int HEIGHT = 500;
    private static final int WIDTH = 800;

protected Component calendar(){ //This component creates an internal frame for MonthlyCalendar.java
    MonthlyCalendar.run();
    JInternalFrame frame = new JInternalFrame("",false,false,false,false);
    frame.add(MonthlyCalendar.run());
    frame.setSize(HEIGHT,WIDTH);
    frame.setVisible(true);
    frame.setResizable(false);
    return frame;
}

public CalendarProject()
{
    //basic stuff for the window
    setTitle("Calendar Project");
    setSize(WIDTH,HEIGHT);
    setVisible(true);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    Container cont = getContentPane();
    Component panel2 = calendar();
    cont.add(panel2);
    
}

    public static void main(String[] args){
        //Actually run the program
        CalendarProject yeet = new CalendarProject(); 
    }
}