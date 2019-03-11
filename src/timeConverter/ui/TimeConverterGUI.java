package timeConverter.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
//import timeConverter.main.StoreDetails;
import timeConverter.main.TimeConverter;
//import timeConverter.util.StoreDetailsParser;

/**
 * @author dalichandramathi_s
 *
 */

public class TimeConverterGUI
{
    private class ConvertionHandler
        implements ActionListener
    {

        final TimeConverterGUI this$0;

        public void actionPerformed(ActionEvent e)
        {
            String fromDateTimeString = null;
            String outputDateTimeString = null;
            try
            {
                if(isToOtherTZ)
                {
                    toTimeZoneString = textToTimeZone.getText().trim();
                }
                if(isFromOtherTZ)
                {
                    fromTimeZoneString = textFromTimeZone.getText().trim();
                }
                if(fromFormat.equals("Milliseconds"))
                {
                    timeInMilliseconds = Long.parseLong(textTimeToConvert.getText());
                    try
                    {
                        DateTimeZone toTimeZone = DateTimeZone.forID(toTimeZoneString);
                        jodaTime = new DateTime(timeInMilliseconds, toTimeZone);
                        textResult.setText(parser1.print(jodaTime));
                        System.out.println((new StringBuilder("From Milliseconds ")).append(timeInMilliseconds).append(" to ").append(toTimeZoneString).append(" is :").append(textResult.getText()).toString());
                    }
                    catch(IllegalArgumentException illEx)
                    {
                        illEx.printStackTrace();
                        System.out.println("Inside illegal Arg catch");
                        TimeConverterGUI.errorMessage = "Invalid TimeZone, make sure the timezone is in CamelCase";
                        JOptionPane.showMessageDialog(frameTimeConverter, (new StringBuilder("ERROR, ")).append(TimeConverterGUI.errorMessage).toString(), "Error", 0);
                    }
                } else
                if(!fromFormat.equals("Milliseconds"))
                {
                    fromDateTimeString = textTimeToConvert.getText();
                }
                if(!fromFormat.equals("Milliseconds"))
                {
                    outputDateTimeString = (new TimeConverter()).convertFromOneTimezoneToAnother(fromDateTimeString, fromTimeZoneString, toTimeZoneString);
                }
                if(toFormat.equals("Milliseconds") && fromFormat.equals("Current"))
                {
                    System.out.println((new StringBuilder("outputDateTimeString = ")).append(outputDateTimeString).toString());
                    textResult.setText(String.valueOf(currentDate.getTime()));
                } else
                if(toFormat.equals("Date") && !fromFormat.equals("Milliseconds"))
                {
                    textResult.setText(outputDateTimeString);
                }
            }
            catch(Exception ex)
            {
                System.out.println("inside the catch block");
                JOptionPane.showMessageDialog(frameTimeConverter, (new StringBuilder("ERROR, ")).append(TimeConverterGUI.errorMessage).toString(), "Error", 0);
                ex.printStackTrace();
            }
        }

        private ConvertionHandler()
        {
            super();
            this$0 = TimeConverterGUI.this;
        }

        ConvertionHandler(ConvertionHandler convertionhandler)
        {
            this();
        }
    }

