package View;

import Controller.IMBankController;
import DTO.TransactionHistoryDTO;
import Model.Transaction;
import Utility.ViewUtility;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class MainWindow {

    private JFrame mainFrame;

    // Header panel and its components
    private JPanel headerPanel;
    private JLabel headerLbl;
    private JPanel bannerPanel;
    private JLabel bannerLbl;

    // Left Side Navigation Bar Panel and its components
    private JPanel navPanel;
    private JButton homeBtn;
    private JButton transactionHistoryBtn;
    private JButton profileBtn;
    private JButton logoutBtn;

    // Home panel and its components here
    private JPanel homePanel;
    private JLabel balanceLbl;
    private JTextField displayBalanceField;
    private JButton depositBtn;
    private JButton withdrawBtn;


    // Transaction history panel and its components
    private JPanel transactionHistoryPanel;
    private String[] columnNames;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;

    //Profile panel and its components
    private JPanel profilePanel;

    // Main window components
    private JSeparator horizontalSeparator;
    private JSeparator verticalSeparator;

    //dimensions
    private int frameheight = 800;
    private int framewidth = 1200;

    private JPanel[] panels = new JPanel[3];
    private final IMBankController bankController;

    public MainWindow(IMBankController bankController) {
        this.bankController = bankController;
        initMainWindowComponents();
    }

    private void initMainWindowComponents() {
        mainFrame = ViewFactory.createFrame("IM Bank: Main", framewidth, frameheight);

        initHeaderPanel();
        initNavPanel();

        bannerPanel = new JPanel();
        bannerPanel.setLayout(null);
        bannerPanel.setBounds(0,0,framewidth,90);
        bannerPanel.setBackground(new Color(35, 35, 77));

        bannerLbl = new JLabel();
        bannerLbl.setText("IMBANK");
        bannerLbl.setBounds(500,10,300,60);
        bannerLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 55));
        bannerLbl.setForeground(Color.WHITE);
        bannerPanel.add(bannerLbl);

        horizontalSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        horizontalSeparator.setBounds(0, 80, 800, 2);
        horizontalSeparator.setForeground(Color.BLACK);

        verticalSeparator = new JSeparator(SwingConstants.VERTICAL);
        verticalSeparator.setBounds(200, 80, 2 , 498);
        verticalSeparator.setForeground(Color.BLACK);

        initHomePanelAndComponents();
        initTransactionHistoryPanel();
        initProfilePanel();


        mainFrame.getContentPane().add(bannerPanel);
        //mainFrame.getContentPane().add(headerPanel);
        mainFrame.getContentPane().add(navPanel);
        mainFrame.getContentPane().add(homePanel);
        mainFrame.getContentPane().add(transactionHistoryPanel);
        mainFrame.getContentPane().add(profilePanel);
        mainFrame.getContentPane().add(horizontalSeparator);
        mainFrame.getContentPane().add(verticalSeparator);

        mainFrame.setResizable(false);
    }

    private void initHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 900, 80);
        //headerPanel.setBackground(Color.CYAN);

        headerLbl = new JLabel("Welcome [Name of Person],");
        headerLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 28));
        headerLbl.setForeground(new java.awt.Color(35, 35, 77));
        headerLbl.setBounds(30, 30, 400, 50);

        headerPanel.add(headerLbl);
    }


    private void initNavPanel() {
        navPanel = new JPanel();
        navPanel.setLayout(null);
        navPanel.setBounds(0, 82, 300, frameheight);
        navPanel.setBackground(Color.PINK);

        homeBtn = new JButton("Home");
        homeBtn.setFocusable(false);
        homeBtn.setBounds(0, 10, 300, 70); //+50
        homeBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 30));
        homeBtn.setForeground(new java.awt.Color(224, 224, 231));
        homeBtn.setBackground(new java.awt.Color(35, 35, 77));
        homeBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        homeBtn.setBorderPainted(false);
        homeBtn.addActionListener(this::homeBtnActionPerformed);

        transactionHistoryBtn = new JButton("Transaction History");
        transactionHistoryBtn.setFocusable(false);
        transactionHistoryBtn.setBounds(0, 83, 300, 70);
        transactionHistoryBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 30));
        transactionHistoryBtn.setForeground(new java.awt.Color(224, 224, 231));
        transactionHistoryBtn.setBackground(new java.awt.Color(35, 35, 77));
        transactionHistoryBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        transactionHistoryBtn.setBorderPainted(false);
        transactionHistoryBtn.addActionListener(this::transactionHistoryBtnActionPerformed);

        profileBtn = new JButton("Profile");
        profileBtn.setFocusable(false);
        profileBtn.setBounds(0, 156, 300, 70);
        profileBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 30));
        profileBtn.setForeground(new java.awt.Color(224, 224, 231));
        profileBtn.setBackground(new java.awt.Color(35, 35, 77));
        profileBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        profileBtn.setBorderPainted(false);
        profileBtn.addActionListener(this::profileBtnActionPerformed);

        logoutBtn = new JButton("Logout");
        logoutBtn.setFocusable(false);
        logoutBtn.setBounds(0, 570, 300, 70);
        logoutBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 30));
        logoutBtn.setForeground(new java.awt.Color(224, 224, 231));
        logoutBtn.setBackground(new java.awt.Color(35, 35, 77));
        logoutBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(this::logoutBtnActionPerformed);

        navPanel.add(homeBtn);
        navPanel.add(transactionHistoryBtn);
        navPanel.add(profileBtn);
        navPanel.add(logoutBtn);
    }

    private void initHomePanelAndComponents() {
        homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setBounds(300, 80, 900, 1110);

        //homePanel.setBackground(Color.GREEN);

        balanceLbl = new JLabel("Current Account Balance:");
        balanceLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 35));
        balanceLbl.setBounds(240, 140, 450, 40);

        displayBalanceField = new JTextField();
        displayBalanceField.setEditable(false);
        displayBalanceField.setFont(new java.awt.Font("MS UI Gothic", 1, 24));
        displayBalanceField.setBounds(330, 200, 230, 45);

        JLabel choiceLbl = new JLabel("Select a transaction type below:");
        choiceLbl.setFont(new Font("MS UI Gothic", 1, 35));
        choiceLbl.setForeground(new java.awt.Color(35, 35, 77));
        choiceLbl.setBounds(180,320,550,40);

        depositBtn = new JButton("Deposit");
        depositBtn.setFocusable(false);
        depositBtn.setBounds(200, 400, 200, 45);
        depositBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 30));
        depositBtn.setForeground(new java.awt.Color(224, 224, 231));
        depositBtn.setBackground(new java.awt.Color(35, 35, 77));
        //depositBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        depositBtn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        depositBtn.setBorderPainted(false);
        depositBtn.addActionListener(this::depositBtnActionPerformed);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setFocusable(false);
        withdrawBtn.setBounds(430, 400, 200, 45);
        withdrawBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 30));
        withdrawBtn.setForeground(new java.awt.Color(224, 224, 231));
        withdrawBtn.setBackground(new java.awt.Color(35, 35, 77));
        withdrawBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        withdrawBtn.setBorderPainted(false);
        withdrawBtn.addActionListener(this::withdrawBtnActionPerformed);


        homePanel.add(headerPanel);
        homePanel.add(choiceLbl);
        homePanel.add(balanceLbl);
        homePanel.add(displayBalanceField);
        homePanel.add(depositBtn);
        homePanel.add(withdrawBtn);

        panels[0] = homePanel;

        ViewUtility.setEnabledPanelAndComponents(panels[0], true);
    }

    private void initTransactionHistoryPanel() {
        transactionHistoryPanel = new JPanel();
        transactionHistoryPanel.setLayout(null);
        transactionHistoryPanel.setBounds(300, 80, 900, 810);
        transactionHistoryPanel.setBackground(Color.GREEN);

        columnNames = new String[]{
                "<html>Transaction<br>ID</html>",
                "<html>Bank Account<br>Number ID</html>",
                "<html>Bank<br>Name</html>",
                "<html>Transaction<br>Type</html>",
                "<html>Amount</html>",
                "<html>Transaction<br>DateTime</html>",
                "<html>Request<br>Status</html>",
                "<html>OTP</html>"
        };

        JLabel transactionHistoryLbl = new JLabel("Transaction History");
        transactionHistoryLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 35));
        transactionHistoryLbl.setForeground(new java.awt.Color(35, 35, 77));
        transactionHistoryLbl.setBounds(310,20,400,50);

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 80, 870, 498);
        scrollPane.setBorder(null);

        JTableHeader header = table.getTableHeader();
        header.setFont(new java.awt.Font("MS UI Gothic", 1, 18));
        header.setBackground(new Color(35, 35, 77));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        header.setBorder(null);
        //header.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        table.setFont(new java.awt.Font("MS UI Gothic", 1, 18));
        table.setRowHeight(30);
        table.setBackground(new Color(240, 240, 255));
        table.setForeground(new Color(35, 35, 77));
        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setSelectionForeground(Color.BLACK);


        transactionHistoryPanel.add(transactionHistoryLbl);
        transactionHistoryPanel.add(scrollPane);

        panels[1] = transactionHistoryPanel;

        ViewUtility.setEnabledPanelAndComponents(panels[1], false);
    }

    private void initProfilePanel() {

        profilePanel = new JPanel();
        profilePanel.setLayout(null);
        profilePanel.setBackground(Color.MAGENTA);

    }

    // Pop-up window for transaction
    private JFrame popUpFrame1;
    private JLabel bankAccountNumLbl;
    private JLabel cardPINLbl;
    private JTextField bankAccountNumField;
    private JTextField cardPINField;
    private JButton cancelBtn;
    private JButton enterBtn;

    private void createPopUpWindow1() {
        popUpFrame1 = new JFrame("Initiating Transaction Request");
        popUpFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUpFrame1.setLayout(null);
        popUpFrame1.setSize(240, 300);
        popUpFrame1.setLocationRelativeTo(mainFrame);

        bankAccountNumLbl = new JLabel("Enter Bank Account Number:");
        bankAccountNumLbl.setBounds(10, 10, 200, 30);

        cardPINLbl = new JLabel("Enter Card PIN");
        cardPINLbl.setBounds(10, 70, 200, 30);

        bankAccountNumField = new JTextField();
        bankAccountNumField.setBounds(10, 45, 200, 25);
        bankAccountNumField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        cardPINField = new JTextField();
        cardPINField.setBounds(10, 105, 200, 25);
        cardPINField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        cancelBtn = new JButton("Cancel");
        cancelBtn.setFocusable(false);
        cancelBtn.setBounds(10, 140, 100, 25);
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);

        enterBtn = new JButton("Enter");
        enterBtn.setFocusable(false);
        enterBtn.setBounds(110, 140, 100, 25);
        enterBtn.addActionListener(this::enterBtnActionPerformed);

        popUpFrame1.add(bankAccountNumLbl);
        popUpFrame1.add(cardPINLbl);
        popUpFrame1.add(bankAccountNumField);
        popUpFrame1.add(cardPINField);
        popUpFrame1.add(cancelBtn);
        popUpFrame1.add(enterBtn);

        popUpFrame1.setResizable(false);
        popUpFrame1.setVisible(true);
    }

    private JFrame popUpFrame2;
    private JToggleButton bank1Btn;
    private JToggleButton bank2Btn;
    private JToggleButton bank3Btn;
    private JToggleButton bank4Btn;
    private JToggleButton bank5Btn;
    private JToggleButton bank6Btn;
    private ButtonGroup bankGroup;
    private JButton submitBtn;
    private JLabel chooseLbl;
    private JLabel amountLbl;
    private JTextField amountField;
    private String selectedBank;

    public void createPopUpWindow2() {
        popUpFrame2 = new JFrame("Ongoing Transaction...");
        popUpFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUpFrame2.setLayout(null);
        popUpFrame2.setSize(314, 300);
        popUpFrame2.setLocationRelativeTo(mainFrame);

        chooseLbl = new JLabel("Choose Bank:");
        chooseLbl.setBounds(0, 0, 100, 30);

        bank1Btn = ViewFactory.createToggleButton(popUpFrame2 ,"IMBank", 0, 40);
        bank2Btn = ViewFactory.createToggleButton(popUpFrame2 ,"BDO", 100, 40);
        bank3Btn = ViewFactory.createToggleButton(popUpFrame2 ,"LandBank", 200, 40);
        bank4Btn = ViewFactory.createToggleButton(popUpFrame2 ,"MetroBank", 0, 70);
        bank5Btn = ViewFactory.createToggleButton(popUpFrame2 ,"BPI", 100, 70);
        bank6Btn = ViewFactory.createToggleButton(popUpFrame2 ,"RCBC", 200, 70);

        bankGroup = new ButtonGroup();
        bankGroup.add(bank1Btn);
        bankGroup.add(bank2Btn);
        bankGroup.add(bank3Btn);
        bankGroup.add(bank4Btn);
        bankGroup.add(bank5Btn);
        bankGroup.add(bank6Btn);

        ActionListener toggleListener = e ->{
            selectedBank = getSelectedBank((JToggleButton) e.getSource());
        };

        bank1Btn.addActionListener(toggleListener);
        bank2Btn.addActionListener(toggleListener);
        bank3Btn.addActionListener(toggleListener);
        bank4Btn.addActionListener(toggleListener);
        bank5Btn.addActionListener(toggleListener);
        bank6Btn.addActionListener(toggleListener);

        amountLbl = new JLabel("Enter Amount:");
        amountLbl.setBounds(0, 100, 100, 30);

        amountField = new JTextField();
        amountField.setBounds(0, 130, 120, 25);
        amountField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        cancelBtn = new JButton("Cancel");
        cancelBtn.setFocusable(false);
        cancelBtn.setBounds(10, 190, 100, 25);
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);

        submitBtn = new JButton("Submit");
        submitBtn.setFocusable(false);
        submitBtn.setBounds(110, 190, 100, 25);
        submitBtn.addActionListener(this::submitTransactionRequestBtnActionPerformed);

        popUpFrame2.add(chooseLbl);
        popUpFrame2.add(amountLbl);
        popUpFrame2.add(cancelBtn);
        popUpFrame2.add(submitBtn);
        popUpFrame2.add(amountField);

        popUpFrame2.setResizable(false);
        popUpFrame2.setVisible(true);
    }

    // Navigation bar buttons here
    private void homeBtnActionPerformed(ActionEvent ae) {
        ViewUtility.setEnabledPanelAndComponents(panels[0], true);
        ViewUtility.setEnabledPanelAndComponents(panels[1], false);
        ViewUtility.setEnabledPanelAndComponents(panels[2],false);
        bankController.getAccountBalance();
    }

    private void transactionHistoryBtnActionPerformed(ActionEvent ae) {
        ViewUtility.setEnabledPanelAndComponents(panels[0], false);
        ViewUtility.setEnabledPanelAndComponents(panels[1], true);
        ViewUtility.setEnabledPanelAndComponents(panels[2], false);
        bankController.getTransactionHistory();
    }

    private void profileBtnActionPerformed(ActionEvent actionEvent) {
        ViewUtility.setEnabledPanelAndComponents(panels[0], false);
        ViewUtility.setEnabledPanelAndComponents(panels[1], false);
        ViewUtility.setEnabledPanelAndComponents(panels[2], true);
    }

    private void logoutBtnActionPerformed(ActionEvent ae) {
        int response = JOptionPane.showConfirmDialog(
                this.getMainFrame(),
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            bankController.showLoginWindow();
        }
    }

    // Transaction buttons here
    private void depositBtnActionPerformed(ActionEvent ae) {
        withdrawBtn.setEnabled(false);
        createPopUpWindow1();
    }

    private void withdrawBtnActionPerformed(ActionEvent ae) {
        depositBtn.setEnabled(false);
        createPopUpWindow1();
    }

    private void enterBtnActionPerformed(ActionEvent ae) {
        if (popUpFrame1 != null) {
            popUpFrame1.dispose();
        }
        if (popUpFrame2 == null || !popUpFrame2.isVisible()) {
            try{
                bankController.authenticateBankCredentials(
                        Integer.parseInt(bankAccountNumField.getText()),
                        Integer.parseInt(cardPINField.getText())
                );
            } catch (NumberFormatException ex){
                ViewUtility.showMessage("Please enter fields.");
            }
        } else {
            popUpFrame2.dispose();
        }
    }

    private void cancelBtnActionPerformed(ActionEvent ae) {
        if (popUpFrame1 != null) {
            popUpFrame1.dispose();
        }

        depositBtn.setEnabled(true);
        withdrawBtn.setEnabled(true);

        if (popUpFrame2 != null && popUpFrame2.isVisible()) {
            popUpFrame2.dispose();
        }
    }

    private void submitTransactionRequestBtnActionPerformed(ActionEvent ae) {
        if(bankGroup.getSelection() != null && !amountField.getText().isEmpty()){
            String transactionType = (depositBtn.isEnabled())? depositBtn.getText() : withdrawBtn.getText()+"al";
            System.out.println(transactionType + " " + selectedBank);
            bankController.initiateTransactionRequest(transactionType , selectedBank, Integer.parseInt(amountField.getText()));
        } else {
            ViewUtility.showMessage("Please enter fields.");
        }
        popUpFrame2.dispose();
        depositBtn.setEnabled(true);
        withdrawBtn.setEnabled(true);
    }

    public void udpateTransactionHistoryTable(TransactionHistoryDTO transactionHistory) {
        System.out.println("invoked");
        tableModel.addRow(new Object[]{
                transactionHistory.getTransactionID(),
                transactionHistory.getBankAccountNumberID(),
                transactionHistory.getBankName(),
                transactionHistory.getTransactionType(),
                transactionHistory.getAmount(),
                transactionHistory.getTransactionDateTime(),
                transactionHistory.getRequestStatus(),
                transactionHistory.getOTP()
        });
        tableModel.fireTableDataChanged();
        table.revalidate();
        table.repaint();
    }

    // Getters/Setters
    public String getSelectedBank(JToggleButton tb) {
        return tb.getText();
    }

    public void setDisplayBalanceField(String bankAccountBalance) {
        this.displayBalanceField.setText(bankAccountBalance);;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
