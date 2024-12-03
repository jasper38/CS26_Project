package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {

    public ATM() {
        JFrame frame = new JFrame("ATM Machine");
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        JPanel logopanel = new JPanel();
        logopanel.setBackground(Color.white);
        logopanel.setLayout(null);
        logopanel.setBounds(0, 0, 900, 100);

        JLabel IMBanklabel = new JLabel("I.M Bank");
        IMBanklabel.setForeground(new Color(0x23234D));
        IMBanklabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        IMBanklabel.setBounds(20, 25, 180, 40);

        JLabel reminderlabel = new JLabel("<html> You are currently transacting through <br>a cardless method</html>");
        reminderlabel.setForeground(new Color(0x23234D));
        reminderlabel.setFont(new Font("Arial", Font.PLAIN, 16));
        reminderlabel.setBounds(700, 15, 180, 70);

        logopanel.add(IMBanklabel);
        logopanel.add(reminderlabel);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(null);
        mainpanel.setBackground(new Color(0x223345));
        mainpanel.setBounds(0, 100, 900, 500);

        JLabel otplabel = new JLabel("Enter your OTP:");
        otplabel.setForeground(Color.white);
        otplabel.setFont(new Font("Arial", Font.BOLD, 30));
        otplabel.setBounds(330, 130, 300, 50);

        JTextField otpfield = new JTextField();
        otpfield.setBounds(360, 190, 150, 50);
        otpfield.setForeground(new Color(0x223345));
        otpfield.setFont(new Font("Arial", Font.BOLD, 30));
        otpfield.setHorizontalAlignment(JTextField.CENTER);

        JLabel pinlabel = new JLabel("Enter your 4-digit PIN:");
        pinlabel.setForeground(Color.white);
        pinlabel.setFont(new Font("Arial", Font.BOLD, 30));
        pinlabel.setBounds(290, 280, 500, 50);

        JPasswordField pinfield = new JPasswordField();
        pinfield.setBounds(360, 340, 150, 50);
        pinfield.setForeground(new Color(0x223345));
        pinfield.setFont(new Font("Arial", Font.BOLD, 30));
        pinfield.setHorizontalAlignment(JPasswordField.CENTER);

        JButton enterbutton = new JButton("Enter");
        enterbutton.setForeground(new Color(0x223345));
        enterbutton.setFont(new Font("Arial", Font.BOLD, 30));
        enterbutton.setFocusable(false);
        enterbutton.setBounds(340, 480, 190, 40);
        enterbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainpanel);

                JPanel confirmationpanel = new JPanel();
                confirmationpanel.setLayout(null);
                confirmationpanel.setBackground(new Color(0x223345));
                confirmationpanel.setBounds(0, 100, 900, 500);

                JLabel withdrawalamountlabel = new JLabel("<html> Confirm your (transaction type) <br>amount (In Php):</html>");
                withdrawalamountlabel.setForeground(Color.white);
                withdrawalamountlabel.setFont(new Font("Arial", Font.BOLD, 30));
                withdrawalamountlabel.setBounds(210, 150, 500, 110);

                JTextField withdrawalamountfield = new JTextField();
                withdrawalamountfield.setBounds(300, 280, 300, 50);
                withdrawalamountfield.setForeground(new Color(0x223345));
                withdrawalamountfield.setFont(new Font("Arial", Font.BOLD, 30));

                JButton backbutton = new JButton("Back");
                backbutton.setForeground(new Color(0x223345));
                backbutton.setFont(new Font("Arial", Font.BOLD, 20));
                backbutton.setFocusable(false);
                backbutton.setBounds(50, 480, 190, 40);
                backbutton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.remove(confirmationpanel);
                        frame.add(mainpanel);
                        frame.revalidate();
                        frame.repaint();
                    }
                });

                JButton proceedbutton = new JButton("Proceed");
                proceedbutton.setForeground(new Color(0x223345));
                proceedbutton.setFont(new Font("Arial", Font.BOLD, 20));
                proceedbutton.setFocusable(false);
                proceedbutton.setBounds(650, 480, 190, 40);
                proceedbutton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmationpanel.remove(withdrawalamountlabel);
                        confirmationpanel.remove(withdrawalamountfield);
                        confirmationpanel.remove(backbutton);
                        confirmationpanel.remove(proceedbutton);

                        JLabel closinglabel = new JLabel("Thank you for transacting!");
                        closinglabel.setForeground(Color.white);
                        closinglabel.setFont(new Font("Arial", Font.BOLD, 30));
                        closinglabel.setBounds(250, 160, 700, 80);

                        JLabel askreceiptlabel=new JLabel("Would you like to print your receipt?");
                        askreceiptlabel.setForeground(Color.white);
                        askreceiptlabel.setFont(new Font("Arial", Font.BOLD, 30));
                        askreceiptlabel.setBounds(180, 200, 700, 80);

                        JButton yesbutton=new JButton("Yes");
                        yesbutton.setForeground(new Color(0x223345));
                        yesbutton.setFont(new Font("Arial", Font.BOLD, 20));
                        yesbutton.setFocusable(false);
                        yesbutton.setBounds(210, 340, 190, 40);
                        yesbutton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {

                                frame.remove(confirmationpanel);

                                JPanel bgpanel=new JPanel();
                                bgpanel.setLayout(null);
                                bgpanel.setBackground(new Color(0x223345));
                                bgpanel.setBounds(0, 100, 900, 500);

                                JPanel receiptpanel=new JPanel();
                                receiptpanel.setLayout(null);
                                receiptpanel.setBackground(Color.white);
                                receiptpanel.setBounds(200, 130, 450, 400);

                                JLabel logolabel=new JLabel("I.M Bank");
                                logolabel.setForeground(new Color(0x23234D));
                                logolabel.setFont(new Font("Helvetica", Font.BOLD, 30));
                                logolabel.setBounds(165,10, 150, 40);

                                JLabel brokenlinelabel=new JLabel("-------------------------------------------------");
                                brokenlinelabel.setForeground(Color.black);
                                brokenlinelabel.setFont(new Font("Arial", Font.PLAIN, 25));
                                brokenlinelabel.setBounds(10,40, 450, 30);

                                JLabel datelabel=new JLabel("DATE");
                                datelabel.setForeground(Color.black);
                                datelabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                datelabel.setBounds(20,70, 60, 20);
                                JLabel timelabel=new JLabel("TIME");
                                timelabel.setForeground(Color.black);
                                timelabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                timelabel.setBounds(120,70, 60, 20);
                                JLabel transactionnumlabel=new JLabel("<html>TRANSACTION<br>NUMBER</html>");
                                transactionnumlabel.setForeground(Color.black);
                                transactionnumlabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                transactionnumlabel.setBounds(210,70, 450, 40);
                                JLabel atmidlabel=new JLabel("ATM ID");
                                atmidlabel.setForeground(Color.black);
                                atmidlabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                atmidlabel.setBounds(370,70, 80, 20);
                                JLabel addresslabel=new JLabel("Davao City, 8000, Philippines");
                                addresslabel.setForeground(Color.black);
                                addresslabel.setFont(new Font("Arial", Font.BOLD, 15));
                                addresslabel.setBounds(20,145, 225, 20);
                                JLabel cardnumlabel=new JLabel("Card number:");
                                cardnumlabel.setForeground(Color.black);
                                cardnumlabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                cardnumlabel.setBounds(20,170, 100, 20);
                                JLabel acctypelabel=new JLabel("(transaction type) from: ");
                                acctypelabel.setForeground(Color.black);
                                acctypelabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                acctypelabel.setBounds(20,195, 200, 20);
                                JLabel amountlabel=new JLabel("Amount:");
                                amountlabel.setForeground(Color.black);
                                amountlabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                amountlabel.setBounds(20,220, 80, 20);
                                JLabel chargefeelabel=new JLabel("Charge fee:");
                                chargefeelabel.setForeground(Color.black);
                                chargefeelabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                chargefeelabel.setBounds(20,245, 90, 20);
                                JLabel totallabel=new JLabel("Total:");
                                totallabel.setForeground(Color.black);
                                totallabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                totallabel.setBounds(20,270, 80, 20);
                                JLabel accbalancelabel=new JLabel("Account Balance: ");
                                accbalancelabel.setForeground(Color.black);
                                accbalancelabel.setFont(new Font("Arial", Font.PLAIN, 15));
                                accbalancelabel.setBounds(20,295, 130, 20);
                                JLabel asterisklabel1=new JLabel("******************************************");
                                asterisklabel1.setForeground(Color.black);
                                asterisklabel1.setFont(new Font("Arial", Font.PLAIN, 25));
                                asterisklabel1.setBounds(10,320, 420, 30);
                                JLabel disposelabel=new JLabel("PLEASE RETAIN OR DISPOSE OF THOUGHTFULLY.");
                                disposelabel.setForeground(Color.black);
                                disposelabel.setFont(new Font("Arial", Font.BOLD, 15));
                                disposelabel.setBounds(30,340, 390, 20);
                                JLabel asterisklabel2=new JLabel("******************************************");
                                asterisklabel2.setForeground(Color.black);
                                asterisklabel2.setFont(new Font("Arial", Font.PLAIN, 25));
                                asterisklabel2.setBounds(10,365, 420, 30);

                                receiptpanel.add(logolabel);
                                receiptpanel.add(brokenlinelabel);
                                receiptpanel.add(datelabel);
                                receiptpanel.add(timelabel);
                                receiptpanel.add(transactionnumlabel);
                                receiptpanel.add(atmidlabel);
                                receiptpanel.add(addresslabel);
                                receiptpanel.add(cardnumlabel);
                                receiptpanel.add(acctypelabel);
                                receiptpanel.add(amountlabel);
                                receiptpanel.add(chargefeelabel);
                                receiptpanel.add(totallabel);
                                receiptpanel.add(accbalancelabel);
                                receiptpanel.add(asterisklabel1);
                                receiptpanel.add(disposelabel);
                                receiptpanel.add(asterisklabel2);

                                bgpanel.add(receiptpanel);

                                frame.add(bgpanel);
                                frame.revalidate();
                                frame.repaint();

                                Timer timer = new Timer(2500, new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frame.remove(bgpanel);
                                        otpfield.setText("");
                                        pinfield.setText("");
                                        frame.add(mainpanel);
                                        frame.revalidate();
                                        frame.repaint();

                                    }
                                });
                                timer.setRepeats(false);
                                timer.start();
                            }
                        });

                        JButton nobutton=new JButton("No");
                        nobutton.setForeground(new Color(0x223345));
                        nobutton.setFont(new Font("Arial", Font.BOLD, 20));
                        nobutton.setFocusable(false);
                        nobutton.setBounds(470, 340, 190, 40);
                        nobutton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {

                                confirmationpanel.remove(closinglabel);
                                confirmationpanel.remove(askreceiptlabel);
                                confirmationpanel.remove(yesbutton);
                                confirmationpanel.remove(nobutton);

                                JLabel closinglabel2 = new JLabel("<html>Thank you for transacting with I.M Bank.<br> &nbsp &nbsp &nbsp &nbsp &nbsp "
                                        + "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Come Again!</html>");
                                closinglabel2.setForeground(Color.white);
                                closinglabel2.setFont(new Font("Arial", Font.BOLD, 30));
                                closinglabel2.setBounds(150, 270, 700, 80);

                                confirmationpanel.add(closinglabel2);

                                frame.revalidate();
                                frame.repaint();

                                Timer timer = new Timer(2500, new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frame.remove(confirmationpanel);
                                        otpfield.setText("");
                                        pinfield.setText("");
                                        frame.add(mainpanel);
                                        frame.revalidate();
                                        frame.repaint();

                                    }
                                });
                                timer.setRepeats(false);
                                timer.start();
                            }
                        });

                        confirmationpanel.add(closinglabel);
                        confirmationpanel.add(askreceiptlabel);
                        confirmationpanel.add(yesbutton);
                        confirmationpanel.add(nobutton);

                        frame.revalidate();
                        frame.repaint();
                    }
                });

                confirmationpanel.add(withdrawalamountlabel);
                confirmationpanel.add(withdrawalamountfield);
                confirmationpanel.add(backbutton);
                confirmationpanel.add(proceedbutton);

                frame.add(confirmationpanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        mainpanel.add(otplabel);
        mainpanel.add(otpfield);
        mainpanel.add(pinlabel);
        mainpanel.add(pinfield);
        mainpanel.add(enterbutton);

        frame.add(logopanel);
        frame.add(mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
    }
}
