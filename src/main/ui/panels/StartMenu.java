package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Initial start point for UI
public class StartMenu {

    private ActionListener listener;

    // MODIFIES: this
    // EFFECTS: creates menu with listener
    public StartMenu(ActionListener listener) {
        this.listener = listener;
    }

    // MODIFIES: this
    // EFFECTS: creates menu tabs and fills with menu items
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;

        menuBar = new JMenuBar();

        menu = new JMenu("Watchlist");
        buildWatchlistMenu(menu);
        menuBar.add(menu);

        menu = new JMenu("Log");
        buildLogMenu(menu);
        menuBar.add(menu);

        menu = new JMenu("Save/Load");
        buildSaveLoadMenu(menu);
        menuBar.add(menu);

        return menuBar;
    }

    // MODIFIES: this
    // EFFECTS: fills out watchlist menu dropdown
    private void buildWatchlistMenu(JMenu menu) {
        JMenuItem menuItem;
        menuItem = new JMenuItem("View watchlist");
        menuItem.addActionListener(listener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Suggest random movie");
        menuItem.addActionListener(listener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Add to watchlist");
        menuItem.addActionListener(listener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Remove from watchlist");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    // MODIFIES: this
    // EFFECTS: fills out log menu dropdown
    private void buildLogMenu(JMenu menu) {
        JMenuItem menuItem;
        menuItem = new JMenuItem("View movie log");
        menuItem.addActionListener(listener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Log a new movie");
        menuItem.addActionListener(listener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Remove from log");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    // MODIFIES: this
    // EFFECTS: fills out save/load menu dropdown
    private void buildSaveLoadMenu(JMenu menu) {
        JMenuItem menuItem;
        menuItem = new JMenuItem("Save to file");
        menuItem.addActionListener(listener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load from file");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    // EFFECTS: creates content pane for menu and returns it
    public Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
        return contentPane;
    }
}
