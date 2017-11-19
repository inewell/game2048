import java.lang.Math;

public class GridThrees extends GridGame
{
  public GridThrees()
  {
    super();
    for (int i = 1; i <= 9; i++)
    {
      this.addRandom();
    }
    super.setFileName("bestThrees.txt");
  }

  public int calcScore(GridThrees grid)
  {
    int total = 0;
    for (int[] r : grid.getEntries())
      for (int c : r)
        if (c >= 3)
          total += (int)(Math.pow(3, (Math.log(2*c/3)/Math.log(2))));
    return total;
  }

  //returns 1, 2, or 3 using a weighted probability value
  public int numToSpawn()
  {
    double p = Math.random();
    if (p < 1.0/3)
      return 1;
    else if (p > 2.0/3)
      return 2;
    else
      return 3;
  }

  //returns whether or not two cells can merge
  public boolean canMerge(int a, int b)
  {
    //if one value is 1 and the other is 2, merge
    if (a == 1 && b == 2)
      return true;
    else if (a == 2 && b == 1)
      return true;
    //if both values are 1 or both values are 2, do not merge
    //if they are otherwise equal, merge
    else if (a == b)
      return (!(a == 1 || a == 2));
    else
      return false;
  }

  //performs a swipe up move
  public void swipeUp()
  {
    boolean spawn = false;

    for (int col = 0; col < 4; col++) //goes through the columns
    {
      for (int row = 0; row < 3; row++) //goes through the rows
      {
        if (getEntries()[row][col] == 0) //if you reach a zero cell,
        {
          for (int k = row; k < 3; k++) //shift subsequent cells upward, then break.
          {
            if (getEntries()[k+1][col] != 0)
              spawn = true;
            setEntry(getEntries()[k+1][col], k, col);
          }
          setEntry(0, 3, col);
          break;
        }
        else //if nonzero entry
        {
          if (canMerge(getEntries()[row][col], getEntries()[row+1][col])) //if it can be merged with the cell below,
          {
            setEntry(getEntries()[row][col] + getEntries()[row+1][col], row, col); //complete the merge

            for (int k = row+1; k < 3; k++) //shift subsequent cells upward, then break.
            {
              setEntry(getEntries()[k+1][col], k, col);
            }
            setEntry(0, 3, col);
            spawn = true;
            break;
          }
        }
      }
    }

    if (spawn)
      this.addRandom();

    setScore(calcScore(this));

    if (getScore() > getBest())
      setBest(getScore());
  }

  //performs a swipe down move
  public void swipeDown()
  {
    boolean spawn = false;

    for (int col = 0; col < 4; col++)
    {
      for (int row = 3; row > 0; row--)
      {
        if (getEntries()[row][col] == 0)
        {
          for (int k = row; k > 0; k--)
          {
            if (getEntries()[k-1][col] != 0)
              spawn = true;
            setEntry(getEntries()[k-1][col], k, col);
          }
          setEntry(0, 0, col);
          break;
        }
        else
        {
          if (canMerge(getEntries()[row][col], getEntries()[row-1][col]))
          {
            setEntry(getEntries()[row][col] + getEntries()[row-1][col], row, col);

            for (int k = row-1; k > 0; k--)
            {
              setEntry(getEntries()[k-1][col], k, col);
            }
            setEntry(0, 0, col);
            spawn = true;
            break;
          }
        }
      }
    }

    if (spawn)
      this.addRandom();

    setScore(calcScore(this));

    if (getScore() > getBest())
      setBest(getScore());
  }

  //performs a swipe left move
  public void swipeLeft()
  {
    boolean spawn = false;

    for (int row = 0; row < 4; row++)
    {
      for (int col = 0; col < 3; col++)
      {
        if (getEntries()[row][col] == 0)
        {
          for (int k = col; k < 3; k++)
          {
            if (getEntries()[row][k+1] != 0)
              spawn = true;
            setEntry(getEntries()[row][k+1], row, k);
          }
          setEntry(0, row, 3);
          break;
        }
        else
        {
          if (canMerge(getEntries()[row][col], getEntries()[row][col+1]))
          {
            setEntry(getEntries()[row][col] + getEntries()[row][col+1], row, col);

            for (int k = col+1; k < 3; k++)
            {
              setEntry(getEntries()[row][k+1], row, k);
            }
            setEntry(0, row, 3);
            spawn = true;
            break;
          }
        }
      }
    }

    if (spawn)
      this.addRandom();

    setScore(calcScore(this));

    if (getScore() > getBest())
      setBest(getScore());
  }

  //performs a swipe right move
  public void swipeRight()
  {
    boolean spawn = false;

    for (int row = 0; row < 4; row++)
    {
      for (int col = 3; col > 0; col--)
      {
        if (getEntries()[row][col] == 0)
        {
          for (int k = col; k > 0; k--)
          {
            if (getEntries()[row][k-1] != 0)
              spawn = true;
            setEntry(getEntries()[row][k-1], row, k);
          }
          setEntry(0, row, 0);
          break;
        }
        else
        {
          if (canMerge(getEntries()[row][col], getEntries()[row][col-1]))
          {
            setEntry(getEntries()[row][col] + getEntries()[row][col-1], row, col);

            for (int k = col-1; k > 0; k--)
            {
              setEntry(getEntries()[row][k-1], row, k);
            }
            setEntry(0, row, 0);
            spawn = true;
            break;
          }
        }
      }
    }

    if (spawn)
      this.addRandom();

    setScore(calcScore(this));

    if (getScore() > getBest())
      setBest(getScore());
  }

  public static void main(String[] args)
  {
    GridThrees g = new GridThrees();
    g.print();
    for (int i = 1; i <= 10; i++)
    {
      g.swipeRight();
      g.print();
    }
  }
}
