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


public class schedule extends JFrame implements ActionListener
{
    private JFrame frame;
    private JTextField fields[];
    private JTextField fields2[];
    private JButton main_button;
    private JButton buttons[];
    private String show_name;
    private int num_acts;
    private LocalTime time;
    private String[] act_names = new String[20];
    private int[] duration = new int[20];
    private int[] priority = new int[20];
    private int[] order_duration = new int[20];
    private LocalTime[] act_times = new LocalTime[20];
    private int index = 0;
    private int i = 0;

    public schedule(JFrame f)
    {
        frame = f;

    }

    public void main_window()
    {
        int box_pos = 150;
        int label_pos = 95;
        String[] inputs = {"Event name", "Number of acts", "Headliner time"};

        fields2 = new JTextField[3];

        for (int i = 0; i <= 2; i++)
        {
            JLabel inputLabel = new JLabel(inputs[i]);
            inputLabel.setForeground(Color.WHITE);  
            inputLabel.setFont(new Font("Serif", Font.PLAIN, 18));    
            inputLabel.setBounds(85, label_pos, 185, 185);
            label_pos = label_pos + 100;
            frame.getContentPane().add(inputLabel);

            fields2[i] = new JTextField();
            fields2[i].setBounds(240, box_pos, 280, 80);
            box_pos = box_pos + 100;
            frame.getContentPane().add(fields2[i]);
        }
    }

    public void buttons()
    {
        int pos = 90;
        String[] names = {"Back", "enter"};
        buttons = new JButton[2];

        main_button = new JButton("Next");
        main_button.setBounds(90, 450, 430, 60);

        for (int j = 0; j <= 1; j++)
        {
            buttons[j] = new JButton(names[j]);
            buttons[j].setBounds(pos, 450, 210, 60);  
            pos = pos + 220;
        }  
        frame.getContentPane().add(main_button);
        main_button.addActionListener(this);
        buttons[0].addActionListener(this);
        buttons[1].addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == main_button)
        {
            try 
            {
                show_name = fields2[0].getText();
                num_acts = Integer.parseInt(fields2[1].getText());
                time = LocalTime.parse(fields2[2].getText());
                act_times[0] = time;
                System.out.println(time);
                
                frame.getContentPane().removeAll();
                frame.repaint();
                this.user_input();
            } 
            catch (Exception f) 
            {
                JOptionPane.showMessageDialog(frame, "Error: no information was entered");
            }            
        }
        else if (e.getSource() == buttons[1])
        {
            this.getinfo();
        }

    }

    public void user_input()
    {
        String[] inputs2 = {"Act name", "Duration", "Priority"};
        int pos2 = 65;       
        int pos = 120;
        fields = new JTextField[3];
        for (int i = 0; i <= 2; i++)
        {
            JLabel inputlabel2 = new JLabel(inputs2[i]);
            inputlabel2.setFont(new Font("Serif", Font.PLAIN, 18));
            inputlabel2.setForeground(Color.WHITE); 
            inputlabel2.setBounds(85, pos2, 185, 185);
            pos2 = pos2 + 100;
            frame.getContentPane().add(inputlabel2);

            fields[i]= new JTextField();
            fields[i].setBounds(240, pos, 280, 80);
            pos = pos + 100;
            frame.getContentPane().add(fields[i]);       
        }
        frame.getContentPane().add(buttons[0]);
        frame.getContentPane().add(buttons[1]);
    }

    public void getinfo()
    {  
        try 
        {
            act_names[index] = fields[0].getText();
            duration[index] = Integer.parseInt(fields[1].getText());
            priority[index] = Integer.parseInt(fields[2].getText());
      
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(frame, "Error: no information was entered");
        }

        if (num_acts > 1)
        {
            for (int i = 0; i <= 2; i++)
            {
               fields[i].setText("");
            }
            num_acts = num_acts - 1;
        }
        else
        {
            frame.getContentPane().removeAll();
            frame.repaint();  
            this.ordering();      
        }
        index++;
    }

    public void ordering()
    {
        int gap = 8;
        num_acts = Integer.parseInt(fields2[1].getText());
        int num = 1;
        int j = 1;
        while(num != (num_acts + 1))
        {
            for (int i = 0; i <= num_acts; i++)
            {
                if(priority[i] == num) 
                {                  
                    System.out.println(duration[i]);
                    time = time.plusMinutes(duration[i]+gap);
                    System.out.println(time);
                }    
                   
            }
            num = num + 1;
        }
    }   
}