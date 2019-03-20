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
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class Trail extends GameObject {

    private float alpha = 1;
    private Handler handler;
    private Color color;

    private int width, height;
    private float life;

    public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.color = color;
        this.handler = handler;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    @Override
    public void tick() {
        if (alpha > life) {
            alpha -= life - 0.001;
            width -= 1;
            height -= 1;
        } else {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        if (!Game.isDebug) {
            g.setColor(color);
            g.fillRect((int)x, (int)y, width, height);
        }

        g2d.setComposite(makeTransparent(1));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    private AlphaComposite makeTransparent(float alpha) {
        return (AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

}
