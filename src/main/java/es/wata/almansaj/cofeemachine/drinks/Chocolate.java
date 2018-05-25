package es.wata.almansaj.cofeemachine.drinks;

public class Chocolate extends Drink{
	
	private static final String CHOCO_LETTER = "H";
	private static final double CHOCOLATE_PRICE = 0.5D;
	private static final String CHOCOLATE = "Chocolate";

	public Chocolate() {
		super(CHOCOLATE, CHOCO_LETTER, CHOCOLATE_PRICE);
	}

}
