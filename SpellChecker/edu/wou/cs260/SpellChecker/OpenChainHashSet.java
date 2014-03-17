/**
 * 
 */
package edu.wou.cs260.SpellChecker;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wytsa
 * @param <E>
 *
 */
public class OpenChainHashSet<E> implements Set<E>, CompareCount {
	
	private int tableSize = 150001;
	private int currentItems = 0;
	private DLList<E>[] hashTable;

	@SuppressWarnings("unchecked")
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
		boolean val = false;
		if(arg0 == null){
			throw new NullPointerException();
		}
		int index = Math.abs( arg0.hashCode( )) % tableSize;
		if(hashTable[index] == null){
			DLList<E> temp = new DLList<E>();
			val = temp.add(arg0);
			if(val == true){
			currentItems ++;
			hashTable[index] = temp;
			return val;
			}
			else{
				return val;
			}			
		}
		else{
			DLList<E> temp = hashTable[index];
			val = temp.add(arg0);
			if(val == true){
				currentItems ++;
				hashTable[index] = temp;
				return val;
			}
			else{
				return val;
			}
		}
	}
		
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		return false;
	}

	@Override
	public void clear() {
		currentItems = 0;
		for(int i = 0; i < tableSize; i++){
			if(hashTable[i] != null){
				hashTable[i] = null;
			}
		}	
	}

	@Override
	public boolean contains(Object arg0) {
		if(arg0 == null){
			throw new NullPointerException();
		}
		int index = Math.abs( arg0.hashCode( )) % tableSize;
		if(hashTable[index] == null){
			return false;
			}
		else{
			DLList<E> temp = hashTable[index];
			return temp.contains(arg0);
			}
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
		if(arg0 == null){
			throw new NullPointerException();
		}
		int index = Math.abs( arg0.hashCode( )) % tableSize;
		if(hashTable[index] == null){
			return false;
			}
		else{
			DLList<E> temp = hashTable[index];
			if(temp.remove(arg0) == true){
				currentItems --;
				return true;
			}
			else{
				return false;
			}
		}	
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
		return (currentItems);
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

}
