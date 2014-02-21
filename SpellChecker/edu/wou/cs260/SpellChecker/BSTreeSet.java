/**
 * 
 */
package edu.wou.cs260.SpellChecker;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

//import java.lang.Math.*;
//import java.util.NoSuchElementException;
//import edu.wou.cs260.SpellChecker.DLList.DLLNode;
//import edu.wou.cs260.SpellChecker.DLList.DLLIterator;

/**
 * @author sam wytoski
 * 
 */
public class BSTreeSet<E extends Comparable<E>> implements Set<E>, CompareCount {

	public BSTreeSet(){	
		root = null;
		size = 0;
		compareCount = 0;
	}
	
	protected int size;
	protected Node root;
	protected int compareCount;

	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		if(arg0 == null){
			throw new NullPointerException();
		}
		else if(contains(arg0)){
			return false;
		}
		else{
			root = addHelper(root, arg0);
			return true;
		}
	}
	
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
		subTree.height = calcHeight(subTree) + 1;
		return subTree;
	}
	
	protected int calcHeight(Node subTree){
		if(subTree.lChild != null && subTree.rChild != null){
			return Math.max(getHeight(subTree.lChild), getHeight(subTree.rChild));
		}
		else if(subTree.lChild != null && subTree.rChild == null){
			return Math.max(getHeight(subTree.lChild), -1);
		}
		else if(subTree.lChild == null && subTree.rChild != null){
			return Math.max(-1, getHeight(subTree.rChild));
		}
		else{
			return -1;
		}
	}
	
	protected int getHeight(Node subTree){
		if(subTree == null){
			return -1;
		}
		else{
			return (subTree.height);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		return false;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(Object arg0) {
		return has(root, arg0);
	}
	
	@SuppressWarnings("unchecked")
	protected Boolean has(Node subTree, Object item){
		if(subTree == null){
			return false;
		}
		else if(subTree.item.equals(item)){
			compareCount++;
			return true;
		}
		else if(subTree.item.compareTo((E) item) > 0){// go left 
			return has(subTree.lChild, item);
		}
		else{ //if(subTree.item.compareTo((E) item) > 0){// go right
			return has(subTree.rChild, item);
		}
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(root == null){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new TreeIterator();
	}
	
	//traversal
	protected void visit(Node current){
		System.out.println(current.item);
	}
	
	protected void preOrder(Node current){
		if(current == null){
			return;
		}
		else{
			visit(current);
			preOrder(current.lChild);
			preOrder(current.rChild);
		}
	}
	
	protected void inOrder(Node current){
		if(current == null){
			return;
		}
		else{
			inOrder(current.lChild);
			visit(current);
			inOrder(current.rChild);
		}
	}
	
	protected void postOrder(Node current){
		if(current == null){
			return;
		}
		else{
			postOrder(current.lChild);
			postOrder(current.rChild);
			visit(current);
		}
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}
	
	class Node {
		// fields
		E item;
		int height;
		Node lChild;
		Node rChild;

		// methods
		// 3 constructors
		Node() {
			this(null, null, null);
			height = 0;
		}

		Node(E item) {
			this(null, item, null);
			height = 0;
		}

		Node(Node lChild, E item, Node rChild) {
			this.lChild = lChild;
			this.item = item;
			this.rChild = rChild;
			height = 0;
		}
	}
	
	class TreeIterator implements Iterator<E>{
		// Iterator fields
		DLList<Node> que;
		
		public TreeIterator(){
			que = new DLList<Node>();
			que.add(root);
		}
		
		public void visit(Node arg0){
			System.out.println(arg0.item + " ");
		}
		
		@Override
		public boolean hasNext() {
			return !que.isEmpty();
		}

		@Override
		public E next() {
			Node result = que.remove();
			if(result.lChild != null){
				que.add(result.lChild);
			}
			if(result.rChild != null){
				que.add(result.rChild);
			}			
			return result.item;
		}

		@Override
		public void remove() {
		}
	}

	@Override
	public int getLastCompareCount() {
		return compareCount;
	}

}
