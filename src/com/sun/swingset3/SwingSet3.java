package com.sun.swingset3;

import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.UserBean;
import com.sun.swingset3.utilities.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.View;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.sun.swingset3.codeview.CodeViewer;

public class SwingSet3 extends SingleFrameApplication  {

    static Map<String,String> authMap = new HashMap<String,String>();

    static ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();

    static final Logger logger = Logger.getLogger(SwingSet3.class.getName());

    private static final ServiceLoader<LookAndFeel> LOOK_AND_FEEL_LOADER = ServiceLoader.load(LookAndFeel.class);

    public static String title;

    public static final String CONTROL_VERY_LIGHT_SHADOW_KEY = "controlVeryLightShadowColor";
    public static final String CONTROL_LIGHT_SHADOW_KEY = "controlLightShadowColor";
    public static final String CONTROL_MID_SHADOW_KEY = "controlMidShadowColor";
    public static final String CONTROL_VERY_DARK_SHADOW_KEY = "controlVeryDarkShadowColor";
    public static final String CONTROL_DARK_SHADOW_KEY = "controlDarkShadowColor";
    public static final String TITLE_GRADIENT_COLOR1_KEY = "titleGradientColor1";
    public static final String TITLE_GRADIENT_COLOR2_KEY = "titleGradientColor2";
    public static final String TITLE_FOREGROUND_KEY = "titleForegroundColor";
    public static final String CODE_HIGHLIGHT_KEY = "codeHighlightColor";
    public static final String TITLE_FONT_KEY = "titleFont";
    public static final String SUB_PANEL_BACKGROUND_KEY = "subPanelBackgroundColor";

    public static final int MAIN_FRAME_WIDTH = 1066;
    public static final int MAIN_FRAME_HEIGHT = 700;
    public static final int DEMO_SELECTOR_WIDTH = 216;
    public static final int DEMO_PANEL_HEIGHT = 400;
    public static final int DEMO_PANEL_WIDTH = MAIN_FRAME_WIDTH - DEMO_SELECTOR_WIDTH;

    private static final Border EMPTY_BORDER = new EmptyBorder(0, 0, 0, 0);
    private static final Border PANEL_BORDER = new EmptyBorder(10, 10, 10, 10);

    private static final String DEMO_LIST_FILE = "/META-INF/demolist";

    /** UIManager中UI字体相关的key */
    public static String[] DEFAULT_FONT  = new String[]{
            "Table.font"
            ,"TableHeader.font"
            ,"CheckBox.font"
            ,"Tree.font"
            ,"Viewport.font"
            ,"ProgressBar.font"
            ,"RadioButtonMenuItem.font"
            ,"ToolBar.font"
            ,"ColorChooser.font"
            ,"ToggleButton.font"
            ,"Panel.font"
            ,"TextArea.font"
            ,"Menu.font"
            ,"TableHeader.font"
            // ,"TextField.font"
            ,"OptionPane.font"
            ,"MenuBar.font"
            ,"Button.font"
            ,"Label.font"
            ,"PasswordField.font"
            ,"ScrollPane.font"
            ,"MenuItem.font"
            ,"ToolTip.font"
            ,"List.font"
            ,"EditorPane.font"
            ,"Table.font"
            ,"TabbedPane.font"
            ,"RadioButton.font"
            ,"CheckBoxMenuItem.font"
            ,"TextPane.font"
            ,"PopupMenu.font"
            ,"TitledBorder.font"
            ,"ComboBox.font"
    };

