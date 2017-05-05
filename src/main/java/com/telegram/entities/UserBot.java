package com.telegram.entities;

import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.Location;

public class UserBot {
	
	User u;
	int state;
	Location loc;
	int lastSentMessage;
	int lastReceivecMessage;
	
	public UserBot(User input )
	{
		this.u=input;
		this.state=0;
		this.loc=null;
		this.lastReceivecMessage=0;
		this.lastSentMessage=0;
	}
	
	
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
	
	
	public Location getLoc() {
		return loc;
	}


	public void setLoc(Location loc) {
		this.loc = loc;
	}


	public int getLastSentMessage() {
		return lastSentMessage;
	}


	public void setLastSentMessage(int lastSentMessage) {
		this.lastSentMessage = lastSentMessage;
	}


	public int getLastReceivecMessage() {
		return lastReceivecMessage;
	}


	public void setLastReceivecMessage(int lastReceivecMessage) {
		this.lastReceivecMessage = lastReceivecMessage;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	
	

}
