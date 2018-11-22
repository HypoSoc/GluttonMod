package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AutotomyAction
        extends AbstractGameAction
{
    private int BLOCKGAIN_AMOUNT = 0;

    public AutotomyAction(int blockAmount)
    {
        this.BLOCKGAIN_AMOUNT = blockAmount;
        setValues(AbstractDungeon.player, AbstractDungeon.player);
        this.actionType = AbstractGameAction.ActionType.BLOCK;
    }

    public void update()
    {
        if (!this.isDone)
        {
            this.isDone = true;
            int total = 0;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE)
                {
                    AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                    total += this.BLOCKGAIN_AMOUNT;
                }
            }
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, total));
        }
    }
}
