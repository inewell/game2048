import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.*;

public abstract class ControlGame extends JFrame implements KeyListener
{
  private int gridSide = 600;
  private int margin = gridSide/30;
  private int boxSide = (gridSide - 5*margin)/4;
  private Font font = new Font("Serif", Font.PLAIN, 45);
  private int scoreHeight = 40;
  private Color bgColor;

  private GridGame grid;

  public ControlGame(GridGame grid, Color bgColor)
  {
    this.grid = grid;
    this.bgColor = bgColor;
    setSize(gridSide,gridSide+20+scoreHeight);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    addKeyListener(this);
  }

  public abstract Color boxColor(int n);

  public abstract Color fontColor(int n);

  public void drawCenteredString(Graphics g, String text, int xCoord, int yCoord, int w, int h, Font font)
  {
    // Get the FontMetrics
    FontMetrics metrics = g.getFontMetrics(font);
    // Determine the X coordinate for the text
    int x = xCoord + ((w - metrics.stringWidth(text)) / 2);
    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
    int y = yCoord + ((h - metrics.getHeight()) / 2) + metrics.getAscent();
    // Set the font
    g.setFont(font);
    // Draw the String
    g.drawString(text, x, y);
  }

  public void displayScore(Graphics g)
  {
    Font scoreFont = new Font("Serif", Font.PLAIN, 32);
    g.setColor(Color.WHITE);
    g.fillRect(0, 20+gridSide, gridSide, scoreHeight);

    g.setColor(Color.BLACK);
    drawCenteredString(g, "SCORE: "+grid.getScore(), 0, 20+gridSide, gridSide/2, scoreHeight, scoreFont);
    drawCenteredString(g, "BEST: "+GridGame.getBest(), gridSide/2, 20+gridSide, gridSide/2, scoreHeight, scoreFont);
  }

  public void paint(Graphics g)
  {
    g.setColor(bgColor);
    g.fillRect(0, 20, gridSide, gridSide);

    int xLoc = margin;
    int yLoc = 20+margin;
    for (int[] r : grid.getEntries())
    {
      for (int c : r)
      {
        g.setColor(boxColor(c));
        g.fillRect(xLoc, yLoc, boxSide, boxSide);
        if (c != 0)
        {
          g.setColor(fontColor(c));
          drawCenteredString(g, c+"", xLoc, yLoc, boxSide, boxSide, font);
        }
        xLoc += boxSide+margin;
      }
      xLoc = margin;
      yLoc += boxSide+margin;
    }

    displayScore(g);

    if (grid.gameOver())
    {
      JOptionPane.showMessageDialog(null,"Game Over", "", JOptionPane.PLAIN_MESSAGE);
    }
  }

  public void keyPressed(KeyEvent e)
  {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_UP)
    {
      grid.swipeUp();
      repaint();
    }
    else if (code == KeyEvent.VK_DOWN)
    {
      grid.swipeDown();
      repaint();
    }
    else if (code == KeyEvent.VK_LEFT)
    {
      grid.swipeLeft();
      repaint();
    }
    else if (code == KeyEvent.VK_RIGHT)
    {
      grid.swipeRight();
      repaint();
    }
  }

  public void keyTyped(KeyEvent e)
  {

  }

  public void keyReleased(KeyEvent e)
  {

  }
}
