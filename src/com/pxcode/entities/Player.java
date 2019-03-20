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
package com.pxcode.entities;

import com.pxcode.main.Game;
import com.pxcode.main.GameObject;
import com.pxcode.main.HUD;
import com.pxcode.main.Handler;
import com.pxcode.main.ID;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class Player extends GameObject{

    Handler handler;
    
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        
        x = Game.clamp(x, 0, Game.WIDTH - 36);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
        
        collision();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        if (Game.isDebug){
            g.setColor(Color.white);
            g2d.draw(getBounds());
        } else {
            g.setColor(Color.white);
            g.fillRect(x, y, 32, 32);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    private void collision(){
        for (int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);
            
            if (tempObject.getId() == ID.BasicEnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH--;
                }
            }
            
        }
    }
    
}
