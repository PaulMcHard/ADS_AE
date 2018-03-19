import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTBag<E extends Comparable<E>> implements Bag<E> {

	public 	Node<E> root;
	private int size;
	/////////////// Inner class ///////////////
	public static class Node<E extends Comparable<E>> {
		protected CountedElement element;
		protected Node<E> left, right;

		protected Node (E elem) {
			element = new CountedElement(elem, 1);
			left = null;
			right = null;
		}
	}
	public BSTBag() {
		root = null;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		if( root == null ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(E element) {
		CountedElement thatElem = new CountedElement(element);
		Iterator containIt = this.iterator();
		while(containIt.hasNext()) {
			CountedElement thisElem = new CountedElement((E) containIt.next());
			if(thisElem.compareTo(thatElem) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Bag<E> that) {
		that = (BSTBag<E>) that;
		if( this.size != that.size()) { //size mismatch means equality is impossible
			return false;
		}
		Iterator thisIt = this.iterator();
		Iterator thatIt = that.iterator();
		//to reach this point, both must be the same size, thus we need only rely on one iterator for the hasNext while loop.
		while(thisIt.hasNext() ) {
			E thisCurr = (E) thisIt.next();
			E thatCurr = (E) thatIt.next();
			if(thisCurr.compareTo(thatCurr) != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void clear() {
		this.root = null;
		this.size = 0;
	}

	@Override
	public void add(E element) {
		this.size++; // increment bag size counter.
		//if the bag is empty then we are setting the root node.
		if(this.isEmpty()) {
			root = new Node<E>(element);
			return;
		}
		//otherwise, search the tree to find either a matching node to increment or the closest parent node to add to.
		int comparison = 0; //holds integer returned from compareTo
		Node parent = null; //stores most recently visited parent node;
		Node index = root; //current node, start from root
		while(index != null) { //only break the loop on return statement or if we reach the bottom of a branch.
			comparison = element.compareTo((E) index.element.getElement());
			if ( comparison == 0 ) {
				index.element.setCount(index.element.getCount()+1);
				return;
			}
			else if( comparison < 0 ) { 
				parent = index;
				index = index.left;
			}
			else { //(comparison > 0) 
				parent = index;
				index = index.right;
			}
		}
		//at the bottom of a branch, parent will point to last node reached in search, index will point to null
		if (comparison < 0) { //comparison still has value of last compareTo done during while loop, use to set new child as L or R respectively.
			parent.left = new Node<E>(element);
			return;
		}
		else {
			parent.right = new Node<E>(element);
			return;
		}
	}

	@Override
	public void remove(E element) {
		//throw an exception if remove called while BSTBag is empty
		if(this.isEmpty()) {
			//System.out.println("No elements in bag!");
			throw new NoSuchElementException();
		}
		int comparison = 0;
		Node parent = null;
		Node index = root;
		while(index != null ) {
			comparison = element.compareTo((E)index.element.getElement());
			if	(comparison == 0) {//Node has been found
				int count = index.element.getCount();
				if(count > 0) { //If element has multiple occurrences, remove one from bag. When we remove the last one it 
					index.element.setCount(--count);
					this.size--; //decrement size counter
				}
				//if count is already at zero then remove does nothing.
				return;
			}
			//if match not found we traverse through tree to find node as in add
			else if( comparison < 0 ) {
				parent = index;
				index = index.left;
			}
			else { //(comparison > 0) 
				parent = index;
				index = index.right;
			}
		}
		//System.out.println("Element could not be found in bag!");
		throw new NoSuchElementException();
	}

	@Override
	public Iterator<E> iterator() {
		return new BSTBag.InOrderIterator();
	}

	////////// I T E R A T O R ////////// 
	private class InOrderIterator implements Iterator{

		private Stack<Node<E>> track;

		private InOrderIterator() {
			track = new LinkedStack<Node<E>>();
			for (Node<E> index = root; index != null; index = index.left) {
				/* still add nodes of zero count to the stack, ignoring them is handled by iterator
				 * need to push them to properly access their children.
				 */
				if(index.element.getCount() == 0) {
					track.push(index);
				}
				else {
					for(int i =0; i < index.element.getCount(); i ++) {
						track.push(index);
					}
				}
			}
		}

		@Override
		public boolean hasNext() {
			return (! track.empty());
		}

		@Override
		public E next(){
			if(!hasNext()) {
				throw new NoSuchElementException();
			}

			Node<E> place = track.pop();
			if(hasNext()) {
				Node<E> peek = (Node<E>) track.peek();
				if(place.element.compareTo(peek.element) == 0 ) {
					return (E) place.element.getElement();
				}
			}
			for(Node<E> index = place.right; index != null; index = index.left) {
				for(int i =0; i < index.element.getCount(); i ++) {
					track.push(index);
				}
			}
			if(place.element.getCount() < 1) {
				return next();
			}
			return (E) place.element.getElement();

		}
	}

}
