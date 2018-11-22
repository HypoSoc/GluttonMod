package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import gluttonmod.GluttonMod;

public class EnfeeblementPower extends AbstractGluttonPower implements OnReceivePowerPower {
    public static final String POWER_ID = "Glutton:Enfeeblement";
    public static final String NAME = "Enfeeblement";
    public static final String[] DESCRIPTIONS = {"Whenever you gain a debuff, deal #b",
            " damage to a random enemy."};
    public static final String IMG = "powers/enfeeblement.png";

    public EnfeeblementPower(AbstractCreature owner, int amount) {
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
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power.type == PowerType.DEBUFF){
            flash();
            AbstractDungeon.actionManager.addToBottom(new SwordBoomerangAction(
                    AbstractDungeon.getMonsters().getRandomMonster(true),
                    new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), 1));
        }
        return true;
    }
}
