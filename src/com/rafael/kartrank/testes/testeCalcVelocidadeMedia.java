package com.rafael.kartrank.testes;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import com.rafael.kartrank.main.mainClass;
import com.rafael.kartrank.model.Piloto;

public class testeCalcVelocidadeMedia {

	@Test
	public void test() throws IOException, ParseException {
		mainClass.geraHashMapPiloto();
		mainClass.geraListaPilotos();
		mainClass.calcVelocidadeMedia();
		for(Piloto p : mainClass.lPilotos)
		{
		System.out.println(p);		
		}
	}

}
