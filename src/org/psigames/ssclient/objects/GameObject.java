package org.psigames.ssclient.objects;

import info.rockscode.util.Vector2f;
import info.rockscode.util.Vector3f;
import org.psigames.ssclient.Component;
import org.psigames.ssclient.components.RenderComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jadengiordano on 8/27/15.
 */
public abstract class GameObject {

    // Unique Id used for cross checking with server
    protected long uuid;

    protected Vector3f pos;
    protected Vector2f scale; // TODO Use Tranforms

    protected Map<String, Component> components;

    public GameObject() {
        this.components = new HashMap<String, Component>();

        this.pos = new Vector3f(0, 0, 1);
        this.scale = new Vector2f(1, 1);
    }

    public void render() {
        ((RenderComponent) this.components.get("mesh")).render();
    }

    public void setPosition(Vector3f pos) {
       this.pos = pos;
    }

    public Vector3f getPosition() {
        return this.pos;
    }

    public void setScale(Vector2f scl) {
        this.scale = scl;
    }

    public Vector2f getScale() {
        return this.scale;
    }

    public Component getComponent(String key) {
        return this.components.get(key);
    }

    public void addComponent(String key, Component c) {
        this.components.put(key, c);
    }

    public long getUUID() {
        return this.uuid;
    }

}
