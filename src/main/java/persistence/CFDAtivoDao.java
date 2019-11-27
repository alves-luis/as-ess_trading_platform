package persistence;

import business.CFD;
import business.Long;
import business.Observer;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CFDAtivoDao implements List<Observer> {
    private String idAtivo;

    public CFDAtivoDao(String id) {
        this.idAtivo = id;
    }

    @Override
    public int size() {
        Connection c = Connect.connect();
        if (c == null) {
            System.out.println("Can't connect!");
            return 0;
        }

        PreparedStatement s = null;
        int result = 0;

        try {
            s = c.prepareStatement("select count(*) from cfd where idativo = ?;");
            s.setString(1, this.idAtivo);

            ResultSet resultSet = s.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);
            resultSet.close();

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
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Observer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Observer observer) {
        Connection c = Connect.connect();
        if (c == null) {
            System.out.println("Can't connect!");
            return false;
        }

        // we need to ignore the pattern here, to save our DB state :/!
        if (!(observer instanceof CFD))
            return false;

        CFD cfd = (CFD) observer;
        if (cfd.isAberto())
            return false;

        PreparedStatement s = null;
        try {
            s = c.prepareStatement("update cfd set aberto = ?, valorporunidadenofim = ? where cfd.id = ?; ");
            s.setBoolean(1, cfd.isAberto());
            s.setDouble(2, cfd.getValorPorUnidadeFinal());
            s.setInt(3, cfd.getId());

            s.executeUpdate();

            Connect.close(c);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connect.close(c);
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Observer> collection) {
        for(Observer o : collection) {
            this.add(o);
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Observer> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        // do nothing
    }

    @Override
    public Observer get(int i) {
        Connection c = Connect.connect();
        if (c == null) {
            System.out.println("Can't connect!");
            return null;
        }

        PreparedStatement s;
        try {
            s = c.prepareStatement("select * from cfd where idativo = ?");
            s.setString(1,this.idAtivo);

            ResultSet resultSet = s.executeQuery();
            if (!resultSet.isBeforeFirst())
                return null;

            while(i >= 0) {
                resultSet.next();
                i--;
            }
            int id = resultSet.getInt("id");
            LocalDateTime data = resultSet.getTimestamp("data").toLocalDateTime();
            double udativo = resultSet.getDouble("unidadesdeativo");
            double vpuc = resultSet.getDouble("valorporunidadenacompra");
            Double limitesup = resultSet.getDouble("limitesup");
            Double limiteinf = resultSet.getDouble("limiteinf");
            String idAtivo = this.idAtivo;
            int nif = resultSet.getInt("nifnegociador");
            boolean aberto = resultSet.getBoolean("aberto");

            Observer n = new Long(id, data, udativo, vpuc, limiteinf, limitesup, idAtivo, nif, aberto);

            resultSet.close();

            Connect.close(c);
            return n;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        Connect.close(c);

        return null;
    }

    @Override
    public Observer set(int i, Observer observer) {
        return null;
    }

    @Override
    public void add(int i, Observer observer) {

    }

    @Override
    public Observer remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Observer> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Observer> listIterator(int i) {
        return null;
    }

    @Override
    public List<Observer> subList(int i, int i1) {
        return null;
    }
}
