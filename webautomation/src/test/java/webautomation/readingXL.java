package webautomation;

import java.io.File;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readingXL {

    public static String readXL(int row, int column) throws Exception {
        File path = new File("webautomation\\src\\I-O Files\\input.xlsx");
        OPCPackage pkg = OPCPackage.open(path);
        XSSFWorkbook myWB = new XSSFWorkbook(pkg);
        XSSFCell cell = myWB.getSheetAt(0).getRow(row).getCell(column);
        
        return cell.getStringCellValue();
    }
}