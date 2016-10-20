// Assignment #: 7
//         Name: Bailey Bowman
//    StudentID: 1208740698
//      Lecture: MWF 2:00 PM
//  Arizona State University CSE205
//  Description: Driver class that starts applet and uploads content.

import javax.swing.*;

public class Assignment7 extends JApplet {

    public void init() {
        // create a WholePanel object and add it to the applet
        WholePanel wholePanel = new WholePanel();
        getContentPane().add(wholePanel);

        //set applet size to 500 X 400
        setSize(500, 400);
    }

}



