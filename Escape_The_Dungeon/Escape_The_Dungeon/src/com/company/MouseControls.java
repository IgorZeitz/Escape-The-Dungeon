package com.company;

import com.sun.jdi.event.MethodExitEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class responsible for mouse input
 *
 * It implements essential MouseListeners to
 * properly use menu
 *
 * @author Igor Zeitz
 */
public class MouseControls implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e){
        e.getY();

        if(GamePanel.gameOn==2){
            if(e.getY()>315 && e.getY()<375) {
                GamePanel.gameOn = 0;
            } else if(e.getY()>415 && e.getY()<475){
               System.exit(0);
            } else if(e.getY()>515 && e.getY()<575){
                GamePanel.gameOn = 3;
            }
        }else if(GamePanel.gameOn==1){
            if(e.getY()>370 && e.getY()<410) {
                GamePanel.gameOn = 3;
                UI.menu();
                System.out.println("dziala");
            } else if(e.getY()>520 && e.getY()<560){
                System.exit(0);
            }
        }


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
}
