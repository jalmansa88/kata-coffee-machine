package es.wata.almansaj.cofeemachine;

import es.wata.almansaj.cofeemachine.drinks.Drink;
import es.wata.almansaj.cofeemachine.drinks.OrangeJuice;

public class OrderBuilder {
	
	CustomerOrder order;
	
	public OrderBuilder() {
		order = new CustomerOrder();
	}
	
	public OrderBuilder withDrink(Drink drink) {
		order.setDrink(drink);
		return this;
	}
	
	public OrderBuilder withSugar(int sugar) {
		order.setAmountOfSugar(sugar);
		return this;
	}
	
	public OrderBuilder withMoney(double amount) {
		order.setPayment(amount);
		return this;
	}
	
	public OrderBuilder withExtraHot() {
		if(!(order.getDrink() instanceof OrangeJuice)) {
			order.setExtraHot(true);
		}
		return this;
	}
	
	public CustomerOrder build() {
		return order;
	}
	
}
