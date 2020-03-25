package calendarproject;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public final class CalendarProject extends JFrame {
    
    //Height and width used by main window size
    private static final int HEIGHT = 500;
    private static final int WIDTH = 800;

//protected Component monthlyCalendarTab(){
//    MonthlyCalendar.run();
//    JPanel panel = new JPanel();
//    panel.add(MonthlyCalendar.pane);
//    return panel;
//}

//protected Component weeklyCalendarTab(){
//    JPanel panel = new JPanel();
//    JLabel test = new JLabel("Test");
//    panel.add(test);
//    return panel;
//}

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
    
    //JTabbedPane tabbedPane = new JTabbedPane();
    
    //Component panel1 = weeklyCalendarTab();
    //tabbedPane.addTab("Weekly Calandar",panel1);
    
    //Component panel2 = monthlyCalendarTab();
    Component panel2 = test();
    //tabbedPane.addTab("Monthly Calandar",panel2);
    
    //Cont.add(tabbedPane);
    cont.add(panel2);
}

    public static void main(String[] args){
        CalendarProject yeet = new CalendarProject();
        
    }}