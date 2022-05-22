package Librarian;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class LibrarianLogin implements ActionListener {

    private JLabel header, userName, password;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;
    public JFrame frame;

    public LibrarianLogin()
    {
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);

        loginForm();

    }

    private void loginForm()
    {
        header = new JLabel("Librarian Login Form");
        header.setFont(new Font("SansSerif", Font.PLAIN, 16));
        header.setSize(150, 50);
        header.setLocation((frame.getWidth() - header.getWidth()) / 2, 10);
        frame.add(header);

        userName = new JLabel("User Name:");
        userName.setFont(new Font("SansSerif", Font.PLAIN, 12));
        userName.setSize(100, 50);
        userName.setLocation(75, 100);
        frame.add(userName);

        userField = new JTextField();
        userField.setBounds(150, 110, 200, 30);
        frame.add(userField);

        password = new JLabel("Password:");
        password.setFont(new Font("SansSerif", Font.PLAIN, 12));
        password.setSize(100, 50);
        password.setLocation(75, 180);
        frame.add(password);

        passField = new JPasswordField();
        passField.setBounds(150, 190, 200, 30);
        frame.add(passField);

        loginBtn = new JButton("Login");
        loginBtn.setSize(150, 50);
        loginBtn.setLocation((frame.getWidth() - loginBtn.getWidth()) / 2, 260);
        frame.add(loginBtn);

        loginBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        String userName = userField.getText();
        String password = String.valueOf(passField.getPassword()); // getPassword returns char array

        if (e.getSource().equals(loginBtn))
        {

            if (userName.equals("") || userName.equals("") && password.equals(""))
            {
                frame.dispose();
                LibrarianMenu libMenu = new LibrarianMenu();
                libMenu.frame.setVisible(true);
            }

        }
    }
}
