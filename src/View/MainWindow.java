package View;

import Controller.IMBankController;
import DTO.TransactionHistoryDTO;
import DTO.UserProfileDTO;
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
    private JLabel fnameLbl,profileLbl,
                   personInfoLbl, ageLbl,
                   bdayLbl, addressLbl,genderLbl;
    private JLabel usernameLbl, bankAccIDNoLbl,contactNoLbl,emailLbl,accInfoLbl;
    private JTextField changePassTF, confirmpassTF;
    private JButton editBtn, changePassBtn;

    // Main window components
    private JSeparator horizontalSeparator;
    private JSeparator verticalSeparator;

    //dimensions
    private int frameheight = 700;
    private int framewidth = 1200;

    private JPanel[] panels = new JPanel[3];
    private final IMBankController bankController;

    // Constructor
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
            logoutBtn.setBounds(0, 510, 300, 70);
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

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255,255,255));
        //topPanel.setSize(680,150);
        topPanel.setBounds(50,125,760,140);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255,255,255));
        //topPanel.setSize(680,150);
        bottomPanel.setBounds(50,300,760,170);

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
            homePanel.add(topPanel);
            homePanel.add(bottomPanel);

        panels[0] = homePanel;

        ViewUtility.setEnabledPanelAndComponents(panels[0], true);
    }

    private void initTransactionHistoryPanel() {
        transactionHistoryPanel = new JPanel();
        transactionHistoryPanel.setLayout(null);
        transactionHistoryPanel.setBounds(300, 80, 900, 810);
        //transactionHistoryPanel.setBackground(Color.GREEN);

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
            table = new JTable(tableModel){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 80, 870, 480);
            scrollPane.setBorder(null);

            JTableHeader header = table.getTableHeader();
            header.setFont(new java.awt.Font("MS UI Gothic", 1, 15));
            header.setBackground(new Color(35, 35, 77));
            header.setForeground(Color.WHITE);
            header.setReorderingAllowed(false);
            header.setBorder(BorderFactory.createEmptyBorder());
            //header.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

            table.setFont(new java.awt.Font("MS UI Gothic", 1, 15));
            table.setRowHeight(30);
            table.setBackground(new Color(255, 255, 255));
            table.setForeground(new Color(35, 35, 77));
            table.setGridColor(new Color(200, 200, 200));
            table.setSelectionBackground(new Color(173, 216, 230));
            table.setSelectionForeground(Color.BLACK);

            table.setShowVerticalLines(false);

        transactionHistoryPanel.add(transactionHistoryLbl);
        transactionHistoryPanel.add(scrollPane);

        panels[1] = transactionHistoryPanel;

        ViewUtility.setEnabledPanelAndComponents(panels[1], false);
    }

    private void initProfilePanel() {

        profilePanel = new JPanel();
        profilePanel.setLayout(null);
        //profilePanel.setBackground(Color.MAGENTA);
        profilePanel.setBounds(300, 80, 900, 1110);

            profileLbl = new JLabel("User Information");
            profileLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 35));
            profileLbl.setForeground(new java.awt.Color(35, 35, 77));
            profileLbl.setBounds(350,20,600,50);

            editBtn = new JButton("Edit");
            editBtn.setFocusable(false);
            editBtn.setBounds(40, 80, 100, 40);
            editBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 25));
            editBtn.setForeground(new java.awt.Color(224, 224, 231));
            editBtn.setBackground(new java.awt.Color(35, 35, 77));
            editBtn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
            editBtn.setBorderPainted(false);

            //Left Panel, Personal Information
            personInfoLbl = new JLabel("Personal Information");
            personInfoLbl.setFont(new Font("MS UI Gothic",1,28));
            personInfoLbl.setBounds(80,130,400,40);
            personInfoLbl.setForeground(new Color(35, 35, 77));

            fnameLbl = new JLabel("Full Name: ");
            fnameLbl.setFont(new Font("MS UI Gothic", 1, 24));
            fnameLbl.setForeground(new java.awt.Color(35, 35, 77));
            fnameLbl.setBounds(50,180,400,30);

            bdayLbl = new JLabel("Birthdate: ");
            bdayLbl.setFont(new Font("MS UI Gothic", 1, 24));
            bdayLbl.setForeground(new java.awt.Color(35, 35, 77));
            bdayLbl.setBounds(50,230,400,30);

            ageLbl = new JLabel("Age: ");
            ageLbl.setFont(new Font("MS UI Gothic", 1, 24));
            ageLbl.setForeground(new java.awt.Color(35, 35, 77));
            ageLbl.setBounds(50,280,400,30);

            genderLbl = new JLabel("Sex: ");
            genderLbl.setFont(new Font("MS UI Gothic", 1, 24));
            genderLbl.setForeground(new java.awt.Color(35, 35, 77));
            genderLbl.setBounds(50,330,400,30);

            //Right Panel, Account Information
            accInfoLbl = new JLabel("Account Information");
            accInfoLbl.setFont(new Font("MS UI Gothic",1,28));
            accInfoLbl.setForeground(new Color(35, 35, 77));
            accInfoLbl.setBounds(470,130,400,30);

            usernameLbl = new JLabel("Username: ");
            usernameLbl.setFont(new Font("MS UI Gothic", 1, 24));
            usernameLbl.setForeground(new java.awt.Color(35, 35, 77));
            usernameLbl.setBounds(450,180,400,30);

            bankAccIDNoLbl = new JLabel("Bank Account ID NO.: ");
            bankAccIDNoLbl.setFont(new Font("MS UI Gothic", 1, 24));
            bankAccIDNoLbl.setForeground(new java.awt.Color(35, 35, 77));
            bankAccIDNoLbl.setBounds(450,230,400,30);

            contactNoLbl = new JLabel("Contact No: ");
            contactNoLbl.setFont(new Font("MS UI Gothic", 1, 24));
            contactNoLbl.setForeground(new java.awt.Color(35, 35, 77));
            contactNoLbl.setBounds(450,280,400,30);

            emailLbl = new JLabel("Email: ");
            emailLbl.setFont(new Font("MS UI Gothic", 1, 24));
            emailLbl.setForeground(new java.awt.Color(35, 35, 77));
            emailLbl.setBounds(450,330,400,30);

            changePassBtn = new JButton("Change Password");
            changePassBtn.setFocusable(false);
            changePassBtn.setBounds(450, 500, 400, 40);
            changePassBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 25));
            changePassBtn.setForeground(new java.awt.Color(224, 224, 231));
            changePassBtn.setBackground(new java.awt.Color(35, 35, 77));
            changePassBtn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
            changePassBtn.setBorderPainted(false);

            changePassTF = new JTextField();
            changePassTF.setFont(new java.awt.Font("MS UI Gothic", 1, 25));
            changePassTF.setBounds(450,400,400,30);
            changePassTF.setForeground(new Color(35, 35, 77));

            confirmpassTF = new JTextField();
            confirmpassTF.setFont(new java.awt.Font("MS UI Gothic", 1, 25));
            confirmpassTF.setBounds(450,450,400,30);
            confirmpassTF.setForeground(new Color(35, 35, 77));

        profilePanel.add(personInfoLbl);
        profilePanel.add(changePassBtn);
        profilePanel.add(confirmpassTF);
        profilePanel.add(changePassTF);
        profilePanel.add(profileLbl);
        profilePanel.add(editBtn);
        profilePanel.add(fnameLbl);
        profilePanel.add(bdayLbl);
        profilePanel.add(ageLbl);
        profilePanel.add(genderLbl);
        profilePanel.add(usernameLbl);
        profilePanel.add(accInfoLbl);
        profilePanel.add(usernameLbl);
        profilePanel.add(bankAccIDNoLbl);
        profilePanel.add(contactNoLbl);
        profilePanel.add(emailLbl);

        panels[2] =profilePanel;

        ViewUtility.setEnabledPanelAndComponents(panels[2], false);
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
        // Create the pop-up frame
        popUpFrame1 = ViewFactory.createFrame("Initiating Transaction Request", 600, 400);
        popUpFrame1.setLayout(null);
        popUpFrame1.setLocationRelativeTo(mainFrame);

        // Header Label
        JLabel headerLabel1 = new JLabel("Transaction Details");
        headerLabel1.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
        headerLabel1.setBounds(200, 30, 300, 40);
        headerLabel1.setForeground(new Color(35, 35, 77));
        popUpFrame1.add(headerLabel1);

        // Bank Account Number Label and Text Field
        bankAccountNumLbl = new JLabel("Bank Account Number:");
        bankAccountNumLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        bankAccountNumLbl.setBounds(50, 100, 250, 30);
        bankAccountNumLbl.setForeground(new Color(35, 35, 77));
        popUpFrame1.add(bankAccountNumLbl);

        bankAccountNumField = new JTextField();
        bankAccountNumField.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        bankAccountNumField.setBounds(300, 100, 250, 30);
        bankAccountNumField.setForeground(new Color(35, 35, 77));
        popUpFrame1.add(bankAccountNumField);

        // Card PIN Label and Text Field
        cardPINLbl = new JLabel("Card PIN:");
        cardPINLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        cardPINLbl.setBounds(50, 160, 250, 30);
        cardPINLbl.setForeground(new Color(35, 35, 77));
        popUpFrame1.add(cardPINLbl);

        cardPINField = new JTextField();
        cardPINField.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        cardPINField.setBounds(300, 160, 250, 30);
        cardPINField.setForeground(new Color(35, 35, 77));
        popUpFrame1.add(cardPINField);

        // Cancel Button
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(100, 250, 200, 40);
        cancelBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        cancelBtn.setForeground(new Color(224, 215, 231));
        cancelBtn.setBackground(new Color(35, 35, 77));
        cancelBtn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        cancelBtn.setBorderPainted(false);
        cancelBtn.setFocusable(false);
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);
        popUpFrame1.add(cancelBtn);

        // Enter Button
        enterBtn = new JButton("Enter");
        enterBtn.setBounds(300, 250, 200, 40);
        enterBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        enterBtn.setForeground(new Color(224, 215, 231));
        enterBtn.setBackground(new Color(35, 35, 77));
        enterBtn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        enterBtn.setBorderPainted(false);
        enterBtn.setFocusable(false);
        enterBtn.addActionListener(this::enterBtnActionPerformed);
        popUpFrame1.add(enterBtn);

        // Display the frame
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

        if (chooseLbl == null) {
            chooseLbl = new JLabel();
        }
        if (amountLbl == null) {
            amountLbl = new JLabel();
        }
        if (amountField == null) {
            amountField = new JTextField();
        }
        if (cancelBtn == null) {
            cancelBtn = new JButton();
        }
        if (submitBtn == null) {
            submitBtn = new JButton();
        }

        // Create the pop-up frame
        popUpFrame2 = ViewFactory.createFrame("Ongoing Transaction...", 600, 500);
        popUpFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUpFrame2.setLayout(null);
        popUpFrame2.setLocationRelativeTo(mainFrame);

        // Header Label
        chooseLbl.setText("Select Bank and Enter Amount");
        chooseLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        chooseLbl.setBounds(150, 30, 400, 40);
        chooseLbl.setForeground(new Color(35, 35, 77));

        // Create bank toggle buttons
        bank1Btn = ViewFactory.createToggleButton(popUpFrame2, "IMBank", 20, 70);
        bank2Btn = ViewFactory.createToggleButton(popUpFrame2, "BDO", 220, 70);
        bank3Btn = ViewFactory.createToggleButton(popUpFrame2, "LandBank", 420, 70);
        bank4Btn = ViewFactory.createToggleButton(popUpFrame2, "MetroBank", 20, 170);
        bank5Btn = ViewFactory.createToggleButton(popUpFrame2, "BPI", 220, 170);
        bank6Btn = ViewFactory.createToggleButton(popUpFrame2, "RCBC", 420, 170);

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

        // Bank Buttons
        bank1Btn.setBounds(20, 130, 150, 40);
        bank1Btn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        bank1Btn.setForeground(new Color(224, 224, 231));
        bank1Btn.setBackground(new Color(35, 35, 77));
        bank1Btn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        bank1Btn.setBorderPainted(false);
        bank1Btn.setFocusable(false);

        bank2Btn.setBounds(220, 130, 150, 40);
        bank2Btn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        bank2Btn.setForeground(new Color(224, 224, 231));
        bank2Btn.setBackground(new Color(35, 35, 77));
        bank2Btn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        bank2Btn.setBorderPainted(false);
        bank2Btn.setFocusable(false);

        bank3Btn.setBounds(420, 130, 150, 40);
        bank3Btn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        bank3Btn.setForeground(new Color(224, 224, 231));
        bank3Btn.setBackground(new Color(35, 35, 77));
        bank3Btn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        bank3Btn.setBorderPainted(false);
        bank3Btn.setFocusable(false);

        bank4Btn.setBounds(20, 230, 150, 40);
        bank4Btn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        bank4Btn.setForeground(new Color(224, 224, 231));
        bank4Btn.setBackground(new Color(35, 35, 77));
        bank4Btn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        bank4Btn.setBorderPainted(false);
        bank4Btn.setFocusable(false);

        bank5Btn.setBounds(220, 230, 150, 40);
        bank5Btn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        bank5Btn.setForeground(new Color(224, 224, 231));
        bank5Btn.setBackground(new Color(35, 35, 77));
        bank5Btn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        bank5Btn.setBorderPainted(false);
        bank5Btn.setFocusable(false);

        bank6Btn.setBounds(420, 230, 150, 40);
        bank6Btn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        bank6Btn.setForeground(new Color(224, 224, 231));
        bank6Btn.setBackground(new Color(35, 35, 77));
        bank6Btn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        bank6Btn.setBorderPainted(false);
        bank6Btn.setFocusable(false);

        // Amount Label
        amountLbl.setText("Enter Amount:");
        amountLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        amountLbl.setBounds(50, 300, 200, 30);
        amountLbl.setForeground(new Color(35, 35, 77));

        // Amount Text Field
        amountField.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        amountField.setBounds(250, 300, 250, 30);
        amountField.setForeground(new Color(35, 35, 77));
        amountField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        // Cancel Button
        cancelBtn.setText("Cancel");
        cancelBtn.setBounds(100, 400, 200, 40);
        cancelBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        cancelBtn.setForeground(new Color(224, 224, 231));
        cancelBtn.setBackground(new Color(35, 35, 77));
        cancelBtn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        cancelBtn.setBorderPainted(false);
        cancelBtn.setFocusable(false);
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);

        // Submit Button
        submitBtn.setText("Submit");
        submitBtn.setBounds(350, 400, 200, 40);
        submitBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        submitBtn.setForeground(new Color(224, 224, 231));
        submitBtn.setBackground(new Color(35, 35, 77));
        submitBtn.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 77), 1, true));
        submitBtn.setBorderPainted(false);
        submitBtn.setFocusable(false);
        submitBtn.addActionListener(this::submitTransactionRequestBtnActionPerformed);

        popUpFrame2.add(chooseLbl);
        popUpFrame2.add(amountLbl);
        popUpFrame2.add(bank1Btn);
        popUpFrame2.add(bank2Btn);
        popUpFrame2.add(bank3Btn);
        popUpFrame2.add(bank4Btn);
        popUpFrame2.add(bank5Btn);
        popUpFrame2.add(bank6Btn);
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
        System.out.println("Profile button pressed");
        bankController.getUserProfile();
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

    public void displayUserProfile(UserProfileDTO userProfile) {
        System.out.println(userProfile.toString());

        fnameLbl.setText("Full Name: " + userProfile.getFullName());
        bdayLbl.setText("Birth Date: " + userProfile.getBirthDate());
        ageLbl.setText("Age: " + userProfile.getAge());
        genderLbl.setText("Sex: " + userProfile.getSex());
        usernameLbl.setText("Username: " + userProfile.getUsername());
        bankAccIDNoLbl.setText("Bank Account Number: " + userProfile.getBankAccountNumberID());
        contactNoLbl.setText("Contact Number: " + userProfile.getContactNumber());
        emailLbl.setText("Email: " + userProfile.getEmail());
    }

    // Getters/Setters
    public String getSelectedBank(JToggleButton tb) {
        return tb.getText();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public DefaultTableModel getTableModel() { return tableModel; }

    public void setDisplayBalanceField(String bankAccountBalance) {
        this.displayBalanceField.setText(bankAccountBalance);;
    }
}
