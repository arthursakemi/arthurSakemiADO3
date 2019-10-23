/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.CarroModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.GerenciadorConexao;

public class CarroDAO {

    public static boolean salvar(CarroModel c) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement(
                    "INSERT INTO carro (modelo, ano, valor)"
                    + "VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            instrucaoSQL.setString(1, c.getModelo());
            instrucaoSQL.setInt(2, c.getAno());
            instrucaoSQL.setDouble(3, c.getValor());

            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();

                if (generatedKeys.next()) {
                    c.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID do carro!");
                }

            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static boolean editar(CarroModel c) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement(
                    "UPDATE carro SET modelo = ?, ano = ?, valor = ?"
                    + "WHERE idcliente = ?;");

            instrucaoSQL.setString(1, c.getModelo());
            instrucaoSQL.setInt(2, c.getAno());
            instrucaoSQL.setDouble(3, c.getValor());
            instrucaoSQL.setInt(4, c.getId());

            int linhasAfetadas = instrucaoSQL.executeUpdate();

            retorno = linhasAfetadas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            retorno = false;

        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {

            }
        }

        return retorno;
    }

    public static boolean excluir(int id) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("DELETE FROM carro WHERE idcliente = ?;");

            instrucaoSQL.setInt(1, id);

            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;

            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            retorno = false;

        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static ArrayList<CarroModel> consultar() {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ResultSet rs = null;

        ArrayList<CarroModel> listaCarros = new ArrayList<>();

        try {

            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM carro;");

            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                CarroModel c = new CarroModel();
                c.setId(rs.getInt("idcarro"));
                c.setModelo(rs.getString("modelo"));
                c.setAno(rs.getInt("ano"));
                c.setValor(rs.getDouble("valor"));
                listaCarros.add(c);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaCarros = null;
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                GerenciadorConexao.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return listaCarros;
    }

}
