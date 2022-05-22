package admin;

import Main.Window;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class AdminMenu implements ActionListener {

  public JFrame frame;
  private JButton addLib, viewLib, delLib, logout;
  private JLabel header;

  public AdminMenu() 
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

  private void createMenu() {

    header = new JLabel("Admin Section");
    header.setFont(new Font("SansSerif", Font.PLAIN, 16));
    header.setSize(150, 50);
    header.setLocation((frame.getWidth() - header.getWidth()) / 2, 10);
    frame.add(header);

    addLib = new JButton("Add Librarian");
    addLib.setSize(150, 50);
    addLib.setLocation((frame.getWidth() - addLib.getWidth()) / 2, 120);
    addLib.addActionListener(this);
    frame.add(addLib);

    viewLib = new JButton("View Librarian");
    viewLib.setSize(150, 50);
    viewLib.setLocation((frame.getWidth() - viewLib.getWidth()) / 2, 200);
    viewLib.addActionListener(this);
    frame.add(viewLib);

    delLib = new JButton("Delete Librarian");
    delLib.setSize(150, 50);
    delLib.setLocation((frame.getWidth() - delLib.getWidth()) / 2, 280);
    delLib.addActionListener(this);
    frame.add(delLib);

    logout = new JButton("Logout");
    logout.setSize(150, 50);
    logout.setLocation((frame.getWidth() - logout.getWidth()) / 2, 360);
    logout.addActionListener(this);
    frame.add(logout);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource().equals(addLib)) 
    {
        frame.dispose();
        AddLibrarian addLib = new AddLibrarian();
        addLib.frame.setVisible(true);
    }
    
    if (e.getSource().equals(viewLib))
    {
        frame.dispose();
        ViewLibrarian view = new ViewLibrarian();
        view.frame.setVisible(true);
    }
    if (e.getSource().equals(delLib))
    {
        frame.dispose();
        DeleteLibrarian delete = new DeleteLibrarian();
        delete.frame.setVisible(true);
    }
    if (e.getSource().equals(logout))
    {
        frame.dispose();
        Window window = new Window();
    }
  }
}
