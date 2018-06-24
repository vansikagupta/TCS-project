package tcs.groupB;

public class Master 
{
	private int SID;
	private String TID;
	private String TDate;
	private String CID;
	private String CName;
	private String CContact;
	private String CEmail;
	private int CAge;
	private String PID;
	private String PName;
	private String PVal;
	private float PTarrif;
	private String PStatus;
	
	
	
	public Master(int sID, String tID, String tDate, String cID, String cName, String cContact, String cEmail, int cAge,
			String pID, String pName, String pVal, float pTarrif, String pStatus) {
		super();
		SID = sID;
		TID = tID;
		TDate = tDate;
		CID = cID;
		CName = cName;
		CContact = cContact;
		CEmail = cEmail;
		CAge = cAge;
		PID = pID;
		PName = pName;
		PVal = pVal;
		PTarrif = pTarrif;
		PStatus = pStatus;
	}
	
	public int getSID() {
		return SID;
	}
	public void setSID(int sID) {
		SID = sID;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getTDate() {
		return TDate;
	}
	public void setTDate(String tDate) {
		TDate = tDate;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	public String getCContact() {
		return CContact;
	}
	public void setCContact(String cContact) {
		CContact = cContact;
	}
	public String getCEmail() {
		return CEmail;
	}
	public void setCEmail(String cEmail) {
		CEmail = cEmail;
	}
	public int getCAge() {
		return CAge;
	}
	public void setCAge(int cAge) {
		CAge = cAge;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	public String getPVal() {
		return PVal;
	}
	public void setPVal(String pVal) {
		PVal = pVal;
	}
	public float getPTarrif() {
		return PTarrif;
	}
	public void setPTarrif(float pTarrif) {
		PTarrif = pTarrif;
	}
	public String getPStatus() {
		return PStatus;
	}
	public void setPStatus(String pStatus) {
		PStatus = pStatus;
	}

	@Override
	public String toString() {
		return "Master [SID=" + SID + ", TID=" + TID + ", TDate=" + TDate + ", CID=" + CID + ", CName=" + CName
				+ ", CContact=" + CContact + ", CEmail=" + CEmail + ", CAge=" + CAge + ", PID=" + PID + ", PName="
				+ PName + ", PVal=" + PVal + ", PTarrif=" + PTarrif + ", PStatus=" + PStatus + "]";
	}
	
	
}
