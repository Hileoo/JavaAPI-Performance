import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * This class provides Record test-result in spreadsheet and Draw line-chart based on data in the spreadsheet.
 * Implement the requirement of Task2
 */
public class DataRecord {
	// Call for TestPerformance Class
	private TestPerformance pe;
	
	/**
	 * The constructor for DataRecord with 1 parameter
	 * @param size  Define the basic size for tested collection
	 */
	public DataRecord(int size) {
		pe = new TestPerformance(size);
	}
	
	/**
	 * Execute various type of testing.
	 * Record the result in spreadsheet, different test type stored in different sheet.
	 * Read data from spreadsheet and draw corresponding line chart.
	 */
	public void RecordAndGraph() throws RowsExceededException, WriteException, IOException, BiffException {
		String[] titleA = {" ", "ArrayList", "HashMap"};
		/* Create the file of spreadsheet */ 
		File fileA = new File("Output/record.xls");
		if(fileA.exists())
			fileA.delete();
		fileA.createNewFile();
		
		/* Create writable workbook and sheet to write in spreadsheet */
		WritableWorkbook workbookA = Workbook.createWorkbook(fileA);
		
		WritableSheet sheet1 = createSheetWithTitle(workbookA, "Search (Order)", titleA, 0);
		WritableSheet sheet2 = createSheetWithTitle(workbookA, "Search (Size)", titleA, 1);
		WritableSheet sheet3 = createSheetWithTitle(workbookA, "Remove (Order)", titleA, 2);
		WritableSheet sheet4 = createSheetWithTitle(workbookA, "Remove (Size)", titleA, 3);
		WritableSheet sheet5 = createSheetWithTitle(workbookA, "Insert (Order)", titleA, 4);
		WritableSheet sheet6 = createSheetWithTitle(workbookA, "Insert (Size)", titleA, 5);
		
		System.out.println("Finish create sheet");
		
		/* Test various operation */
		// Test Search By Order
		pe.TestSearchByOrder(sheet1);
		System.out.println("Finish test-1 operation");
		// Test Search By Size
		pe.TestSearchBySize(sheet2);
		System.out.println("Finish test-2 operation");
		// Test Remove By Order
		pe.TestRemoveByOrder(sheet3);
		System.out.println("Finish test-3 operation");
		// Test Remove By Size
		pe.TestRemoveBySize(sheet4);
		System.out.println("Finish test-4 operation");
		// Test Insert By Order
		pe.TestInsertByOrder(sheet5);
		System.out.println("Finish test-5 operation");
		// Test Insert By Size
		pe.TestInsertBySize(sheet6);
		System.out.println("Finish test-6 operation");
		
		/* Complete data-storing process and close workbook */
		workbookA.write();
		workbookA.close();
		
		System.out.println("Finish Write");
		
		/* Draw the chart */
		generateChart("Search Person in the same size collection by different order", 0);
		generateChart("Search Person at the same order in different size collection", 1);
		generateChart("Remove Person in the same size collection by different order", 2);
		generateChart("Remove Person at the same order in different size collection", 3);
		generateChart("Insert Person in the same size collection by different order", 4);
		generateChart("Insert Person to tail of the collection by different collection size", 5);
		System.out.println("Finish Draw");
	}
	
	/**
	 * Create and return the sheet with sheet name, sheet title (1st row of the sheet) and sheet index.
	 */
	public WritableSheet createSheetWithTitle(WritableWorkbook workbook, String sheetName, String[] title, int index) throws RowsExceededException, WriteException {
		WritableSheet sheet = workbook.createSheet(sheetName, index);
		Label labelA = null;
		for(int i=0; i<title.length; i++) {
			labelA = new Label(i, 0, title[i]);
			sheet.addCell(labelA);
		}
		return sheet;		
	}
	
	/**
	 * Create JFree Line Chart and read data from specific sheet with sheet index.
	 * Set the default configure of the layout and content of line-chart.
	 * Save the chart as PNG file.
	 */
	public void generateChart(String chartName, int chartIndex) throws BiffException, IOException {
		/* Set the configure and layout of the chart */
		StandardChartTheme mChartTheme = new StandardChartTheme("EN");
		ChartFactory.setChartTheme(mChartTheme);
		CategoryDataset mDataset = GetDataset(chartIndex);
		JFreeChart mChart = ChartFactory.createLineChart(chartName,"Data Sequence","Time",mDataset,PlotOrientation.VERTICAL,true,true,false);
		CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
		mPlot.setBackgroundPaint(Color.white);
		CategoryAxis domainAxis = mPlot.getDomainAxis();
		domainAxis.setLowerMargin(0);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
		
		/* Save the chart as PNG with chart-name */
		File file = new File("Output/"+ chartName +".png");
		ChartUtilities.saveChartAsPNG(file, mChart, 1200, 725);
		
		/* Set the java frame to display chart as invisible */
		ChartFrame mChartFrame = new ChartFrame(chartName, mChart);
		mChartFrame.pack();
		mChartFrame.setVisible(false);
	}
	
	/**
	 * Read the result-record from spreadsheet by Cell and add them to Data Set.
	 */
	public CategoryDataset GetDataset(int sheetIndex) throws BiffException, IOException {
		/* Read the data from spreadsheet */
		File file = new File("Output/record.xls");
		InputStream input = new FileInputStream(file);
		Workbook readxls = Workbook.getWorkbook(input);
		Sheet readsheet = readxls.getSheet(sheetIndex);	
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		
		/* Add data to Data Set cell by cell */
		for(int i=1; i<readsheet.getRows(); i++) {
			Cell cell1 = readsheet.getCell(0, i);
			Cell cell2 = readsheet.getCell(1, i);
			Cell cell3 = readsheet.getCell(2, i);
			
			// add value for chart: (x-axis, type, y-axis)
			mDataset.addValue(Double.parseDouble(cell2.getContents()), "ArrayList", cell1.getContents());
			mDataset.addValue(Double.parseDouble(cell3.getContents()), "HashMap", cell1.getContents());
		}
		return mDataset;
	}
}
