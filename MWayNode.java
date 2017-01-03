import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
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
public class MWayNode {
	public int[] A;//arrays that holds number of children and keys 
	public MWayNode parent;
	public LinkedList<MWayNode> childern;//children nodes 
	
	public static void main(String[] args) {
		MWayNode root = new MWayNode(10,14);
	
		
				
		
		MWayNode node1 =new MWayNode(8);
		node1.parent = root;
		MWayNode node2 =new MWayNode(11,13);
		node2.parent = root;
		MWayNode node3 =new MWayNode(16);
		node3.parent = root;
		
		
		root.childern = new LinkedList<MWayNode>();
		root.childern.add(node1);
		root.childern.add(node2);
		root.childern.add(node3);
		
		
		System.out.println("2-4 Tree represantion " + root.toString());
		System.out.println(" tree height "+root.height());
		
		System.out.println(" is 2-4 tree  "+ root.check_2_4Baum());
		root.insert(15);
		System.out.println("tree after 15 insertion " +root.toString());
		root.insert(12);
		System.out.println("tree after 12 insertion " +root.toString());
		//no 2-4 , a leaves height is not the same 
		root = new MWayNode(10,215);
		node1 =new MWayNode(8);
		node1.parent = root;
		node2 = new MWayNode();
		
		node3 =new MWayNode(16);
		node3.parent = root;
		
		
		root.childern = new LinkedList<MWayNode>();
		root.childern.add(node1);
		root.childern.add(node2);
		root.childern.add(node3);
		
		System.out.println(" second tree height "+root.height());
		System.out.println(" is second tree 2-4 tree  "+ root.check_2_4Baum());
		
		//a tree with overflow 
		root = new MWayNode(10,215);
		node1 =new MWayNode(8);
		node1.parent = root;
		node2 = new MWayNode(98);
		
		node3 =new MWayNode(716);
		node3.parent = root;
	
		root.A[0]=5;
		root.childern = new LinkedList<MWayNode>();
		root.childern.add(node1);
		root.childern.add(node2);
		root.childern.add(node3);
		
		System.out.println("tree with overflow  tree height "+root.height());
		System.out.println(" is third  tree 2-4 tree  "+ root.check_2_4Baum());
	}

	//Begin 8.a
	/**
	 * constructor to create a leaf 
	 * 
	 */
	public MWayNode(){
		A = new int[5];
		
		childern = new LinkedList<MWayNode>();
		A[0]=0;
	}
	/**
	 * a constructor to create a node with
	 * one key , one key has 2 leaves 
	 * 
	 * @param k1
	 */
	public MWayNode(int k1){
		A = new int[5];
		A[0]=2;//number of children, in our case leaves 
		A[1]=k1;
		//create children 
		childern = new LinkedList<MWayNode>();
		childern.add(new MWayNode());
		childern.add(new MWayNode());
		//assign parent to children 
		for(MWayNode child:this.childern){
			child.parent=this;
		}
		
	}
	/**
	 * a constructor to create a node with
	 * 2 key , 2 keys have 3 children leaves 
	 * 
	 * @param k1, k2 
	 */
	
	public MWayNode(int k1, int k2){
		A = new int[5];
		A[0]=3;
		A[1]=k1;
		A[2]=k2;
		childern = new LinkedList<MWayNode>();
		childern.add(new MWayNode());
		childern.add(new MWayNode());
		childern.add(new MWayNode());
		for(MWayNode child:this.childern){
			child.parent=this;
		}
	}
	
	/**
	 * a constructor to create a node with
	 * 3 key , 3 keys have 4 children leaves 
	 * 
	 * @param k1, k2 
	 */
	public MWayNode(int k1, int k2, int k3){
		A = new int[5];
		A[0]=4;
		A[1]=k1;
		A[2]=k2;
		A[3]=k3;
		childern = new LinkedList<MWayNode>();
		childern.add(new MWayNode());
		childern.add(new MWayNode());
		childern.add(new MWayNode());
		childern.add(new MWayNode());
		for(MWayNode child:this.childern){
			child.parent=this;
		}
	}
	public int size(){
		return A[0];
	}
	

	
	//end  8.a
	
	/**
	 * returns 
	 *rep(v) := () wenn v Blatt ist.
	rep(v) := (k1, . . . , kd−1)[rep(v1), . . . , rep(vd)] wenn v ein d-Knoten mit den Schl¨usseln
		k1, . . . , kd−1 und den Kindern v1, . . . , vd ist.
		of a tree
	 */
	//Begin 8.b
	public String toString(){
		
		
		
		return toString(this);
	}
	
