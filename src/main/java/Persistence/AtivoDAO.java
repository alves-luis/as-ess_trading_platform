package Persistence;

import Business.Ativos.Ativo;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AtivoDAO implements Map<String,Ativo>{

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
	public Ativo get(Object o) {
		return null;
	}

	@Override
	public Ativo put(String s, Ativo ativo) {
		return null;
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