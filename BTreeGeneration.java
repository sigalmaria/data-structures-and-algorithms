
public class BTreeGeneration {
/**
 * 
 * 
 * Maria Sigal, Marina Khitynova

 * 
 * 
 * 
 */
	
	
	public static void main(String[] args) {
		
		//8(5(9, 3), 3(6, 2(5, 1)))
		
	
		
		BTNode<Integer> rn = new BTNode<Integer>(1, null, null, null);
		BTNode<Integer> ln = new BTNode<Integer>(5, null, null, null);
		BTNode<Integer> pn = new BTNode<Integer>(2, null, ln, rn);
		rn.setParent(pn);
		ln.setParent(pn);
		
		
		ln = new BTNode<Integer>(6, null, null, null);
	
		BTNode<Integer> lr = new BTNode<Integer>(3,null,ln,pn);
		ln.setParent(lr);
		pn.setParent(lr);
		
		
		ln = new BTNode<Integer>(9,null,null,null );
		rn = new BTNode<Integer>(3, null, null,null);
		pn =new BTNode<Integer>(5,null,ln,rn);
		ln.setParent(pn);
		rn.setParent(pn);
		BinTree<Integer> bTree =new BinTree<Integer>();
		bTree.root().setElement(8);
		bTree.root().setLeft(pn);
		bTree.root().setRight(lr);
		
		
		pn.setParent(bTree.root());
		lr.setParent(bTree.root());
		
		
		
		
		System.out.println("our tree notation:  " + BinTree.outputBT(bTree));
		/*BinTree.depth(bTree);
				
		System.out.println("our tree after depth method  :  " + BinTree.outputBT(bTree));
		
		BinTree.height(bTree);
		
		System.out.println("our tree after height method  :  " + BinTree.outputBT(bTree));
		*/
		
		System.out.println("preorder: " +bTree.preOrder(bTree));
		System.out.println("postorder: " +bTree.postOrder(bTree));
	}

}
