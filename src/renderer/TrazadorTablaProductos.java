package renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrazadorTablaProductos extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor, boolean isSelected, boolean hasFocus,
			int fila, int columna) {

		JLabel label = new JLabel();
		label.setForeground(Color.black);

		if (isSelected) {
			if (columna % 2 == 0) {
				label.setBackground(Color.decode("#fbe16c")); // Cambiar por colores de musports
			} else {
				label.setBackground(Color.decode("#bac9d5"));
			}
		} else {
			label.setBackground(Color.WHITE);
		}
		label.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		label.setSize(columna, 100);

		label.setOpaque(true);

		switch (columna) {
		case 0:
			label.setText(valor.toString());
			break;
		case 1:
			label.setText(String.format("%.2f €", valor));
			break;
		case 2:
			label.setText(valor.toString());
			break;
		case 3:
			label.setText(String.format("%.2f €", valor));
			break;
		}
		return label;
	}
}
