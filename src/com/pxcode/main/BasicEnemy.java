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

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class BasicEnemy extends GameObject{

    private Random r;
    
    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        
        r = new Random();
        
        velocityX = r.nextInt(3)+3;
        velocityY = r.nextInt(3)+3;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        
        if (x <= 0 || x >= Game.WIDTH - 16) velocityX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 40) velocityY *= -1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
    
}
