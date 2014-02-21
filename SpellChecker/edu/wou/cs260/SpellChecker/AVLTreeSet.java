package edu.wou.cs260.SpellChecker;


/**
 * @author sam
 *
 */
public class AVLTreeSet<E extends Comparable<E>> extends BSTreeSet<E>{
	// fields
	
	/*
	 * a mechanical method 
	 * a method to find and set the balance value of the nodes
	 */
	protected int balanceValue(Node temp){
		if(temp == null){
			return 0;
		}
		else{
			return (getHeight(temp.rChild) - getHeight(temp.lChild));
		}
	}
	
	/*
	 * a mechanical method 
	 * a method to do a simple rotate right
	 */
	protected Node singleRight(Node current){
		Node temp = current.lChild;
		current.lChild = temp.rChild;
		temp.rChild = current;
		current.height = calcHeight(current);
		temp.height = calcHeight(temp);
		return temp;
	}
	
	/*
	 * a mechanical method 
	 * a method to do a double rotate right
	 */
	protected Node doubleRight(Node current){
		current.lChild = singleLeft(current.lChild);
		return singleRight(current);
	}
	
	/*
	 * a mechanical method 
	 * a method to do a simple rotate left
	 */
	protected Node singleLeft(Node current){
		Node temp = current.rChild;
		current.rChild = temp.lChild;
		temp.lChild = current;
		current.height = calcHeight(current);
		temp.height = calcHeight(temp);
		return temp;
	}
	
	/*
	 * a mechanical method 
	 * a method to do a double rotate left
	 */
	protected Node doubleLeft(Node current){
		current.rChild = singleRight(current.rChild);
		return singleLeft(current);
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
		// does not seem to be calling the next lines method or not working
		subTree.height = balanceNode(subTree);
		return subTree;
	}

	/*
	 * policy method
	 * my attempt to make a method to chose how to balance the tree
	 */
	private int balanceNode(Node subTree) {
		// check to see if the tree/node is balanced
		if(balanceValue(subTree) == -1 || balanceValue(subTree) == 0 || balanceValue(subTree) == 1){
			// re-calculate the height of the node
			return calcHeight(subTree);
		}
		// check to see if the tree is heavy on the left
		else if(balanceValue(subTree) < -1){
			// check the balance of the lChild to the heavy node
			if(balanceValue(subTree.lChild) < 1){
				// run a single right rotate
				singleRight(subTree);
				// re-calculate the height of the node
				return calcHeight(subTree);
			}
			// check the balance of the rChild to the heavy node
			else if(balanceValue(subTree.rChild) > 0){
				// run a double right rotate
				doubleRight(subTree);
				// re-calculate the height of the node
				return calcHeight(subTree);
			}
			// does nothing but allows for the previous test
			else{return 0;}
		}
		// check to see if the tree is heavy on the right
		else if(balanceValue(subTree) > 1){
			// check the balance of the rChild to the heavy node
			if(balanceValue(subTree.rChild) > 1){
				// run a single left rotate
				singleLeft(subTree);
				// re-calculate the height of the node
				return calcHeight(subTree);
			}
			// check the balance of the lChild to the heavy node
			else if(balanceValue(subTree.lChild) < -1){
				// run a double left rotate
				doubleLeft(subTree);
				// re-calculate the height of the node
				return calcHeight(subTree);
			}
			// does nothing but allows for the previous test
			else{return 0;}
		}
		// does nothing but allows for the previous test
		else{return 0;}
	}
}
