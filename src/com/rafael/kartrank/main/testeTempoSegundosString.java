package com.rafael.kartrank.main;
import static org.junit.Assert.*;

import org.junit.Test;

public class testeTempoSegundosString {

	@Test
	public void test() {
		mainClass tester = new mainClass(); 

         assertEquals("1:00.000 tem que retornar 60 segundos", 60.0, tester.TempoSegundosString("1:00.000"),0.01);
         assertEquals("2:00.000 tem que retornar 120 segundos", 120.0, tester.TempoSegundosString("2:00.000"),0.01);
    
	}

}
