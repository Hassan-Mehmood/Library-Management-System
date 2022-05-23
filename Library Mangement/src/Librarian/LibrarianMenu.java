package Librarian;

import Main.Window;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class LibrarianMenu implements ActionListener {

    public JFrame frame;
    private JButton addBooks, viewBooks, issueBooks, viewIssueBooks, returnBooks, logout;
    private JLabel header;

    public LibrarianMenu()
    {
        frame = new JFrame("Librarian Menu");
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
        header = new JLabel("Librarian Section");
        header.setFont(new Font("SansSerif", Font.PLAIN, 16));
        header.setSize(150, 50);
        header.setLocation((frame.getWidth() - header.getWidth()) / 2, 10);
        frame.add(header);

        addBooks = new JButton("Add Books");
        addBooks.setSize(150, 50);
        addBooks.setLocation((frame.getWidth() - addBooks.getWidth()) / 2, 90);
        addBooks.addActionListener(this);
        frame.add(addBooks);

        viewBooks = new JButton("View Books");
        viewBooks.setSize(150, 50);
        viewBooks.setLocation((frame.getWidth() - viewBooks.getWidth()) / 2, 150);
        viewBooks.addActionListener(this);
        frame.add(viewBooks);

        issueBooks = new JButton("Issue Books");
        issueBooks.setSize(150, 50);
        issueBooks.setLocation((frame.getWidth() - issueBooks.getWidth()) / 2, 210);
        issueBooks.addActionListener(this);
        frame.add(issueBooks);

        viewIssueBooks = new JButton("View Issue Books");
        viewIssueBooks.setSize(150, 50);
        viewIssueBooks.setLocation((frame.getWidth() - viewIssueBooks.getWidth()) / 2, 270);
        viewIssueBooks.addActionListener(this);
        frame.add(viewIssueBooks);

        returnBooks = new JButton("Return Book");
        returnBooks.setSize(150, 50);
        returnBooks.setLocation((frame.getWidth() - returnBooks.getWidth()) / 2, 330);
        returnBooks.addActionListener(this);
        frame.add(returnBooks);

        logout = new JButton("Logout");
        logout.setSize(150, 50);
        logout.setLocation((frame.getWidth() - logout.getWidth()) / 2, 390);
        logout.addActionListener(this);
        frame.add(logout);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(addBooks)) {
            frame.dispose();
            AddBooks add = new AddBooks();
            add.frame.setVisible(true);
        }
        if (e.getSource().equals(issueBooks)) {
            frame.dispose();
            IssueBook issue = new IssueBook();
            issue.frame.setVisible(true);
        }
        if (e.getSource().equals(viewBooks)) {
            frame.dispose();
            ViewBooks view = new ViewBooks();
            view.frame.setVisible(true);
        }
        if (e.getSource().equals(viewIssueBooks)) {
            frame.dispose();
            ViewIssuedBooks view = new ViewIssuedBooks();
            view.frame.setVisible(true);
        }
        if (e.getSource().equals(returnBooks)) {
            frame.dispose();
            ReturnBooks returnBook = new ReturnBooks();
            returnBook.frame.setVisible(true);
        }
        if (e.getSource().equals(logout)) {
            frame.dispose();
            Window window = new Window();
        }
    }
}
