package org.psigames.ssclient.components;

import info.rockscode.util.Texture;
import info.rockscode.util.Vector3f;
import org.psigames.ssclient.Component;
import org.psigames.ssclient.objects.GameObject;
import org.psigames.ssclient.tools.Constants;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by jadengiordano on 9/8/15.
 */
public class RenderComponent extends Component {

    private Vector3f color;
    private Texture texture; // TODO Use Materials

    public RenderComponent(GameObject p) {
        super(p);

        this.color = new Vector3f(255, 255, 255);

        if (Constants.glVersion >= 3.1f) {
            // Create VBO
        }
    }

    public void render() {
        if (Constants.glVersion >= 3.1f) {
            // Render VBO
        }
        else {
            // Render using immediate mode
            glPushMatrix();
            {
                // Translation, Scale and TODO Rotations
                glTranslatef(this.parent.getPosition().x, this.parent.getPosition().y, this.parent.getPosition().z/10);
                glScalef(this.parent.getScale().x, this.parent.getScale().y, 1);

                // Color
                glColor3f(color.x, color.y, color.z);

                // Texture
                if (texture != null) {
                    texture.bind();
                }

                // Rendering Square
                glBegin(GL_QUADS);
                {
                    glTexCoord2f(0, 0);
                    glVertex3f(0, 0, 0);
                    glTexCoord2f(1, 0);
                    glVertex3f(1, 0, 0);
                    glTexCoord2f(1, 1);
                    glVertex3f(1, 1, 0);
                    glTexCoord2f(0, 1);
                    glVertex3f(0, 1, 0);
                }
                glEnd();
            }
            glPopMatrix();
        }
    }

    public void setColor(float x, float y, float z) {
        this.color = new Vector3f(x, y, z);
    }

    public void setColor(Vector3f c) {
        this.setColor(c.x, c.y, c.z);
    }

    public Vector3f getColor() {
        return this.color;
    }

    public void setTexture(Texture t) {
        this.texture = t;
    }

    public Texture getTexture() {
        return this.texture;
    }

}
