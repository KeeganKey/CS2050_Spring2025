import java.util.*;
import java.io.*;

public class CarvanaVendingMachineIteration02KeeganKey
{

    // Main method to run the vending machine system
    public static void main(String[] args)
    {

        System.out.println();
        Scanner scanner = new Scanner(System.in);

        VendingMachine vendingMachine;

        // Prompt the user for number of floors and spaces
        System.out.print("Enter the number of floors: ");
        int floors = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the number of spaces: ");
        int spaces = Integer.parseInt(scanner.nextLine());

        vendingMachine = new VendingMachine(floors, spaces);

        while (true)
        {
            // Main menu for the user to interact with the vending machine
            System.out.println(" ||| Car Vending Machine ||| ");

            System.out.println("1. Load your file");
            System.out.println("2. Display Vending Machine");
            System.out.println("3. Retrieve a car");
            System.out.println("4. Print Inventory sorted by price");
            System.out.println("5. Print Inventory sorted by year");
            System.out.println("6. Search by Manufacturer/Type");
            System.out.println("7. Add Car to Wash Queue");
            System.out.println("8. Process Car Wash Queue");
            System.out.println("9. Sell a car");
            System.out.println("10. Test drive a car");
            System.out.println("11. Exit");

            System.out.println("");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice)
            {
                case 1:
                    // Load cars from a file
                    System.out.print("Enter file name: ");
                    String file = scanner.nextLine();
                    loadFile(vendingMachine, file);
                    break;

                case 2:
                    // Display the current state of the vending machine
                    vendingMachine.displayMachine();
                    break;

                case 3:
                    // Retrieve a car from a specific floor and space
                    System.out.print("Enter floor: ");
                    int floor = scanner.nextInt() - 1;

                    System.out.print("Enter space: ");
                    int space = scanner.nextInt() - 1;

                    scanner.nextLine();
                    vendingMachine.retrieveCar(floor, space);
                    break;

                case 4:
                    // Display cars sorted by price
                    vendingMachine.sortedInventoryByPrice();
                    break;

                case 5:
                    // Display cars sorted by year
                    vendingMachine.sortedInventoryByYear();
                    break;

                case 6:
                    // Search cars by manufacturer and type
                    System.out.print("Enter manufacturer: ");
                    String manufacturer = scanner.nextLine();

                    System.out.print("Basic/Premium: ");
                    String type = scanner.nextLine();

                    vendingMachine.searchByManufacturer(manufacturer, type);
                    break;

                case 7:
                    // Add a car to the wash queue
                    System.out.print("Enter floor: ");
                    int washFloor = scanner.nextInt() - 1;

                    System.out.print("Enter space: ");
                    int washSpace = scanner.nextInt() - 1;

                    scanner.nextLine();
                    vendingMachine.addToCarWashQueue(washFloor, washSpace);
                    break;

                case 8:
                    // Process the car wash queue
                    vendingMachine.processCarWashQueue();
                    break;

                case 9:
                    // Sell a car
                    System.out.print("Enter floor: ");
                    int sellFloor = scanner.nextInt() - 1;

                    System.out.print("Enter space: ");
                    int sellSpace = scanner.nextInt() - 1;

                    scanner.nextLine();
                    vendingMachine.sellCar(sellFloor, sellSpace);
                    break;

                case 10:
                    // Test drive a car
                    System.out.print("Enter floor: ");
                    int tdFloor = scanner.nextInt() - 1;

                    System.out.print("Enter space: ");
                    int tdSpace = scanner.nextInt() - 1;

                    vendingMachine.testDriveCar(tdFloor, tdSpace);
                    break;

                case 11:
                    // Exit the program
                    System.out.println("Exiting.");
                    return;
            }
        }
    }

    // Method to load cars from a file into the vending machine
    // Reads each parameter from the file individually and allocates it in the correct data structure

    public static void loadFile(VendingMachine vendingMachine, String filename)
    {
        try (Scanner fileScanner = new Scanner(new File(filename)))
        {
            while (fileScanner.hasNextLine())
            {

                String type = fileScanner.next();
                int floor = fileScanner.nextInt();
                int space = fileScanner.nextInt();
                int year = fileScanner.nextInt();
                double price = fileScanner.nextDouble();
                String make = fileScanner.next();
                String model = fileScanner.nextLine().trim();

                String key = floor + "" + space;

                Car car;

                if (type.equals("B"))
                {
                    car = new BasicCar(year, price, make, model);
                }

                else (type.equals("P"))
                {
                    car = new PremiumCar(year, price, make, model);
                }

                vendingMachine.addCar(floor, space, car);
            }
        }

        catch (FileNotFoundException e)
        {
            System.err.println("File not found");
        }
    }
}

