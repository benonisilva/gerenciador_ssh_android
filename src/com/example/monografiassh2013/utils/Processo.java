package com.example.monografiassh2013.utils;

import java.util.Comparator;

public class Processo{
    // PID|USER|% CPU|% MEM|TIME|COMMAND
	private String pid,user,cpu,men,time,command;

	public Processo(String pid, String user, String cpu, String men,
			String time, String command) {
		super();
		this.pid = pid;
		this.user = user;
		this.cpu = cpu;
		this.men = men;
		this.time = time;
		this.command = command;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMen() {
		return men;
	}

	public void setMen(String men) {
		this.men = men;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//builder.append("Processo [pid=");
		//builder.append(user);
		//builder.append(" - ");
		builder.append(pid);
		builder.append("     -     "); 
		builder.append(cpu);
		builder.append("     -     ");
		builder.append(men);
		builder.append("     -     ");
		builder.append(time);
		builder.append("     -     ");
		builder.append("comando");
		builder.append("\n");
		return builder.toString();
	}

		
}
