/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Views;

/**
 *
 * @author quentinmartinez
 */
public interface DefaultTableModel {
	abstract void removeRow(int row);
	abstract Object getItem(int index);
	abstract void addRow(Object row);
}
