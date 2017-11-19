import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JOptionPane;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;

public class Control2048 extends ControlGame
{
  public Control2048()
  {
    super(new Grid2048(), new Color(166, 151, 134));
  }

  public Color boxColor(int n)
  {
    Color color;
    switch (n)
    {
      case 0:
        color = new Color(212, 204, 196);
        break;
      case 2:
        color = new Color(248, 242, 237);
        break;
      case 4:
        color = new Color(255, 247, 221);
        break;
      case 8:
        color = new Color(255, 196, 144);
        break;
      case 16:
        color = new Color(255, 166, 116);
        break;
      case 32:
        color = new Color(255, 135, 112);
        break;
      case 64:
        color = new Color(255, 93, 64);
        break;
      case 128:
        color = new Color(255, 229, 129);
        break;
      case 256:
        color = new Color(255, 231, 110);
        break;
      case 512:
        color = new Color(255, 220, 81);
        break;
      case 1024:
        color = new Color(255, 219, 64);
        break;
      case 2048:
        color = new Color(255, 211, 14);
        break;
      default:
        color = Color.BLACK;
        break;
    }
    return color;
  }

  public Color fontColor(int n)
  {
    if (n == 2 || n == 4)
      return new Color(95, 88, 85);
    else
      return Color.WHITE;
  }

  public static void main(String[] args)
  {
    //setFileName("best2048.txt");
    Control2048 c = new Control2048();
    //c.setFileName("best2048.txt");
  }
}
