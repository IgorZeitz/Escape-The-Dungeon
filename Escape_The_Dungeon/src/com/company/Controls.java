package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            up=true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            down=true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            left=true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            right=true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            //Additional functionality
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            up=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            down=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            left=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            right=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    //Pause menu - Lvl 1
            if(GamePanel.gameOn==0){
                GamePanel.gameOn=2;
            } else if(GamePanel.gameOn==2){
                GamePanel.gameOn=0;
            }
                    //Pause menu - Lvl 2
            else if(GamePanel.gameOn==8){
                GamePanel.gameOn=2;
            } else if(GamePanel.gameOn==2){
                GamePanel.gameOn=8;
            }
                    //Pause menu - Lvl 3
            else if(GamePanel.gameOn==9){
                GamePanel.gameOn=2;
            } else if(GamePanel.gameOn==2){
                GamePanel.gameOn=9;
            }

        }
    }
}
