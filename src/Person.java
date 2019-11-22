public class Person 
{
	private String name;
	private int age;

	public Person (String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	public String toString()
	{
		return "Person: " + name + " (" + age + ")";
		//eg: Person: Person500000 (2)
	}

	public boolean equals (Object o)
	{
		return (o instanceof Person && ((Person)o).name.equals(name));
		//true / false
	}

	public int hashCode() {
		return name.hashCode();
		// -1410586416
	}

	public String getName()
	{
		return name;
		// Person500000
	}

	public int getAge()
	{
		return age;
	}

}
