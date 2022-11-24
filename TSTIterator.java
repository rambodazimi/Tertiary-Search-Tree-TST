import java.util.Iterator;
import java.util.LinkedList;

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
	
	LinkedList<T> list;
	int position;
	
	public TSTIterator() {	// constructor should create an ordered list of all the elements in the tree	
		list = new LinkedList<T>();
		
		for(int i = 0 ; i < TST.myList.size() ; i++) {
			list.addFirst((T) TST.myList.get(i));
		}
		position = 0;
		sort(list);
		TST.myList.clear();
	}
	
	
	private void sort(LinkedList<T> list) {
		LinkedList<T> list2 = new LinkedList<T>();
		int sizeOfList = list.size();
		for(int i = 0 ; i < sizeOfList ; i++) {
			T min = (T) min(list);
			list2.addLast((T) min);
			list.remove(min);
		}	
		for(int i = 0 ; i < list2.size() ; i++) {
			list.addLast(list2.get(i));
		}
	}
	
	private T min(LinkedList<T> list) {
		T minValue = list.get(0);
		for(int i = 0 ; i < list.size() ; i++) {
			int result = minValue.compareTo(list.get(i));
			if (result > 0) {
				minValue = list.get(i);
			}
		}
		return minValue;
	}
	
    /**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next}
     * would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
    	return (position < list.size());
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     *
     * @throws NoSuchElementException
     *         if the iteration has no more elements
     */
    @Override
    public T next() {
    	if(!hasNext()) {
    		throw new java.util.NoSuchElementException();
    	}
    	T element = list.get(position++);
    	return element;

    }
}
