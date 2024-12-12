package View;

import DTO.ATM_DTO;
import DatabaseConnectionManager.IMBankConnectionManager;
import Repository.AffiliatedBankRepository;
import Repository.BankAccountRepository;
import Repository.TransactionRepository;
import Utility.ViewUtility;

import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.awt.print.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class ATM {

    private JTextArea receiptTextArea;
    private double bankAccountBalance;
    private final TransactionRepository transactionRepository;
    private final AffiliatedBankRepository affiliatedBankRepository;
    private final BankAccountRepository bankAccountRepository;

    public ATM(TransactionRepository transactionRepository, AffiliatedBankRepository affiliatedBankRepository, BankAccountRepository bankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.affiliatedBankRepository = affiliatedBankRepository;
        this.bankAccountRepository = bankAccountRepository;
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
                            System.out.println(dto);
                            if(dto != null){
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
                                        SwingUtilities.invokeLater(() -> {
                                            otpField.setText("");
                                            pinField.setText("");
                                            updateGUI(frame, confirmationPanel, mainPanel);
                                        });
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
                                        if(dto.getTransactionAmount() == Integer.parseInt(amountField.getText())){
                                            SwingWorker<Integer, Void> worker1 = new SwingWorker<Integer, Void>() {
                                                @Override
                                                protected Integer doInBackground() throws Exception {
                                                    return transactionRepository.updateTransactionStatus(dto.getTransactionID());
                                                }
                                                @Override
                                                protected void done() {
                                                    try{
                                                        int rowsAffected = get();
                                                        if(rowsAffected > 0){
                                                            getBankAccountBalance(dto.getBankAccountNumberID());

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
                                                                    SwingWorker<Double, Void> worker2 = new SwingWorker<Double, Void>(){
                                                                        @Override
                                                                        protected Double doInBackground() throws Exception {
                                                                            return affiliatedBankRepository.getBankCharge(dto.getTransactionBankID());
                                                                        }

                                                                        @Override
                                                                        protected void done() {
                                                                            try{
                                                                                LocalDate date = LocalDate.now();
                                                                                LocalTime time = LocalTime.now().withNano(0);

                                                                                double charge = get();
                                                                                double total = dto.getTransactionAmount() + charge;

                                                                                System.out.println(bankAccountBalance);
                                                                                writeToFileReceipt(dto, date, time, total, charge);

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
                                                                                                "----------------------------------------------------------------\n" +
                                                                                                "                           I.M BANK\n" +
                                                                                                "----------------------------------------------------------------\n" +
                                                                                                        "Davao City, 8000, Philippines\n\n" +
                                                                                                        "DATE               TIME           TRANSACTION NUMBER\n" +
                                                                                                        date + "    " + time + "     " + dto.getTransactionID() + "\n\n" +
                                                                                                "Card number: XXXX_XXXX_" + String.valueOf(dto.getCardNumber()).substring(8, 12) +"\n" +
                                                                                                "(Transaction type) from: " + dto.getTransactionType() + "\n" +
                                                                                                "Amount: " + dto.getTransactionAmount() + "\n" +
                                                                                                "Charge fee: " + charge + "\n" +
                                                                                                "Total: "+ total + "\n" +
                                                                                                "Account Balance: " + bankAccountBalance  +"\n\n" +
                                                                                                "*************************************************************\n" +
                                                                                                "PLEASE RETAIN OR DISPOSE OF THOUGHTFULLY.\n" +
                                                                                                "*************************************************************"
                                                                                );
                                                                                receiptTextArea.setEditable(false); // Make the text area read-only
                                                                                receiptTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
                                                                                receiptTextArea.setBounds(2, 0, 450, 400);
                                                                                receiptTextArea.setForeground(Color.black);
                                                                                receiptTextArea.setBackground(Color.white);
                                                                                receiptTextArea.setLineWrap(true);
                                                                                receiptTextArea.setWrapStyleWord(true);

                                                                                JScrollPane receiptScrollPane = new JScrollPane(receiptTextArea);
                                                                                receiptScrollPane.setBounds(2, 0, 450, 400);

                                                                                // Add the text area to the receipt panel
                                                                                receiptPanel.add(receiptScrollPane);

                                                                                JButton printButton = new JButton("Print Receipt");
                                                                                printButton.setForeground(new Color(0x223345));
                                                                                printButton.setFont(new Font("Arial", Font.BOLD, 20));
                                                                                printButton.setFocusable(false);
                                                                                printButton.setBounds(670, 350, 190, 40);
                                                                                printButton.addActionListener(new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed(ActionEvent e) {
                                                                                        PrinterJob printerJob = PrinterJob.getPrinterJob();
                                                                                        printerJob.setJobName("Receipt");

                                                                                        // Define the content to print
                                                                                        printerJob.setPrintable(new Printable() {
                                                                                            @Override
                                                                                            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                                                                                                if (pageIndex > 0) {
                                                                                                    return NO_SUCH_PAGE;
                                                                                                }
                                                                                                Graphics2D g2d = (Graphics2D) graphics;
                                                                                                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                                                                                                receiptPanel.paint(g2d);

                                                                                                return PAGE_EXISTS;
                                                                                            }
                                                                                        });

                                                                                        if (printerJob.printDialog()) {
                                                                                            try {
                                                                                                printerJob.print();
                                                                                            } catch (PrinterException ex) {
                                                                                                ViewUtility.showErrorMessage(null, "Printing failed");
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                });

                                                                                JButton backButton = new JButton("Back to Menu");
                                                                                backButton.setForeground(new Color(0x223345));
                                                                                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                                                                                backButton.setFocusable(false);
                                                                                backButton.setBounds(670, 400, 190, 40);
                                                                                backButton.addActionListener(new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed(ActionEvent e) {
                                                                                        otpField.setText("");
                                                                                        pinField.setText("");
                                                                                        updateGUI(frame, bgPanel, mainPanel);
                                                                                    }
                                                                                });

                                                                                // Add the buttons to the receipt panel
                                                                                bgPanel.add(printButton);
                                                                                bgPanel.add(backButton);
                                                                                bgPanel.add(receiptPanel);

                                                                                updateGUI(frame, confirmationPanel, bgPanel);
                                                                            } catch (Exception e){
                                                                               ViewUtility.showErrorMessage(null,"Something went wrong while retrieving bank charge.");
                                                                            }
                                                                        }
                                                                    };
                                                                    worker2.execute();
                                                                }
                                                            });

                                                            JButton noButton=new JButton("No");
                                                            noButton.setForeground(new Color(0x223345));
                                                            noButton.setFont(new Font("Arial", Font.BOLD, 20));
                                                            noButton.setFocusable(false);
                                                            noButton.setBounds(470, 340, 190, 40);
                                                            noButton.addActionListener(new ActionListener() {

                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {

                                                                    JLabel closingLabel2 = new JLabel("<html>Thank you for transacting with I.M Bank.<br> &nbsp &nbsp &nbsp &nbsp &nbsp "
                                                                            + "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Come Again!</html>");
                                                                    closingLabel2.setForeground(Color.white);
                                                                    closingLabel2.setFont(new Font("Arial", Font.BOLD, 30));
                                                                    closingLabel2.setBounds(150, 270, 700, 80);

                                                                    SwingUtilities.invokeLater(() -> {
                                                                        confirmationPanel.remove(closingLabel);
                                                                        confirmationPanel.remove(askReceiptLabel);
                                                                        confirmationPanel.remove(yesButton);
                                                                        confirmationPanel.remove(noButton);
                                                                        confirmationPanel.add(closingLabel2);
                                                                        confirmationPanel.revalidate();
                                                                        confirmationPanel.repaint();
                                                                    });

                                                                    Timer timer = new Timer(2500, new ActionListener(){
                                                                        @Override
                                                                        public void actionPerformed(ActionEvent e) {
                                                                            otpField.setText("");
                                                                            pinField.setText("");
                                                                            updateGUI(frame, confirmationPanel, mainPanel);
                                                                        }
                                                                    });
                                                                    timer.setRepeats(false);
                                                                    timer.start();
                                                                }
                                                            });

                                                            confirmationPanel.remove(amountLabel);
                                                            confirmationPanel.remove(amountField);
                                                            confirmationPanel.remove(backButton);
                                                            confirmationPanel.remove(proceedButton);
                                                            confirmationPanel.add(closingLabel);
                                                            confirmationPanel.add(askReceiptLabel);
                                                            confirmationPanel.add(yesButton);
                                                            confirmationPanel.add(noButton);
                                                            confirmationPanel.revalidate();
                                                            confirmationPanel.repaint();
                                                        } else {
                                                            throw new Exception("An Error Occurred");
                                                        }
                                                    } catch (Exception e){
                                                        ViewUtility.showErrorMessage(null, e.getMessage());
                                                    }
                                                }
                                            };
                                            worker1.execute();
                                        } else if(dto.getTransactionAmount() < Integer.parseInt(amountField.getText())
                                               || dto.getTransactionAmount() > Integer.parseInt(amountField.getText())) {
                                            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame, "Please enter the exact transaction amount"));
                                        }
                                    }
                                });

                                confirmationPanel.add(amountLabel);
                                confirmationPanel.add(amountField);
                                confirmationPanel.add(backButton);
                                confirmationPanel.add(proceedButton);

                                updateGUI(frame, mainPanel, confirmationPanel);

                            } else {
                                throw new Exception("Invalid OTP or Card PIn");
                            }
                        } catch (Exception e){
                            ViewUtility.showInfoMessage(e.getMessage());
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
        String sql = "SELECT t.Transaction_ID, ci.Card_Number, t.Transaction_Type, t.Amount, t.Bank_ID, ba.Bank_Account_Balance, ba.Bank_Account_Number_ID "
                   + "FROM Transaction t "
                   + "JOIN Bank_Accounts ba ON t.Bank_Account_Number_ID = ba.Bank_Account_Number_ID "
                   + "JOIN Customers c ON ba.Customer_ID = c.Customer_ID "
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
                        rs.getInt("Transaction_ID"),
                        rs.getLong("Card_Number"),
                        rs.getString("Transaction_Type"),
                        rs.getDouble("Amount"),
                        rs.getInt("Bank_ID"),
                        rs.getDouble("Bank_Account_Balance"),
                        rs.getInt("Bank_Account_Number_ID")
                );
            }
            return information;
        }
    }

    private void getBankAccountBalance(int bankAccountNumberID){
        SwingWorker<Float, Void> worker = new SwingWorker<>() {
            @Override
            protected Float doInBackground() throws Exception {
                return bankAccountRepository.getBankAccountBalance(bankAccountNumberID);
            }
            @Override
            protected void done() {
                try {
                    bankAccountBalance = get();
                } catch (Exception e){
                    ViewUtility.showErrorMessage(null, e.getMessage());
                }
            }
        };
        worker.execute();   
    }

    private void updateGUI(Container container, Component component1, Component component2){
        SwingUtilities.invokeLater(() -> {
            container.remove(component1);
            container.add(component2);
            container.revalidate();
            container.repaint();
        });
    }

    private void writeToFileReceipt(ATM_DTO atm_dto, LocalDate date, LocalTime time, double total, double charge) {
        String fileName = String.format("%s_%sReceipt", time.toString().replace(":", "-"), String.valueOf(date));
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"))){
            // Header Section
            writer.write("-------------------------------------------------\n");
            writer.write(String.format("%-45s\n", "                   I.M Bank"));
            writer.write("-------------------------------------------------\n");

            // Transaction Details
            writer.write(String.format("%-20s: %s\n", "DATE", date));
            writer.write(String.format("%-20s: %s\n", "TIME", time));
            writer.write(String.format("%-20s: %s\n", "TRANSACTION ID NUMBER", String.valueOf(atm_dto.getTransactionID())));
            writer.write("\n");

            // Location and Card Info
            writer.write(String.format("%-45s\n", "Davao City, 8000, Philippines"));
            writer.write(String.format("%-20s: %s\n", "Card number", "XXXX-XXXX-" + String.valueOf(atm_dto.getCardNumber()).substring(8, 12)));
            writer.write(String.format("%-20s: %s\n", "Transaction type", atm_dto.getTransactionType()));
            writer.write("\n");

            // Amount Details
            writer.write(String.format("%-20s: %s\n", "Amount", String.valueOf(atm_dto.getTransactionAmount())));
            writer.write(String.format("%-20s: %s\n", "Charge fee", String.valueOf(charge)));
            writer.write(String.format("%-20s: %s\n", "Total", String.valueOf(total)));
            writer.write(String.format("%-20s: %s\n", "Remaining Account Balance", String.valueOf(bankAccountBalance)));
            writer.write("\n");

            // Footer Section
            writer.write("***********************************************\n");
            writer.write(" PLEASE RETAIN OR DISPOSE OF THOUGHTFULLY.     \n");
            writer.write("***********************************************\n");
        } catch (IOException e){
            ViewUtility.showErrorMessage(null, "Error writing receipt file");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM(
                new TransactionRepository(),
                new AffiliatedBankRepository(),
                new BankAccountRepository()));
    }
}
