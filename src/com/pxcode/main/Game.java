package com.pxcode.main;

import com.pxcode.utility.KeyInput;
import com.pxcode.utility.ID;
import com.pxcode.gui.HUD;
import com.pxcode.entities.Player;
import com.pxcode.utility.Spawn;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    public static boolean isDebug = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Game Title", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);

        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            System.out.println("Error in stop(): " + e.getMessage());
        }
    }

    public static float clamp(float var, float min, float max) {
        if (var > max) {
            var = max;
        } else if (var < min) {
            var = min;
        }
        return var;
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        new Game();
    }

}
