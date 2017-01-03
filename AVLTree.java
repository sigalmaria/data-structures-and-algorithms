import java.util.ArrayList;
import java.util.List;



/** Implementation of a proper linked Binary Tree with labels of type E */

public class AVLTree {
    /** reference to the root: */
    private BTNode root;
    /** number of nodes: */
   private int size;
  
   /** default constructor: */
   public AVLTree() {
      root = new BTNode(null,null,null,null);
     
      size = 1;
   }
      /**
       * 
       * 
       * check if the tree is height balanced 
       * @return
       */
   public boolean checkHBE(){
	   
	  return checkHBE(root);
	   
   }
   
   public boolean checkHBE(BTNode node){
	   
	  //if we reached the leaf then return true
	   if(node.isLeaf()){
		  return true;
	  }
	   //get the height of left tree and height or right tree
	   
	  int leftHeight = height(node.leftChild());
	  int rightHeight= height(node.rightChild());
	  
	  int hbe =  leftHeight- rightHeight;
	 //check if the difference between heights of right and left tree are less than 2 
	  //if so then go down the tree and check the height balanced property for sub trees
      if(Math.abs(hbe)<=1  && checkHBE(node.leftChild()) && checkHBE(node.rightChild())){
    	  return true;
      }
	  
	  
	  return false;
   }
   
   /**
    * 
    * 
    * checks if the tree if binary search tree 
    * @return
    */
   public boolean checkSearchTree(){
	 

	   if(root!=null){
		  
		//we pass the values of min integer and max integer to
		   // help us to keep track of parent values in the tree
		   //because its not enough to check only locally if
		   //the parent is bigger than left child and smaller than
		   //right child , we must check that all values in left sub
		   //tree are smaller and all values in right tree are bigger
		
		   return checkSearchTree(root, Integer.MIN_VALUE,Integer.MAX_VALUE);
		   
		   
	   }
	   return true;
   }
  
   private boolean checkSearchTree(BTNode node, int min, int max){
	   
	   if(node.isLeaf()){
		   return true;
	   }
	   
	   
	   if (node.element()<min || node.element()>max){
		   return false;
	   }
	   
	   
	   return  checkSearchTree(node.leftChild(),min,node.element()) &&  
			   checkSearchTree(node.rightChild(),node.element(),max);
	   
	
	   
	
		
	}
	   
	   
	
   
   public static String   outputBT(AVLTree t){
	   
	 
		String str="";
		
		if(t.root!=null){
			
			//call the method on root
			str=outputBT(t.root);
		}
	   return str;
	   
   }
   
   /*
    * 
    * construct this art " 8(5(9, 3), 3(6, 2(5, 1)))."
    * of notation recursively
    * 
    */
   
   public static  String outputBT(BTNode node){
	 String output="";
	   
	 
	 //if node is a leaf , simply add and number to the
	 //string
	 if (node.isLeaf()){
		  return  ""+node.element();
	  }
	   output+= node.element();
	   
	   //if the node has left child then add "(" to string and go 
	   //down
	   if(node.leftChild()!=null){
		   
		   
		   
		  output+="("+outputBT(node.leftChild());
		   
	   }
	   
	   //if the node has right child 
	   //add "," to string and go down the free to that right child
	   //then we return to the current level
	   //we close our string with ")"
	   
	   if(node.rightChild()!=null){
		   
		   output+= ","+outputBT(node.rightChild())+")";
		   
	   }
	  
	  
	   return output;
   }
   
   
 
   
   
   public static void height(AVLTree t ){
	   height(t.root);
	   
   }
   
   
   
   /**
    * change the entries  values of nodes 
    * to their height
    * calculates height recursively
    * 
    * @param node
    * @return height of current node
    * 
    */
   
   public static  Integer height(BTNode node){
	  if(node.isLeaf()){
		  
		  return 0;
	  }
	  
	  int leftt = height(node.leftChild());
	  //go to right child node
	  int rigtht =height(node.rightChild());
	 //choose the biggest height of two trees
	  int height = 1+ Math.max(leftt, rigtht) ;
	
	  return height;
	   
   }
   
  
   
   /**
 
   
    /** accessor methods: */
  
   public int size() { return size; }

   public boolean isTrivial() { return (size==1); }
       /** a trivial Tree consists of the root node only
	* empty trees are excluded   */ 

   public boolean isInternal(BTNode v) {return v.isInternal(); }

   public boolean isLeaf(BTNode v) {return  v.isLeaf(); }

   public boolean isRoot(BTNode v) { return (v==root()); }

   public BTNode root() { return root; }

   public BTNode leftChild(BTNode v) { return v.leftChild(); }

   public BTNode rightChild(BTNode v) { return v.rightChild(); }

   public BTNode sibling(BTNode v) {  
      BTNode p  = parent(v);
      BTNode lc = leftChild(p);
      if (v == lc)
         return rightChild(p);
      else
         return lc;
   }

   public BTNode parent(BTNode v) { return v.parent(); }
   
    /** update methods: */
 
    /** turn a leaf v to an inner node by appending two new leaves 
     *  if v is not a leaf, the method won't do anything
     *  alternatively one could throw an exception */
   public void expandExternalNode(BTNode v){
      if( isLeaf(v)){
         v.setLeft(new BTNode(null,v,null,null)); 
         v.setRight(new BTNode(null,v,null,null)); 
         size += 2;
      }
   } 
 
    /** delete a leaf v and replace v's parent node by v's sibling
     *  if v is the root or if v is not a leaf, the method won't do anything 
     *  alternatively one could throw an exception */
   public void removeAboveExternalNode(BTNode v){
      if( isLeaf(v) && !isRoot(v)){
         BTNode p = parent(v);
         BTNode s = sibling(v);
         if( isRoot(p)){
            s.setParent(null);
            root = s;
         }  
         else{
             BTNode g = parent(p);
             if( p == leftChild(g))
                g.setLeft(s);
             else
                g.setRight(s);
             s.setParent(g);
         }
         size -= 2;
      }
   } 
   
   
   public class BTNode {
	   private Integer element;
	   private BTNode left, right, parent;

	   /** default constructor */
	   public BTNode() { }

	   /** constructor with parameters */
	   public BTNode(Integer e, BTNode u, BTNode v, BTNode w) {
	      setElement(e);
	      setParent(u);
	      setLeft(v);
	      setRight(w);
	   }
	    /** accessor methods: */
	    
	   public Integer element() { return element; }
	    
	   public void setElement(Integer e) { element=e; }
	    
	   public BTNode leftChild() { return left; }
	    
	   public void setLeft(BTNode v) { left=v; }
	    
	   public BTNode rightChild() { return right; }
	    
	   public void setRight(BTNode v) { right=v; }
	    
	   public BTNode parent() { return parent; }
	    
	   public void setParent(BTNode v) { parent=v; }

	    /** methods for  checking basic properties: */
	    
	   public boolean isLeaf() {
	      return ( left==null && right==null);
	   }
	   public boolean isInternal() {
	      return ( left!=null || right!=null);
	   }
	      /** note that in proper binary trees one could use && instead of || */
	    
	   public boolean isRoot() { return (parent==null); }

	}
   
   
   
   
   
    /** Just a short test: */
    public static void main(String[] args){
	AVLTree bt = new AVLTree();
	BTNode v = bt.new BTNode();
        (bt.root()).setLeft(v);
        bt.expandExternalNode(v);
	bt.expandExternalNode(v.leftChild());
        System.out.println(" Size = " + bt.size());
        }
}