	private String toString(MWayNode node){
		//if leaf 
		if (node.A[0]==0){
			return "()";
		}
		String str ="(";
		int i=1;
		//go through all keys , concatenate them to the string 
		for(;i<node.A[0]-1;i++){
			str+=node.A[i]+",";
		}
		str+=node.A[i]+")";
				
		// now concatenate nodes 
		str +="[";
		for(MWayNode c:node.childern){
			// go recursively 
			str += toString(c) +",";
		}
		str = str.substring(0,str.length()-1);
		str +="]";
		return  str;
		
	}
	
	//end 8.b
	
	
	
	//Begin 8.c 
	//the tree is 2-4 if the leaves have the same depth 
	//and all nodes except leaves have 2 to 4 children 
	
	public boolean check_2_4Baum(){
		
		return check24Baum(this);
		
		
	}
private boolean check24Baum(MWayNode node){
	boolean is24Baum=false;
	
	//if leaf return true
	if(node.A[0] ==0){
		return true;
	}
	int heights[]=new int[4];
	int i=0;
	//check of height of the nodes 
	for(MWayNode child:node.childern){
		heights[i]=height(child);
		i++;
	}
	
	boolean sameHeight=false;
	//check  whether all entries have the same height 
	for(i=0;i<node.childern.size()-1;i++){
		sameHeight = heights[i] ==heights[i+1];
		// if no return false 
		if(!sameHeight) return false;
	}
	
	//check whether  the entry has minimum 2 children and maximum 4 children 
	if((node.A[0]>=2 && node.A[0]<=4) && sameHeight){
		is24Baum =true;
		//check recursively
		for(MWayNode child:node.childern){
			is24Baum =is24Baum && check24Baum(child);
		}
		
	}
	return is24Baum;
	
}
	/**
	 * 
	 * calculates height of a node 
	 * @return
	 */
	
	public int height(){
		return height(this);
	}
	
	private int height(MWayNode node){
		
		if(node.A[0] ==0 && node.childern.size()==0){
			return 0;
		}
		
		int heights[]=new int[4];
		int i=0;
		for(MWayNode child :node.childern){
			heights[i] = height(child);
			i++;
		}
		
		return 1+findMax(heights);
	}
	
	private int findMax(int[] array){
		int max = array[0];

		for (int i = 1; i < array.length; i++) {
		    if (array[i] > max) {
		      max = array[i];
		    }
		}
		return max;
	}
	
	//2.d
	
	/**
	 * insert key in tree 
	 * 
	 * 
	 * 
	 * @param k
	 */
	
	public void insert(int k){
		//first we find a leaf 
		MWayNode leave =  findLeaf(k,this);
		MWayNode parent = leave.parent;
		
		
	
		int[] keysCopy = new int[5];
		LinkedList<MWayNode> children = new LinkedList<MWayNode>();
		//we create new keys array 
		//increment number of entries 
		keysCopy[0]=parent.A[0]+1;
		int j=1;
		//System.out.println("parent.A[0] = " +keysCopy[0]+": " + Arrays.toString(parent.A));
		int i=1;
		//we go through the keys in leaf parent 
		// to find the position where to insert the key 
		for(i=1;i<parent.A[0];i++){
			//if key is smaller than current key
			// we found the right position 
			if(k<=parent.A[i]){
				// store new key 
				keysCopy[j] =k;
				//copy the successor  key for original array 
				keysCopy[j+1] =parent.A[i];
				//create new leaf for  new inserted key 
				 MWayNode newLeaf = new MWayNode();
				 //update the parent 
				 newLeaf.parent = parent;
				 //add the new leaf to children list  
				children.add(newLeaf);
				
				j=j+2;
			}else{
				//copy key from original keys array 
			   keysCopy[j] =parent.A[i];
			   j++;
			}
			//copy child  node 
			children.add(parent.childern.get(i-1));
		}
		children.add(parent.childern.get(i-1));
		//update keys 
		parent.A = keysCopy;
		//System.out.println("keysCopy  " + Arrays.toString(keysCopy));
		//System.out.println("children   " + children);
		//update entries 
		parent.childern = children;
		
		
	}
	
	private MWayNode findLeaf(int k, MWayNode node){
		if(node.A[0]==0){
			return node;
		}
		
		int position=node.A[0]-1;
		
		for(int i=1;i<=node.A[0]-1;i++){
			//System.out.println("find " + Arrays.toString(node.A));
			
			if(k<=node.A[i]){
				
				position = i-1;
				//System.out.println("found poisitionfff " + Arrays.toString(node.A) + " "  + position);
				break;
			}
		}
		//System.out.println("found poisition " + Arrays.toString(node.A) + " "  + position);
		return findLeaf(k, node.childern.get(position));
		
	}

}
