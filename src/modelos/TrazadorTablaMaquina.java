package modelos;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TrazadorTablaMaquina implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {
		table.setRowHeight(50);
		JLabel label = new JLabel();
		Object nombre = value;
		label.setBorder((row % 2 == 0) ? BorderFactory.createLineBorder(Color.decode("#fbe16c"), 4)
				: BorderFactory.createLineBorder(Color.decode("#bac9d5"), 4));

		label.setForeground(Color.BLACK);
		if (isSelected) {
			if (row % 2 == 0) {
				label.setBackground(Color.decode("#fbe16c"));
			} else {
				label.setBackground(Color.decode("#bac9d5"));
			}
		} else {
			label.setBackground(Color.WHITE);
		}

		label.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
		label.setSize(col, 100);

		label.setOpaque(true);
		switch (col) {
		case 0:
			if (value != null)
				label.setText(value.toString());

			break;
			
		case 1:
			label.setHorizontalAlignment(JLabel.CENTER);
		
			if (value.toString().equals("true")) {
				return new JLabel(new ImageIcon("logos/rsz_encendido.png"));
			} else {
				return new JLabel(new ImageIcon("logos/rsz_apagado.png"));
			}
	
		default:
			break;
		}

		return label;
	}
}
