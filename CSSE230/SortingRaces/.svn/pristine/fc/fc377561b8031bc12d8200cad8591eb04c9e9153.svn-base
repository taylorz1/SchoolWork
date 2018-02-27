import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * This program runs various sorts and gathers timing information on them.
 *
 * @author Zachary Taylor
 *         Created May 7, 2013.
 */
public class SortRunner {
	private static Random rand = new Random(17); // uses a fixed seed for debugging. Can remove later.
	
	/**
	 * Starts here.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// array size must be an int. You will need to use something much larger
		int size = 500000; 
		
		// Each integer will have the range from [0, maxValue). If this is significantly higher than size, you
		// will have small likelihood of getting duplicates.
		int maxValue = Integer.MAX_VALUE; 
		
		// Test 1: Array of random values.
		int[] randomIntArray = getRandomIntArray(size, maxValue);
		System.out.println("\nRunning sort for random");
		runAllSortsForOneArray(randomIntArray);

		// DONE: Tests 2-4
		// Generate the three other types of arrays (shuffled, almost sorted, almost reverse sorted)
		// and run the sorts on those as well.
		int[] shuffled = getUniqueElementArray(size);
		System.out.println("\nRunning sort for shuffled");
		runAllSortsForOneArray(shuffled);
		int[] almostsorted = getAlmostArray(getUniqueElementArray(size));
		System.out.println("\nRunning sort for almost shuffled");
		runAllSortsForOneArray(almostsorted);
		int[] almostreversesorted = getReverseArray(getUniqueElementArray(size));
		System.out.println("\nRunning sort of reverse shuffled");
		runAllSortsForOneArray(almostreversesorted);

	}

	/**
	 * 
	 * Runs all the specified sorts on the given array and outputs timing results on each.
	 *
	 * @param array
	 */
	private static void runAllSortsForOneArray(int[] array) {
		long startTime, elapsedTime; 
		boolean isSorted = false;
		
		int[] sortedIntsUsingDefaultSort = array.clone();
		Integer[] sortedIntegersUsingDefaultSort = copyToIntegerArray(array);
		Integer[] sortedIntegersUsingHeapSort = sortedIntegersUsingDefaultSort.clone();
		Integer[] sortedIntegersUsingTreeSort = sortedIntegersUsingDefaultSort.clone();
		Integer[] sortedIntegersUsingSkipListSort = sortedIntegersUsingDefaultSort.clone();
		int[] sortedIntsUsingQuickSort = array.clone();

		int size = array.length;
		
		// What is the default sort for ints? Read the javadoc.
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntsUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntsUsingDefaultSort);
		displayResults("int", "the default sort", elapsedTime, size, isSorted);
		
		// What is the default sort for Integers (which are objects that wrap ints)?
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntegersUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		System.out.println("verifying default");
		isSorted = verifySort(sortedIntegersUsingDefaultSort);
		displayResults("Integer", "the default sort", elapsedTime, size, isSorted);

		// Sort using the following methods, and time and verify each like done above. 
		// DONE: a simple sort that uses a TreeSet 
		// TODO: your BinaryHeap sort 
		// DONE: your implementation of quick sort 
		
		System.out.println();
		startTime = System.currentTimeMillis();
		treeSort(sortedIntegersUsingTreeSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		System.out.println("verifying tree");
		isSorted = verifySort(sortedIntegersUsingTreeSort);
		displayResults("int", "the tree sort", elapsedTime, size, isSorted);
		
//		startTime = System.currentTimeMillis();
//		heapSort(sortedIntegersUsingTreeSort);
//		elapsedTime = (System.currentTimeMillis() - startTime);
//		System.out.println("verifying heap");
//		isSorted = verifySort(sortedIntegersUsingTreeSort);
//		displayResults("int", "the heap sort", elapsedTime, size, isSorted);
		
		startTime = System.currentTimeMillis();
		Integer[] sortedIntsUsing = new Integer[sortedIntsUsingQuickSort.length];
		sortedIntsUsing = copyToIntegerArray(sortedIntsUsingQuickSort);
		quickSort(sortedIntsUsing);
		elapsedTime = (System.currentTimeMillis() - startTime);
		System.out.println("verifying quick");
		isSorted = verifySort(sortedIntsUsing);
		displayResults("int", "the quick sort", elapsedTime, size, isSorted);
	}
	

	private static void quickSort(Integer[] sortedIntegersUsingTreeSort) {
		quickSorter(sortedIntegersUsingTreeSort, 0, sortedIntegersUsingTreeSort.length-1);
		
	}

	private static void quickSorter(Integer[] ss, int i, int length) {
		// DONE Auto-generated method stub.
		if (length - i < 20) {
			insertionsort(ss, i, length);
			return;//Technically faster than quick sort of small arrays
		}
		int pivot = pivot(ss, i, length);
		swap(ss,pivot, length);
		pivot = length;
		int k = i;
		int j = length-1;
		while (k < j) {
			while(k < length && ss[k] < ss[pivot]) k++;
			while(j > i && ss[pivot] < ss[j]) j--;
			swap(ss, k, j);
		}
		swap(ss, pivot, j);
		pivot = j;
		
		quickSorter(ss, pivot, length);
		quickSorter(ss, i, pivot);
	}
	
