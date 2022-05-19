import javax.swing.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalTime;

public class festival extends JFrame implements ActionListener
{
    private JFrame frame;
    private JTextField fields[];
    private JTextField fields2[];
    private JLabel label2[];
    private JButton buttons[];
    private int num_acts;
    private String[] act_names = new String[20];
    private int[] duration = new int[20];
    private int[] priority = new int[20];
    private int index = 0;
    
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

        this.main_window();

        frame.setSize(600, 600);
        frame.setTitle("window");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the window closes when the user presses the exit button
       
    }

    public static void main(String[] args) 
    {   
        festival fest = new festival(); // creates an instance of the class to access methods
        fest.buttons();     
    } 

    // creates the inital layout which asks for headliner time and number of acts
    public void main_window()
    {
        int box_pos = 150;
        int label_pos = 85;
        String[] inputs = {"Event name", "Number of acts", "Headliner time"};

        fields2 = new JTextField[3];

        for (int i = 0; i <= 2; i++)
        {
            JLabel inputLabel = new JLabel(inputs[i]);
            inputLabel.setForeground(Color.WHITE);  
            inputLabel.setFont(new Font("Serif", Font.PLAIN, 15));    
            inputLabel.setBounds(130, label_pos, 185, 185);
            label_pos = label_pos + 80;
            frame.getContentPane().add(inputLabel);

            fields2[i] = new JTextField();
            fields2[i].setBounds(270, box_pos, 210, 60);
            box_pos = box_pos + 80;
            frame.getContentPane().add(fields2[i]);
        }
        
    }   


    // this method creates the different buttons through out the scheduling process
    public void buttons()
    {
        String[] names = {"next", "enter"};
        buttons = new JButton[2]; //buttons will be stored in an array once created instead of initialising each one manually
        for (int j = 0; j <= 1; j++)
        {
            buttons[j] = new JButton(names[j]);
            buttons[j].setBounds(228, 380, 150, 40);  
        }  
        frame.getContentPane().add(buttons[0]);
        buttons[0].addActionListener(this);
        buttons[1].addActionListener(this);
    }

    //this method is automatically called when a button is pressed
    public void actionPerformed(ActionEvent e)
    {
        //if statements branch in repsonse to the specific button that is clicked by retrieving the button name
        if (e.getSource() == buttons[0])
        {
            try 
            {
                String act_name = fields2[0].getText();
                num_acts = Integer.parseInt(fields2[1].getText());
                String headliner = fields2[2].getText();
                LocalTime Time = LocalTime.parse(headliner);
                System.out.println(Time);
                
                frame.getContentPane().removeAll(); //clears the page by removing all the components added to it 
                frame.repaint(); 
                this.user_input();
            } 
            catch (Exception f) 
            {
                JOptionPane.showMessageDialog(frame, "Error: information is missing");
            }
            
            
        }
        else if (e.getSource() == buttons[1])
        {
            this.getinfo();
        }

    }

    // this method is responsible for creating the GUI for the act info
    public void user_input()
    {
        String[] inputs2 = {"Act name", "Duration", "Priority"};
        int pos2 = 40;       
        int pos = 100;
        fields = new JTextField[3];
        for (int i = 0; i <= 2; i++)
        {
            JLabel inputlabel2 = new JLabel(inputs2[i]);
            inputlabel2.setFont(new Font("Serif", Font.PLAIN, 18));
            inputlabel2.setForeground(Color.WHITE);
            inputlabel2.setBounds(140, pos2, 185, 185);
            pos2 = pos2 + 100;
            frame.getContentPane().add(inputlabel2);

            fields[i]= new JTextField();
            fields[i].setBounds(270, pos, 210, 60);
            pos = pos + 100;
            frame.getContentPane().add(fields[i]);       
        }
        frame.getContentPane().add(buttons[1]);
    }

    //This method is responsible for retreiving and storing the values entered into the input boxes
    public void getinfo()
    {  
        try 
        {
            act_names[index] = fields[0].getText();
            duration[index] = Integer.parseInt(fields[1].getText());
            priority[index] = Integer.parseInt(fields[2].getText());

        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(frame, "Error: information is missing");
        } 

        if (num_acts > 1)
        {
            for (int i = 0; i <= 2; i++)
            {
                fields[i].setText(""); //clears the boxes for new info to be entered
            }
            num_acts = num_acts - 1; //counter is decremented
        }
        else
        {
            frame.getContentPane().removeAll();
            frame.repaint();
            this.scheduling();
        }
        index++;
    }

    public void scheduling()
    {
        num_acts = Integer.parseInt(fields2[1].getText());
        int num = 1;
        while(num != (num_acts + 1))
        {
            for (int i = 0; i <= num_acts; i++)
            {
                if(priority[i] == num) 
                {                  
                    System.out.println(duration[i]);
                }    
                   
            }
            num = num + 1;
        }      
    } 

} 