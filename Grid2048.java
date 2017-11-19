import java.lang.Math;

public class Grid2048 extends GridGame
{
  //constructor initializes getEntries() and sets two distinct, randomly picked squares to 2 or 4
  public Grid2048()
  {
    super();
    for (int i = 1; i <= 2; i++)
    {
      this.addRandom();
    }
    super.setFileName("best2048.txt");
  }

  //returns a randomly selected number between 2 and 4 (90% weight on 2)
  public int numToSpawn()
  {
    //selects the value to spawn with 9:1 ratio of of 2s to 4s
    double p = Math.random();
    if (p < 0.1)
      return 4;
    else
      return 2;
  }

  //determines whether two getEntries() can merge
  public boolean canMerge(int a, int b)
  {
    return a == b;
  }

  //performs a swipe up move
  public void swipeUp()
  {
    boolean spawn = false; //whether or not to spawn a new entry

    for (int col = 0; col < 4; col++)
    {
      //compresses all nonzero getEntries() over to the top
      int nextNonzeroRow = 0;
      for (int row = 0; row < 4; row++)
      {
        if (getEntries()[row][col] != 0)
        {
          setEntry(getEntries()[row][col], nextNonzeroRow, col);
          if (row != nextNonzeroRow)
          {
            spawn = true; //if a tile is moved, then spawn
            setEntry(0, row, col);
          }
          nextNonzeroRow++;
        }
      }

      //looks for adjacent pairs to merge
      for (int row = 0; row < 3; row++)
      {
        if (getEntries()[row][col] == getEntries()[row+1][col] && getEntries()[row][col] != 0)
        {
          spawn = true; //if tiles are merged, then spawn
          doubleEntry(row, col); //complete the merge,
          addToScore(row, col);
          for (int k = row+1; k < 3; k++) //then shift the subsequent tiles up 1
          {
            setEntry(getEntries()[k+1][col], k, col);
          }
          setEntry(0, 3, col);
        }
      }
    }

    if (spawn) //if it should spawn a new entry, call addRandom()
      this.addRandom();

    if (getScore() > getBest())
      setBest(getScore());
  }

  //performs a swipe down move
  public void swipeDown()
  {
    boolean spawn = false; //whether or not to spawn a new entry

    for (int col = 0; col < 4; col++)
    {
      //compresses all nonzero getEntries() over to the bottom
      int nextNonzeroRow = 3;
      for (int row = 3; row > -1; row--)
      {
        if (getEntries()[row][col] != 0)
        {
          setEntry(getEntries()[row][col], nextNonzeroRow, col);
          if (row != nextNonzeroRow)
          {
            spawn = true; //if a tile is moved, then spawn
            setEntry(0, row, col);
          }
          nextNonzeroRow--;
        }
      }

      //looks for adjacent pairs to merge
      for (int row = 3; row > 0; row--)
      {
        if (getEntries()[row][col] == getEntries()[row-1][col] && getEntries()[row][col] != 0)
        {
          spawn = true; //if tiles are merged, then spawn
          doubleEntry(row, col); //complete the merge,
          addToScore(row, col);
          for (int k = row-1; k > 0; k--) //then shift the subsequent tiles down 1
          {
            setEntry(getEntries()[k-1][col], k, col);
          }
          setEntry(0, 0, col);
        }
      }
    }

    if (spawn) //if it should spawn a new entry, call addRandom()
      this.addRandom();

    if (getScore() > getBest())
      setBest(getScore());
  }

  //performs a swipe left move
  public void swipeLeft()
  {
    boolean spawn = false;

    for (int row = 0; row < 4; row++)
    {
      //compresses all nonzero getEntries() over to the left
      int nextNonzeroCol = 0;
      for (int col = 0; col < 4; col++)
      {
        if (getEntries()[row][col] != 0)
        {
          setEntry(getEntries()[row][col], row, nextNonzeroCol);
          if (col != nextNonzeroCol)
          {
            spawn = true; //if a tile is moved, then spawn
            setEntry(0, row, col);
          }
          nextNonzeroCol++;
        }
      }

      //looks for adjacent pairs to merge
      for (int col = 0; col < 3; col++)
      {
        if (getEntries()[row][col] == getEntries()[row][col+1] && getEntries()[row][col] != 0)
        {
          spawn = true; //if tiles are merged, then spawn
          doubleEntry(row, col); //complete the merge,
          addToScore(row, col);
          for (int k = col+1; k < 3; k++) //then shift the subsequent tiles left 1
          {
            setEntry(getEntries()[row][k+1], row, k);
          }
          setEntry(0, row, 3);
        }
      }
    }

    if (spawn) //if it should spawn a new entry, call addRandom()
      this.addRandom();

    if (getScore() > getBest())
      setBest(getScore());
  }

  //performs a swipe right move
  public void swipeRight()
  {
    boolean spawn = false;

    for (int row = 0; row < 4; row++)
    {
      //compresses all nonzero getEntries() over to the right
      int nextNonzeroCol = 3;
      for (int col = 3; col > -1; col--)
      {
        if (getEntries()[row][col] != 0)
        {
          setEntry(getEntries()[row][col], row, nextNonzeroCol);
          if (col != nextNonzeroCol)
          {
            spawn = true; //if a tile is moved, then spawn
            setEntry(0, row, col);
          }
          nextNonzeroCol--;
        }
      }

      //looks for adjacent pairs to merge
      for (int col = 3; col > 0; col--)
      {
        if (getEntries()[row][col] == getEntries()[row][col-1] && getEntries()[row][col] != 0)
        {
          spawn = true; //if tiles are merged, then spawn
          doubleEntry(row, col); //complete the merge,
          addToScore(row, col);
          for (int k = col-1; k > 0; k--) //then shift the subsequent tiles right 1
          {
            setEntry(getEntries()[row][k-1], row, k);
          }
          setEntry(0, row, 0);
        }
      }
    }

    if (spawn) //if it should spawn a new entry, call addRandom()
      this.addRandom();

    if (getScore() > getBest())
      setBest(getScore());
  }

  //prints out the grid, good for testing
  public void print()
  {
    for (int[] r : getEntries())
    {
      for (int c : r)
        System.out.print(c + " ");
      System.out.println();
    }
    System.out.println();
  }

  //tests swipe methods
  public static void main(String[] args)
  {
    Grid2048 g = new Grid2048();
    g.print();

    for (int i = 1; i <= 10; i++)
    {
      g.swipeUp();
      g.print();
    }
  }
}
