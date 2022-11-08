package telran.net.test;

import java.io.IOException;
import java.util.ArrayList;

import telran.net.TcpHandler;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorAppl {

	public static void main(String[] args) {
		Calculator calculator = new CalculatorImpl();	
		InputOutput io = new ConsoleInputOutput();
		try {
			TcpHandler tcpHandler = new TcpHandler("localhost", 3000);
			NetCalculatorProxy proxy = new NetCalculatorProxy(tcpHandler);
			Menu menu = new Menu("Calculator", getItems(calculator, proxy));
			menu.perform(io);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		

	}

	private static ArrayList<Item> getItems(Calculator calculator, NetCalculatorProxy proxy) {
		Item[] Items = CalculatorMenu.getCalculatorItems(calculator);
		
		Item exit = Item.of("Exit", io -> {
			try {
				proxy.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}, true);
		
		ArrayList<Item> res = new ArrayList<>();
		for(Item i: Items) {
			res.add(i);
		}
		res.add(exit);
		return res;
	}

}
