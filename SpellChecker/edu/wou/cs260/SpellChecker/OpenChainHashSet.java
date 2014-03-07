/**
 * 
 */
package edu.wou.cs260.SpellChecker;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.wou.cs260.SpellChecker.DLList.DLLNode;

/**
 * @author wytsa
 * @param <E>
 *
 */
public class OpenChainHashSet<E> implements Set<E>, CompareCount {
	
	private int tableSize = 150001;
	private int currentItems = 0;
	private DLList<E>[] hashTable;
	
	//

	public OpenChainHashSet(int tableSize) {
		hashTable = (DLList<E>[]) new DLList[tableSize];
	}

	/* (non-Javadoc)
	 * @see edu.wou.cs260.SpellChecker.CompareCount#getLastCompareCount()
	 */
	@Override
	public int getLastCompareCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		int index = Math.abs( arg0.hashCode( )) % tableSize;
		OCHSNode temp = new OCHSNode(arg0);
		if(temp.data == null){
			throw new NullPointerException();
		}
		else{
			return addAddress(index, temp);
		}		
	}
	
	private boolean addAddress(int index, OCHSNode temp){
		
		
		return false;		
	}
	
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(currentItems == 0){
			return true;
		}
		else{
			return false;
		}		
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	class OCHSNode {
			    
		OCHSNode prev;    
		E data;    
		OCHSNode next; 
		
		OCHSNode() { 
			this(null, null, null);    
			} 
		
		OCHSNode(E d) {
			this(null, d, null);
			}    
		
		OCHSNode(OCHSNode p, E d, OCHSNode n) {        
			prev = p; 
			data = d; 
			next = n;    
			}
		
		public E getData(){
			return data;
		}
		
		public OCHSNode getNext(){
			return next; 
		}
	}
}
