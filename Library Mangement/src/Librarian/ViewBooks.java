package Librarian;

import Connection.ConnectDb;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewBooks {

    public JFrame frame;
    private Connection conn;
    private String query;
    private JButton backBtn;

    public ViewBooks()
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

        query = "SELECT * FROM BOOKS";

        String columns[] = {"CALL NO", "NAME", "AUTHOR", "PUBLISHER", "QUANTITY"};
        String data[][] = new String[8][5];

        Statement stm;
        try {

            stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);

            int i = 0;
            while (res.next()) {
                String callNo = res.getString("CALLNO");
                String name = res.getString("NAME");
                String author = res.getString("AUTHOR");
                String publisher = res.getString("PUBLISHER");
                String quantity = res.getString("QUANTITY");

                data[i][0] = callNo;
                data[i][1] = name;
                data[i][2] = author;
                data[i][3] = publisher;
                data[i][4] = quantity;
                i++;
            }

            conn.close();
            System.out.println("Connection Closed");

        } catch (SQLException ex) {
            Logger.getLogger(ViewBooks.class.getName()).log(Level.SEVERE, null, ex);
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);

        frame.add(pane);
    }

}
