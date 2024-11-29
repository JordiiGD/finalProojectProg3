package co.edu.uptc.structures;

import java.util.Comparator;
import java.util.Iterator;

public interface ITree<T> {
	//Adds the specified element to this set if it is not already present.
	public boolean add(T value);
	
	//Returns the comparator used to order the elements in this set, or null if this set uses the natural ordering of its elements.
	public Comparator<T> comparator();
	
	//Returns true if this set contains the specified element.
	public boolean contains(T value);
	
	//Returns true if this set contains no elements.
	public boolean isEmpty();
	
	//Returns an iterator over the elements in this set in ascending order.
	public Iterator<T> iterator();
	
	//Removes the specified element from this set if it is present.
	public boolean remove(T value);
}
