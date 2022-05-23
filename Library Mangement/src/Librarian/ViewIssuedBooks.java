package Librarian;

import Connection.ConnectDb;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewIssuedBooks {

    public JFrame frame;
    private Connection conn;
    private String query;
    private JButton backBtn;

    public ViewIssuedBooks()
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

    public void createView()
    {
        conn = ConnectDb.connectDatabase();

        query = "SELECT * FROM ISSUEDBOOKS";

        String columns[] = {"CALL NO", "STUDENT ID", "STUDENT NAME", "STUDENT CONTACT"};
        String data[][] = new String[8][4];

        Statement stm;
        try {

            stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);

            int i = 0;
            while (res.next()) {
                String callNo = res.getString("CALLNO");
                String id = res.getString("STUDENTID");
                String name = res.getString("STUDENTNAME");
                String contact = res.getString("STUDENTCONTACT");

                data[i][0] = callNo;
                data[i][1] = id;
                data[i][2] = name;
                data[i][3] = contact;

                i++;
            }

            conn.close();
            System.out.println("Connection Closed");
        } catch (SQLException ex) {
            Logger.getLogger(ViewIssuedBooks.class.getName()).log(Level.SEVERE, null, ex);
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);

        frame.add(pane);
    }

}
