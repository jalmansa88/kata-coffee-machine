package es.wata.almansaj.cofeemachine.drinks;

public class Tea extends Drink{
	
	private static final String TEA_LETTER = "T";
	private static final double TEA_PRICE = 0.4D;
	private static final String TEA = "Tea";

	public Tea() {
		super(TEA, TEA_LETTER, TEA_PRICE);
	}

}
