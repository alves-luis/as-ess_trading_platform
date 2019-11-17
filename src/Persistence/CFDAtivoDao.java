package Persistence;

import Business.Observer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CFDAtivoDao implements List<Observer> {
    @Override
    public int size() {
        return 0;
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

    }

    @Override
    public Observer get(int i) {
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
