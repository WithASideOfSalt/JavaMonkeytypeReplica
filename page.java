import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.text.*;
//import java.awt.Color;
import java.awt.event.*;
class Page extends JFrame{
    JTextPane pane;
    //JButton button;
    DefaultStyledDocument doc;
    int currentPosition = 0;

    Style style;

    Page(String Title)
    {
        super(Title);
        setSize(750,500);
        setLayout(new GridLayout());
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                pane.requestFocus();
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

        doc = new DefaultStyledDocument();

        pane = new JTextPane(doc);
        pane.setBounds(50,50,300,200);
        pane.setEditable(false);
        pane.setText("Hello");
        pane.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                logic(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE)
                {
                    //backspace();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        pane.setFocusable(true);
        add(pane);
        
        /*button = new JButton();
        //button.setSize(100,100);
        //button.setLocation(50,50);
        //button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                StyleConstants.setForeground(style, Color.orange);
                pane.getStyledDocument().setCharacterAttributes(currentPosition,1,style, true);
                currentPosition++;
            }
        });*/
    }

    public void logic(char KeyChar)
    {
        
        if(KeyChar == KeyEvent.VK_BACK_SPACE){
            if(currentPosition == 0)
            {
                //stops from breaking
            }
            else{
                currentPosition--;
                Style style = pane.addStyle("", null);
                StyleConstants.setForeground(style, Color.black);
                pane.getStyledDocument().setCharacterAttributes(currentPosition,1,style, true);
            }
        }
        else if(KeyChar == pane.getText().charAt(currentPosition)){
            Style style = pane.addStyle("", null);
            StyleConstants.setForeground(style, Color.green);
            pane.getStyledDocument().setCharacterAttributes(currentPosition,1,style, true);
            currentPosition++;
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
        Page go = new Page("Experiment");
        go.setVisible(true);
        go.pane.requestFocus();
    }
}