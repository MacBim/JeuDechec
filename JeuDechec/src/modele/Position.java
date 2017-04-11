package modele;

public class Position {
	
	public Integer x;

	public Integer y;

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public boolean equals(Position pos){
		if(this.x != pos.x || this.y != pos.y)
			return false;
		return true;
	}

}