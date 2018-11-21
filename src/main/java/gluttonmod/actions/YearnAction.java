package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YearnAction extends AbstractGameAction{

    private int echoAmount;
    private int discount;

    public YearnAction(int echoAmount, int discount){
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.echoAmount = echoAmount;
        this.discount = discount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.drawPile.isEmpty())
        {
            this.isDone = true;
            return;
        }
        AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
        if(card.type != AbstractCard.CardType.STATUS
            && card.type != AbstractCard.CardType.CURSE){
            AbstractDungeon.actionManager.addToBottom(new MakeEchoAction(card, this.echoAmount, this.discount));
        }
        this.isDone = true;
    }
}
