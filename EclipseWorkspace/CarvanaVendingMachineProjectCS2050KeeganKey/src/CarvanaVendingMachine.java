import java.util.Scanner;

//A large portion of this code was written on my Linux machine, so the versioning may look strange.

//Linkage between sort and main
//Input for sort is compressed 2D array
//print the sorted compressed 2D array in main

//File input!
//Try catch for file input-

public class CarvanaVendingMachine {

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input your tower size floors: ");
        int floors = scanner.nextInt();

        System.out.println("Enter your tower size spaces: ");
        int spaces = scanner.nextInt();

        car[][] tower = new car[floors][spaces];
        
        VendingMachine vm = new VendingMachine(floors, spaces, tower);

        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("Welcome to the car tower terminal! Select an option");
        int userChoice;

        do {
            System.out.println("Please choose an option:");
            System.out.println("1.Inventory report ");
            System.out.println("2.Search a car space ");
//Adding cars not done through file input
            System.out.println("3.Add a car ");
            System.out.println("4.Remove a car from your tower ");
            System.out.println("5.Check total number of cars ");
            System.out.println("6.Display list sorted by price ");
            System.out.println("7.Display list sorted by year ");
       System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");


            userChoice = scanner.nextInt();

            switch (userChoice) {
            
                case 1:
                    System.out.println("Displaying inventory report:");
                    vm.inventoryReport();
                    break;
                    
                case 2:
                    System.out.println("Searching a space...");
                    vm.findCarIndex();
                    break;
                    
                case 3:
                    System.out.println("Adding a car to your tower");
                    
                    System.out.println("What is your car's name?:");
                    String name = scanner.next();
                    
                    System.out.println("What's your car's production year?:");
                    int year = scanner.nextInt();
                    
                    System.out.println("Enter the price of your car:");
                    double price = scanner.nextDouble();
                    
                    System.out.println("Enter your car's floor:");
                    int floor = scanner.nextInt();
                    
                    System.out.println("Enter yourc car's space:");
                    int space = scanner.nextInt();
                    
                    vm.addCar(name, year, price, floor, space);
                    
                    break;
                    
                case 4:
                    System.out.println("Removing a car from your tower");
                    break;
                    
                case 5:
                    System.out.println("Checking total number of cars...");
                    System.out.println("You have" + "" + vm.carCount() + "" + "cars.");
                    break;
                    
                case 6:
                	System.out.println("Here is your inventory sorted by price: ");
                	break;
                	
                case 7:
                	System.out.println("Here is your inventory sorted by year: ");
                	break;
                	
                case 8:
                	System.out.println("Exit.");
                	break;
         
            } 

        } while (userChoice < 9);

       scanner.close();
    }
}


//End Main
	
//Try catch!
	
	class car{
		
		private String name;
		private int year;
		private double price;
		
		public car(String name, int year, double price) {
			
			this.name = name;
			this.year = year;
			this.price = price;
		}
		
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			
			public int getYear() {
				return year;
			}	
			public void setYear(int year) {
				this.year = year;
			}
		
			public double getPrice() {
				return price;
			}
			public void setPrice(double price) {
				this.price = price;
			}
	}
			
		
class VendingMachine {
		
//Constructor for a tower
		
		//Floors and Spaces !
		
//Needs work!
		
		public car[][] tower;
		
		private int floors;
		private int spaces;
		
//Vending machine constructor
		
/*     public VendingMachine(int floors, int spaces) {
        this.floors = floors;
        this.spaces = spaces;
        this.tower = new Car[floors][spaces];
    }
*/
		public VendingMachine(int floors, int spaces, car[][] tower) {
			
			this.tower = new car[floors][spaces];
			this.floors = floors;
			this.spaces = spaces;
		}
	
	public void addCar(String name, int year, double price, int floors, int spaces) {
			
			
			if (floors >= 0 && floors < this.floors && spaces >= 0 && spaces < this.spaces) {
				
				if(tower[floors][spaces] == null) {
					
					tower[floors][spaces] = new car(name, year, price);
					
				}
				
				else {
	                System.out.println("This spacenis already taken.");
	            }
				
//End first loop
	        } 
			
			else {
	            System.out.println("Invalid position.");
			}
		}

//Find car index
	public void findCarIndex() {
		
        Scanner indexScanner = new Scanner(System.in);

        	System.out.println("What floor would you like to check?");
        
        int checkFloor = indexScanner.nextInt();

        	System.out.println("Which space would you like to check?");
        
        int checkSpace = indexScanner.nextInt();

        if (checkFloor >= 0 && checkFloor < this.floors && checkSpace >= 0 && checkSpace < this.spaces) {
            car carAtIndex = tower[checkFloor][checkSpace];

            if (carAtIndex != null) {
            	
                System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
                System.out.println("The car at this floor/space is: " + checkFloor + checkSpace);
                
                System.out.println("Name: " + carAtIndex.getName());
                System.out.println("Year: " + carAtIndex.getYear());
                System.out.println("Price: " + carAtIndex.getPrice());
                System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");

            } 
            
            else {
                System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
                System.out.println("This space is empty!");
           }
        } 
        
        else {
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("This floor/space doesn't exist!");
        }
      
	}
	
	public int carCount() {
		
		int carCount = 0;
		
		for(int i = 0; i < this.floors; i++) {
			
			for(int j = 0; j < this.spaces; j++) {
				
				if (tower[i][j] != null) {
				 
				carCount++;
			}
			}
		}
		return carCount;
}
	
