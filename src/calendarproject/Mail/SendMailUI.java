package calendarproject.Mail;

import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class SendMailUI {

    public void sendIt() {
        
        //create everything
        HashSet<String> emailList = new HashSet();
        JFrame emailUI = new JFrame();
        JLabel emailUILabel = new JLabel("Please enter recipiant Email:");
        JTextField emailUIInput = new JTextField();
        JButton emailAddToList = new JButton();
        JButton emailRemoveFromList = new JButton();
        DefaultListModel emailUIListModel = new DefaultListModel();
        JList emailUIList = new JList(emailUIListModel);
        JButton emailUISendButton = new JButton();
        
        //define the size of everything, and add to the frame
        emailUI.setSize(300, 300);
        

        Mail.sendMail((emailList.toArray()), " calanderp84@gmail.com", "sPqG9MHdj3Hur7sP", "Event Reminder", "Message");
    }

}