//Car is now abstract to create basic and premium cars

abstract class Car
{

    private int year;
    private double price;
    private String make;
    private String model;

    public Car(int year, double price, String make, String model)
    {
        this.year = year;
        this.price = price;
        this.make = make;
        this.model = model;
    }

    public int getYear()
    {
        return year;
    }

    public double getPrice()
    {
        return price;
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    @Override
    public String toString()
    {
        return make + " " + model + " " + year + " $" + price;
    }
}

// Two classes that utilize the abstract Car class to make our basic and premium parameters

class BasicCar extends Car
{
    public BasicCar(int year, double price, String make, String model)
    {
        super(year, price, make, model);
    }
}

class PremiumCar extends Car
{
    public PremiumCar(int year, double price, String make, String model)
    {
        super(year, price, make, model);
    }
}

class CarStall
{

//Instance declarations
    int floor;
    int space;
    Car car;

    public CarStall(int floor, int space, Car car)
    {
        this.floor = floor;
        this.space = space;
        this.car = car;
    }

    // Method to get the key for the car stall
    public String getKey()
    {
        return floor + "" + space;
    }

    @Override
    public String toString()
    {
        return "Floor " + (floor + 1) + " Space " + (space + 1) + ": " + car.toString();
    }
}

// Manually implemented linked list node class
class Node
{

    CarStall data;
    Node next;

    public Node(CarStall data)
    {
        this.data = data;
        this.next = null;
    }
}

class VendingMachine
{
    private int floors;
    private int spaces;

//The head of the linked list-
    private Node head = null;
    private Map<String, CarStall> carMap = new HashMap<>();
    private Queue<Car> carWashQueue = new LinkedList<>();

    public VendingMachine(int floors, int spaces)
    {
        this.floors = floors;
        this.spaces = spaces;
    }

    // Method to add a car to the vending machine
    public boolean addCar(int floor, int space, Car car)
    {
        if (floor < 1 || floor > floors || space < 1 || space > spaces)
        {
            System.out.println("Invalid position: " + floor + "" + space);
            return false;
        }

        String key = floor + "" + space;

        if (carMap.containsKey(key))
        {
            System.out.println("Space is full at: " + key);
            return false;
        }

//Algorithm to handle all of our linked list manually

        CarStall stall = new CarStall(floor, space, car);
        Node newNode = new Node(stall);

        if (head == null)
        {
            head = newNode;
        }

        else
        {
            Node current = head;

            while (current.next != null)
            {
                current = current.next;
            }
            current.next = newNode;
        }

        carMap.put(key, stall);
        return true;
    }

    // Method to display the current state of the vending machine
    public void displayMachine()
    {
        Node current = head;

        while (current != null)
        {
            CarStall stall = current.data;
            System.out.println(stall);
            current = current.next;
        }
    }

    // Method to retrieve a car from a specific floor and space
    public void retrieveCar(int floor, int space)
    {
        String key = floor + "" + space;
        CarStall stall = carMap.remove(key);

        if (stall == null)
        {
            System.out.println("No car located at " + (floor + 1) + "" + (space + 1));
            return;
        }

        // Now remove the node manually
        if (head == null) return;

        if (head.data.equals(stall))
        {
            head = head.next;
        }

//Pulled almost directly from our class notes
        else
        {
            Node current = head;
            while (current.next != null)
            {
                if (current.next.data.equals(stall))
                {
                    current.next = current.next.next;
                    break;
                }
                current = current.next;
            }
        }

        System.out.println("Car retrieved: " + stall.car);
    }

