package es.danirod.sprint9.game.box2d;

import com.badlogic.gdx.physics.box2d.BodyDef;

//Helper class for creating body definitions without having all that code in Box2DScreen.
public class BodyDefFactory {

    public static BodyDef createPlayer() {
        BodyDef def = new BodyDef();
        def.position.set(0, 0.5f);

        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    public static BodyDef createSpikes(float x) {
        BodyDef def = new BodyDef();
        def.position.set(x, 0.5f);
        return def;
    }

    public static BodyDef createFloor() {
        BodyDef def = new BodyDef();
        def.position.set(0, -1);
        return def;
    }
}
