import javax.swing.*;
import java.awt.*;

public class start{
    JFrame frame;
    JButton button;
    public start()
    {
        frame = new JFrame();
        button = new JButton("CLICK");
        InitialiseFrame();

    }

    public void InitialiseFrame()
    {
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridBagLayout());
        frame.add(button, new GridBagLayout());

        frame.setVisible(true);
    }
}