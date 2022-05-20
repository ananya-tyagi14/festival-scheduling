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
   
    //constructor creates the frame for the main window
    public festival()
    {
        frame  = new JFrame("festival");

        frame.setLayout(null); //allows the components to be positioned freely

        frame.getContentPane().setBackground(Color.BLACK);

        JLabel label1 = new JLabel("Festival Scheduling");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Serif", Font.PLAIN, 30));
        label1.setBounds(160, 0, 400, 200);
        frame.getContentPane().add(label1);

        frame.setSize(600, 600);
        frame.setTitle("window");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        schedule obj = new schedule(frame);
        obj.main_window(); 
        obj.buttons();   
    }

    public static void main(String[] args) 
    {   
        festival obj2 = new festival();
    }       
} 