    private class FromRadioButtonHandler
        implements ActionListener
    {

        final TimeConverterGUI this$0;

        public void actionPerformed(ActionEvent e)
        {
            JRadioButton selectedButton = (JRadioButton)e.getSource();
            String text = selectedButton.getText();
            if("Milliseconds".equals(text))
            {
                fromFormat = "Milliseconds";
                toFormat = "Date";
                toTimeZoneString = "GMT";
                isToOtherTZ = false;
                isFromOtherTZ = false;
                textTimeToConvert.requestFocus(true);
                textTimeToConvert.setEditable(true);
                radioButtonFromGMT.setEnabled(false);
                radioButtonFromIST.setEnabled(false);
                radioButtonFromTimezone.setEnabled(false);
                radioButtonToMillisecond.setEnabled(false);
                radioButtonToDate.setEnabled(true);
                radioButtonToDate.setSelected(true);
                radioButtonToGMT.setEnabled(true);
                radioButtonToGMT.setSelected(true);
                radioButtonToIST.setEnabled(true);
                radioButtonToTimezone.setEnabled(true);
            } else
            if("dd-mmm-yy HH:mm:ss".equals(text))
            {
                fromFormat = "Date";
                toFormat = "Date";
                textTimeToConvert.setEditable(true);
                textTimeToConvert.requestFocus(true);
                radioButtonFromGMT.setEnabled(true);
                radioButtonFromIST.setEnabled(true);
                radioButtonFromTimezone.setEnabled(true);
                radioButtonToMillisecond.setEnabled(true);
            } else
            if("Current Time (IST)".equals(text))
            {
                fromFormat = "Current";
                fromTimeZoneString = "Asia/Kolkata";
                isFromOtherTZ = false;
                textTimeToConvert.requestFocus(true);
                currentDate = new Date();
                textTimeToConvert.setText(parser1.print(currentDate.getTime()));
                textTimeToConvert.setEditable(false);
                radioButtonFromGMT.setEnabled(false);
                radioButtonFromIST.setEnabled(true);
                radioButtonFromIST.setSelected(true);
                radioButtonFromTimezone.setEnabled(false);
                radioButtonToMillisecond.setEnabled(true);
            } else
            if("GMT".equals(text))
            {
                fromTimeZoneString = "GMT";
                isFromOtherTZ = false;
                textFromTimeZone.setEditable(false);
                //textFromTimeZone.setBackground(new Color(255, 228, 225));
            } else
            if("IST".equals(text))
            {
                fromTimeZoneString = "Asia/Kolkata";
                isFromOtherTZ = false;
                textFromTimeZone.setEditable(false);
              //  textFromTimeZone.setBackground(new Color(255, 228, 225));
            } else
            if("Other Timezone".equals(text))
            {
                isFromOtherTZ = true;
                textFromTimeZone.setEditable(true);
                textFromTimeZone.setBackground(Color.WHITE);
            }
        }

        private FromRadioButtonHandler()
        {
            super();
            this$0 = TimeConverterGUI.this;
        }

        FromRadioButtonHandler(FromRadioButtonHandler fromradiobuttonhandler)
        {
            this();
        }
    }

    private class ResetHandler
        implements ActionListener
    {

        final TimeConverterGUI this$0;

        public void actionPerformed(ActionEvent e)
        {
            textTimeToConvert.setText("");
            textStoreNumber.setText("");
            textFromTimeZone.setText("");
            textToTimeZone.setText("");
            textConvertedTime.setText("");
            textCurrentIST.setText("");
            textCurrentTimeAtStore.setText("");
            textResult.setText("");
            textStoreIDAndPhonenumber.setText("");
            textStoreNameAndCountry.setText("");
            textStoreTimezone.setText("");
        }

        private ResetHandler()
        {
            super();
            this$0 = TimeConverterGUI.this;
        }

        ResetHandler(ResetHandler resethandler)
        {
            this();
        }
    }

    /*  private class StoreNumberTextFieldFocusListener
        implements FocusListener
    {

        final TimeConverterGUI this$0;

        public void focusGained(FocusEvent arg0)
        {
            System.out.println((new StringBuilder("Focus gained ")).append(arg0).toString());
        }

        public void focusLost(FocusEvent arg0)
        {
            System.out.println((new StringBuilder("Focus lost ")).append(arg0).toString());
            String key = textStoreNumber.getText().trim().toUpperCase();
            if(key != null)
            {
                StoreDetails givenStore = (StoreDetails)StoreDetailsParser.storeDetailsMap.get(key);
                if(givenStore != null)
                {
                    textStoreNameAndCountry.setText((new StringBuilder(String.valueOf(givenStore.getStoreName()))).append(", ").append(givenStore.getStoreCountry()).toString());
                    textStoreIDAndPhonenumber.setText((new StringBuilder(String.valueOf(Long.toString(givenStore.getStoreID())))).append(", ").append(givenStore.getStorePhonenumber()).toString());
                    textStoreTimezone.setText(givenStore.getStoreTimezone());
                    textCurrentTimeAtStore.setText(getCurrentTimeForTimezone(givenStore.getStoreTimezone()));
                    textCurrentIST.setText(getCurrentTimeForTimezone("Asia/Kolkata"));
                    textTimeToConvert.requestFocus(true);
                } else
                {
                    resetStoreDetailsTextBoxes();
                    JOptionPane.showMessageDialog(frameTimeConverter, "No store found with this store number \n Please enter a valid store number", "Error", 0);
                }
            }
        }

        private StoreNumberTextFieldFocusListener()
        {
            super();
            this$0 = TimeConverterGUI.this;
        }

        StoreNumberTextFieldFocusListener(StoreNumberTextFieldFocusListener storenumbertextfieldfocuslistener)
        {
            this();
        }
    }*/

