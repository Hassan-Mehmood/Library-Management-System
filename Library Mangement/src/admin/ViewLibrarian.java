package admin;

import Connection.ConnectDb;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ViewLibrarian {
    
    public JFrame frame;
    private Connection conn;
    private String query;
    private JButton backBtn;

    public ViewLibrarian() 
    {
        frame = new JFrame();
        frame.setSize(850, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        
        createView();
    }
    
    private void createView()
    {
        conn = ConnectDb.connectDatabase();

        query = "SELECT * FROM LIBRARIAN";

        String columns[] = {"ID", "NAME", "Password", "E-Mail", "City", "Contact"};
        String data[][] = new String[8][6];


        Statement stm;
        try {

            stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);

            int i = 0;
            while (res.next()) {
                int id = res.getInt("ID");
                String name = res.getString("NAME");
                String password = res.getString("PASSWORD");
                String email = res.getString("EMAIL");
                String city = res.getString("CITY");
                String contact = res.getString("CONTACT");
                data[i][0] = id + "";
                data[i][1] = name;
                data[i][2] = password;
                data[i][3] = email;
                data[i][4] = city;
                data[i][5] = contact;
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ViewLibrarian.class.getName()).log(Level.SEVERE, null, ex);
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);

        frame.add(pane);



    }

}
