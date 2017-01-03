import java.util.Arrays;
import java.util.Stack;

/**
 * 
 * 
 * Uebung 4
 * 
 * @author user Maria Sigal, Marina Khitynova

 *
 * @param <T>
 */

public class StackSort<T extends Comparable<T>> {

	public static void main(String[] args) {
		Integer[] intsarr = {-6,8,10,0,5,6,0,6};
		Integer[] intsarr2 = {100,4,0,-5,88,78,0,56,3,-1};
		 StackSort<Integer> stackSort = new  StackSort<Integer> ();
		 
		 stackSort.sort(intsarr2);
		 System.out.println(stackSort.s.peek());
		 System.out.println(Arrays.toString(stackSort.s.toArray()));
		 System.out.println(Arrays.toString(stackSort.t.toArray()));
	}
	
	public Stack<T> s;
	public Stack<T> t;
	
	public StackSort(){
		s = new Stack<T>();
		t = new Stack<T>();
		
		
	}
	
	public void sort(T[]  arr){
		
		T so;
		T to;
		 for(int i=0;i<arr.length;i++){
			 
		
			 if (!s.isEmpty() && !t.isEmpty()){
				 // invariant holds
				 so = s.peek();
				 to= t.peek();
				  if( to.compareTo(arr[i])==1){
					  
					// if the top object in t stack is bigger then we 
						// look for the right position for array[i] in 
	 // and all objects in s that are bigger than array[i]  are  
	// move to t stack

					  while(!s.isEmpty() && so.compareTo(arr[i])==1){
						  t.push(s.pop());
						  if(!s.isEmpty()){
						    so=s.peek();
						  }
						  
					  }
					  s.push(arr[i]);
					  
				  }else{
					  //if  the top object in t stack is smaller than array[i]  , this object
						// could be added in t stack, and all object that are smaller 
						// will be moved to s stack , invariant alway holds

					  while(!t.isEmpty() && to.compareTo(arr[i])==-1){
						  
						  s.push(t.pop());
						  if(!t.isEmpty()){
						   to = t.peek();
						  }
					  }
					  
					  t.push(arr[i]);
					  
				  }
				
			 }else{
				 


					//for the case that t  stack is empty 
					//if the top element is bigger than array[i], we should fix 
					//the order 

			if(!s.isEmpty()){
				so=s.peek();
				 while(!s.isEmpty() && so.compareTo(arr[i])==1){
					 t.push(s.pop());
					 if(!s.isEmpty())
					 so = s.peek();
					 
				 }
			   } 
			  s.push(arr[i]);
				 
				 
			 }
			 
			 
			 
			 
		 }
			// move elements from stack t to s 

		 // i am not sure if needed, move all objects from t to s 
		 
		 while (!t.isEmpty()){
			 s.push(t.pop());
		 }
	}

}
