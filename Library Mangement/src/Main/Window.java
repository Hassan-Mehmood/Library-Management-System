package Main;

import Librarian.LibrarianLogin;
import admin.AdminLogin;
import java.awt.Font;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame implements ActionListener {

    private JLabel header;
    private JButton adminBtn;
    private JButton librarianBtn;

    public Window()
    {   // Set Basic Window
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Buttons();
    }

    // Create the admin and librarian buttons
    private void Buttons()
    {
        header = new JLabel("Library Management");
        header.setFont(new Font("SansSerif", Font.PLAIN, 16));
        header.setSize(150, 50);
        header.setLocation((this.getWidth() - header.getWidth()) / 2, 10); // To center the text horizantally
        this.add(header);

        adminBtn = new JButton("Admin Login");
        adminBtn.setSize(150, 50);
        adminBtn.setLocation((this.getWidth() - adminBtn.getWidth()) / 2, 140);
        this.add(adminBtn);

        librarianBtn = new JButton("Librarian Login");
        librarianBtn.setSize(150, 50);
        librarianBtn.setLocation((this.getWidth() - librarianBtn.getWidth()) / 2, 220);
        this.add(librarianBtn);

        adminBtn.addActionListener(this);
        librarianBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource().equals(adminBtn)) {
            this.dispose();
            AdminLogin login = new AdminLogin();
            login.frame.setVisible(true);
        } else if (e.getSource().equals(librarianBtn)) {

            this.dispose();
            LibrarianLogin login = new LibrarianLogin();
            login.frame.setVisible(true);
        }

    }

}
