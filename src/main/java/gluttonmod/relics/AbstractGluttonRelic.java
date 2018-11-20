package gluttonmod.relics;

import com.badlogic.gdx.graphics.Texture;

import basemod.abstracts.CustomRelic;
import gluttonmod.GluttonMod;

public abstract class AbstractGluttonRelic extends CustomRelic{

    public AbstractGluttonRelic(String id, String img, RelicTier tier, LandingSound sfx) {
        super("Glutton:" + id, new Texture(GluttonMod.getResourcePath(img)), tier, sfx);
    }

}
