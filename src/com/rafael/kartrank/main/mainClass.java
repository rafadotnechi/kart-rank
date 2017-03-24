package com.rafael.kartrank.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.rafael.kartrank.model.Piloto;

public class mainClass {

	public static HashMap<Integer, Piloto> hmPilotos = new HashMap<Integer, Piloto>();
	public static List<Piloto> lPilotos = new ArrayList<Piloto>();
	public static Double melhorVolta = 0.0;
	
	public static void main(String arg[]) throws IOException, ParseException {
		
		geraHashMapPiloto();
		geraListaPilotos();
		calcVelocidadeMedia();
		melhorVolta = calcMelhorVolta();
		definePosicoes();
		calcDiferencaPrimeiro();
		for(Piloto p : lPilotos)
		{
		System.out.println(p);		
		}
		System.out.println("Melhor volta da corrida: " + melhorVolta + "s");
	}

	public static void geraHashMapPiloto() throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader("corrida.txt"));
		br.readLine();
		String line = br.readLine();
		while (line != null) {
			String[] dados = line.split("\\s+");
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.SSS");
		    Date umomento = dateFormat.parse(dados[0]);
			int id = Integer.parseInt(dados[1]);
			String nome = dados[3];
			int nvolta = Integer.parseInt(dados[4]);
			double tvolta = tempoEmSegundos(dados[5]);
			double vmedia = Double.parseDouble(dados[6].replace(",", "."));
			
			Piloto p = new Piloto();

			if (hmPilotos.containsKey(id)) {
				hmPilotos.get(id).gettVoltas().add(tvolta);
				hmPilotos.get(id).getVelocidades().add(vmedia);
				hmPilotos.get(id).setvCompletas(nvolta);
				hmPilotos.get(id).setUltimoMomento(umomento);
			} else {
				p.setId(id);
				p.setNome(nome);
				p.settVoltas(new ArrayList<Double>());
				p.setVelocidades(new ArrayList<Double>());
				p.gettVoltas().add(tvolta);
				p.getVelocidades().add(vmedia);
				p.setUltimoMomento(umomento);
				hmPilotos.put(id, p);
			}
			line = br.readLine();
		}
		br.close();
	}
	
	public static void geraListaPilotos()
	{
		for(Integer key : hmPilotos.keySet())
		{
			lPilotos.add(hmPilotos.get(key));
		}
	}
	
	
	public static void calcVelocidadeMedia()
	{
		for(Piloto p : lPilotos)
		{
			p.calcVmedia();
		}
	}
	
	public static Double calcMelhorVolta()
	{
		List<Double> mVoltas = new ArrayList<Double>();
		for(Piloto p : lPilotos)
		{
			p.calcMelhorVolta();
			mVoltas.add(p.getMelhorVolta());
		}
		return Collections.min(mVoltas,null);
	}
	
	public static void calcDiferencaPrimeiro()
	{
		Date tempoPrimeiro = lPilotos.get(0).getUltimoMomento();
		for(Piloto p : lPilotos) {
			p.calcTempoDoPrimeiro(tempoPrimeiro);
		}
	}
	
	public static void definePosicoes()
	{
		
		Collections.sort(lPilotos, new Comparator<Piloto>() {
		    @Override
		    public int compare(Piloto p1, Piloto p2) {
		        return p1.getUltimoMomento().compareTo(p2.getUltimoMomento());
		    }
		});
		
		for(int i = 1 ; i <= lPilotos.size() ; i++)
		{
			lPilotos.get(i -1).setPosição(i);	
		}
	}
	
	public static Double tempoEmSegundos(String s) {
		String[] numeros = s.split("[:|.]");
		Double t = 60 * Double.parseDouble(numeros[0]) + Double.parseDouble(numeros[1])
				+ (Double.parseDouble(numeros[2]) / 1000);
		return t;
	}

}