    private class TimezoneDocumentListener
        implements DocumentListener
    {

        final TimeConverterGUI this$0;

        public void changedUpdate(DocumentEvent e)
        {
            System.out.println("inside changedUpdate");
        }

        public void insertUpdate(DocumentEvent e)
        {
            System.out.println("inside insertUpdate");
            if(isToOtherTZ)
            {
                toTimeZoneString = textToTimeZone.getText().trim();
            }
            if(isFromOtherTZ)
            {
                fromTimeZoneString = textFromTimeZone.getText().trim();
            }
        }

        public void removeUpdate(DocumentEvent e)
        {
            System.out.println("inside removeUpdate");
            if(isToOtherTZ)
            {
                toTimeZoneString = textToTimeZone.getText().trim();
            }
            if(isFromOtherTZ)
            {
                fromTimeZoneString = textFromTimeZone.getText().trim();
            }
        }

        private TimezoneDocumentListener()
        {
            super();
            this$0 = TimeConverterGUI.this;
        }

        TimezoneDocumentListener(TimezoneDocumentListener timezonedocumentlistener)
        {
            this();
        }
    }

    private class ToRadioButtonHandler
        implements ActionListener
    {

        final TimeConverterGUI this$0;

        public void actionPerformed(ActionEvent e)
        {
            JRadioButton selectedButton = (JRadioButton)e.getSource();
            String text = selectedButton.getText();
            if("Milliseconds".equals(text))
            {
                toFormat = "Milliseconds";
                toTimeZoneString = "GMT";
                isToOtherTZ = false;
                radioButtonToGMT.setSelected(true);
                radioButtonToGMT.setEnabled(false);
                radioButtonToIST.setEnabled(false);
                radioButtonToTimezone.setEnabled(false);
            } else
            if("dd-mmm-yy HH:mm:ss".equals(text))
            {
                toFormat = "Date";
                radioButtonToIST.setEnabled(true);
                radioButtonToTimezone.setEnabled(true);
                radioButtonToGMT.setEnabled(true);
            } else
            if("GMT".equals(text))
            {
                toTimeZoneString = "GMT";
                isToOtherTZ = false;
                textToTimeZone.setEditable(false);
                textToTimeZone.setBackground(new Color(255, 228, 225));
            } else
            if("IST".equals(text))
            {
                toTimeZoneString = "Asia/Kolkata";
                isToOtherTZ = false;
                textToTimeZone.setEditable(false);
                textToTimeZone.setBackground(new Color(255, 228, 225));
            } else
            if("Other Timezone".equals(text))
            {
                isToOtherTZ = true;
                textToTimeZone.setEditable(true);
                textToTimeZone.setBackground(Color.WHITE);
            }
        }

        private ToRadioButtonHandler()
        {
            super();
            this$0 = TimeConverterGUI.this;
        }

        ToRadioButtonHandler(ToRadioButtonHandler toradiobuttonhandler)
        {
            this();
        }
    }


