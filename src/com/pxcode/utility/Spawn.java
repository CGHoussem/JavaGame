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

import com.pxcode.entity.BasicEnemy;
import com.pxcode.entity.BossEnemy;
import com.pxcode.entity.FastEnemy;
import com.pxcode.entity.SmartEnemy;
import com.pxcode.gui.HUD;
import com.pxcode.main.Game;
import com.pxcode.main.Handler;
import java.util.Random;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;

        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.BasicEnemy, handler));
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 250) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            switch (hud.getLevel()) {
                case 2:
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 120), ID.FastEnemy, handler));
                    break;
                case 3:
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 120), ID.BasicEnemy, handler));
                    break;
                case 4:
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 120), ID.FastEnemy, handler));
                    break;
                case 5:
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 120), ID.SmartEnemy, handler));
                    break;
                case 6:
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 120), ID.FastEnemy, handler));
                    break;
                case 7:
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 120), ID.BasicEnemy, handler));
                    break;
                case 8:
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 120), ID.BasicEnemy, handler));
                    break;
                case 9:
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 120), ID.SmartEnemy, handler));
                    break;
                case 10:
                    handler.clearEnemies();
                    handler.addObject(new BossEnemy(Game.WIDTH * 0.5f - 48, -192, ID.BossEnemy1, handler));
                    break;
            }

        }
    }
}
