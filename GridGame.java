import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class GridGame
{
  private int[][] entries;
  private int score;

  private static String fileName;

  public GridGame()
  {
    entries = new int[4][4];
  }

  //accessor methods
  public int[][] getEntries()
  {
    return entries;
  }

  public int getScore()
  {
    return score;
  }

  //setter methods
  public void setEntry(int e, int r, int c)
  {
    entries[r][c] = e;
  }

  public void setScore(int s)
  {
    score = s;
  }

  public void addToScore(int x)
  {
    score += x;
  }

  public void addToScore(int row, int col)
  {
    score += entries[row][col];
  }

  public void doubleEntry(int r, int c)
  {
    entries[r][c] *= 2;
  }

  public static void setFileName(String fn)
  {
    fileName = fn;
  }

  //TEXT FILE METHODS
  public static void setBest(int n)
  {
    try
    {
      FileWriter fw = new FileWriter(fileName);
      PrintWriter pw = new PrintWriter(fw);

      pw.println(n);

      pw.close();
    }
    catch (IOException e)
    {

    }
  }

  public static int getBest()
  {
    try
    {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);

      String str;
      while ((str = br.readLine()) != null)
      {
        return Integer.parseInt(str);
      }
      return 0;
    }
    catch (IOException e)
    {
      return 0;
    }
  }

  //returns the number of empty cells (equal to 0) in entries
  private int numEmpty()
  {
    int count = 0;
    for (int[] r : entries)
      for (int c : r)
        if (c == 0)
          count++;
    return count;
  }

  //sets a randomly selected empty entry to either 2 or 4 (90% weight on 2)
  public void addRandom()
  {
    int numEmpty = this.numEmpty();
    int emptyIndex = (int)(Math.random()*numEmpty); //randomly selected empty entry

    int c = 0;
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        if (entries[i][j] == 0)
        {
          if (c == emptyIndex)
          {
            entries[i][j] = numToSpawn(); //once the emptyIndex th random entry is set to value,
            return; //break out of the method
          }
          c++;
        }
      }
    }
  }

  public boolean gameOver()
  {
    if (this.numEmpty() == 0)
    {
      for (int row = 0; row < 4; row++)
      {
        for (int col = 0; col < 3; col++)
        {
          if (canMerge(entries[row][col], entries[row][col+1]))
            return false;
        }
      }

      for (int col = 0; col < 4; col++)
      {
        for (int row = 0; row < 3; row++)
        {
          if (canMerge(entries[row][col], entries[row+1][col]))
            return false;
        }
      }
      return true;
    }
    else
      return false;
  }

  //prints out the grid, good for testing
  public void print()
  {
    for (int[] r : entries)
    {
      for (int c : r)
        System.out.print(c + " ");
      System.out.println();
    }
    System.out.println();
  }

  public abstract int numToSpawn();

  public abstract boolean canMerge(int a, int b);

  //Swipe methods
  public abstract void swipeUp();

  public abstract void swipeDown();

  public abstract void swipeLeft();

  public abstract void swipeRight();
}