    private JFrame frameTimeConverter;
    private JLabel labelEmpty1;
    private JLabel labelEmpty2;
    private JLabel labelStoreNumber;
    private JLabel labelStoreNameAndCountry;
    private JLabel labelStoreIDAndPhonenumber;
    private JLabel labelStoreTimezone;
    private JLabel labelCurrentTimeAtStore;
    private JLabel labelCurrentIST;
    private JLabel labelChooseFromFormat;
    private JLabel labelChooseFromTimezone;
    private JLabel labelChooseToFormat;
    private JLabel labelChooseToTimezone;
    private JLabel labelResult;
    //private JPanel panelStoreDetails;
    private JPanel panelconvertionDetails;
    private JPanel panelButtons;
    private JPanel panelResult;
    private JTextField textStoreNumber;
    private JTextField textStoreNameAndCountry;
    private JTextField textStoreIDAndPhonenumber;
    private JTextField textStoreTimezone;
    private JTextField textCurrentTimeAtStore;
    private JTextField textCurrentIST;
    private JTextField textTimeToConvert;
    private JTextField textFromTimeZone;
    private JTextField textToTimeZone;
    private JTextField textConvertedTime;
    private JTextField textResult;
    private JButton buttonConvert;
    private JButton buttonReset;
    private JRadioButton radioButtonFromMillisecond;
    private JRadioButton radioButtonFromDate;
    private JRadioButton radioButtonFromCurrentTime;
    private JRadioButton radioButtonFromGMT;
    private JRadioButton radioButtonFromTimezone;
    private JRadioButton radioButtonFromIST;
    private JRadioButton radioButtonToMillisecond;
    private JRadioButton radioButtonToDate;
    private JRadioButton radioButtonToGMT;
    private JRadioButton radioButtonToTimezone;
    private JRadioButton radioButtonToIST;
    private String fromFormat;
    private String fromTimeZoneString;
    private String toFormat;
    private String toTimeZoneString;
    public static String errorMessage = null;
    private boolean isFromOtherTZ;
    private boolean isToOtherTZ;
    private Date currentDate;
    long timeInMilliseconds;
    DateTime jodaTime;
    SimpleDateFormat dateFormatter;
    DateTimeFormatter parser1;
    InputStream in;

    public TimeConverterGUI()
    {
        fromFormat = "Milliseconds";
        fromTimeZoneString = "GMT";
        toFormat = "Milliseconds";
        toTimeZoneString = "GMT";
        isFromOtherTZ = false;
        isToOtherTZ = false;
        currentDate = null;
        timeInMilliseconds = (new Date()).getTime();
        jodaTime = null;
        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        parser1 = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss");
       // in = getClass().getResourceAsStream("/timeConverter/main/StoreAndTimezone.txt");
   //     StoreDetailsParser.populateStoreDetailsMap(in);
        frameTimeConverter = new JFrame("Time Converter");
        frameTimeConverter.setName("Time Converter");
        frameTimeConverter.setVisible(true);
        frameTimeConverter.setLocation(80, 200);
        frameTimeConverter.setVisible(true);
        frameTimeConverter.setDefaultCloseOperation(3);
        labelEmpty1 = new JLabel("");
        labelEmpty2 = new JLabel("");
        labelStoreNumber = new JLabel("  Store number");
        labelStoreNameAndCountry = new JLabel("  Store Name, Country");
        labelStoreIDAndPhonenumber = new JLabel("  Store_ID, Ph#");
        labelStoreTimezone = new JLabel("  Store Timezone");
        labelCurrentTimeAtStore = new JLabel("  Current time at the store");
        labelCurrentIST = new JLabel("  Corresponding IST");
        labelChooseFromFormat = new JLabel(" Convert From Format:");
        labelChooseFromTimezone = new JLabel(" Convert From Timezone:");
        labelChooseToFormat = new JLabel(" Convert To Format :");
        labelChooseToTimezone = new JLabel(" Convert To Timezone :");
        labelResult = new JLabel("  Result");
        textStoreNumber = new JTextField(13);
      //  textStoreNumber.addFocusListener(new StoreNumberTextFieldFocusListener(null));
        textStoreNameAndCountry = new JTextField(16);
        textStoreIDAndPhonenumber = new JTextField(13);
        textStoreTimezone = new JTextField(13);
        textCurrentTimeAtStore = new JTextField(13);
        textCurrentIST = new JTextField(13);
        textTimeToConvert = new JTextField(13);
        textFromTimeZone = new JTextField(5);
        textFromTimeZone.setEditable(false);
      //  textFromTimeZone.setBackground(new Color(255, 228, 225));
        textFromTimeZone.getDocument().addDocumentListener(new TimezoneDocumentListener(null));
        textToTimeZone = new JTextField(13);
        textToTimeZone.setEditable(false);
       ///////////////////////////////////// textToTimeZone.setBackground(new Color(255, 228, 225));
        textToTimeZone.getDocument().addDocumentListener(new TimezoneDocumentListener(null));
        textConvertedTime = new JTextField(13);
        Container contentPane = frameTimeConverter.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        GridBagConstraints frameConstraints = new GridBagConstraints();
        /*panelStoreDetails = new JPanel();
        panelStoreDetails.setLayout(new GridLayout(0, 6));
        panelStoreDetails.setOpaque(true);
        panelStoreDetails.setVisible(true);
        panelStoreDetails.setBackground(Color.LIGHT_GRAY);
        panelStoreDetails.add(labelStoreNumber);
        panelStoreDetails.add(textStoreNumber);
        panelStoreDetails.add(labelStoreNameAndCountry);
        panelStoreDetails.add(textStoreNameAndCountry);
        panelStoreDetails.add(labelStoreIDAndPhonenumber);
        panelStoreDetails.add(textStoreIDAndPhonenumber);
        panelStoreDetails.add(labelStoreTimezone);
        panelStoreDetails.add(textStoreTimezone);
        System.out.println("added Store Tz");
        panelStoreDetails.add(labelCurrentTimeAtStore);
        panelStoreDetails.add(textCurrentTimeAtStore);
        panelStoreDetails.add(labelCurrentIST);
        panelStoreDetails.add(textCurrentIST);*/
        frameConstraints.fill = 2;
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 1;
        frameConstraints.ipady = 5;
       // contentPane.add(panelStoreDetails, frameConstraints);
        panelconvertionDetails = new JPanel();
        panelconvertionDetails.setLayout(new GridLayout(0, 5));
        panelconvertionDetails.setOpaque(true);
     //   panelconvertionDetails.setBackground(new Color(255, 228, 225));
        panelconvertionDetails.setVisible(true);
        panelconvertionDetails.add(labelChooseFromFormat);
        addConvertFromFormatRadioButton();
        panelconvertionDetails.add(labelChooseFromTimezone);
        addConvertFromTimezoneRadioButton();
        panelconvertionDetails.add(labelChooseToFormat);
        addConvertToFormatRadioButton();
        panelconvertionDetails.add(labelChooseToTimezone);
        addConvertToTimezoneRadioButton();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 2;
        frameConstraints.ipady = 30;
        contentPane.add(panelconvertionDetails, frameConstraints);
        addButtons();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 3;
        frameConstraints.ipady = 30;
        contentPane.add(panelButtons, frameConstraints);
        addResultPanel();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 4;
        frameConstraints.ipady = 60;
        contentPane.add(panelResult, frameConstraints);
        setDefaultSelections();
        frameTimeConverter.pack();
    }

