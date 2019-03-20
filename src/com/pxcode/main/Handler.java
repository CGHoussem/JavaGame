/*
 * Copyright (C) 2019 Houssem Ben Mabrouk
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.pxcode.main;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Houssem Ben Mabrouk
 */
public class Handler {
    
    public LinkedList<GameObject> objects = new LinkedList<>();
    
    public void tick(){
        for (int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }
    
    public void render(Graphics g){
         for (int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.objects.add(object);
    }
    
    public void removeObject(GameObject object){
        this.objects.remove(object);
    }
    
}
