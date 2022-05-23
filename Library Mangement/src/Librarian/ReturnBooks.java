package Librarian;

import Connection.ConnectDb;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class ReturnBooks implements ActionListener {

    public JFrame frame;
    private JLabel header, callNo, studentId;
    private JTextField callField, idField;
    private JButton returnBtn, backBtn;

    public ReturnBooks()
    {
        frame = new JFrame("Return Books");
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
        header = new JLabel("Return Books");
        header.setFont(new Font("SansSerif", Font.PLAIN, 16));
        header.setSize(150, 50);
        header.setLocation((frame.getWidth() - header.getWidth()) / 2, 10);
        frame.add(header);

        callNo = new JLabel("Book CallNo: ");
        callNo.setBounds(75, 100, 100, 25);
        frame.add(callNo);

        callField = new JTextField();
        callField.setBounds(200, 100, 125, 25);
        frame.add(callField);

        studentId = new JLabel("Student ID: ");
        studentId.setBounds(75, 150, 100, 25);
        frame.add(studentId);

        idField = new JTextField();
        idField.setBounds(200, 150, 125, 25);
        frame.add(idField);

        returnBtn = new JButton("Return Book");
        returnBtn.setBounds(150, 320, 125, 30);
        returnBtn.addActionListener(this);
        frame.add(returnBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(150, 360, 125, 30);
        backBtn.addActionListener(this);
        frame.add(backBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(returnBtn) && (callField.getText().equals("") || idField.getText().equals(""))) {
            JOptionPane.showMessageDialog(frame, "Please enter all the fields");
            return;
        }
        if (e.getSource().equals(returnBtn)) {
            Connection conn = ConnectDb.connectDatabase();

            String SQL = "DELETE FROM ISSUEDBOOKS WHERE CALLNO = ? AND STUDENTID = ?";

            try {

                PreparedStatement pstmt = conn.prepareCall(SQL);

                pstmt.setString(1, callField.getText());
                pstmt.setString(2, idField.getText());

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(frame, "Books returned");
                } else {
                    JOptionPane.showMessageDialog(frame, "Sorry, Unable to return the books");
                }

                conn.close();
                System.out.println("Connection Closed");

            } catch (SQLException ex) {
                Logger.getLogger(ReturnBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(backBtn)) {
            frame.dispose();
            LibrarianMenu menu = new LibrarianMenu();
            menu.frame.setVisible(true);
        }

    }

}