    private void setDefaultSelections()
    {
        textTimeToConvert.requestFocus(true);
        radioButtonFromGMT.setEnabled(false);
        radioButtonFromIST.setEnabled(false);
        radioButtonFromTimezone.setEnabled(false);
        radioButtonToDate.setSelected(true);
        toFormat = "Date";
        radioButtonToMillisecond.setEnabled(false);
    }

    private void addButtons()
    {
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridBagLayout());
        panelButtons.setVisible(true);
        panelButtons.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridwidth = 1;
        buttonConstraints.gridheight = 1;
        buttonConvert = new JButton("Convert");
        buttonReset = new JButton("Reset");
        buttonConvert.addActionListener(new ConvertionHandler(null));
        buttonReset.addActionListener(new ResetHandler(null));
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 0;
        panelButtons.add(buttonConvert);
        buttonConstraints.gridx = 2;
        buttonConstraints.gridy = 0;
        panelButtons.add(buttonReset);
    }

    private void addResultPanel()
    {
        panelResult = new JPanel();
        panelResult.setLayout(new GridBagLayout());
        GridBagConstraints resutlConstraints = new GridBagConstraints();
        resutlConstraints.gridwidth = 1;
        resutlConstraints.gridheight = 1;
        panelResult.setVisible(true);
        panelResult.setBackground(Color.WHITE);
        resutlConstraints.gridx = 1;
        resutlConstraints.gridy = 0;
        panelResult.add(labelResult, resutlConstraints);
        resutlConstraints.gridx = 2;
        resutlConstraints.gridy = 0;
        textResult = new JTextField(20);
        panelResult.add(textResult, resutlConstraints);
    }

    private void addConvertFromFormatRadioButton()
    {
        ButtonGroup formatButtonGroup = new ButtonGroup();
        radioButtonFromMillisecond = new JRadioButton("Milliseconds", true);
        radioButtonFromDate = new JRadioButton("dd-mmm-yy HH:mm:ss");
        radioButtonFromCurrentTime = new JRadioButton("Current Time (IST)");
        radioButtonFromMillisecond.addActionListener(new FromRadioButtonHandler(null));
        radioButtonFromDate.addActionListener(new FromRadioButtonHandler(null));
        radioButtonFromCurrentTime.addActionListener(new FromRadioButtonHandler(null));
        formatButtonGroup.add(radioButtonFromMillisecond);
        formatButtonGroup.add(radioButtonFromDate);
        formatButtonGroup.add(radioButtonFromCurrentTime);
        panelconvertionDetails.add(radioButtonFromMillisecond);
        panelconvertionDetails.add(radioButtonFromDate);
        panelconvertionDetails.add(radioButtonFromCurrentTime);
        panelconvertionDetails.add(textTimeToConvert);
    }

    private void addConvertFromTimezoneRadioButton()
    {
        ButtonGroup timezoneButtonGroup = new ButtonGroup();
        radioButtonFromGMT = new JRadioButton("GMT", true);
        radioButtonFromTimezone = new JRadioButton("Other Timezone");
        radioButtonFromIST = new JRadioButton("IST");
        radioButtonFromGMT.addActionListener(new FromRadioButtonHandler(null));
        radioButtonFromTimezone.addActionListener(new FromRadioButtonHandler(null));
        radioButtonFromIST.addActionListener(new FromRadioButtonHandler(null));
        timezoneButtonGroup.add(radioButtonFromGMT);
        timezoneButtonGroup.add(radioButtonFromTimezone);
        timezoneButtonGroup.add(radioButtonFromIST);
        panelconvertionDetails.add(radioButtonFromGMT);
        panelconvertionDetails.add(radioButtonFromIST);
        panelconvertionDetails.add(radioButtonFromTimezone);
        panelconvertionDetails.add(textFromTimeZone);
    }

    private void addConvertToFormatRadioButton()
    {
        ButtonGroup formatButtonGroup = new ButtonGroup();
        radioButtonToMillisecond = new JRadioButton("Milliseconds", true);
        radioButtonToDate = new JRadioButton("dd-mmm-yy HH:mm:ss");
        radioButtonToMillisecond.addActionListener(new ToRadioButtonHandler(null));
        radioButtonToDate.addActionListener(new ToRadioButtonHandler(null));
        formatButtonGroup.add(radioButtonToMillisecond);
        formatButtonGroup.add(radioButtonToDate);
        panelconvertionDetails.add(radioButtonToMillisecond);
        panelconvertionDetails.add(radioButtonToDate);
        panelconvertionDetails.add(labelEmpty1);
        panelconvertionDetails.add(labelEmpty2);
    }

    private void addConvertToTimezoneRadioButton()
    {
        ButtonGroup timezoneButtonGroup = new ButtonGroup();
        radioButtonToGMT = new JRadioButton("GMT", true);
        radioButtonToTimezone = new JRadioButton("Other Timezone");
        radioButtonToIST = new JRadioButton("IST");
        radioButtonToGMT.addActionListener(new ToRadioButtonHandler(null));
        radioButtonToTimezone.addActionListener(new ToRadioButtonHandler(null));
        radioButtonToIST.addActionListener(new ToRadioButtonHandler(null));
        timezoneButtonGroup.add(radioButtonToGMT);
        timezoneButtonGroup.add(radioButtonToTimezone);
        timezoneButtonGroup.add(radioButtonToIST);
        panelconvertionDetails.add(radioButtonToGMT);
        panelconvertionDetails.add(radioButtonToIST);
        panelconvertionDetails.add(radioButtonToTimezone);
        panelconvertionDetails.add(textToTimeZone);
    }

    public String getCurrentTimeForTimezone(String timezone)
    {
        System.out.println((new StringBuilder("inside getCurrentTimeForTimezone ")).append(timezone).toString());
        long currentTimeInMilliseconds = (new Date()).getTime();
        DateTime jodaTime = new DateTime(currentTimeInMilliseconds, DateTimeZone.forID(timezone));
        return parser1.print(jodaTime);
    }

    public void resetStoreDetailsTextBoxes()
    {
        textCurrentTimeAtStore.setText("");
        textStoreIDAndPhonenumber.setText("");
        textStoreNameAndCountry.setText("");
        textStoreTimezone.setText("");
        textCurrentIST.setText("");
    }

    public static void main(String args[])
    {
        TimeConverterGUI tc = new TimeConverterGUI();
        System.out.println((new StringBuilder("Systime : ")).append((new Date()).getTime()).toString());
    }

}