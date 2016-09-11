/*
 * Created by JFormDesigner on Sun Sep 11 13:22:17 EDT 2016
 */

package me.matt11matthew.atherialrunes.tools.menus;

import me.matt11matthew.atherialrunes.tools.Main;
import me.matt11matthew.atherialrunes.tools.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Matthew Matthew
 */
public class ToolsMenu extends JFrame {

    private User user;

    public ToolsMenu(User user) {
        this.user = user;
        initComponents();
        label1.setText(user.getUsername());
    }

    private void onLogout(ActionEvent e) {

    }

    private void button1ActionPerformed(ActionEvent e) {
        Main.user = null;
        MainMenu menu = new MainMenu();
        menu.setSize(new Dimension(500, 500));
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setVisible(true);
        this.dispose();
    }

    private void createItem(ActionEvent e) {
        CreateItemmenu createItemmenu = new CreateItemmenu();
        createItemmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createItemmenu.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Matthew Matthew
        button1 = new JButton();
        label1 = new JLabel();
        button2 = new JButton();

        //======== this ========
        setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("Logout");
        button1.setToolTipText("Click to logout");
        button1.setIcon(null);
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD));
        button1.addActionListener(e -> {
			onLogout(e);
			button1ActionPerformed(e);
		});
        contentPane.add(button1);
        button1.setBounds(590, 0, 80, button1.getPreferredSize().height);

        //---- label1 ----
        label1.setText("%username%");
        contentPane.add(label1);
        label1.setBounds(470, 5, 95, 25);

        //---- button2 ----
        button2.setText("Create Item");
        button2.addActionListener(e -> createItem(e));
        contentPane.add(button2);
        button2.setBounds(30, 305, 130, 125);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Matthew Matthew
    private JButton button1;
    private JLabel label1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
