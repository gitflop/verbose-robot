package fx.aal.arduapp.model;

public class DataSnap {

	private int userId;
	private int snapshotId;
	private int sensorA;
	private int sensorB;
	private int sensorC;
	private int date;


public int getSensorA(){
	return sensorA;
}


public int getSensorB() {
	return sensorB;
}


public void setSensorB(int sensorB) {
	this.sensorB = sensorB;
}


public int getSensorC() {
	return sensorC;
}


public void setSensorC(int sensorC) {
	this.sensorC = sensorC;
}

public void setSensorA(int sensorA){
	this.sensorA = sensorA;
}


public int getDate() {
	return date;
}


public void setDate(int date) {
	this.date = date;
}


public int getUserId() {
	return userId;
}


public void setUserId(int userId) {
	this.userId = userId;
}


public int getSnapshotId() {
	return snapshotId;
}


public void setSnapshotId(int snapshotId) {
	this.snapshotId = snapshotId;
}

}



