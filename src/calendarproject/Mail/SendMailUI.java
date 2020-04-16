package calendarproject.Mail;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SendMailUI {
    
    //create everything
        ArrayList<String> emailList = new ArrayList();
        JFrame emailUI = new JFrame("Email sender 9000");
        JLabel emailUILabel = new JLabel("Please enter recipiant Email:");
        JTextField emailUIInput = new JTextField();
        JButton emailAddToList = new JButton("Add to List");
        JButton emailRemoveFromList = new JButton("Remove selected from List");
        DefaultListModel emailUIListModel = new DefaultListModel();
        JList emailUIList = new JList(emailUIListModel);
        JButton emailUISendButton = new JButton("Send E-mail");
        JLabel emailUIMessageSubjectLabel = new JLabel("Please enter Email subject:");
        JTextField emailUIMessageSubject = new JTextField();
        JLabel emailUIMessageBodyLabel = new JLabel("Please enter Email body contents:");
        JTextArea emailUIMessageBody = new JTextArea();
        
    public void sendIt() {
        //Some things need to be created here, else I would be getting errors
        emailUI.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        emailUIMessageBody.setBorder(BorderFactory.createLineBorder(Color.black));
                
        //create action listeners        
        emailAddToList.addActionListener(new emailAddToList_Action());
        emailRemoveFromList.addActionListener(new emailRemoveFromList_Action());
        emailUISendButton.addActionListener(new emailUISendButton_Action());

        //formatting for the panel, and adding everything in
        emailUI.setSize(250, 400);
        emailUIList.setBorder(BorderFactory.createLineBorder(Color.black));
        emailUIList.setSize (100, 300);
        emailUIMessageBody.setColumns(10);
        emailUIMessageBody.setRows(4);
        
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 1;
        emailUI.add(emailUILabel,grid);
        
        grid.gridy = 1;
        emailUI.add(emailUIInput,grid);
        
        grid.gridy = 2;
        emailUI.add(emailAddToList,grid);
        
        grid.gridy = 3;
        emailUI.add(emailRemoveFromList,grid);
        
        grid.gridy = 4;
        emailUI.add(emailUIList,grid);
        
        grid.gridy = 5;
        emailUI.add(emailUIMessageSubjectLabel,grid);
        
        grid.gridy = 6;
        emailUI.add(emailUIMessageSubject,grid);
        
        grid.gridy = 7;
        emailUI.add(emailUIMessageBodyLabel,grid);
        
        grid.gridy = 8;
        emailUI.add(emailUIMessageBody,grid);
        
        grid.gridy = 9;
        emailUI.add(emailUISendButton,grid);
        
        //And pack it
        //emailUI.pack();
        
        //make window visible
        emailUI.setVisible(true);
    }

    private class emailAddToList_Action implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            emailUIListModel.addElement(emailUIInput.getText());
            emailList.add(emailUIInput.getText());
            emailUIInput.setText("");
        }
    }

    private class emailRemoveFromList_Action implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            if (emailUIList.getSelectedIndex() != -1) {
                emailList.remove(emailUIList.getSelectedIndex());
                emailUIListModel.remove(emailUIList.getSelectedIndex());
            }
        }
    }

    private class emailUISendButton_Action implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            Mail.sendMail((emailList.toArray()), "calanderp84@gmail.com", "sPqG9MHdj3Hur7sP", emailUIMessageSubject.getText(), emailUIMessageBody.getText());
            emailUI.dispose(); //yeeeeeeet
        }
    }

}
