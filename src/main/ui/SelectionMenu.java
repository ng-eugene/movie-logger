package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectionMenu {

    private ActionListener listener;

    public SelectionMenu(ActionListener listener) {
        this.listener = listener;
    }

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

    private void buildSaveLoadMenu(JMenu menu) {
        JMenuItem menuItem;
        menuItem = new JMenuItem("Save to file");
        menuItem.addActionListener(listener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load from file");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    public Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
        return contentPane;
    }
}
