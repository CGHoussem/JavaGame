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
import com.pxcode.utility.GameObject;
import com.pxcode.main.Handler;
import com.pxcode.utility.ID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                player = handler.objects.get(i);
            }
        }
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        float distance = (float) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));

        velocityX = (float) ((-1 / distance) * diffX);
        velocityY = (float) ((-1 / distance) * diffY);

        if (x <= 0 || x >= Game.WIDTH - 16) {
            velocityX *= -1;
        }
        if (y <= 0 || y >= Game.HEIGHT - 40) {
            velocityY *= -1;
        }

        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, (float) 0.1, handler));
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (Game.isDebug) {
            g.setColor(Color.green);
            g2d.draw(getBounds());
        } else {
            g.setColor(Color.green);
            g.fillRect((int)x, (int)y, 16, 16);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

}