    // Method to sell a car
    // Simple iteration and then manual removal of the node that contains our car.
    public void sellCar(int floor, int space)
    {
        //Making sure to keep the key naming convention the same.
        String key = floor + "" + space;
        CarStall stall = carMap.remove(key);

        if (stall != null)
        {
            // Now remove the node manually
            if (head == null) return;

            if (head.data.equals(stall))
            {
                head = head.next;
            }

            else
            {

                Node current = head;
                while (current.next != null)
                {
                    if (current.next.data.equals(stall))
                    {
                        current.next = current.next.next;
                        break;
                    }
                    current = current.next;
                }
            }

            System.out.println("Car sold: " + stall.car);
        }

        else
        {
            System.out.println("No car located at " + (floor + 1) + "" + (space + 1));
        }
    }

    // Method to display cars sorted by price
    public void sortedInventoryByPrice()
    {
        List<Car> cars = new ArrayList<>();
        Node current = head;

        while (current != null)
        {
            cars.add(current.data.car);
            current = current.next;
        }

    //The new sort, using a comparator
        cars.sort(Comparator.comparingDouble(Car::getPrice));
        System.out.println("Sorted Inventory by Price: ");

        for (Car car : cars)
        {
            System.out.println(car);
        }
    }

    // Method to display cars sorted by year
    public void sortedInventoryByYear()
    {
        List<Car> cars = new ArrayList<>();
        Node current = head;

        while (current != null)
        {
            cars.add(current.data.car);
            current = current.next;
        }

    //Also a new comparator sort
        cars.sort(Comparator.comparingInt(Car::getYear));
        System.out.println("Sorted Inventory by Year: ");

        for (Car car : cars)
        {
            System.out.println(car);
        }
    }

    // Method to retrieve and test drive a car from a specific floor and space
    public void testDriveCar(int floor, int space)
    {

        String key = floor + "" + space;
        CarStall stall = carMap.get(key);

        if (stall != null)
        {
            System.out.println("Car retrieved: " + stall.cqueuear);
        }

        else
        {
            System.out.println("No car located there.");
        }
    }

    // Method to search cars by manufacturer and type
    public void searchByManufacturer(String manufacturer, String type)
    {

        List<Car> matchingCars = new ArrayList<>();
        Node current = head;

        while (current != null)
        {
            Car car = current.data.car;

            //This line was from a similar project, this is for us to check the parameters of our cars and print by manufacturer.
            boolean typeMatches = (type.equalsIgnoreCase("Basic") && car instanceof BasicCar) ||
                                  (type.equalsIgnoreCase("Premium") && car instanceof PremiumCar);

            //if our car matches basic/premium, and the cars manufacturer matches, then we add it.
            if (typeMatches && car.getMake().equalsIgnoreCase(manufacturer))
            {
                matchingCars.add(car);
            }

            current = current.next;
        }

        if (matchingCars.isEmpty())
        {
            System.out.println("No cars available.");
            return;
        }

        matchingCars.sort(Comparator.comparing(Car::getMake));
        System.out.println("Matching Cars (Manufacturer / Type): ");
        for (Car car : matchingCars)
        {
            System.out.println(car);
        }
    }

    // Method to add a car to the car wash queue
    public void addToCarWashQueue(int floor, int space)
    {
        String key = floor + "" + space;
        CarStall stall = carMap.get(key);

        if (stall != null)
        {
            carWashQueue.offer(stall.car);
            System.out.println("Car added to wash queue: " + stall.car);
        }

        else
        {
            System.out.println("No car located at " + (floor + 1) + "" + (space + 1));
        }
    }

    // Method to process the car wash queue
    //If the q isnt empty we wash the cars one at a time, FIFO structure
    public void processCarWashQueue()
    {
        //If empty our car wash is empty
        if (carWashQueue.isEmpty())
        {
            System.out.println("Car wash is now empty.");
            return;
        }

        while (!carWashQueue.isEmpty())
        {
            Car car = carWashQueue.poll();
            System.out.println("Washing car: " + car);
        }
    }
}
