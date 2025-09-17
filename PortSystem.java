import java.awt.Container;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

public class PortSystem {
	
	static class Containers {
		private int ContainerWeight;
		private String ContainerDesc;
	private String ContainersID;
	
	public Containers(String ID, String Description, int Weight) {
		ContainersID = ID;
		ContainerDesc = Description;
		ContainerWeight = Weight;
	}
	public String toString() {
		return ContainersID + " | " + ContainerDesc + " | " + ContainerWeight + "Kg: ";
	}
	}
	static class Ship{
		private String ShipName;
		private String ShipCaptain;
		
		public Ship(String Name, String Capt) {
			ShipName = Name;
			ShipCaptain = Capt;
		}
		public String toString() {
			return " Ship:  " + ShipName + " Capt: " + ShipCaptain ;
		}
	}
	
	public static class  PortManagementSystem {
		private static ArrayDeque<Containers> ContainerStack = new ArrayDeque<>();
		private static ArrayDeque<Ship> ShipQue = new ArrayDeque<>();
		private static Scanner scan = new Scanner(System.in); 
		
	public static void main(String[] args) {
		int choice;
		
		do {
			System.out.println("\n PORT CONTAINTER MANAGEMENT SYSTEM ");
			System.out.println(" [1] STORE CONTAINER(PUSH) ");
			System.out.println(" [2] VIEW PORT CONTAINERS ");
			System.out.println(" [3] REGISTER ARRIVING SHIP(enqueue) ");
			System.out.println(" [4] VIEW WAITING SHIPS ");
			System.out.println(" [5] LOAD NEXT SHIP ");
			System.out.println(" [0] EXIT ");
			System.out.print("ENTER CHOICE: ");
			choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			case 1:
				storeContainer();
				break;
			case 2:
				viewContainers();
				break;
			case 3:
				registerShip();
				break;
			case 4:
				viewShips();
				break;
			case 5:
				loadNxtShip();
				break;
			case 0:
				System.out.println(" Exiting System! Goodbye! ");
				break;
			default:
				System.out.println(" Invalid choice. Try again! ");
			}
		} while(choice!=0);
	

	}
	
	private static void storeContainer() {
		System.out.println(" Enter Container ID: ");
		String ContainersID= scan.nextLine();
		System.out.println(" Enter Description: ");
		String ContainerDesc = scan.nextLine();
		System.out.println(" Enter weight(kg): ");
		int ContainerWeight = Integer.parseInt(scan.nextLine());
		
		Containers container = new Containers(ContainersID, ContainerDesc , ContainerWeight);
		ContainerStack.push(container);
		
		System.out.println("Stored: " + container);
	}
	private static void viewContainers() {
		 if (ContainerStack.isEmpty()) {
	            System.out.println("No containers at the port.");
	            return;
	}
		 System.out.println("\nTOP - ");
		 for(PortSystem.Containers c : ContainerStack) {
			 System.out.println(c);
		 }
		 System.out.println("\nBottom - ");
		 
	}
	private static void registerShip() {
		 System.out.print("Enter ship name: ");
	        String name = scan.nextLine();
	        System.out.print("Enter captain's name: ");
	        String captain = scan.nextLine();

	        Ship ship = new Ship(name, captain);
	        ShipQue.offer(ship);

	        System.out.println("Registered: " + ship);
	}
	private static void viewShips() {
		if(ShipQue.isEmpty()) {
			System.out.println("No ships are waiting at the moment");
			return;
		}
		System.out.println("\nFRONT - ");
		for(Ship s: ShipQue) {
			System.out.println(s);
		}
		System.out.println(" - REAR ");
	}
	private static void loadNxtShip() {
		if(ContainerStack.isEmpty()) {
			System.out.println("No Containers available to load at the moment");
			return;
			
		}
		 if (ShipQue.isEmpty()) {
	            System.out.println("No ships waiting to load.");
	            return;
	}
		 PortSystem.Containers c = ContainerStack.pop();
		 Ship s = ShipQue.poll();
		 System.out.println("Loaded: " + c + " → " + s);
	        System.out.println("Remaining containers: " + ContainerStack.size());
	        System.out.println("Remaining ships waiting: " + ShipQue.size());
	}
	}
}

