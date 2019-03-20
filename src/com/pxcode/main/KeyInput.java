/*
 * Copyright (C) 2019 Houssem Ben Mabrouk
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pxcode.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.id == ID.Player) {
                if (key == KeyEvent.VK_Z) tempObject.setVelocityY(-5);
                if (key == KeyEvent.VK_S) tempObject.setVelocityY(5);
                if (key == KeyEvent.VK_Q) tempObject.setVelocityX(-5);
                if (key == KeyEvent.VK_D) tempObject.setVelocityX(5);
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.id == ID.Player) {
                if (key == KeyEvent.VK_Z || key == KeyEvent.VK_S) 
                    tempObject.setVelocityY(0);
                if (key == KeyEvent.VK_Q || key == KeyEvent.VK_D) 
                    tempObject.setVelocityX(0);
            }
        }
        
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
    }

}
