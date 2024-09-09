package MartyrPackege;

import java.util.Date;

import DistrictPackege.District;
import LocationPackege.Location;

public class Martyr {
	
	private String mName;
	private Date date;
	private int age;
	private Location location;
	private District district;
	private String gender;
	

	public Martyr(String mName, Date date, int age,Location location, District district,String gender) {
		super();
		this.mName = mName;
		this.date = date;
		this.age = age;
		this.location = location;
		this.district = district;
		this.gender=gender;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Override
	public String toString() {
		String d=date.getMonth()+"/"+date.getDate()+"/"+date.getYear();
		return mName + "," + d + "," + age + "," + location.getlName() + ","
				+ district.getdName() + "," + gender ;
	}
	
	
	
	
}
