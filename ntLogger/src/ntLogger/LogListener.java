package ntLogger;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class LogListener implements ITableListener {

	private String logFilePath;
	
	public LogListener( String _logFilePath )
	{
		logFilePath = _logFilePath;
	}
	
	public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) 
	{
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS");
		
		String logString = String.format("%s\t%s\n", dateFormatter.format( new Date() ), arg2 );
		
		System.out.format( logString );
		
		Writer writer = null;

		try
		{
		    writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(this.logFilePath, true), "utf-8"));
		    writer.write( logString );
		} 
		catch (IOException ex) 
		{
		  System.out.format("Error writing log to %s: %s\n", this.logFilePath, ex.getMessage());
		} 
		finally 
		{
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
}
