package es.wata.almansaj.cofeemachine;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.wata.almansaj.cofeemachine.drinks.Chocolate;
import es.wata.almansaj.cofeemachine.drinks.Coffee;
import es.wata.almansaj.cofeemachine.drinks.FooDrink;
import es.wata.almansaj.cofeemachine.drinks.OrangeJuice;
import es.wata.almansaj.cofeemachine.drinks.Tea;

@RunWith(Parameterized.class)
public class DrinkMakerParserTest {
	
	private CustomerOrder order;
	private String codeExpected;
	
	public DrinkMakerParserTest(CustomerOrder order, String codeExpected) {
		this.order = order;
		this.codeExpected = codeExpected;
	}
	
	@Parameters(name = "User Order: {0}. Code expected: {1}")
	public static List<Object> data(){
		return Arrays.asList(
			new Object[] {new OrderBuilder()
					.withDrink(new Tea())
					.withSugar(1)
					.withMoney(0.4D)
					.build(), "T:1:0"},
			new Object[] {new OrderBuilder()
					.withDrink(new Chocolate())
					.withSugar(0)
					.withMoney(0.5D)
					.build(), "H::"},
			new Object[] {new OrderBuilder()
					.withDrink(new Coffee())
					.withSugar(2)
					.withMoney(0.6D)
					.build(), "C:2:0"},
			new Object[] {new OrderBuilder()
					.withDrink(new FooDrink())
					.withSugar(1)
					.withMoney(0.4D)
					.build(), "M:invalid-drink"},
			new Object[] {new OrderBuilder()
					.withDrink(new Coffee())
					.withSugar(1)
					.withMoney(0.3D)
					.build(), "M:0.3 missing"},
			new Object[] {new OrderBuilder()
					.withDrink(new Tea())
					.withMoney(0)
					.build(), "M:0.4 missing"},
			new Object[] {new OrderBuilder()
					.withDrink(new OrangeJuice())
					.withMoney(0.6D)
					.withExtraHot()
					.build(), "O::"},
			new Object[] {new OrderBuilder()
					.withDrink(new Coffee())
					.withSugar(0)
					.withMoney(0.6D)
					.withExtraHot()
					.build(), "Ch::"},
			new Object[] {new OrderBuilder()
					.withDrink(new OrangeJuice())
					.withSugar(1)
					.withMoney(0.6D)
					.withExtraHot()
					.build(), "O:1:0"},
			new Object[] {new OrderBuilder()
					.withDrink(new Chocolate())
					.withSugar(1)
					.withMoney(0.5D)
					.withExtraHot()
					.build(), "Hh:1:0"},
			new Object[] {new OrderBuilder()
					.withDrink(new Tea())
					.withSugar(2)
					.withMoney(0.4D)
					.withExtraHot()
					.build(), "Th:2:0"}
			);
	}
	
	@Mock
	DrinkMachine machine;
	@Mock
	EmailNotifierService notifier;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void processingTestValues() {
		DrinkMakerParser parser = new DrinkMakerParser(machine, notifier);
		assertEquals(codeExpected, parser.encodeOrder(order));
	}
	
//	@Test
//	public void report_oneDrinkOfEach_doubleCoffee_270(){
//		DrinkMakerParser parser = new DrinkMakerParser(machine, notifier);
//		
//		CustomerOrder order = new OrderBuilder()
//				.withDrink(new Coffee())
//				.withSugar(0)
//				.withMoney(0.6D)
//				.withExtraHot(true)
//				.build();
//		parser.encodeOrder(order);
//		
//		order = new OrderBuilder()
//				.withDrink(new Coffee())
//				.withSugar(0)
//				.withMoney(0.6D)
//				.withExtraHot(true)
//				.build();
//		parser.encodeOrder(order);
//		
//		order = new OrderBuilder()
//				.withDrink(new Chocolate())
//				.withSugar(0)
//				.withMoney(0.5D)
//				.build();
//		parser.encodeOrder(order);
//		
//		order = new OrderBuilder()
//				.withDrink(new Tea())
//				.withSugar(0)
//				.withMoney(0.4D)
//				.build();
//		parser.encodeOrder(order);
//		
//		order = new OrderBuilder()
//				.withDrink(new OrangeJuice())
//				.withSugar(0)
//				.withMoney(0.6D)
//				.build();
//		parser.encodeOrder(order);
//		
//		String outputExpected = 
//				"####  DRINK MACHINE REPORT  ####\n" + 
//				"Tea sold: 1\n" + 
//				"Coffee sold: 2\n" + 
//				"Chocolate sold: 1\n" + 
//				"OrangeJuice sold: 1\n" + 
//				"TOTAL INCOME: 2.7";
//		
//		assertEquals(outputExpected, parser.printReport());
//	}
//	
//	@Test
//	public void checkDrinkIsShortages_emailSent(){
//		CustomerOrder order = new OrderBuilder()
//				.withDrink(new Coffee())
//				.withSugar(0)
//				.withMoney(0.6D)
//				.build();
//		
//		when(machine.isEmpty("coffee")).thenReturn(true);
//		DrinkMakerParser parser = new DrinkMakerParser(machine, notifier);
//		
//		parser.encodeOrder(order);
//		verify(notifier, times(1)).notifyMissingDrink("coffee");
//	}
//	
//	@Test
//	public void checkDrinkIsNotShortages_emailNotSent(){
//		CustomerOrder order = new OrderBuilder()
//				.withDrink(new Coffee())
//				.withSugar(0)
//				.withMoney(0.6D)
//				.build();
//		
//		when(machine.isEmpty("coffee")).thenReturn(false);
//		DrinkMakerParser parser = new DrinkMakerParser(machine, notifier);
//		
//		verify(notifier, never()).notifyMissingDrink("coffee");
//		parser.encodeOrder(order);
//	}
}
