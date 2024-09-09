package DistrictPackege;

import LocationPackege.Lls;
import LocationPackege.Lnode;

public class Dnode {
	public Dnode next;
	Dnode prev;
	public Object data;
	Lnode Lnext;
	public int Lcount;
	public Lls ls=new Lls();


	public Dnode(Object data) {
		super();
		this.data = data;
	}

	public void deleteLoc(String name) {
		Lnode node = ls.searchLocation(name);
		if(node.equals(ls.first)) {
			Lnext=ls.first.next;
		}
		ls.removeByData(node);
	}



}
