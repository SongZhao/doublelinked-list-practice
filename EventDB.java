// File:             (EventDB)
// Semester:         CS367 Spring 2013
// Author:           (Song Zhao szhao34@wisc.edu)
// CS Login:         (szhao)
// Lecturer's Name:  (Jim Skrentny)
// Lab Section:      (Lecture 2)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class EventDB {
	
	/*Constructs an empty database.*/
	private List<Event>eventList;
	public EventDB()
	{
		eventList=new ArrayList<Event>();
	}
	/*Add a Event with the given type t to the end of the database. 
	* If a Event with the type t is already in the database, just return.
   	* @param (t) (t is the Event name and will be passed into the method to see if the database has the Event name )
    * @return (if the Event is already in the database, just return. if the Event is not 
    * in the database, no return)
    * */
	public void addEvent(String t){
		if(containsEvent(t)){
			return;
			}else{
				eventList.add(new Event(t));
				}
		}
		
		/*Add the Athlete with given name n to the Event with the given type t in the database.
	/*	* @param (n) (Athlete name to add into the database based one the Event name)
	    * @param (t) (Event name, and put Athlete name in)
	    * @return (If a Event with the type t is not in the database throw a java.lang.IllegalArgumentException.
		If n is already in the list of Athletes roster in the Event with type t, just return.*/
	public void addAthlete(String n, String t) {
		if (t == null || n == null) {
			throw new IllegalArgumentException();
			} else {
				Iterator<Event> itr = eventList.iterator();
				boolean flag = false; //boolean variable to see if the event is in the database.
	            while (itr.hasNext()) {
	                Event temp = itr.next();
	                 String EventName = temp.getType();
	                 if (t.equals(EventName)) {
	                    List<String> tempAthleteList = temp.getRoster();
	                    Iterator<String> iter = tempAthleteList.iterator();
	                     boolean flag1 = false; //boolean variable to see if the athlete is in the event.
	                    while (iter.hasNext()) {
	                        String tempAthlete = iter.next();
	                        if (tempAthlete == n) {
	                            flag1 = true;
	                         }
	                    }
	                    if (flag1 == false) {
	                        temp.getRoster().add(n);
	                    }
	                    flag = true;
	                }
	             }
	            if (flag == false) {
	                throw new IllegalArgumentException();
	            }
	        }
		}
	
	/*Remove the Event with the type t from the database.
	* @param (t) (t is the Event name to be removed)
	* @return (If a Event with the type t is not in the database, return false;
    * otherwise (i.e., removal is successful) return true.)*/
	
	public boolean removeEvent(String t)
	{
		Iterator<Event> iter = eventList.iterator();
		while(iter.hasNext())
		{
			Event tmp = iter.next();
			String tempname = tmp.getType();
			if(tempname .equals(t))
			{
				eventList.remove(tmp);
				return true;
			}
		}
		return false;
	}
		
		
	    /*	
	     * check if the database contain the Event name
	     * @param (t) (it is the Event name that we want to check)
	     * @return (/*Return true if a Event with the type t is in the database.*/
	public boolean containsEvent(String t)
	{
		Iterator<Event> iter=eventList.iterator();
		boolean flag= false; //boolean variable to see if the event is in the database
		while(iter.hasNext())
		{
			Event temp = iter.next();
			String tempname = temp.getType();
			if(tempname.equals(t))
			{
				flag = true;
			}
		}
		if(flag == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/*	* @param (n) (The Event name that we want to check)
	    * @return (/*Return true if a Athlete with the name n appears in the roster for at least one Event in the database.*/
	public boolean containsAthlete(String n)
	{
		Iterator<Event> iter=eventList.iterator();
		boolean flag= false; //boolean variable to see if the athlete is in the event
		while(iter.hasNext())
		{
			Event temp= iter.next();
			List<String> AthletesList= new ArrayList<String>();
			AthletesList=temp.getRoster();
			if(AthletesList.contains(n))
			{
				flag=true;
			}
		}
		if(flag==true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	 
	/* If a Event with the type t is not in the database, return false.
		* @param (n) (Athlete name, to see if the Event contains the Athlete)
	    * @param (t) (Event name to check if the Event contains the Athlete)
	    * @return (Returns true if the given Athlete n is in the Event with the given type t.)*/
	public boolean isRegistered(String n, String t)
	{
		Iterator<Event> iter=eventList.iterator();
		List<String> tempAthleteList=new ArrayList<String>();//Roster arraylist
		boolean flag= false; //boolean variable to see if the athlete is in an event
		while(iter.hasNext())
		{
			Event temp = iter.next();
			String tempname = temp.getType();
			if(tempname == t)
			{
				tempAthleteList = temp.getRoster();
				if(tempAthleteList.contains(n))
				{
					flag = true;
				}
			}
		}
		if(flag == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
		If a Event with the type t is not in the database, return null.
	    * @param (t) (the Athlete name to get the Events he/she is in)
	    * @return (Return the roster (list of Athletes) for the Event with the given type t. )*/
	public List<String> getRoster(String t)
	{
		Iterator<Event> iter=eventList.iterator();
		List<String> tempAthleteList=new ArrayList<String>();
		boolean flag=false; //boolean variable to see if the athlete is in the event
		while(iter.hasNext()&&!flag)
		{
			Event temp= iter.next();
			String tempname= temp.getType();
			if(t.equals(tempname))
			{
				tempAthleteList=temp.getRoster();
				flag=true;
			}
		}
		if(flag==true)
		{
			return tempAthleteList;
		}
		else
		{
			return null;	
		}
	}
	/*
		If a Athlete with the name n is not in the database, return null.
		* @param (n) (the Athlete name, we will get eventList that this Athlete is in)
	    * @return (Return the list of Events in which the Athlete with the given name n is in. ) */
	public List<String> getEvents(String n)
	{
		if (n == null) 
		{
		throw new IllegalArgumentException();
		}
		else
		{
			Iterator<Event> iter=eventList.iterator();
			List<String> tempAthleteList=new ArrayList<String>();
			List<String> finaleventList=new ArrayList<String>();
			boolean flag=false; //boolean variable to see if the event is in the database
			while(iter.hasNext())
			{
				Event temp= iter.next();
				String tempEvent=temp.getType();
				tempAthleteList=temp.getRoster();
				Iterator<String> iter1=tempAthleteList.iterator();
				while(iter1.hasNext())
				{
					if(n.equals(iter1.next()))
					{
						finaleventList.add(tempEvent);
						flag=true;
					}
				}
			}
			if(flag==true)
			{
				return finaleventList;
			}
			else
			{
				return null;
			}
		}
	}
	/*
		The Events should be returned in the order they were added to the database 
		(resulting from the order in which they are in the text file)
		* @return (Return an Iterator over the Event objects in the database. )*/
	public Iterator<Event> iterator()
	{
		Iterator<Event> iter = eventList.iterator();
		return iter;
	}
	
	/*Return the number of Events in this database.*/
	public int size()
	{
		Iterator<Event> iter=eventList.iterator();
		List<String> tempeventList= new ArrayList<String>();
		while(iter.hasNext())
		{
			Event temp= iter.next();
			String tempname= temp.getType();
			if(!tempeventList.contains(tempname))
			{
				tempeventList.add(tempname);
			}
		}
		return tempeventList.size();
	}
	/*Remove the Athlete with the given name n from the database 
		(i.e., remove the Athlete from every Event in which they are in). 
		* @return (If a Athlete with the name n is not in the database,
	    return false;otherwise (i.e., the removal is successful) return true.)*/
	public boolean removeAthlete(String n)
	{
		Iterator<Event> iter=eventList.iterator();
		boolean flag= false;
		while(iter.hasNext())
		{
			Event temp= iter.next();
			List<String> AthletesList= new ArrayList<String>();
			AthletesList=temp.getRoster();
			if(AthletesList.contains(n))
			{
				temp.getRoster().remove(n);
				flag = true;
			}
		}
		if(flag==true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

