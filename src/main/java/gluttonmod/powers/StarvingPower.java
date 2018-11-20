package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gluttonmod.GluttonMod;
import gluttonmod.cards.HungerPang;

public class StarvingPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Starving";
    public static final String NAME = "Starving";
    public static final String[] DESCRIPTIONS = {
            "At the start of your turn, shuffle #b",
            " #yHunger #yPang into your discard pile.",
            " #yHunger #yPangs into your discard pile."
    };
    public static final String IMG = "powers/starving.png";

    public StarvingPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;

        this.img = new Texture(GluttonMod.getResourcePath(IMG));
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        this.updateDescription();
    }

    public void updateDescription()
    {
        if(amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
        else{
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new HungerPang(),
                this.amount));
    }
}
