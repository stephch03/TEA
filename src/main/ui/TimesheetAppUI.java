package ui;

import model.Employee;
import model.EmployeeDatabase;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Sourced from:
// https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
// https://www.youtube.com/watch?v=5o3fMLPY7qY
// CPSC 210 Alarm System
// https://stackoverflow.com/questions/8852560/how-to-make-popup-window-in-java
public class TimesheetAppUI extends JFrame {

    private static final String JSON_STORE = "./data/employeeDatabase.json";
    private EmployeeDatabase ed;
    private JFrame frame;
    private JPanel panel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public TimesheetAppUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        startAndLoad();
        quitAndSave();

        // center on screen?
    }

    private void quitAndSave() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent w) {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(frame,
                        "Would you like to save your current employee database?",
                        "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                        null);
                if (n == JOptionPane.YES_OPTION) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(ed);
                        jsonWriter.close();
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(frame, "Sorry, your timesheet could not be loaded",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }


    private void startAndLoad() {
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(frame,
                "Would you like to load your previous employee database?",
                "Load",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);
        if (n == JOptionPane.YES_OPTION) {
            try {
                ed = jsonReader.read();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame,
                        "Sorry, your timesheet could not be loaded",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//    final JOptionPane optionPane = new JOptionPane(
//            "The only way to close this dialog is by\n"
//                    + "pressing one of the following buttons.\n"
//                    + "Do you understand?",
//            JOptionPane.QUESTION_MESSAGE,
//            JOptionPane.YES_NO_OPTION);
//
//    if (optionPane =)


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
        frame.setLayout(new GridLayout(10, 1));

        frame.add(new JButton(new AddEmployeeAction()));
        frame.add(new JButton(new RemoveEmployeeAction()));
        frame.add(new JButton(new ChangeEmployeeNameAction()));
        frame.add(new JButton(new AddEmployeeHourAction()));
        frame.add(new JButton(new UpdateEmployeeHourAction()));
        frame.add(new JButton(new GetHourAction()));

// TODO
//        frame.add(new JButton(new ChangeDateAction()));
//        frame.add(new JButton(new ResetAction()));
//        frame.add(new JButton(new DisplayTimesheetAction()));
//        frame.add(new JButton(new QuitAndSaveAction()));
//        frame.add(new JButton(new LoadAction()));


        panel.add(buttonPanel, BorderLayout.WEST);
    }


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
            String name = JOptionPane.showInputDialog("Enter the FirstName LastName of the employee:",
                    null);
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
            if (ed.getNumEmployees() == 0) {
                JOptionPane.showMessageDialog(frame,
                        "There are no employees in the database",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String name = (String) JOptionPane.showInputDialog(
                        frame,
                        "Select an employee:",
                        "Remove Employee",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        ed.employeeNames().toArray(),
                        null);
                ed.removeEmployee(name);
            }
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
            if (ed.getNumEmployees() == 0) {
                JOptionPane.showMessageDialog(frame,
                        "There are no employees in the database",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String name = (String) JOptionPane.showInputDialog(frame, "Select an employee:",
                        "Change Employee Name", JOptionPane.PLAIN_MESSAGE, null, ed.employeeNames().toArray(),
                        null);
                if (name != null) {
                    String newName = JOptionPane.showInputDialog("Change " + name + " to:", null);
                    if (newName != null) {
                        ed.findEmployee(name).changeName(newName);
                    }
                }
            }
        }
    }


    private class AddEmployeeHourAction extends AbstractAction {

        AddEmployeeHourAction() {
            super("Add Employee Hour");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (ed.getNumEmployees() == 0) {
                JOptionPane.showMessageDialog(frame,
                        "There are no employees in the database",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String name = (String) JOptionPane.showInputDialog(frame, "Select an employee:",
                        "Add Employee Hour", JOptionPane.PLAIN_MESSAGE, null, ed.employeeNames().toArray(),
                        null);
                if (name != null) {
                    Object[] hourOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
                    String hour = (String) JOptionPane.showInputDialog(frame, "Select the number of hours that "
                                    + name + " worked today:", "Add Employee Hour", JOptionPane.PLAIN_MESSAGE,
                            null, hourOptions, null);
                    ed.findEmployee(name).inputHours(Integer.parseInt(hour));
                }
            }
        }
    }

    private class UpdateEmployeeHourAction extends AbstractAction {

        UpdateEmployeeHourAction() {
            super("Update Employee Hour");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (ed.getNumEmployees() == 0) {
                JOptionPane.showMessageDialog(frame, "There are no employees in the database", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String name = (String) JOptionPane.showInputDialog(frame, "Select an employee:",
                        "Update Employee Hour", JOptionPane.PLAIN_MESSAGE, null, ed.employeeNames().toArray(),
                        null);
                if (ed.findEmployee(name).hoursSize() == 0 && name != null) {
                    JOptionPane.showMessageDialog(frame,
                            name + " has not worked any hours yet.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int day = (int) JOptionPane.showInputDialog(frame, "Select a day:",
                            "Update Employee Hour", JOptionPane.PLAIN_MESSAGE, null,
                            employeeDates(name).toArray(), null);

                    Object[] hourOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
                    String hour = (String) JOptionPane.showInputDialog(frame, "Select the number of hours that "
                                    + name + " worked today:", "Add Employee Hour", JOptionPane.PLAIN_MESSAGE,
                            null, hourOptions, null);
                    ed.findEmployee(name).updateHours(day, Integer.parseInt(hour));
                }
            }
        }

        private List<Integer> employeeDates(String name) {
            List<Integer> dates = new ArrayList<>();

            for (int i = 0; i < ed.findEmployee(name).hoursSize(); i++) {
                dates.add(i + 1);
            }

            return dates;
        }
    }


    private class GetHourAction extends AbstractAction {

        GetHourAction() {
            super("Get Employee Hours");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (ed.getNumEmployees() == 0) {
                JOptionPane.showMessageDialog(frame, "There are no employees in the database", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String name = (String) JOptionPane.showInputDialog(
                        frame,
                        "Select an employee:",
                        "Get Employee Total Hours",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        ed.employeeNames().toArray(),
                        null);

                if (ed.findEmployee(name).hoursSize() == 0 && name != null) {
                    JOptionPane.showMessageDialog(frame,
                            name + " has not worked any hours yet.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            getHoursWorkedByDay(name) + "\n" + name + "'s total hours worked: "
                                    + ed.findEmployee(name).getHoursWorked());
                }
            }
        }

        private String getHoursWorkedByDay(String name) {
            List<String> hoursList = new ArrayList<>();
            for (int i = 0; i < ed.findEmployee(name).hoursSize(); i++) {
                hoursList.add("Day " + (i + 1) + ": " + ed.findEmployee(name).getHours().get(i) + " hours");
            }

            return String.join("\n", hoursList);
        }
    }


//    private class ChangeDateAction extends AbstractAction {
//
//        ChangeDateAction() {
//            super("Change Timesheet Date");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            String name = (String) JOptionPane.showInputDialog(
//                    frame,
//                    "Select an employee:",
//                    "Get Employee Total Hours",
//                    JOptionPane.PLAIN_MESSAGE,
//                    null,
//                    ed.employeeNames().toArray(),
//                    null);
//
//            if (ed.findEmployee(name).hoursSize() == 0 && name != null) {
//                JOptionPane.showMessageDialog(frame,
//                        name + " has not worked any hours yet.",
//                        "Error",
//                        JOptionPane.ERROR_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(frame,
//                        getHoursWorkedByDay(name) + "\n" + name + "'s total hours worked: "
//                                + ed.findEmployee(name).getHoursWorked());
//            }
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
        try {
            new TimesheetAppUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }


}

//    /**
//     * Adds menu bar.
//     */
//    private void addMenu() {
//        JMenuBar menuBar = new JMenuBar();
//        JMenu systemMenu = new JMenu("System");
////        addMenuItem(systemMenu, new ChangeDateAction(),
////                KeyStroke.getKeyStroke("control D"));
////        addMenuItem(systemMenu, new ResetAction(),
////                KeyStroke.getKeyStroke("control R"));
////        addMenuItem(systemMenu, new DisplayTimesheetAction(),
////                KeyStroke.getKeyStroke("control P"));
////        addMenuItem(systemMenu, new QuitAndSaveAction(),
////                KeyStroke.getKeyStroke("control Q"));
//        menuBar.add(systemMenu);
//
//        JMenu employeeMenu = new JMenu("Employee");
//        addMenuItem(employeeMenu, new AddEmployeeAction(),
//                KeyStroke.getKeyStroke("control A"));
//        addMenuItem(employeeMenu, new RemoveEmployeeAction(),
//                KeyStroke.getKeyStroke("control R"));
//        addMenuItem(employeeMenu, new ChangeEmployeeNameAction(),
//                KeyStroke.getKeyStroke("control N"));
////        addMenuItem(employeeMenu, new AddEmployeeHourAction(),
////                KeyStroke.getKeyStroke("control H"));
////        addMenuItem(employeeMenu, new UpdateEmployeeHourAction(),
////                KeyStroke.getKeyStroke("control U"));
////        addMenuItem(employeeMenu, new GetHourAction(),
////                KeyStroke.getKeyStroke("control G"));
//        menuBar.add(employeeMenu);
//
//        setJMenuBar(menuBar);
//    }
//
//
//    /**
//     * Adds an item with given handler to the given menu
//     *
//     * @param theMenu     menu to which new item is added
//     * @param action      handler for new menu item
//     * @param accelerator keystroke accelerator for this menu item
//     */
//    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
//        JMenuItem menuItem = new JMenuItem(action);
//        menuItem.setMnemonic(menuItem.getText().charAt(0));
//        menuItem.setAccelerator(accelerator);
//        theMenu.add(menuItem);
//    }