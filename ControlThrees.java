import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.*;

public class ControlThrees extends ControlGame
{
  public ControlThrees()
  {
    super(new GridThrees(), new Color(177, 200, 193));
  }

  public Color boxColor(int n)
  {
    if (n == 0)
      return new Color(161, 180, 180);
    else if (n == 1)
      return new Color(112, 204, 255);
    else if (n == 2)
      return new Color(242, 87, 146);
    else
      return Color.WHITE;
  }

  public Color fontColor(int n)
  {
    if (n == 1 || n == 2)
      return Color.WHITE;
    else
      return Color.BLACK;
  }

  public static void main(String[] args)
  {
    ControlThrees c = new ControlThrees();
  }
}
