package es.wata.almansaj.cofeemachine.drinks;

public abstract class Drink {
	
	String name;
	double price;
	String letterCode;
	
	public Drink(String name, String letterCode, double price) {
		this.name = name;
		this.letterCode = letterCode;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getLetterCode() {
		return letterCode;
	}

}
