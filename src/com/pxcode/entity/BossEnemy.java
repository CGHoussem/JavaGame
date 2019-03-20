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
public class BossEnemy extends GameObject {

    private Handler handler;
    Random r = new Random();

    private int timer = 100;
    private int timer2 = 50;

    public BossEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velocityX = 0;
        velocityY = 2;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        if (timer <= 0) {
            velocityY = 0;
            timer2--;
        } else {
            timer--;
        }
        if (timer2 <= 0) {
            if (velocityX == 0) {
                velocityX = 3;
            }
            int spawn = r.nextInt(10);
            if (spawn == 0) {
                handler.addObject(new BossEnemyBullet(x + 48, y + 96, ID.BasicEnemy, handler));
            }
        }

        if (x <= 0 || x >= Game.WIDTH - 96) {
            velocityX *= -1;
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (Game.isDebug) {
            g.setColor(Color.red);
            g2d.draw(getBounds());
        } else {
            g.setColor(Color.red);
            g.fillRect((int) x, (int) y, 96, 96);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

}
