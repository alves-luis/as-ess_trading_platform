package persistence;

import business.ativos.Ativo;
import business.ativos.AtivoConsts;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AtivoTipoDAO implements Map<String, List<Ativo>> {
    @Override
    public int size() {
        return AtivoConsts.TOTAL_TIPOS_ATIVOS;
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
    public List<Ativo> get(Object o) {
        return null;
    }

    @Override
    public List<Ativo> put(String s, List<Ativo> ativos) {
        return null;
    }

    @Override
    public List<Ativo> remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends List<Ativo>> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<List<Ativo>> values() {
        return null;
    }

    @Override
    public Set<Entry<String, List<Ativo>>> entrySet() {
        return null;
    }
}
