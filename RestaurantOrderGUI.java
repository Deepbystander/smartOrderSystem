/**
 * <h1> Restaurant Order </h1>
 *
 * Enter name, table no, and select food and beverage for make oder table no
 * should be 1-8 selection of food and beverage should not me default one while
 * making oder selection of food and beverage to get nutrients value selection
 * of table no before view oder only 3 time customer can make wrong entry
 *
 * @author Dipesh Maharjan
 * @id 12062632
 * @since 2018-19-4
 * @file name ResturantOderGUI.java
 */
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class RestaurantOrderGUI extends JFrame {

    public static void main(String[] args) {
        new RestaurantOrderGUI();
    }

    public RestaurantOrderGUI() {
        //variable declaration
        orderedCustomersList = new ArrayList<>();
        foodItems = new ArrayList<>();
        beverageItems = new ArrayList<>();

        //Interface Setup
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 650);
        setResizable(true);
        panelFormat();
        add(contextArea);
        allActionsTrigger();
        getreadFile();
        addMenuItems();
        setVisible(true);
    }

    // variables for jpanel
    JPanel contextArea;

    //variables for jtextfield
    JTextField customerNameField;
    JTextField tableNumberField;

    //variables for jcombobox
    JComboBox menuBox;
    JComboBox beverageBox;

    //variables for jtextArea
    JTextArea displayContext;

    // display area jbutton
    JButton enterData;
    JButton displayChoices;
    JButton displayDetails;
    JButton clearDisplay;
    JButton quitApp;

    //arraylist varaible declaration for storage
    ArrayList<MenuItem> foodItems;
    ArrayList<MenuItem> beverageItems;
    ArrayList<OrderedCustomer> orderedCustomersList;

    //fine name declaration
    String fileName = "Ass1Data.csv";

    //variable for nutirend head(eg protein, energy)
    static String[] nutirentHeader;

    //variable for error counting
    int errorCount = 0;

    private void panelFormat() {
        // panel formation method start
        contextArea = new JPanel();

        //layouts
        contextArea.setLayout(new BorderLayout());
        JPanel top = new JPanel(new GridLayout(2, 1));

        //jpanels
        JPanel panelFirst = new JPanel(new FlowLayout());
        JPanel panelSecond = new JPanel(new FlowLayout());
        JPanel panelMid = new JPanel(new FlowLayout());
        JPanel messageArea = new JPanel(new FlowLayout());
        JPanel bottom = new JPanel(new GridLayout());

        //labels
        JLabel customerLabel = new JLabel("Customer Details Name");
        JLabel tableNo = new JLabel("Table Number");
        JLabel menuLabel = new JLabel("Menu Choices Food");
        JLabel beverageLabel = new JLabel("Beverage");

        //input field
        customerNameField = new JTextField(40);
        tableNumberField = new JTextField(10);
        customerNameField.setText("");
        tableNumberField.setText("1");

        //combo box
        menuBox = new JComboBox();
        beverageBox = new JComboBox();

        //text area
        displayContext = new JTextArea(31, 93);
        JScrollPane displayContextScrollPane = new JScrollPane(displayContext);
        displayContext.setText("");
        displayContext.setEditable(false);
        messageArea.add(displayContextScrollPane);
        messageArea.setFont(new Font("Table No.", Font.BOLD, WIDTH));

        //bottons
        enterData = new JButton("Enter Data");
        displayChoices = new JButton("Display Choices");
        displayDetails = new JButton("Display Order");
        clearDisplay = new JButton("Clear Display");
        quitApp = new JButton("Quit");

        //boders
        panelMid.setBorder(BorderFactory.createTitledBorder("Your Menu Choice and Nutrition Information"));
        bottom.setBorder(BorderFactory.createTitledBorder("Command Buttons"));

        //adding controls to panels
        //top
        top.add(panelFirst);
        top.add(panelSecond);
        //first
        panelFirst.add(customerLabel);
        panelFirst.add(customerNameField);
        panelFirst.add(tableNo);
        panelFirst.add(tableNumberField);
        //second
        panelSecond.add(menuLabel);
        panelSecond.add(menuBox);
        panelSecond.add(beverageLabel);
        panelSecond.add(beverageBox);
        contextArea.add(top, BorderLayout.NORTH);
        //middle panel formation
        panelMid.add(messageArea);
        contextArea.add(panelMid);

        // button formation
        bottom.add(enterData);
        bottom.add(displayChoices);
        bottom.add(displayDetails);
        bottom.add(clearDisplay);
        bottom.add(quitApp);
        contextArea.add(bottom, BorderLayout.SOUTH);
    }

    //function for reading xcv file
    private void getreadFile() {
        try {
            File file = new File(fileName);
            ArrayList<String> data = new ArrayList<String>();
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNextLine()) {
                data.add(inputStream.nextLine());
            }
            for (int i = 0; i < data.size(); i++) {
                String[] element = data.get(i).split(",");
                //selecting data to store values
                if (element[0].equals("Course")) {
                    nutirentHeader = element;//for the header of contains
                }

                if (element[0].equals("food")) {
                    addFood(element);//for the food dropdown
                }

                if (element[0].equals("Beverage")) {
                    addBeverage(element);//for the beverage dropdown
                }
            }
            inputStream.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "NO FILE, PLEASE LOAD THE CORRECT FILE", JOptionPane.ERROR_MESSAGE);
        }
    }

    //function for the action while click in buttons
    private void allActionsTrigger() {

        //event for the Enter Data buttons click
        enterData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderedCustomer customer = new OrderedCustomer();
                ArrayList<MenuItem> item = new ArrayList<>();
                String customerName = customerNameField.getText();//storing customer name
                //validation start
                if (!customerName.equals("")) {//name validation
                    try {
                        customer.setCustomerName(customerName);
                        String tableNumber = tableNumberField.getText();//storing table number
                        if (tableNumber.length() == 0) {//validation for blank table number
                            JOptionPane.showMessageDialog(null, "Table Number cannot be blank", "Table Number", JOptionPane.ERROR_MESSAGE);
                            errorCheck(errorCount);//error count checking for alert
                            return;
                        } else {
                            try {
                                boolean check = selectionCheck();//validation for food and beverage selection
                                if (check == true) {
                                    if (errorCount < 2) {
                                        //validation result check for food and beverage selection
                                        item.add(new MenuItem(foodItems.get(menuBox.getSelectedIndex() - 1)));//storing item food , here -1 is because of default selection added in food
                                        item.add(new MenuItem(beverageItems.get(beverageBox.getSelectedIndex() - 1)));//storing item beverage, here -1 is because of default selection added in food
                                        customer.setOrderedCustomrItems(item);//storing whole item in customer
                                        orderedCustomersList.add(customer);//storing customer in list for oder display

                                        //validation for confirmation of oder
                                        int result = JOptionPane.showConfirmDialog(null, "THANK YOU FOR YOUR ODER! PLEASE WAIT FOR SERVE "
                                                + "\n\t Your choice are:\n\t" + menuBox.getSelectedItem() + "\n\t" + beverageBox.getSelectedItem(),
                                                "Order Placed", JOptionPane.YES_NO_OPTION);

                                        if (result == JOptionPane.YES_OPTION) {//initialzing deafult value and checking customer response for confiramation
                                            customerNameField.setText("");//initializing default name
                                            tableNumberField.setText("1");//initializing default table no.
                                            displayContext.setText("");//initializing default text field
                                            addMenuItems();//initializing default selection
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "You have enter error oder 3 times please ask staff for help!!!",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                customer.setCustomerTableNumber(Integer.parseInt(tableNumber));//validation for table no range by calling function
                            } catch (Exception ex) {//error message for out of rage alert for table no.
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Table Number", JOptionPane.ERROR_MESSAGE);
                                errorCheck(errorCount);//error count checking for alert
                                return;
                            }
                        }
                    } catch (Exception ex) {//error catch
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Customer Name", JOptionPane.ERROR_MESSAGE);
                        errorCheck(errorCount);//error count checking for alert
                        return;
                    }
                } else {//error message for name validation
                    JOptionPane.showMessageDialog(null, "Customer name cannot be blank", "Customer Name", JOptionPane.ERROR_MESSAGE);
                    errorCheck(errorCount);//error count checking for alert
                    return;
                }

            }
        });

        //event for Display choices buttons
        displayChoices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checkSelect = selectionCheck();//selection check of food and beverage
                if (checkSelect == true) {//validation for food and beverage selection
                    ArrayList<Double> total = new ArrayList<Double>();
                    for (int i = 1; i < nutirentHeader.length; i++) {//for intial value
                        total.add(0.00);//initizaling value zero to avoid null value
                    }
                    String text = "";//initizaling text

                    //selection food value add for total
                    MenuItem selectedItem = new MenuItem(foodItems.get(menuBox.getSelectedIndex() - 1));//storing item food , here -1 is because of default selection added in food
                    text += selectedItem.getMenuItemName() + "\n";//adding text

                    for (int i = 0; i < selectedItem.getMenuNutrientList().size(); i++) {//Nutrient for selected food value adding for total
                        text += selectedItem.getMenuNutrientList().get(i).toString();
                        for (int j = 2; j < nutirentHeader.length - 1; j++) {
                            if (selectedItem.getMenuNutrientList().get(i).getNutrientName().equals(nutirentHeader[j])) {
                                total.set(i, total.get(i) + selectedItem.getMenuNutrientList().get(i).getNutrientQuantity());
                            }
                        }
                    }

                    //beverage selection total nutrient add with food
                    MenuItem seletedBeverage = new MenuItem(beverageItems.get(beverageBox.getSelectedIndex() - 1));//storing item beverage , here -1 is because of default selection added in food
                    text += "\n" + seletedBeverage.getMenuItemName() + "\n";

                    for (int i = 0; i < seletedBeverage.getMenuNutrientList().size(); i++) {//Nutrient for selected beverage value adding for total
                        text += seletedBeverage.getMenuNutrientList().get(i).toString();
                        for (int j = 2; j < nutirentHeader.length - 1; j++) {
                            if (seletedBeverage.getMenuNutrientList().get(i).getNutrientName().equals(nutirentHeader[j])) {
                                total.set(i, total.get(i) + seletedBeverage.getMenuNutrientList().get(i).getNutrientQuantity());
                            }
                        }
                    }

                    //display for display choices that show details of food and beverage
                    text += "\nTotal Nutritional Values:\n";
                    for (int i = 2; i < nutirentHeader.length - 1; i++) {
                        text += nutirentHeader[i] + ": " + String.format("%.2f", total.get(i - 2)) + "\n";
                    }
                    displayContext.setText(text);//implemetion result text in display field
                    addMenuItems();//setup for difault selection
                }
            }
        });

        displayDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean orderFoundFlag = false;
                //getting table no to display order
                String searchTableNumber = tableNumberField.getText();
                if (searchTableNumber.length() == 0) {//validation for table no not blank
                    JOptionPane.showMessageDialog(null, "Table Number cannot be blank", "Table Number", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    try {
                        //validation for table no rage
                        int table = Integer.parseInt(searchTableNumber);
                        if (table < 1 || table > 8) {
                            JOptionPane.showMessageDialog(null, "Table number can be between 1 and 8.", "Table Number", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String displayText = "";//default setup
                            for (int i = 0; i < orderedCustomersList.size(); i++) {

                                if (orderedCustomersList.get(i).getCustomerTableNumber() == table) {//table no. check for oder display
                                    orderFoundFlag = true;
                                    displayText += orderedCustomersList.get(i).toString() + "\n";
                                }
                            }

                            if (!orderFoundFlag) {//no oder error
                                JOptionPane.showMessageDialog(null, "There is NO ORDER for this Table!!", "Order Not Found", JOptionPane.ERROR_MESSAGE);
                            } else {
                                displayContext.setText(displayText);
                            }
                        }
                    } catch (Exception ex) {//Out of Range Error
                        JOptionPane.showMessageDialog(null, "Only integer in Table Number", "Table Number", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        });

        //event for Clear Display button
        clearDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayContext.setText("");//default text box
                customerNameField.setText("");//default name space
                tableNumberField.setText("1");//default Table No.
                addMenuItems();//Default food and beverage selection
            }
        });

        //event for Quit button
        quitApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//exit of system
            }
        });
    }

    //fucntion for add food list
    private void addFood(String[] element) {
        ArrayList<Nutrient> foodNutrients = new ArrayList<>();
        for (int i = 2; i < nutirentHeader.length; i++) {//nutrient valuse add with selection for beverage
            foodNutrients.add(new Nutrient(nutirentHeader[i], Float.parseFloat(element[i])));
        }
        foodItems.add(new MenuItem(element[1], Integer.parseInt(element[7]), foodNutrients));
    }

    //function for add beverage list
    private void addBeverage(String[] element) {
        ArrayList<Nutrient> beverageNutrients = new ArrayList<>();
        for (int i = 2; i < nutirentHeader.length; i++) {//nutrient valuse add with selection for beverage
            beverageNutrients.add(new Nutrient(nutirentHeader[i], Float.parseFloat(element[i])));
        }
        beverageItems.add(new MenuItem(element[1], Integer.parseInt(element[7]), beverageNutrients));
    }

    //function for adding food and beverage in combo box
    private void addMenuItems() {
        menuBox.removeAllItems();
        beverageBox.removeAllItems();
        menuBox.addItem("Caprese Sandwich");//default food choice
        beverageBox.addItem("Cafe Latte");//default beverage choice

        for (int i = 0; i < foodItems.size(); i++) {//food List add
            menuBox.addItem(foodItems.get(i).getMenuItemName());
        }

        for (int i = 0; i < beverageItems.size(); i++) {//beverag list add
            beverageBox.addItem(beverageItems.get(i).getMenuItemName());
        }
    }

    //function for error count
    private void errorCheck(int i) {
        if (i > 1) {//error limitaion check
            JOptionPane.showMessageDialog(null, "You have enter error oder 3 times please ask staff for help!!!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (i > 0) {//error alert before lock of system
            JOptionPane.showMessageDialog(null, "You have enter error oder 2 times,this is Last chance \n "
                    + "PLEASE ENTER CORRECTLY!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            errorCount++;//error counting on
        } else {
            errorCount++;//error counting on
        }
    }

    private boolean selectionCheck() {//function for selection validation
        boolean checkFlag = false;//flag setup
        if (menuBox.getSelectedIndex() > 0) {//seletion validation for not default selection been selected for food
            checkFlag = true;
            if (beverageBox.getSelectedIndex() > 0) {//seletion validation for not default selection been selected for food
                checkFlag = true;
            } else {//error for wrong beverage choice
                JOptionPane.showMessageDialog(null, "You Have Not Select Any Beverage", "Beverage Selection Error", JOptionPane.ERROR_MESSAGE);
                errorCheck(errorCount);//error validation
                checkFlag = false;//flag setup
            }
        } else {//error for wrong food choice
            JOptionPane.showMessageDialog(null, "You Have Not Selected Any Food", "Food Selection Error", JOptionPane.ERROR_MESSAGE);
            errorCheck(errorCount);//error validation
            checkFlag = false;//flag setup
        }
        return checkFlag;//flag setup
    }
}//end of main
