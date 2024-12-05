package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {

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
        

        JLabel pinLabel = new JLabel("Enter your 4-digit PIN:");
        pinLabel.setForeground(Color.white);
        pinLabel.setFont(new Font("Arial", Font.BOLD, 30));
        pinLabel.setBounds(290, 280, 500, 50);

        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(360, 340, 150, 50);
        pinField.setForeground(new Color(0x223345));
        pinField.setFont(new Font("Arial", Font.BOLD, 30));
        pinField.setHorizontalAlignment(JPasswordField.CENTER);

        JButton enterButton = new JButton("Enter");
        enterButton.setForeground(new Color(0x223345));
        enterButton.setFont(new Font("Arial", Font.BOLD, 30));
        enterButton.setFocusable(false);
        enterButton.setBounds(340, 480, 190, 40);
        enterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);

                JPanel confirmationPanel = new JPanel();
                confirmationPanel.setLayout(null);
                confirmationPanel.setBackground(new Color(0x223345));
                confirmationPanel.setBounds(0, 100, 900, 500);

                JLabel withdrawalAmountLabel = new JLabel("<html> Confirm your (transaction type) <br>amount (In Php):</html>");
                withdrawalAmountLabel.setForeground(Color.white);
                withdrawalAmountLabel.setFont(new Font("Arial", Font.BOLD, 30));
                withdrawalAmountLabel.setBounds(210, 150, 500, 110);

                JTextField withdrawalAmountField = new JTextField();
                withdrawalAmountField.setBounds(300, 280, 300, 50);
                withdrawalAmountField.setForeground(new Color(0x223345));
                withdrawalAmountField.setFont(new Font("Arial", Font.BOLD, 30));

                JButton backButton = new JButton("Back");
                backButton.setForeground(new Color(0x223345));
                backButton.setFont(new Font("Arial", Font.BOLD, 20));
                backButton.setFocusable(false);
                backButton.setBounds(50, 480, 190, 40);
                backButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
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
                        confirmationPanel.remove(withdrawalAmountLabel);
                        confirmationPanel.remove(withdrawalAmountField);
                        confirmationPanel.remove(backButton);
                        confirmationPanel.remove(proceedButton);

                        JLabel closingLabel = new JLabel("Thank you for transacting!");
                        closingLabel.setForeground(Color.white);
                        closingLabel.setFont(new Font("Arial", Font.BOLD, 30));
                        closingLabel.setBounds(250, 160, 700, 80);

                        JLabel askReceiptLabel=new JLabel("Would you like to print your receipt?");
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

                                JPanel bgPanel=new JPanel();
                                bgPanel.setLayout(null);
                                bgPanel.setBackground(new Color(0x223345));
                                bgPanel.setBounds(0, 100, 900, 500);

                                JPanel receiptPanel=new JPanel();
                                receiptPanel.setLayout(null);
                                receiptPanel.setBackground(Color.white);
                                receiptPanel.setBounds(200, 130, 450, 400);

                                JLabel logoLabel=new JLabel("I.M Bank");
                                logoLabel.setForeground(new Color(0x23234D));
                                logoLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
                                logoLabel.setBounds(165,10, 150, 40);

                                JLabel brokenLineLabel=new JLabel("-------------------------------------------------");
                                brokenLineLabel.setForeground(Color.black);
                                brokenLineLabel.setFont(new Font("Arial", Font.PLAIN, 25));
                                brokenLineLabel.setBounds(10,40, 450, 30);

                                JLabel dateLabel=new JLabel("DATE");
                                dateLabel.setForeground(Color.black);
                                dateLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                dateLabel.setBounds(20,70, 60, 20);
                                JLabel timeLabel=new JLabel("TIME");
                                timeLabel.setForeground(Color.black);
                                timeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                timeLabel.setBounds(120,70, 60, 20);
                                JLabel transactionNumLabel=new JLabel("<html>TRANSACTION<br>NUMBER</html>");
                                transactionNumLabel.setForeground(Color.black);
                                transactionNumLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                transactionNumLabel.setBounds(210,70, 450, 40);
                                JLabel atmIdLabel=new JLabel("ATM ID");
                                atmIdLabel.setForeground(Color.black);
                                atmIdLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                atmIdLabel.setBounds(370,70, 80, 20);
                                JLabel addressLabel=new JLabel("Davao City, 8000, Philippines");
                                addressLabel.setForeground(Color.black);
                                addressLabel.setFont(new Font("Arial", Font.BOLD, 15));
                                addressLabel.setBounds(20,145, 225, 20);
                                JLabel cardNumLabel=new JLabel("Card number:");
                                cardNumLabel.setForeground(Color.black);
                                cardNumLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                cardNumLabel.setBounds(20,170, 100, 20);
                                JLabel accTypeLabel=new JLabel("(transaction type) from: ");
                                accTypeLabel.setForeground(Color.black);
                                accTypeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                accTypeLabel.setBounds(20,195, 200, 20);
                                JLabel amountLabel=new JLabel("Amount:");
                                amountLabel.setForeground(Color.black);
                                amountLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                amountLabel.setBounds(20,220, 80, 20);
                                JLabel chargeFeeLabel=new JLabel("Charge fee:");
                                chargeFeeLabel.setForeground(Color.black);
                                chargeFeeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                chargeFeeLabel.setBounds(20,245, 90, 20);
                                JLabel totalLabel=new JLabel("Total:");
                                totalLabel.setForeground(Color.black);
                                totalLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                totalLabel.setBounds(20,270, 80, 20);
                                JLabel accBalanceLabel=new JLabel("Account Balance: ");
                                accBalanceLabel.setForeground(Color.black);
                                accBalanceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                accBalanceLabel.setBounds(20,295, 130, 20);
                                JLabel asteriskLabel1=new JLabel("******************************************");
                                asteriskLabel1.setForeground(Color.black);
                                asteriskLabel1.setFont(new Font("Arial", Font.PLAIN, 25));
                                asteriskLabel1.setBounds(10,320, 420, 30);
                                JLabel disposeLabel=new JLabel("PLEASE RETAIN OR DISPOSE OF THOUGHTFULLY.");
                                disposeLabel.setForeground(Color.black);
                                disposeLabel.setFont(new Font("Arial", Font.BOLD, 15));
                                disposeLabel.setBounds(30,340, 390, 20);
                                JLabel asteriskLabel2=new JLabel("******************************************");
                                asteriskLabel2.setForeground(Color.black);
                                asteriskLabel2.setFont(new Font("Arial", Font.PLAIN, 25));
                                asteriskLabel2.setBounds(10,365, 420, 30);

                                receiptPanel.add(logoLabel);
                                receiptPanel.add(brokenLineLabel);
                                receiptPanel.add(dateLabel);
                                receiptPanel.add(timeLabel);
                                receiptPanel.add(transactionNumLabel);
                                receiptPanel.add(atmIdLabel);
                                receiptPanel.add(addressLabel);
                                receiptPanel.add(cardNumLabel);
                                receiptPanel.add(accTypeLabel);
                                receiptPanel.add(amountLabel);
                                receiptPanel.add(chargeFeeLabel);
                                receiptPanel.add(totalLabel);
                                receiptPanel.add(accBalanceLabel);
                                receiptPanel.add(asteriskLabel1);
                                receiptPanel.add(disposeLabel);
                                receiptPanel.add(asteriskLabel2);

                                bgPanel.add(receiptPanel);

                                frame.add(bgPanel);
                                frame.revalidate();
                                frame.repaint();

                                Timer timer = new Timer(2500, new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frame.remove(bgPanel);
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
                    }
                });

                confirmationPanel.add(withdrawalAmountLabel);
                confirmationPanel.add(withdrawalAmountField);
                confirmationPanel.add(backButton);
                confirmationPanel.add(proceedButton);

                frame.add(confirmationPanel);
                frame.revalidate();
                frame.repaint();
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
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATM::new);
    }
}
