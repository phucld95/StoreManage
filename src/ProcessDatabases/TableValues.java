package ProcessDatabases;

import javax.swing.table.AbstractTableModel;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

public class TableValues extends AbstractTableModel{

    public final static boolean GENDER_MALE = true;
    public final static boolean GENDER_FEMALE = false;

    public final static String[] columnNames = {
	     "ID Mặt hàng", "Tên Mặt Hàng", "Giá bán", "Số lượng"
	    };
    private static String id;
    private static String num;
    
    private Vector tbData;		//Chứa dữ liệu của bảng
    private static int count;	// Số cột của bảng
    
    private static int Pr[] = new int[100];
    private static int countPr = 0;
    public TableValues(){
        count = 4;    
        tbData = new Vector();
    }
    
    /**
     * func: Get more 1 row at table
     * input: 	ResultSet, resultSet of mat_hang, which want to add.
     * 			int, id of mat_hang
     * return: nothing
     */
    public void getMoreRow(ResultSet rsData, int id,int numb) throws SQLException{
    	ResultSetMetaData rsMeta = rsData.getMetaData();
    	Pr[countPr] = id;
    	while (rsData.next()) {
    		Vector dataRow = new Vector(count);
    		//Lấy dữ liệu của từng cột trong 1 dòng.
    		for (int i = 1; i < count; i++) {
    			dataRow.addElement(rsData.getObject(i));
    		}
    		dataRow.addElement(numb);
    		tbData.addElement(dataRow);
    	}
    	countPr++;
    }
    
    /**
     * func: Remove 1 row in jtable
     * input: int, ID_Mat_Hang want remove
     * return: nothing
     */
    public void removeRow(int id) {
    	int i;
    	for (i = 0; i <= countPr; i++) {
			if(Pr[i] == id) break;
		}
        tbData.removeElementAt(i);
        countPr--;
        for(;i<=countPr;i++){
        	Pr[i] = Pr[i+1];
        }
    }  
    
    /**
     * Định nghĩa phương thức lấy số cột của JTable
     * @return
     */
    public int getColumnCount() {
        return count;
    }
    /**
     * Định nghĩa phương thức lấy số hàng có trong JTable
     * @return
     */
    public int getRowCount() {
        return tbData.size();
    }
    /**
     * Định nghĩa phương thức lấy dữ liệu tại 1 ô nào đó của bảmg
     * @param row - Vị trí của hàng chứa ô dữ liệu cần lấy
     * @param column - Vị trí của cột chứa ô dữ liệu cần lấy
     * @return Giá trị trả về cho nơi gọi là 1 Object chứa giá trị, để dùng phải Cast lại
     */
   
    public Object getValueAt(int row, int column) {
        Vector rowData = (Vector) (tbData.elementAt(row));
        return rowData.elementAt(column);
    }
    /**
     * Func: Get column name of table
     * (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
}


