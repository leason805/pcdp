package com.boxun.estms.util;

import java.util.Collections;
import java.util.List;

public class SortUtil {

	public static void sort(List list, String field){
		SortComparator comparator = new SortComparator(field);
		Collections.sort(list, comparator);
	}
	
	public static void sort(List list, String field, String orderBy){
		SortComparator comparator = new SortComparator(field, orderBy);
		Collections.sort(list, comparator);
	}
	
}
