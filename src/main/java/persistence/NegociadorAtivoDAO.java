package persistence;
import business.ativos.Ativo;

import java.sql.*;
import java.util.*;


public class NegociadorAtivoDAO implements Map<String, Ativo> {

    private Integer nifNegociador;

    public NegociadorAtivoDAO(int nif) {
        this.nifNegociador = nif;
    }

    @Override
    public int size() {
        Connection c = Connect.connect();
        if (c == null) {
            System.out.println("Can't connect!");
            return 0;
        }

        Statement s;
        int result = 0;

        try {
            s = c.createStatement();

            ResultSet rs = s.executeQuery("select count(*) from negociadorativo where idnegociador = " + nifNegociador + ";");
            rs.next();
            result = rs.getInt(1);
            rs.close();
        } catch (SQLException e) {
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
        if (c == null) {
            System.out.println("Can't connect");
            return false;
        }

        String key = (String) o;
        boolean result = false;

        PreparedStatement s;

        try {
            s = c.prepareStatement("select * from negociadorativo where idAtivo = ?");
            s.setString(1, key);

            ResultSet resultSet = s.executeQuery();
            result = resultSet.isBeforeFirst();
            resultSet.close();
        } catch (SQLException e) {
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
    public Ativo get(Object o) {
        Connection c = Connect.connect();
        if (c == null) {
            System.out.print("Can't connect");
            return null;
        }

        PreparedStatement s;
        String id = (String) o;
        try {
            s = c.prepareStatement("select * from negociadorativo where idnegociador = ? and idativo = ?");
            s.setInt(1, this.nifNegociador);
            s.setString(2, id);

            ResultSet rs = s.executeQuery();
            if (!rs.isBeforeFirst())
                return null;

            rs.next();

            String idAtivo = rs.getString("idativo");
            double origValue = rs.getDouble("valororiginal");
            Ativo a = new AtivoDAO().get(idAtivo); // reuse code :P
            a.setValorPorUnidade(origValue);
            Connect.close(c);
            return a;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connect.close(c);
        return null;

    }

    @Override
    public Ativo put(String id, Ativo ativo) {
        Connection c = Connect.connect();
        if (c == null){
            System.out.print("Can't connect");
            return null;
        }

        PreparedStatement s;

        try {
            if (!this.containsKey(id)) {
                s = c.prepareStatement("insert into negociadorativo values (?,?,?)");
                s.setInt(1,this.nifNegociador);
                s.setString(2,ativo.getId());
                s.setDouble(3,ativo.getValorPorUnidade());
                s.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        Connect.close(c);
        return ativo;
    }

    @Override
    public Ativo remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Ativo> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Ativo> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Ativo>> entrySet() {
        return null;
    }
}