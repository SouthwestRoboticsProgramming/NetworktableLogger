package networkTablesTestServer;

import java.io.IOException;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class networkTablesTestServer_Main {

	public static void main(String[] args) {
		NetworkTable.setIPAddress("localhost");
		NetworkTable.setServerMode();
		try {
			NetworkTable.initialize();
		} catch (IOException e) {
			System.err.print("IOException thrown from NetworkTable.initialize()"); 
			e.printStackTrace();
			return;
		}
		
		System.out.print("Terminating");

		while (true)
		{
			
		}
	}

}
