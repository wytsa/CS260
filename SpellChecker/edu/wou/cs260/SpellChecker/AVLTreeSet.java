package edu.wou.cs260.SpellChecker;


/**
 * @author sam
 *
 */
public class AVLTreeSet<E extends Comparable<E>> extends BSTreeSet<E>{
	// fields
	
	/*
	 * a simple method that returns the height of the node passed in
	 * @see edu.wou.cs260.SpellChecker.BSTreeSet#getHeight(edu.wou.cs260.SpellChecker.BSTreeSet.Node)
	 */
	protected int getHeight(Node subTree){
		//if(subTree == null){
		//	return -1;
		//}
		if(subTree.lChild == null && subTree.rChild == null){
			return 0;
		}
		else if(subTree.lChild == null){
			return (Math.max(-1, subTree.rChild.height) + 1);
		}
		else if(subTree.rChild == null){
			return (Math.max(subTree.lChild.height, -1) + 1);
		}
		else{
			return (Math.max(subTree.lChild.height, subTree.rChild.height) + 1);
		}
	}
	
	/*
	 * a mechanical method
	 * this method fixes the height of the node passed in
	 */
	private void fixHeight(Node subTree){
		if(subTree.lChild == null && subTree.rChild == null){
			subTree.height = 0;
		}
		else if(subTree.lChild == null && subTree.rChild != null){
			subTree.height = subTree.rChild.height + 1;
		}
		else if(subTree.lChild != null && subTree.rChild == null){
			subTree.height = subTree.lChild.height + 1;
		}
	}
	
	/*
	 * a mechanical method 
	 * a method to find and get the balance value of the node passed in
	 */
	private int getBalanceVal(Node subTree){
		if(subTree.lChild == null && subTree.rChild == null){
			return 0;
		}
		else if(subTree.lChild == null){
			return (subTree.rChild.height - 0);
		}
		else if(subTree.rChild == null){
			return (0 - subTree.lChild.height);
		}
		else{
			return (getHeight(subTree.rChild) - getHeight(subTree.lChild));
		}
	}
	
	/*
	 * a mechanical method 
	 * a method to do a simple rotate right
	 */
	private Node singleRotateRight(Node current){//singleRotateRight
		Node temp = current.lChild;
		current.lChild = temp.rChild;
		temp.rChild = current;
		fixHeight(current);
		fixHeight(temp);
		return temp;
	}
	
	/*
	 * a mechanical method 
	 * a method to do a double rotate right
	 */
	private Node doubleRotateRight(Node current){
		current.lChild = singleRotateLeft(current.lChild);
		return singleRotateRight(current);
	}
	
	/*
	 * a mechanical method 
	 * a method to do a simple rotate left
	 */
	private Node singleRotateLeft(Node current){
		Node temp = current.rChild;
		current.rChild = temp.lChild;
		temp.lChild = current;
		fixHeight(current);
		fixHeight(temp);
		return temp;
	}
	
	/*
	 * a mechanical method 
	 * a method to do a double rotate left
	 */
	private Node doubleRotateLeft(Node current){
		current.rChild = singleRotateRight(current.rChild);
		return singleRotateLeft(current);
	}
	
	/*
	 * an over-ride for the private add method in DLList/BSTree
	 * @see edu.wou.cs260.SpellChecker.BSTreeSet#addHelper(edu.wou.cs260.SpellChecker.BSTreeSet.Node, java.lang.Comparable)
	 */
	protected Node addHelper(Node subTree, E arg0){
		if(subTree == null){
			size++;
			return new Node(arg0);
		}
		else if(subTree.item.compareTo(arg0) > 0){// go left
			subTree.lChild = addHelper(subTree.lChild, arg0);
		}
		else{// go right
			subTree.rChild = addHelper(subTree.rChild, arg0);
		}
		//fixHeight(subTree);
		// does not seem to be calling the next lines method or not working
		return balance(subTree);
	}

	/*
	 * policy method
	 * my attempt to make a method to chose how to balance the tree
	 */
	protected Node balance(Node subTree) {
		// check to see if the tree is heavy on the left
		if(getBalanceVal(subTree) < -1){
			return lBalance(subTree);
		}
		// check to see if the tree is heavy on the right
		else if(getBalanceVal(subTree) > 1){
			return rBalance(subTree);
		}
		else{// nothing to do but return the balanced tree =^-^=
			return subTree;
		}
		
	}
	
	protected Node lBalance(Node subTree){
		// check the balance of the lChild to the heavy node
		//if(getBalanceVal(subTree.lChild) < 1){
			return singleRotateRight(subTree);// run a single right rotate
		//}
		// check the balance of the rChild to the heavy node
		//if(getBalanceVal(subTree.rChild) > 0){
		//	return doubleRotateRight(subTree);// run a double right rotate
		//}
		//else{return subTree;}// does nothing but allows for the previous test
	}
	
	protected Node rBalance(Node subTree){
		// check the balance of the lChild to the heavy node
		if(getBalanceVal(subTree.lChild) < 1){			
			return singleRotateLeft(subTree);// run a single right rotate
		}
		// check the balance of the rChild to the heavy node
		else if(getBalanceVal(subTree.rChild) > 0){
			return doubleRotateLeft(subTree);// run a double right rotate
		}
		else{return subTree;}// does nothing but allows for the previous test
	}
}
