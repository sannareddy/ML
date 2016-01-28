import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class PurchaseTest {
	private ArrayList<Integer> postcodes;                      // List to store  postcodes
	private ArrayList<Float> purchaseAmounts;				   // List to store  purchase amounts 	
	private ArrayList<Float> totalPayables;					   // List to store  total payables
	final int N;
	
	//Initialization Block
	{
		Random randomGen = new Random();
		N=randomGen.nextInt(4)+6; 								// Generates a Random Number between 6 and 9		
	}
	
	//Constructor to initialize ArrayLists	
	PurchaseTest(){
		postcodes = new ArrayList<Integer>(N);
		purchaseAmounts = new ArrayList<Float>(N);
		totalPayables = new ArrayList<Float>(N);		
	}
	
	//Method to initiate reading , validating and storing postcodes and purchase amounts
	public	void createOrders(){
		Scanner sc = new Scanner(System.in);
		int postcode;
		float purchaseAmount;
		boolean result = true;
		if(this.postcodes.size()>0){
			System.out.println("Attention ! Few entries found, entering new values would replace existed values. Do you want to continue? Y , N ");
			if(sc.nextLine().charAt(0)=='Y'){
				this.postcodes.clear();
				this.purchaseAmounts.clear();
				this.totalPayables.clear();
			}else{
				return;
			}
		}
		System.out.println("Hi There, You need to enter "+N+" Orders information");
		for(int i=1;i<=N;i++){
			do{				
				System.out.println("Enter postcode for order:"+i);			
				postcode = Integer.parseInt(sc.nextLine());
				result = validatePostCode(postcode);
				if(!result){
					System.out.println("Postcode should be greater than 4120 and less than 4124");
				}
			}while(!result);
			
			do{
				System.out.println("Enter purchase amount for order->"+i);			
				purchaseAmount = Float.parseFloat(sc.nextLine());
				result = validateAmount(purchaseAmount);
				if(!result){
					System.out.println("Amount should be greater than 59$ and less than 501$");
				}
			}while(!result);			
			storeOrder(postcode, purchaseAmount);		// Stores the order in ArrayList			
		}
		
	} 
	
	// Method to display the total payable for all customers 
	public void displayTotalPayables(){
		float purchaseAmount;
		int postcode;
		/*
		 * When this choice is mode twice, there is a chance of recalculating the totalPayables again though it is done already.
		 * To avoid such situation we are finding the total number of new entries by calculating the difference between both the collections. 
		 * If no difference in sizes, we assume that the calculation is made already so the loop will not be processed   
		 */
		int newEntries = Math.abs(totalPayables.size()-postcodes.size());		
		int j=totalPayables.size();
		//Loop to calculate total payables
		for(int i=0;i<newEntries;i++,j++){
			purchaseAmount = this.purchaseAmounts.get(j);
			postcode = postcodes.get(j);
			switch(postcode){
			case 4121:
				if(purchaseAmount < 400 ){
					this.totalPayables.add(purchaseAmount+8);
				}else{
					this.totalPayables.add(purchaseAmount);
				}
				break;
			case 4122:
				this.totalPayables.add(purchaseAmount+12);
				break;
			case 4123:
				this.totalPayables.add(purchaseAmount+15);
				break;
			}	
		}
		//Loop to print total payables
		System.out.printf("%-20s %-20s\n","POSTCODES","TOTAL PAYABLES");
		for(int i=0;i<this.postcodes.size();i++){
			System.out.printf("%-20d %-20.2f\n",this.postcodes.get(i),this.totalPayables.get(i) );
		}
	}
	
	
	// Method to validate postcodes
	private boolean validatePostCode(int postcode){
		if(postcode<4121 || postcode>4123){
			return false;
		}
		return true;
	}
	
	//Method to validate purchase amounts
	private boolean validateAmount(float purchaseAmount){
		if(purchaseAmount < 60 || purchaseAmount>500){
			return false;
		}
		return true;
	}	
	
	/* Method to store postcode and purchase amount for N customers */
	private void storeOrder(int postcode,float purchaseAmount){
		postcodes.add(postcode);
		purchaseAmounts.add(purchaseAmount);
	}
		
	private void displayMenu(){
		System.out.println("Enter your choice");
		System.out.println("1)Read, validate, and store postcode, and purchase amount for "+N+" customers");
		System.out.println("2)Calculate, store and display the total payable for all customers");
		System.out.println("3)Display postcode, and total payable for purchase amounts of $400 and above");
		System.out.println("4)Find and display the postcode/s with the highest purchase amount");
		System.out.println("5)Find and display the postcode/s with the lowest purchase amount");
		System.out.println("6)Sort and display the total payable in descending order");
		System.out.println("7)Search and display all purchase amount and Total payable with a given postcode");
		System.out.println("8)Exit from the application");
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int choice=8;
		Scanner sc = new Scanner(System.in);
		PurchaseTest testObj=new PurchaseTest();
		Purchase purchaseObj=new Purchase();
		System.out.println("Welcome to Online Grocery Store");
		ArrayList<Integer> dupPostcodes = null;
		ArrayList<Float> dupPurchaseAmounts = null;
		ArrayList<Float> dupTotalPayables = null;
		do{
			testObj.displayMenu();
			choice = Integer.parseInt(sc.nextLine());
			switch(choice){
			case 1:
				testObj.createOrders();		
				dupPostcodes = (ArrayList<Integer>)testObj.postcodes.clone();
				dupPurchaseAmounts = (ArrayList<Float>)testObj.purchaseAmounts.clone();
				break;
			case 2:
				testObj.displayTotalPayables();				
				dupTotalPayables = (ArrayList<Float>) testObj.totalPayables.clone();
				break;			
			case 3:		
				if(dupTotalPayables==null){
					System.out.println("You can enter this choice after choice 2 only");
					continue;
				}
				purchaseObj.displayPremiumPayables(dupPostcodes,dupPurchaseAmounts,dupTotalPayables);			
				break;
			case 4:
				if(dupTotalPayables==null){
					System.out.println("You can enter this choice after choice 2 only");
					continue;
				}				
				purchaseObj.displayCostliestOrders(dupPostcodes,dupPurchaseAmounts);
				break;
			case 5:
				if(dupTotalPayables==null){
					System.out.println("You can enter this choice after choice 2 only");
					continue;
				}	
				purchaseObj.displayCheapestOrders(dupPostcodes, dupPurchaseAmounts);
				break;
			case 6:
				if(dupTotalPayables==null){
					System.out.println("You can enter this choice after choice 2 only");
					continue;
				}
				purchaseObj.sortPayables(dupPostcodes, dupPurchaseAmounts, dupTotalPayables);
				break;
			case 7:
				if(dupTotalPayables==null){
					System.out.println("You can enter this choice after choice 2 only");
					continue;
				}
				int postcode;
				System.out.print("Enter a postcode:");
				postcode = Integer.parseInt(sc.nextLine());
				purchaseObj.displayPurchases(postcode,dupPostcodes,dupPurchaseAmounts,dupTotalPayables);
			}			
		}while(choice != 8);
		
	}
}