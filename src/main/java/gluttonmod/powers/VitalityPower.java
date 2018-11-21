package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gluttonmod.GluttonMod;

public class VitalityPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Vitality";
    public static final String NAME = "Vitality";
    public static final String[] DESCRIPTIONS = {"Whenever you heal HP, gain #b",
            " energy."};
    public static final String IMG = "powers/vitality.png";

    public VitalityPower(AbstractCreature owner, int amount) {
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
        description = DESCRIPTIONS[0] + amount + " " + DESCRIPTIONS[1];
    }

    @Override
    public int onHeal(int healAmount){
        if(healAmount > 0){
            flash();
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
        }
        return healAmount;
    }
}
