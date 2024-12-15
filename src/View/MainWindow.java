package View;

import Controller.IMBankController;
import DTO.TransactionHistoryDTO;
import DTO.UserProfileDTO;
import Utility.ViewUtility;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;


public class MainWindow extends Component {

    private JFrame mainFrame;

    // Header panel and its components
    private JPanel headerPanel, bannerPanel;
    private JLabel headerLbl, bannerLbl;

    // Left Side Navigation Bar Panel and its components
    private JPanel navPanel;
    private JButton homeBtn, transactionHistoryBtn, profileBtn, logoutBtn;

    // Home panel and its components here
    private JPanel homePanel;
    private JLabel balanceLbl;
    private JTextField displayBalanceField;
    private JButton depositBtn, withdrawBtn;

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
                   bdayLbl, genderLbl;
    private JLabel usernameLbl, bankAccIDNoLbl,contactNoLbl,emailLbl,accInfoLbl;
    private JTextField changePassTF, confirmpassTF;
    private JButton editBtn, changePassBtn;

    private JPanel editableProfilePanel;

    // Main window components
    private JSeparator horizontalSeparator, verticalSeparator;

    //dimensions
    private int frameheight = 700;
    private int framewidth = 1200;

    private JPanel[] panels = new JPanel[4];
    private JFrame[] popUpFrames = new JFrame[2];

    private final IMBankController bankController;

    // Constructor
    public MainWindow(IMBankController bankController) {
        this.bankController = bankController;
        initMainWindowComponents();
    }

    private void initMainWindowComponents() {
        mainFrame = ViewFactory.createFrame("IM Bank: Main", framewidth, frameheight);
        mainFrame.addWindowListener(ViewUtility.getWindowAdapter());

            initBannerPanel();
            initHeaderPanel();
            initNavPanel();

            horizontalSeparator = new JSeparator(SwingConstants.HORIZONTAL);
            horizontalSeparator.setBounds(0, 80, 800, 2);
            horizontalSeparator.setForeground(Color.BLACK);

            verticalSeparator = new JSeparator(SwingConstants.VERTICAL);
            verticalSeparator.setBounds(200, 80, 2 , 498);
            verticalSeparator.setForeground(Color.BLACK);

            initHomePanelAndComponents();
            initTransactionHistoryPanel();
            initProfilePanel();
            initEditableProfilePanel();

        mainFrame.getContentPane().add(bannerPanel);
        mainFrame.getContentPane().add(navPanel);
        mainFrame.getContentPane().add(homePanel);
        mainFrame.getContentPane().add(transactionHistoryPanel);
        mainFrame.getContentPane().add(profilePanel);
        mainFrame.getContentPane().add(editableProfilePanel);
        mainFrame.getContentPane().add(horizontalSeparator);
        mainFrame.getContentPane().add(verticalSeparator);

        mainFrame.setResizable(false);
    }

    private void initBannerPanel() {
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
    }

