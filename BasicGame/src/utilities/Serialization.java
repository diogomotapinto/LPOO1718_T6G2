//package utilities;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//
//import dkeep.logic.Controller;
//
//public class Serialization {
//
//	private static final String PatientSerFile = "registoDoentes.ser";
//
//	public static void serialize() {
//		try {
//			FileOutputStream fileOut1 = new FileOutputStream(PatientSerFile);
//			ObjectOutputStream outStream1 = new ObjectOutputStream(fileOut1);
//			outStream1.writeObject((Serializable) Controller.getInstance());
//			outStream1.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//
//		} catch (Exception e) {
//
//		}
//	}
//
//	public static void deserialize() {
//		try {
//			FileInputStream fileIn1 = new FileInputStream(PatientSerFile);
//			ObjectInputStream inStream1 = new ObjectInputStream(fileIn1);
//			Object readObject = inStream1.readObject();
//			InformationCenter.setPatientList((ArrayList<Patient>) readObject);
//			InformationCenter.deserializeAES();
//			InformationCenter.updateAge();
//			inStream1.close();
//		} catch (Exception e) {
//			InformationCenter.setPatientList(new ArrayList<>());
//			e.printStackTrace();
//		}
//	}
//}
