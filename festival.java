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
    private JLabel label2[];
    
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
        fest.box_labels();
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

    public void box_labels()
    {
        String[] inputs = {"headliner time", "act name", "duration", "priority"};
        int pos2 = 60;
        label2 = new JLabel[4];
        for (int i = 0; i <= 3; i++)
        {
            label2[i] = new JLabel(inputs[i]);
            label2[i].setForeground(Color.WHITE);
            label2[i].setBounds(160, pos2, 185, 185);
            pos2 = pos2 + 50;
            frame.getContentPane().add(label2[i]);
        }
    }
}