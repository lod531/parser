import java.util.NoSuchElementException;

public class Parser {
	//DISTINCTION BETWEEN HASNEXT AND NEXT IS THAT THE INDEX OF STRING THUS FAR PARSED IS ONLY INCREMENTED BY THE "NEXT" METHODS
	//eg hasnextInt() will return a boolean of whether or not there is a next int, but will find the same next int every time until nextInt() is called
	//which, nextInt(), will increment the index of string thus far parsed.
	//nextInt() returns nextInt, if present.
	//nextDouble() returns nextDouble, if present.
	//nextWord() returns next sequence of uninterrupted chars
	//nextLetter() returns next char
	//nextDigit() returns next digit. eg 01 would return first 0, then 1
	//nextSign() returns an arithmetic sign, if present (+ - / * )
	//hasNextInt() returns a boolean on whether the string has a next unparsed Int
	//hasNextDouble() returns a boolean on whether the string has a next unparsed Double
	//hasNextLetter() returns a boolean on whether the string has a next unparsed char
	//indexOfNextInt() returns the index of the next unparsed int, otherwise -1
	//reset() resets the index, allowing for parsing anew without recreating object
	private final char[] userInputChars;
	private int index;
	
	public Parser(String inputString)
	{
		userInputChars = inputString.toCharArray();
		index = 0;
	}
	
	public int nextInt()
	//nextInt() prints error message and outputs -1 if no int is present.
	{
		try
		{
			int indexOffset = 0;
			boolean negative = false;
			while(index + indexOffset < userInputChars.length)
			{
				if(userInputChars[index + indexOffset] == '-')
				{
					negative = !negative;
				}
				if(Character.isDigit(userInputChars[index + indexOffset]))
				{
					int currentValue = Character.getNumericValue(userInputChars[index + indexOffset]);
					if( (index + 1) < userInputChars.length)
					{
						indexOffset++;
						while(((index + indexOffset) < userInputChars.length) && Character.isDigit(userInputChars[index + indexOffset]) )			//important to check if within array
						{																																//before checking if isDigit
							currentValue *= 10;
							currentValue += Character.getNumericValue(userInputChars[index + indexOffset]);
							indexOffset++;
						}
					}	
					index += indexOffset;
					return currentValue*((negative)? -1:1);
				}	
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}												
				}
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return -1;					//this return is here just to get this method to compile.
	}
	
	public double nextDouble()
	{
		int indexOffset = 0;
		boolean negative = false;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(userInputChars[index + indexOffset] == '-')
				{
					negative = !negative;
				}
				if(Character.isDigit(userInputChars[index + indexOffset]))
				{
					double currentValue = Character.getNumericValue(userInputChars[index + indexOffset]);
					if( (index + 1) < userInputChars.length)
					{
						indexOffset++;
						while(((index + indexOffset) < userInputChars.length) && Character.isDigit(userInputChars[index + indexOffset]) )			//important to check if within array
						{																																//before checking if isDigit
							currentValue *= 10;
							currentValue += Character.getNumericValue(userInputChars[index + indexOffset]);
							indexOffset++;
						}
						if(((index + indexOffset) < userInputChars.length) && userInputChars[index + indexOffset] == '.')
						{
							indexOffset++;
							double tempFractionValue = Character.getNumericValue(userInputChars[index + indexOffset]);
							indexOffset++;
							while(((index + indexOffset) < userInputChars.length) && Character.isDigit(userInputChars[index + indexOffset]) )			//important to check if within array
							{																																//before checking if isDigit
								tempFractionValue *= 10;
								tempFractionValue += Character.getNumericValue(userInputChars[index + indexOffset]);
								indexOffset++;
							}
							while(tempFractionValue >= 1)
							{
								tempFractionValue *= 0.1;
							}
							currentValue += tempFractionValue;
						}
					}	
					index += indexOffset;
					return currentValue*((negative)? -1:1);
				}	
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return -1;								//this return is here just to get it to compile
	}
	
	public char nextLetter()
	{
		int indexOffset = 0;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isLetter(userInputChars[index + indexOffset]))
				{
					indexOffset++;
					index += indexOffset;
					return userInputChars[index-1];
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return ' ';								//this return is here just to get it to compile
	}
	
	public char nextDigit()
	{
		int indexOffset = 0;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isDigit(userInputChars[index + indexOffset]))
				{
					indexOffset++;
					index += indexOffset;
					return userInputChars[index-1];
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return ' ';								//this return is here just to get it to compile
	}
	

