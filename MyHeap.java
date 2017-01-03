import java.util.Arrays;
/**
 * 
 * 
 * 
 * 
 * @author Maria Sigal 
 * Marina Khytynova
 * 
 * Tutorium , Dienstag 16:00
 *
 */

public class MyHeap {

	
	
	private int lastElem;
	private Integer[] arr;
	
	public MyHeap(int size){
		//create array with size as param 
		arr = new Integer[size];
		lastElem=0;
	}
	
	
	
	/**
	 * 
	 * check if element in index i has left child 
	 * 
	 * 
	 * @param i
	 * @return
	 */
	public boolean hasLeftChild(int i){
		
		return  leftChildIndex(i) <=lastElem;
	}
	
	/**
	 * check if element in index i has right child 
	 * 
	 * 
	 * @param i
	 * @return
	 */
	public boolean hasRightChild(int i){
		
		
		return  rightChildIndex(i)<=lastElem;
		
		
	}
	/**
	 * returns left child index of element in index i
	 * 
	 * 
	 * @param i
	 * @return
	 */
	
	public int leftChildIndex(int i){
		return i*2;
	}
	
	/**
	 * returns right child index of element in index i
	 * 
	 * 
	 * @param i
	 * @return
	 */
	public int rightChildIndex(int i){
		
		return i*2+1;
	}
	/**
	 * returns true in element in index k has parent 
	 * 
	 * @param k
	 * @return
	 */
	
	public boolean hasParent(int k){
		int i = k/2;
		
		return i!=0;
		
	}
	/**
	 * returns index of parent of element in index i
	 * 
	 * 
	 * @param i
	 * @return
	 */
	public int  parent(int i){
		
		return i/2;
	}
	
	
	/**
	 * 
	 * removes element with smallest key 
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public int deleteMin() throws Exception{
		
		//if heap is empty , throw exception 
		if(lastElem==0){
			throw new Exception("Empty Array");
			
		}
		int result = arr[1];
	
		arr [1] = arr[lastElem];
		arr[lastElem] =(Integer) null;
		lastElem--;
		//restore the heap property
		bubbleDown(1);
		return result;
		
	}
	/**
	 * 
	 * insert new element to the heap 
	 * 
	 * @param elm
	 * @throws Exception
	 */
	
	public void insert(int elm) throws Exception{
		
		//if heap is full, throw exception
		if(lastElem+1>=arr.length){
			
			throw new Exception("Heap overflow");
		}
		//increase number of elements in heap
		lastElem++;
		arr[lastElem]=elm;
		//fix the heap property 
		bubbleUp(lastElem);
		
		
		
	}
	/*
	 * fixes the heap property with bubble up approach 
	 * 
	 * goes up to the tree from i element ans swaps elements
	 * as longs as the order is not correct 
	 */
	private void bubbleUp(int i){
		
		
		while(hasParent(i) && arr[parent(i)]>arr[i]){	
			
			
				
				swap(parent(i),i);
				i=parent(i);
			
		}
	}
	/*
	 * fixes the heap property with bubble down approach 
	 * goes down the tree from i ans swaps elements
	 * as lons as heap is not in right order 
	 * 
	 */
	private void bubbleDown(int i){
		
		
		
		
	  while(hasLeftChild(i)){
		
		int smallerChildIndex =leftChildIndex(i);
		  
		if( hasRightChild(i) && arr[rightChildIndex(i)]<arr[ smallerChildIndex ]){
			
			 smallerChildIndex  =rightChildIndex(i);
			
		}
		
		
		if(arr[i]>arr[ smallerChildIndex ]){
			swap(i,smallerChildIndex);
			i = smallerChildIndex;
		
		}else{
			
			break;
		}
		
	  }
	  
	  
		
	}
	/**
	 * update element in index i with new key k 
	 * 
	 * 
	 * @param i
	 * @param k
	 */
	
	public void updateKey(int i, int k){
		
		arr[i] = k;
		//
		//if parent is bigger than new key,
		//the heap property is corrupted, we need to fix 
		if (hasParent(i) && parent(i)>k){
			
			bubbleUp(i);
			//if left child is smaller than new key,
			//the heap property is corrupted, we need to fix 
		}else if(hasLeftChild(i) && leftChildIndex(i)<k){
			
			bubbleDown(i);
		}else if(hasRightChild(i) && rightChildIndex(i)<k){
			bubbleDown(i);
		}
		
	}
	
	public boolean isEmpty(){
		
		
		return lastElem <1;
	}
	
	/**
	 * 
	 * 
	 * sort the elements 
	 * @return
	 */
	public Integer[] sort(){
		
		Integer[]sortedArray = new Integer[lastElem];
		int i=0;
		while(!isEmpty()){
			
			try {
				Integer x = deleteMin();
				
				sortedArray[i] =x ;
				i++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sortedArray;
		
	}

	private void swap(int i, int j){
		
		int k = arr[i];
		arr[i] = arr[j];
		arr[j] = k;
	}
	
	public static void main(String[] args) {
	 MyHeap heap = new MyHeap(20);
	 //15, 50, -5, 34,90, 100, 0, 4, -9, - 45, 99 , 89
	 
	
	 try{
			 heap.insert(15);
			 heap.insert(50);
			 heap.insert(-5);
			 heap.insert(34);
			 heap.insert(34);
			 heap.insert(90);
			 heap.insert(100);
			 heap.insert(0);
			 heap.insert(4);
			 heap.insert(-9);
			 heap.insert(-45);
			 heap.insert(99);
			 heap.insert(89);
			 System.out.println("array: " + Arrays.toString( heap.arr));
			 heap.updateKey(7, -100);
			 System.out.println("update key at pos 7 to -100: " + Arrays.toString( heap.arr));
			Integer[] sortedArray = heap.sort();
			
			//System.out.println("sorted array: " + Arrays.toString(sortedArray));
			
			
			System.out.println("sorted array: " + Arrays.toString(sortedArray));
			 
	 }catch(Exception exp){
		 
		 System.out.println(exp.getMessage());
	 }
	 
	 
	 
	 

	}

}
