class ArrayStack <T> 
{
	private T[] array;
	private int top; 
	
	public ArrayStack() 
	{
		this.array = (T[]) new Object[10];
		top = -1;
	}
	
	public void push(T element)
	{
		if ((double) size() / (double) getCapacity() >= 0.75 && element instanceof T)
		{
			expandCapacity();
		}
		for (int i = size() -1; i >= 0; i--)
		{
			array[i + 1] = array[i];
		}
		array[0] = element;
		
		++top;
	}
	
	public T pop() throws StackException
	{
		if (isEmpty()) {
			throw new StackException("Stack is empty");
		}
		
		if ((double) size() / (double) getCapacity() <= 0.25)
		{
			shrinkCapacity();
		}
		
		T val = array[0];
		for (int i = 0; i < size(); i++)
		{
			array[i] = array[i + 1];
		}
		--top;
		
		return val; 
	}
	
	public T peek() throws StackException
	{
		if (isEmpty())
		{
			throw new StackException("Stack is empty");
		}
		
		return array[0]; 
	}
	
	public boolean isEmpty()
	{
		boolean empty = false;
		
		if (top == -1)
		{
			empty = true;
		}
		
		return empty;
	}
	
	public int size()
	{
		return top + 1; 
	}
	
	public void clear()
	{
		T[] newArray = (T[]) new Object [10];
		array = newArray;
		top = -1;
	}
	
	public int getCapacity()
	{
		return array.length;
	}
	
	public int getTop()
	{
		return top;
	}
	
	public String toString()
	{
		String arrayString = "Stack: ";
		
		if (isEmpty())
		{
			return ("Empty stack.");
		}
		
		for (int i = 0; i < size(); i++)
		{
			if (i == size() -1)
			{
				arrayString += (array[i] + ".");
			}
			
			else
			{
				arrayString += (array[i] + ", ");
			}
		}
		return arrayString;
	}
	
	private void expandCapacity()
	{
		if ((double) size() / (double) getCapacity() >= 0.75)
		{
			int currentCapacity = getCapacity();
			int newCapacity = getCapacity() + 10;
			T[] newArray = (T[]) new Object [newCapacity];
			
			for (int i = 0; i < currentCapacity; i++)
			{
				newArray[i] = array[i];
			}
			
			array = newArray;
		}
	}
	
	private void shrinkCapacity()
	{
		if ((double) size() / (double) getCapacity() <= 0.25 && getCapacity() >= 20)
		{
			int newCapacity = getCapacity() - 10;
			T[] newArray = (T[]) new Object [newCapacity];
			
			for (int i = 0; i < newCapacity; i++)
			{
				newArray[i] = array[i];
			}
			
			array = newArray;
		}
	}
	}
