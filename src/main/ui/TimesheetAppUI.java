package ui;

import model.Employee;
import model.EmployeeDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimesheetAppUI extends JFrame {


    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private EmployeeDatabase ed;
    //private JComboBox<String> printCombo;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private JFrame frame;
    private JPanel panel;

    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public TimesheetAppUI() {
        ed = new EmployeeDatabase();
        frame = new JFrame();
        panel = new JPanel();
//        JButton button = new JButton("Add Employee");
        panel.setBorder(BorderFactory.createEmptyBorder(300,500,300,500));
        panel.setLayout(new GridLayout(0,1));
//        panel.add(button);

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Timesheet Entry Application");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();
        addMenu();
//        addKeyPad();
//        addAlarmDisplayPanel();

//        Remote r = new Remote(ac);
//        addRemote(r);

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

//    /**
//     * Helper to set up visual alarm status window
//     */
//    private void addAlarmDisplayPanel() {
//        AlarmUI alarmUI = new AlarmUI();
//        ac.addAlarmObserver(alarmUI);
//        controlPanel.add(alarmUI, BorderLayout.NORTH);
//    }

    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(new JButton(new AddEmployeeAction()));
//        buttonPanel.add(new JButton(new RemoveCodeAction()));
//        buttonPanel.add(new JButton(new ArmAction()));
//        buttonPanel.add(new JButton(new DisarmAction()));
//        buttonPanel.add(new JButton(new AddSensorAction()));
//        buttonPanel.add(new JButton(new ClearLogAction()));
//        buttonPanel.add(new JButton(new PrintLogAction()));
//        buttonPanel.add(createPrintCombo());

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    /**
     * Adds menu bar.
     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu systemMenu = new JMenu("System");
//        systemMenu.setMnemonic('y');
//        addMenuItem(systemMenu, new ChangeDateAction(),
//                KeyStroke.getKeyStroke("control D"));
//        addMenuItem(systemMenu, new ResetAction(),
//                KeyStroke.getKeyStroke("control R"));
//        addMenuItem(systemMenu, new DisplayTimesheetAction(),
//                KeyStroke.getKeyStroke("control P"));
//        addMenuItem(systemMenu, new QuitAndSaveAction(),
//                KeyStroke.getKeyStroke("control Q"));
        menuBar.add(systemMenu);

        JMenu employeeMenu = new JMenu("Employee");
//        systemMenu.setMnemonic('y');
        addMenuItem(employeeMenu, new AddEmployeeAction(),
                KeyStroke.getKeyStroke("control A"));
//        addMenuItem(employeeMenu, new RemoveEmployeeAction(),
//                KeyStroke.getKeyStroke("control R"));
//        addMenuItem(employeeMenu, new ChangeEmployeeNameAction(),
//                KeyStroke.getKeyStroke("control N"));
//        addMenuItem(employeeMenu, new AddEmployeeHourAction(),
//                KeyStroke.getKeyStroke("control H"));
//        addMenuItem(employeeMenu, new UpdateEmployeeHourAction(),
//                KeyStroke.getKeyStroke("control U"));
//        addMenuItem(employeeMenu, new GetHourAction(),
//                KeyStroke.getKeyStroke("control G"));
        menuBar.add(employeeMenu);

        setJMenuBar(menuBar);
    }


    /**
     * Adds an item with given handler to the given menu
     *
     * @param theMenu     menu to which new item is added
     * @param action      handler for new menu item
     * @param accelerator keystroke accelerator for this menu item
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }


    /**
     * Helper to create print options combo box
     *
     * @return the combo box
     */
//    private JComboBox<String> createPrintCombo() {
//        printCombo = new JComboBox<String>();
//        printCombo.addItem(FILE_DESCRIPTOR);
//        printCombo.addItem(SCREEN_DESCRIPTOR);
//        return printCombo;
//    }


    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    /**
     * Helper to add new employee
     */
    private class AddEmployeeAction extends AbstractAction {

        AddEmployeeAction() {
            super("Add Employee");
        }

        //when click want to open popup
        //https://stackoverflow.com/questions/8852560/how-to-make-popup-window-in-java
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = JOptionPane.showInputDialog("What is your name?", null);
            Employee e = new Employee(name);
            ed.addEmployee(e);
        }
    }

    /**
     * Represents the action to be taken when the user wants to add a new
     * sensor to the system.
     */
//    private class AddSensorAction extends AbstractAction {
//
//        AddSensorAction() {
//            super("Add Sensor");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            String sensorLoc = JOptionPane.showInputDialog(null,
//                    "Sensor location?",
//                    "Enter sensor location",
//                    JOptionPane.QUESTION_MESSAGE);
//            try {
//                if (sensorLoc != null) {
//                    Sensor s = new Sensor(sensorLoc, ac);
//                    desktop.add(new SensorUI(s, AlarmControllerUI.this));
//                }
//            } catch (DuplicateSensorException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    /**
//     * Represents the action to be taken when the user wants to remove
//     * a code from the system.
//     */
//    private class RemoveCodeAction extends AbstractAction {
//
//        RemoveCodeAction() {
//            super("Remove Code");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            AlarmCode alarmCode = new AlarmCode(kp.getCode());
//            kp.clearCode();
//            try {
//                ac.removeCode(alarmCode);
//            } catch (NotValidCodeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            } catch (CodeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            } catch (LastCodeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    /**
//     * Represents the action to be taken when the user wants to arm
//     * the system.
//     */
//    private class ArmAction extends AbstractAction {
//
//        ArmAction() {
//            super("Arm System");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            AlarmCode alarmCode = new AlarmCode(kp.getCode());
//            kp.clearCode();
//            try {
//                ac.arm(alarmCode);
//            } catch (SystemNotReadyException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            } catch (CodeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    /**
//     * Represents the action to be taken when the user wants to
//     * disarm the system.
//     */
//    private class DisarmAction extends AbstractAction {
//
//        DisarmAction() {
//            super("Disarm System");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            AlarmCode alarmCode = new AlarmCode(kp.getCode());
//            kp.clearCode();
//
//            try {
//                ac.disarm(alarmCode);
//            } catch (CodeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    /**
//     * Represents the action to be taken when the user wants to
//     * print the event log.
//     */
//    private class PrintLogAction extends AbstractAction {
//        PrintLogAction() {
//            super("Print log to...");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            String selected = (String) printCombo.getSelectedItem();
//            LogPrinter lp;
//            try {
//                if (selected.equals(FILE_DESCRIPTOR))
//                    lp = new FilePrinter();
//                else {
//                    lp = new ScreenPrinter(AlarmControllerUI.this);
//                    desktop.add((ScreenPrinter) lp);
//                }
//
//                lp.printLog(EventLog.getInstance());
//            } catch (LogException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    /**
//     * Represents the action to be taken when the user wants to
//     * clear the event log.
//     */
//    private class ClearLogAction extends AbstractAction {
//        ClearLogAction() {
//            super("Clear log");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            EventLog.getInstance().clear();
//        }
//    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            TimesheetAppUI.this.requestFocusInWindow();
        }
    }

    // starts the application
    public static void main(String[] args) {
        new TimesheetAppUI();
    }
}
