package ui;

import model.Employee;
import model.EmployeeDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


// Sourced from:
// https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
// https://www.youtube.com/watch?v=5o3fMLPY7qY
// CPSC 210 Alarm System
// https://stackoverflow.com/questions/8852560/how-to-make-popup-window-in-java
public class TimesheetAppUI extends JFrame {


    private EmployeeDatabase ed;
    private JFrame frame;
    private JPanel panel;

    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public TimesheetAppUI() {
        ed = new EmployeeDatabase();
        frame = new JFrame();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 500, 300, 500));
        panel.setLayout(new GridLayout(0, 2));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Timesheet Entry Application");
        frame.pack();
        frame.setVisible(true);
        addButtonPanel();
        // center on screen?
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
        frame.setLayout(new GridLayout(4, 2));

        frame.add(new JButton(new AddEmployeeAction()));
        frame.add(new JButton(new RemoveEmployeeAction()));
        frame.add(new JButton(new ChangeEmployeeNameAction()));
//        frame.add(new JButton(new AddEmployeeHourAction()));
//        frame.add(new JButton(new UpdateEmployeeHourAction()));
//        frame.add(new JButton(new GetHourAction()));


//        frame.add(new JButton(new ChangeDateAction()));
//        frame.add(new JButton(new ResetAction()));
//        frame.add(new JButton(new DisplayTimesheetAction()));
//        frame.add(new JButton(new QuitAndSaveAction()));
//        frame.add(new JButton(new LoadAction()));


//        buttonPanel.add(new JButton(new PrintLogAction()));
//        buttonPanel.add(createPrintCombo());

        panel.add(buttonPanel, BorderLayout.WEST);
    }

    //    /**
//     * Adds menu bar.
//     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu systemMenu = new JMenu("System");
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
        addMenuItem(employeeMenu, new AddEmployeeAction(),
                KeyStroke.getKeyStroke("control A"));
        addMenuItem(employeeMenu, new RemoveEmployeeAction(),
                KeyStroke.getKeyStroke("control R"));
        addMenuItem(employeeMenu, new ChangeEmployeeNameAction(),
                KeyStroke.getKeyStroke("control N"));
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
     * Adds a new employee to the database
     */
    private class AddEmployeeAction extends AbstractAction {

        AddEmployeeAction() {
            super("Add Employee");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = JOptionPane.showInputDialog("What is your name?", null);
            Employee e = new Employee(name);
            ed.addEmployee(e);
        }
    }

    /**
     * Removes an employee from the database
     */
    private class RemoveEmployeeAction extends AbstractAction {

        RemoveEmployeeAction() {
            super("Remove Employee");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select an employee:\"",
                    "Remove Employee",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    ed.employeeNames().toArray(),
                    null);
            ed.removeEmployee(name);
        }
    }

    /**
     * Represents the action to be taken when the user wants to add a new
     * sensor to the system.
     */
    private class ChangeEmployeeNameAction extends AbstractAction {

        ChangeEmployeeNameAction() {
            super("Change Employee Name");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select an employee:\"",
                    "Change Employee Name",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    ed.employeeNames().toArray(),
                    null);
            String newName = JOptionPane.showInputDialog("Change " + name + " to:", null);
            ed.findEmployee(name).changeName(newName);
        }
    }


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
