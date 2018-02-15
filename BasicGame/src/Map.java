
public class Map {
	private String[][] map;
	private String[][] legend;
	private Interface myInterface;
	
	class Legend{
		public String wall;
		public String door;
		public String guard;
		public String lever;
		public String freeSpace;
	}
	
	public Map()
	{
		this.map = new String[][] {{" X"," X"," X"," X"," X"," X"," X"," X"," X"," X"},
				{" X"," H","  ","  "," I","  "," X","  "," G"," X"},
				{" X"," X"," X","  "," X"," X"," X","  ","  "," X"},
				{" X","  "," I","  "," I"," X"," X","  ","  "," X"},
				{" X"," X"," X","  "," X"," X"," X","  ","  "," X"},
				{" I","  ","  ","  ","  ","  ","  ","  ","  "," X"},
				{" I","  ","  ","  ","  ","  ","  ","  ","  "," X"},
				{" X"," X"," X","  "," X"," X"," X"," X","  "," X"},
				{" X","  "," I","  "," I","  "," X"," K","  "," X"},
				{" X"," X"," X"," X"," X"," X"," X"," X"," X"," X"}};
				
		this.legend = new String[][] {{"X - Wall"},
			{"I - Door"},
			{"H - Hero"},
			{"G - Guard"},
			{"k - lever"},
			{"empty cell - free space"}};		
				
		this.myInterface = new Interface();	
			
		
	}
	
	public String[][] getMap()
	{
		return this.map;
	}
	
	public String[][] getLegend()
	{
		
		return this.legend;
	}
	
	
	public void printMap()
	{
		for(int i = 0; i < this.map.length; i++)
		{
			for(int j = 0; j < this.map[i].length; j++)
			{
				System.out.print(this.map[i][j]);
			}
			System.out.println();
		}
	}
	
//	public void printArray(String[][] myArray)
//	{
//		for(int i = 0; i < myArray.length; i++)
//		{
//			for(int j = 0; j < myArray[i].length; j++)
//			{
//				System.out.print(myArray[i][j]);
//			}
//			System.out.println();
//		}
//	}

	
	public void printLegend() {
		
		for(int i = 0; i < this.legend.length; i++){
			
			for(int j = 0; j < this.legend[i].length; j++){
				
				System.out.print(this.legend[i][j]);
			}
			
			System.out.println();
		}
	}
	
	public boolean checkMove() {
		
		
		return false;
	}
	
	public int[] findCharacter() {
		
	}
	
}
