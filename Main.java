import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Task {
    private String name;
    private String description;
    private boolean completed;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public void markIncomplete() {
        completed = false;
    }

    @Override
    public String toString() {
        return "Task: " + name + "\nDescription: " + description + "\nStatus: " + (completed ? "Completed" : "Pending") + "\n";
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

    class TaskManagementApp extends JFrame {
    private TaskManager taskManager;
    private JTextArea displayArea;
    private JTextField nameField, descriptionField;

    public TaskManagementApp() {
        taskManager = new TaskManager();

        setTitle("Task Management App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Tamaño de la ventana de 600x400 píxeles
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Task Name:");
        inputPanel.add(nameLabel);
        nameField = new JTextField();
        inputPanel.add(nameField);

        JLabel descriptionLabel = new JLabel("Description:");
        inputPanel.add(descriptionLabel);
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                Task task = new Task(name, description);
                taskManager.addTask(task);
                updateDisplayArea();
                clearFields();
            }
        });
        inputPanel.add(addButton);

        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameToRemove = nameField.getText();
                List<Task> tasks = taskManager.getTasks();
                for (Task task : tasks) {
                    if (task.getName().equals(nameToRemove)) {
                        taskManager.removeTask(task);
                        break;
                    }
                }
                updateDisplayArea();
                clearFields();
            }
        });
        inputPanel.add(removeButton);

        add(inputPanel, BorderLayout.SOUTH);
    }

    private void updateDisplayArea() {
        StringBuilder sb = new StringBuilder();
        List<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TaskManagementApp app = new TaskManagementApp();
                app.setVisible(true);
            }
        });
    }
}
