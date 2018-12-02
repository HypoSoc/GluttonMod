package gluttonmod.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import gluttonmod.GluttonMod;
import gluttonmod.cards.Flail;
import gluttonmod.patches.AbstractCardEnum;
import gluttonmod.patches.GluttonEnum;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;


public class GluttonCharacter extends CustomPlayer {

    public static final int ENERGY_PER_TURN = 3;

    public static final String SHOULDER_2 = "char/shoulder2.png"; // campfire pose
    public static final String SHOULDER_1 = "char/shoulder.png"; // another campfire pose
    public static final String CORPSE = "char/corpse.png"; // dead corpse
    public static final String SKELETON_ATLAS = "char/skeleton.atlas"; // spine animation atlas
    public static final String SKELETON_JSON = "char/skeleton.json"; // spine animation json
    public static final String[] orbTextures = {
            "gluttonmod/images/char/orb/layer1.png",
            "gluttonmod/images/char/orb/layer2.png",
            "gluttonmod/images/char/orb/layer3.png",
            "gluttonmod/images/char/orb/layer4.png",
            "gluttonmod/images/char/orb/layer5.png",
            "gluttonmod/images/char/orb/layer6.png",
            "gluttonmod/images/char/orb/layer1d.png",
            "gluttonmod/images/char/orb/layer2d.png",
            "gluttonmod/images/char/orb/layer3d.png",
            "gluttonmod/images/char/orb/layer4d.png",
            "gluttonmod/images/char/orb/layer5d.png"
    };

    public GluttonCharacter(String name) {
        super(name, GluttonEnum.GLUTTON, orbTextures,
                "gluttonmod/images/char/orb/vfx.png", (String)null, null);

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 220.0F * Settings.scale);

        initializeClass(null, GluttonMod.getResourcePath(SHOULDER_2),
                GluttonMod.getResourcePath(SHOULDER_1),
                GluttonMod.getResourcePath(CORPSE),
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        this.loadAnimation(GluttonMod.getResourcePath(SKELETON_ATLAS), GluttonMod.getResourcePath(SKELETON_JSON), 1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Glutton:Strike");
        retVal.add("Glutton:Strike");
        retVal.add("Glutton:Strike");
        retVal.add("Glutton:Strike");
        retVal.add("Glutton:Strike");
        retVal.add("Glutton:Strike");
        retVal.add("Glutton:Defend");
        retVal.add("Glutton:Defend");
        retVal.add("Glutton:Defend");
        retVal.add("Glutton:Flail");
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Glutton:EternalHunger");
        UnlockTracker.markRelicAsSeen("Glutton:EternalHunger");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("The Glutton", "A tormented being seeking to NL end its misery. NL Consumes its own life force to hunt its next meal.",
                120, 120, 0, 99,
                5, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "The Glutton";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.GLUTTON;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.LIME;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Flail();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.LIME.cpy();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 10;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("SLIME_SPLIT", MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "SLIME_SPLIT";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "The Glutton";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new GluttonCharacter(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return "NL You prepare to feast...";
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.RED;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SMASH,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[5];
    }
}
