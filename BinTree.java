import java.util.ArrayList;
import java.util.List;

/** Implementation of a proper linked Binary Tree with labels of type E */

public class BinTree<E> {
    /** reference to the root: */
    private BTNode<E> root;
    /** number of nodes: */
   private int size;
  
   /** default constructor: */
   public BinTree() {
      root = new BTNode(null,null,null,null);
      size = 1;
   }
      
   
   public static String   outputBT(BinTree<Integer> t){
	   
	 
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
   
   public static  String outputBT(BTNode<Integer> node){
	 String output="";
	   
	 
	 //if node is a leaf , simply add and number to the
	 //string
	 if (node.isLeaf()){
		  return  node.element().toString();
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
   
   
   public static void  depth(BinTree<Integer> t){
	   
	   
	   depth(t.root, 0);
	   
	   
   }
   
   /**
    * change the entries  values of nodes 
    * to their depth 
    * calculates depth recursively
    * 
    * @param node
    * @param depth
    */
   
   public static  void depth(BTNode<Integer> node, int  depth){
	   node.setElement(depth);  
	   depth = depth+1;
	   if(node.leftChild()!=null){
		   depth(node.leftChild(), depth);
	   }
	   
	   if(node.rightChild()!=null){
		   depth(node.rightChild(), depth);
	   }
	   
   }
   
   
   
   public static void height(BinTree<Integer> t ){
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
   
   public static  Integer height(BTNode<Integer> node){
	  if(node==null){
		  
		  return 0;
	  }
	   //if node is leaf return 0 for height. the height of
	  //leaf is always 0
	   if(node.isLeaf()){
		   node.setElement(0);
		  return 0;
	   }
	
	   //go to left child node
	  int leftt = height(node.leftChild());
	  //go to right child node
	  int rigtht =height(node.rightChild());
	 //choose the biggest height of two trees
	  int height = 1+ Math.max(leftt, rigtht) ;
	  node.setElement(height);
	  return height;
	   
   }
   
   /**
    * returns  nodes in pre order
    * first display root, then left subtree then right subtree
    * @param - binary tree
    * @return list of nodes in pre order
    * 
    */
 
   public  List<E> preOrder(BinTree<E> t){
	   
	   List<E> list = new ArrayList<>();
       if(t.root!=null){
    	   preOrder(list,root);
	   }
	 return list;
	   
	   
	   
	   
   }
   private void preOrder(List<E> list,BTNode<E> node) {
	   
	  
	   list.add(node.element());
	   if(node.leftChild()!=null){
		   preOrder(list,node.leftChild());
	   }
	   if(node.rightChild()!=null){
		   preOrder(list,node.rightChild());
		   
	   }
	   
	   
   }
   
   /**
    * 
    * returns list of nodes in post order
    * first display left  subtree, then right subtree 
    * and then root 
    * @param t binary tree
    * @return
    */
   
public  List<E> postOrder(BinTree<E> t){
	   
	   List<E> list = new ArrayList<>();
       if(t.root!=null){
    	   postOrder(list,root);
	   }
	 return list;
	   
	   
	   
	   
   }
 private void postOrder(List<E> list,BTNode<E> node) {
	   
	  
	  
	   if(node.leftChild()!=null){
		   postOrder(list,node.leftChild());
		   
	   }
	   
	   if(node.rightChild()!=null){
		   postOrder(list,node.rightChild());
		   
	   }
	   
	   list.add(node.element());
   }
   
   
    /** accessor methods: */
  
   public int size() { return size; }

   public boolean isTrivial() { return (size==1); }
       /** a trivial Tree consists of the root node only
	* empty trees are excluded   */ 

   public boolean isInternal(BTNode<E> v) {return v.isInternal(); }

   public boolean isLeaf(BTNode<E> v) {return  v.isLeaf(); }

   public boolean isRoot(BTNode<E> v) { return (v==root()); }

   public BTNode<E> root() { return root; }

   public BTNode<E> leftChild(BTNode<E> v) { return v.leftChild(); }

   public BTNode<E> rightChild(BTNode<E> v) { return v.rightChild(); }

   public BTNode<E> sibling(BTNode<E> v) {  
      BTNode<E> p  = parent(v);
      BTNode<E> lc = leftChild(p);
      if (v == lc)
         return rightChild(p);
      else
         return lc;
   }

   public BTNode<E> parent(BTNode<E> v) { return v.parent(); }
   
    /** update methods: */
 
    /** turn a leaf v to an inner node by appending two new leaves 
     *  if v is not a leaf, the method won't do anything
     *  alternatively one could throw an exception */
   public void expandExternalNode(BTNode<E> v){
      if( isLeaf(v)){
         v.setLeft(new BTNode<E>(null,v,null,null)); 
         v.setRight(new BTNode<E>(null,v,null,null)); 
         size += 2;
      }
   } 
 
    /** delete a leaf v and replace v's parent node by v's sibling
     *  if v is the root or if v is not a leaf, the method won't do anything 
     *  alternatively one could throw an exception */
   public void removeAboveExternalNode(BTNode<E> v){
      if( isLeaf(v) && !isRoot(v)){
         BTNode<E> p = parent(v);
         BTNode<E> s = sibling(v);
         if( isRoot(p)){
            s.setParent(null);
            root = s;
         }  
         else{
             BTNode<E> g = parent(p);
             if( p == leftChild(g))
                g.setLeft(s);
             else
                g.setRight(s);
             s.setParent(g);
         }
         size -= 2;
      }
   } 
    /** Just a short test: */
    public static void main(String[] args){
	BinTree<Integer> bt = new BinTree();
        BTNode<Integer> v = new BTNode();
        (bt.root()).setLeft(v);
        bt.expandExternalNode(v);
	bt.expandExternalNode(v.leftChild());
        System.out.println(" Size = " + bt.size());
        }
}


