package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class rsTableModel extends  AbstractTableModel {
    //-- Khai báo 2 Vector Object để sử dụng cho JTable
    private  Vector colHeaders;        //-- Chứa thông tin là tên của các Field dùng làm ColumnHeader
    private  Vector tbData;              //-- Phần chứa dữ liệu của JTable
    /**
     * Constructor truyền vào1 ResultSet để gán dữ liệu cho JTable có Model tham chiếu tới
     * @param rset - ResultSet chứa dữ liệu đọc từ Database
     * @throws SQLException
     */
    public rsTableModel(ResultSet rsData) throws SQLException {
        ResultSetMetaData rsMeta = rsData.getMetaData();    //-- Đọc MetaData của ResultSet
        int count = rsMeta.getColumnCount();              //-- Xác định số Field trong ResultSet

        colHeaders = new Vector(count);
        tbData = new Vector();
                  //--- Lặp tương ứng với số phần tử của columnHeaders để lấy tên của từng cột trong bảng
        for (int i = 1; i <= count; i++) {
            colHeaders.addElement(rsMeta.getColumnName(i));
        }
           //--- Lặp từ Record đầu tiên đến cuối ResultSet để lấy dữ liệu và Add vào Table
        while (rsData.next()) {
                      //--- Khai báo 1 Object Vector có tên là rowData để chứa dữ liệu tương ứng với mỗi dòng đọc từ Table
            Vector dataRow = new Vector(count);
                      //-- Lặp dựa theo số cột của bảng để lấy thông tin của từng đối tượng
            for (int i = 1; i <= count; i++) {
                dataRow.addElement(rsData.getObject(i));
            }
            tbData.addElement(dataRow);
        }
    }
    /**
     * Định nghĩa phương thức lấy số cột của JTable
     * @return
     */
    public int getColumnCount() {
        return colHeaders.size();
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
     * Định nghĩa lại hàm lấy tên của cột thứ n trong JTable
     * @param column - Vị trí của cột muốn lấy tên
     * @return - Kết quả trả về là tên của cột ở dạng chuỗi ký tự
     */
    @Override
    public String getColumnName(int column) {
        return (String) (colHeaders.elementAt(column));
    }
}