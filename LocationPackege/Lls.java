package LocationPackege;
import java.util.Date;

import DistrictPackege.District;
import DistrictPackege.Dnode;
import MartyrPackege.Martyr;
import MartyrPackege.Mls;
import MartyrPackege.Mnode;

public class Lls {

	public Lnode first;
	private Lnode last;
	public int count=0;
	public Mls mls=new Mls();



	public Lls(String name) {
		addFirst(new Location(name));
	}

	public Lls() {
		// TODO Auto-generated constructor stub
	}

	public void addFirst(Object data) {
		Lnode node=new Lnode(data);
		if(first==null) {
			last=first=node;
			last.next=first;
		}
		else {
			node.next=first;
			first=node;	
			last.next=first;
		}
		count++;
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
		Lnode node=new Lnode(data);
		if(last==null) {
			last=first=node;
			last.next=first;
		}
		else {
			last.next=node;
			last=node;	
			last.next=first;

		}
		count++;
	}

	public Lnode getLast() {
		if(last==null) {
			return null;
		}
		else {
			return last;
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
			Lnode node =new Lnode(data);
			Lnode temp=first;
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

	public boolean removeFirst() {
		if (first == null) {
			return false;
		} else {
			Lnode temp = first;
			first = first.next;
			last.next=first;
			temp.Mnext=null;
			temp.next = null;
			temp=null;
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
			Lnode temp = first;
			for (int i = 0; i < count - 1; i++) {
				temp = temp.next;
			}
			temp.next = null;
			last = temp;
			last.next=first;
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

			Lnode ptr=first;
			for(int i=0;i<index-1;i++) {
				ptr=ptr.next;
			}
			Lnode temp=ptr.next;
			ptr.next=temp.next;
			temp.next=null;
			count--;
		}
		return true;
	}

	public boolean removeByData(Lnode node) {
		if (first == null) {
			return false;
		} else if (first.equals(node)) {
			return removeFirst();
		}
		else if (last.equals(node)) {
			return removeLast();

		} else {
			Lnode ptr = first.next;
			System.out.println(count);
			for (int i = 0; i < count; i++) {
				if (((Location)ptr.data).getlName().equals(((Location)node.data).getlName())) {
					return remove(i);
				}
				ptr = ptr.next;
			}
		}
		return false;
	}

	public void print() {
		Lnode node=first;
		for(int i=0;i<count;i++) {
			System.out.print(((Location)node.data).getlName()+" ");
			node=node.next;
		}
	}

	public Lnode searchLocation(String name) {
		Lnode temp=first;
		for(int i=0;i<count;i++) {
			if(((Location)temp.data).getlName().equals(name)) {
				return temp;
			}
			temp=temp.next;
		}
		return null;
	}

	public void addMartyr(Martyr m,String Lname) {
		Lnode temp = searchLocation(Lname);

		if(temp!=null) {
			Mnode ptr = new Mnode(m);

			if (temp.Mnext == null) {
				temp.Mnext = ptr;
			} else {
				ptr.next = temp.Mnext;
				temp.Mnext = ptr;
			}
			temp.CountMartyr++;

		}
		else {
			return;
		}
	}


	public void martyrToString(String name) {
		if(searchLocation(name)!=null) {
			Mnode temp = searchLocation(name).Mnext;
			for (int i = 0; i < searchLocation(name).CountMartyr; i++) {
				System.out.print("[Martyr Name: " + ((Martyr) temp.data).getmName()+ " " + "Age: "
						+ ((Martyr) temp.data).getAge() + "] \n");
				temp = temp.next;
			}
		}
		else {
			System.out.println("Location not found");
		}
	}

	public String[] getMar(String name) {
		String []arr=new String[searchLocation(name).CountMartyr];
		if(searchLocation(name)!=null) {
			Mnode temp = searchLocation(name).Mnext;
			for (int i = 0; i < searchLocation(name).CountMartyr; i++) {
				arr[i]=((Martyr) temp.data).getmName();
				temp = temp.next;
			}
		}
		else {
			System.out.println("Location not found");
		}
		return arr;
	}

	public int TotalMartyr(String Lname) {
		Lnode temp=searchLocation(Lname);
		return temp.CountMartyr;
	}

	public int TotalMale(String Lname) {
		Lnode temp=searchLocation(Lname);
		int sum=0;
		Mnode ptr=temp.Mnext;
		for(int i=0;i<temp.CountMartyr;i++) {
			if(((Martyr)ptr.data).getGender().equals("M")){
				sum++;
			}
			ptr=ptr.next;
		}
		return sum;
	}

	public boolean updateLoc(String oldName,String newName) {
		Lnode node;
		if(searchLocation(oldName)!=null) {
			node=searchLocation(oldName);
			((Location)node.data).setlName(newName);
			return true;
		}
		else {
			return false;
		}
	}


	public int TotalFemale(String Lname) {
		Lnode temp=searchLocation(Lname);
		int sum=0;
		Mnode ptr=temp.Mnext;
		for(int i=0;i<temp.CountMartyr;i++) {
			if(((Martyr)ptr.data).getGender().equals("F")){
				sum++;
			}
			ptr=ptr.next;
		}
		return sum;
	}

	public int ageAVG(String Lname) {
		Lnode temp=searchLocation(Lname);
		int sum=0;
		Mnode ptr=temp.Mnext;
		for(int i=0;i<temp.CountMartyr;i++) {

			sum+=(((Martyr)ptr.data).getAge());
			ptr=ptr.next;
		}
		if(temp.CountMartyr!=0) {
			return sum/temp.CountMartyr;
		}
		else {
			return 0;
		}
	}

	public Martyr youngest (String name) {
		Lnode temp=searchLocation(name);
		if(temp!=null) {
			Mnode ptr=temp.Mnext;
			Mnode abc=ptr;
			int min;
			if(ptr!=null) {
				min=((Martyr)ptr.data).getAge();
			}
			else {
				min=0;
			}
			while(ptr!=null) {
				if(((Martyr)ptr.data).getAge()<min) {
					min=((Martyr)ptr.data).getAge();
					abc=ptr;
				}
				ptr=ptr.next;
			}
			if(abc!=null) {
				return ((Martyr)abc.data);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	public Martyr oldest (String name) {
		Lnode temp=searchLocation(name);
		if(temp!=null) {
			Mnode ptr=temp.Mnext;
			Mnode abc=ptr;
			int max=0;

			while(ptr!=null) {
				if(((Martyr)ptr.data).getAge()>max) {
					max=((Martyr)ptr.data).getAge();
					abc=ptr;
				}
				ptr=ptr.next;
			}
			if(abc!=null) {
				return ((Martyr)abc.data);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	public Martyr searchMartyr(String Lname,String Mname) {
		Lnode temp=searchLocation(Lname);
		if(temp!=null) {
			Mnode ptr=temp.Mnext;
			for(int i=0;i<temp.CountMartyr;i++) {
				if(((Martyr)ptr.data).getmName().contains(Mname)) {
					return ((Martyr)ptr.data);
				}
				ptr=ptr.next;
			}
			System.out.println("Martyr doesnt exist");
			return null;
		}
		else {
			System.out.println("location doesnt exist");
			return null;
		}
	}

	public void updateMartyr(String Lname,String oldMname,String newMname,Date date,int age,String gender) {
		Lnode temp=searchLocation(Lname);
		if(temp!=null) {
			Mnode ptr=temp.Mnext;
			for(int i=0;i<temp.CountMartyr;i++) {
				if(((Martyr)ptr.data).getmName().equals(oldMname)) {
					((Martyr)ptr.data).setmName(newMname);
					((Martyr)ptr.data).setDate(date);
					((Martyr)ptr.data).setAge(age);
					((Martyr)ptr.data).setGender(gender);
				}
				ptr=ptr.next;
			}
		}
		else {
			System.out.println("Location doesnr exist");
			return;
		}
	}
	///////////////////////////////////////////////////////////
	public void deleteMartyr(String Lname,String Mname) {
		Lnode temp=searchLocation(Lname);
		if(temp!=null) {
			Mnode ptr=temp.Mnext;
			if(temp!=null) {
				if(((Martyr)ptr.data).getmName().equals(Mname)) {
					removeMFirst(Lname);
				}
				for(int i=0;i<temp.CountMartyr;i++) {
					if(((Martyr)ptr.data).getmName().equals(Mname)) {
						removeMartyrByData(((Martyr)ptr.data).getmName(),Lname);
						return;
					}
					ptr=ptr.next;
				}
			}
		}
		else {
			System.out.println("Location doesnr exist");
			return;
		}
	}



	public boolean removeMartyrByData(String name,String Lname) {
		Lnode abc=searchLocation(Lname);
		Mnode ptr = abc.Mnext;
		for (int i = 0; i < count; i++) {
			if ((((Martyr)ptr.data).getmName().equals(name))) {
				return removeM(i,Lname);
			}
			ptr = ptr.next;
		}

		return false;

	}

	public boolean removeM(int index,String Lname) {

		Lnode abc=searchLocation(Lname);
		Mnode ptr=abc.Mnext;
		for(int i=0;i<index-1;i++) {
			ptr=ptr.next;
		}
		Mnode temp=ptr.next;
		if(temp!=null) {
			ptr.next=temp.next;
			temp.next=null;
			abc.CountMartyr--;
		}
		else {
			temp=ptr;
		}
		return true;
	}

	public boolean removeMFirst(String Lname) {
		Lnode abc=searchLocation(Lname);
		Mnode ptr=first.Mnext;
		if (ptr == null) {
			return false;
		} else {
			abc.Mnext=ptr.next;
			last.Mnext=abc.Mnext;
		}
		abc.CountMartyr--;
		return true;
	}

	/////////////////////////////////////////////////////////////////

}
