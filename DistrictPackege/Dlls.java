package DistrictPackege;

import java.util.Date;

import LocationPackege.Lls;
import LocationPackege.Lnode;
import LocationPackege.Location;
import MartyrPackege.Martyr;
import MartyrPackege.Mls;
import MartyrPackege.Mnode;

public class Dlls  {
	
	public Dnode first;
	Dnode last;
	static int count = -1;

	public Object getFirst() {
		if (first == null) {
			return null;
		}
		return first;
	}

	public Object getLast() {
		if (last == null) {
			return null;
		}
		return last;
	}

	public void addFirst(Object data) {

		if(CheckDistrict(((District)data).getdName())){
			Dnode node = new Dnode(data);
			if (first == null) {
				last = first = node;
			} 
			else {
				node.next = first;
				first.prev = node;
				first = node;
				first.prev = last;
				last.next=first;

			}
			count++;
		}
		else {
			System.out.println("Error first");
		}

	}


	public void addLast(Object data) {
		if(CheckDistrict(((District)data).getdName())){
			Dnode node = new Dnode(data);
			if (last == null) {
				last = first = node;
			} else {
				node.prev = last;
				last.next = node;
				last = node;
				last.next=first;
				first.prev=last;
			}
			count++;
		}
		else {
			System.out.println("Error");
		}
	}

