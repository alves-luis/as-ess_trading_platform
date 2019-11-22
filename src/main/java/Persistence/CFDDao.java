package Persistence;

import Business.CFD;
import Business.Long;
import Business.Negociador;

import java.io.SequenceInputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CFDDao implements Map<Integer, CFD> {

    @Override
    public int size() {

        Connection c = Connect.connect();
        if (c==null){
            System.out.println("Can't connect!");
            return 0;
        }
        Statement s;
        int result=0;

        try{
            s= c.createStatement();

            ResultSet resultset = s.executeQuery("select count(*) from cfd;");
            resultset.next();
            result = resultset.getInt(1);
            resultset.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        Connect.close(c);

        return result;

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        Connection c = Connect.connect();
        if (c == null){
            System.out.println("Can't connect!");
            return false;
        }

        Integer key= (Integer) o;

        PreparedStatement s = null;
        boolean result= false;

        try {
            s= c.prepareStatement("select id from cfd where id = ?;");
            s.setInt(1,key);

            ResultSet resultset = s.executeQuery();
            result = resultset.isBeforeFirst();
            resultset.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        Connect.close(c);
        return result;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public CFD get(Object o) {

        Connection c = Connect.connect();
        if (c == null){
            System.out.println("Can't connect!");
            return null;
        }

        Integer key = (Integer) o;

        PreparedStatement s =  null;

        try{
            s = c.prepareStatement("select * from cfd where id = ?;");
            s.setInt(1,key);

            ResultSet resultSet= s.executeQuery();
            //não há CFD com aquele id
            if(!resultSet.isBeforeFirst())
                return null;

            resultSet.next();

            int id = resultSet.getInt("id");
            Timestamp date = resultSet.getTimestamp("data");
            double uniAtivo = resultSet.getDouble("unidadesdeativo");
            double valorUnidadeCompra = resultSet.getDouble("valorporunidadenacompra");
            double limiteS = resultSet.getDouble("limiteSup");
            double limiteI = resultSet.getDouble("limitInf");
            boolean aberto = resultSet.getBoolean("aberto");
            int nifNeg = resultSet.getInt("nif");
            String idAtivo = resultSet.getString("idAtivo");
            LocalDateTime data = date.toLocalDateTime();

            CFD cfd;
            if (!aberto) {
                double valorUnidFim = resultSet.getDouble("valorporunidadenofim");
                cfd = new Long(id, data, uniAtivo, valorUnidadeCompra, limiteI, limiteS, idAtivo, nifNeg, aberto, valorUnidadeCompra);

            }
            else {
                cfd = new Long(id, data, uniAtivo, valorUnidadeCompra, limiteI, limiteS, idAtivo, nifNeg, aberto);
            }

            resultSet.close();
            return cfd;


        }
        catch (SQLException e){
            e.printStackTrace();
        }

        Connect.close(c);

        return null;
    }
    

        @Override
    public CFD put(Integer integer, CFD cfd) {
        Connection c = Connect.connect();
        if (c == null){
            System.out.println("Can't connect!");
            return null;
        }

        if (integer != cfd.getId()){
            return null;
        }

        PreparedStatement s = null;
        try{
            if(this.containsKey(integer)){
                s=c.prepareStatement("update cfd set id = ?, data = ?, unidadesdeativo = ?, valorporunidadenacompra = ?, limiteInf = ?, limiteSup = ?, nifnegociador = ?,  aberto = ?, valorporunidadenofim = ? where id = ?;");
                s.setInt(11, cfd.getId());
            }
            else
                s=c.prepareStatement("insert into cfd values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            s.setInt(1,cfd.getId());
            s.setTimestamp(2,Timestamp.valueOf(cfd.getData()));
            s.setDouble(3,cfd.getUnidadesDeAtivo());
            s.setDouble(4,cfd.getValorPorUnidadeNaCompra());
            s.setDouble(5,cfd.getLimiteInf());
            s.setDouble(6,cfd.getLimitSup());
            s.setString(7,cfd.getIdAtivo());
            s.setInt(8,cfd.getNifNegociador());
            s.setBoolean(9,cfd.isAberto());
            s.setDouble(10,cfd.getValorPorUnidadeFinal());

            int update = s.executeUpdate();

            if (update == 1)
                return cfd;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

            Connect.close(c);

            return null;
    }

    @Override
    public CFD remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends CFD> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<CFD> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, CFD>> entrySet() {
        return null;
    }
}
