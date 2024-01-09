//package Editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class TextEditor extends JFrame implements ActionListener 
{

    JTextArea t;
    JFrame f;

    public TextEditor() 
    {
        f = new JFrame("Editor");
        t = new JTextArea();
        JMenuBar mb = new JMenuBar();

        JMenu m1 = new JMenu("File");
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi4 = new JMenuItem("Print");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi4);

        JMenu m2 = new JMenu("Edit");
        JMenuItem mi5 = new JMenuItem("Cut");
        JMenuItem mi6 = new JMenuItem("Copy");
        JMenuItem mi7 = new JMenuItem("Paste");

        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);

        m2.add(mi5);
        m2.add(mi6);
        m2.add(mi7);

        JMenuItem mi = new JMenuItem("Close");
        mi.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(mi);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String s = e.getActionCommand();
        if (s.equals("Cut")) 
        {
            t.cut();
        } 
        else if (s.equals("Copy")) 
        {
            t.copy();
        } 
        else if (s.equals("Paste")) 
        {
            t.paste();
        } 
        else if (s.equals("Save")) 
        {
            JFileChooser c = new JFileChooser("f:");
            int r = c.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) 
            {
                File f1 = new File(c.getSelectedFile().getAbsolutePath());
                try 
                {
                    FileWriter fw = new FileWriter(f1, false);
                    BufferedWriter w = new BufferedWriter(fw);
                    w.write(t.getText());
                    w.flush();
                    w.close();
                } 
                catch (Exception exc) 
                {
                    JOptionPane.showMessageDialog(f, exc.getMessage());
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(f, "Cancelled Operation!!!");
            }
        } 
        else if (s.equals("Print")) 
        {
            try 
            {
                t.print();
            } 
            catch (Exception ex) 
            {
                System.out.println(ex.toString());
            }
        } 
        else if (s.equals("Open")) 
        {
            JFileChooser c = new JFileChooser("f:");
            int r = c.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) 
            {
                File f1 = new File(c.getSelectedFile().getAbsolutePath());
                try 
                {
                    String s1 = "", sl = "";
                    FileReader fr = new FileReader(f1);
                    BufferedReader br = new BufferedReader(fr);
                    sl = br.readLine();
                    while ((s1 = br.readLine()) != null) 
                    {
                        sl = sl + "\n" + s1;
                    }
                    t.setText(sl);
                } 
                catch (Exception exc1) 
                {
                    JOptionPane.showMessageDialog(f, exc1.getMessage());
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(f, "Cancelled Operation!!!");
            }
        } 
        else if (s.equals("New")) 
        {
            t.setText("");
        } 
        else if (s.equals("Close")) 
        {
            f.setVisible(false);
        }
    }

    public static void main(String[] args) 
    {
        TextEditor editor = new TextEditor();
    }
}
