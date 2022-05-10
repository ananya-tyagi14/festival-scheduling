import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class festival extends JFrame implements ActionListener
{
    private JFrame frame;
    private JTextField fields[];
    private JLabel label2[];
    private JButton buttons[];
    
    //constructor creates the frame for the main window
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

        this.main_window();

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

    public void main_window()
    {
        int box_pos = 200;
        int label_pos = 80;
        String[] inputs = {"Number of acts", "headliner time"};

        for (int i = 0; i <= 1; i++)
        {
            JLabel inputLabel = new JLabel(inputs[i]);
            inputLabel.setForeground(Color.WHITE);      
            inputLabel.setBounds(228, label_pos, 400, 200);
            label_pos = label_pos + 80;
            frame.getContentPane().add(inputLabel);

            JTextField head = new JTextField();
            head.setBounds(228, box_pos, 160, 30);
            box_pos = box_pos + 80;
            frame.getContentPane().add(head);
        }
        
    }

    public void buttons()
    {
        String[] names = {"next", "enter"};
        buttons = new JButton[2];
        for (int j = 0; j <= 1; j++)
        {
            buttons[j] = new JButton(names[j]);
            buttons[j].setBounds(258, 340, 100, 30);  
        }  
        frame.getContentPane().add(buttons[0]);
        buttons[0].addActionListener(this);
        buttons[1].addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == buttons[0])
        {
            frame.getContentPane().removeAll();
            frame.repaint();
            this.user_input();
        }
        else if (e.getSource() == buttons[1])
        {
            System.out.println("button2");
        }

    }

    public void user_input()
    {
        String[] inputs2 = {"act name", "duration", "priority"};
        int pos2 = 60;
        label2 = new JLabel[3];
        int pos = 140;
        fields = new JTextField[3];
        for (int i = 0; i <= 2; i++)
        {
            label2[i] = new JLabel(inputs2[i]);
            label2[i].setForeground(Color.WHITE);
            label2[i].setBounds(160, pos2, 185, 185);
            pos2 = pos2 + 50;
            frame.getContentPane().add(label2[i]);

            fields[i]= new JTextField();
            fields[i].setBounds(300, pos, 160, 30);
            pos = pos + 50;
            frame.getContentPane().add(fields[i]);       
        }
        frame.getContentPane().add(buttons[1]); 
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