package admin;

import Connection.ConnectDb;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;


public class DeleteLibrarian implements ActionListener{
    
    public JFrame frame;
    private JLabel enterId;
    private JTextField field;
    private JButton deleteBtn, backBtn;
    
    public DeleteLibrarian() 
    {    
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        
        createView();
    }
    
    private void createView() 
    {
        enterId = new JLabel("Enter ID");
        enterId.setBounds(110, 50, 50, 30);
        frame.add(enterId);
        
        field = new JTextField();
        field.setBounds(175, 50, 125, 30);
        frame.add(field);
        
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(130, 120, 75, 30);
        deleteBtn.addActionListener(this);
        frame.add(deleteBtn);
    
        backBtn = new JButton("Back");
        backBtn.setBounds(225, 120, 75, 30);
        backBtn.addActionListener(this);
        frame.add(backBtn);   
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource().equals(deleteBtn))
        {
            
            if(field.getText().equals(""))
            {
                JOptionPane.showMessageDialog(frame, "Please enter the id");
            } else 
            {
                String SQL = "DELETE FROM LIBRARIAN WHERE ID = ?";
                
                try {
                    
                    Connection conn = ConnectDb.connectDatabase();
                    
                    PreparedStatement pstmt = conn.prepareCall(SQL);
                    pstmt.setString(1,field.getText());
                    
                    int affectedRows = pstmt.executeUpdate();
                    
                    if(affectedRows > 0) 
                    {
                        JOptionPane.showMessageDialog(frame, "Librarian Deleted");
                    } else 
                    {
                        JOptionPane.showMessageDialog(frame, "Failed to delete Librarian");
                    }
                    
                    conn.close();
                    System.out.println("Connection Closed");
                    
                } catch (Exception ex) {
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