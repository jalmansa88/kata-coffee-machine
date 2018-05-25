package es.wata.almansaj.cofeemachine;

import java.util.List;

import es.wata.almansaj.cofeemachine.drinks.Chocolate;
import es.wata.almansaj.cofeemachine.drinks.Coffee;
import es.wata.almansaj.cofeemachine.drinks.Drink;
import es.wata.almansaj.cofeemachine.drinks.OrangeJuice;
import es.wata.almansaj.cofeemachine.drinks.Tea;

public class ReportService {

	private final List<Drink> drinksDataSource;
	
	public ReportService(List<Drink> soldDrinks) {
		this.drinksDataSource = soldDrinks;
	}

	public String generate() {
		String output = "####  DRINK MACHINE REPORT  ####\n";
		output += "Tea sold: " + countDrinksByType(drinksDataSource, new Tea()) + "\n";
		output += "Coffee sold: " + countDrinksByType(drinksDataSource, new Coffee()) + "\n";
		output += "Chocolate sold: " + countDrinksByType(drinksDataSource, new Chocolate()) + "\n";
		output += "OrangeJuice sold: " + countDrinksByType(drinksDataSource, new OrangeJuice()) + "\n";
		
		output += "TOTAL INCOME: " + calculateTotalIncome();
		
		return output;
	}
	
	private double calculateTotalIncome() {
		return drinksDataSource.stream()
				.mapToDouble(Drink::getPrice)
				.reduce((a, b) -> a+b).getAsDouble();
	}
	
	private int countDrinksByType(List<Drink> drinkList, Drink drink) {
		return drinkList.stream()
				.filter(aDrink -> namesAreEquals(aDrink, drink))
				.mapToInt(e -> 1).sum();
	}
	
	private boolean namesAreEquals(Drink drink, Drink anotherDrink) {
		return drink.getName().equalsIgnoreCase(anotherDrink.getName());
	}
	
}
