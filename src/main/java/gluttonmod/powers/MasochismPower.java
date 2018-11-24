package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import gluttonmod.GluttonMod;

public class MasochismPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Masochism";
    public static final String NAME = "Masochism";
    public static final String[] DESCRIPTIONS = new String[]{ "Whenever you lose HP, gain #b", " Block."};
    public static final String IMG = "powers/masochism.png";

    public MasochismPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;

        this.img = new Texture(GluttonMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public int onLoseHp(int damageAmount)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.amount));
        flash();
        return damageAmount;
    }
}
