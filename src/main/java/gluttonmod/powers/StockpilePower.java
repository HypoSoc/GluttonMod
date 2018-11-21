package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import gluttonmod.GluttonMod;

public class StockpilePower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Stockpile";
    public static final String NAME = "Additional Hoard";
    public static final String[] DESCRIPTIONS = {"You will start accumulating your next Hoard as soon as your current one finishes. You have #b",
    " additional Hoard.", " additional Hoards."};
    public static final String IMG = "powers/stockpile.png";

    public StockpilePower(AbstractCreature owner, int amount) {
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
        if(amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
        else{
            description = DESCRIPTIONS[0] + amount +  DESCRIPTIONS[2];
        }
    }
}
