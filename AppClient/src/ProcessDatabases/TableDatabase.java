package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableDatabase {

	public JFrame frmBngDLiu;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TableDatabase window = new TableDatabase();
//					window.frmBngDLiu.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	private static String[] nameColumn;
	private static String[][] data;
	int countRow;
	int countColumn;
	
	
	
	public TableDatabase(String[] nameColumn, String[][] data, int countRow, int countColumn) {
		this.nameColumn = nameColumn;
		this.data = data;
		this.countRow = countRow;
		this.countColumn = countColumn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBngDLiu = new JFrame();
		frmBngDLiu.setResizable(false);
		frmBngDLiu.setTitle("Bảng dữ liệu");
		frmBngDLiu.setBounds(100, 100, 834, 475);
		frmBngDLiu.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmBngDLiu.getContentPane().setLayout(null);
		
		Object[] colump = new Object[countColumn];
		Object[] row = new Object[countColumn];
		for (int i = 0; i < countColumn; i++) {
			colump[i] = nameColumn[i];
		}
		DefaultTableModel model = new DefaultTableModel();		
		model.setColumnIdentifiers(colump);
		JTable table = new JTable();
		table.setModel(model);
		table.setBounds(10, 11, 560, 414);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(10, 11, 808, 424);		
		frmBngDLiu.getContentPane().add(jsp);
		for (int i = 0; i < countRow; i++) {
			for (int j = 0; j < countColumn; j++) {
				row[j] = data[i][j];
			}
			model.addRow(row);
		}
	}
}
