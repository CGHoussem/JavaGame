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
package com.pxcode.utility;

import com.pxcode.main.Game;
import com.pxcode.main.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        keyDown[0] = keyDown[1] = keyDown[2] = keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.id == ID.Player) {
                if (key == KeyEvent.VK_Z) {
                    tempObject.setVelocityY(-5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelocityY(5);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_Q) {
                    tempObject.setVelocityX(-5);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelocityX(5);
                    keyDown[3] = true;
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (key == KeyEvent.VK_CONTROL) {
            Game.isDebug = Game.isDebug ? false : true;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.id == ID.Player) {
                if (key == KeyEvent.VK_Z) {
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_S) {
                    keyDown[1] = false;
                }
                if (key == KeyEvent.VK_Q) {
                    keyDown[2] = false;
                }
                if (key == KeyEvent.VK_D) {
                    keyDown[3] = false;
                }

                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setVelocityY(0);
                }
                if (!keyDown[2] && !keyDown[3]) {
                    tempObject.setVelocityX(0);
                }
            }
        }

    }

}
