package ntLoggerTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ntLoggerTest {

	public static void main(String[] args) {
		NetworkTable.setIPAddress("localhost");
		NetworkTable.setClientMode();
		try {
			NetworkTable.initialize();
		} catch (IOException e) {
			System.err.print("IOException thrown from NetworkTable.initialize()"); 
			e.printStackTrace();
			return;
		}
		
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS");
		
		NetworkTable nt = NetworkTable.getTable("2129/log");
		String sMessage = dateFormatter.format(date) + " Hello World!";
		nt.putString("logMessage", sMessage );
		System.out.print("Terminating");
		
		return;
	}

}
