package persistence;

import business.ativos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOHelper {


    protected static Acao getAcao(ResultSet rs, String id, String nome, double valorPorUnidade){

        try {
            String empresa = rs.getString("empresa");
            Acao c = new Acao(id, nome,valorPorUnidade,empresa);
            return c;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    protected static Moeda getMoeda(ResultSet rs, String id, String nome, double valorPorUnidade){

        try {
            String moedaA = rs.getString("moedaa");
            String moedaB = rs.getString("moedab");
            Moeda m = new Moeda(id, nome,valorPorUnidade,moedaA,moedaB);
            return m;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    protected static Indice getIndice(ResultSet rs, String id, String nome, double valorPorUnidade){

        try {
            int numEmpresa = rs.getInt("numempresas");
            Indice i = new Indice(id, nome,valorPorUnidade,numEmpresa);
            return i;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    protected static Commodity getCommodity(ResultSet rs, String id, String nome, double valorPorUnidade){

        try {
            String pais = rs.getString("pais");
            Commodity c = new Commodity(id, nome,valorPorUnidade,pais);
            return c;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    protected static String getFullInformationForType(String tipo){
        return "select * from " + tipo +"ativo INNER JOIN ativo ON ativo.id = "+ tipo + "ativo.idativo INNER JOIN "+ tipo +" ON " + tipo +".id = "+ tipo +"ativo.id"+ tipo +";";
    }

}
