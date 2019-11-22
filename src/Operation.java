import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class provides the detail operations for Collection API and record time-cost for each operation.
 * Include Collection API: ArrayList, HashMap.
 * Include Operation: Insert, Remove, Search.
 */
public class Operation {
	/**
	 * Generate an integer from 0-99 as Person's age;
	 */
	public int generateRandomAge() {
		Random r = new Random();
		return r.nextInt(100);
	}
	
	/**
	 * Create a Person object and insert it to specific location of ArrayList.
	 * Execute 10000 times for accuracy, return average execute time.
	 */
	public long insertPersonIntoArrayList(ArrayList<Person> existList, int location) {	
		Person insertPerson = new Person("Insert", generateRandomAge());
		/* Repeat 10000 times for accuracy */
		long startTime = System.nanoTime();
		for(int i=0; i<10000; i++ )
			existList.add(location+i, insertPerson);
		long endTime = System.nanoTime();
		return (endTime - startTime)/10000;
	}
	
	/**
	 * Create a Person object and insert it to specific key of HashMap.
	 * Execute 100000 times for accuracy, return average execute time.
	 */
	public long insertPersonIntoHashMap(HashMap<String, Person> existMap, String name) {
		Person insertPerson = new Person("Insert", generateRandomAge());
		/* Repeat 10000 times for accuracy */
		long startTime = System.nanoTime();
		for(int i=0; i<10000; i++)
			existMap.put(name, insertPerson);
		long endTime = System.nanoTime();
		return (endTime - startTime)/10000;
	}
	
	/**
	 * Search specific Person object in ArrayList by given "name", return period time.
	 */
	public long retrievePersonInArrayListByName(ArrayList<Person> existList, String searchName) {
		/* Repeat 100 times for accuracy */
		long startTime = System.nanoTime();
		for(int j=0; j<100; j++) {
			for(int i=0; i<existList.size(); i++) {
				Person thisPerson = existList.get(i);
				if(searchName.equals(thisPerson.getName())) 
					break;
			}	
		}
		long endTime = System.nanoTime();
		return (endTime - startTime)/100;
	}
	
	/**
	 * Search specific Person object in HashMap by given "name", return period time.
	 */
	public long retrievePersonInHashMapByName(HashMap<String, Person> existList, String searchName) {	
		/* Repeat 100 times for accuracy */
		long startTime = System.nanoTime();
		for(int i=0; i<100; i++)
			existList.get(searchName);
		long endTime = System.nanoTime();
		return (endTime - startTime)/100;
	}
	
	/**
	 * Remove object by given Person object reference in ArrayList, return period time.
	 * Use index number as unique object reference of specific Person object.
	 */
	public long removePersonFromArrayList(ArrayList<Person> existList, int index) {
		long startTime = System.nanoTime();
		existList.remove(index);
		long endTime = System.nanoTime();
		return (endTime - startTime);
	}
	
	/**
	 * Remove object by given Person object reference in HashMap, return period time.
	 * Use name of Person (Key) as unique object reference of specific Person object.
	 */
	public long removePersonFromHashMap(HashMap<String, Person> existList, String key) {		
		long startTime = System.nanoTime();
		existList.remove(key);
		long endTime = System.nanoTime();
		return (endTime - startTime);
	}
	
	/**
	 * Create an ArrayList with specific amount of Person object.
	 * @param amount  amount of Person object; size of ArrayList.
	 * @return ArrayList with many Person object.
	 */
	public ArrayList<Person> createArrayList(int amount) {
		ArrayList<Person> list = new ArrayList<Person>();
		for(int i=0; i<amount; i++ )
			list.add(new Person("Person"+i, generateRandomAge()));
		return list;
	}
	
	/**
	 * Create an HashMap with specific amount of Person object. Use "name" as Key.
	 * @param amount  amount of Person object; size of HashMap.
	 * @return HashMap with many Person object.
	 */
	public HashMap<String, Person> createHashMap(int amount) {
		HashMap<String, Person> map = new HashMap<String, Person>();
		for(int i=0; i<amount; i++) {
			Person eachPerson = new Person("Person"+i, generateRandomAge());
			map.put(eachPerson.getName(), eachPerson);
		}
		return map;
	}
}
