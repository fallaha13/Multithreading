import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.AbstractCollection;
import java.util.Random;

//HashMap
//LinkedHashMap
//Hashtable

//defining a thread 
class MyThread extends Thread {

	private static int time;
	private static Patient patient;

	MyThread(Patient withPatient) {
		patient = withPatient;
		MyThread.setTime(patient.getTime());

	}
	
	MyThread() {
		
	}

	public void run() {// job of thread
		try {
			
			System.out.println("Doctor is seeing " + patient.getName());
			sleep(1000);
			System.out.println("The patient is here for a " + patient.getAppointmentType());
			if(time > 3000) {
				sleep(1000);
				System.out.println("Tell the next patient he'll be done at the half hour mark");
			} else if (time < 2000) {
				System.out.println("The doctor should be out 20 minutes from his appointment");
				sleep(1000);
			} else {
				System.out.println("The doctor should be out of his appointment soon");
				sleep(1000);
			}
			sleep(time);
			
			System.out.println("Doctor will see the next patient");
			
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		MyThread.time = time;
	}
}

// theres gonna be a boolean variable for the doctor class that will determine
// whether he is done with a patient or not, or whats on his agenda
//

class ThreadDemo {
	
	private static MyThread appointment = new MyThread();
	private static Patient[] patients;
	
	private static Patient patient;
	
	public static void main(String[] args) throws InterruptedException {
		Patient patient1 = new Patient("Physical","John Keys", randInt(2000,3600));
		Patient patient2 = new Patient("Check up","Marcus Mariota", randInt(2000,3900));
		Patient patient3 = new Patient("Pick up prescription","Alex Fallah", randInt(2000,3900));
		
		Patient patient4 = new Patient("New Patient","Karina", randInt(1000,3900));
		
		patients = new Patient[] {patient1, patient2,patient3,patient4};
		
		for(int i = 0; i < patients.length; i++) {
			appointment = new MyThread(patients[i]);
			appointment.sleep(2200);
			appointment.start();
			appointment.sleep(3000);

		}
		appointment.sleep(2000);
		System.out.println("Doctor has seen all his patients");
	}


	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}

// we need a patient prioritizer
