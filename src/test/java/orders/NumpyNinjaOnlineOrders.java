package orders;

import java.util.Scanner;

public class NumpyNinjaOnlineOrders {

	int service;
	int cntChickenBriyani, cntFishCurry, cntChicken65, cntMuttonGravy, cntChickFriedRice;
	int cntPaneerTikka, cntCashewPulao, cntVegRice, cntGobi65, cntVegThali,numberofItems;
	String wantMore;
	int serviceCharges = 30, deliveryCharges = 10;
	double totalCost, gst, extraCharge, totalbill, discount, netBill;

	public int ReadUserInputNumber() {
		Scanner scanner = new Scanner(System.in);

		int input = scanner.nextInt(); // Read user input
		return input;
	}

	public String ReadUserInputString() {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.next(); // Read user input

		return input;
	}

	public int SelectService() {
		System.out.println("Hello Welcome to Numpy Ninja restaurant !");
		System.out.println("Please select the service offering from below list :");
		System.out.println("\t [1]. dine-in");
		System.out.println("\t [2]. take away");
		System.out.println("\nPlease enter the option");
		return ReadUserInputNumber();
	}

	public int SelectCategory() {
		System.out.println("\nPlease select the category from the below list :");
		System.out.println("\t [1]. Vegetarian");
		System.out.println("\t [2]. Non-vegetarian");
		System.out.println("\nPlease enter the option");
		return ReadUserInputNumber();
	}

	public void DisplayVegMenu() {
		System.out.println("\nBelow are the dishes available currently to order :");
		System.out.println("\t 1. Paneer tikka - Rs. 120.00 (per item)");
		System.out.println("\t 2. Cashew pulao - Rs. 150.00 (per item)");
		System.out.println("\t 3. Veg fried rice - Rs. 130.00 (per item)");
		System.out.println("\t 4. Gobi 65 - Rs. 100.00 (per item)");
		System.out.println("\t 5. Veg. thali - Rs. 140.00 (per item)");
		TakeVegOrders();
	}

	public void DisplayNonVegMenu() {
		System.out.println("\nBelow are the dishes available currently to order :");
		System.out.println("\t 1. Chicken briyani - Rs. 200.00 (per item)");
		System.out.println("\t 2. Fish curry -  Rs. 150.00 (per item)");
		System.out.println("\t 3. Chicken 65 - Rs. 120.00 (per item)");
		System.out.println("\t 4. Mutton gravy -  Rs. 220.00 (per item)");
		System.out.println("\t 5. Chicken fried rice - Rs. 180.00 (per item)");
		TakeNonVegOrders();
	}

	public void DisplayMenuTakeOrders() {

		service = SelectService();

		int category = SelectCategory();

		switch (category) {

		case 1:
			DisplayVegMenu();
			break;

		case 2:
			DisplayNonVegMenu();
			break;

		default:
			System.out.println("You have chosen invalid input");
			break;
		}

	}

	public void TakeVegOrders() {
		System.out.println("\nPlease enter the option");
		int userChoiceofItem = ReadUserInputNumber();

		switch (userChoiceofItem) {

		case 1:
			cntPaneerTikka = cntPaneerTikka + NumberOfOrders("Paneer tikka");
			numberofItems++;
			break;
		case 2:
			cntCashewPulao = cntCashewPulao + NumberOfOrders("Cashew Pulao");
			numberofItems++;
			break;
		case 3:
			cntVegRice = cntVegRice + NumberOfOrders(" Veg fried rice");
			numberofItems++;
			break;
		case 4:
			cntGobi65 = cntGobi65 + NumberOfOrders("Gobi 65");
			numberofItems++;
			break;
		case 5:
			cntVegThali = cntVegThali + NumberOfOrders("Veg. thali");
			numberofItems++;
			break;
		default:
			System.out.println("You have chosen invalid input");
			break;
		}

		OrderMore(true); // true for more veg orders
	}

