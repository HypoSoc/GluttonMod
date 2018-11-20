package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gluttonmod.GluttonMod;

public class ClotPower extends AbstractGluttonPower {
    private static final int CLOT_HEAL = 3;

    public static final String POWER_ID = "Glutton:Clot";
    public static final String NAME = "Clot";
    public static final String[] DESCRIPTIONS = {"Heal #b", " HP at the start of each turn for #b",
            " turn.", " turns."};
    public static final String IMG = "powers/clot.png";

    public ClotPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;

        this.img = new Texture(GluttonMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.updateDescription();

        this.isTurnBased = true;
    }

    @Override
    public void updateDescription() {
        if(amount == 1) {
            description = DESCRIPTIONS[0] + CLOT_HEAL + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
        else{
            description = DESCRIPTIONS[0] + CLOT_HEAL + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, CLOT_HEAL));
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Glutton:Clot"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Glutton:Clot", 1));
        }
    }
}
