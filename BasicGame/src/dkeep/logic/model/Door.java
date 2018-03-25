package dkeep.logic.model;

public class Door extends GameObject{
	private boolean open;
	private final char close;
	private final char openChar;
	
	public Door(int xPos, int yPos) {
		super(xPos, yPos);
		this.open = false;
		this.close = 'I';
		this.openChar = 'S';
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}

	public char getChar() {
		if(this.open) {
			return this.openChar;
		}else {
			return this.close;
		}
	}
}
