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

	private HashMap<Integer, Piloto> hmPilotos = new HashMap<Integer, Piloto>();
	private List<Piloto> lPilotos = new ArrayList<Piloto>();
	
	public static void main(String arg[]) throws IOException, ParseException {
		mainClass m = new mainClass();
		m.geraHashMapCorrida();
		m.geraListaPilotos();
		m.calcVelocidadeMedia();
		m.definePosições();
		for(Piloto p : m.lPilotos)
		{
		System.out.println(p);		
		}
	}

	private void geraHashMapCorrida() throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader("corrida.txt"));
		br.readLine();
		String line = br.readLine();
		while (line != null) {
			String[] dados = line.split("\\s+");
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.SSS");
		    Date uhora = dateFormat.parse(dados[0]);
			int id = Integer.parseInt(dados[1]);
			String nome = dados[3];
			int nvolta = Integer.parseInt(dados[4]);
			double tvolta = TempoSegundosString(dados[5]);
			double vmedia = Double.parseDouble(dados[6].replace(",", "."));
			
			Piloto p = new Piloto();

			if (hmPilotos.containsKey(id)) {
				hmPilotos.get(id).gettVoltas().add(tvolta);
				hmPilotos.get(id).getVelocidades().add(vmedia);
				hmPilotos.get(id).setvCompletas(nvolta);
				hmPilotos.get(id).setUltimaHora(uhora);
			} else {
				p.setId(id);
				p.setNome(nome);
				p.settVoltas(new ArrayList<Double>());
				p.setVelocidades(new ArrayList<Double>());
				p.gettVoltas().add(tvolta);
				p.getVelocidades().add(vmedia);
				p.setUltimaHora(uhora);
				hmPilotos.put(id, p);
			}
			line = br.readLine();
		}
		br.close();
	}
	
	private void geraListaPilotos()
	{
		for(Integer key : hmPilotos.keySet())
		{
			lPilotos.add(hmPilotos.get(key));
		}
	}
	
	
	private void calcVelocidadeMedia()
	{
		for(Piloto p : lPilotos)
		{
			p.calcVmedia();
		}
	}
	
	
	private void definePosições()
	{
		
		Collections.sort(lPilotos, new Comparator<Piloto>() {
		    @Override
		    public int compare(Piloto p1, Piloto p2) {
		        return p1.getUltimaHora().compareTo(p2.getUltimaHora());
		    }
		});
		
		for(int i = 1 ; i <= lPilotos.size() ; i++)
		{
			lPilotos.get(i -1).setPosição(i);	
		}
	}
	
	public Double TempoSegundosString(String s) {
		String[] numeros = s.split("[:|.]");
		Double t = 60 * Double.parseDouble(numeros[0]) + Double.parseDouble(numeros[1])
				+ (Double.parseDouble(numeros[2]) / 1000);
		return t;
	}

}
