package gui;

import javax.swing.*;
import java.util.Stack;

public class PanelNavigator {

    private final Stack<JPanel> backStack = new Stack<>();
    private final Stack<JPanel> forwardStack = new Stack<>();
    private JPanel currentPanel = null;
    private final JPanel mainContainer;

    public PanelNavigator(JPanel mainContainer) {
        this.mainContainer = mainContainer;
    }

    public void addPanel(JPanel panel) {
        if (currentPanel != null) {
            backStack.push(currentPanel);
            forwardStack.clear();
        }
        show(panel);
    }

    public void goBack() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentPanel);
            show(backStack.pop());
        }
    }

    public void goForward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentPanel);
            show(forwardStack.pop());
        }
    }

    private void show(JPanel panel) {
        if (currentPanel != null) {
            mainContainer.remove(currentPanel);
        }
        currentPanel = panel;
        mainContainer.add(currentPanel);
        mainContainer.revalidate();
        mainContainer.repaint();
    }

    public boolean canGoBack() {
        return !backStack.isEmpty();
    }

    public boolean canGoForward() {
        return !forwardStack.isEmpty();
    }
}
