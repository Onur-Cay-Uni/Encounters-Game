package LogsPackage;

import CoordinatesPackage.Coordinates;

public class LogAnalytics
{
	private static LogAnalytics logInstance=null;
	private LogAnalytics()
	{
		System.out.println("Making a connection to the external database");
	}

	public static LogAnalytics getOrCreateInstance(){
		if(logInstance==null){
			logInstance = new LogAnalytics();
		}
		return logInstance;
	}
	public void logMove(Coordinates newCoordinates)
	{
		System.out.println("Logging:" +newCoordinates.toString());
	}
}
