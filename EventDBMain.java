// Title 			 Programming Assignment 1	
// File:             (EventDB)
// Semester:         CS367 Spring 2013
// Author:           (Song Zhao szhao34@wisc.edu)
// CS Login:         (szhao)
// Lecturer's Name:  (Jim Skrentny)
// Lab Section:      (Lecture 2)

import java.util.*;
import java.io.File;
import java.io.IOException;
public class EventDBMain {
    public static void main(String[] args) throws IOException{
    	/*Check whether exactly one command-line argument is given;
    	if not, display "Usage: java EventDBMain FileName" and quit.*/
    	if(args.length!=1)
    	{
    		System.out.println("Usage: java EventDBMain FileName");
    		System.exit(1);
    	}
    	/*Check whether the input file exists and is readable; 
    	if not, display "Error: Cannot access input file" and quit.
    	 */	
    	File srcFile = new File(args[0]);
    		if(!srcFile.exists()||!srcFile.canRead())
    		{
    			System.out.println("Error: Cannot access input file");
    			System.exit(1);
    		}
    	
    	/*Load the data from the input file and use it to construct an Athlete database.*/
    	Scanner fileIn = new Scanner(srcFile);
    	EventDB edb = new EventDB();
    	List<String> athleteList = new ArrayList<String>();
    	while(fileIn.hasNext())
    	{
    		String line= capitalizeString(fileIn.nextLine());// capitalize the initial word
    		String delims = ",";
    		String[] tokens = line.split(delims);
    		athleteList.add(tokens[0]);
    		int i = 0;
    		while(i + 1 < tokens.length) //add Events into database, add Athlete into Event.
    		{
    			edb.addEvent(tokens[i+1]);
    			edb.addAthlete(tokens[0],tokens[i+1]);
    			i++;
    		}
    	}
    	fileIn.close(); 
    	  	
        Scanner stdin = new Scanner(System.in);  // for reading console input
        boolean done = false;
        while (!done) {
            System.out.print("Enter option ( cdprswx ): ");//display input promo
            String input = stdin.nextLine();

            // only do something if the user enters at least one character
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) // if there is an argument
                {
                    remainder = capitalizeString(input.substring(1).trim());  // trim off any leading or trailing spaces
	                switch (choice) { // the commands that have arguments
	               
	                /*If there is no event with the given type in the database, 
	                 * display "event not found". Otherwise, clear the event 
	                 * with the given type from the database, by removing it 
	                 * and displaying "event removed".
	                 */
	                case 'c':
	                	String t1 = remainder; //t1 is the arguments
	                	if(!edb.containsEvent(t1))
	                	{
	                		System.out.println("event not found");
	                	}else
	                	{
	                		edb.removeEvent(t1);
	                		System.out.println(t1);
	                		System.out.println("event removed");	               
	                	}
	                 break;
	              
	                 
	             	/*If there is no Athlete with the given name in the database, 
	                	display "Athlete not found". Otherwise, 
	                	print the events the Athlete with the given name is cast in in the format:
	                	event title 1, event title 2, event title 3
	                */
	                case 'p':
	                	String n = remainder;//n is the arguments
	                	String tEvent="";
	                	if(edb.containsAthlete(n))
	                	{
	                		List<String> tempEventList=new ArrayList<String>();
	                		tempEventList =edb.getEvents(n);
	                		Iterator<String>itr = tempEventList.iterator();
	                		while(itr.hasNext())
	                		{
	                			tEvent += itr.next() + ", ";
	                		}
	                		tEvent = tEvent.substring(0, tEvent.length()-2);
	                		System.out.println("p "+ remainder);
	                		System.out.println(tEvent);
	                	}
	                	else
	                	{
	                		System.out.println("p "+ remainder);
	                		System.out.println("athlete not found");
	                	}
	                	break;
	                	
	                /*	If there is no event with the given type in the database, 
	                 * display "event not found". If the event's roster is empty, 
	                 * display "none". Otherwise, display the roster for the event 
	                 * with the given type in the format: athlete name 1, athlete name 2, 
	                 * athlete name 3 
	                 */
	
	                case 'r':
	                	String t = remainder; //t is the arguments
                		if(edb.containsEvent(t))
                		{
                			List<String> tempAthleteList = new ArrayList<String>();
                			tempAthleteList = edb.getRoster(t); //get athlete list
	                		String tAthlete = "";
	                		if(tempAthleteList.isEmpty())
	                		{
	                			System.out.println("none");
	                		}
	                		else
	                		{
	                			Iterator<String> itr = tempAthleteList.iterator();
	                			while(itr.hasNext()){
	                				tAthlete += itr.next() + ", ";
	                			}
	                			tAthlete = tAthlete.substring(0, tAthlete.length()-2);
	                			System.out.println("r "+ remainder);
	                			System.out.println(tAthlete);
	                	 		}
	                		
                			}
                			else
                			{
	                			System.out.println("r "+ remainder);
	                			System.out.println("Event not found");
	                			
	                		}
	                break;
	
	                case 's':
	                    // The following code reads in a comma-separated sequence 
	                    // of strings.  If there are exactly two strings in the 
	                    // sequence, the strings are assigned to name1 and name2.
	                    // Otherwise, an error message is printed.
	                    String[] tokens = remainder.split("[,]+");
	                    if (tokens.length != 2) {
	                        System.out.println("need to provide exactly two names");
	                    }
	                    else {
	                        String name1 = tokens[0].trim();
	                        String name2 = tokens[1].trim();
	                        if(edb.containsAthlete(name1)){
	                        	if(edb.containsAthlete(name2)){
	                        		List<String>tempEventList1 = new ArrayList<String>();
	                        		List<String>tempEventList2 = new ArrayList<String>();
	                        		List<String>tempEventList3 = new ArrayList<String>();
	                        		    tempEventList1=edb.getEvents(name1);
	                        		    tempEventList2=edb.getEvents(name2);
	                        		    Iterator<String> itr1 = tempEventList1.iterator(); 
	                        		    String MutiAthlete = "";
	                        		    while(itr1.hasNext()){
	                        		    	String tempname = itr1.next();
	                        		    	if(tempEventList2.contains(tempname)){
	                        		    		tempEventList3.add(tempname);
	                        		    		}
	                        		    	}
	                        		    Iterator<String>itrr = tempEventList3.iterator();
	                        		    while(itrr.hasNext()){
	                        		    	MutiAthlete += itrr.next() + ", ";
	                        		    }
	                        		    MutiAthlete = MutiAthlete.substring(0, MutiAthlete.length()-2);
	                        		    System.out.println("s " + name1 + ", " + name2);
	                        		    System.out.println(MutiAthlete);
	                        		    }
	                        	}else{
	                        		System.out.println("s " + name1 + ", "+name2);
	                        		System.out.println("none");
	                        		}
	                        // *** Add more code to implement this option ***
	                    }
	                break;
	                /*If there is no athlete with the given name in the database, 
                	display "athlete not found". Otherwise, 
                	withdraw the athlete with the given name and 
                	display "name withdrawn from all events" where name 
                	is the name of the athlete.*/
	                case 'w':
	                	String n1 = remainder; //n1 is the arguments
	                	if(edb.containsAthlete(n1)){
	                		edb.removeAthlete(n1);
	                		athleteList.remove(n1);
	                		System.out.println("w " + remainder);
	                		System.out.println(remainder + " withdrawn from all events");
	                	}else{
	                		System.out.println("athlete not found");
	                	}
	                    break;
	
	                default: // ignore any unknown commands
                    	System.out.println("Incorrect command.");
	                	break;
	                
	                } // end switch
                } // end if
                else { //if there is no argument
                	switch (choice) { // the commands without arguments
                	/*  1.Display on a line: "Events: integer, Athletes: integer"
						This is the number of unique events followed by the number of unique athletes.
						2. Display on a line: "# of athletes/event: most integer, least integer, average integer"
						where most is the largest number of athletes that any event has, least is the fewest, and 
						average is arithmetic mean number of athletes per event (rounded to the nearest integer).
						3. Display on a line: "# of events/athlete: most integer, least integer, average integer"
						where most is the largest number of events that any athlete is registered in, least is the 
						fewest, and average is arithmetic mean number of events per athlete (rounded to the nearest 
						integer).
						4. Display on a line: "Most Popular: event(s) [integer]"
						This is the event with the largest roster (i.e., most number of athletes roster) followed by 
						the number of athletes roster in that event in square brackets. If there is a tie for the most 
						popular, display all those tying in the order they appear in the database separated by commas.
						5. Display on a line: "Least Popular: event(s) [integer]"
						This is the event with the smallest roster (i.e., least number of athletes roster) followed by 
						the number of athletes roster in that event in square brackets. If there is a tie for the least 
						popular, display all those tying in the order they appear in the database separated by commas.
                	 */
                	case 'd': 
                		System.out.println("Events: " + edb.size() + ", " + "Athletes: " + athleteList.size());
	                    /*Display on a line: "# of Athletes/Event: most integer, least integer, average integer"*/
	                    Iterator<Event> iter = edb.iterator();
	                    int x = edb.getRoster(iter.next().getType()).size();  //the total people in a event
	                    int maxAthlete = x; //highest people in an event
	                    int minAthlete = x; //smallest people in an event
	                    int currentAthlete = 0; //the people in a event at this moment
	                    int currentAve = x;  //average athlete 
	                    int AveAthlete = 0;  //average athlete
	                    int count = 0; //number of events
	                    int Total = x; //total people
	                    while(iter.hasNext())
	                    {
	                    	Event temp3 = iter.next();
	                    	currentAthlete = edb.getRoster(temp3.getType()).size();
	                    	if(currentAthlete > maxAthlete)
	                    	{
	                    		maxAthlete = currentAthlete;
	                    	}
	                    	if(currentAthlete < minAthlete)
	                    	{
	                    		minAthlete = currentAthlete;
	                    	}
	                    	Total = currentAthlete+Total;
	                    	count++;
	                    }
	                    currentAve=Total/count;
	                    AveAthlete=(int) Math.rint(currentAve);
	                    System.out.println("# of Athletes/Event: most "+ maxAthlete+ ", least " + minAthlete + ", average " + AveAthlete);
	                    
	                    /* Display on a line: "# of Events/Athlete: most integer, least integer, average integer"*/	                  	         
	                    Iterator<String> iter1= athleteList.iterator();
	                    int maxEvent=edb.getEvents(iter1.next()).size();
	                    int minEvent=maxEvent;
	                    int Total2=maxEvent;
	                    double aveEvent=0;
	                    int ave=0;
	                    int count1=0;
	                    int currentEvent;
	                    while(iter1.hasNext())
	                    {
	                    	currentEvent=edb.getEvents(iter1.next()).size();
	                    	if(currentEvent>maxEvent)
	                    	{
	                    		maxEvent= currentEvent;
	                    	}
	                    	else if (currentEvent<minEvent)
	                    	{
	                    		minEvent=currentEvent;
	                    	}
	                    	Total2=Total2+currentEvent;
	                    	count1++;
	                    }
	                    aveEvent=Total2/count1;
	                    ave=(int) Math.rint(aveEvent);
	                    System.out.println("# of Events/Athlete: most "+ maxEvent+ ", least " + minEvent + ", average " + ave);
	                    
	                    /*Display on a line: "Most Popular: Event(s) [integer]"*/
	                    String pEvent = ""; //event with most population
	                    String lEvent= "";  //event with least population
	                    Iterator<Event> iter4 = edb.iterator();
	                    while (iter4.hasNext()) 
	                    {
	                    	Event temp = iter4.next();
	                    	if (temp.getRoster().size() == maxAthlete) 
	                    	{
	                    		pEvent += temp.getType() + ", ";
	                    	}
	                    	if (temp.getRoster().size() == minAthlete) 
	                    	{
	                    		lEvent += temp.getType() + ", ";
	                    	}
	                    }
	                    pEvent = pEvent.substring(0, pEvent.length()-2);
	                    lEvent = lEvent.substring(0, lEvent.length()-2);
	                    System.out.println("Most Popular: " + pEvent + " [" + maxAthlete + "]");
	                    System.out.println("Least Popular: " + lEvent + " [" + minAthlete + "]");
	                    break;
	               
	                    
                	case 'x':
	                    done = true;
	                    System.out.println("exit");
	                    break;
	                    
                	default:  // a command with no argument
                		System.out.println("Incorrect command.");
	                    break;
                	} // end switch
                } // end else  
           } // end if
        } // end while
    } // end main



/*This is a function to capitalize all the names
*@param string (the string that need to be converted)
*/    
public static String capitalizeString(String string) {
	  char[] chars = string.toLowerCase().toCharArray();
	  boolean found = false;   //boolean variable that indicates it is a letter or something else.
	  for (int i = 0; i < chars.length; i++) {
	    if (!found && Character.isLetter(chars[i])) //if it's a small letter
	    	{
	    		chars[i] = Character.toUpperCase(chars[i]); //capitalize it
	    		found = true;
	    	} 
	    else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\''||chars[i]==',') //if its not a letter
	    	{
	    		found = false;// do nothing
	    	}
	  }
	  return String.valueOf(chars);
	}
}