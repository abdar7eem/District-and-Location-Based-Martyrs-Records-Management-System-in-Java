package MartyrPackege;

import LocationPackege.Lls;
import LocationPackege.Lnode;

public class Mls  {

	public Mnode first;
	public Mnode last;
	private static int count=0;

	public void addFirst(Object data) {
		Mnode node=new Mnode(data);
		if(first==null) {
			last =first=node;
			count++;
		}
		else {
			node.next=first;
			first=node;	
			count++;
		}
	}

	public Object getFirst() {
		if(first==null) {
			return null;
		}
		else {
			return first.data;
		}
	}

	public void addLast(Object data) {
		Mnode node=new Mnode(data);
		if(last==null) {
			last=first=node;
		}
		else {
			last.next=node;
			last=node;		

		}
		count++;
	}

	public Object getLast() {
		if(last==null) {
			return null;
		}
		else {
			return last.data;
		}
	}

	public void add(Object data,int index) {

		if(index<=0) {
			addFirst(data);
		}
		else if(index>=count) {
			addLast(data);
		}
		else {
			Mnode node =new Mnode(data);
			Mnode temp=first;
			for(int i=0;i<index;i++) {
				temp=temp.next;
			}
			node.next=temp.next;
			temp.next=node;
			count++;
		}
	}



	public int size() {
		return count;
	}


	public void print() {
		Mnode node=first;
		for(int i=0;i<count;i++) {
			if(node!=null) {
				System.out.print(((Martyr)node.data).getmName()+" ");
				node=node.next;}
		}

	}

	public String p() {
		Mnode node=first;
		String str="";
		for(int i=0;i<count;i++) {
			if(node!=null) {
				str+="\n"+node.data;
				node=node.next;}
		}
		return str;
	}


	public boolean removeFirst() {
		if (first == null) {
			return false;
		} else {
			Mnode temp = first;
			first = first.next;
			temp.next = null;
		}
		count--;
		return true;
	}

	public boolean removeLast() {
		if (count < 0) {
			return false;
		} else if (count == 0) {
			first = last = null;
		} else {
			Mnode temp = first;
			for (int i = 0; i < count - 1; i++) {
				if(temp.next!=null) {
					temp = temp.next;}
			}
			temp.next = null;
			last = temp;
		}
		count--;
		return true;

	}

	public boolean remove(int index) {
		if (count < 0 || index > count) {
			return false;

		} else if (count == 1) {
			return removeFirst();
		} else if (count == index) {
			return removeLast();
		}
		else if(index==0) {
			return removeFirst();
		}
		else {
			Mnode ptr=first;
			for(int i=0;i<index-1;i++) {
				ptr=ptr.next;
			}
			Mnode temp=ptr.next;
			ptr.next=temp.next;
			temp.next=null;
			count--;
		}
		return true;
	}

	public boolean removeByData(Object data) {

		if(count<0) {
			return false;
		}
		else if(((Object)first.data).equals(data)) {
			return removeFirst();
		}
		else if(((Object)last.data).equals(data)) {
			return removeLast();
		}

		else {
			Mnode ptr = first;
			for(int i=0; i<count-1;i++) {
				if(ptr!=null) {
					if(ptr.data.equals(data)){
						return remove(i);
					}
				}else
					ptr=ptr.next;
			}
		}
		return false;
	}

	public void sort(Mls list) {
		Mnode temp=list.first;
		Mnode ptr=null;
		Martyr abc=null;

		if (list.first == null) {
			return;
		}
		else {
			while(temp!=null) {
				ptr=temp.next;
				while(ptr!=null) {
					if(((Martyr)temp.data).getAge()>(((Martyr)ptr.data).getAge())) {
						abc=(Martyr)temp.data;
						temp.data=ptr.data;
						ptr.data=abc;
					}
					ptr=ptr.next;
				}
				temp=temp.next;
			}
		}
	}

}








