package template;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import template.Calculator;

public class CalcSumTest {
	Calculator calculator;
	String numFilePath;
	
	@Before
	public void setUp() {
		this.calculator = new Calculator();
		this.numFilePath = getClass().getResource("numbers.txt").getPath();
	}
	@Test
	public void sumOfNumbers() throws IOException{
		assertThat(calculator.calcSum(this.numFilePath), is(10));
	}
	@Test
	public void multiplyOfNumber() throws IOException{
		assertThat(calculator.calcMultiply(this.numFilePath), is(24));
	}
}
