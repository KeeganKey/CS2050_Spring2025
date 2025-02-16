import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InvalidObjectException;
import java.io.IOException;
import java.util.Scanner;

public static void main(String[]args) {
	
//This needs to be the correct size of the first number of the array.

	String animalFilePath = "Animal.txt";
	
//Include the file text to write
	
	String animalTextToWrite = "7\nSloth Ace Leaves 12 15 Urban Jungle\n Bear Po Bamboo 550 20 Asian Passage\n "
			+ "Monkey Rafiki Fruit 27 17 Lost Forest\n Sloth Sid Leaves 15 12 Urban Jungle\nElephant Titan Grass 12500 4 Elephant Odyssey"
			+ "\nMonkey Louie Fruit 14 16 Lost Forest \nBear Baloo Honey 1050 18 Asian Passage";
	
//Creation of a new file object
	File file = new File(animalFilePath);

	Scanner scanner = new Scanner(file);
	
	try(FileWriter writer = new FileWriter(file)) { 
		
		writer.write(animalTextToWrite);
		System.out.println("File created");
		
//I tried to fix this line for a few hours, and got help from steph to solve it.
/*This line is to parse out the first integer of the file and read it to find the 
 * number of animals we need.
 */
		int animalNumber = Integer.parseInt(scanner.nextLine());
		
//Creating the array based on size.
		
		Animal[] animalArray = new Animal[animalNumber];
		
//Fix this here!
//Reassign the variable 'line'
		
	for (int i = 0; i < animalNumber; i++) {
		String line = scanner.nextLine().trim();
		
		String[] animalData = line.split("");
		
		String name = animalData[0].trim();
		int age = Integer.parseInt(animalData[1].trim());
		String type = animalData[2].trim();
		
		
//This is a switch case to fill each index of the array
//With information about each animal as we parse them.
		
//We need room for a name, age, food, weight, sleep(int), and location
		
		switch(type.toUpperCase()){
		
		case "BEAR":
			animalArray[i] = new Bear(name, age);
			break;
		case "MONKEY":
			animalArray[i] = new Monkey(name, age);
			break;
		case "ELEPHANT":
			animalArray[i] = new Elephant(name, age);
			break;
		case "SLOTH":
			animalArray[i] = new Sloth(name, age);
			break;
		}
		
	
		}
	}
	
	
	catch(FileNotFoundException e) {
//Exception catch
		System.err.println("File generation/reading error.");
	}
	
//It was recommended that I add this in tutoring,
//It's pretty clever but I can't take credit.
	finally {
		if (scanner != null) {
			scanner.close();
		}
	}
	
}


/* bear elephant monkey sloth */


public class Animal {

//Encapsulation for our variables we don't want users to alter

//Constructor
	
	public Animal(String name, int age, String food, int weight, String location) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.location = location;
		this.food = food;
	}
	
//Encapsulation and static variables
	
	private String name;
	private int age;
	private int weight;
	String location;
	private String food;
	
	
	
	public String getName() {
		return name;
	}
	
	public String setName() {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight() {
		this.weight = weight;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation() {
		this.location = location;
	}
	 
	public String getFood() {
		return food;
	}
	
	public void setFood() {
		this.food = food;
	}
		public void printAnimalInfo() {
			System.out.println();
		}
	
}
	public class Bear extends Animal{
//I called the super explicitly here, not sure if this is ideal.
		
		public Bear(String name, int age, String food, int weight, String location) {
			super(name, age, food, weight, location);
		}
	}

	public class Elephant extends Animal{
		public Elephant(String name, int age, String food, int weight, String location) {
			super(name, age, food, weight, location);
		}
	
	}

	public class Monkey extends Animal {
		public Monkey(String name, int age, String food, int weight, String location) {
			super(name, age, food, weight, location);
		}
	}

	public class Sloth extends Animal {
		public Sloth(String name, int age, String food, int weight, String location) {
			super (name, age, food, weight, location);
		}
	
	}