	public void add(Object data, int index) {

		Dnode node = new Dnode(data);
		if (index <= 0) {
			addFirst(data);
		} else if (index > count) {
			addLast(data);
		} else {
			Dnode curr = first;
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}
			node.next = curr.next;
			node.prev = curr;
			curr.next = node;
			node.next.prev = node;
			count++;
		}
	}



	public boolean removeFirst() {
		if (first == null) {
			return false;
		} else if (count == 0) {
			last = first = null;

		} else {
			Dnode temp = first;
			first = first.next;
			first.prev = null;
			first.prev = last;
			last.next=first;
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
			Dnode temp = last;
			last=last.prev;
			last.next=null;
			last.next=first;
			first.prev=last;
			temp.prev = null;

		}
		count--;
		return true;

	}

	public boolean remove(int index) {
		if (count < 0 || index > count) {
			return false;

		} else if  (index == 0) {
			return removeFirst();
		} else if (count == index) {
			return removeLast();
		} else {
			Dnode curr = first;
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}
			Dnode temp = curr.next;
			curr.next = temp.next;
			temp.next.prev = curr;
			temp.next=null;
			temp.prev=null;
			count--;

		}
		return true;
	}

	public boolean removeByData(Dnode node) {
		
		if (first == null) {
			return false;
		} else if (first.equals(node)) {
			return removeFirst();

		}
		else if (last.equals(node)) {
			return removeLast();

		} else {
			Dnode ptr = first.next;
			for (int i = 1; i < count-1; i++) {
				if (((District)ptr.data).getdName().equals(((District)node.data).getdName())) {	
					return remove(i);
				}
				ptr = ptr.next;
			}
		}
		return false;
	}

	public int getSize() {
		return count;
	}

	public Dnode searchDistrict(String dname) {
		Dnode temp = first;
		for (int i = 0; i <=count; i++) {
			if(((District)temp.data).getdName().equals(dname)) {
				return temp;		
			}
			temp = temp.next;
		}
		return null;
	}

	public void addLocation(String Dname,Lls list) {
		
		Dnode temp = searchDistrict(Dname);
		Lnode node = list.first;
		if (temp.Lnext == null) {
			temp.Lnext = node;
			return;
		} 
		else {
			node.next=temp.Lnext;
			temp.Lnext = node;
		}
		temp.Lcount=list.count;
	}

	public void PrintDirstricts() {

		Dnode temp = first;
		for (int i = 0; i < count; i++) {
			System.out.print(((District)temp.data).getdName()+"\n");
			temp = temp.next;
		}
		System.out.print(((District)temp.data).getdName()+"\n");

	}

	public void locationToString(String name) {

		Lnode temp = searchDistrict(name).Lnext;

		for (int i = 0; i < searchDistrict(name).Lcount; i++) {
			System.out.println(((Location)temp.data).getlName());
			temp = temp.next;
		}
	}

	public String[] getNames() {
		String []arr=new String[count+1];
		Dnode temp=first;
		for(int i=0;i<arr.length;i++) {
			arr[i]=(((District)temp.data).getdName());
			temp=temp.next;
		}
		return arr;
	}

	public String[] getLocations(String dname) {
		Dnode abc=searchDistrict(dname);
		String []arr=new String[abc.Lcount];
		Lnode temp = abc.Lnext;
		for(int i=0;i<abc.Lcount;i++) {

			arr[i]=(((Location)temp.data).getlName());
			temp=temp.next;

		}
		return arr;
	}

	public boolean updateDistrict(String oldName,String newName) {

		Dnode node;
		if(searchDistrict(oldName)!=null) {
			node=searchDistrict(oldName);
			((District)node.data).setdName(newName);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean CheckDistrict(String name) {
		Dnode temp=first;
		for(int i=-1;i<count;i++) {
			if(((District)temp.data).getdName().equals(name)) {
				return false;
			}
			temp=temp.next;
		}
		return true;
	}

	public void deleteDistrict(String name) {
		Dnode node = searchDistrict(name);
		removeByData(node);
	}

	public boolean CheckLocation(String name) {
		Dnode temp=first;
		Lnode ptr=temp.Lnext;
		for(int i=-1;i<count;i++) {
			for(int j=-1;j<temp.Lcount;j++) {
				if(((Location)ptr.data).getlName().equals(name)) {
					return false;
				}
				ptr=ptr.next;
			}
			temp=temp.next;
		}
		return true;
	}

	public int TotalMartyr(String dname) {
		Dnode temp=searchDistrict(dname);
		if(temp!=null) {
			Lnode ptr=temp.Lnext;
			int x=0;
			for(int i=0;i<temp.Lcount;i++) {
				x+=ptr.CountMartyr;
				ptr=ptr.next;
			}
			return x;
		}
		else {
			return -1;
		}
	}

	public int TotalMale(String dname) {
		Dnode temp=searchDistrict(dname);
		if(temp!=null) {
			Lnode ptr=temp.Lnext;

			int x=0;

			for(int i=0;i<temp.Lcount;i++) {
				Mnode abc=ptr.Mnext;
				for(int j=0;j<ptr.CountMartyr;j++) {
					if(((Martyr)abc.data).getGender().equals("M")) {
						x++;
					}
					abc=abc.next;
				}
				ptr=ptr.next.next;
			}
			return x;
		}
		else {
			return -1;
		}
	}

	public int TotalFemale(String dname) {
		Dnode temp=searchDistrict(dname);
		if(temp!=null) {
			Lnode ptr=temp.Lnext;

			int x=0;

			for(int i=0;i<temp.Lcount;i++) {
				Mnode abc=ptr.Mnext;
				for(int j=0;j<ptr.CountMartyr;j++) {
					if(((Martyr)abc.data).getGender().equals("F")) {
						x++;
					}
					abc=abc.next;
				}
				ptr=ptr.next.next;
			}
			return x;
		}
		else {
			return -1;
		}
	}

	public int TotalDate(String dname,String str) {
		Dnode temp=searchDistrict(dname);
		if(temp!=null) {
			Lnode ptr=temp.Lnext;
			Date date =new Date();
			String d[]=str.split("/");
			if(d.length!=3) {
				return -1;
			}
			date.setDate(Integer.parseInt(d[1]));
			date.setMonth(Integer.parseInt(d[0]));
			date.setYear(Integer.parseInt(d[2]));
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);

			int x=0;

			for(int i=0;i<temp.Lcount;i++) {
				Mnode abc=ptr.Mnext;
				for(int j=0;j<ptr.CountMartyr;j++) {
					if(((Martyr)abc.data).getDate().getYear()==date.getYear()) {
						if(((Martyr)abc.data).getDate().getMonth()==date.getMonth()) {
							if(((Martyr)abc.data).getDate().getDate()==date.getDate()){
								x++;
							}
						}

					}
					abc=abc.next;
				}
				ptr=ptr.next.next;
			}
			return x;
		}
		else {
			return -1;
		}
	}

	public double ageAvg(String Dname) {
		Dnode temp=searchDistrict(Dname);
		if(temp!=null) {
			Lnode ptr=temp.Lnext;

			int x=0;
			int Mcount=0;
			for(int i=0;i<temp.Lcount;i++) {
				Mnode abc=ptr.Mnext;
				for(int j=0;j<ptr.CountMartyr;j++) {
					x+=(((Martyr)abc.data).getAge());
					abc=abc.next;
				}
				Mcount +=ptr.CountMartyr;
				ptr=ptr.next;
			}
			if(Mcount!=0) {
				return x/Mcount;
			}
			else {
				return 0;
			}
		}

		else {
			return -1;
		}


	}

	public Location loadFirstLoc(String name) {
		Dnode temp = searchDistrict(name);
		if(temp!=null) {
			Lnode x=temp.Lnext;
			if(x!=null) {
				for(int i=0;i<temp.Lcount;i++) {
					System.out.println("AAA");
					x=x.next;
				}

				return (((Location)x.data));
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}


	public void addSorted(Object newData) {
		Dnode newNode = new Dnode(newData);

		if (first == null) {
			last=first = newNode;
			count++;
			return;
		}

		if (((District)newNode.data).getdName().compareToIgnoreCase(((District)first.data).getdName()) < 0) {
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
			count++;
			return;
		}

		Dnode current = first;
		while (current.next != null && ((District)newNode.data).getdName().compareToIgnoreCase(((District)current.next.data).getdName()) > 0) {
			current = current.next;
		}

		newNode.next = current.next;
		if (current.next != null) {
			current.next.prev = newNode;
		}
		current.next = newNode;
		newNode.prev = current;
		count++;
	}

	public Date maxDate(String name) {
		Dnode temp=searchDistrict(name);

		if(temp==null) {
			return null;
		}

		Lnode ptr=temp.Lnext;
		if(ptr != null) {
			Mnode mNode=ptr.Mnext;

			Mnode max=mNode;
			String str1="";

			int res=0;

			for(int i=0;i<temp.Lcount+1;i++) {
				if(mNode!=null) {
					if(mNode.next!=null) {
						max=mNode.next;
					}
				}
				for(int j=0;j<ptr.CountMartyr;j++) {
					Date date=(((Martyr)mNode.data).getDate());
					String str=date.getMonth()+"/"+date.getDate()+"/"+date.getYear();
					if(max!=null) {
						Date d1=(((Martyr)max.data).getDate());
						str1=d1.getMonth()+"/"+d1.getDate()+"/"+d1.getYear();
					}
					if(TotalDate( name, str)> TotalDate( name, str1)) {
						max=mNode;
					}
					mNode=mNode.next;
				}
				ptr=ptr.next;
				mNode=ptr.Mnext;

			}
			return ((Martyr)max.data).getDate();
		}
		return null;
	}

}

