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
			// return a height value of 0
			// children nodes are null
			subTree.height = 0;
		}
		else if(subTree.lChild == null && subTree.rChild != null){
			// return a height value of rChild height +1
			subTree.height = getHeight(subTree.rChild) + 1;
		}
		else if(subTree.lChild != null && subTree.rChild == null){
			// return a height value of lChild height +1
			subTree.height = getHeight(subTree.lChild) + 1;
		}
		else{
			// return a height value of the max value
			// between lChild and rChild
			subTree.height = (Math.max(subTree.lChild.height, subTree.rChild.height) + 1);
		}
		System.out.println("value of node " + subTree.item + " has height " + subTree.height);
	}
	
	/*
	 * a mechanical method 
	 * a method to find and get the balance value of the node passed in
	 */
	private int getBalanceVal(Node subTree){
		if(subTree.lChild == null && subTree.rChild == null){
			// return a balanced value of 0
			return 0;
		}
		else if(subTree.lChild == null && subTree.rChild != null){
			// left is null right is not
			return (getHeight(subTree.rChild) - -1);
		}
		else if(subTree.lChild != null && subTree.rChild == null){
			// left is null right is not
			return (0 - (getHeight(subTree.lChild) + 1));
		}
		else{
			// left is null right is not
			return (getHeight(subTree.rChild) - getHeight(subTree.lChild));
		}
	}
	
	/*
	 * a mechanical method 
	 * a method to do a simple rotate right
	 * currently working
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
	 * a method to do a simple rotate left
	 * currently working
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
	 * a method to do a double rotate right
	 */ //broken
	private Node doubleRotateRight(Node current){
		current.lChild = singleRotateLeft(current.lChild);
		return singleRotateRight(current);
	}
	
	/*
	 * a mechanical method 
	 * a method to do a double rotate left
	 */ //broken
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
			//System.out.println(arg0 + " addhelper go left");
			subTree.lChild = addHelper(subTree.lChild, arg0);
		}
		else if(subTree.item.compareTo(arg0) < 0){// go right
			//System.out.println(arg0 + " addhelper go right");
			subTree.rChild = addHelper(subTree.rChild, arg0);
		}
		fixHeight(subTree);
		//System.out.println("balanceVal is " + getBalanceVal(subTree));
		//System.out.println(subTree.item + ", item has height of " + subTree.height);
		return balance(subTree);
	}

	/*
	 * policy method
	 * my attempt to make a method to chose how to balance the tree
	 */
	protected Node balance(Node subTree) {
		int temp = getBalanceVal(subTree);
		//System.out.println("enter balance method");
		if(temp == 0){
			return subTree;
		}
		else{
			// check to see if the tree is heavy on the left
			if(temp < -1){
				//System.out.println("balVal is " + temp);
				//System.out.println("go left");
				subTree = leftSideBalance(subTree);
			}
			// check to see if the tree is heavy on the right
			if(temp > 1){
				//System.out.println("balVal is " + temp);
				//System.out.println("go right");
				subTree = rightSideBalance(subTree);
			}
		}
		return subTree;		
	}
	
	/*
	 * 
	 */
	protected Node leftSideBalance(Node subTree){
		int tempLeftBalance = getBalanceVal(subTree.lChild);
		//System.out.println("enter leftSideBalance method");
		//System.out.println("balVal is " + tempLeftBalance);
		// check the balance of the lChild to the heavy node
		if(tempLeftBalance < 1){
			//System.out.println("single right");
			return singleRotateRight(subTree);// run a single right rotate
		}
		// check the balance of the rChild to the heavy node
		else if(tempLeftBalance > 0){
			//System.out.println("double right");
			return doubleRotateRight(subTree);// run a double right rotate
		}
		else{return subTree;}// does nothing but allows for the previous test
	}
	
	/*
	 * 
	 */
	protected Node rightSideBalance(Node subTree){
		int tempRightBalance = getBalanceVal(subTree.rChild);
		//System.out.println("enter rightSideBalance method");
		//System.out.println("balVal is " + tempRightBalance);
		// check the balance of the lChild to the heavy node
		if(tempRightBalance > -1){
			//System.out.println("single left");
			return singleRotateLeft(subTree);// run a single right rotate
		}
		// check the balance of the rChild to the heavy node
		else if(tempRightBalance < 0){
			//System.out.println("double left");
			return doubleRotateLeft(subTree);// run a double right rotate
		}
		else{return subTree;}// does nothing but allows for the previous test
	}
}
