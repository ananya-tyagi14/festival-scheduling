import javax.swing.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class festival
{
    public static void main(String[] args) 
    {   
        JFrame frame  = new JFrame("festival");

        frame.setLayout(null);

        frame.setSize(900, 600);
        frame.setTitle("Festival scheduling");

        JTextField field1 = new JTextField("name");
        field1.setBounds(50,100, 200,30);  
        frame.getContentPane().add(field1);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    } 
}