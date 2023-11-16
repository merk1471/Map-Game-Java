public class MineEscape
{
	
	private Map map;
	private int numGold;
	private int[] numKeys; 

	public MineEscape (String filename)
	{
		
		try {
			map = new Map(filename);
			this.numGold = 0;
			this.numKeys = new int[3];
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	private MapCell findNextCell (MapCell cell)
	{
		
		MapCell current = null;
		MapCell next = null;
		boolean found = false;
		
		for (int i = 0; i < 4; i++)
		{
			if (!found && cell.getNeighbour(i) != null)
			{
				current = cell.getNeighbour(i);
				if (!current.isWall() && !current.isMarked() && !current.isLava())
				{
					if (current.isExit())
					{
						next = current;
						current.markInStack();
						found = true;
					}
				}
			}
		}
		
		for (int i = 0; i < 4; i++)
		{
			if (!found && cell.getNeighbour(i) != null)
			{
				current = cell.getNeighbour(i);
				if (!current.isWall() && !current.isMarked() && !current.isLava())
				{
					if (current.isKeyCell() || current.isGoldCell())
					{
						next = current;
						current.markInStack();
						found = true;
					}
				}
			}
		}
		
		for (int i = 0; i < 4; i++)
		{
			if (!found && cell.getNeighbour(i) != null)
			{
				current = cell.getNeighbour(i);
				if (!current.isWall() && !current.isMarked() && !current.isLava())
				{
					if (current.isFloor())
					{
						System.out.println(i);
						next = current;
						current.markInStack();
						found = true;
					}
				}
			}
		}
		
		for (int i = 0; i < 4; i++)
		{
			if (!found && cell.getNeighbour(i) != null)
			{
				current = cell.getNeighbour(i);
				if (!current.isWall() && !current.isMarked() && !current.isLava())
				{
					if (current.isLockCell())
					{
						
						if ((current.isRed() && numKeys[0] != 0) || (current.isBlue() && numKeys[1] != 0) || (current.isGreen() && numKeys[2] != 0))
							{
								next = current;
								current.markInStack();
								found = true;
							}
					}
				}
			}
		}
		
		return next;
	}

	public String findEscapePath()
	{
		ArrayStack<MapCell> S = new ArrayStack<>();
		String pathString = "Path: ";
		
		S.push(map.getStart());
		pathString += S.peek() + " ";
		boolean running = true;
		S.peek().markInStack();
		MapCell curr;
		
		while (!S.isEmpty() && running)
		{
			curr = S.peek();
			if (curr.isExit())
			{
				running = false;
				break;
			}
			
			if (curr.isKeyCell())
			{
				if (curr.isRed())
				{
					numKeys[0] += 1;
				}
				
				if (curr.isBlue())
				{
					numKeys[1] += 1;
					
				}
				
				if (curr.isGreen())
				{
					numKeys[2] += 1;
				}
				
				curr.changeToFloor();
			}
			
			if (curr.isGoldCell())
			{
				++numGold;
				curr.changeToFloor();
			}
			
			for (int i = 0; i < 4; i++)
			{
				if (curr.getNeighbour(i)!= null)
				{
					if (curr.getNeighbour(i).isLava())
					{
						numGold = 0;
					}
				}
			}
			
			
			MapCell next = findNextCell(curr);
			if (next == null)
			{
				curr = S.pop();
				curr.isMarkedOutStack();
			}
			
			else
			{
				pathString += (next.getID()) + " ";
				S.push(next);
				next.isMarkedInStack();
				if (next.isLockCell())
				{
					if (next.isRed())
					{
						numKeys[0] -= 1;
					}
					
					if (next.isBlue())
					{
						numKeys[1] -= 1;
						
					}
					
					if (next.isGreen())
					{
						numKeys[2] -= 1;
					}
					
					next.changeToFloor();
				}
			}
			
		}

		if (!running)
		{
			return(pathString + numGold + "G");
		}
		
		else
		{
			return ("No solution found");
		}
	}
}
