package game.gameessentials;

import biuoop.DrawSurface;
import game.levels.LevelInformation;
import gamegeometry.basetypes.GameObject;
import gamegeometry.basetypes.Sprite;

import java.util.ArrayList;

public class Background implements Sprite {

    private ArrayList<GameObject> backgroundMakeup;

    public Background() {
        this.backgroundMakeup = new ArrayList<>();
    }

    public void addToBackground(GameObject gameObject) {
        this.backgroundMakeup.add(gameObject);
    }

    @Override
    public void addToLevel(LevelInformation level) {
        level.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (GameObject object : backgroundMakeup) {
            object.drawOn(d);
        }
    }

    @Override
    public void removeFromLevel(LevelInformation level) {
        level.removeSprite(this);
    }

    @Override
    public void timePassed() {

    }
}
