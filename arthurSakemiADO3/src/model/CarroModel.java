/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author arthur.msakemi
 */
public class CarroModel {

    private int id;
    private String modelo;
    private int ano;
    private double valor;

    public CarroModel() {
    }

    public CarroModel(String modelo, int ano, double valor) {
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
    }

    public CarroModel(int id, String modelo, int ano, double valor) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
