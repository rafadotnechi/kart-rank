package com.rafael.kartrank.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Piloto {
	private Integer Id;
	private String nome;
	private List<Double> tVoltas;
	private List<Double> velocidades;
	private Double vmedia;
	private int vCompletas;
	private int posição;
	private Date ultimoMomento;
	private Double melhorVolta;
	private Double tempoDoPrimeiro;

	public Date getUltimoMomento() {
		return ultimoMomento;
	}

	public void setUltimoMomento(Date ultimoMomento) {
		this.ultimoMomento = ultimoMomento;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Double> getVelocidades() {
		return velocidades;
	}

	public void setVelocidades(List<Double> velocidades) {
		this.velocidades = velocidades;
	}

	public List<Double> gettVoltas() {
		return tVoltas;
	}

	public void settVoltas(List<Double> tVoltas) {
		this.tVoltas = tVoltas;
	}

	public Double getVmedia() {
		return vmedia;
	}

	public void setVmedia(Double vmedia) {

		this.vmedia = vmedia;
	}

	public void calcVmedia() {
		Double vtotal = 0.0;
		for (Double v : this.velocidades) {
			vtotal += v;
		}
		this.vmedia = vtotal / (this.velocidades.size());
	}

	public void calcMelhorVolta() {
		this.melhorVolta = Collections.min(this.tVoltas, null);
	}

	public int getvCompletas() {
		return vCompletas;
	}

	public void setvCompletas(int vCompletas) {
		this.vCompletas = vCompletas;
	}

	public int getPosição() {
		return posição;
	}

	public void setPosição(int posição) {
		this.posição = posição;
	}

	public Double getMelhorVolta() {
		return melhorVolta;
	}

	public void setMelhorVolta(Double melhorVolta) {
		this.melhorVolta = melhorVolta;
	}

	public Double getTempoDoPrimeiro() {
		return tempoDoPrimeiro;
	}

	public void setTempoDoPrimeiro(Double tempoDoPrimeiro) {
		this.tempoDoPrimeiro = tempoDoPrimeiro;
	}

	public void calcTempoDoPrimeiro(Date tempo) {
		Long diff = Math.abs(ultimoMomento.getTime() - tempo.getTime());
		this.tempoDoPrimeiro = diff.doubleValue() / 1000;
	}

	@Override
	public String toString() {
		return "Piloto [Id=" + Id + ", Nome=" + nome + ", Velocidade Media=" + vmedia + ", Voltas Completas="
				+ vCompletas + ", Posição=" + posição + ", Melhor Volta=" + melhorVolta + "s"
				+ ", Diferença para o primeiro=" + tempoDoPrimeiro + "s" + "]";
	}

}
