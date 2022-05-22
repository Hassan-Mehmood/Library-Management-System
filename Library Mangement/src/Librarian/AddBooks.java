package Librarian;

import Connection.ConnectDb;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddBooks implements ActionListener {

    public JFrame frame;
    private JLabel callNo, name, author, publisher, quantity;
    private JTextField callField, nameField, authField, pubField, quantField;
    private JButton addBook, backBtn;

    public AddBooks()
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
        callNo = new JLabel("Call No: ");
        callNo.setBounds(75, 100, 50, 25);
        frame.add(callNo);

        callField = new JTextField();
        callField.setBounds(150, 100, 125, 25);
        frame.add(callField);

        name = new JLabel("Name: ");
        name.setBounds(75, 145, 50, 25);
        frame.add(name);

        nameField = new JTextField();
        nameField.setBounds(150, 145, 125, 25);
        frame.add(nameField);

        author = new JLabel("Author: ");
        author.setBounds(75, 190, 50, 25);
        frame.add(author);

        authField = new JTextField();
        authField.setBounds(150, 190, 125, 25);
        frame.add(authField);

        publisher = new JLabel("Publisher: ");
        publisher.setBounds(75, 235, 75, 25);
        frame.add(publisher);

        pubField = new JTextField();
        pubField.setBounds(150, 235, 125, 25);
        frame.add(pubField);

        quantity = new JLabel("Quantity: ");
        quantity.setBounds(75, 280, 75, 25);
        frame.add(quantity);

        quantField = new JTextField();
        quantField.setBounds(150, 280, 125, 25);
        frame.add(quantField);

        addBook = new JButton("Add Book");
        addBook.setBounds(150, 320, 125, 30);
        addBook.addActionListener(this);
        frame.add(addBook);

        backBtn = new JButton("Back");
        backBtn.setBounds(150, 360, 125, 30);
        backBtn.addActionListener(this);
        frame.add(backBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(addBook) && (callField.getText().equals("") || nameField.getText().equals("") || authField.getText().equals("")
                || pubField.getText().equals("") || quantField.getText().equals("")))
        {
            JOptionPane.showMessageDialog(frame, "Please enter all the fields");
            return;
        }

        if (e.getSource().equals(addBook))
        {
            Connection conn = ConnectDb.connectDatabase();

            String SQL = "INSERT INTO BOOKS(CALLNO, NAME, AUTHOR, PUBLISHER, QUANTITY) VALUES (?,?,?,?,?)";

            try
            {
                PreparedStatement pstmt = conn.prepareCall(SQL);

                pstmt.setString(1, callField.getText());
                pstmt.setString(2, nameField.getText());
                pstmt.setString(3, authField.getText());
                pstmt.setString(4, pubField.getText());
                pstmt.setString(5, quantField.getText());

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0)
                {
                    JOptionPane.showMessageDialog(frame, "Book added");
                } else
                {
                    JOptionPane.showMessageDialog(frame, "Failed to add Book");
                }

                conn.close();
                System.out.println("Connection Closed");

            } catch (SQLException ex)
            {
                Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(backBtn))
        {
            frame.dispose();
            LibrarianMenu menu = new LibrarianMenu();
            menu.frame.setVisible(true);
        }
    }
}
