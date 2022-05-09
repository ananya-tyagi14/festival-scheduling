import javax.swing.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class festival extends JFrame implements ActionListener
{
    private JFrame frame ;
    private JFrame frame2;
    private JTextField fields[];
    private JLabel label2[];
    private JButton buttons[];
    
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

        JTextField head = new JTextField();
        head.setBounds(230, 280, 160, 30);
        frame.getContentPane().add(head);

        JLabel headLabel = new JLabel("Headliner time");
        headLabel.setForeground(Color.WHITE);      
        headLabel.setBounds(230, 160, 400, 200);
        frame.getContentPane().add(headLabel);

        frame.setSize(600, 600);
        frame.setTitle("window");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    public static void main(String[] args) 
    {   
        festival fest = new festival();
        fest.buttons();
    } 

    public void buttons()
    {
        String[] names = {"next", "enter"};
        buttons = new JButton[2];
        for (int j = 0; j <= 1; j++)
        {
            buttons[j] = new JButton(names[j]);
            buttons[j].setBounds(260, 340, 100, 30);  
        }  
        frame.getContentPane().add(buttons[0]);
        buttons[0].addActionListener(this);
    }

    public void user_input()
    {
        String[] inputs = {"act name", "duration", "priority"};
        int pos2 = 60;
        label2 = new JLabel[3];
        int pos = 140;
        fields = new JTextField[3];
        for (int i = 0; i <= 2; i++)
        {
            label2[i] = new JLabel(inputs[i]);
            label2[i].setForeground(Color.WHITE);
            label2[i].setBounds(160, pos2, 185, 185);
            pos2 = pos2 + 50;
            frame2.getContentPane().add(label2[i]);

            fields[i]= new JTextField();
            fields[i].setBounds(300, pos, 160, 30);
            pos = pos + 50;
            frame2.getContentPane().add(fields[i]);       
        }
        frame2.getContentPane().add(buttons[1]);  
    }


    public void actionPerformed(ActionEvent e)
   {
        frame.setVisible(false);
        frame2  = new JFrame("festival");
        frame2.setLayout(null);
        frame2.getContentPane().setBackground(Color.BLACK);

        frame2.setSize(600, 600);
        frame2.setTitle("window");
        frame2.setVisible(true);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.user_input();
   }

   public void getinfo()
    {
        String headliner = fields[0].getText();
        String name = fields[1].getText();
        String duration = fields[2].getText();
        String priority = fields[3].getText();

        for (int i = 0; i <= 3; i++)
        {
            fields[i].setText("");
        } 
    }
}