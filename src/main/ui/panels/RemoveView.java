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
    private JButton btn;
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

        btn = new JButton("Remove");
        btn.setActionCommand("Remove");
        btn.addActionListener(this);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(btn);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panel = new JPanel(new BorderLayout());
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: removes selected movie from list
    public void actionPerformed(ActionEvent e) {
        int index = list.getSelectedIndex();
        movieList.removeMovie(movieList.getMovie(index));
        listModel.remove(index);

        if (listModel.size() == 0) {
            btn.setEnabled(false);
        } else {
            list.setSelectedIndex(0);
            list.ensureIndexIsVisible(index);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes button state depending on list selection
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (list.getSelectedIndex() == -1) {
                btn.setEnabled(false);
            } else {
                btn.setEnabled(true);
            }
        }
    }

    // EFFECTS: returns pane
    public JPanel getPanel() {
        return panel;
    }

}
