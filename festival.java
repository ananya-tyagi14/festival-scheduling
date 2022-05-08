import javax.swing.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class festival
{
    private JFrame frame ;
    private JTextField fields[];
    
    
    public festival()
    {
        frame  = new JFrame("festival");

        frame.setLayout(null);

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
    }

    public static void main(String[] args) 
    {   
        festival fest = new festival();
        fest.user_input();
    } 

    public void user_input()
    {
        int pos = 140;
        fields = new JTextField[4];
        for (int i = 0; i <= 3; i++)
        {
            fields[i]= new JTextField();
            fields[i].setBounds(300, pos, 160, 30);
            pos = pos + 50;
            frame.getContentPane().add(fields[i]);       
        }
    }
}