package swing;

import control.Command;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.ImageDisplay;

public class SwingFrame extends JFrame {

    public static final int NEXT = 0;
    public static final int PREV = 1;
    public static final int CLOSE = 2;

    private Command[] commands;
    private SwingImageDisplay display;
    private final IdleManager idleManager = new IdleManager();

    public SwingFrame() {
        setTitle("Image Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(createDisplay());
        add(createToolbar(), BorderLayout.SOUTH);
        pack();
    }

    public JPanel createDisplay() {
        display = new SwingImageDisplay();
        display.setPreferredSize(new Dimension(1024, 768));
        return display;
    }

    public void register(Command[] commands) {
        this.commands = commands;
    }

    public void start() {
        setVisible(true);
    }

    public ImageDisplay getDisplay() {
        return display;
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(createButton(PREV, "<"));
        panel.add(createButton(CLOSE, "Close"));
        panel.add(createButton(NEXT, ">"));
        return panel;
    }
        
    private JButton createButton(final int operation, String label) {
        final JButton button = new JButton(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                commands[operation].execute();
            }
        });
        idleManager.add(new IdleTask() {
            @Override
            public void execute() {
                if (commands == null) {
                    return;
                }
                button.setEnabled(commands[operation].isEnabled());
            }

        });
        return button;
    }

    private class IdleManager extends EventQueue {

        private final ArrayList<IdleTask> tasks = new ArrayList<>();

        public IdleManager() {
            Toolkit.getDefaultToolkit().getSystemEventQueue().push(this);
        }

        @Override
        protected void dispatchEvent(AWTEvent event) {
            super.dispatchEvent(event);
            for (IdleTask task : tasks) {
                task.execute();
            }
        }

        private void add(IdleTask task) {
            tasks.add(task);
        }

    }

    private interface IdleTask {

        public void execute();
        
    }
}