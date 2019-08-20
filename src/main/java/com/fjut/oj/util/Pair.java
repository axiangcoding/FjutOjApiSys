package com.fjut.oj.util;

/**
 * @author QAQ [20151122]
 */
public class Pair<K, V> {
    K key;
    V value;

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K k) {
        this.key = k;
    }

    public void setValue(V v) {
        this.value = v;
    }
}
