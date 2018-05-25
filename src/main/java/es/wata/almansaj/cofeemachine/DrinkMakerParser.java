package es.wata.almansaj.cofeemachine;

import java.util.ArrayList;
import java.util.List;

import es.wata.almansaj.cofeemachine.drinks.Drink;
import es.wata.almansaj.cofeemachine.drinks.FooDrink;

public class DrinkMakerParser {
	
	private static final int STICK = 0;
	private static final String HOT = "h";
	private static final String MISSING = " missing";
	private static final String SEPARATOR = ":";
	private static final String INVALID_DRINK = "invalid-drink";
	private static final String MESSAGE_LETTER = "M";

	private final List<Drink> soldDrinks;
	private final EmailNotifierService notifierService;
	private final DrinkMachine drinkMachine;
	
	public DrinkMakerParser(DrinkMachine drinkMachine, EmailNotifierService notifierService) {
		soldDrinks = new ArrayList<>();
		this.drinkMachine = drinkMachine;
		this.notifierService = notifierService;
	}
	
	public String encodeOrder(CustomerOrder order) {
		Drink drink = order.getDrink();
		
		// Testing porpouse
		if(drink instanceof FooDrink) {
			return sendMessageResponse(INVALID_DRINK);
		}
		
		double difference = drink.getPrice() - order.getPayment();
		
		if(difference > 0) {
			return sendMessageResponse(difference + MISSING);
		}
		
		soldDrinks.add(drink);
		checkIfDrinkIsEmpty(drink);
		
		return constructDrinkMakerProtocol(drink, order);
	}

	private String sendMessageResponse(String message) {
		return MESSAGE_LETTER + SEPARATOR + message;
	}

	public String printReport() {
		ReportService reportService = new ReportService(soldDrinks);
		String report = reportService.generate();
		System.out.println(report);
		
		return report;
	}
	
	private String constructDrinkMakerProtocol(Drink drink, CustomerOrder order) {
		return addExtraHotWhenNecessary(drink, order) + addAmountOfSugar(order.getAmountOfSugar());
	}

	private String addAmountOfSugar(int amountOfSugar) {
		return amountOfSugar == 0 ? SEPARATOR : amountOfSugar + SEPARATOR + STICK;
	}

	private String addExtraHotWhenNecessary(Drink drink, CustomerOrder order) {
		return order.isExtraHot()  ? drink.getLetterCode() + HOT + SEPARATOR : drink.getLetterCode() + SEPARATOR;
	}
	
	private void checkIfDrinkIsEmpty(Drink drink) {
		if(drinkMachine.isEmpty(drink.getName())) {
			notifierService.notifyMissingDrink(drink.getName());
		}
	}
}
