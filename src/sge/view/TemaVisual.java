package sge.view;
import javax.swing.*;
import java.awt.*;
/** Tema visual comum para os módulos do SGE. */
public final class TemaVisual {
 public static final Color MARINHO=new Color(68,82,97), AZUL=new Color(109,142,174), DESTAQUE=new Color(113,163,153), FUNDO=new Color(250,251,253), TEXTO=new Color(48,60,72);
 private TemaVisual(){}
 public static void aplicar(){
  Font f=new Font("Segoe UI",Font.PLAIN,14);
  for(String n:new String[]{"Label","TextField","PasswordField","TextArea","ComboBox","Table","TableHeader","Button","CheckBox","RadioButton","OptionPane","TabbedPane","List","Spinner","Menu","MenuItem"})UIManager.put(n+".font",f);
  UIManager.put("Button.font",f.deriveFont(Font.BOLD));
  UIManager.put("Panel.background",FUNDO);UIManager.put("Viewport.background",FUNDO);
  UIManager.put("Table.background",Color.WHITE);UIManager.put("Table.foreground",TEXTO);
  UIManager.put("Table.selectionBackground",new Color(232,240,248));UIManager.put("Table.selectionForeground",MARINHO);
  UIManager.put("Table.gridColor",new Color(228,234,241));UIManager.put("Table.rowHeight",33);
  UIManager.put("TableHeader.background",new Color(239,243,247));UIManager.put("TableHeader.foreground",MARINHO);
  UIManager.put("TextField.background",Color.WHITE);UIManager.put("TextField.foreground",TEXTO);
  UIManager.put("PasswordField.background",Color.WHITE);UIManager.put("PasswordField.foreground",TEXTO);
  UIManager.put("ComboBox.background",Color.WHITE);UIManager.put("Button.background",new Color(235,240,246));
  UIManager.put("Button.foreground",TEXTO);
  UIManager.put("ToolTip.background",new Color(240,253,250));UIManager.put("ToolTip.foreground",MARINHO);
 }
}
