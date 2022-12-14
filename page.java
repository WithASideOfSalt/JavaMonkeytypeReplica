import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

class Page {
    JFrame frame;
    Race start;
    Dictionary<Integer,Character> TimingsAndCharacters;
    ArrayList<Integer> Timings;
    Page()
    {
        frame = new JFrame();
        TimingsAndCharacters = new Hashtable<Integer,Character>();
        Timings = new ArrayList<>();
        start = new Race("Title",frame,TimingsAndCharacters, Timings);
        
        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                start.pane.requestFocus();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        frame.addPropertyChangeListener("title", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(frame.getTitle() == "changed")
                {
                    frame.setVisible(false);
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    RaceFinished();
                }
            }
            
        });
        
    }

    public void RaceFinished(){
        System.out.println(TimingsAndCharacters.toString());
        System.out.println(Timings);
        Results results = new Results("Title", frame);
        results.setTimes(Timings);
        results.setTANDC(TimingsAndCharacters);
        results.show(frame);
    }
}

class Results{
    JTextArea label;
    JButton button;
    Integer currentTime =0;
    int currentPosition =0;
    java.util.Timer timer;

    Dictionary<Integer,Character> TANDC;
    ArrayList<Integer> Times;

    Results(String Title, JFrame frame){
        label = new JTextArea();
        frame.setTitle("");

        button = new JButton();
        button.setBounds(300,300,100,100);
        boolean active = false;
        button.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean activetemp = active;
            if(!active)
            {
                activetemp = true;
                label.setText("");
                for(int i= 0; i < Times.size(); i++)
                {
                    int temp = i;
                    if(TANDC.get(Times.get(temp)) != KeyEvent.VK_BACK_SPACE)
                    {
                        setTimeout(() ->label.setText(label.getText() + TANDC.get(Times.get(temp))), Times.get(i));
                    }
                    else
                    {
                        setTimeout(() ->label.setText(label.getText().substring(0, label.getText().length() -1)), Times.get(i));
                    }
                }
                activetemp = false;
            } 
        }
        });

        label.setBounds(50,50,200,200);
        frame.add(label);
        frame.add(button);
    }

    public void show(JFrame frame)
    {
        frame.setVisible(true);
    }

    public void setTimes(ArrayList<Integer> Timings)
    {
        Times = Timings;
    }

    public void setTANDC(Dictionary<Integer, Character> TimingsAndCharacters)
    {
        TANDC = TimingsAndCharacters;
    }


    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

}

class Race{
    JTextPane pane;
    //JButton button;
    DefaultStyledDocument doc;
    int currentPosition = 0;
    Timer timer;
    Integer currentTime = 0;

    Style style;

    Race(String Title, JFrame frame, Dictionary<Integer,Character> TimingsAndCharacters, ArrayList<Integer> Timings)
    {
        frame.setTitle(Title);
        frame.setSize(750,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        doc = new DefaultStyledDocument();

        pane = new JTextPane(doc);
        pane.setSize(500,100);
        pane.setLocation((frame.getWidth() - pane.getWidth())/2, (frame.getHeight() - pane.getHeight())/2);
        pane.setEditable(false);
        pane.setText("Hello this is not going well isn't it");
        pane.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
        /*
         * set pane text alligment to middle
         */
        style = pane.addStyle("",null);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), style, false);
         
        pane.setBackground(new Color(240,240,240));
        pane.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                logic(e.getKeyChar(), frame,TimingsAndCharacters, Timings);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        pane.setFocusable(true);
        frame.add(pane);
        timer = new Timer(1, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTime += 10;
            }
        });
        
        /*
        button = new JButton();
        button.setSize(100,100);
        button.setLocation(50,50);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                StyleConstants.setForeground(style, Color.orange);
                pane.getStyledDocument().setCharacterAttributes(currentPosition,1,style, true);
                currentPosition++;
            }
        });
        */
    }

    public void logic(char KeyChar, JFrame frame, Dictionary<Integer, Character> TimingsAndCharacters, ArrayList<Integer> Timings)
    {
        TimingsAndCharacters.put(currentTime, KeyChar);
        Timings.add(currentTime);
        if(!timer.isRunning())
        {
            timer.start();
        }
        if(KeyChar == KeyEvent.VK_BACK_SPACE){
            if(currentPosition == 0){
                //stops from breaking
            }
            else{
                currentPosition--;
                Style style = pane.addStyle("", null);
                StyleConstants.setForeground(style, Color.black);
                pane.getStyledDocument().setCharacterAttributes(currentPosition,1,style, true);
            }
        }
        else if((KeyChar == KeyEvent.VK_SPACE || KeyChar == KeyEvent.VK_ENTER) && currentPosition == pane.getText().length()){
            System.out.println("END");
            frame.setTitle("changed");
        }
        else if(KeyChar == pane.getText().charAt(currentPosition)){
            Style style = pane.addStyle("", null);
            StyleConstants.setForeground(style, Color.green);
            pane.getStyledDocument().setCharacterAttributes(currentPosition,1,style, true);
            currentPosition++;
            if(currentPosition == pane.getText().length())
            {
                System.out.println();
                frame.setTitle("changed");
            }
        }
        else if(KeyChar != pane.getText().charAt(currentPosition)){        
            Style style = pane.addStyle("", null);
            StyleConstants.setForeground(style, Color.red);
            pane.getStyledDocument().setCharacterAttributes(currentPosition,1,style, true);
            currentPosition++;
        }
    }
}
class caller{
    public static void main(String[] args)
    {
        Page go = new Page();
        go.frame.setVisible(true);
        go.start.pane.requestFocus();
    }
}