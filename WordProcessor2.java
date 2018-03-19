import java.util.Iterator;

public class WordProcessor2 {

	public static void main (String[] args) {
		BSTBag mainBag = new BSTBag();

		String bird = "bird";
		String grape = "grape";
		String ant = "ant";
		String mac = "mac";
		String egg = "egg";
		String cheese = "cheese";
		String frank = "frank";
		String dorito = "dorito";

		mainBag.add(egg);
		mainBag.add(grape);
		mainBag.add(bird);
		mainBag.remove(bird);
		mainBag.add(ant);
		mainBag.add(cheese);
		mainBag.add(cheese);
		mainBag.add(mac);
		mainBag.add(frank);
		mainBag.add(frank);
		mainBag.add(grape);
		mainBag.add(dorito);
		
		BSTBag thatBag = new BSTBag();
		thatBag.add(egg);
		thatBag.add(grape);
		thatBag.add(bird);
		thatBag.remove(bird);
		thatBag.add(ant);
		thatBag.add(cheese);
		thatBag.add(cheese);
		thatBag.add(mac);
		thatBag.add(mac);
		thatBag.add(frank);
		thatBag.add(frank);
		thatBag.add(grape);
		thatBag.add(dorito);
		thatBag.remove(egg);
		thatBag.remove(egg);
	

		System.out.println("root, egg: "+mainBag.root.element.toString());
		System.out.println("root left, bird: "+mainBag.root.left.element.toString());
		System.out.println("bird left, ant: "+mainBag.root.left.left.element.toString());
		System.out.println("bird right, cheese: "+mainBag.root.left.right.element.toString());
		System.out.println("cheese right, dorito: "+mainBag.root.left.right.right.element.toString());
		System.out.println("root right, grape: "+mainBag.root.right.element.toString());
		System.out.println("grape left, frank: "+mainBag.root.right.left.element.toString());
		System.out.println("grape left, mac: "+mainBag.root.right.right.element.toString());

		Iterator<String> it = mainBag.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		String compare = "celines butthole";
		if(mainBag.contains(compare)) {
			System.out.println("Bag does contain "+compare);
		}
		else {
			System.out.println("Bag does not contain "+compare);
		}
		
		System.out.println(mainBag.size());
		System.out.println(thatBag.size());
		System.out.println("Equality: "+mainBag.equals(thatBag));
		
		BSTBag emptyBag = new BSTBag();
		emptyBag.add(egg);
		emptyBag.remove(egg);
		System.out.println(emptyBag.iterator().hasNext());
	}

}
