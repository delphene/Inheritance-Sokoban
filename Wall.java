package sokoban;

/**
 * A class that represents the location of a wall in the
 * game Sokoban.
 */
public class Wall extends Ambiguous {

	/**
	 * Initialize a wall with the specified location.
	 * 
	 * @param loc the location of this wall
	 */
	public Wall(Location loc) {
		this.loc = loc;
		this.name = "Wall";
		this.floorObject = false;
		this.movable = false;
	}

	@Override
	public void playerInteractWithThisObject() {
		// TODO Auto-generated method stub
		
	}
}