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

import com.pxcode.entity.Player;
import com.pxcode.main.Game;
import static com.pxcode.main.Game.HEIGHT;
import static com.pxcode.main.Game.WIDTH;
import com.pxcode.main.Handler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, 220, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
            } else if (mouseOver(mx, my, 220, 234, 200, 64)) {
                game.gameState = Game.STATE.Help;
            } else if (mouseOver(mx, my, 220, 318, 200, 64)) {
                System.exit(0);
            }
        } else if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 220, 318, 200, 64)) {
                game.gameState = Game.STATE.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        return ((mx > x && mx < x + width) && (my > y && my < y + height));
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        g.setFont(fnt);
        g.setColor(Color.white);

        if (game.gameState == Game.STATE.Menu) {
            g.drawString("Nibba Dodge", 160, 100);
            g.setFont(fnt2);
            g.drawRect(220, 150, 200, 64);
            g.drawString("Play", 255 + 30, 150 + 40);
            g.drawRect(220, 234, 200, 64);
            g.drawString("Help", 255 + 30, 234 + 40);
            g.drawRect(220, 318, 200, 64);
            g.drawString("Exit", 255 + 30, 318 + 40);
        } else if (game.gameState == Game.STATE.Help) {
            g.drawString("Help", 255, 100);
            g.drawRect(220, 318, 200, 64);
            g.setFont(fnt2);
            g.drawString("Back", 255 + 30, 318 + 40);
        }
    }
}
