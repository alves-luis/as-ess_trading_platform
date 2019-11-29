package persistence;
import business.ativos.Ativo;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class NegociadorAtivoDAO implements List<Ativo> {

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

            ResultSet rs = s.executeQuery("select count(*) from negociadorativo where idnegociador = " + nifNegociador +";");
            rs.next();
            result = rs.getInt(1);
            rs.close();
        }
        catch (SQLException e) {
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

        Connection c = Connect.connect();
        if (c == null){
            System.out.println("Can't connect");
            return false;
        }

        String key = (String) o;
        boolean result = false ;

        PreparedStatement s;

        try{
            s = c.prepareStatement("select * from negociadorativo where idAtivo = ? ;");
            s.setString(1,key);

            ResultSet resultSet = s.executeQuery();
            result = resultSet.isBeforeFirst();
            resultSet.close();
        }
        catch (SQLException e){
            e.toString();
        }

        Connect.close(c);
        return result;
    }

    @Override
    public Iterator<Ativo> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Ativo ativo) {
        Connection c = Connect.connect();
        if (c == null){
            System.out.print("Can't connect");
            return false;
        }

        PreparedStatement s;
        boolean result = false;

        try{
            if(! this.contains(ativo.getId())){
               s = c.prepareStatement("insert into negociadorativo values (?,?,?)");
               s.setInt(1,this.nifNegociador);
               System.out.println(ativo.getId());

               s.setString(2,ativo.getId());
               s.setDouble(3,ativo.getValorPorUnidade());
               int inserted = s.executeUpdate();

               if(inserted == 1) {
                   result = true;
               }
            }
        }
        catch (SQLException e){
            e.toString();
        }

        Connect.close(c);
        return result;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Ativo> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Ativo> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Ativo get(int index) {
        return null;
    }

    @Override
    public Ativo set(int index, Ativo element) {
        return null;
    }

    @Override
    public void add(int index, Ativo element) {

    }

    @Override
    public Ativo remove(int index) {
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
    public ListIterator<Ativo> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Ativo> listIterator(int index) {
        return null;
    }

    @Override
    public List<Ativo> subList(int fromIndex, int toIndex) {
        return null;
    }
}