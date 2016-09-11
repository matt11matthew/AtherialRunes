/*
 * Created by JFormDesigner on Sun Sep 11 11:16:21 EDT 2016
 */

package me.matt11matthew.atherialrunes.tools.menus;

import me.matt11matthew.atherialrunes.tools.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Matthew Matthew
 */
public class MainMenu extends JFrame {
    public MainMenu() {
        initComponents();
    }

    private void OnLogin(ActionEvent e) {
        String username = textField1.getText();
        String password = textField2.getText();
        if ((username.isEmpty()) || (password.isEmpty())) {
            label3.setVisible(true);
            textField1.setText("");
            textField2.setText("");
            return;
        }
        User user = User.login(username, password);
        if (user == null) {
            label3.setVisible(true);
            textField1.setText("");
            textField2.setText("");
            return;
        }
        ToolsMenu toolsMenu = new ToolsMenu(user);
        toolsMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        toolsMenu.setVisible(true);
        MainMenu.dispose();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Matthew Matthew
        MainMenu = this;
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();
        label3 = new JLabel();

        //======== MainMenu ========
        {
            MainMenu.setTitle("Atherial Runes Tools");
            MainMenu.setAlwaysOnTop(true);
            MainMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Container MainMenuContentPane = MainMenu.getContentPane();
            MainMenuContentPane.setLayout(null);

            //---- label1 ----
            label1.setText("Username");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 1f));
            MainMenuContentPane.add(label1);
            label1.setBounds(27, 107, 103, label1.getPreferredSize().height);
            MainMenuContentPane.add(textField1);
            textField1.setBounds(108, 104, 262, textField1.getPreferredSize().height);

            //---- label2 ----
            label2.setText("Password");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 1f));
            MainMenuContentPane.add(label2);
            label2.setBounds(27, 162, 76, label2.getPreferredSize().height);
            MainMenuContentPane.add(textField2);
            textField2.setBounds(108, 159, 262, textField2.getPreferredSize().height);

            //---- button1 ----
            button1.setText("Login");
            button1.addActionListener(e -> OnLogin(e));
            MainMenuContentPane.add(button1);
            button1.setBounds(40, 235, 292, 52);

            //---- label3 ----
            label3.setText("Invalid login info");
            label3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            label3.setForeground(Color.red);
            MainMenuContentPane.add(label3);
            label3.setVisible(false);
            label3.setBounds(135, 135, 235, label3.getPreferredSize().height);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < MainMenuContentPane.getComponentCount(); i++) {
                    Rectangle bounds = MainMenuContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = MainMenuContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                MainMenuContentPane.setMinimumSize(preferredSize);
                MainMenuContentPane.setPreferredSize(preferredSize);
            }
            MainMenu.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Matthew Matthew
    private JFrame MainMenu;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button1;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
