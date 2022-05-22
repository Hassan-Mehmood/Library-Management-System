package admin;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewLibrarian {
    
    public JFrame frame;
    private JLabel id, name, password, email, address, city, contact;
    private JPanel panel;
    private JButton back;
    
    public ViewLibrarian() 
    {
        frame = new JFrame();
        frame.setSize(650, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        
        createView();
    }
    
    private void createView() 
    {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        id = new JLabel("ID");
        id.setBounds(10, 10, 70, 50);
        panel.add(id);
        frame.add(panel, BorderLayout.NORTH);
        
        name = new JLabel("Name");
        id.setBounds(80, 10, 70, 50);
        panel.add(name);
        
        frame.add(panel, BorderLayout.NORTH);
        
    }
    
    
    
    
    
    
    
    
    
}
