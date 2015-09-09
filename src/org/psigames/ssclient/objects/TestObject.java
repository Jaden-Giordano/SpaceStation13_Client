package org.psigames.ssclient.objects;

import info.rockscode.util.Vector2f;
import info.rockscode.util.Vector3f;
import org.psigames.ssclient.components.RenderComponent;

/**
 * Created by jadengiordano on 9/8/15.
 */
public class TestObject extends GameObject {

    public TestObject() {
        this.addComponent("mesh", new RenderComponent(this));

        this.scale = new Vector2f(100, 100);
    }

}
