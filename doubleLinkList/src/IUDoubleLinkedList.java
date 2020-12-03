import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Double-linked node implementation of IndexUnsortedList.
 * An Iterator with working remove() method is implemented
 * 
 * @author emily mead
 *
 * @param <T> type to store
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
	
	private DLLNode<T> head;
	private DLLNode<T> tail;
	private int size;
	private int modCount;
	
	/**
	 * creates a new empty list
	 */
	public IUDoubleLinkedList()
	{
		head = null;
		tail = null;
		size = 0;
		modCount = 0;
	}
	@Override
	public void addToFront(T element) {
		
		DLLNode<T> newNode = new DLLNode<T>(element);
		newNode.setNext(head);
		if(head != null)
		{
			head.setPrevious(newNode);
		}
		head = newNode;
		if(isEmpty())
		{
			tail = newNode;
			newNode.setNext(null);
		}
		
		size++;
		modCount++;
		
	}

	@Override
	public void addToRear(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		newNode.setPrevious(tail);
		if(!isEmpty())
		{
			tail.setNext(newNode);
		}
		tail = newNode;
		if(isEmpty())
		{
			head = newNode;
		}
		
		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
		
	}

	@Override
	public void addAfter(T element, T target) {
		
		DLLNode<T> newNode = new DLLNode<T>(element);
		DLLNode<T> current = head;
		int index = indexOf(target);
		
		if(index == -1)
		{
			throw new NoSuchElementException();
		}
		
		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		
		DLLNode<T> next = current.getNext();
		if(current != tail)
		{
			next.setPrevious(newNode);
		}
		current.setNext(newNode);
		newNode.setNext(next);
		newNode.setPrevious(current);
		
		if(current == tail)
		{
			tail = newNode;
		}

		
		size++;
		modCount++;
		
	}

	@Override
	public void add(int index, T element) {
		if(index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		DLLNode<T> newNode = new DLLNode<T>(element);
		DLLNode<T> current = head;
		
		for(int i = 0; i < index - 1; i++)
		{
			current = current.getNext();
		}
		
		if(!isEmpty())
		{
			addAfter(element, current.getElement());
		}
		else
		{
			head = newNode;
			tail = newNode;
		}
	
	}

	@Override
	public T removeFirst() {
		
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		DLLNode<T> placeHolder = head.getNext();
		T element = head.getElement();
		head.setNext(null);
		head = placeHolder;
		placeHolder.setPrevious(null);
	
		size--;
		modCount++;
		
		return element;
	}

	@Override
	public T removeLast() {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		T element = tail.getElement();
		DLLNode<T> placeHolder = tail.getPrevious();
		tail.setPrevious(null);
		tail = placeHolder;
		tail.setNext(null);
		
		size--;
		modCount++;
		return element;
	}

	@Override
	public T remove(T element) {
		
		DLLNode<T> current = head;
		int index = indexOf(element);
		
		if(index == -1)
		{
			throw new NoSuchElementException();
		}
		
		for(int i = 0; i < index; i++)
		{
	
			current = current.getNext();
		}
		DLLNode<T> next = current.getNext();
		if(current != head)
		{
			DLLNode<T> previous = current.getPrevious();
			current.setNext(null);
			current.setPrevious(null);
			if(current == tail)
			{
				tail = previous;
			}
			else
			{
				next.setPrevious(previous);
				previous.setNext(next);
			}
		}
		else
		{
			current.setNext(null);
			current.setPrevious(null);
			head = next;
			head.setNext(null);
		}
		
		
		
		size--;
		modCount++;
		return current.getElement();
	}

	@Override
	public T remove(int index) {
		
		DLLNode<T> current = head;
		
		
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		
		DLLNode<T> next = current.getNext();
		DLLNode<T> previous = current.getPrevious();
		
		if(current != tail)
		{
			next.setPrevious(previous);
		}
		if(current != head)
		{
			previous.setNext(next);
		}
		current.setNext(null);
		current.setPrevious(null);
		
		if(current == head)
		{
			head = next;
		}
		else if(current == tail)
		{
			tail = previous;
		}
		
		size--;
		modCount++;
		return current.getElement();
	}

	@Override
	public void set(int index, T element) {
		DLLNode<T> current = head; 
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		
		current.setElement(element);
		
	}

	@Override
	public T get(int index) {
		DLLNode<T> current = head; 
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
	
		return current.getElement();
	}

	@Override
	public int indexOf(T element) {
		DLLNode<T> current = head;
		
		if(isEmpty())
		{
			return -1;
		}
		
		int count = 0;
		
		while(current != null)
		{
			if(current.getElement().equals(element))
			{
				return count;
			}
			current = current.getNext();
			count++;
		}
		
		return -1;
	}

	@Override
	public T first() {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		return head.getElement();
	}

	@Override
	public T last() {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		
		if(isEmpty())
		{
			return false;
		}
		
		DLLNode<T> current = head;
		while(current != null)
		{
			if(current.getElement().equals(target))
			{
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}
	
	/**
	 * List iterator for IUDoubleLinkList
	 * @author emilymead
	 */
	
	private class DLLIterator implements ListIterator<T>
	{
		private DLLNode<T> next;
		private int nextIndex;
		private int counter;
		private DLLNode<T> lastR;
		
		/**
		 * Creates an default iterator 
		 */
		public DLLIterator()
		{
			next = head;
			nextIndex = 0;
			counter = modCount;
		}
		
		/**
		 * Creates an iterator that starts at a given starting index
		 * @param startingIndex Index of the element that the 
		 * iterator will start at
		 */
		public DLLIterator(int startingIndex)
		{
			if(startingIndex < 0 || startingIndex > size)
			{
				throw new IndexOutOfBoundsException();
			}
			
			next = head;
			counter = modCount;
			lastR = null;
			for(nextIndex = 0; nextIndex < startingIndex; nextIndex++)
			{
				next = next.getNext();
			}
			
		}

		@Override
		public void add(T arg0) {
			
			//check for changes
			if(counter != modCount)
			{
				throw new ConcurrentModificationException();
			}
			DLLNode<T> newNode = new DLLNode<T>(arg0);
			newNode.setNext(next);
			//check what next is to catch special cases
			
			//next in the middle
			if(next != null)
			{
				newNode.setPrevious(next.getPrevious());
				next.setPrevious(newNode);
			}
			else //next is after tail
			{
				newNode.setPrevious(tail);
				tail = newNode;
			}
			
			//checking if next is head
			if(next != head)
			{
				newNode.getPrevious().setNext(newNode);
			}
			else //next is head
			{
				head = newNode;
			}
			
			//keep track of changes
			size++;
			counter++;
			modCount++;
			nextIndex++;
			lastR = null;
		}

		@Override
		public boolean hasNext() {
			//check changes
			if(counter != modCount)
			{
				throw new ConcurrentModificationException();
			}
			return next != null;
		}

		@Override
		public boolean hasPrevious() {
			if(counter != modCount)
			{
				throw new ConcurrentModificationException();
			}
			return next != head;
		}

		@Override
		public T next() {
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			T returnMe = next.getElement();
			lastR = next;
			next = next.getNext();
			nextIndex++;
			//System.out.println("nextg: " + returnMe);
			return returnMe;
		}

		@Override
		public int nextIndex() {
			if(counter != modCount)
			{
				throw new ConcurrentModificationException();
			}
			//System.out.println(nextIndex);
			return nextIndex;
		}

		@Override
		public T previous() {
			if(!hasPrevious())
			{
				throw new NoSuchElementException();
			}
			if(next != null)
			{
				next = next.getPrevious();
			}
			else
			{
				next = tail;
			}
			nextIndex--;
			lastR = next;
			//System.out.println("Previous: " + next);
			return next.getElement();
			
		}

		@Override
		public int previousIndex() {
			if(counter != modCount)
			{
				throw new ConcurrentModificationException();
			}
			System.out.println(nextIndex);
			return (nextIndex - 1);
		}

		@Override
		public void remove() {
			//check changes
			if(counter != modCount)
			{
				throw new ConcurrentModificationException();
			}
			//check for the last returned element
			if(lastR == null)
			{
				throw new IllegalStateException();
			}
			if(size == 1)
			{
				head = null;
				tail = null;
			}
			else if(lastR == head)
			{
				lastR.getNext().setPrevious(null);
				head = lastR.getNext();
				lastR.setNext(null);
			}
			else if(lastR == tail)
			{
				lastR.getPrevious().setNext(null);
				tail = lastR.getPrevious();
				lastR.setPrevious(null);
			}
			else
			{
				//middle case
				//setting previous to next
				lastR.getPrevious().setNext(lastR.getNext());
				//setting next to previous
				lastR.getNext().setPrevious(lastR.getPrevious());
				//lastR.setNext(null);
				//lastR.setPrevious(null);
			}
			
			//updating counters
			size--;
			modCount++;
			counter++;
			lastR = null;
		}

		@Override
		public void set(T arg0) {
			if(counter != modCount)
			{
				throw new ConcurrentModificationException();
			}
			if(lastR == null)
			{
				throw new IllegalStateException();
			}
			lastR.setElement(arg0);
			
			modCount++;
			counter++;
			lastR = null;
		}
		
	}

}
