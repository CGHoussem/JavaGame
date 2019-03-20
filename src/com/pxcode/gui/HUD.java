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
package com.pxcode.gui;

import com.pxcode.main.Game;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class HUD {

    public static int HEALTH = 100;
    private int greenValue = 255;

    private int score = 0;
    private int level = 1;

    public void tick() {
        HEALTH = (int) Game.clamp(HEALTH, 0, 100);

        greenValue = HEALTH * 2;
        greenValue = (int) Game.clamp(greenValue, 0, 255);

        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(125, greenValue, 0));
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        g.drawString("level: " + level, 15, 68);
        g.drawString("Score: " + score, 15, 78);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
