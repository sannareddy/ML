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
				break;
			case 8:
				System.out.println("Thank you for using Online Grocery Store");
			}			
		}while(choice != 8);
		
	}
