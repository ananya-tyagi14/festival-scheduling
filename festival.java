import javax.swing.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.time.LocalTime;
import javax.swing.JPanel;

public class festival extends JPanel 
{
    private JFrame frame;
    private JPanel panel;
   
    //constructor creates the frame for the main window
    public festival()
    {
        frame  = new JFrame("festival");

        panel = new JPanel();
        panel.setLayout(null);

        frame.add(panel);

        JLabel label1 = new JLabel("Festival Scheduling");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Serif", Font.PLAIN, 30));
        label1.setBounds(160, 0, 400, 200);
        panel.add(label1);

        frame.setSize(600, 600);
        frame.setTitle("window");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        schedule obj = new schedule(frame, panel);
        obj.main_window(); 
        obj.buttons();   
    }

    public static void main(String[] args) 
    {   
        festival obj2 = new festival();
    }       
} 