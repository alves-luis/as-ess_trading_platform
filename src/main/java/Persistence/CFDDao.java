package Persistence;

import Business.CFD;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CFDDao implements Map<Integer, CFD> {
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
    public CFD get(Object o) {
        return null;
    }

    @Override
    public CFD put(Integer integer, CFD cfd) {
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
