import java.util.ArrayList;


public class Purchase {
	/* Method to display postcode, and total payable for purchase amounts of $400 and above */
	public void displayPremiumPayables(ArrayList<Integer> postcodes,ArrayList<Float> purchaseAmounts,ArrayList<Float> totalPayables){
		int flag=0;
		System.out.printf("%-20s %-20s\n","POSTCODES","TOTAL PAYABLES");
		for(int i=0;i<postcodes.size();i++){
			if(purchaseAmounts.get(i)>=400){
				System.out.printf("%-20d %-20.2f\n",postcodes.get(i),totalPayables.get(i) );
				flag=1;
			}
		}
		if(flag==0){
			System.out.println("There are no purchases with 400 and more amount");
		}
	}
	
	/* Method to find and display the postcode/s with the highest purchase amount */
	public void displayCostliestOrders(ArrayList<Integer> postcodes,ArrayList<Float> purchaseAmounts){
		float maxAmount=purchaseAmounts.get(0);
		for(int i=1;i<postcodes.size();i++){
			if(maxAmount<purchaseAmounts.get(i)){
				maxAmount = purchaseAmounts.get(i);
			}			
		}		
		System.out.printf("%-20s %-20s\n","POSTCODES","PURCHASE AMOUNTS");
		for(int i=0;i<postcodes.size();i++){
			if(purchaseAmounts.get(i) == maxAmount){				
				System.out.printf("%-20d %-20.2f\n",postcodes.get(i),purchaseAmounts.get(i) );
			}
		}
	}
	
	/* Method to find and display the postcode/s with the lowest purchase amount */
	public void displayCheapestOrders(ArrayList<Integer> postcodes,ArrayList<Float> purchaseAmounts){
		float minAmount=purchaseAmounts.get(0);
		for(int i=1;i<postcodes.size();i++){
			if(minAmount>purchaseAmounts.get(i)){
				minAmount = purchaseAmounts.get(i);
			}			
		}
		System.out.printf("%-20s %-20s\n","POSTCODES","PURCHASE AMOUNTS");
		for(int i=0;i<postcodes.size();i++){
			if(purchaseAmounts.get(i) == minAmount){
				System.out.printf("%-20d %-20.2f\n",postcodes.get(i),purchaseAmounts.get(i) );
			}
		}
	}
	
	/* Method to sort and display the total payable in descending order */
	public void sortPayables(ArrayList<Integer> postcodes,ArrayList<Float> purchaseAmounts,ArrayList<Float> totalPayables){
		int tempPostcode;
		float tempAmount;
		float tempPayable;
		for(int i=0;i<postcodes.size();i++){
			for(int j=0;j<postcodes.size()-i-1;j++){
				if(purchaseAmounts.get(j)<purchaseAmounts.get(j+1)){
					tempAmount=purchaseAmounts.get(j);
					tempPostcode = postcodes.get(j);
					tempPayable = totalPayables.get(j);
					
					purchaseAmounts.set(j, purchaseAmounts.get(j+1));
					postcodes.set(j, postcodes.get(j+1));
					totalPayables.set(j, totalPayables.get(j+1));
					
					purchaseAmounts.set(j+1, tempAmount);
					postcodes.set(j+1, tempPostcode);
					totalPayables.set(j+1, tempPayable);
				}
			}
		}
		System.out.printf("%-20s %-20s %-20s\n","POSTCODES","PURCHASE AMOUNTS","TOTAL PAYABLES");
		for(int i=0;i<postcodes.size();i++){
			System.out.printf("%-20s %-20s %-20s\n",postcodes.get(i),purchaseAmounts.get(i),totalPayables.get(i));
		}
	} 
	/* Method to search and display all purchase amounts and total payables with a given postcode */
	public void displayPurchases(int postcode,ArrayList<Integer> postcodes,ArrayList<Float> purchaseAmounts,ArrayList<Float> totalPayables){
		System.out.printf("%-20s %-20s %-20s\n","POSTCODES","PURCHASE AMOUNTS","TOTAL PAYABLES");
		for(int i=0;i<postcodes.size();i++){
			if(postcodes.get(i)==postcode){
				System.out.printf("%-20s %-20s %-20s\n",postcodes.get(i),purchaseAmounts.get(i),totalPayables.get(i));
			}
		}
			
	}
}
