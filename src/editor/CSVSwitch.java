package editor;

import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CSVSwitch
  extends JPanel
{
  JCheckBox extra = new JCheckBox("Extra Players");
  JCheckBox create = new JCheckBox("Created Players");
  
  public CSVSwitch()
  {
    super(new GridLayout(0, 1));
    extra.setSelected(true);
    create.setSelected(true);
    add(this.extra);
    add(this.create);
  }
}


/* Location:              PESFan Editor 5.08.jar!/editor/CSVSwitch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */