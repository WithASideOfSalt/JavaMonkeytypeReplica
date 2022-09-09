import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat.Style;

public class start extends JFrame implements ActionListener{
    JEditorPane pane;
    JButton btn;
    int currentPosition = 0;
    
    public start()
    {
        pane = new JEditorPane();
        btn = new JButton();
        InitialiseFrame();

    }

    public void InitialiseFrame()
    {
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int btHeight = 100;
        int btWidth = 100;

        pane.setBounds((this.getWidth() - btWidth)/2,(this.getHeight() - btHeight)/2,btWidth,btHeight);
        pane.setText("Hello");

        btn.addActionListener(this);

        setLayout(null);
        add(pane);
    }

    public void visible()
    {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //pane.
    }
}