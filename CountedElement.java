public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	public CountedElement(E e, int count){
		//constructor - to complete
		this.element = e;
		this.count = count;
	}

	public CountedElement(E e){
		//constructor - to complete
		this.element = e;
	}

	//add getters and setters

	//accessor
	public E getElement(){
		return element;
	}
	public int getCount(){
		return count;
	}

	//mutator
	public void setElement(E e){
		this.element = e;
	}
	public void setCount(int count){
		this.count = count;
	}

	//add toString() method
	public String toString(){
		String output= "(";
		output+=this.element+","+this.count+")";
		return output;
	}

	public int compareTo(CountedElement<E> sC1) {
		//to complete
		return getElement().compareTo(sC1.getElement());
	}

}
