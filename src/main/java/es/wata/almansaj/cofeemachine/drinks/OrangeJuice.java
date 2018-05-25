package es.wata.almansaj.cofeemachine.drinks;

public class OrangeJuice extends Drink{

	private static final String ORANGEJUICE_LETTER = "O";
	private static final double ORANGEJUICE_PRICE = 0.6D;
	private static final String ORANGE_JUICE = "OrangeJuice";

	public OrangeJuice() {
		super(ORANGE_JUICE, ORANGEJUICE_LETTER, ORANGEJUICE_PRICE);
	}
}
