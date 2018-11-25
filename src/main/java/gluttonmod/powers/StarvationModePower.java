package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import gluttonmod.GluttonMod;

public class StarvationModePower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:StarvationMode";
    public static final String NAME = "Starvation Mode";
    public static final String[] DESCRIPTIONS = new String[]{ "Whenever you draw a Status or Curse, apply #b", " #yPoison to ALL enemies."};
    public static final String IMG = "powers/starvationmode.png";

    public StarvationModePower(AbstractCreature owner, int amount) {
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
    public void onCardDraw(AbstractCard card) {
        if(card.type == AbstractCard.CardType.STATUS ||
        card.type == AbstractCard.CardType.CURSE){
            flash();
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if ((!m.isDead) && (!m.isDying)) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(m, this.owner, new PoisonPower(m, this.owner, this.amount), this.amount));
                }
            }
        }
    }
}