	   public void inventoryReport() {
		   
	        String report = "";
	        
	        for (int i = 0; i < this.floors; i++) {
	        	
	            for (int j = 0; j < this.spaces; j++) {
	            	
	                if (tower[i][j] != null) {
	                	
	                    report += "[" + i + "][" + j + "]: " + tower[i][j].getName() + "\n";
	                } 
	                
	                else {
	                    report += "At tower[" + i + "][" + j + "]: EMPTY\n";
	                }
	            }
	        }
	        System.out.println(report);
	    }
	   
//Sorting algorithms for the cars
	
	   
	   public static void sortByPrice(car[] cars) {
		   
		   for (int i = 0; i < cars.length - 1; i++) {
			   for (int j = 0; j < cars.length - 1 - i; j++) {
				   if (cars[j].getPrice() > cars[j + 1].getPrice()) {
					   
					   car temp = cars[j];
					   cars[j] = cars[j + 1];
					   cars[j + 1] = temp;
				   }
			   }
		   }
		   
	       
		   for (car car : cars) {
		   System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
			   System.out.println("Name: " + car.getName());
			   System.out.println("Price: " + car.getPrice());
			   System.out.println("Year: " + car.getYear());
			   System.out.println("");
		   System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");

		   }
		   
	   }
	   
	   public static void sortByYear(car[] cars) {
		   
		   for (int i = 0; i < cars.length - 1; i++) {
	            for (int j = 0; j < cars.length - 1 - i; j++) {
	                if (cars[j].getYear() > cars[j + 1].getYear()) {
	                   
	                    car temp = cars[j];
	                    cars[j] = cars[j + 1];
	                    cars[j + 1] = temp;
	                }
	            }
	        }
		   
		   System.out.println("");
		   
		   for (car car : cars) {
		       System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
			   System.out.println("Name: " + car.getName());
			   System.out.println("Price: " + car.getPrice());
			   System.out.println("Year: " + car.getYear());
			   System.out.println("");
		       System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");

			   
		   }
	   }

/*
			public int getFloors() {
			    return floors;
			}

			public void setFloors(int floors) {
			    this.floors = floors;
			}
			public int getSpaces() {
			    return spaces;
			}

			public void setSpaces(int spaces) {
			    this.spaces = spaces;
			}

*/
		}
		

	
/*		public void addCar(String name, int year, double price, int floors, int spaces) {
			
			
			if (floors >= 0 && floors < this.floors && spaces >= 0 && spaces < this.spaces) {
				
				if(tower[floors][spaces] == null) {
					
					tower[floors][spaces] = new car(name, year, price);
					
				}
				
				else {
	                System.out.println("This spacenis already taken.");
	            }
				
//End first loop
	        } 
			
			else {
	            System.out.println("Invalid position.");
			}
		}
		*/
		
		
// Type mismatch
// Cannot evaluate blank space in array to null

/*
		public boolean addCar(int floors, int spaces, car car) {
				
		if (row >= 0 && row < rowMax && cols >= 0 && cols < columnMax) {
			if (tower[floors][spaces] == null) {
		
				tower[row][cols] = car;
				return true;
			}
			
			else {
				System.out.println("The slot is occupied");
				return false;
			}
		}
*/
		
//		public int findCarIndex() {
			
// Method for returning car index
			
//			Scanner scanner = new Scanner(System.in);
		
// 		String findIndex = scanner.next();

/*			Scanner indexScanner = new Scanner(System.in);
			
			System.out.println("What floor would you like to check?" + "");
			int checkFloor = indexScanner.nextInt();
			
			System.out.println("Which space would you like to check?" + "");
			int checkSpace = indexScanner.nextInt();
			
			if (checkFloor >= 0 && checkFloor < 0 && checkSpace >= 0 )	{
				
				int value = vendingMachine.tower[checkFloor][checkSpace];
				
				System.out.println("The value at index" + checkFloor + checkSpace + "is:" + car);
			}
		}
*/	
		
/*	public void findCarIndex() {
			
		
            Scanner indexScanner = new Scanner(System.in);

            	System.out.println("What floor would you like to check?");
            
            int checkFloor = indexScanner.nextInt();

            	System.out.println("Which space would you like to check?");
            
            int checkSpace = indexScanner.nextInt();

            if (checkFloor >= 0 && checkFloor < this.floors && checkSpace >= 0 && checkSpace < this.spaces) {
                car carAtIndex = tower[checkFloor][checkSpace];

                if (carAtIndex != null) {
                	
                    System.out.println("The car at floor " + checkFloor + ", space " + checkSpace + " is: ");
                    
                    System.out.println("Name: " + carAtIndex.getName());
                    System.out.println("Year: " + carAtIndex.getYear());
                    System.out.println("Price: " + carAtIndex.getPrice());
                } 
                
                else {
                    System.out.println("This space is empty!");
               }
            } 
            
            else {
                System.out.println("This floor/space doesn't exist!");
            }
          
		}
	*/
		
//Method to count the number of cars in the array
	
/*
public int carCount() {
	
		for(int i = 0; i < this.floors; i++) {
			
			for(int j = 0; j < this.spaces; j++) {
				
				if (tower[i][j] != null) {
					
				 int carCount;
				 
				carCount++;
			}
				
	return carCount();
			}
		}
}
*/
		
//Method for printing the inventory report
//Data types

	/*
		public String inventoryReport(int floors, int spaces, car[][] tower) {
			
			 String report = "";
		
//Array iteration
			for(int i = 0; i < this.floors; i++) {
				
				for(int j = 0; j < this.spaces; j++) {
				
					 if (tower[i][j] != null) {
//Syntax changes
			                report += "[" + i + "][" + j + "]: " + tower[i][j] + "\n";
			         } 
					 
					 else {
			                report += "At tower[" + i + "][" + j + "]: EMPTY\n";
					 }
				}
		}
		}

	

		return report;
	}
		
//Method for removing a car from a position
		
//End vending machine class
		}
		
		}
	}
	*/
  