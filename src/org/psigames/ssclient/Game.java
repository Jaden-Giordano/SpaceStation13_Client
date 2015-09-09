package org.psigames.ssclient;

import org.psigames.ssclient.objects.GameObject;
import org.psigames.ssclient.objects.TestObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jadengiordano on 9/8/15.
 */
public class Game {

    private List<GameObject> clientObjects;

    public Game() {
        this.clientObjects = new ArrayList<GameObject>();

        this.clientObjects.add(new TestObject());
    }

    public void update() {
        for (GameObject i : this.clientObjects) {
            //i.update();
        }
    }

    public void render() {
        for (GameObject i : this.clientObjects) {
            i.render();
        }
    }

}