    private void initHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 900, 80);

        headerLbl = new JLabel("Welcome ,");
        headerLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 28));
        headerLbl.setForeground(new java.awt.Color(35, 35, 77));
        headerLbl.setBounds(30, 30, 400, 50);

        headerPanel.add(headerLbl);
    }

    private void initNavPanel() {
        navPanel = new JPanel();
        navPanel.setLayout(null);
        navPanel.setBounds(0, 82, 300, frameheight);
        navPanel.setBackground(new Color(255, 255, 255));

            homeBtn = ViewFactory.createCustomButton1(navPanel, "Home", new ViewFactory.Bounds(0, 10, 300, 70), 30);
            homeBtn.addActionListener(this::homeBtnActionPerformed);

            transactionHistoryBtn = ViewFactory.createCustomButton1(navPanel, "Transaction History", new ViewFactory.Bounds(0, 83, 300, 70), 30);
            transactionHistoryBtn.addActionListener(this::transactionHistoryBtnActionPerformed);

            profileBtn = ViewFactory.createCustomButton1(navPanel, "Profile", new ViewFactory.Bounds(0, 156, 300, 70), 30);
            profileBtn.addActionListener(this::profileBtnActionPerformed);

            logoutBtn = ViewFactory.createCustomButton1(navPanel, "Logout", new ViewFactory.Bounds(0, 512, 300, 70), 30);
            logoutBtn.addActionListener(this::logoutBtnActionPerformed);
    }

    private void initHomePanelAndComponents() {
        homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setBounds(300, 80, 900, 1110);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255,255,255));
        topPanel.setBounds(50,125,760,140);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255,255,255));
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

            depositBtn = ViewFactory.createCustomButton1(homePanel, "Deposit", new ViewFactory.Bounds(200, 400, 200, 45), 30);
            depositBtn.addActionListener(this::depositBtnActionPerformed);

            withdrawBtn = ViewFactory.createCustomButton1(homePanel, "Withdraw", new ViewFactory.Bounds(430, 400, 200, 45), 30);
            withdrawBtn.addActionListener(this::withdrawBtnActionPerformed);

            homePanel.add(headerPanel);
            homePanel.add(choiceLbl);
            homePanel.add(balanceLbl);
            homePanel.add(displayBalanceField);
            homePanel.add(topPanel);
            homePanel.add(bottomPanel);

        panels[0] = homePanel;

        ViewUtility.enablePanelAndComponents(panels[0], true);
    }

    private void initTransactionHistoryPanel() {
        transactionHistoryPanel = new JPanel();
        transactionHistoryPanel.setLayout(null);
        transactionHistoryPanel.setBounds(300, 80, 900, 810);

        columnNames = new String[]{
                "<html><center>Transaction<br>ID</center></html>",
                "<html><center>Bank Account<br>Number ID</center></html>",
                "<html><center>Bank<br>Name</center></html>",
                "<html><center>Transaction<br>Type</center></html>",
                "<html><center>Amount</center></html>",
                "<html><center>Transaction<br>DateTime</center></html>",
                "<html><center>Request<br>Status</center></html>",
                "<html><center>OTP</center></html>"
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

            int[] columnWidths = {100, 100, 80, 110, 80, 200, 100, 80}; // Adjust as needed
            for (int i = 0; i < columnWidths.length; i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
                table.getColumnModel().getColumn(i).setMinWidth(columnWidths[i]);
                table.getColumnModel().getColumn(i).setMaxWidth(columnWidths[i]);
            }

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            headerRenderer.setFont(new java.awt.Font("MS UI Gothic", 1, 15));
            headerRenderer.setBackground(new Color(35, 35, 77));
            headerRenderer.setForeground(Color.WHITE);

            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }

            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 80, 870, 430);
            scrollPane.setBorder(null);
            
            JButton deleteBtn = ViewFactory.createCustomButton1(transactionHistoryPanel, "Delete", new ViewFactory.Bounds(90, 530, 100, 30), 25);
            deleteBtn.addActionListener(this::deleteBtnActionPerformed);

            JTableHeader header = table.getTableHeader();
            header.setFont(new java.awt.Font("MS UI Gothic", 1, 15));
            header.setBackground(new Color(35, 35, 77));
            header.setForeground(Color.WHITE);
            header.setReorderingAllowed(false);
            header.setBorder(BorderFactory.createEmptyBorder());

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

        ViewUtility.enablePanelAndComponents(panels[1], false);
    }

    private void initProfilePanel() {

        profilePanel = new JPanel();
        profilePanel.setLayout(null);
        profilePanel.setBounds(300, 80, 900, 1110);

        JPanel whitePanel = new JPanel();
        whitePanel.setLayout(null);
        whitePanel.setBounds(30,100,830,450);
        whitePanel.setBackground(Color.white);

            profileLbl = new JLabel("User Information");
            profileLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 35));
            profileLbl.setForeground(new java.awt.Color(35, 35, 77));
            profileLbl.setBounds(320,20,600,50);

            //Left Panel, Personal Information
            personInfoLbl = new JLabel("Personal Information");
            personInfoLbl.setFont(new Font("MS UI Gothic",1,28));
            personInfoLbl.setBounds(80,120,400,40);
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
            accInfoLbl.setBounds(470,120,400,30);

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

            editBtn = ViewFactory.createCustomButton1(profilePanel, "Edit", new ViewFactory.Bounds(31, 55, 70, 30), 20);
            editBtn.addActionListener(this::editBtnActionPerformed);

            changePassBtn = ViewFactory.createCustomButton1(profilePanel, "Change Password", new ViewFactory.Bounds(450, 500, 400, 40), 25);

            changePassTF = new JTextField();
            changePassTF.setFont(new java.awt.Font("MS UI Gothic", 1, 25));
            changePassTF.setBounds(450,400,400,30);
            changePassTF.setForeground(new Color(35, 35, 77));

            confirmpassTF = new JTextField();
            confirmpassTF.setFont(new java.awt.Font("MS UI Gothic", 1, 25));
            confirmpassTF.setBounds(450,450,400,30);
            confirmpassTF.setForeground(new Color(35, 35, 77));

        profilePanel.add(personInfoLbl);
        profilePanel.add(confirmpassTF);
        profilePanel.add(changePassTF);
        profilePanel.add(profileLbl);
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
        profilePanel.add(whitePanel);

        panels[2] =profilePanel;

        ViewUtility.enablePanelAndComponents(panels[2], false);
    }

    private JTextField usernameField, contactNoField, emailField;
    private JLabel editPanelFNameLlb, editPanelBDayLbl, editPanelAgeLbl, editPanelSexLbl, editPanelBankAccIdNumLbl;

    private void initEditableProfilePanel() {
        editableProfilePanel = new JPanel();
        editableProfilePanel.setLayout(null);
        editableProfilePanel.setBounds(300, 80, 900, 1110);

        JLabel profileLbl = new JLabel("User Information");
        profileLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 35));
        profileLbl.setForeground(new java.awt.Color(35, 35, 77));
        profileLbl.setBounds(320,20,600,50);

        //Left Panel, Personal Information
        JLabel personInfoLbl = new JLabel("Personal Information");
        personInfoLbl.setFont(new Font("MS UI Gothic",1,28));
        personInfoLbl.setBounds(80,120,400,40);
        personInfoLbl.setForeground(new Color(35, 35, 77));

        editPanelFNameLlb = new JLabel("Full Name: " );
        editPanelFNameLlb.setFont(new Font("MS UI Gothic", 1, 24));
        editPanelFNameLlb.setForeground(new java.awt.Color(35, 35, 77));
        editPanelFNameLlb.setBounds(50,180,400,30);

        editPanelBDayLbl = new JLabel("Birthdate: " );
        editPanelBDayLbl.setFont(new Font("MS UI Gothic", 1, 24));
        editPanelBDayLbl.setForeground(new java.awt.Color(35, 35, 77));
        editPanelBDayLbl.setBounds(50,230,400,30);

        editPanelAgeLbl = new JLabel("Age: " );
        editPanelAgeLbl.setFont(new Font("MS UI Gothic", 1, 24));
        editPanelAgeLbl.setForeground(new java.awt.Color(35, 35, 77));
        editPanelAgeLbl.setBounds(50,280,400,30);

        editPanelSexLbl = new JLabel("Sex: " );
        editPanelSexLbl.setFont(new Font("MS UI Gothic", 1, 24));
        editPanelSexLbl.setForeground(new java.awt.Color(35, 35, 77));
        editPanelSexLbl.setBounds(50,330,400,30);

        //Right Panel, Account Information
        JLabel accInfoLbl = new JLabel("Account Information");
        accInfoLbl.setFont(new Font("MS UI Gothic",1,28));
        accInfoLbl.setForeground(new Color(35, 35, 77));
        accInfoLbl.setBounds(470,120,400,30);

        JLabel usernameLbl = new JLabel("Username: ");
        usernameLbl.setFont(new Font("MS UI Gothic", 1, 24));
        usernameLbl.setForeground(new java.awt.Color(35, 35, 77));
        usernameLbl.setBounds(450,180,400,30);

        editPanelBankAccIdNumLbl = new JLabel("Bank Account ID NO.: " );
        editPanelBankAccIdNumLbl.setFont(new Font("MS UI Gothic", 1, 24));
        editPanelBankAccIdNumLbl.setForeground(new java.awt.Color(35, 35, 77));
        editPanelBankAccIdNumLbl.setBounds(450,230,400,30);

        JLabel contactNoLbl = new JLabel("Contact No: ");
        contactNoLbl.setFont(new Font("MS UI Gothic", 1, 24));
        contactNoLbl.setForeground(new java.awt.Color(35, 35, 77));
        contactNoLbl.setBounds(450,280,400,30);

        JLabel emailLbl = new JLabel("Email: ");
        emailLbl.setFont(new Font("MS UI Gothic", 1, 24));
        emailLbl.setForeground(new java.awt.Color(35, 35, 77));
        emailLbl.setBounds(450,330,400,30);

        ViewFactory.createCustomButton1(editableProfilePanel, "Edit", new ViewFactory.Bounds(31, 55, 70, 30), 20);

        JButton saveBtn = ViewFactory.createCustomButton1(editableProfilePanel, "Save", new ViewFactory.Bounds(102, 55, 70, 30), 20);
        saveBtn.addActionListener(this::saveProfileChangesBtnActionPerformed);

        JButton cancelBtn = ViewFactory.createCustomButton1(editableProfilePanel, "Cancel", new ViewFactory.Bounds(173, 55, 70, 30), 20);
        cancelBtn.addActionListener(this::profileBtnActionPerformed);

        ViewFactory.createCustomButton1(editableProfilePanel, "Change Password", new ViewFactory.Bounds(450, 500, 400, 40), 25);

        usernameField = new JTextField();
        usernameField.setBounds(540, 82, 200, 30);
        usernameField.setFont(new Font("MS UI Gothic", 1, 24));
        usernameField.setForeground(new java.awt.Color(35, 35, 77));

        contactNoField = new JTextField();
        contactNoField.setBounds(560, 182, 200, 30);
        contactNoField.setFont(new Font("MS UI Gothic", 1, 24));
        contactNoField.setForeground(new java.awt.Color(35, 35, 77));

        emailField = new JTextField();
        emailField.setBounds(490, 232, 200, 30);
        emailField.setFont(new Font("MS UI Gothic", 1, 24));
        emailField.setForeground(new java.awt.Color(35, 35, 77));

        changePassTF = new JTextField();
        changePassTF.setFont(new java.awt.Font("MS UI Gothic", 1, 25));
        changePassTF.setBounds(450,400,400,30);
        changePassTF.setForeground(new Color(35, 35, 77));

        confirmpassTF = new JTextField();
        confirmpassTF.setFont(new java.awt.Font("MS UI Gothic", 1, 25));
        confirmpassTF.setBounds(450,450,400,30);
        confirmpassTF.setForeground(new Color(35, 35, 77));

        JPanel whitePanel = new JPanel();
        whitePanel.setLayout(null);
        whitePanel.setBounds(30,100,830,450);
        whitePanel.setOpaque(true);
        whitePanel.setBackground(Color.white);
        whitePanel.add(usernameField);
        whitePanel.add(contactNoField);
        whitePanel.add(emailField);

        editableProfilePanel.add(personInfoLbl);
        editableProfilePanel.add(confirmpassTF);
        editableProfilePanel.add(changePassTF);
        editableProfilePanel.add(profileLbl);
        editableProfilePanel.add(editPanelFNameLlb);
        editableProfilePanel.add(editPanelBDayLbl);
        editableProfilePanel.add(editPanelAgeLbl);
        editableProfilePanel.add(editPanelSexLbl);
        editableProfilePanel.add(usernameLbl);
        editableProfilePanel.add(accInfoLbl);
        editableProfilePanel.add(usernameLbl);
        editableProfilePanel.add(editPanelBankAccIdNumLbl);
        editableProfilePanel.add(contactNoLbl);
        editableProfilePanel.add(emailLbl);
        editableProfilePanel.add(whitePanel);

        panels[3] = editableProfilePanel;

        ViewUtility.enablePanelAndComponents(panels[3], false);
    }

    private void editBtnActionPerformed(ActionEvent actionEvent) {
        SwingUtilities.invokeLater(() -> {
            ViewUtility.enablePanelAndComponents(panels[3], true);
            ViewUtility.enablePanelAndComponents(panels[0], false);
            ViewUtility.enablePanelAndComponents(panels[1], false);
            ViewUtility.enablePanelAndComponents(panels[2], false);
        });
    }

    // Pop-up window for transaction
    private JFrame popUpFrame1;
    private JLabel bankAccountNumLbl, cardPINLbl;
    private JTextField bankAccountNumField;
    private JPasswordField cardPINField;
    private JButton cancelBtn, enterBtn;

    private void createPopUpWindow1() {
        // Create the pop-up frame
        popUpFrame1 = ViewFactory.createFrame("Initiating Transaction Request", 600, 350);
        popUpFrame1.setLayout(null);
        popUpFrame1.setLocationRelativeTo(mainFrame);
        popUpFrame1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                withdrawBtn.setEnabled(true);
                depositBtn.setEnabled(true);
                popUpFrames[0] = null;
            }
        });

        // Header Label
        JLabel headerLabel1 = new JLabel("Verify Account Credentials");
        headerLabel1.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
        headerLabel1.setBounds(120, 25, 400, 40);
        headerLabel1.setForeground(new Color(35, 35, 77));
        popUpFrame1.add(headerLabel1);

        // Bank Account Number Label and Text Field
        bankAccountNumLbl = new JLabel("Bank Account Number:");
        bankAccountNumLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
        bankAccountNumLbl.setBounds(50, 110, 250, 30);
        bankAccountNumLbl.setForeground(new Color(35, 35, 77));
        popUpFrame1.add(bankAccountNumLbl);

        bankAccountNumField = new JTextField();
        bankAccountNumField.setFont(new Font("MS UI Gothic", Font.BOLD, 27));
        bankAccountNumField.setBounds(300, 108, 250, 32);
        bankAccountNumField.setForeground(new Color(35, 35, 77));
        bankAccountNumField.addKeyListener(ViewUtility.addNumberInputKeyListener());
        popUpFrame1.add(bankAccountNumField);

        // Card PIN Label and Text Field
        cardPINLbl = new JLabel("Card PIN:");
        cardPINLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
        cardPINLbl.setBounds(50, 170, 250, 30);
        cardPINLbl.setForeground(new Color(35, 35, 77));

        popUpFrame1.add(cardPINLbl);

        cardPINField = new JPasswordField();
        cardPINField.setFont(new Font("MS UI Gothic", Font.BOLD, 27));
        cardPINField.setBounds(300, 168, 250, 32);
        cardPINField.setForeground(new Color(35, 35, 77));
        cardPINField.addKeyListener(ViewUtility.addNumberInputKeyListener());
        cardPINField.setEchoChar('*');
        popUpFrame1.add(cardPINField);

        // Cancel Button
        cancelBtn = ViewFactory.createCustomButton1(popUpFrame1, "Cancel", new ViewFactory.Bounds(120, 250, 150, 40), 25);
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);

        // Enter Button
        enterBtn = ViewFactory.createCustomButton1(popUpFrame1, "Enter", new ViewFactory.Bounds(320, 250, 150, 40), 25);
        enterBtn.addActionListener(this::enterBtnActionPerformed);
        enterBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ENTER"), "enter");
        enterBtn.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterBtnActionPerformed(e);
            }
        });

        // Display the frame
        popUpFrame1.setVisible(true);
        popUpFrames[0] = popUpFrame1;
    }

    private JFrame popUpFrame2;
    private JToggleButton bank1Btn, bank2Btn, bank3Btn;
    private JToggleButton bank4Btn, bank5Btn, bank6Btn;
    private ButtonGroup bankGroup;
    private JButton submitBtn;
    private JLabel chooseLbl, amountLbl;
    private JTextField amountField;
    private String selectedBank;

    public void createPopUpWindow2() {
        // Create the pop-up frame
        popUpFrame2 = ViewFactory.createFrame("Ongoing Transaction...", 600, 500);
        popUpFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUpFrame2.setLayout(null);
        popUpFrame2.setLocationRelativeTo(mainFrame);
        popUpFrame2.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                withdrawBtn.setEnabled(true);
                depositBtn.setEnabled(true);
                disposePopUpFrame2();
            }
        });

            // Header Label
            chooseLbl = new JLabel();
            chooseLbl.setText("Select Bank and Enter Amount");
            chooseLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 29));
            chooseLbl.setBounds(105, 30, 400, 40);
            chooseLbl.setForeground(new Color(35, 35, 77));

            // Create bank toggle buttons
            bank1Btn = ViewFactory.createToggleButton(popUpFrame2, "IMBank", 38, 110);
            bank2Btn = ViewFactory.createToggleButton(popUpFrame2, "BDO", 218, 110);
            bank3Btn = ViewFactory.createToggleButton(popUpFrame2, "LandBank", 398, 110);
            bank4Btn = ViewFactory.createToggleButton(popUpFrame2, "MetroBank", 38, 200);
            bank5Btn = ViewFactory.createToggleButton(popUpFrame2, "BPI", 218, 200);
            bank6Btn = ViewFactory.createToggleButton(popUpFrame2, "RCBC", 398, 200);

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

            // Amount Label
            amountLbl = new JLabel();
            amountLbl.setText("Enter Amount:");
            amountLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
            amountLbl.setBounds(80, 320, 200, 30);
            amountLbl.setForeground(new Color(35, 35, 77));

            // Amount Text Field
            amountField = new JTextField();
            amountField.setFont(new Font("MS UI Gothic", Font.BOLD, 27));
            amountField.setBounds(250, 320, 250, 32);
            amountField.setForeground(new Color(35, 35, 77));
            amountField.addKeyListener(ViewUtility.addNumberInputKeyListener());

            // Cancel Button
            cancelBtn = ViewFactory.createCustomButton1(popUpFrame2, "Cancel", new ViewFactory.Bounds(120, 400, 150, 40), 25);
            cancelBtn.addActionListener(this::cancelBtnActionPerformed);

            // Submit Button
            submitBtn = ViewFactory.createCustomButton1(popUpFrame2, "Submit", new ViewFactory.Bounds(320, 400, 150, 40), 25);
            submitBtn.addActionListener(this::submitTransactionRequestBtnActionPerformed);
            submitBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                     .put(KeyStroke.getKeyStroke("ENTER"), "submit");
            submitBtn.getActionMap().put("submit", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    submitTransactionRequestBtnActionPerformed(e);
                }
            });

        popUpFrame2.add(chooseLbl);
        popUpFrame2.add(amountLbl);
        popUpFrame2.add(amountField);

        popUpFrame2.setResizable(false);
        popUpFrame2.setVisible(true);

        popUpFrames[1] = popUpFrame2;
        disposePopUpFrame1();
     }

    // Navigation bar buttons here
    private void homeBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            ViewUtility.enablePanelAndComponents(panels[0], true);
            ViewUtility.enablePanelAndComponents(panels[1], false);
            ViewUtility.enablePanelAndComponents(panels[2], false);
            ViewUtility.enablePanelAndComponents(panels[3], false);
        });
        bankController.getAccountBalance();
    }

    private void transactionHistoryBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            ViewUtility.enablePanelAndComponents(panels[0], false);
            ViewUtility.enablePanelAndComponents(panels[1], true);
            ViewUtility.enablePanelAndComponents(panels[2], false);
            ViewUtility.enablePanelAndComponents(panels[3], false);
        });
        bankController.getTransactionHistory();
    }

    private void profileBtnActionPerformed(ActionEvent actionEvent) {
        SwingUtilities.invokeLater(() -> {
            ViewUtility.enablePanelAndComponents(panels[0], false);
            ViewUtility.enablePanelAndComponents(panels[1], false);
            ViewUtility.enablePanelAndComponents(panels[2], true);
            ViewUtility.enablePanelAndComponents(panels[3], false);
        });
        bankController.getUserProfile();
    }

    private void logoutBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            int response = JOptionPane.showConfirmDialog(
                    this.getMainFrame(),
                    "Are you sure you want to log out?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                tableModel.setNumRows(0);
                bankController.showLoginWindow();
                bankController.logoutUserSession();
                ViewUtility.enablePanelAndComponents(panels[0], true);
                ViewUtility.enablePanelAndComponents(panels[1], false);
                ViewUtility.enablePanelAndComponents(panels[2], false);
                ViewUtility.enablePanelAndComponents(panels[3], false);
                if(popUpFrames[0] != null) { disposePopUpFrame1(); }
                if(popUpFrames[1] != null) { disposePopUpFrame2(); }
            }
        });
    }

    // Transaction buttons here
    private void depositBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if(popUpFrames[0] != null || popUpFrames[1] != null){
                ViewUtility.showInfoMessage("You have an ongoing transaction window open.");
                return;
            }
            withdrawBtn.setEnabled(false);
            createPopUpWindow1();
        });
    }

    private void withdrawBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if(popUpFrames[0] != null || popUpFrames[1] != null){
                ViewUtility.showInfoMessage("You have an ongoing transaction window open.");
                return;
            }
            depositBtn.setEnabled(false);
            createPopUpWindow1();
        });
    }

    private void enterBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if (popUpFrames[1] == null || !popUpFrames[1].isVisible()) {
                if(!String.valueOf(bankAccountNumField.getText()).isEmpty() && !String.valueOf(cardPINField.getPassword()).isEmpty()) {
                    SwingUtilities.invokeLater(bankController::checkForPendingTransactions);
                } else {
                    SwingUtilities.invokeLater(() -> {
                        ViewUtility.showErrorMessage(popUpFrame1,"Please enter fields.");
                    });
                }
            } else {
                disposePopUpFrame2();
            }
        });
    }

    private void cancelBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if(popUpFrames[1] == null){ disposePopUpFrame1(); }
            depositBtn.setEnabled(true);
            withdrawBtn.setEnabled(true);
            if (popUpFrames[1] != null && popUpFrames[1].isVisible()) {
                disposePopUpFrame2();
            }
        });
    }

    private void submitTransactionRequestBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if(bankGroup.getSelection() != null && !String.valueOf(amountField.getText()).isEmpty()){
                String transactionType = (depositBtn.isEnabled())? depositBtn.getText() : withdrawBtn.getText()+"al";
                System.out.println(transactionType + " " + selectedBank);
                bankController.initiateTransactionRequest(transactionType , selectedBank, Integer.parseInt(amountField.getText()));
            } else {
                ViewUtility.showInfoMessage("Please enter fields.");
                return;
            }
            disposePopUpFrame2();
            bankGroup.clearSelection();
            amountField.setText("");
            depositBtn.setEnabled(true);
            withdrawBtn.setEnabled(true);
        });
    }

    private void saveProfileChangesBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if(!String.valueOf(usernameField.getText()).isEmpty() && !String.valueOf(contactNoField).isEmpty()
                    && !String.valueOf(emailField.getText()).isEmpty()){
                SwingUtilities.invokeLater(() -> {
                    bankController.updateUserProfile(String.valueOf(usernameField.getText()), String.valueOf(contactNoField.getText()), String.valueOf(emailField.getText()));
                    profileBtn.doClick();
                });
            } else {
                ViewUtility.showInfoMessage("Fields cannot be empty or cancel changes.");
            }
        });
    }

    private void deleteBtnActionPerformed(ActionEvent actionEvent) {
        SwingUtilities.invokeLater(() -> {
            int[] selectedRows = table.getSelectedRows(); // Get selected row indices
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(this,
                        "No items selected! \nPlease select at least one item.",
                            "Select Rows",
                            JOptionPane.ERROR_MESSAGE);
            } else {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete the selected items?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    List<Integer> idsToDelete = new ArrayList<>();

                    for (int row : selectedRows) {
                        int modelRow = table.convertRowIndexToModel(row);
                        int id = (int) model.getValueAt(modelRow, 0);
                        idsToDelete.add(id);
                    }

                    bankController.deleteTransactionsByIdsAsync(idsToDelete, () -> {
                        for (int i = selectedRows.length - 1; i >= 0; i--) {
                            model.removeRow(table.convertRowIndexToModel(selectedRows[i]));
                        }
                    });
                }
            }
        });
    }

    public void updateTransactionHistoryTable(TransactionHistoryDTO transactionHistory) {
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

    private void disposePopUpFrame1() {
        popUpFrames[0].dispose();
        popUpFrames[0] = null;
    }

    private void disposePopUpFrame2() {
        popUpFrames[1].dispose();
        popUpFrames[1] = null;
    }

    public void displayUserProfile(UserProfileDTO userProfile) {
        editPanelFNameLlb.setText("Full Name: " + userProfile.getFullName());
        editPanelBDayLbl.setText("Birthdate: " + userProfile.getBirthDate());
        editPanelAgeLbl.setText("Age: " + userProfile.getAge());
        editPanelSexLbl.setText("Sex: " + userProfile.getSex());
        editPanelBankAccIdNumLbl.setText("Bank Account ID NO.: " + userProfile.getBankAccountNumberID());
        usernameField.setText(userProfile.getUsername());
        contactNoField.setText(userProfile.getContactNumber());
        emailField.setText(userProfile.getEmail());

        fnameLbl.setText("Full Name: " + userProfile.getFullName());
        bdayLbl.setText("Birthdate: " + userProfile.getBirthDate());
        ageLbl.setText("Age: " + userProfile.getAge());
        genderLbl.setText("Sex: " + userProfile.getSex());
        usernameLbl.setText("Username: " + userProfile.getUsername());
        bankAccIDNoLbl.setText("Bank Account ID NO.: " + userProfile.getBankAccountNumberID());
        contactNoLbl.setText("Contact No: " + userProfile.getContactNumber());
        emailLbl.setText("Email: " + userProfile.getEmail());
     }

    public void proceedTransaction(){
        bankController.authenticateBankCredentials(
                Integer.parseInt(bankAccountNumField.getText()),
                Integer.parseInt(String.valueOf(cardPINField.getPassword()))
        );
    }

    public void updateHeaderLbl(String name){
        SwingUtilities.invokeLater(() -> {
            this.headerLbl.setText("Welcome " + name + ",");
        });
    }

    public void clearAccountCredentialsField(){
        bankAccountNumField.setText("");
        cardPINField.setText("");
    }

    // Getters/Setters
    public String getSelectedBank(JToggleButton tb) {
        return tb.getText();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setDisplayBalanceField(String bankAccountBalance) {
        this.displayBalanceField.setText("Php. " + bankAccountBalance);;
    }

    public JButton getTransactionBtn(){
        return transactionHistoryBtn;
    }
}

