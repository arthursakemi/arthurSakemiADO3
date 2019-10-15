/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.CarroModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarroDAO {

    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String LOGIN = "root";
    public static String SENHA = "";

    public static String URL = "jdbc:mysql://localhost:3307/lojacarro?useTimezone=true&serverTimezone=UTC&useSSL=false";

    public static boolean salvar(CarroModel c) {
        boolean retorno = false;
        Connection conexao = null;
        Statement instrucaoSQL = null;

        try {
            //Carrega a classe responsável pelo driver
            Class.forName(DRIVER);

            //Tenta estabeler a conexão com o SGBD e cria o objeto de conexão
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
            instrucaoSQL = conexao.createStatement();

            int linhasAfetadas = instrucaoSQL.executeUpdate("INSERT INTO carro (modelo, ano, valor) "
                    + "VALUES("
                    + "'" + c.getModelo() + "'" + ","
                    + c.getAno() + ","
                    + c.getValor() + ")"
            );

            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado.");
            retorno = false;
        } catch (SQLException ex) {
            System.out.println("Erro no comando SQL.");
            retorno = false;
        } finally {

            //Libero os recursos da memória
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static boolean editar(CarroModel c) {
        boolean retorno = false;
        Connection conexao = null;
        Statement instrucaoSQL = null;

        try {
            //Carrega a classe responsável pelo driver
            Class.forName(DRIVER);

            //Tenta estabeler a conexão com o SGBD e cria o objeto de conexão
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
            instrucaoSQL = conexao.createStatement();

            int linhasAfetadas = instrucaoSQL.executeUpdate("UPDATE carro"
                    + "SET modelo = '" + c.getModelo() + "',"
                    + "ano = " + c.getAno() + ","
                    + "valor = " + c.getValor()
                    + "WHERE idcarro = " + c.getId()
            );

            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado.");
            retorno = false;
        } catch (SQLException ex) {
            System.out.println("Erro no comando SQL.");
            retorno = false;
        } finally {

            //Libero os recursos da memória
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static boolean excluir(int id) {
        boolean retorno = false;
        Connection conexao = null;
        Statement instrucaoSQL = null;

        try {
            //Carrega a classe responsável pelo driver
            Class.forName(DRIVER);

            //Tenta estabeler a conexão com o SGBD e cria o objeto de conexão
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
            instrucaoSQL = conexao.createStatement();

            int linhasAfetadas = instrucaoSQL.executeUpdate("DELETE FROM carro WHERE idcarro = " + id);

            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado.");
            retorno = false;
        } catch (SQLException ex) {
            System.out.println("Erro no comando SQL.");
            retorno = false;
        } finally {

            //Libero os recursos da memória
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static ArrayList<CarroModel> consultar() {
        Connection conexao = null;
        Statement instrucaoSQL = null;
        ResultSet rs = null;

        ArrayList<CarroModel> listaCarros = new ArrayList<CarroModel>();

        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
            instrucaoSQL = conexao.createStatement();
            rs = instrucaoSQL.executeQuery("SELECT * FROM cliente;");

            while (rs.next()) {
                CarroModel c = new CarroModel();
                c.setId(rs.getInt("idcarro"));
                c.setModelo(rs.getString("modelo"));
                c.setAno(rs.getInt("ano"));
                c.setValor(rs.getDouble("valor"));
                listaCarros.add(c);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado.");
            listaCarros = null;
        } catch (SQLException ex) {
            System.out.println("Erro no comando SQL.");
            listaCarros = null;
        } finally {
            //Libero os recursos da memória
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
            }
        }

        return listaCarros;
    }

}
