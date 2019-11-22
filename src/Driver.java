import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * This class provides a driver to run the test, record and graph process.
 * Implement the requirement of Task1, and call for function to implement Task2.
 */
public class Driver {
	public static void main(String[] args) throws RowsExceededException, WriteException, BiffException, IOException {
		// Define how many instance to create for Task1
		int instanceAmount = 1000000;
		Operation op = new Operation();
		
		/* Create ArrayList and Measure how long to insertion */
		ArrayList<Person> list = new ArrayList<Person>();
		long time1 = System.nanoTime();
		for(int i=0; i<instanceAmount; i++ )
			list.add(new Person("Person"+i, op.generateRandomAge()));
		long time2 = System.nanoTime();
		System.out.println("The insert operations in ArrayList takes " + (time2-time1)/instanceAmount + " ns");

		/* Create HashMap and Measure how long to insertion */
		HashMap<String, Person> map = new HashMap<String, Person>();
		long time3 = System.nanoTime();
		for(int i=0; i<instanceAmount; i++) {
			Person eachPerson = new Person("Person"+i, op.generateRandomAge());
			map.put(eachPerson.getName(), eachPerson);
		}
		long time4 = System.nanoTime();
		System.out.println("The insert operations in HashMap takes " + (time4-time3)/instanceAmount + " ns");
		
		/* Test the operations in Task2 */
		DataRecord run = new DataRecord(instanceAmount);
		run.RecordAndGraph();
	}
}
