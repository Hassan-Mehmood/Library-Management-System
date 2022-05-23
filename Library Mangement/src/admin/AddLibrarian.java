package admin;

import Connection.ConnectDb;
import java.sql.*;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class AddLibrarian implements ActionListener {

  public JFrame frame;
  private JLabel header, lblName, lblPassword, lblEmail, lblCity, lblContact;
  private JTextField nameField, emailField, cityField, contactField;
  private JPasswordField passField;
  private JButton addLib, backBtn;

  AddLibrarian() {
    frame = new JFrame();
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLayout(null);
    frame.setLocationRelativeTo(null);
    frame.setVisible(false);

    createSection();
  }

  public void createSection() {

    header = new JLabel("Add Librarian");
    header.setFont(new Font("SansSerif", Font.PLAIN, 16));
    header.setSize(150, 50);
    header.setLocation((frame.getWidth() - header.getWidth()) / 2, 10);
    frame.add(header);

    lblName = new JLabel("Name");
    lblName.setFont(new Font("SansSerif", Font.PLAIN, 12));
    lblName.setSize(100, 50);
    lblName.setLocation(75, 100);
    frame.add(lblName);

    nameField = new JTextField();
    nameField.setBounds(150, 110, 200, 30);
    frame.add(nameField);

    lblPassword = new JLabel("Password");
    lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
    lblPassword.setSize(100, 50);
    lblPassword.setLocation(75, 150);
    frame.add(lblPassword);

    passField = new JPasswordField();
    passField.setBounds(150, 160, 200, 30);
    frame.add(passField);

    lblEmail = new JLabel("E-mail");
    lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
    lblEmail.setSize(100, 50);
    lblEmail.setLocation(75, 200);
    frame.add(lblEmail);

    emailField = new JTextField();
    emailField.setBounds(150, 210, 200, 30);
    frame.add(emailField);

    lblCity = new JLabel("City");
    lblCity.setFont(new Font("SansSerif", Font.PLAIN, 12));
    lblCity.setSize(100, 50);
    lblCity.setLocation(75, 250);
    frame.add(lblCity);

    cityField = new JTextField();
    cityField.setBounds(150, 260, 200, 30);
    frame.add(cityField);

    lblContact = new JLabel("Contact");
    lblContact.setFont(new Font("SansSerif", Font.PLAIN, 12));
    lblContact.setSize(100, 50);
    lblContact.setLocation(75, 300);
    frame.add(lblContact);

    contactField = new JTextField();
    contactField.setBounds(150, 310, 200, 30);
    frame.add(contactField);

    addLib = new JButton("Add Librarian");
    addLib.setLocation(175, 350);
    addLib.setSize(140, 45);
    frame.add(addLib);    
    addLib.addActionListener(this);
    
    backBtn = new JButton("Back");
    backBtn.setLocation(195, 410);
    backBtn.setSize(100, 30);
    frame.add(backBtn);
    backBtn.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) 
  {  
      if (e.getSource().equals(addLib)) {
          String password = String.valueOf(passField.getPassword());
          // emailField, cityField, contactField
          if (nameField.getText().equals("")|| password.equals("") || 
              emailField.getText().equals("") || cityField.getText().equals("")|| 
              contactField.getText().equals("")) 
          {
              JOptionPane.showMessageDialog(frame, "Some fields are empty");
          } else 
          {
              Random rand = new Random();
              String randomID = String.valueOf(rand.nextInt(1000));
              System.out.println(randomID);
              
              String SQL = "INSERT INTO LIBRARIAN(ID,NAME,PASSWORD,EMAIL,CITY,CONTACT) VALUES(?,?,?,?,?,?)";
              
              try 
              {
                  Connection conn = ConnectDb.connectDatabase();
                  
                  PreparedStatement pstmt = conn.prepareStatement(SQL);
                  
                  pstmt.setString(1, randomID);
                  pstmt.setString(2, nameField.getText());
                  pstmt.setString(3, password);
                  pstmt.setString(4, emailField.getText());
                  pstmt.setString(5, cityField.getText());
                  pstmt.setString(6, contactField.getText());
                  
                  int affectedRows = pstmt.executeUpdate();
                  if(affectedRows > 0)
                  {
                      JOptionPane.showMessageDialog(frame, "Librarian Added Successfully");
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(frame, "Failed to add Librarian");
                  }
                  
                  conn.close();
                  System.out.println("Connection Closed");
              } catch (Exception ex)
              {
                  System.out.println(ex.getMessage());
              }
          }
      }
      if(e.getSource().equals(backBtn))
      {
          frame.dispose();
          AdminMenu menu = new AdminMenu();
          menu.frame.setVisible(true);
      }      
  }
}

