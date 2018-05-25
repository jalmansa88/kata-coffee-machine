package es.wata.almansaj.cofeemachine;

import es.wata.almansaj.cofeemachine.drinks.Drink;

public class CustomerOrder {
	
	private Drink drink;
	private int amountOfSugar;
	private double payment;
	private boolean extraHot = false;
	
	public CustomerOrder() {
		super();
	}
	
	public int getAmountOfSugar() {
		return amountOfSugar;
	}

	public Drink getDrink() {
		return drink;
	}
	
	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public void setAmountOfSugar(int amountOfSugar) {
		this.amountOfSugar = amountOfSugar;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public double getPayment() {
		return payment;
	}

	public boolean isExtraHot() {
		return extraHot;
	}

	public void setExtraHot(boolean extraHot) {
		this.extraHot = extraHot;
	}

	@Override
	public String toString() {
		return "Drink=" + drink.getName() + ", amountOfSugar=" + amountOfSugar + ", payment=" + payment
				+ ", extraHot=" + extraHot;
	}
	
	
}