	private static void swap(Integer[] ss, int index1, int index2) {
		int temp = ss[index1];
		ss[index1] = ss[index2];
		ss[index2] = temp;
	}

	private static void insertionsort(Integer[] ss, int begin, int end) {
		// DONE Auto-generated method stub.
		if (end-begin < 2) {
			return;
		}
		int i,j;
		for (i = begin; i <= end; i++) {
			int temp = ss[i];
			for (j=i; j > begin && temp < ss[j-1]; j--) {
				ss[j] = ss[j-1];
			}
			ss[j] = temp;
		}
	}

	private static int pivot(Integer[] ss, int i, int length) {
		// DONE Auto-generated method stub.
		int first = i;
		int middle = (length - i)/2 + i; //Gotta shift it back up
		int last = length;
		
		if (ss[first] > ss[middle]) {
			swap(ss, first, middle);
		}
		if (ss[last] < ss[first]) {
			swap(ss, first, last);
		}
		if (ss[middle] < ss[last]) {
			swap(ss, last, middle);
		}
		return middle;
	}

	private static void heapSort(Integer[] sortedIntegersUsingTreeSort) {
		// TODO Auto-generated method stub.
		
	}

	private static void treeSort(Integer[] sortedIntegersUsingTreeSort) {
		// DONE Auto-generated method stub.
		TreeSet<Integer> tree = new TreeSet<Integer>();
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		boolean add;
		for (int i = 0; i < sortedIntegersUsingTreeSort.length; i++) {
			add = tree.add(sortedIntegersUsingTreeSort[i]);
			if (!add) {
				int get = map.get(sortedIntegersUsingTreeSort[i]);
				map.put(sortedIntegersUsingTreeSort[i],++get);
			} else {
				map.put(sortedIntegersUsingTreeSort[i], 0);
			}
		}
		Iterator<Integer> iter = tree.iterator();
		int index = 0;
		while(iter.hasNext()) {
			int toadd = iter.next();
			sortedIntegersUsingTreeSort[index] = toadd;
			index++;
			for (int i = 0; i < map.get(toadd); i++) {
				sortedIntegersUsingTreeSort[index] = toadd;
				index++;
			}
		}
	}

	private static void displayResults(String typeName, String sortName, long elapsedTime, int size,  boolean isSorted) {
		if (isSorted) {
			System.out.printf("Sorted %.1e %ss using %s in %d milliseconds\n", (double)size, typeName, sortName, elapsedTime);
		} else {
			System.out.println("ARRAY NOT SORTED");
		}
	}
	
	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a An array to check to see if it is sorted.
	 */
	private static boolean verifySort(int[] a) {
		// DONE: implement this.
		for(int i = 0; i < a.length-1; i++) {
			if (a[i] > a[i+1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a An array to check to see if it is sorted.
	 */
	private static boolean verifySort(Integer[] a) {
		// DONE: implement this.
		for (int i = 0; i < a.length-1; i++) {
			if (a[i] > a[i+1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Copies from an int array to an Integer array.
	 *
	 * @param randomIntArray
	 * @return A clone of the primitive int array, but with Integer objects.
	 */
	private static Integer[] copyToIntegerArray(int[] ints) {
		Integer[] integers = new Integer[ints.length];
		for (int i = 0; i < ints.length; i++) {
			integers[i] = ints[i];
		}
		return integers;
	}

	/**
	 * Creates and returns an array of random ints of the given size.
	 *
	 * @return An array of random ints.
	 */
	private static int[] getRandomIntArray(int size, int maxValue) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = rand.nextInt(maxValue);
		}
		return a;
	}

	/**
	 * Creates a shuffled random array.
	 *
	 * @param size
	 * @return An array of the ints from 0 to size-1, all shuffled
	 */
	private static int[] getUniqueElementArray(int size) {
		// DONE: implement and call this method.
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			array.add(i);
		}
		Collections.shuffle(array);
		int[] ret = new int[size];
	    Iterator<Integer> iterator = array.iterator();
	    for (int i = 0; i < ret.length; i++) {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	
	private static int[] getAlmostArray(int[] array) {
		// DONE: implement and call this method.
		int[] tosort = new int[(int) (array.length*.3)+1];
		for (int i = 0; i < (int) array.length*.3; i++) {
			tosort[i] = array[i];
		}
		Arrays.sort(tosort);
		int[] toreturn = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			if (i < (int) array.length * .3) {
				toreturn[i] = tosort[i];
			} else {
				toreturn[i] = array[i];
			}
		}
		return toreturn;
	}
	
	private static int[] getReverseArray(int[] array) {
		// DONE: implement and call this method.
		Integer[] tosort = new Integer[(array.length/3)];
		for (int i = 0; i < (array.length/3); i++) {
			tosort[i] = array[i];
		}
		Arrays.sort(tosort, Collections.reverseOrder());
		int[] toreturn = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			if (i < (int) array.length * .3) {
				toreturn[i] = tosort[i];
			} else {
				toreturn[i] = array[i];
			}
		}
		return toreturn;
	}
}
