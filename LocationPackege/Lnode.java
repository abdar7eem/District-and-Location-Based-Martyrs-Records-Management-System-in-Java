package LocationPackege;


import MartyrPackege.Martyr;
import MartyrPackege.Mls;
import MartyrPackege.Mnode;

public class Lnode {
	public Lnode next;
	public Object data;
	public Mnode Mnext;
	public int CountMartyr;
	

	
	public Lnode(Object data) {
		super();
		this.data = data;
	}
	

	public void sortByAge() {
		Mnode current = Mnext;
		Mnode ptr = null;
		Martyr temp;

		if (current == null) {
			return;
		} else {
			while (current != null) {
				ptr = current.next;
				while (ptr!= null) {
					if (((Martyr)current.data).getAge() > ((Martyr) ptr.data).getAge()) {
						temp = (Martyr) current.data;
						current.data = ptr.data;
						ptr.data = temp;
					}
					ptr= ptr.next;
				}
				current = current.next;
			}
		}
	}

	

	
	
	
}
