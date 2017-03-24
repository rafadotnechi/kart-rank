package com.rafael.kartrank.testes;
import static org.junit.Assert.*;

import org.junit.Test;

import com.rafael.kartrank.main.mainClass;

public class testeTempoEmSegundos {

	@Test
	public void test() {

         assertEquals("1:00.000 tem que retornar 60 segundos", 60.0, mainClass.tempoEmSegundos("1:00.000"),0.01);
         assertEquals("2:00.000 tem que retornar 120 segundos", 120.0, mainClass.tempoEmSegundos("2:00.000"),0.01);
    
	}

}
