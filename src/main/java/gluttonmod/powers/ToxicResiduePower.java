package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import gluttonmod.GluttonMod;

public class ToxicResiduePower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:ToxicResidue";
    public static final String NAME = "Toxic Residue";
    public static final String[] DESCRIPTIONS = new String[]{ "Whenever a card is #yExhausted, apply #b",
            " #yPoison to a random enemy."};
    public static final String IMG = "powers/toxicresidue.png";

    public ToxicResiduePower(AbstractCreature owner, int amount) {
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
        description = DESCRIPTIONS[0] +  amount +  DESCRIPTIONS[1];
    }

    @Override
    public void onExhaust(AbstractCard card) {
        flash();
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);
        if(randomMonster != null){
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(randomMonster, this.owner,
                            new PoisonPower(randomMonster, this.owner, this.amount), this.amount));
        }
    }
}

