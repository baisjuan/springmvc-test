package com.devspark.dietsystem.util;

import java.util.ArrayList;
import java.util.List;

/**
 * User: dfanaro
 * Date: 6/13/13
 * Time: 12:26 PM
 */
public class AbstractList<T> {

    private List<T> list;

    public AbstractList() {
        this.list = new ArrayList<T>();
    }

    public void addList(List<T> list) {
        this.list.addAll(list);
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return this.list;
    }

    public void addElement(T element) {
        this.list.add(element);
    }
}
