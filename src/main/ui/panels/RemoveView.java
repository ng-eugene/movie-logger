package ui.panels;

import model.MovieList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// View for removing movies from list
public class RemoveView implements ListSelectionListener, ActionListener {

    private MovieList movieList;
    private JList list;
    private DefaultListModel listModel;
    private String[] movieNames;
    private JButton removeBtn;
    private JButton closeBtn;
    private JPanel panel;

    // MODIFIES: this
    // EFFECTS: creates list removing view for given list
    public RemoveView(MovieList movieList) {
        this.movieList = movieList;

        movieNames = movieList.listMovies();
        listModel = new DefaultListModel();

        for (String s : movieNames) {
            listModel.addElement(s);
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);

        initButtons();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeBtn);
        buttonPane.add(closeBtn);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panel = new JPanel(new BorderLayout());
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: initialises buttons
    private void initButtons() {
        removeBtn = new JButton("Remove");
        removeBtn.setActionCommand("Remove");
        removeBtn.addActionListener(this);

        closeBtn = new JButton("Close");
        closeBtn.setActionCommand("Close");
        closeBtn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: removes selected movie from list
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Close")) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            return;
        }

        int index = list.getSelectedIndex();
        movieList.removeMovie(movieList.getMovie(index));
        listModel.remove(index);

        if (listModel.size() == 0) {
            removeBtn.setEnabled(false);
        } else {
            list.setSelectedIndex(0);
            list.ensureIndexIsVisible(index);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes button state depending on list selection
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (list.getSelectedIndex() == -1) {
                removeBtn.setEnabled(false);
            } else {
                removeBtn.setEnabled(true);
            }
        }
    }

    // EFFECTS: returns pane
    public JPanel getPanel() {
        return panel;
    }

}
