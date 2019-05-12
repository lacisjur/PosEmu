package az.pashabank.posemu;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class SwingUtils {

    static void alignColumnsLeft (JTable table, int ... columns) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
        TableColumnModel model = table.getColumnModel();
        for (int column : columns) {
            model.getColumn(column).setCellRenderer(renderer);
        }
    }
    
}