	public String nextWord()			
	{
		int indexOffset = 0;
		String returnString = "";
		while(index + indexOffset < userInputChars.length)
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isLetter(userInputChars[index + indexOffset]))
				{
					returnString += userInputChars[index + indexOffset];
					indexOffset++;
					while(((index + indexOffset) < userInputChars.length) && Character.isLetter(userInputChars[index + indexOffset]) )
					{
						returnString += userInputChars[index + indexOffset];
						indexOffset++;
					}
					index += indexOffset;
					return returnString;
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		return "";
	}
	
	public char nextSign()
	{
		int indexOffset = 0;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(userInputChars[index + indexOffset] == '+' || userInputChars[index + indexOffset] == '-' || 
						userInputChars[index + indexOffset] == '/' || userInputChars[index + indexOffset] == '*')
				{
					indexOffset++;
					index += indexOffset;
					return userInputChars[index-1];
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return ' ';								//this return is here just to get it to compile
	}
	
	public boolean hasNextInt()
	{
		try
		{
			int indexOffset = 0;
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isDigit(userInputChars[index + indexOffset]))
				{
					if( (index + 1) < userInputChars.length)
					{
						indexOffset++;
						while(((index + indexOffset) < userInputChars.length) && Character.isDigit(userInputChars[index + indexOffset]) )			//important to check if within array
						{																																//before checking if isDigit
							indexOffset++;
						}
						if(((index + indexOffset) < userInputChars.length) && userInputChars[index + indexOffset] == '.')
						{
							return false;
						}
						else
						{
							return true;
						}
					}
					else
					{
						return true;
					}
				}	
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}												
				}
			}
		}
		catch(NoSuchElementException exception)
		{
			return false;
		}
		return false; //this return is here just to get this method to compile.
	}
	
	boolean hasNextDouble()
	{
		try
		{
			int indexOffset = 0;
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isDigit(userInputChars[index + indexOffset]))
				{
					if( (index + 1) < userInputChars.length)
					{
						indexOffset++;
						while(((index + indexOffset) < userInputChars.length) && Character.isDigit(userInputChars[index + indexOffset]) )			//important to check if within array
						{																																//before checking if isDigit
							indexOffset++;
						}
						if(((index + indexOffset) < userInputChars.length) && userInputChars[index + indexOffset] == '.')
						{
							indexOffset++;
							if(((index + indexOffset) < userInputChars.length) && Character.isDigit(userInputChars[index + indexOffset]) )
							{
								return true;
							}
						}
						else
						{
							return false;
						}
					}
					else
					{
						return false;
					}
				}	
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}												
				}
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
			return false;
		}
		return false; //this return is here just to get this method to compile.
	}
	
	public boolean hasNextLetter()
	{
		int indexOffset = 0;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isLetter(userInputChars[index + indexOffset]))
				{
					return true;
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return false;								//this return is here just to get it to compile
	}
	
	public boolean hasNextSign()
	{
		int indexOffset = 0;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(userInputChars[index + indexOffset] == '+' || userInputChars[index + indexOffset] == '-' || 
						userInputChars[index + indexOffset] == '/' || userInputChars[index + indexOffset] == '*')
				{
					return true;
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return false;								
	}
	
	public boolean hasNextDigit()
	{
		int indexOffset = 0;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isDigit(userInputChars[index + indexOffset]))
				{
					return true;
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return false;							
	}
	
	public int indexOfNextInt()
	{
		try
		{
			int indexOffset = 0;
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isDigit(userInputChars[index + indexOffset]))
				{
					if( (index + 1) < userInputChars.length)
					{
						indexOffset++;
						while(((index + indexOffset) < userInputChars.length) && Character.isDigit(userInputChars[index + indexOffset]) )			//important to check if within array
						{																																//before checking if isDigit
							indexOffset++;
						}
						if(((index + indexOffset) < userInputChars.length) && userInputChars[index + indexOffset] == '.')
						{
							return -1;
						}
						else
						{
							return index + indexOffset - 1;
						}
					}
					else
					{
						return index + indexOffset - 1;
					}
				}	
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}												
				}
			}
		}
		catch(NoSuchElementException exception)
		{
			return -1;
		}
		return -1;
	}
	
	public int indexOfNextLetter()
	{
		int indexOffset = 0;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isLetter(userInputChars[index + indexOffset]))
				{
					return index + indexOffset;
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return -1;							
	}
	
	public int indexOfNextDigit()
	{
		int indexOffset = 0;
		try
		{
			while(index + indexOffset < userInputChars.length)
			{
				if(Character.isDigit(userInputChars[index + indexOffset]))
				{
					return index + indexOffset;
				}
				else
				{
					if(indexOffset + 1 < userInputChars.length)
					{
						indexOffset++;
					}
					else
					{
						throw new NoSuchElementException();
					}
				}	
			}
		}
		catch(NoSuchElementException exception)
		{
			System.out.println("No such element!");
		}
		return -1;								
	}
	
	public void reset()
	{
		index = 0;
	}
}