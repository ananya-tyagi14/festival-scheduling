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
import java.io.File;
import java.io.IOException;
import java.io.FileWriter; 
import java.io.BufferedWriter;

/** This class creates the components to be added, takes user input and produces the schedule
 * @author Ananya 
*/
public class schedule extends JFrame implements ActionListener
{
    private JFrame frame;
    private JPanel panel;

    private JTextField fields[];
    private JTextField fields2[];
    private JButton buttons[];

    private String show_name;
    private int num_acts;
    private LocalTime start;
    private LocalTime headliner;
    private String[] act_names = new String[20];
    private int[] duration = new int[20];
    private int[] priority = new int[20];
    private String columnNames[];

    //counters 
    private int index = 0;
    private int p = 1;

    //elements of the final table
    private DefaultTableModel model;
    private JTable table;
    private int row;

     /**
     * creates a view of schedule.
     * 
     * @param f the frame of the window
     * @param p the panel in which components will be added
     */
    public schedule(JFrame f, JPanel p)
    {
        frame = f;
        panel = p;
    }

    /**
     * method creates the first initial form layout - called within the festival class
     */
    public void main_window()
    {
        int box_pos = 150;
        int label_pos = 90;
        String[] inputs = {"Event name", "Number of acts", "Start time", "Headliner time"}; //textfield labels 

        // array stores the textfields created within the loop
        fields2 = new JTextField[4]; 

        // loop creates labels and textfields instead of specifiying each one manually
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

    /**
     * method creates the buttons - called within the festival class
     */
    public void buttons()
    {
        int pos = 90;

        //stores the button labels
        String[] names = {"Next", "Enter", "Create File", "Delete"}; //button labels 
        buttons = new JButton[4];

        //this loop creates the longer buttons
        for (int j = 0; j <= 1; j++)
        {
            buttons[j] = new JButton(names[j]);
            buttons[j].setBounds(90, 460, 430, 60);
        }

        //this loop creates the shorter buttons
        for (int j = 2; j <= 3; j++)
        {
            buttons[j] = new JButton(names[j]);
            buttons[j].setBounds(pos, 450, 210, 40);  
            pos = pos + 220;
        }  
        panel.add(buttons[0]);
        buttons[0].addActionListener(this);
        buttons[1].addActionListener(this);
        buttons[2].addActionListener(this);
        buttons[3].addActionListener(this);
    }

    /**
     * method is automatically called when button is clicked
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == buttons[0])
        {
            try 
            {
                // retrive data from textfields and stores them in variables
                show_name = fields2[0].getText();
                num_acts = Integer.parseInt(fields2[1].getText()); //converts textfield string into an int
                start = LocalTime.parse(fields2[2].getText());  //converts textfield string into time format 
                headliner = LocalTime.parse(fields2[3].getText());
                
                this.clear();
                this.user_input();
            } 
            catch (Exception f) 
            {
                // creates an error message box
                JOptionPane.showMessageDialog(frame, "Error: no information was entered");
            }            
        }
        else if (e.getSource() == buttons[1])
        {
            this.getinfo();
        
        }
        else if (e.getSource() == buttons[2])
        {
            this.textfile();
        }
        else if (e.getSource() == buttons[3])
        {
            //deletes the row on based on the mouse click event
            model.removeRow(row); 
        }
    }

    /**
     * method creates the form for the other information required - called within buttons 
     */
    public void user_input()
    {
        String[] inputs2 = {"Act name", "Duration"};
        //position variables for components
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
        panel.add(buttons[1]);
    }

    /**
     * method retrieves data from second form and clears it until acts have been entered  - called within buttons 
     */
    public void getinfo()
    {  
        try 
        {
            //storing the inputs in the initialised arrays 
            act_names[index] = fields[0].getText();
            duration[index] = Integer.parseInt(fields[1].getText());
            priority[index] = p;
      
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(frame, "Error: no information was entered");
        }

        //conditions check to see if the form should be displayed or cleared when max number of acts is reached
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
        index++; //incrementing counter for arrays 
    }

    /**
     * method responsible for allocating times to each act based around the headliner - called within getinfo method
     */
    public void scheduling()
    {
        this.table(); 
        int gap = 8; //the gap between the acts 
        num_acts = Integer.parseInt(fields2[1].getText()); //retrieve again as its value was changed in a different method

        //variables to store the current state of the instructions as the loop runs
        LocalTime first = headliner; 
        LocalTime after = headliner;
        int current = duration[0];

        //conditions check if start time is equal to the headliner time. Act times are then manipulated accordingly
        if (headliner.isAfter(start))
        {
            model.addRow(new Object[] {priority[0], headliner , act_names[0]});
            for (int j = 1; j < num_acts; j++)
            {
                //inserts the act before the headliner if its duration does not exceed the start time and after if it is too long 
                if (start.isAfter(first.minusMinutes(duration[j] + gap)))
                {
                    after = after.plusMinutes(current + gap);
                    model.addRow(new Object[] {priority[j], after , act_names[j]});
                    current = duration[j];
                }
                else
                {
                    first = first.minusMinutes(duration[j] + gap);
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

    /**
     * method creates the table on which the final schedule will be displayed- called within the scheduling method
     */
    public void table()
    {
        columnNames = new String[] {"Priority", "Act time", "Act Name"};

        JLabel eventlabel = new JLabel("Event Name: " + show_name);
        eventlabel.setForeground(Color.BLACK);
        eventlabel.setFont(new Font("Serif", Font.PLAIN, 15));
        eventlabel.setBounds(0, 0, 400, 200);
        panel.add(eventlabel);

        JLabel timelabel = new JLabel("Event Time: " + start);
        timelabel.setForeground(Color.BLACK);
        timelabel.setFont(new Font("Serif", Font.PLAIN, 15));
        timelabel.setBounds(0, 20, 400, 200);
        panel.add(timelabel);
    
        //creates a default table 
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setRowHeight(40);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 140, 600, 600);
        for (int i = 0; i < 3; i++)
        {
            model.addColumn(columnNames[i]);
        }
        frame.add(scrollPane);
        panel.add(buttons[2]);
        panel.add(buttons[3]);

        //retrieves the row number when selected with the mouse click
        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
                row = table.rowAtPoint(e.getPoint());          
            }
        });
    }

    /**
     * method creates and populates a textfile with the table info - called in the buttons method
     */
    public void textfile()
    {
        try 
        {
            File filepath = new File("//home//tyagia//h-drive//scc110//java//coursework//back-up//schedule.txt");

            // if file doesnt exists, then create it
            if (!filepath.exists()) 
            {
                filepath.createNewFile();
            }
            FileWriter fw = new FileWriter("schedule.txt");
            BufferedWriter bw = new BufferedWriter(fw); 
            bw.write("Event name: " + (String)show_name + "\n");
            bw.write("Start Time: " + (String)start.toString()+ "\n");
            bw.write("_______________________________________________\n\n");

            for (int i = 0; i < table.getRowCount(); i++)
            {
                for (int k = 0; k < 3; k++)
                {
                    String value = table.getModel().getValueAt(i, k).toString(); //converts all the data into string type 
                    bw.write((String)columnNames[k] + ": " + (String)value);
                    bw.write("\n");
                }
            
                bw.write("______________\n\n");
            }
            bw.close();
            fw.close();                
        }
        catch (IOException e) //will be run if an unexpected error occurs
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * method clears the panel and then allows new components to be added
     */
    public void clear()
    {
        panel.removeAll();
        panel.repaint(); 
    }

}