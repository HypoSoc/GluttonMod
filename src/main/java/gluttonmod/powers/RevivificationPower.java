package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import gluttonmod.GluttonMod;

public class RevivificationPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Revivification";
    public static final String NAME = "Revivification";
    public static final String[] DESCRIPTIONS = {"Whenever you heal HP, heal an additional #b",
            " HP."};
    public static final String IMG = "powers/revivification.png";

    public RevivificationPower(AbstractCreature owner, int amount) {
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

    @Override
    public int onHeal(int healAmount){
        if(healAmount > 0){
            flash();
            return healAmount + this.amount;
        }
        return healAmount;
    }
}
