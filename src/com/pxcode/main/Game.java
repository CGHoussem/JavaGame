package com.pxcode.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class Game extends Canvas implements Runnable{
    
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    
    private Thread thread;
    private boolean running = false;
    
    private Handler handler;
    
    public Game(){
        new Window(WIDTH, HEIGHT, "Game Title", this);
        
        handler = new Handler();
        
        handler.addObject(new Player(250, 250, ID.Player));
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e){
            System.out.println("Error in stop(): " + e.getMessage());
        }
    }
    
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        handler.tick();
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        g.dispose();
        bs.show();
    }
    
    public static void main(String args[]){
        new Game();
    }

}
