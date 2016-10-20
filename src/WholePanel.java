// Assignment #: 7
//         Name: Bailey
//    StudentID: 1208740698
//      Lecture: 2:00 MWF
//  Description: Panel class that uploads eight radio buttons by which the background color and the color of an oval can be chosen.
//              The oval can also be chosen to be filled or unfilled.
//              The selections are displayed in a canvas below the buttons.
//              User can also move the oval by hitting the arrow keys.
//              The B key will make the oval smaller; S will make it larger.
//              The oval will shrink until it becomes 10 pixels across.


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // to use listener interfaces

public class WholePanel extends JPanel
{
    private Color foregroundColor, backgroundColor;
    private boolean isFilled;
    private int currentDiameter, x1, y1;
    private CanvasPanel canvas;
    private JPanel buttonPanel;

    private JRadioButton redRadio, greenRadio, blueRadio, cyanRadio, yellowRadio, pinkRadio, filledRadio, unfilledRadio;

    public WholePanel()
    {
        //initialize variables
        backgroundColor = Color.CYAN;
        foregroundColor = Color.RED;
        isFilled = false;
        currentDiameter = 100;
        x1 = 200; y1 = 100;


        //initialize containers
        buttonPanel = new JPanel();
        canvas = new CanvasPanel();

        //initialize buttons and groups
        redRadio = new JRadioButton("Red", true);
        greenRadio = new JRadioButton("Green");
        blueRadio = new JRadioButton("Blue");

        ButtonGroup group1 = new ButtonGroup();
        group1.add(redRadio);
        group1.add(greenRadio);
        group1.add(blueRadio);

        cyanRadio = new JRadioButton("Cyan", true);
        yellowRadio = new JRadioButton("Yellow");
        pinkRadio = new JRadioButton("pink");

        ButtonGroup group2 = new ButtonGroup();
        group2.add(cyanRadio);
        group2.add(yellowRadio);
        group2.add(pinkRadio);


        unfilledRadio = new JRadioButton("Unfilled", true);
        filledRadio = new JRadioButton("Filled");

        ButtonGroup group3 = new ButtonGroup();
        group3.add(unfilledRadio);
        group3.add(filledRadio);



        //add listeners to buttons
        redRadio.addActionListener(new ColorListener());
        greenRadio.addActionListener(new ColorListener());
        blueRadio.addActionListener(new ColorListener());

        cyanRadio.addActionListener(new ColorListener());
        yellowRadio.addActionListener(new ColorListener());
        pinkRadio.addActionListener(new ColorListener());

        unfilledRadio.addActionListener(new FillListener());
        filledRadio.addActionListener(new FillListener());


        //Label Creation
        JLabel label1 = new JLabel("Foreground Color");
        JLabel label2 = new JLabel("Background Color");
        JLabel label3 = new JLabel("Circle Filling");


        //Organizing ButtonPanel


        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        //buttonpanel components
        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.add(panel1);
        buttonPanel.add(panel2);
        buttonPanel.add(panel3);

        //top of buttonpanel        panel1 components
        panel1.setLayout(new GridLayout(1,4));
        panel1.add(label1);
        panel1.add(redRadio);
        panel1.add(greenRadio);
        panel1.add(blueRadio);

        //middle of buttonpanel     panel2 components
        panel2.setLayout(new GridLayout(1,4));
        panel2.add(label2);
        panel2.add(cyanRadio);
        panel2.add(yellowRadio);
        panel2.add(pinkRadio);

        //bottom of button panel    panel3 components
        panel3.setLayout(new GridLayout(1,3));
        panel3.add(label3);
        panel3.add(unfilledRadio);
        panel3.add(filledRadio);




        //Organize WholePanel
        JSplitPane sPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, canvas);
        setLayout(new BorderLayout());
        add(sPane);
    }

    //Updates color selection according to radio buttons
    private class ColorListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == redRadio) {
                foregroundColor = Color.RED;
            } else if (event.getSource() == greenRadio) {
                foregroundColor = Color.GREEN;
            } else if (event.getSource() == blueRadio){
                foregroundColor = Color.BLUE;

            } else if (event.getSource() == cyanRadio) {
                backgroundColor = Color.CYAN;
            } else if (event.getSource() == yellowRadio) {
                backgroundColor = Color.YELLOW;
            } else {
                backgroundColor = Color.PINK;
            }

        }
    } // end of ColorListener


    //Its description should be completed
    private class FillListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == unfilledRadio) {
                isFilled = false;
            } else if (event.getSource() == filledRadio) {
                isFilled = true;
            }

        }
    }

    //CanvasPanel is the panel where a circle is drawn
    private class CanvasPanel extends JPanel
    {
        //Constructor to initialize the canvas panel
        public CanvasPanel( )
        {
            // make this canvas panel listen to keys
            addKeyListener(new DirectionListener());
            // make this canvas panel listen to mouse
            addMouseListener(new PointListener());

            setBackground(backgroundColor);

            //This method needs to be called for this panel to listen to keys
            //When panel listens to other things, and go back to listen
            //to keys, this method needs to be called again.
            requestFocus();
        }


        //this method draws all characters pressed by a user so far
        public void paintComponent(Graphics page)
        {
            super.paintComponent(page);
            setBackground(backgroundColor);
            setForeground(foregroundColor);

            //draw oval according to fill selection
            if (isFilled)
                page.fillOval(x1, y1, currentDiameter, currentDiameter);
            else
                page.drawOval(x1, y1, currentDiameter, currentDiameter);

            repaint();


        }


        /** This method is overridden to enable keyboard focus */
        public boolean isFocusable()
        {
            return true;
        }

        // listener class to listen to keyboard keys
        private class DirectionListener implements KeyListener
        {
            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {}

            // in case that a key is pressed, the following will be executed.
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode()) {
                    case 83:
                        //s key
                        if (currentDiameter >= 16) {
                            currentDiameter -= 6;
                        } else {
                            currentDiameter = 10;
                        }
                        break;
                    case 66:
                        //b key
                        currentDiameter += 6;
                        break;
                    case 37:
                        //left key
                        x1 -= 5;
                        break;
                    case 38:
                        //up key
                        y1 -= 5;
                        break;
                    case 39:
                        //left key
                        x1 += 5;
                        break;
                    case 40:
                        //down key
                        y1 += 5;
                        break;
                }
            }
        } // end of DirectionListener


        // listener class that listens to the mouse
        public class PointListener implements MouseListener
        {
            //in case that a user presses using a mouse,
            //it gains the focus of the keyboard keys
            public void mousePressed (MouseEvent event)
            {
                canvas.requestFocus();
            }

            public void mouseClicked (MouseEvent event) {}
            public void mouseReleased (MouseEvent event) {}
            public void mouseEntered (MouseEvent event) {}
            public void mouseExited (MouseEvent event) {}

        } // end of PointListener

    } // end of Canvas Panel Class

} // end of Whole Panel Class