package Mods;

import Files.*;

// Box class
public class Box extends Modification {

	// Attributes
	// Inherited from Parent Modification Class

	// Static
	private static final String DEFAULT_BOX = "Box";
	private static final String STORAGE_BOX = "box_on_storage";

	// Constructor
	public Box(Location loc) {

		this.initLoc(loc);
		this.onFloor = false;
		this.letter = "B";
		this.img = DEFAULT_BOX;
		this.canPush = true;

	}

	// Methods

	// Overrides
	@Override
	public Modification makeCopy() {

		Box Object = null;

		try {
			Object = (Box) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return Object;
	}

	@Override
	public boolean canMove(String cmd) {
		int coord = 0;

		if (cmd.equals("LEFT")) {
			coord = getCoord(this.loc.getX() - 1, this.loc.getY());
		} else if (cmd.equals("RIGHT")) {
			coord = getCoord(this.loc.getX() + 1, this.loc.getY());
		} else if (cmd.equals("UP")) {
			coord = getCoord(this.loc.getX(), this.loc.getY() - 1);
		} else if (cmd.equals("DOWN")) {
			coord = getCoord(this.loc.getX(), this.loc.getY() + 1);
		}

		for (Modification mod : getModsAt(coord)) {
			if (!mod.isOnFloor()) {
				// only changed line:
				if (!mod.getLetter().equals("B") && mod.canBePushed()) {	// one box cannot be pushed into another
					return mod.canMove(cmd);
				} else {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public String update(String cmd) {

		switch (cmd) {
			case "PLEFT":
				for (Modification mod : getModsAt(this.getCoord())) {
					if (mod.getLetter().equals("P")) {
						board.changeLoc(this.getCoord(), getCoord(this.loc.getX() - 1, this.loc.getY()), this.loc);
						this.loc.setX(this.loc.getX() - 1);
						for (Modification newMod : getModsAt(this.getCoord())) {
							if (newMod.getLetter().equals("S")) {
								this.img = STORAGE_BOX;
								return null;
							} else {
								this.img = DEFAULT_BOX;
							}
						}
					}
				}
				return null;
			case "PRIGHT":
				for (Modification mod : getModsAt(this.getCoord())) {
					if (mod.getLetter().equals("P")) {
						board.changeLoc(this.getCoord(), getCoord(this.loc.getX() + 1, this.loc.getY()), this.loc);
						this.loc.setX(this.loc.getX() + 1);
						for (Modification newMod : getModsAt(this.getCoord())) {
							if (newMod.getLetter().equals("S")) {
								this.img = STORAGE_BOX;
								return null;
							} else {
								this.img = DEFAULT_BOX;
							}
						}
					}
				}
				return null;
			case "PUP":
				for (Modification mod : getModsAt(this.getCoord())) {
					if (mod.getLetter().equals("P")) {
						board.changeLoc(this.getCoord(), getCoord(this.loc.getX(), this.loc.getY() - 1), this.loc);
						this.loc.setY(this.loc.getY() - 1);
						for (Modification newMod : getModsAt(this.getCoord())) {
							if (newMod.getLetter().equals("S")) {
								this.img = STORAGE_BOX;
								return null;
							} else {
								this.img = DEFAULT_BOX;
							}
						}
					}
				}
				return null;
			case "PDOWN":
				for (Modification mod : getModsAt(this.getCoord())) {
					if (mod.getLetter().equals("P")) {
						board.changeLoc(this.getCoord(),
								getCoord(this.loc.getX(), this.loc.getY() + 1), this.loc);
						this.loc.setY(this.loc.getY() + 1);
						for (Modification newMod : getModsAt(this.getCoord())) {
							if (newMod.getLetter().equals("S")) {
								this.img = STORAGE_BOX;
								return null;
							} else {
								this.img = DEFAULT_BOX;
							}
						}
					}
				}
				return null;
			default:
				return null;
		}

	}
}
