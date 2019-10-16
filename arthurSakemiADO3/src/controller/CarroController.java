/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CarroDAO;
import java.util.ArrayList;
import model.CarroModel;

/**
 *
 * @author arthur.msakemi
 */
public class CarroController {

    public static boolean salvar(String modelo, int ano, double valor) {
        CarroModel c = new CarroModel(modelo, ano, valor);
        return CarroDAO.salvar(c);
    }

    public static boolean editar(int id, String modelo, int ano, double valor) {
        CarroModel c = new CarroModel(id, modelo, ano, valor);
        return CarroDAO.editar(c);
    }

    public static boolean excluir(int id) {
        return CarroDAO.excluir(id);
    }

    public static ArrayList<String[]> consultar() {
        ArrayList<CarroModel> carros = CarroDAO.consultar();
        ArrayList<String[]> listaCarros = new ArrayList<>();

        for (int i = 0; i < carros.size(); i++) {
            listaCarros.add(new String[]{String.valueOf(carros.get(i).getId()),
                carros.get(i).getModelo(),
                String.valueOf(carros.get(i).getAno()),
                String.valueOf(carros.get(i).getValor())
            });
        }

        return listaCarros;

    }

}
