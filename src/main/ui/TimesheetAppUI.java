package ui;


import model.Employee;
import model.EmployeeDatabase;
import model.Event;
import model.EventLog;
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
// https://www.javatpoint.com/how-to-change-titlebar-icon-in-java-awt-swing
// save icon from : https://publicdomainvectors.org/en/free-clipart/File-save-icon/88085.html
public class TimesheetAppUI extends JFrame {

    private static final String JSON_STORE = "./data/employeeDatabase.json";
    private EmployeeDatabase ed;
    private JFrame frame;
    private JPanel panel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // Timesheet entry application graphical user interface
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
        Image teaIcon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\steph\\IdeaProjects\\CPSC210\\labs\\"
                + "project_w9h8b\\data\\images\\teapotIcon.png");
        frame.setIconImage(teaIcon);
        frame.setTitle("Timesheet Entry Application");

        frame.setVisible(true);
        startAndLoad();
        addButtonPanel();
        quitAndSave();
        frame.pack();
    }

    // EFFECTS: helps to resize icons
    // Sourced from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    private ImageIcon resizeIcon(String fileName) {
        ImageIcon imageIcon = new ImageIcon(fileName);
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    // EFFECTS: handles user input to quit and/or save
    private void quitAndSave() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent w) {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(frame,
                        "Would you like to save your current employee database?",
                        "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        resizeIcon("C:\\Users\\steph\\IdeaProjects\\CPSC210\\labs\\"
                                + "project_w9h8b\\data\\images\\saveIcon.png"), options,
                        null);
                if (n == JOptionPane.YES_OPTION) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(ed);
                        jsonWriter.close();
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(frame, "Sorry, your timesheet could not be saved",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                printLog();
            }
        });
    }

    // EFFECTS: handles user input to load
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

    // EFFECTS: add buttons to the frame
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        frame.setLayout(new GridLayout(10, 1));

        frame.add(new JButton(new AddEmployeeAction()));
        frame.add(new JButton(new RemoveEmployeeAction()));
        frame.add(new JButton(new ChangeEmployeeNameAction()));
        frame.add(new JButton(new AddEmployeeHourAction()));
        frame.add(new JButton(new UpdateEmployeeHourAction()));
        frame.add(new JButton(new GetHourAction()));

        panel.add(buttonPanel, BorderLayout.WEST);
    }


    public void printLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.getDate() + " " + event.getDescription());
        }
    }

    // Adds a new employee to the database
    private class AddEmployeeAction extends AbstractAction {

        // EFFECTS: see super
        AddEmployeeAction() {
            super("Add Employee");
        }

        // EFFECTS: see super
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = JOptionPane.showInputDialog("Enter the FirstName LastName of the employee:",
                    null);
            if (name != null) {
                Employee e = new Employee(name);
                ed.addEmployee(e);
                JOptionPane.showMessageDialog(frame,
                        "Here are the employees currently in your database: \n" + names(),
                        "Your employees",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }

        // EFFECTS: returns names of employees
        private String names() {
            return String.join("\n", ed.employeeNames());
        }
    }

    // Removes an employee from the database
    private class RemoveEmployeeAction extends AbstractAction {

        // EFFECTS: see super
        RemoveEmployeeAction() {
            super("Remove Employee");
        }

        // EFFECTS: see super
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
                JOptionPane.showMessageDialog(frame,
                        "Here are the employees currently in your database: \n" + names(),
                        "Your employees",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }

        // EFFECTS: returns names of employees
        private String names() {
            return String.join("\n", ed.employeeNames());
        }
    }


    // Changes an employee's name
    private class ChangeEmployeeNameAction extends AbstractAction {

        // EFFECTS: see super
        ChangeEmployeeNameAction() {
            super("Change Employee Name");
        }

        // EFFECTS: see super
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
                        JOptionPane.showMessageDialog(frame,
                                "Here are the employees currently in your database: \n" + names(),
                                "Your employees",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        }

        // EFFECTS: returns names of employees
        private String names() {
            return String.join("\n", ed.employeeNames());
        }
    }


    // Adds new hour to an employee's hours
    private class AddEmployeeHourAction extends AbstractAction {

        // EFFECTS: see super
        AddEmployeeHourAction() {
            super("Add Employee Hour");
        }

        // EFFECTS: see super
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

    // Updates an existing hour in the employee's hours
    private class UpdateEmployeeHourAction extends AbstractAction {

        // EFFECTS: see super
        UpdateEmployeeHourAction() {
            super("Update Employee Hour");
        }

        // EFFECTS: see super
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (ed.getNumEmployees() == 0) {
                JOptionPane.showMessageDialog(frame, "There are no employees in the database", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String name = (String) JOptionPane.showInputDialog(frame, "Select an employee:",
                        "Update Employee Hour", JOptionPane.PLAIN_MESSAGE, null, ed.employeeNames().toArray(),
                        null);
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


        // EFFECTS: returns the possible day numbers associated with an employee's hours
        private List<Integer> employeeDates(String name) {
            List<Integer> dates = new ArrayList<>();

            for (int i = 0; i < ed.findEmployee(name).hoursSize(); i++) {
                dates.add(i + 1);
            }

            return dates;
        }
    }

    // Gets employee's current hours
    private class GetHourAction extends AbstractAction {

        // EFFECTS: see super
        GetHourAction() {
            super("Get Employee Hours");
        }

        // EFFECTS: see super
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

        // EFFECTS: returns current hours worked
        private String getHoursWorkedByDay(String name) {
            List<String> hoursList = new ArrayList<>();
            for (int i = 0; i < ed.findEmployee(name).hoursSize(); i++) {
                hoursList.add("Day " + (i + 1) + ": " + ed.findEmployee(name).getHours().get(i) + " hours");
            }

            return String.join("\n", hoursList);
        }
    }

}