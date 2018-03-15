
public class WordProcessor2 {
	
	public static void main (String[] args) {
		BSTBag mainBag = new BSTBag();

		CountedElement ant = new CountedElement("ant");
		CountedElement grape = new CountedElement("grapes");
		CountedElement bird = new CountedElement("bird");
		CountedElement bird2 = new CountedElement("bird");
		CountedElement mac = new CountedElement("mac");
		CountedElement demarco = new CountedElement("demarco");
		CountedElement egg = new CountedElement("egg");
		
		
		mainBag.add(bird);
		mainBag.add(grape);
		mainBag.add(ant);
		mainBag.add(demarco);
		mainBag.add(mac);
		mainBag.add(egg);
		mainBag.remove(grape);
		mainBag.remove(ant);
		
		System.out.println("Size of Bag is "+mainBag.size());
		System.out.println("root is currently "+mainBag.root.element.toString());
		System.out.println("root has right node "+mainBag.root.right.element.toString());
		System.out.println("which has left node "+mainBag.root.right.left.element.toString());
		System.out.println("which has right node "+mainBag.root.right.left.right.element.toString());
	}

}
