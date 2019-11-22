package Persistence;

import Business.CFD;
import Business.Long;
import Business.Observer;

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
            s = c.prepareStatement("select count(*) from ativo where id = ?;");
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

        PreparedStatement s = null;
        try {
            s = c.prepareStatement("insert into cfd values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            s.setInt(1, cfd.getId());
            s.setTimestamp(2, Timestamp.valueOf(cfd.getData()));
            s.setDouble(3, cfd.getUnidadesDeAtivo());
            // TO-DO
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        return false;
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
        Connection c = Connect.connect();
        if (c == null) {
            System.out.println("Can't connect!");
            return;
        }

        try {
            PreparedStatement s = null;
            s = c.prepareStatement("delete from cfd where idativo = ?, aberto = b'1';");
            s.setString(1, this.idAtivo);

            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connect.close(c);
    }

    @Override
    public Observer get(int i) {
        Connection c = Connect.connect();
        if (c == null) {
            System.out.println("Can't connect!");
            return null;
        }

        PreparedStatement s = null;
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
