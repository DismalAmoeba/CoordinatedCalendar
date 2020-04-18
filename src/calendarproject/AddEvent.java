package calendarproject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import calendarproject.login.LoginForm;

public class AddEvent extends JFrame{
//Height and width used by window
int HEIGHT = 300;
int WIDTH = 200;

//create ALL of the things
JFrame addFrame =  new JFrame("Add Event");
JPanel addPanel = new JPanel();
JLabel dateLabel;
JLabel eventNameLabel = new JLabel("Event Name:");
JTextField eventNameField = new JTextField(18);
JLabel startTimeLabel = new JLabel("Start Time:");
JTextField startTimeField = new JTextField(5);
JLabel endTimeLabel = new JLabel("End Time:");
JTextField endTimeField = new JTextField(5);
JButton addEventButton = new JButton("Add Event");

//grab values from the calendar
int day = MonthlyCalendar.tblDay;
int month = MonthlyCalendar.currentMonth;
int year = MonthlyCalendar.currentYear;
LoginForm test = new LoginForm();

    public JFrame addOneTimeEvent(int day, int month, int year){
        
        addEventButton.addActionListener(new addEventButton_Action());
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        dateLabel = new JLabel("Selected date:\r\n" + day + "/" + (month + 1) + "/" + year);
        
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 2;
        addPanel.add(dateLabel,grid);
        
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridx = 0;
        grid.gridy = 1;
        grid.gridwidth = 1;
        addPanel.add(eventNameLabel,grid);
        
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridx = 1;
        grid.gridy = 1;
        addPanel.add(eventNameField,grid);
        
        grid.gridx = 0;
        grid.gridy = 2;
        addPanel.add(startTimeLabel,grid);
        
        grid.gridx = 1;
        grid.gridy = 2;
        addPanel.add(startTimeField,grid);
        
        grid.gridx = 0;
        grid.gridy = 3;
        addPanel.add(endTimeLabel,grid);
        
        grid.gridx = 1;
        grid.gridy = 3;
        addPanel.add(endTimeField,grid);
        
        grid.gridx = 0;
        grid.gridy = 4;
        grid.gridwidth = 2;
        addPanel.add(addEventButton,grid);
        
        addFrame.add(addPanel);
        addFrame.setSize(HEIGHT,WIDTH);
        addFrame.setVisible(true);
        addFrame.pack();
        return addFrame;        
    }

    class addEventButton_Action implements ActionListener{  
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DataHandler.addToList(0, 1, test.username, year, month, day, eventNameField.getText(), Integer.parseInt(startTimeField.getText()), Integer.parseInt(endTimeField.getText()));
                DataHandler.newEntry(0, 1, test.username, year, month, day, eventNameField.getText(), Integer.parseInt(startTimeField.getText()), Integer.parseInt(endTimeField.getText()));
            } catch (IOException ex) {
                Logger.getLogger(AddEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            addFrame.dispose(); //yah yeet
        }
    }
}
