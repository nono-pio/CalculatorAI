package com.exemple.test;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		
		int[] n = new int[] {1, 2};
		int[] n1 = new int[] {1, 2};
		int[] n2 = new int[] {1, 2, 3};
		int[][] m = new int[][] {n, n1, n2};
		
		int[] base = new int[] {};
		
		ArrayList<int[]> couples = getCouples(m, base);
		for (int[] is : couples) {
			for (int is2 : is) {
				System.out.print(is2 + "-");
			}
			System.out.println();
		}

	}
	
	public static ArrayList<int[]> getCouples(int[][] couplesList, int[] base)
	{ return getCouples(new int[couplesList.length], couplesList, 0, base); }
	
	public static ArrayList<int[]> getCouples(int[] curPath, int[][] paths, int index, int[] base)
	{
		if (index >= paths.length)
		{
			ArrayList<int[]> r = new ArrayList<>();
			int[] path = new int[base.length + curPath.length];
			System.arraycopy(base, 0, path, 0, base.length);  
			System.arraycopy(curPath, 0, path, base.length, curPath.length); 
			r.add(path);
			return r;
		}
		ArrayList<int[]> couples = new ArrayList<int[]>();
		for (int path : paths[index]) {
			curPath[index] = path;
			couples.addAll(getCouples(curPath, paths, index + 1, base));
		}
		
		return couples;
	}
}
