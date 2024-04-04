package resources.data.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class DataExcel {
    private DefaultTableModel dataTable;
    FileInputStream excelFile;
    Workbook workbook;
    Sheet sheet;
    FileOutputStream fos;
    String sheetName="Hoja1";
    public String excelFilePath="C:\\Users\\Maria Jose\\Desktop\\PROYECTOS\\MY_PROJECTS\\BankProyectTestAutomation\\src\\main\\java\\resources\\data\\inputs\\TestData.xlsx";
    int numerofila=0;
    //conexión excel
    //region
    public void excelAbrirConexion(boolean actualizar){
        try{
            excelFile=new FileInputStream(excelFilePath);
            workbook=new XSSFWorkbook(excelFile);
            sheet =workbook.getSheet(sheetName);
            if(actualizar){
                fos = new FileOutputStream(excelFilePath);
            }

            System.out.println("conexion exitosa");

        }catch(Exception fallo){
            System.out.println(fallo.getMessage());
        }
    }
       public void excelcerrarConexion (){
        try {
            workbook.close();
            excelFile.close();

        }catch (Exception fallo){
            System.out.println(fallo.getMessage());

        }


    }
    //endregion
   //obtener informacion excel
    //region


    public DefaultTableModel excelTabla() {
        dataTable=new DefaultTableModel();
        //Obtiene la hoja con el  nombre sheetName del archivo excel
        Iterator<Row> rowIterator= sheet.iterator();
        //Obten las cabeceras de las columnas
        Row headerRow= rowIterator.next();
        Iterator<Cell> headerCellIterator=headerRow.cellIterator();
        while (headerCellIterator.hasNext()){
            Cell cell= headerCellIterator.next();
            dataTable.addColumn(cell.getStringCellValue());
        }
        while (rowIterator.hasNext()){
            Row row =rowIterator.next();
            Object[]rowData=new Object[dataTable.getColumnCount()];
            Iterator<Cell> cellIterator=row.cellIterator();
            int columnIndex=0;
            while (cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                cell.setCellType(CellType.STRING);
                rowData[columnIndex]=cell.getStringCellValue();
                columnIndex++;
            }
            dataTable.addRow(rowData);
        }
        return dataTable;
    }
    //Metodo para obtener el valor de una celda especifica basado en el numero de fila y el  nombre de la columna
    //new*
    public Object obtenerValorColumna(int rowIndex, String columnName){
        int columnIndex= dataTable.findColumn(columnName);

        if (columnIndex==-1){
            System.out.println("Column not found:"+columnName);
            return null;
        }
        return dataTable.getValueAt(rowIndex, columnIndex);
    }

    //endregion

}
