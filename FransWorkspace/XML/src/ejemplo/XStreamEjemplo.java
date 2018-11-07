package ejemplo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamEjemplo {
	public static void main(String[] args) {
		
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("person", Person.class);
		xstream.alias("phonenumber", PhoneNumber.class);
		
		Person joe = new Person("Joe", "Walnes");
		joe.setPhone(new PhoneNumber(123, "1234-456"));
		joe.setFax(new PhoneNumber(123, "9999-999"));
		String xml = xstream.toXML(joe);
		System.out.println(xml);
		//Person newJoe = (Person)xstream.fromXML(xml);
	}
}

class Person {
		  private String firstname;
		  private String lastname;
		  private PhoneNumber phone;
		  private PhoneNumber fax;
		  // ... constructors and methods
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public PhoneNumber getPhone() {
			return phone;
		}
		public void setPhone(PhoneNumber phone) {
			this.phone = phone;
		}
		public PhoneNumber getFax() {
			return fax;
		}
		public void setFax(PhoneNumber fax) {
			this.fax = fax;
		}
		public Person(String firstname, String lastname) {
			super();
			this.firstname = firstname;
			this.lastname = lastname;
//			this.phone = phone;
//			this.fax = fax;
		}
		  
		
}

class PhoneNumber {
		  private int code;
		  private String number;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public PhoneNumber(int code, String number) {
			super();
			this.code = code;
			this.number = number;
		}
		  
		  
}