    static {
        // Property must be set *early* due to Apple Bug#3909714
        if (System.getProperty("os.name").equals("Mac OS X")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        // temporary workaround for problem with Nimbus classname
        UIManager.LookAndFeelInfo lafInfo[] = UIManager.getInstalledLookAndFeels();
        for(int i = 0; i < lafInfo.length; i++) {
            if (lafInfo[i].getName().equals("Nimbus")) {
                lafInfo[i] = new UIManager.LookAndFeelInfo("Nimbus",
                        "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                break;
            }
        }
        // 调整默认字体
        for (int i = 0; i < DEFAULT_FONT.length; i++)
            UIManager.put(DEFAULT_FONT[i],new Font("微软雅黑", Font.BOLD,14));
        UIManager.setInstalledLookAndFeels(lafInfo);
        UIManager.put("swing.boldMetal", Boolean.FALSE);
    }

    public static void main(final String[] args) {
        initAuthMap();
        final JFrame f=new JFrame();
        f.setTitle("停车场缴费管理系统登录界面");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //设置窗口的大小和位置

        final Container con=f.getContentPane();//生成一个容器
        con.setLayout(new GridLayout(7,1));

        JPanel pan1=new JPanel();//生成一个新的版面
        JLabel title=new JLabel("欢迎登陆本系统");
        title.setFont(new Font("微软雅黑",Font.BOLD, 20));
        pan1.add(title);
        con.add(pan1);
        //最上面的登陆文字

        JPanel pan2=new JPanel();//生成一个新的版面
        JLabel name=new JLabel("用户账号:");
        pan2.add(name);
        final TextField tf_name=new TextField(20);
        pan2.add(tf_name);
        con.add(pan2);
        //用户名及其文本框放置在第二个版面上

        JPanel pan3=new JPanel();//生成一个新的版面
        JLabel pass = new JLabel("密码:");
        pan3.add(pass);
        final JPasswordField password=new JPasswordField(15);
        pan3.add(password);
        con.add(pan3);
        //密码及其密码域放在第三个版面上

        JPanel pan4 = new JPanel();
        JButton b_log=new JButton("登陆");
        b_log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserBean userBean  = new UserBean();
                Integer account = Integer.parseInt(tf_name.getText());
                String pass = password.getText();
                userBean.setId(account);
                userBean.setPassword(pass);
                Boolean flag = parkingLotDBUtils.checkLogin(userBean);
                if(!flag){
                    JOptionPane.showMessageDialog(con,"对不起,您输入的账号或者密码错误!请重新输入","登陆失败",JOptionPane.WARNING_MESSAGE);
                    return;
                }else{
                    f.setVisible(false);
                    LoginInfo.userId = userBean.getId();
                    LoginInfo.name = userBean.getName();
                    LoginInfo.isAdmin = userBean.getAdmin();
                    launch(SwingSet3.class, args);
                }
            }
        });
        pan4.add(b_log);
        JButton b_exit=new JButton("退出");
        pan4.add(b_exit);
        con.add(pan4);
        //登陆和退出这两个按钮放在第四个版面上
        JPanel pan5 = new JPanel();
        con.add(pan5);
        JPanel pan6 = new JPanel();
        con.add(pan6);
        JPanel pan7 = new JPanel();
        con.add(pan7);
        //空白版面
        f.setSize(400,300);
        f.setLocation(200,200);
        f.setVisible(true);
    }

    public static boolean runningOnMac() {
        return System.getProperty("os.name").equals("Mac OS X");
    }

    public static boolean usingNimbus() {
        return UIManager.getLookAndFeel().getName().equals("Nimbus");
    }

    private static List<String> readDemoClassNames(Reader reader) throws IOException {
        List<String> demoClassNames = new ArrayList<String>();

        BufferedReader breader = new BufferedReader(reader);
        String line;
        while((line = breader.readLine()) != null) {
            demoClassNames.add(line);
        }
        breader.close();
        return demoClassNames;
    }
    // End of statics

    private ResourceMap resourceMap;

    // Application models
    private String demoListTitle;
    private List<Demo> demoList;
    private PropertyChangeListener demoPropertyChangeListener;
    private Map<String, DemoPanel> runningDemoCache;
    private Demo currentDemo;

    // GUI components
    private JPanel mainPanel;
    private DemoSelectorPanel demoSelectorPanel;
    private AnimatingSplitPane demoSplitPane;
    private JPanel demoContainer;
    private JComponent currentDemoPanel;
    private JComponent demoPlaceholder;
    private JPanel codeContainer;
    private CodeViewer codeViewer;
    private JCheckBoxMenuItem sourceCodeCheckboxItem;
    private ButtonGroup lookAndFeelRadioGroup;

    private JPopupMenu popup;

    // Properties
    private String lookAndFeel;
    private boolean sourceVisible = true;

    @Override
    protected void initialize(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        	BeautyEyeLNFHelper.launchBeautyEyeLNF();
        	UIManager.put("RootPane.setupButtonVisible", false);

        } catch (Exception ex) {

        }
        resourceMap = getContext().getResourceMap();

        title = resourceMap.getString("mainFrame.title");
        runningDemoCache = new HashMap<String, DemoPanel>();
        setDemoList(resourceMap.getString("demos.title"), getDemoClassNames(args));

        IntroPanel intro = new IntroPanel();
        setDemoPlaceholder(intro);

    }

    private List<String>getDemoClassNames(String args[]) {
        List<String> demoList = new ArrayList<String>();
        boolean augment = false;
        Exception exception = null;

        // First look for any demo list files specified on the command-line
        for(String arg : args) {
            if (arg.equals("-a") || arg.equals("-augment")) {
                augment = true;
            } else {
                // process argument as filename containing names of demo classes
                try {
                    demoList.addAll(readDemoClassNames(new FileReader(arg) /*filename*/));
                } catch (IOException ex) {
                    exception = ex;
                    logger.log(Level.WARNING, "unable to read demo class names from file: "+arg, ex);
                }
            }
        }

        if (demoList.isEmpty() || augment) {
            // Load default Demos
            try {
                demoList.addAll(readDemoClassNames(new InputStreamReader(getClass().getResourceAsStream(DEMO_LIST_FILE))));
            } catch (IOException ex) {
                exception = ex;
                logger.log(Level.WARNING, "unable to read resource: " + DEMO_LIST_FILE, ex);
            }
        }

        if (demoList.isEmpty()) {
            displayErrorMessage(resourceMap.getString("error.noDemosLoaded"),
                    exception);
        }
        return demoList;

    }

    public void setDemoList(String demoListTitle, List<String> demoClassNamesList) {
        List<Demo> demoList = new ArrayList<Demo>();
        for(String demoClassName: demoClassNamesList) {
            Demo demo = createDemo(demoClassName);
            if (demo != null) {
                demoList.add(demo);
            }
        }
        this.demoListTitle = demoListTitle;
        this.demoList = demoList;
    }

    /**
     */
    protected Demo createDemo(String demoClassName) {
        Class<?> demoClass = null;
        Demo demo = null;
        try {
            demoClass = Class.forName(demoClassName);
        } catch (Exception e) {
            logger.log(Level.WARNING, "demo class not found:"+ demoClassName);
        }
        if (demoClass != null) {
            // Wrap Demo
            demo = new Demo(demoClass);
            demo.swingSet3 = this;
            demo.addPropertyChangeListener(getDemoPropertyChangeListener());
        }

        return demo;
    }

    protected PropertyChangeListener getDemoPropertyChangeListener() {
        if (demoPropertyChangeListener == null) {
            demoPropertyChangeListener = new DemoPropertyChangeListener();
        }
        return demoPropertyChangeListener;
    }

    @Override
    protected void startup() {
        UIManager.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getPropertyName().equals("lookAndFeel")) {
                    configureDefaults();
                }
            }
        });

        configureDefaults();

        View view = getMainView();
        view.setComponent(createMainPanel());
        view.setMenuBar(createMenuBar());




        applyDefaults();

        // application framework should handle this
        getMainFrame().setIconImage(resourceMap.getImageIcon("Application.icon").getImage());
        getMainFrame().setResizable(false);
        show(view);
    }

    private void configureDefaults() {

        // Color palette algorithm courtesy of Jasper Potts
        Color controlColor = UIManager.getColor("control");

	UIManager.put(CONTROL_VERY_LIGHT_SHADOW_KEY,
                Utilities.deriveColorHSB(controlColor, 0, 0, -0.02f));
        UIManager.put(CONTROL_LIGHT_SHADOW_KEY,
                Utilities.deriveColorHSB(controlColor, 0, 0, -0.06f));
        UIManager.put(CONTROL_MID_SHADOW_KEY,
                Utilities.deriveColorHSB(controlColor, 0, 0, -0.16f));
        UIManager.put(CONTROL_VERY_DARK_SHADOW_KEY,
                Utilities.deriveColorHSB(controlColor, 0, 0, -0.5f));
        UIManager.put(CONTROL_DARK_SHADOW_KEY,
                Utilities.deriveColorHSB(controlColor, 0, 0, -0.32f));

        // Calculate gradient colors for title panels
        Color titleColor = UIManager.getColor(usingNimbus()? "nimbusBase" : "activeCaption");

        // Some LAFs (e.g. GTK) don't contain "activeCaption"
        if (titleColor == null) {
            titleColor = controlColor;
        }
        float hsb[] = Color.RGBtoHSB(
                titleColor.getRed(), titleColor.getGreen(), titleColor.getBlue(), null);
        UIManager.put(TITLE_GRADIENT_COLOR1_KEY,
                Color.getHSBColor(hsb[0]-.013f, .15f, .85f));
        UIManager.put(TITLE_GRADIENT_COLOR2_KEY,
                Color.getHSBColor(hsb[0]-.005f, .24f, .80f));
        UIManager.put(TITLE_FOREGROUND_KEY,
                Color.getHSBColor(hsb[0], .54f, .40f));

        // Calculate highlight color for code pane
        UIManager.put(CODE_HIGHLIGHT_KEY,
                Color.getHSBColor(hsb[0]-.005f, .20f, .95f));

        Font labelFont = UIManager.getFont("Label.font");
        UIManager.put(TITLE_FONT_KEY, labelFont.deriveFont(Font.BOLD, labelFont.getSize()+4f));

        Color panelColor = UIManager.getColor("Panel.background");
        UIManager.put(SUB_PANEL_BACKGROUND_KEY,
                Utilities.deriveColorHSB(panelColor, 0, 0, -.06f));

        applyDefaults();

    }

    protected void applyDefaults() {
        if (codeViewer != null) {
            codeViewer.setHighlightColor(UIManager.getColor(CODE_HIGHLIGHT_KEY));
        }
    }

    protected JComponent createMainPanel() {

        // Create main panel with demo selection on left and demo/source on right
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create demo selector panel on left
        demoSelectorPanel = new DemoSelectorPanel(demoListTitle, demoList);
        demoSelectorPanel.setPreferredSize(new Dimension(DEMO_SELECTOR_WIDTH, MAIN_FRAME_HEIGHT));
        demoSelectorPanel.addPropertyChangeListener(new DemoSelectionListener());
        mainPanel.add(demoSelectorPanel, BorderLayout.WEST);

        // Create splitpane on right to hold demo and source code
        demoSplitPane = new AnimatingSplitPane(JSplitPane.VERTICAL_SPLIT);
        demoSplitPane.setBorder(EMPTY_BORDER);
        mainPanel.add(demoSplitPane, BorderLayout.CENTER);

        // Create panel to contain currently running demo
        demoContainer = new JPanel();
        demoContainer.setLayout(new BorderLayout());
        demoContainer.setBorder(PANEL_BORDER);
        demoContainer.setPreferredSize(new Dimension(DEMO_PANEL_WIDTH, DEMO_PANEL_HEIGHT));
        demoSplitPane.setTopComponent(demoContainer);

        currentDemoPanel = demoPlaceholder;
        demoContainer.add(demoPlaceholder, BorderLayout.CENTER);

        // Create collapsible source code pane

        codeViewer = new CodeViewer();
        codeContainer = new JPanel(new BorderLayout());
        codeContainer.add(codeViewer);
        codeContainer.setBorder(PANEL_BORDER);
        codeContainer.setMinimumSize(new Dimension(0,0));
        //不需要代码显示
        //不需要代码显示
        //不需要代码显示
        //不需要代码显示
        //不需要代码显示
        /*demoSplitPane.setBottomComponent(codeContainer);*/

        addPropertyChangeListener(new SwingSetPropertyListener());

        // Create shareable popup menu for demo actions
        popup = new JPopupMenu();
        popup.add(new EditPropertiesAction());
        popup.add(new ViewCodeSnippetAction());

        return mainPanel;
    }

    protected JMenuBar createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        menubar.setName("menubar");

        // File menu
        JMenu fileMenu = new JMenu();
        fileMenu.setName("file");
        menubar.add(fileMenu);

        // File -> Quit
        if (!runningOnMac()) {
            JMenuItem quitItem = new JMenuItem();
            quitItem.setName("quit");
            quitItem.setAction(getAction("quit"));
            fileMenu.add(quitItem);
        }

        // View menu
        JMenu viewMenu = new JMenu();
        viewMenu.setName("view");
        // View -> Look and Feel
        viewMenu.add(createLookAndFeelMenu());
        // View -> Source Code Visible
        sourceCodeCheckboxItem = new JCheckBoxMenuItem();
        sourceCodeCheckboxItem.setSelected(isSourceCodeVisible());
        sourceCodeCheckboxItem.setName("sourceCodeCheckboxItem");
        sourceCodeCheckboxItem.addChangeListener(new SourceVisibilityChangeListener());
        viewMenu.add(sourceCodeCheckboxItem);
        menubar.add(viewMenu);

        return menubar;
    }

    protected JMenu createLookAndFeelMenu() {
        JMenu menu = new JMenu();
        menu.setName("lookAndFeel");

        // Look for toolkit look and feels first
        UIManager.LookAndFeelInfo lookAndFeelInfos[] = UIManager.getInstalledLookAndFeels();
        lookAndFeel = UIManager.getLookAndFeel().getClass().getName();
        lookAndFeelRadioGroup = new ButtonGroup();
        for(UIManager.LookAndFeelInfo lafInfo: lookAndFeelInfos) {
            menu.add(createLookAndFeelItem(lafInfo.getName(), lafInfo.getClassName()));
        }
        // Now load any look and feels defined externally as service via java.util.ServiceLoader
        LOOK_AND_FEEL_LOADER.iterator();
        for (LookAndFeel laf : LOOK_AND_FEEL_LOADER) {
            menu.add(createLookAndFeelItem(laf.getName(), laf.getClass().getName()));
        }

        return menu;
    }

    protected JRadioButtonMenuItem createLookAndFeelItem(String lafName, String lafClassName) {
        JRadioButtonMenuItem lafItem = new JRadioButtonMenuItem();

        lafItem.setSelected(lafClassName.equals(lookAndFeel));
        lafItem.setHideActionText(true);
        lafItem.setAction(getAction("setLookAndFeel"));
        lafItem.setText(lafName);
        lafItem.setActionCommand(lafClassName);
        lookAndFeelRadioGroup.add(lafItem);

        return lafItem;
    }

    private javax.swing.Action getAction(String actionName) {
        return getContext().getActionMap().get(actionName);
    }

    // For displaying error messages to user
    protected void displayErrorMessage(String message, Exception ex) {
        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message);
        messagePanel.add(label);
        if (ex != null) {
            RoundedPanel panel = new RoundedPanel(new BorderLayout());
            panel.setBorder(new RoundedBorder());

            // remind(aim): provide way to allow user to see exception only if desired
            StringWriter writer = new StringWriter();
            ex.printStackTrace(new PrintWriter(writer));
            JTextArea exceptionText = new JTextArea();
            exceptionText.setText("Cause of error:\n" +
                    writer.getBuffer().toString());
            exceptionText.setBorder(new RoundedBorder());
            exceptionText.setOpaque(false);
            exceptionText.setBackground(
                    Utilities.deriveColorHSB(UIManager.getColor("Panel.background"),
                    0, 0, -.2f));
            JScrollPane scrollpane = new JScrollPane(exceptionText);
            scrollpane.setBorder(EMPTY_BORDER);
            scrollpane.setPreferredSize(new Dimension(600,240));
            panel.add(scrollpane);
            messagePanel.add(panel, BorderLayout.SOUTH);
        }
        JOptionPane.showMessageDialog(getMainFrame(), messagePanel,
                resourceMap.getString("error.title"),
                JOptionPane.ERROR_MESSAGE);

    }

    public void setDemoPlaceholder(JComponent demoPlaceholder) {
        JComponent oldDemoPlaceholder = this.demoPlaceholder;
        this.demoPlaceholder = demoPlaceholder;
        firePropertyChange("demoPlaceholder", oldDemoPlaceholder, demoPlaceholder);
    }

    public JComponent getDemoPlaceholder() {
        return demoPlaceholder;
    }

    public void setCurrentDemo(Demo demo) {
        if (currentDemo == demo) {
            return; // already there
        }
        Demo oldCurrentDemo = currentDemo;
        currentDemo = demo;
        if (demo != null) {
            DemoPanel demoPanel = runningDemoCache.get(demo.getName());
            if (demoPanel == null || demo.getDemoComponent() == null) {
                demo.startInitializing();
                demoPanel = new DemoPanel(demo);
                demoPanel.setPreferredSize(currentDemoPanel.getPreferredSize());
                runningDemoCache.put(demo.getName(), demoPanel);
            }
            demoContainer.remove(currentDemoPanel);
            currentDemoPanel = demoPanel;
            demoContainer.add(currentDemoPanel, BorderLayout.CENTER);
            AuthUtils.CheckAuth(currentDemoPanel,currentDemoPanel,LoginInfo.userId,authMap.get(demo.getName()));
            demoContainer.revalidate();
            demoContainer.repaint();
            getMainFrame().validate();
        }

        if (currentDemo == null) {
            demoContainer.add(demoPlaceholder, BorderLayout.CENTER);
        }

        if (isSourceCodeVisible()) {
                codeViewer.setSourceFiles(currentDemo != null?
                                          currentDemo.getSourceFiles() : null);
        }

        firePropertyChange("currentDemo", oldCurrentDemo, demo);
    }


    public Demo getCurrentDemo() {
        return currentDemo;
    }

    public void setLookAndFeel(String lookAndFeel) throws ClassNotFoundException,
        InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        String oldLookAndFeel = this.lookAndFeel;

	if (oldLookAndFeel != lookAndFeel) {
            UIManager.setLookAndFeel(lookAndFeel);
            this.lookAndFeel = lookAndFeel;
            updateLookAndFeel();
            firePropertyChange("lookAndFeel", oldLookAndFeel, lookAndFeel);
	}
    }

    @Action
    public void setLookAndFeel() {
        ButtonModel model = lookAndFeelRadioGroup.getSelection();
        String lookAndFeelName = model.getActionCommand();
        try {
            setLookAndFeel(lookAndFeelName);
        } catch (Exception ex) {
            displayErrorMessage(resourceMap.getString("error.unableToChangeLookAndFeel") +
                    "to "+lookAndFeelName, ex);
        }
    }

    public String getLookAndFeel() {
        return lookAndFeel;
    }

    public void setSourceCodeVisible(boolean sourceVisible) {
        boolean oldSourceVisible = this.sourceVisible;
        this.sourceVisible = sourceVisible;
        firePropertyChange("sourceCodeVisible", oldSourceVisible, sourceVisible);
    }

    public boolean isSourceCodeVisible() {
        return sourceVisible;
    }

    private void updateLookAndFeel() {
        Window windows[] = Frame.getWindows();

        for(Window window : windows) {
            SwingUtilities.updateComponentTreeUI(window);
            for(DemoPanel demoPanel : runningDemoCache.values()) {
                SwingUtilities.updateComponentTreeUI(demoPanel);
            }
        }
    }

    // hook used to detect if any components in the demo have registered a
    // code snippet key for the their creation code inside the source
    private void registerPopups(Component component) {

        if (component instanceof Container) {
            Component children[] = ((Container)component).getComponents();
            for(Component child: children) {
                if (child instanceof JComponent) {
                    registerPopups(child);
                }
            }
        }
        if (component instanceof JComponent) {
            JComponent jcomponent = (JComponent)component;
            String snippetKey = (String)jcomponent.getClientProperty("snippetKey");
            if (snippetKey != null) {
                jcomponent.setComponentPopupMenu(popup);
            }
        }
    }

    private class DemoSelectionListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent event) {
            if (event.getPropertyName().equals("selectedDemo")) {

                setCurrentDemo((Demo)event.getNewValue());
            }
        }
    }


    // registered on Demo to detect when the demo component is instantiated.
    // we need this because when we embed the demo inside an HTML description pane,
    // we don't have control over the demo component's instantiation
    private class DemoPropertyChangeListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent e) {
            String propertyName = e.getPropertyName();
            if (propertyName.equals("demoComponent")) {
                Demo demo = (Demo)e.getSource();
                JComponent demoComponent = (JComponent)e.getNewValue();
                if (demoComponent != null) {
                    demoComponent.putClientProperty("swingset3.demo", demo);
                    demoComponent.addHierarchyListener(new DemoVisibilityListener());
                    registerPopups(demoComponent);
                }
            }
        }
    }

    private class DemoVisibilityListener implements HierarchyListener {
        public void hierarchyChanged(HierarchyEvent event) {
            if ((event.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) > 0) {
                JComponent component = (JComponent)event.getComponent();
                final Demo demo = (Demo)component.getClientProperty("swingset3.demo");
                if (!component.isShowing()) {
                    demo.stop();
                } else {
                    demoContainer.revalidate();
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            demo.start();
                        }
                    });
                }
            }
        }
    }
    // activated when user selects/unselects checkbox menu item
    private class SourceVisibilityChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent event) {
            setSourceCodeVisible(sourceCodeCheckboxItem.isSelected());
        }
    }

    private class SwingSetPropertyListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent event) {
            String propertyName = event.getPropertyName();
            if (propertyName.equals("sourceCodeVisible")) {
                boolean sourceVisible = ((Boolean)event.getNewValue()).booleanValue();
                if (sourceVisible) {
                    // update codeViewer in case current demo changed while
                    // source was invisible
                    codeViewer.setSourceFiles(currentDemo != null?
                                                  currentDemo.getSourceFiles() : null);
                }
                demoSplitPane.setExpanded(!sourceVisible);
                sourceCodeCheckboxItem.setSelected(sourceVisible);
            }
        }
    }

    private class ViewCodeSnippetAction extends AbstractAction {
        public ViewCodeSnippetAction() {
            super("View Source Code");
        }
        public void actionPerformed(ActionEvent actionEvent) {
            Container popup = (JComponent)actionEvent.getSource();
            while(popup != null && !(popup instanceof JPopupMenu)) {
                popup = popup.getParent();
            }
            JComponent target = (JComponent)((JPopupMenu)popup).getInvoker();
            setSourceCodeVisible(true);

            String snippetKey = (String)target.getClientProperty("snippetKey");
            if (snippetKey != null) {
                codeViewer.highlightSnippetSet(snippetKey);
            } else {
                logger.log(Level.WARNING, "can't find source code snippet for:" + snippetKey);
            }
        }
    }

    private static class EditPropertiesAction extends AbstractAction {
        public EditPropertiesAction() {
            super("Edit Properties");
        }
        public boolean isEnabled() {
            return false;
        }
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    public List<Demo> getDemoList() {
        return demoList;
    }

    public void setDemoList(List<Demo> demoList) {
        this.demoList = demoList;
    }

    public String getDemoListTitle() {
        return demoListTitle;
    }

    public void setDemoListTitle(String demoListTitle) {
        this.demoListTitle = demoListTitle;
    }

    public static void initAuthMap(){
        authMap.put("车辆进场","AUTH_CAR_IN");
        authMap.put("车辆出场","AUTH_CAR_OUT");
        authMap.put("停车场管理","AUTH_PARKINGLOT_MANAGEMENT");
        authMap.put("车辆进出记录","AUTH_CAR_LOG");
        authMap.put("停车场反向寻车","AUTH_CAR_SEARCH");

        authMap.put("IC卡授权发行","AUTH_CARD_RELEASE");
        authMap.put("IC卡账户查询","AUTH_CARD_SEARCH");
        authMap.put("IC卡消费记录查询","AUTH_CARD_LOG");
        authMap.put("IC卡充值/续期","AUTH_CARD_RECHARGE");
        authMap.put("账户挂失/注销/解挂","AUTH_CARD_STATUS");

        authMap.put("权限管理","AUTH_ACCESS");
        authMap.put("角色管理","AUTH_ROLE");
        authMap.put("用户管理","AUTH_USER");
        authMap.put("待我审批的权限申请","AUTH_REQUEST");
        authMap.put("我的权限审批历史","AUTH_LOG");

        authMap.put("数据分析","AUTH_DATA");
    }
}
