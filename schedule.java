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
import javax.swing.table.DefaultTableModel;


public class schedule extends JFrame implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    private JTextField fields[];
    private JTextField fields2[];
    private JButton main_button;
    private JButton buttons[];
    private String show_name;
    private int num_acts;
    private LocalTime start;
    private LocalTime headliner;
    private String[] act_names = new String[20];
    private int[] duration = new int[20];
    private int[] priority = new int[20];
    private int index = 0;
    private int p = 1;
    private DefaultTableModel model;


    public schedule(JFrame f, JPanel p)
    {
        frame = f;
        panel = p;

    }

    public void main_window()
    {
        int box_pos = 150;
        int label_pos = 90;
        String[] inputs = {"Event name", "Number of acts", "Start time", "Headliner time"};

        fields2 = new JTextField[4];

        for (int i = 0; i <= 3; i++)
        {
            JLabel inputLabel = new JLabel(inputs[i]);
            inputLabel.setForeground(Color.BLACK);  
            inputLabel.setFont(new Font("Serif", Font.PLAIN, 15));    
            inputLabel.setBounds(90, label_pos, 185, 185);
            label_pos = label_pos + 80;
            panel.add(inputLabel);

            fields2[i] = new JTextField();
            fields2[i].setBounds(270, box_pos, 250, 60);
            box_pos = box_pos + 80;
            panel.add(fields2[i]);
        }
    }

    public void buttons()
    {
        int pos = 90;
        String[] names = {"Back", "enter"};
        buttons = new JButton[2];

        main_button = new JButton("Next");
        main_button.setBounds(90, 460, 430, 60);

        for (int j = 0; j <= 1; j++)
        {
            buttons[j] = new JButton(names[j]);
            buttons[j].setBounds(pos, 450, 210, 60);  
            pos = pos + 220;
        }  
        panel.add(main_button);
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
                start = LocalTime.parse(fields2[2].getText());
                headliner = LocalTime.parse(fields2[3].getText());
                
                this.clear(); 
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
        String[] inputs2 = {"Act name", "Duration"};
        int pos2 = 145;       
        int pos = 200;
        fields = new JTextField[2];
        for (int i = 0; i <= 1; i++)
        {
            JLabel plabel = new JLabel("Priority: " + Integer.toString(p));
            plabel.setFont(new Font("Serif", Font.PLAIN, 25));
            plabel.setForeground(Color.BLACK); 
            plabel.setBounds(85, 55, 185, 185);
            panel.add(plabel);

            JLabel inputlabel2 = new JLabel(inputs2[i]);
            inputlabel2.setFont(new Font("Serif", Font.PLAIN, 15));
            inputlabel2.setForeground(Color.BLACK); 
            inputlabel2.setBounds(85, pos2, 185, 185);
            pos2 = pos2 + 100;
            panel.add(inputlabel2);

            fields[i]= new JTextField();
            fields[i].setBounds(240, pos, 280, 80);
            pos = pos + 100;
            panel.add(fields[i]);       
        }
        panel.add(buttons[0]);
        panel.add(buttons[1]);
    }

    public void getinfo()
    {  
        try 
        {
            act_names[index] = fields[0].getText();
            duration[index] = Integer.parseInt(fields[1].getText());
            priority[index] = p;
      
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(frame, "Error: no information was entered");
        }

        if (num_acts > 1)
        {
            this.clear(); 
            p++;
            this.user_input();
            num_acts = num_acts - 1;
        }
        else
        {
            this.clear();  
            this.scheduling();    
        }
        index++;
    }

    public void scheduling()
    {
        this.table();
        int gap = 8;
        num_acts = Integer.parseInt(fields2[1].getText());
        LocalTime first = headliner;
        LocalTime after = headliner;
        int current = duration[0];
        if (headliner.isAfter(start))
        {
            model.addRow(new Object[] {priority[0], headliner , act_names[0]});
            for (int j = 1; j < num_acts; j++)
            {
                if (start.isAfter(first.minusMinutes(duration[j])))
                {
                    after = after.plusMinutes(current);
                    model.addRow(new Object[] {priority[j], after , act_names[j]});
                    current = duration[j];
                }
                else
                {
                    first = first.minusMinutes(duration[j]);
                    model.addRow(new Object[] {priority[j], first , act_names[j]});
                }
            }                        
        }
        else
        {
            for (int i = 0; i < num_acts; i++)
            {   
                model.addRow(new Object[] {priority[i], headliner , act_names[i]});
                System.out.println(headliner);
                headliner = headliner.plusMinutes(duration[i]+gap);   
            }  
        }  
    }  

    public void table()
    {
        String[] columnNames = { "Priority", "Act time", "Act Name"};

        JLabel eventlabel = new JLabel("Event Name: " + show_name);
        eventlabel.setForeground(Color.BLACK);
        eventlabel.setFont(new Font("Serif", Font.PLAIN, 15));
        eventlabel.setBounds(0, 0, 400, 200);
        panel.add(eventlabel);
        
        model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setRowHeight(40);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 120, 600, 600);
        for (int i = 0; i < 3; i++)
        {
            model.addColumn(columnNames[i]);
        }
        frame.add(scrollPane);
    }

    public void clear()
    {
        panel.removeAll();
        panel.repaint(); 
    }

}