	public void TakeNonVegOrders() {
		System.out.println("\nPlease enter the option");
		int userChoiceofItem = ReadUserInputNumber();

		switch (userChoiceofItem) {

		case 1:
			cntChickenBriyani = cntChickenBriyani + NumberOfOrders("Chicken briyani");
			numberofItems++;
			break;

		case 2:
			cntFishCurry = cntPaneerTikka + NumberOfOrders("Fish curry");
			numberofItems++;
			break;

		case 3:
			cntChicken65 = cntFishCurry + NumberOfOrders("Chicken 65");
			numberofItems++;
			break;

		case 4:
			cntMuttonGravy = cntMuttonGravy + NumberOfOrders("Mutton gravy");
			numberofItems++;
			break;

		case 5:
			cntChickFriedRice = cntChickFriedRice + NumberOfOrders("Chicken fried rice");
			numberofItems++;
			break;

		default:
			System.out.println("You have chosen invalid input");
			break;
		}

		OrderMore(false);
	}

	public void OrderMore(Boolean isVeg) {
		System.out.println("\nWould you like to order one more dish ? [Y] or [N]");
		wantMore = ReadUserInputString();

		switch (wantMore.toUpperCase()) {

		case "Y":
			if (isVeg)
				DisplayVegMenu();
			else
				DisplayNonVegMenu();
			break;

		case "N":
			System.out.println("\nWould you like to order from other category ? [Y] or [N]");
			wantMore = ReadUserInputString();

			if (wantMore.toUpperCase().equals("Y")) {
				if (isVeg)
					DisplayNonVegMenu();
				else
					DisplayVegMenu();

			} else if (wantMore.toUpperCase().equals("N"))
				CalculateFinalBill();
			else
				System.out.println("You have chosen invalid input");

			break;

		default:
			System.out.println("You have chosen invalid input");
			break;
		}
	}

	public int NumberOfOrders(String menuItem) {
		System.out.println("\nPlease enter number of " + menuItem + " you would like to order");
		return ReadUserInputNumber();
	}

	private void CalculateFinalBill() {

		/*
		 * User will be charged with 6 % of GST on the bill 10 rupees per dish will be
		 * charged for parcel else 30 rupees will be charged per bill as service charge
		 * for dine-in User will get 10 % discount if the total bill amount is more than
		 * 1000 And user will get 15 % discount if the total bill amount is more than
		 * 1500
		 */

		totalCost = CalculateTotalCost();
		gst = CalculateGST();
		extraCharge = service == 1 ? serviceCharges : CalculateDeliveryCharge();
		totalbill = totalCost + gst + extraCharge;

		discount = CalculateDiscount();
		netBill = totalbill - discount;

		System.out.println("Total cost : " + totalCost);
		System.out.println("GST tax :" + String.format("%.2f",gst));
		System.out.println("Parcel/Dine-in charge : " + extraCharge);
		System.out.println("Total bill amount : " + totalbill);
		System.out.println("Discount : " + String.format("%.2f",discount));
		System.out.println("Net bill amount : " + String.format("%.2f",netBill));

		System.out.println("\nThank you ! Visit again !");

	}

	private double CalculateDeliveryCharge() {
		double totalDeliveryCharge;

		// delivery charge 10 per dish
		totalDeliveryCharge = numberofItems * deliveryCharges;

		return totalDeliveryCharge;
	}

	private double CalculateTotalCost() {
		double totalCost;
		totalCost = (cntChickenBriyani * 200) + (cntFishCurry * 150) + (cntChicken65 * 120) + (cntMuttonGravy * 220)
				+ (cntChickFriedRice * 180) + (cntPaneerTikka * 120) + (cntCashewPulao * 150) + (cntVegRice * 130)
				+ (cntGobi65 * 100) + (cntVegThali * 140);
		return totalCost;
	}

	private double CalculateGST() {
		return (0.06 * totalCost);
	}

	private double CalculateDiscount() {
		double discnt = 0;
		if (totalbill > 1000)
			discnt = (0.10 * totalbill); // 10% discount
		else if (totalbill > 1500)
			discnt = (0.15 * totalbill); // 15% discount
		return discnt;
	}

	public static void main(String[] args) {

		NumpyNinjaOnlineOrders order = new NumpyNinjaOnlineOrders();
		order.DisplayMenuTakeOrders();

	}

}