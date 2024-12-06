package View;

import DTO.ATM_DTO;
import DatabaseConnectionManager.IMBankConnectionManager;
import Utility.ViewUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATM {

    private JTextArea receiptTextArea;

    public ATM() {
        initATMGuiComponents();
    }

    private void initATMGuiComponents() {
        JFrame frame = new JFrame("ATM Machine");
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.white);
        logoPanel.setLayout(null);
        logoPanel.setBounds(0, 0, 900, 100);

        JLabel IMBankLabel = new JLabel("I.M Bank");
        IMBankLabel.setForeground(new Color(0x23234D));
        IMBankLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        IMBankLabel.setBounds(20, 25, 180, 40);

        JLabel reminderLabel = new JLabel("<html> You are currently transacting through <br>a cardless method</html>");
        reminderLabel.setForeground(new Color(0x23234D));
        reminderLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        reminderLabel.setBounds(700, 15, 180, 70);

        logoPanel.add(IMBankLabel);
        logoPanel.add(reminderLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0x223345));
        mainPanel.setBounds(0, 100, 900, 500);

        JLabel otpLabel = new JLabel("Enter your OTP:");
        otpLabel.setForeground(Color.white);
        otpLabel.setFont(new Font("Arial", Font.BOLD, 30));
        otpLabel.setBounds(330, 130, 300, 50);

        JTextField otpField = new JTextField();
        otpField.setBounds(360, 190, 150, 50);
        otpField.setForeground(new Color(0x223345));
        otpField.setFont(new Font("Arial", Font.BOLD, 30));
        otpField.setHorizontalAlignment(JTextField.CENTER);
        otpField.addKeyListener(ViewUtility.addNumberInputKeyListener());
        

        JLabel pinLabel = new JLabel("Enter your 4-digit PIN:");
        pinLabel.setForeground(Color.white);
        pinLabel.setFont(new Font("Arial", Font.BOLD, 30));
        pinLabel.setBounds(290, 280, 500, 50);

        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(360, 340, 150, 50);
        pinField.setForeground(new Color(0x223345));
        pinField.setFont(new Font("Arial", Font.BOLD, 30));
        pinField.setHorizontalAlignment(JPasswordField.CENTER);
        pinField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        JButton enterButton = new JButton("Enter");
        enterButton.setForeground(new Color(0x223345));
        enterButton.setFont(new Font("Arial", Font.BOLD, 30));
        enterButton.setFocusable(false);
        enterButton.setBounds(340, 480, 190, 40);
        enterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(otpField.getText().isEmpty() || String.valueOf(pinField.getPassword()).isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Please enter credentials");
                    return;
                }
                SwingWorker<ATM_DTO, Void> worker = new  SwingWorker<ATM_DTO, Void>() {

                    @Override
                    protected ATM_DTO doInBackground() throws Exception {
                        Thread.sleep(50);
                        return verifyOTPAndPIN(Integer.parseInt(
                                otpField.getText()),
                                Integer.parseInt(String.valueOf(pinField.getPassword()))
                        );
                    }

                    @Override
                    protected void done() {
                        try{
                            ATM_DTO dto = get();
                            if(dto != null){
                                frame.remove(mainPanel);
                                JPanel confirmationPanel = new JPanel();
                                confirmationPanel.setLayout(null);
                                confirmationPanel.setBackground(new Color(0x223345));
                                confirmationPanel.setBounds(0, 100, 900, 500);

                                JLabel amountLabel = new JLabel("<html> Confirm your (transaction type) <br>amount (In Php):</html>");
                                amountLabel.setForeground(Color.white);
                                amountLabel.setFont(new Font("Arial", Font.BOLD, 30));
                                amountLabel.setBounds(210, 150, 500, 110);

                                JTextField amountField = new JTextField();
                                amountField.setBounds(300, 280, 300, 50);
                                amountField.setForeground(new Color(0x223345));
                                amountField.setFont(new Font("Arial", Font.BOLD, 30));
                                amountField.addKeyListener(ViewUtility.addNumberInputKeyListener());

                                JButton backButton = new JButton("Back");
                                backButton.setForeground(new Color(0x223345));
                                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                                backButton.setFocusable(false);
                                backButton.setBounds(50, 480, 190, 40);
                                backButton.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        otpField.setText("");
                                        pinField.setText("");
                                        frame.remove(confirmationPanel);
                                        frame.add(mainPanel);
                                        frame.revalidate();
                                        frame.repaint();
                                    }
                                });

                                JButton proceedButton = new JButton("Proceed");
                                proceedButton.setForeground(new Color(0x223345));
                                proceedButton.setFont(new Font("Arial", Font.BOLD, 20));
                                proceedButton.setFocusable(false);
                                proceedButton.setBounds(650, 480, 190, 40);
                                proceedButton.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(dto.getBalance() == Integer.parseInt(amountField.getText())){
                                            SwingWorker<Integer, Void> worker1 = new SwingWorker<Integer, Void>() {
                                                @Override
                                                protected Integer doInBackground() throws Exception {
                                                    return updateTransactionStatus(dto.getTransactionID());
                                                }

                                                @Override
                                                protected void done() {
                                                    try{
                                                        int rowsAffected = get();
                                                        if(rowsAffected > 0){
                                                            confirmationPanel.remove(amountLabel);
                                                            confirmationPanel.remove(amountField);
                                                            confirmationPanel.remove(backButton);
                                                            confirmationPanel.remove(proceedButton);

                                                            JLabel closingLabel = new JLabel("Thank you for transacting!");
                                                            closingLabel.setForeground(Color.white);
                                                            closingLabel.setFont(new Font("Arial", Font.BOLD, 30));
                                                            closingLabel.setBounds(250, 160, 700, 80);

                                                            JLabel askReceiptLabel=new JLabel("Would you like to see your receipt?");
                                                            askReceiptLabel.setForeground(Color.white);
                                                            askReceiptLabel.setFont(new Font("Arial", Font.BOLD, 30));
                                                            askReceiptLabel.setBounds(180, 200, 700, 80);

                                                            JButton yesButton=new JButton("Yes");
                                                            yesButton.setForeground(new Color(0x223345));
                                                            yesButton.setFont(new Font("Arial", Font.BOLD, 20));
                                                            yesButton.setFocusable(false);
                                                            yesButton.setBounds(210, 340, 190, 40);
                                                            yesButton.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    frame.remove(confirmationPanel);

                                                                    JPanel bgPanel = new JPanel();
                                                                    bgPanel.setLayout(null);
                                                                    bgPanel.setBackground(new Color(0x223345));
                                                                    bgPanel.setBounds(0, 100, 900, 500);

                                                                    JPanel receiptPanel = new JPanel();
                                                                    receiptPanel.setLayout(null);
                                                                    receiptPanel.setBackground(Color.white);
                                                                    receiptPanel.setBounds(200, 130, 450, 400);

                                                                    // Create a JTextArea for the receipt
                                                                    receiptTextArea = new JTextArea();
                                                                    receiptTextArea.setText(
                                                                            "          I.M BANK\n" +
                                                                                    "--------------------------------------------\n" +
                                                                                    "DATE                 TIME                 TRANSACTION NUMBER     ATM ID\n" +
                                                                                    "Davao City, 8000, Philippines\n\n" +
                                                                                    "Card number: ********************\n" +
                                                                                    "(Transaction type) from: ******\n" +
                                                                                    "Amount: ***********\n" +
                                                                                    "Charge fee: ********\n" +
                                                                                    "Total: ************\n" +
                                                                                    "Account Balance: **************\n\n" +
                                                                                    "******************************************\n" +
                                                                                    "PLEASE RETAIN OR DISPOSE OF THOUGHTFULLY.\n" +
                                                                                    "******************************************"
                                                                    );
                                                                    receiptTextArea.setEditable(false); // Make the text area read-only
                                                                    receiptTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
                                                                    receiptTextArea.setBounds(10, 10, 430, 380);
                                                                    receiptTextArea.setForeground(Color.black);
                                                                    receiptTextArea.setBackground(Color.white);
                                                                    receiptTextArea.setLineWrap(true);
                                                                    receiptTextArea.setWrapStyleWord(true);

                                                                    // Add the text area to the receipt panel
                                                                    receiptPanel.add(receiptTextArea);

                                                                    bgPanel.add(receiptPanel);

                                                                    frame.add(bgPanel);
                                                                    frame.revalidate();
                                                                    frame.repaint();
                                                                }
                                                            });

//                                                                    Timer timer = new Timer(2500, new ActionListener() {
//
//                                                                        @Override
//                                                                        public void actionPerformed(ActionEvent e) {
//                                                                            frame.remove(bgPanel);
//                                                                            otpField.setText("");
//                                                                            pinField.setText("");
//                                                                            frame.add(mainPanel);
//                                                                            frame.revalidate();
//                                                                            frame.repaint();
//                                                                        }
//                                                                    });
//                                                                    timer.setRepeats(false);
//                                                                    timer.start();



                                                            JButton noButton=new JButton("No");
                                                            noButton.setForeground(new Color(0x223345));
                                                            noButton.setFont(new Font("Arial", Font.BOLD, 20));
                                                            noButton.setFocusable(false);
                                                            noButton.setBounds(470, 340, 190, 40);
                                                            noButton.addActionListener(new ActionListener() {

                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {

                                                                    confirmationPanel.remove(closingLabel);
                                                                    confirmationPanel.remove(askReceiptLabel);
                                                                    confirmationPanel.remove(yesButton);
                                                                    confirmationPanel.remove(noButton);

                                                                    JLabel closingLabel2 = new JLabel("<html>Thank you for transacting with I.M Bank.<br> &nbsp &nbsp &nbsp &nbsp &nbsp "
                                                                            + "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Come Again!</html>");
                                                                    closingLabel2.setForeground(Color.white);
                                                                    closingLabel2.setFont(new Font("Arial", Font.BOLD, 30));
                                                                    closingLabel2.setBounds(150, 270, 700, 80);

                                                                    confirmationPanel.add(closingLabel2);

                                                                    frame.revalidate();
                                                                    frame.repaint();

                                                                    Timer timer = new Timer(2500, new ActionListener(){
                                                                        @Override
                                                                        public void actionPerformed(ActionEvent e) {
                                                                            frame.remove(confirmationPanel);
                                                                            otpField.setText("");
                                                                            pinField.setText("");
                                                                            frame.add(mainPanel);
                                                                            frame.revalidate();
                                                                            frame.repaint();

                                                                        }
                                                                    });
                                                                    timer.setRepeats(false);
                                                                    timer.start();
                                                                }
                                                            });

                                                            confirmationPanel.add(closingLabel);
                                                            confirmationPanel.add(askReceiptLabel);
                                                            confirmationPanel.add(yesButton);
                                                            confirmationPanel.add(noButton);

                                                            frame.revalidate();
                                                            frame.repaint();
                                                        } else {
                                                            throw new Exception("An Error Occurred");
                                                        }
                                                    } catch (Exception e){
                                                        ViewUtility.showMessage(e.getMessage());
                                                    }
                                                }
                                            };
                                            worker1.execute();
                                        } else {
                                            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame, "You don't have enough balance"));
                                        }
                                    }
                                });

                                confirmationPanel.add(amountLabel);
                                confirmationPanel.add(amountField);
                                confirmationPanel.add(backButton);
                                confirmationPanel.add(proceedButton);

                                frame.add(confirmationPanel);
                                frame.revalidate();
                                frame.repaint();
                            } else {
                                throw new Exception("Invalid OTP or Card PIn");
                            }
                        } catch (Exception e){
                            ViewUtility.showMessage(e.getMessage());
                        }
                    }
                };
                worker.execute();
            }
        });

        mainPanel.add(otpLabel);
        mainPanel.add(otpField);
        mainPanel.add(pinLabel);
        mainPanel.add(pinField);
        mainPanel.add(enterButton);

        frame.add(logoPanel);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private ATM_DTO verifyOTPAndPIN(int OTP, int cardPIN) throws SQLException {
        String sql = "SELECT p.First_Name, p.Last_name, t.Amount, t.Transaction_Type, t.Transaction_ID "
                   + "FROM Transaction t "
                   + "JOIN Bank_Accounts ba ON t.Bank_Account_Number_ID = ba.Bank_Account_Number_ID "
                   + "JOIN Customers c ON ba.Customer_ID = c.Customer_ID "
                   + "JOIN Person p ON c.Person_ID = p.Person_ID "
                   + "JOIN Card_Info ci ON ba.Bank_Account_Number_ID = ci.Bank_Account_Number_ID "
                   + "WHERE t.OTP = ? AND ci.Card_PIN = ?";
        try(Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, OTP);
            ps.setInt(2, cardPIN);

            ResultSet rs = ps.executeQuery();
            ATM_DTO information = null;
            while(rs.next()){
                information = new ATM_DTO(
                        rs.getString("First_Name") + " " + rs.getString("Last_Name"),
                        rs.getString("Transaction_Type"),
                        rs.getFloat("Amount"),
                        rs.getInt("Transaction_ID")
                );
            }
            return information;
        }
    }

    private int updateTransactionStatus(int transactionID) throws SQLException {
        String sql = "UPDATE Transaction "
                   + "SET Request_Status = ?, OTP = '------' "
                   + "WHERE Transaction_ID = ? AND Request_Status = 'Pending'";
        try(Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, "Complete");
            ps.setInt(2, transactionID);

            return ps.executeUpdate();
        }
    }

    private void writeToFileReceipt(ATM_DTO atm_dto) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Receipt.txt"))){
            // Header Section
            writer.write(String.format("%-45s\n", "                   I.M Bank"));
            writer.write("-------------------------------------------------\n");

            // Transaction Details
            writer.write(String.format("%-20s: %s\n", "DATE", java.time.LocalDate.now()));
            writer.write(String.format("%-20s: %s\n", "TIME", java.time.LocalTime.now().withNano(0)));
            writer.write(String.format("%-20s: %s\n", "TRANSACTION NUMBER", "XXXX-XXXX-XXXX"));
            writer.write("\n");

            // Location and Card Info
            writer.write(String.format("%-45s\n", "Davao City, 8000, Philippines"));
            writer.write(String.format("%-20s: %s\n", "Card number", "XXXX-XXXX-XXXX-1234"));
            writer.write(String.format("%-20s: %s\n", "Transaction type", "(withdrawal)"));
            writer.write("\n");

            // Amount Details
            writer.write(String.format("%-20s: %s\n", "Amount", "Php 10,000.00"));
            writer.write(String.format("%-20s: %s\n", "Charge fee", "Php 50.00"));
            writer.write(String.format("%-20s: %s\n", "Total", "Php 10,050.00"));
            writer.write(String.format("%-20s: %s\n", "Account Balance", "Php 90,000.00"));
            writer.write("\n");

            // Footer Section
            writer.write("***********************************************\n");
            writer.write(" PLEASE RETAIN OR DISPOSE OF THOUGHTFULLY.     \n");
            writer.write("***********************************************\n");
        } catch (IOException e){
            ViewUtility.showMessage("Error writing receipt file");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATM();
            }
        });
    }
}
