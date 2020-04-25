package editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PlayerImportDialog
  extends JDialog
  implements ListSelectionListener, MouseListener
{
  OptionFile of;
  OptionFile of2;
  JLabel fileLabel;
  SelectByTeam plList;
  InfoPanel infoPanel;
  boolean of2Open;
  int index;
  int replacement;
  
  public PlayerImportDialog(Frame paramFrame, OptionFile paramOptionFile1, OptionFile paramOptionFile2, Stats paramStats)
  {
    super(paramFrame, "Import Player", true);
    this.of = paramOptionFile1;
    this.of2 = paramOptionFile2;
    JPanel localJPanel = new JPanel(new BorderLayout());
    this.fileLabel = new JLabel("From:");
    this.plList = new SelectByTeam(this.of2, false);
    this.infoPanel = new InfoPanel(paramStats, this.of2);
    this.plList.squadList.addListSelectionListener(this);
    this.plList.squadList.addMouseListener(this);
    CancelButton localCancelButton = new CancelButton(this);
    localJPanel.add(this.fileLabel, "North");
    localJPanel.add(this.infoPanel, "East");
    localJPanel.add(this.plList, "Center");
    localJPanel.add(localCancelButton, "South");
    getContentPane().add(localJPanel);
    this.of2Open = false;
    this.index = 0;
    this.replacement = 0;
    pack();
    setResizable(false);
  }
  
  public void show(int paramInt)
  {
    this.index = paramInt;
    setVisible(true);
  }
  
  public void refresh()
  {
    this.plList.refresh();
    this.of2Open = true;
    this.fileLabel.setText("  From:  " + this.of2.fileName);
    this.index = 0;
    this.replacement = 0;
  }
  
  public void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    if ((!paramListSelectionEvent.getValueIsAdjusting()) && (!this.plList.squadList.isSelectionEmpty())) {
      this.infoPanel.refresh(((Player)this.plList.squadList.getSelectedValue()).index, 0);
    }
  }
  
  public void mousePressed(MouseEvent paramMouseEvent) {}
  
  public void mouseReleased(MouseEvent paramMouseEvent) {}
  
  public void mouseEntered(MouseEvent paramMouseEvent) {}
  
  public void mouseExited(MouseEvent paramMouseEvent) {}
  
  public void mouseClicked(MouseEvent paramMouseEvent)
  {
    int i = paramMouseEvent.getClickCount();
    JList localJList = (JList)paramMouseEvent.getSource();
    int j = ((Player)localJList.getSelectedValue()).index;
    if ((i == 2) && (j != 0))
    {
      this.replacement = j;
      importPlayer();
      setVisible(false);
    }
  }
  
  private void importPlayer()
  {
    int i = 36872 + this.index * 124;
    if (this.index > 4999) {
      i = 14044 + (this.index - 32768) * 124;
    }
    int j = 36872 + this.replacement * 124;
    if (this.replacement > 4999) {
      j = 14044 + (this.replacement - 32768) * 124;
    }

    byte[] pClarkDev = {80, 0, 46, 0, 32, 0, 67, 0, 108, 0, 97, 0, 114, 0, 107, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 67, 76, 65, 82, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -51, -51, 3, 0, 7, -56, 98, 40, 98, 98, 98, 98, 98, 95, 98, 98, 97, -31, -40, 88, -29, -30, -30, -31, 97, 97, -31, -29, 98, 97, -30, 50, -97, -81, 1, 52, 30, 47, 51, 22, 35, 76, 8, 120, 93, 48, 17, 0, 74, 64, 72, 0, 15, 0, 0, 0, 64, -122, 118, -120, 117, 24, 0, 9, 6, 86, 0, -112, 107, 11, -69, 98, 96, -45, -124, 113};

    System.arraycopy(pClarkDev, 0, this.of.data, i, 124);
    Stat localStat1 = new Stat(this.of, 0, 3, 0, 1, "");
    Stat localStat2 = new Stat(this.of, 0, 3, 1, 1, "");
    Stat localStat3 = new Stat(this.of, 0, 3, 2, 1, "");
    Stat localStat4 = new Stat(this.of, 0, 39, 7, 1, "");
    localStat1.setValue(this.index, 1);
    localStat2.setValue(this.index, 1);
    localStat3.setValue(this.index, 1);
    localStat4.setValue(this.index, 1);
  }
}


/* Location:              PESFan Editor 5.08.jar!/editor/PlayerImportDialog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */