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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
