package org.psigames.ssclient;

import org.psigames.ssclient.objects.GameObject;

/**
 * Created by jadengiordano on 8/27/15.
 */
public abstract class Component {

    protected GameObject parent;

    public Component(GameObject parent) {
        this.parent = parent;
    }

}
