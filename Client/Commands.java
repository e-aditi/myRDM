package Client;

public enum Commands{
	PRESS_MOUSE(-10),
	RELEASE_MOUSE(-12),
	PRESS_KEY(-13),
	RELEASE_KEY(-14),
	MOVE_MOUSE(-15);

	private int abbrev;

	Commands(int abbrev){
		this.abbrev = abbrev;
	}

	public int getAbbrev(){
		return abbrev;
	}
}
