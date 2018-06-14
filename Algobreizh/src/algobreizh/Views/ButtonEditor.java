/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private ButtonListener bListener;

    public ButtonEditor(JCheckBox checkBox, ButtonListener _bListener) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        bListener = _bListener;
        button.addActionListener(bListener);
    }

    public ButtonEditor(JCheckBox checkBox, String str) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(bListener);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        bListener.setRow(row);
        bListener.setColumn(column);
        bListener.setTable(table);
        button.setText((value == null) ? "" : value.toString());
        return button;
    }

}
