package me.TheFr0gsL3gs.critical_life.event;

abstract public class Event {
	String message;
	String name;
	boolean secret;

	abstract public void onTrigger();

}
