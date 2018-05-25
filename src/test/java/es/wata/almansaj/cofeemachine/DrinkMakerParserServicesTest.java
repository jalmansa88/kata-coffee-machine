package es.wata.almansaj.cofeemachine;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.wata.almansaj.cofeemachine.drinks.Chocolate;
import es.wata.almansaj.cofeemachine.drinks.Coffee;
import es.wata.almansaj.cofeemachine.drinks.OrangeJuice;
import es.wata.almansaj.cofeemachine.drinks.Tea;

@RunWith(MockitoJUnitRunner.class)
public class DrinkMakerParserServicesTest {
	
	@Mock
	DrinkMachine machine;
	@Mock
	EmailNotifierService notifier;
	
	@Test
	public void report_oneDrinkOfEach_doubleCoffee_270(){
		DrinkMakerParser parser = new DrinkMakerParser(machine, notifier);
		
		CustomerOrder order = new OrderBuilder()
				.withDrink(new Coffee())
				.withSugar(0)
				.withMoney(0.6D)
				.withExtraHot()
				.build();
		parser.encodeOrder(order);
		
		order = new OrderBuilder()
				.withDrink(new Coffee())
				.withSugar(0)
				.withMoney(0.6D)
				.withExtraHot()
				.build();
		parser.encodeOrder(order);
		
		order = new OrderBuilder()
				.withDrink(new Chocolate())
				.withSugar(0)
				.withMoney(0.5D)
				.build();
		parser.encodeOrder(order);
		
		order = new OrderBuilder()
				.withDrink(new Tea())
				.withSugar(0)
				.withMoney(0.4D)
				.build();
		parser.encodeOrder(order);
		
		order = new OrderBuilder()
				.withDrink(new OrangeJuice())
				.withSugar(0)
				.withMoney(0.6D)
				.build();
		parser.encodeOrder(order);
		
		String outputExpected = 
				"####  DRINK MACHINE REPORT  ####\n" + 
				"Tea sold: 1\n" + 
				"Coffee sold: 2\n" + 
				"Chocolate sold: 1\n" + 
				"OrangeJuice sold: 1\n" + 
				"TOTAL INCOME: 2.7";
		
		assertEquals(outputExpected, parser.printReport());
	}
	
	@Test
	public void checkDrinkIsShortages_emailSent(){
		CustomerOrder order = new OrderBuilder()
				.withDrink(new Coffee())
				.withSugar(0)
				.withMoney(0.6D)
				.build();
		
		when(machine.isEmpty("coffee")).thenReturn(true);
		DrinkMakerParser parser = new DrinkMakerParser(machine, notifier);
		
		parser.encodeOrder(order);
		verify(notifier, times(1)).notifyMissingDrink("coffee");
	}
	
	@Test
	public void checkDrinkIsNotShortages_emailNotSent(){
		CustomerOrder order = new OrderBuilder()
				.withDrink(new Coffee())
				.withSugar(0)
				.withMoney(0.6D)
				.build();
		
		when(machine.isEmpty("coffee")).thenReturn(false);
		DrinkMakerParser parser = new DrinkMakerParser(machine, notifier);
		
		verify(notifier, never()).notifyMissingDrink("coffee");
		parser.encodeOrder(order);
	}
}
