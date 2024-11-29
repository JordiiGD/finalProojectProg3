package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class FramePruebas extends JFrame {

    private DatePanel datePanel;

    public FramePruebas() {
        setLayout(new BorderLayout());
        initComponents();
    }

    public void initComponents() {
        setBounds(1, 1, 1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        datePanel = new DatePanel();
        add(datePanel, BorderLayout.CENTER);
    }

    public void run(){
        setVisible(true);
    }

    public static void main(String[] args) {
        FramePruebas frame = new FramePruebas();
        frame.run();
    }
}
