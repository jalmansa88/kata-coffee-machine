package es.wata.almansaj.cofeemachine.drinks;

public class Coffee extends Drink{
	
	private static final String COFFEE_LETTER = "C";
	private static final String COFFEE = "coffee";
	private static final double COFFEE_PRICE = 0.6D;
	
	public Coffee() {
		super(COFFEE, COFFEE_LETTER, COFFEE_PRICE);
	}
}
