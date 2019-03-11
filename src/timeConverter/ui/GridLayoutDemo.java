package timeConverter.ui;

import java.awt.*;
import javax.swing.*;

public class GridLayoutDemo
{

    private JFrame frame;
    private JTextField nameTextField;
    private JTextField birthDateTextField;
    private JLabel nameLabel;
    private JLabel birthDateLabel;

    public GridLayoutDemo()
    {
        initComponents();
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                try
                {
                    javax.swing.UIManager.LookAndFeelInfo alookandfeelinfo[];
                    int j = (alookandfeelinfo = UIManager.getInstalledLookAndFeels()).length;
                    for(int i = 0; i < j; i++)
                    {
                        javax.swing.UIManager.LookAndFeelInfo info = alookandfeelinfo[i];
                        if(!"Nimbus".equals(info.getName()))
                        {
                            continue;
                        }
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }

                }
                catch(Exception exception) { }
                new GridLayoutDemo();
            }

        });
    }

    private void initComponents()
    {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(3);
        frame.setLayout(new GridBagLayout());
        nameTextField = new JTextField(30);
        birthDateTextField = new JTextField(30);
        nameLabel = new JLabel("Name:");
        birthDateLabel = new JLabel("Birth Date:");
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = 2;
        gc.insets = new Insets(10, 10, 10, 10);
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(nameLabel, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(nameTextField, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(birthDateLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(birthDateTextField, gc);
        frame.pack();
        frame.setVisible(true);
    }
}