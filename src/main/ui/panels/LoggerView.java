package ui.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoggerView implements ActionListener, FocusListener {

    private JSlider ratingSlider;
    private JTextField reviewField;
    private JCheckBox rewatchBox;
    private JButton submitButton;
    private JPanel panel;
    private String name;

    public LoggerView(String name) {
        this.name = name;
        panel = new JPanel(new SpringLayout());

        String[] labelStr = {"Rating: ", "Review: ", "Rewatch? "};

        JLabel[] labels = new JLabel[labelStr.length];
        JComponent[] fields = new JComponent[labelStr.length];
        int n = 0;

        ratingSlider = new JSlider(JSlider.HORIZONTAL, 10);
        fields[n++] = ratingSlider;

        reviewField = new JTextField();
        reviewField.setColumns(20);
        fields[n++] = reviewField;

        rewatchBox = new JCheckBox("Rewatch");
        fields[n++] = rewatchBox;

        submitButton = new JButton("Submit");

        for (int i = 0; i < labelStr.length; i++) {
            labels[i] = new JLabel(labelStr[i],
                    JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);
        }

        setListeners();
    }

    // MODIFIES: this
    // EFFECTS: adds listeners for each field
    private void setListeners() {
        reviewField.addFocusListener(this);

        ratingSlider.addFocusListener(this);

        rewatchBox.addFocusListener(this);

        submitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
    }

    public void focusGained(FocusEvent e) {
        System.out.println(e.getSource());
    }

    public void focusLost(FocusEvent e) {

    }

    public JPanel getPanel() {
        return panel;
    }

}
