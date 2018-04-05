package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import dkeep.controller.Controller;
import dkeep.logic.Map;
import dkeep.controller.StateMachine;;

public class Serialization {

	private static final String mapSerializedFile = "map.ser";
	private static final String stateMachineSerializedFile = "stateMachine.ser";

	public static void serialize() {
		try {
			FileOutputStream fileOut1 = new FileOutputStream(mapSerializedFile);
			ObjectOutputStream outStream1 = new ObjectOutputStream(fileOut1);

			FileOutputStream fileOut2 = new FileOutputStream(stateMachineSerializedFile);
			ObjectOutputStream outStream2 = new ObjectOutputStream(fileOut2);

			outStream1.writeObject((Serializable) Controller.getInstance().getCurrentMap());
			outStream2.writeObject((Serializable) Controller.getInstance().getStateMachine());

			outStream1.close();
			outStream2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} catch (Exception e) {

		}
	}

	public static Controller deSerialize() {
		try {
			FileInputStream fileIn1 = new FileInputStream(mapSerializedFile);
			ObjectInputStream inStream1 = new ObjectInputStream(fileIn1);

			FileInputStream fileIn2 = new FileInputStream(stateMachineSerializedFile);
			ObjectInputStream inStream2 = new ObjectInputStream(fileIn2);

			Object readObject1 = inStream1.readObject();
			Object readObject2 = inStream2.readObject();

			StateMachine s = (StateMachine) readObject2;
			Map m = (Map) readObject1;
			inStream1.close();
			inStream2.close();

			Controller c = new Controller(m, (StateMachine) readObject2);

			return c;
		} catch (Exception e) {
			// e.printStackTrace();
			return Controller.getInstance();
		}
	}
}
