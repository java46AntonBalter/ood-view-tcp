package telran.net.test;

import java.io.Closeable;
import java.io.IOException;

import telran.net.*;

public class NetCalculatorProxy implements Calculator, Closeable {
	private NetworkHandler networkHandler;

	public NetCalculatorProxy(NetworkHandler networkHandler) {
		this.networkHandler = networkHandler;
	}

	@Override
	public double add(double op1, double op2) {
		return networkHandler.send("add", new Double[] {op1, op2});
	}

	@Override
	public double subtract(double op1, double op2) {
		return networkHandler.send("subtract", new Double[] {op1, op2});
	}

	@Override
	public double divide(double op1, double op2) {
		return networkHandler.send("divide", new Double[] {op1, op2});
	}

	@Override
	public double multiply(double op1, double op2) {
		return networkHandler.send("multiply", new Double[] {op1, op2});
	}

	@Override
	public void close() throws IOException {
		networkHandler.close();		
	}
	
	
	
	

}
