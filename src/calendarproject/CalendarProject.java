package calendarproject;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public final class CalendarProject extends JFrame {
    
    //Height and width used by main window size
    private static final int HEIGHT = 500;
    private static final int WIDTH = 800;

protected Component makeTextPanel(String text) { //delete before midterm presentation
    JPanel panel = new JPanel(false);            //code copied for prototype purposes
    JLabel filler = new JLabel(text);
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new GridLayout(1, 1));
    panel.add(filler);
    return panel;
}

protected Component monthlyCalendarTab(){
    JPanel panel = new JPanel();
    panel.add(MonthlyCalendar.run());
    return panel;
}

protected Component weeklyCalendarTab(){
    JPanel panel = new JPanel();
    JLabel test = new JLabel("Test");
    panel.add(test);
    return panel;
}

public CalendarProject()
{
    //basic stuff for the window
    setTitle("Calendar Project");
    setSize(WIDTH,HEIGHT);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    Container Cont = getContentPane();
    
    JTabbedPane tabbedPane = new JTabbedPane();
    
    Component panel1 = weeklyCalendarTab();
    tabbedPane.addTab("Weekly Calandar",panel1);
    
    Component panel2 = monthlyCalendarTab();
    tabbedPane.addTab("Monthly Calandar",panel2);
    
    Cont.add(tabbedPane);
}

    public static void main(String[] args){
        CalendarProject yeet = new CalendarProject();
        
    }
}
