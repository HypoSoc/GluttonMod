package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import gluttonmod.GluttonMod;

public class MiseryPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Misery";
    public static final String NAME = "Misery";
    public static final String[] DESCRIPTIONS = new String[]{ "Whenever you lose HP, deal", "damage to ALL enemies."};
    public static final String IMG = "powers/misery.png";

    public MiseryPower(AbstractCreature owner, int amount) {
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
        description = DESCRIPTIONS[0] + " "+amount+" " + DESCRIPTIONS[1];
    }

    public int onLoseHp(int damageAmount)
    {
        flash();
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(this.owner, new CleaveEffect(), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.owner,
                DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS,
                AbstractGameAction.AttackEffect.NONE));
        return damageAmount;
    }
}
