package ntLogger;

import java.io.IOException;

//import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.tables.*;

public class LogMain {
		
	private static class CommandLineParms
	{
		public boolean isDeveloperTestMode; // If this is set to true, then host will be localhost
		public String teamNumber;
		public String logFilePath;
	}
	
	private static boolean getCommandLineParms( String[] args, CommandLineParms parms )
	{
		if (args.length == 1)
		{
// 1 parm: assume the parm is the log file path and the server is localhost 
			parms.isDeveloperTestMode = true;
			parms.teamNumber = "";
			parms.logFilePath = args[0];
		}
		else if (args.length != 2)
		{
			System.out.print( "usage: ntLogger <FRC teamNumber> <log file path>");
			return false;
		}
		else
		{
// 2 parms: should be: <Team Number> <File Path>
			parms.teamNumber = args[0];
			parms.isDeveloperTestMode = false;
			parms.logFilePath = args[1];
		}
		
		System.out.format("ntLogger Running with the following parms:\n\tTeam number:\t\t%s\n\tOutput file path:\t%s\n\n"
				, parms.teamNumber
				, parms.logFilePath );
		
		return true;
	}
	
	public static void main(String[] args) {

		CommandLineParms parms = new CommandLineParms();
		if (!getCommandLineParms( args, parms ))
		{
			System.exit(1);
		}
		
		String robotHostName;
		if (parms.isDeveloperTestMode)
		{
			robotHostName = "localhost";
		}
		else
		{
			robotHostName = getRobotHostName( parms.teamNumber );
		}
		
		NetworkTable.setIPAddress(robotHostName);
		NetworkTable.setClientMode();
		try {
			NetworkTable.initialize();
		} catch (IOException e) {
			System.err.format("IOException thrown from NetworkTable.initialize() connecting to %s", robotHostName); 
			e.printStackTrace();
			return;
		}
		 
		NetworkTable nt = NetworkTable.getTable("2129/log");
		
		ITableListener listener = new LogListener( parms.logFilePath );
		nt.addTableListener( listener, true );
		
		return;
	}
	
	public static String getRobotHostName(String teamNumber) {
		return "roboRIO-" + teamNumber + "-frc.local";
//			host = "10." + (_team / 100) + "." + (_team % 100) + ".2";
	}

}
