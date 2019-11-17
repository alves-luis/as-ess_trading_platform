package Persistence;

import Business.Negociador;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class NegociadorDAO implements Map<Integer, Negociador> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public Negociador get(Object o) {
        return null;
    }

    @Override
    public Negociador put(Integer integer, Negociador negociador) {
        return null;
    }

    @Override
    public Negociador remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Negociador> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<Negociador> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, Negociador>> entrySet() {
        return null;
    }
}