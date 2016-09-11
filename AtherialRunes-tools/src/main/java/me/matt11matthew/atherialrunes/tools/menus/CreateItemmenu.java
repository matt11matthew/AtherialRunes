/*
 * Created by JFormDesigner on Sun Sep 11 14:02:14 EDT 2016
 */

package me.matt11matthew.atherialrunes.tools.menus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

/**
 * @author Matthew Matthew
 */
public class CreateItemmenu extends JFrame {
    public CreateItemmenu() {
        initComponents();
    }

    private void RarityBoxItemStateChanged(ItemEvent e) {
        // TODO add your code here
    }

    private void RarityBox1StateChanged(ChangeEvent e) {
        if (checkBox1.isSelected()) {
            RarityBox.setEnabled(true);
            return;
        }
        RarityBox.setEnabled(false);
    }

    private void LoreBoxStateChanged(ChangeEvent e) {
        if (checkBox3.isSelected()) {
            editorPane1.setEnabled(true);
            return;
        }
        editorPane1.setEnabled(false);
    }

    private void TiercheckBox4StateChanged(ChangeEvent e) {
        if (checkBox4.isSelected()) {
            comboBox2.setEnabled(true);
            return;
        }
        comboBox2.setEnabled(false);
    }

    private void NamecheckBox2StateChanged(ChangeEvent e) {
        if (checkBox2.isSelected()) {
            textField2.setEnabled(true);
            return;
        }
        textField2.setEnabled(false);
    }

    private void GenerateActionPerformed(ActionEvent e) {
        String name = textField2.getText();
        if (textField2.isEnabled()) {
            if (name.isEmpty()) {
                name = "&cNull";
            }
        }
        String loretext = editorPane1.getText();
        java.util.List<String> lore = new ArrayList<>();
        if (loretext.isEmpty()) {
            loretext = "Null#None";
        }
        if (!loretext.contains("#")) {
            lore.add(loretext.trim());
        } else {
            for (String s : loretext.split("#")) {
                lore.add(s.trim());
            }
        }
        String type = textField1.getText();
        if (type.isEmpty()) {
            type = "diamond_sword";
        }
        String rarity = "";
        if (RarityBox.isEnabled()) {
            rarity = String.valueOf(RarityBox.getSelectedItem());
        }
        String tier = "";
        if (comboBox2.isEnabled()) {
            tier = String.valueOf(comboBox2.getSelectedItem());
        }
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        array.addAll(lore);
        obj.put("name", name);
        obj.put("tier", tier.split(" ")[1].trim());
        obj.put("material", type.toUpperCase().trim());
        obj.put("rarity", rarity.trim());
        obj.put("lore", array);
        copy(obj.toJSONString());
    }

    private static Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

        return systemClipboard;
    }

    public static void copy(String text)
    {
        Clipboard clipboard = getSystemClipboard();

        clipboard.setContents(new StringSelection(text), null);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Matthew Matthew
        RarityBox = new JComboBox<>();
        RarityLabel = new JLabel();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();
        checkBox4 = new JCheckBox();
        comboBox2 = new JComboBox<>();
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        scrollPane1 = new JScrollPane();
        editorPane1 = new JEditorPane();
        button1 = new JButton();
        COPIED = new JLabel();

        //======== this ========
        setTitle("Create item");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- RarityBox ----
        RarityBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "Normal",
            "Unusual",
            "Epic",
            "Legendary"
        }));
        RarityBox.addItemListener(e -> RarityBoxItemStateChanged(e));
        contentPane.add(RarityBox);
        RarityBox.setBounds(15, 40, 120, 35);

        //---- RarityLabel ----
        RarityLabel.setText("Rarity");
        RarityLabel.setFont(RarityLabel.getFont().deriveFont(RarityLabel.getFont().getStyle() | Font.BOLD, RarityLabel.getFont().getSize() + 3f));
        contentPane.add(RarityLabel);
        RarityLabel.setBounds(45, 15, 95, 25);

        //---- checkBox1 ----
        checkBox1.setText("Rarity");
        checkBox1.setSelected(true);
        checkBox1.addChangeListener(e -> RarityBox1StateChanged(e));
        contentPane.add(checkBox1);
        checkBox1.setBounds(new Rectangle(new Point(10, 365), checkBox1.getPreferredSize()));

        //---- checkBox2 ----
        checkBox2.setText("Name");
        checkBox2.setSelected(true);
        checkBox2.addChangeListener(e -> NamecheckBox2StateChanged(e));
        contentPane.add(checkBox2);
        checkBox2.setBounds(new Rectangle(new Point(10, 395), checkBox2.getPreferredSize()));

        //---- checkBox3 ----
        checkBox3.setText("Lore");
        checkBox3.setSelected(true);
        checkBox3.addChangeListener(e -> LoreBoxStateChanged(e));
        contentPane.add(checkBox3);
        checkBox3.setBounds(new Rectangle(new Point(85, 365), checkBox3.getPreferredSize()));

        //---- checkBox4 ----
        checkBox4.setText("Tier");
        checkBox4.setSelected(true);
        checkBox4.addChangeListener(e -> TiercheckBox4StateChanged(e));
        contentPane.add(checkBox4);
        checkBox4.setBounds(new Rectangle(new Point(85, 395), checkBox4.getPreferredSize()));

        //---- comboBox2 ----
        comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
            "Tier 1",
            "Tier 2",
            "Tier 3",
            "Tier 4"
        }));
        contentPane.add(comboBox2);
        comboBox2.setBounds(150, 40, 110, 35);

        //---- label2 ----
        label2.setText("Tier");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 3f));
        contentPane.add(label2);
        label2.setBounds(185, 15, 95, 25);
        contentPane.add(textField1);
        textField1.setBounds(300, 45, 130, 30);

        //---- label3 ----
        label3.setText("Material");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 3f));
        contentPane.add(label3);
        label3.setBounds(330, 20, 95, 25);
        contentPane.add(textField2);
        textField2.setBounds(455, 45, 150, 30);

        //---- label4 ----
        label4.setText("Name");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 3f));
        contentPane.add(label4);
        label4.setBounds(480, 20, 95, 25);

        //---- label5 ----
        label5.setText("Lore");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 3f));
        contentPane.add(label5);
        label5.setBounds(60, 95, 95, 25);

        //======== scrollPane1 ========
        {

            //---- editorPane1 ----
            editorPane1.setText("&fDamage: &c(100~200)                   ");
            scrollPane1.setViewportView(editorPane1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(30, 140, 140, 100);

        //---- button1 ----
        button1.setText("Generate");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 5f));
        button1.addActionListener(e -> GenerateActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(345, 305, 265, 125);

        //---- COPIED ----
        COPIED.setText("JSON TEXT HAS BEEN COPIED");
        COPIED.setForeground(Color.orange);
        COPIED.setFont(COPIED.getFont().deriveFont(COPIED.getFont().getStyle() | Font.BOLD, COPIED.getFont().getSize() + 3f));
        COPIED.setEnabled(false);
        contentPane.add(COPIED);
        COPIED.setBounds(355, 200, 250, 85);

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
    private JComboBox<String> RarityBox;
    private JLabel RarityLabel;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JComboBox<String> comboBox2;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JTextField textField2;
    private JLabel label4;
    private JLabel label5;
    private JScrollPane scrollPane1;
    private JEditorPane editorPane1;
    private JButton button1;
    private JLabel COPIED;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
