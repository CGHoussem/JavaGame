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
package com.pxcode.entity;

import com.pxcode.main.Game;
import com.pxcode.utility.GameObject;
import com.pxcode.main.Handler;
import com.pxcode.utility.ID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class BossEnemyBullet extends GameObject {

    private Random r;
    private Handler handler;

    public BossEnemyBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        r = new Random();

        velocityX = r.nextInt(10) - 5;
        velocityY = 5;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        if (y >= Game.HEIGHT) {
            handler.removeObject(this);
        }

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, (float) 0.1, handler));
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (Game.isDebug) {
            g.setColor(Color.red);
            g2d.draw(getBounds());
        } else {
            g.setColor(Color.red);
            g.fillRect((int) x, (int) y, 16, 16);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
