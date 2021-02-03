package cafematt;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;


public class CafeMatt {
    private static Scanner input;
    private static final Map<String, MenuItem> theMenu;
    
    static {
		theMenu = new HashMap<>();
		
		/**
		 *  Cola - Cold – 50 cents
			Coffee - Hot - $1.00
			Cheese Sandwich - Cold - $2.00
			Steak Sandwich - Hot - $4.50
		 */
		
		// Prepare the menu
		MenuItem item = new MenuItem ("Cola - Cold", .50, false);
		theMenu.put("Cola - Cold", item);
		
		item = new MenuItem ("Coffee - Hot", 1.00, false);
		theMenu.put("Coffee - Hot", item);
		
		item = new MenuItem ("Cheese Sandwich - Cold", 2.00, true);
		theMenu.put("Cheese Sandwich - Cold", item);
		
		item = new MenuItem ("Steak Sandwich - Hot", 4.50, true);
		theMenu.put("Steak Sandwich - Hot", item);
		
    }

	public static void main (String [] args) {
		
	      input = new Scanner(System.in);
	        System.out.println("What's your name?");
	        String customer = input.nextLine();
	        
	        System.out.println("Welcome to Matt's Cafe, " + customer + "! \n\n");
	        System.out.println("The Following is out Menu");
	        System.out.println("Cola - Cold – 50 cents");
	        System.out.println("Coffee - Hot - $1.00");
	        System.out.println("Cheese Sandwich - Cold - $2.00");
	        System.out.println("Steak Sandwich - Hot - $4.50");
	        
	        System.out.println("\n\n");
	        System.out.println("A Service charge of 10 % will be added for non liquid items!!!");
	        System.out.println("\n\n");
	        
	        
	        List<Pair<String, Integer>> purchaseItems = new ArrayList <> ();
	        
	        
	        System.out.println("Cola - Cold , how many do you want?");
	        Integer numItems = new Integer (input.nextLine());
	        
	        if (numItems >= 1)
	        	purchaseItems.add(new MutablePair("Cola - Cold", numItems));
	        
	        System.out.println("Coffee - Hot , how many do you want?");
	        numItems = new Integer (input.nextLine());
	        if (numItems >= 1)
	        	purchaseItems.add(new MutablePair("Coffee - Hot", numItems));

	        System.out.println("Cheese Sandwich - Cold , how many do you want?");
	        numItems = new Integer (input.nextLine());
	        if (numItems >= 1)
	        	purchaseItems.add(new MutablePair("Cheese Sandwich - Cold", numItems));
	        
	        
	        System.out.println("Steak Sandwich - Hot , how many do you want?");
	        numItems = new Integer (input.nextLine());
	        if (numItems >= 1)
	        	purchaseItems.add(new MutablePair("Steak Sandwich - Hot", numItems));
	        
	        
	        
	        
	        if (purchaseItems.size() == 0) {
	        	System.out.println ("\n\n  " + customer + " You did not purchase any items!!! :( ;(");
	        
	        } else {
	        	System.out.println ("\n\n  " + customer + " :) You purchased ......");
	        	printReceipt (purchaseItems);
	        }
		
	}
	
    //private static DecimalFormat df = new DecimalFormat("#.##");
    private static NumberFormat df = NumberFormat.getCurrencyInstance();

	
	public static void printReceipt (List<Pair<String, Integer>> purchaseItems) {
		Double totalPurchase =purchaseItems.stream().map(p -> {
//	        System.out.println ("Left = " + p.getLeft());
//	        System.out.println ("Value = " + p.getRight());
	        
			double price = theMenu.get(p.getLeft()).getPrice();
			Integer numItems = p.getRight();
			
			System.out.println ("    "+ p.getLeft() + ", numItems " + numItems + ", ItemPrice " + df.format(price) + ",   Amount " + df.format(price * numItems));
			return price * numItems;
		}).reduce(0.0, Double::sum);
		
		if (isServiceChargeRequired (purchaseItems)) {
			Double serviceCharge = totalPurchase*(10.0/100.0);

			System.out.println ("    Service Charge is " + df.format(serviceCharge) );
			totalPurchase += serviceCharge;
		} else {
			System.out.println ("    Service Charge is " + df.format(0.0) );			
		}
		
		System.out.println ("    The Total Purchase = " + df.format(totalPurchase));
	}
	
	public static boolean isServiceChargeRequired (List<Pair<String, Integer>> list) {
//	    return list != null && 
//	    		list.stream().map(i -> i.getLeft()).map(l->theMenu.get(l)).anyMatch(MenuItem::isServiceChargeRequired);
		boolean result = list.stream().map(i -> i.getLeft()).map( key -> {
			MenuItem item = theMenu.get(key);
			return item;
		}).anyMatch(MenuItem::isServiceChargeRequired);
		return result;
	}


	
//	public static boolean isServiceChargeRequired (List<MenuItem> list) {
//	    return list != null && list.stream().anyMatch(MenuItem::isServiceChargeRequired);
//	}
}
