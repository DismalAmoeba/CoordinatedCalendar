package calendarproject;

/**
 *
 * @author cmjun
 */
import static calendarproject.DataHandler.eventList;
import calendarproject.Mail.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class MonthlyCalendar extends JFrame {

    static JLabel lblMonth, lblYear;
    static JButton btnPrev, btnNext, emailButton, loadButton, logoutButton, saveButton, deleteEventButton, mergeButton;
    static JTable tblCalendar;
    static JComboBox cmbYear;
    static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane stblCalendar; //The scrollpane
    static JPanel pnlCalendar;
    static int realYear, realMonth, realDay, currentYear, currentMonth, tblDay;
    static JList eventViewer;
    static DefaultListModel listModel = new DefaultListModel();
    static SimpleDigitalClock clock1;

    /**
     * @return
     */
    public static JPanel run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        //Prepare frame
        frmMain = new JFrame("Monthly Calendar"); //Create frame
        frmMain.setSize(500, 500); //Set size to 400x400 pixels
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        //frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked

        //store all saved events to the hashlist for the JList
        listModel.addElement("this should not be seen");
        listModel.remove(0);

        //Create controls
        lblMonth = new JLabel("January");
        lblYear = new JLabel("Change year:");
        cmbYear = new JComboBox();
        btnPrev = new JButton("<<");
        btnNext = new JButton(">>");
        mtblCalendar = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        pnlCalendar = new JPanel(null);
        emailButton = new JButton("Email");
        loadButton = new JButton("Load from file");
        logoutButton = new JButton("LOGOUT");
        saveButton = new JButton("Save to file");
        eventViewer = new JList(listModel);
        eventViewer.setLayoutOrientation(JList.VERTICAL);
        eventViewer.setBorder(BorderFactory.createLineBorder(Color.black));
        deleteEventButton = new JButton("Remove Event");
        clock1 = new SimpleDigitalClock();
        mergeButton = new JButton("Merge Calendars");

        //Set border
        pnlCalendar.setBorder(BorderFactory.createTitledBorder(""));

        //Register action listeners
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action());
        cmbYear.addActionListener(new cmbYear_Action());
        tblCalendar.addMouseListener(new tblCalendar_Action());
        emailButton.addActionListener(new emailButton_Action());
        loadButton.addActionListener(new loadButton_Action());
        logoutButton.addActionListener(new logoutButton_Action());
        saveButton.addActionListener(new saveButton_Action());
        deleteEventButton.addActionListener(new deleteEventButton_Action());
        mergeButton.addActionListener(new mergeButton_Action());


        //Add controls to pane
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);
        pnlCalendar.add(lblYear);
        pnlCalendar.add(cmbYear);
        pnlCalendar.add(btnPrev);
        pnlCalendar.add(btnNext);
        pnlCalendar.add(stblCalendar);
        pnlCalendar.add(emailButton);
        pnlCalendar.add(loadButton);
        pnlCalendar.add(logoutButton);
        pnlCalendar.add(saveButton);
        pnlCalendar.add(eventViewer);
        pnlCalendar.add(deleteEventButton);
        pnlCalendar.add(clock1);
        pnlCalendar.add(mergeButton);

        //Set bounds
        pnlCalendar.setBounds(0, 0, 320, 335);
        lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25, 100, 25);
        lblYear.setBounds(10, 305, 80, 20);
        cmbYear.setBounds(230, 305, 80, 20);
        btnPrev.setBounds(10, 25, 50, 25);
        btnNext.setBounds(260, 25, 50, 25);
        stblCalendar.setBounds(10, 50, 300, 250);
        emailButton.setBounds(8, 330, 100, 30);
        loadButton.setBounds(118, 330, 100, 30);
        logoutButton.setBounds(8, 370, 100, 30);
        saveButton.setBounds(118, 370, 100, 30);
        eventViewer.setBounds(340, 25, 300, 300);
        deleteEventButton.setBounds(358, 330, 120, 30);
        clock1.setBounds(388,365,100,50);
        mergeButton.setBounds(228,330,120,70);

        //Make frame visible
        frmMain.setResizable(false);
        frmMain.setVisible(false); //double check to make sure this is set to false, otherwise this renders twice -Alex

        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;

        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (int i = 0; i < 7; i++) {
            mtblCalendar.addColumn(headers[i]);
        }

        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background

        //No resize/reorder
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);

        //Single cell selection
        tblCalendar.setColumnSelectionAllowed(true);
        tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Set row/column count
        tblCalendar.setRowHeight(38);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);

        //Populate table
        for (int i = realYear - 100; i <= realYear + 100; i++) {
            cmbYear.addItem(String.valueOf(i));
        }

        //Refresh calendar
        refreshCalendar(realMonth, realYear); //Refresh calendar

        return pnlCalendar;
    }

    static public void refreshCalendar(int month, int year) {
        //Variables
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month

        //Allow/disallow buttons
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear - 10) {
            btnPrev.setEnabled(false);
        } //Too early
        if (month == 11 && year >= realYear + 100) {
            btnNext.setEnabled(false);
        } //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        //Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mtblCalendar.setValueAt(null, i, j);
            }
        }

        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Draw calendar
        for (int i = 1; i <= nod; i++) {
            int row = (i + som - 2) / 7;
            int column = (i + som - 2) % 7;
            mtblCalendar.setValueAt(i, row, column);
        }

        //Apply renderers
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());

    }

    static class tblCalendarRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6) { //Week-end
                setBackground(new Color(255, 220, 220));
            } else { //Week
                setBackground(new Color(255, 255, 255));
            }
            if (value != null) {
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear) { //Today
                    setBackground(new Color(220, 220, 255));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }

    static class btnPrev_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentMonth == 0) { //Back one year
                currentMonth = 11;
                currentYear -= 1;
            } else { //Back one month
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class btnNext_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentMonth == 11) { //Foward one year
                currentMonth = 0;
                currentYear += 1;
            } else { //Foward one month
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class cmbYear_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (cmbYear.getSelectedItem() != null) {
                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }

    static class tblCalendar_Action implements MouseListener {

        //There has to be a better way to do this
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        Object tblData = null;

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tblCalendar.rowAtPoint(e.getPoint());
            int col = tblCalendar.columnAtPoint(e.getPoint());
            tblData = tblCalendar.getValueAt(row, col);
            if (row >= 0 && col >= 0 && tblData != null) {
                tblDay = (int) tblData;
                AddEvent ae = new AddEvent();
                ae.addOneTimeEvent(tblDay, currentMonth, currentYear);
            }
        }
    }

    private static class emailButton_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SendMailUI fullSend = new SendMailUI();
            fullSend.sendIt();
        }
    }

    private static class loadButton_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DataHandler.read();
                DataHandler.arrayToString();
            } catch (IOException ex) {
                Logger.getLogger(MonthlyCalendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static class saveButton_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DataHandler.write();
            } catch (IOException ex) {
                Logger.getLogger(MonthlyCalendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static class logoutButton_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private static class deleteEventButton_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (eventViewer.getSelectedIndex() != -1) {
                eventList.remove(eventViewer.getSelectedIndex());
                listModel.remove(eventViewer.getSelectedIndex());
            }
        }
    }
    
    static class SimpleDigitalClock extends JPanel {  
        String stringTime;  
        int hour, minute, second;  
        String aHour = "";  
        String bMinute = "";  
        String cSecond = "";  
        public void setStringTime(String abc) {  
            this.stringTime = abc;  
        }  
        public int Number(int a, int b) {  
            return (a <= b) ? a : b;  
        }  
        SimpleDigitalClock() {  
            Timer t = new Timer(1000, new ActionListener() {  
                public void actionPerformed(ActionEvent e) {  
                    repaint();  
                }  
            });  
            t.start();  
        }  
        @Override  
        public void paintComponent(Graphics v) {  
            super.paintComponent(v);  
            Calendar rite = Calendar.getInstance();  
            hour = rite.get(Calendar.HOUR_OF_DAY);  
            minute = rite.get(Calendar.MINUTE);  
            second = rite.get(Calendar.SECOND);  
            if (hour < 10) {  
                this.aHour = "0";  
            }  
            if (hour >= 10) {  
                this.aHour = "";  
            }  
            if (minute < 10) {  
                this.bMinute = "0";  
            }  
            if (minute >= 10) {  
                this.bMinute = "";  
            }  
            if (second < 10) {  
                this.cSecond = "0";  
            }  
            if (second >= 10) {  
                this.cSecond = "";  
            }  
            setStringTime(aHour + hour + ":" + bMinute + minute + ":" + cSecond + second);  
            v.setColor(Color.BLACK);  
            int length = Number(this.getWidth(), this.getHeight());  
            Font Font1 = new Font("SansSerif", Font.PLAIN, length / 5);  
            v.setFont(Font1);  
            v.drawString(stringTime, (int) length / 6, length / 2);  
        }  
        @Override  
        public Dimension getPreferredSize() {  
            return new Dimension(200, 200);  
        }  
    }  
  
    private static class mergeButton_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DataHandler.merge();
            } catch (IOException ex) {
                Logger.getLogger(MonthlyCalendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


