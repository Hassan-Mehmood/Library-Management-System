package Librarian;

import Connection.ConnectDb;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class IssueBook implements ActionListener {

    public JFrame frame;
    private JLabel callNo, studentId, studentName, studentContact;
    private JTextField callField, idField, nameField, contactField;
    private JButton issueBook, backBtn;

    public IssueBook()
    {
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);

        createMenu();
    }

    public void createMenu()
    {

        callNo = new JLabel("Call No:");
        callNo.setBounds(75, 100, 120, 25);
        frame.add(callNo);

        callField = new JTextField();
        callField.setBounds(180, 100, 125, 25);
        frame.add(callField);

        studentId = new JLabel("Student ID:");
        studentId.setBounds(75, 145, 120, 25);
        frame.add(studentId);

        idField = new JTextField();
        idField.setBounds(180, 145, 125, 25);
        frame.add(idField);

        studentName = new JLabel("Student Name:");
        studentName.setBounds(75, 190, 120, 25);
        frame.add(studentName);

        nameField = new JTextField();
        nameField.setBounds(180, 190, 125, 25);
        frame.add(nameField);

        studentContact = new JLabel("Student Contact:");
        studentContact.setBounds(75, 235, 120, 25);
        frame.add(studentContact);

        contactField = new JTextField();
        contactField.setBounds(180, 235, 125, 25);
        frame.add(contactField);

        issueBook = new JButton("Issue Book");
        issueBook.setBounds(180, 320, 125, 30);
        issueBook.addActionListener(this);
        frame.add(issueBook);

        backBtn = new JButton("Back");
        backBtn.setBounds(180, 360, 125, 30);
        backBtn.addActionListener(this);
        frame.add(backBtn);
    }

    private void issuebook(String SQL)
    {
        JOptionPane.showMessageDialog(frame, SQL);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(issueBook) && (callField.getText().equals("")
                || nameField.getText().equals("") || idField.getText().equals("") || contactField.getText().equals("")))
        {
            JOptionPane.showMessageDialog(frame, "Please enter all the fields");
            return;
        }

        if (e.getSource().equals(issueBook))
        {
            Connection conn = ConnectDb.connectDatabase();

            // Checking if the Book being issued is available in the database or not
            String bookCheck = "SELECT * FROM BOOKS WHERE CALLNO = ?";
            String SQL = "INSERT INTO ISSUEDBOOKS(CALLNO,STUDENTID,STUDENTNAME,STUDENTCONTACT) VALUES(?,?,?,?)";

            try
            {
                PreparedStatement pstmt = conn.prepareCall(bookCheck);

                pstmt.setString(1, callField.getText());

                // executeQuery() method is used to execute statements that returns tabular data (example SELECT). It returns an object of the class ResultSet.
                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next())
                {
                    issuebook(SQL);
                } else
                {
                    System.out.println("Does not exists");
                }

                conn.close();
                System.out.println("Connection Closed");

            } catch (SQLException ex)
            {
                Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
