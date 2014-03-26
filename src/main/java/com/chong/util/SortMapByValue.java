package com.chong.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * 将Map按照value进行排序。
 *
 * @author  崇伟峰
 */
public class SortMapByValue {

     public static <K, V extends Comparable<V>> Map<K, V> sortByValue(
            Map<K, V> map) {
        List<Entry<K, V>> list = new LinkedList<Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Entry<K, V>>() {
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {

                Comparable<V> v1 = o1.getValue();
                V v2 = o2.getValue();
                if (v1 == null) {
                    if (v2 == null) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    if (v2 == null) {
                        return 1;
                    } else {
                        return (-v1.compareTo(v2));
                    }
                }
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        Iterator<Entry<K, V>> it = list.iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    


	/**
	 * 抽取出一定数量最常用的字/词及其频率. 并按照频率从高到低排列
	 * 
	 * @param num
	 *            要抽取的常用字/词的个数
	 * @return 以字/词为键，以字/词频率为值的map
	 */
	public static <K, V extends Comparable<V>> Map<K, V> dumpDescentMap(Map<K,V> map,int num) {
		Map<K, V> valueSortedMap = SortMapByValue.sortByValue(map);
		List<Entry<K,V>> list = new LinkedList<Entry<K,V>>(valueSortedMap.entrySet());

		Map<K,V> resultMap = new TreeMap<K,V>();

		for (int i = 0, j = Math.min(num, list.size()); i < j; i++) {
			Entry<K,V> word = list.get(i);
			resultMap.put(word.getKey(), word.getValue());
		}

		return sortByValue(resultMap);
	}
    
	public static <K, V extends Comparable<V>> Set<K> dumpDescentSet(Map<K,V> map,int num) {
	
		return (Set<K>) dumpDescentMap(map, num).keySet();
	}
    
	/**
	 * @param map
	 * @param threshold 大于该值的entry会被抽取出来
	 * @return
	 */
	public static <K, V extends Comparable<V>> Map<K, V> dumpDescentMapByThreshold(Map<K, V> map,V threshold){
		Map<K, V> valueSortedMap = SortMapByValue.sortByValue(map);
		List<Entry<K,V>> list = new LinkedList<Entry<K,V>>(valueSortedMap.entrySet());

		Map<K,V> resultMap = new TreeMap<K,V>();

		for (int i = 0, j = list.size(); i < j; i++) {
			
			Entry<K,V> word = list.get(i);
			if(word.getValue().compareTo(threshold)>0){
			resultMap.put(word.getKey(), word.getValue());
			}else{
				break;
			}
		}

		return sortByValue(resultMap);
	}

	public static <K, V extends Comparable<V>> Set<K> dumpDescentSetByThreshold(Map<K, V> map,V threshold){
		return dumpDescentMapByThreshold(map, threshold).keySet();
	}

}
