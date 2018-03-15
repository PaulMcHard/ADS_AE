import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTBag<E extends CountedElement> implements Bag<E> {

	public 	Node<E> root;
	private int size;
	/////////////// Inner class ///////////////
	public static class Node<E extends CountedElement> {
		protected CountedElement element;
		protected Node<E> left, right;

		protected Node (E elem) {
			element = elem;
			element.setCount(1);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Bag<E> that) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(E element) {
		this.size++; // increment bag size counter.
		//if the bag is empty then we are setting the root node.
		if(this.isEmpty()) {
			root = new Node<E>(element);
			System.out.println("root is now "+root.element);
			return;
		}
		//otherwise, search the tree to find either a matching node to increment or the closest parent node to add to.
		int comparison = 0; //holds integer returned from compareTo
		Node parent = null; //stores most recently visited parent node;
		Node index = root; //current node, start from root
		while(index != null) { //only break the loop on return statement or if we reach the bottom of a branch.
			comparison = element.compareTo(index.element);
			if ( comparison == 0 ) {
				index.element.setCount(index.element.getCount()+1);
				System.out.println("Incremented Node "+index.element.toString()+" to "+index.element.getCount());
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
			System.out.println("Added new left node "+element.toString()+" to parent node "+parent.element.toString());
		}
		else {
			parent.right = new Node<E>(element);
			System.out.println("Added new right node "+element.toString()+" to parent node "+parent.element.toString());
		}
	}

	@Override
	public void remove(E element) {
		//throw an exception if remove called while BSTBag is empty
		if(this.isEmpty()) {
			System.out.println("No elements in bag!");
			throw new NoSuchElementException();
		}
		int comparison = 0;
		Node parent = null;
		Node index = root;
		while(index != null ) {
			comparison = element.compareTo(index.element);
			if	(comparison == 0) {//Node has been found
				this.size--; //decrement size counter
				int count = index.element.getCount();
				if(count > 1) { //If element has multiple occurrences, remove one from bag
					index.element.setCount(--count);
					System.out.println("Decremented Node "+index.element.toString());
					return;
				}
				else { // otherwise we have to remove the element
					Node subtree;
					if( index.left == null ) {
						subtree = index.right;
					}
					else if ( index.right == null) {
						subtree = index.left;
					}
					else {
						subtree = index.right;
						Node tempParent = parent;
						while( subtree.left != null ) { //finds leftmost child of the node's right subtree
							tempParent = subtree;
							subtree = subtree.left;
						}
						if(subtree.right != null) {
							tempParent.left = subtree.right;
						}
					}
					if( index == root ) { //deleting the root
						subtree.left = root.left;
						subtree.right = root.right;
						root = subtree;
						System.out.println("root removed, new root is now "+root.element.toString());
					}
					else if( index == parent.left) {
						if(index.left != null ) {
							subtree.left = index.left;
						}
						if(index.right != null) {
							subtree.right = index.right;
						}
						parent.left = subtree;
						System.out.println("element "+index.element.toString()+" removed, new parent to it's branch is "+parent.element.toString());
					}
					else { //index == parent.right
						if(index.left != null ) {
							subtree.left = index.left;
						}
						if(index.right != null) {
							subtree.right = index.right;
						}
						parent.right = subtree;
						System.out.println("element "+index.element.toString()+" removed, new parent to it's branch is "+parent.element.toString());
					}
					return;
				}
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
		System.out.println("Element could not be found in bag!");
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
				track.push(index);
			}
		}

		@Override
		public boolean hasNext() {
			return (! track.empty() );
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Node<E> place = (Node<E>) track.pop();
			for(Node<E> index = place.right; index!= null; index = index.left) {
				track.push(index);
			}
			return (E) place.element.getElement(); //getElement returns E anyway but need to cast it.
		}

	}

}
