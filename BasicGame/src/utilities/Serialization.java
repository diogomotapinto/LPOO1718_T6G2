package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import dkeep.controller.Controller;
import dkeep.controller.StateMachine;
import dkeep.logic.Map;

public class Serialization {

	private final String mapSerializedFile;
	private final String stateMachineSerializedFile;
	private final String ogreNumberSerializedFile;
	private final String editMapSerializedFile;

	public Serialization() {
		mapSerializedFile = "map.ser";
		stateMachineSerializedFile = "stateMachine.ser";
		ogreNumberSerializedFile = "ogreNumber.ser";
		editMapSerializedFile = "editMap.ser";
	}

	public void serialize(Controller c) {
		ObjectOutputStream outStream1 = null, outStream2 = null, outStream3 = null, outStream4 = null;
		FileOutputStream fileOut1 = null, fileOut2 = null, fileOut3 = null, fileOut4 = null;

		try {
			fileOut1 = new FileOutputStream(mapSerializedFile);
			outStream1 = new ObjectOutputStream(fileOut1);

			fileOut2 = new FileOutputStream(stateMachineSerializedFile);
			outStream2 = new ObjectOutputStream(fileOut2);

			fileOut3 = new FileOutputStream(ogreNumberSerializedFile);
			outStream3 = new ObjectOutputStream(fileOut3);

			fileOut4 = new FileOutputStream(editMapSerializedFile);
			outStream4 = new ObjectOutputStream(fileOut4);

			outStream1.writeObject((Serializable) c.getCurrentMap());
			outStream2.writeObject((Serializable) c.getStateMachine());
			outStream3.writeObject((Serializable) c.getEditMap());
			outStream4.writeObject((Serializable) c.getOgreNumber());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileOut1.close();
				fileOut2.close();
				fileOut3.close();
				fileOut4.close();

				outStream1.close();
				outStream2.close();
				outStream3.close();
				outStream4.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Controller deSerialize() {
		ObjectInputStream inStream1 = null, inStream2 = null, inStream3 = null, inStream4 = null;
		FileInputStream fileIn1 = null, fileIn2 = null, fileIn3 = null, fileIn4 = null;
		Object readObject1 = null, readObject2 = null, readObject3 = null, readObject4 = null;
		try {
			fileIn1 = new FileInputStream(mapSerializedFile);
			inStream1 = new ObjectInputStream(fileIn1);
			fileIn2 = new FileInputStream(stateMachineSerializedFile);
			inStream2 = new ObjectInputStream(fileIn2);
			fileIn3 = new FileInputStream(ogreNumberSerializedFile);
			inStream3 = new ObjectInputStream(fileIn3);
			fileIn4 = new FileInputStream(editMapSerializedFile);
			inStream4 = new ObjectInputStream(fileIn4);
			readObject1 = inStream1.readObject();
			readObject2 = inStream2.readObject();
			readObject3 = inStream3.readObject();
			readObject4 = inStream4.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new Controller();
		} finally {
			try {
				fileIn1.close();
				fileIn2.close();
				fileIn3.close();

				fileIn4.close();
				inStream1.close();
				inStream2.close();
				inStream3.close();
				inStream4.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map m = (Map) readObject1;
		StateMachine s = (StateMachine) readObject2;
		char[][] editMap = (char[][]) readObject3;
		String ogreNmb = (String) readObject4;

		return new Controller(m, s, ogreNmb, editMap);

	}
}
