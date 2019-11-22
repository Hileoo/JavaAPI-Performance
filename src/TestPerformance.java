import java.util.ArrayList;
import java.util.HashMap;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * This class provides performance testing for various operations supported by Operation Class based on Sample Points.
 * Add test result of each Operation and each Sample Points to corresponding sheet and tuple.
 */
public class TestPerformance {
	// Number of sample points.
	private int sample;
	// Size of basic collection
	private int basicSize;
	// The distance hop between two sample points
	private int hop;
	// Instance for Operation class
	private Operation op;
	
	/**
	 * The construction for TestPerformance, 50 sample points provided
	 * @param size  Basic size for tested collection
	 */
	public TestPerformance(int size) {
		basicSize = size;
		sample = 50;
		hop = basicSize/sample;
		op = new Operation();
	}
	
	/**
	 * Test Search/Retrieve function by different order in the same size collection.
	 * @param s  the sheet where result located in.
	 */
	public void TestSearchByOrder(WritableSheet s) throws RowsExceededException, WriteException {
		ArrayList<Person> list = op.createArrayList(basicSize);
		HashMap<String, Person> map = op.createHashMap(basicSize);
		
		for(int i=0; i<sample; i++) {
			s.addCell(new Label(0, i+1, ""+i*hop));
			s.addCell(new Label(1, i+1, String.valueOf(op.retrievePersonInArrayListByName(list, "Person"+i*hop))));
			s.addCell(new Label(2, i+1, String.valueOf(op.retrievePersonInHashMapByName(map, "Person"+i*hop))));	
		}
	}
	
	/**
	 * Test Search/Retrieve function by same order in the different size collection.
	 * @param s  the sheet where result located in.
	 */
	public void TestSearchBySize(WritableSheet s) throws RowsExceededException, WriteException {
		for(int i=1; i<=sample; i++) {
			ArrayList<Person> list = op.createArrayList(i*hop);
			HashMap<String, Person> map = op.createHashMap(i*hop);
			
			s.addCell(new Label(0, i, ""+ i*hop));
			s.addCell(new Label(1, i, String.valueOf(op.retrievePersonInArrayListByName(list, "Person5"))));
			s.addCell(new Label(2, i, String.valueOf(op.retrievePersonInHashMapByName(map, "Person5"))));
		}
	}
	
	/**
	 * Test Remove function by different order in the same size collection.
	 * @param s  the sheet where result located in.
	 */
	public void TestRemoveByOrder(WritableSheet s) throws RowsExceededException, WriteException {
		for(int i=0; i<sample; i++) {
			ArrayList<Person> list = op.createArrayList(basicSize);
			HashMap<String, Person> map = op.createHashMap(basicSize);
			
			s.addCell(new Label(0, i+1, ""+ i*hop));
			s.addCell(new Label(1, i+1, String.valueOf(op.removePersonFromArrayList(list, i*hop))));
			s.addCell(new Label(2, i+1, String.valueOf(op.removePersonFromHashMap(map, "Person"+i*hop))));	
		}
	}
	
	/**
	 * Test Remove function by same order in the different size collection.
	 * @param s  the sheet where result located in.
	 */
	public void TestRemoveBySize(WritableSheet s) throws RowsExceededException, WriteException {
		for(int i=1; i<=sample; i++) {
			ArrayList<Person> list = op.createArrayList(i*hop);
			HashMap<String, Person> map = op.createHashMap(i*hop);
			
			s.addCell(new Label(0, i, ""+ i*hop));
			s.addCell(new Label(1, i, String.valueOf(op.removePersonFromArrayList(list, 5))));
			s.addCell(new Label(2, i, String.valueOf(op.removePersonFromHashMap(map, "Person5"))));
		}
	}
	
	/**
	 * Test Insert function by different order in the same size collection.
	 * @param s  the sheet where result located in.
	 */
	public void TestInsertByOrder(WritableSheet s) throws RowsExceededException, WriteException {
		for(int i=0; i<=sample; i++) {
			ArrayList<Person> list = op.createArrayList(basicSize);
			HashMap<String, Person> map = op.createHashMap(basicSize);
			
			s.addCell(new Label(0, i+1, ""+ i*hop));
			s.addCell(new Label(1, i+1, String.valueOf(op.insertPersonIntoArrayList(list, i*hop))));
			s.addCell(new Label(2, i+1, String.valueOf(op.insertPersonIntoHashMap(map, "Person"+i*hop))));
		}
	}
	
	/**
	 * Test Insert function by same order in the different size collection.
	 * @param s  the sheet where result located in.
	 */
	public void TestInsertBySize(WritableSheet s) throws RowsExceededException, WriteException {
		for(int i=0; i<=sample; i++) {
			ArrayList<Person> list = op.createArrayList(i*hop);
			HashMap<String, Person> map = op.createHashMap(i*hop);
			
			s.addCell(new Label(0, i+1, ""+ i*hop));
			s.addCell(new Label(1, i+1, String.valueOf(op.insertPersonIntoArrayList(list, i*hop))));
			s.addCell(new Label(2, i+1, String.valueOf(op.insertPersonIntoHashMap(map, "Person"+i*hop))));
		}
	}
}
