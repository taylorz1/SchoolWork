package csse374.Project;

import h.radfunc_t;

public class RelationObject {
	private String from;
	private String to;
	private Enum<Enums.Enum> type;
	public RelationObject(String from, String to, Enum<Enums.Enum> e) {
		this.from = from;
		this.to = to;
		this.type = e;
	}
	
	
	public String getFrom() {
		return this.from;
	}
	
	public String getTo() {
		return this.to;
	}
	
	public Enum<Enums.Enum> getType() {
		return this.type;
	}
	
	public void setType(Enum<Enums.Enum> type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof RelationObject){
			RelationObject r = (RelationObject)obj;
			return r.getFrom().equals(this.from) && r.getTo().equals(this.getTo()) && r.getType().equals(this.getType());
		}
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return from+" "+to+" "+type;
	}
}
