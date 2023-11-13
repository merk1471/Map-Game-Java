class MineEscape
{
	private Map map;
	private int numGold;
	private int[] numKeys; 
	
	public MineEscape (String filename)
	{
		try {
			Map newMap = new Map(filename);
			this.numGold = 0;
			this.numKeys = new int[3];
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	private MapCell findNextCell (MapCell cell)
	{
		
		if()
		{
			return cell;
		}
		
		else
		{
			
		}
	}
	
	public String findEscapePath()
	{
		ArrayStack<String> S = new ArrayStack<>();
		
		String pathString = "Path: ";
		MapCell cell = findNextCell();
		S.push(cell);
		
		getStart();
		
		pathString += numGold + "G"; 
		
		return ("No solution found");
		
		return pathString;
	}
}
