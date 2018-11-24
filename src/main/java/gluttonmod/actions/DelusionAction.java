package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class DelusionAction extends AbstractGameAction {
    private AbstractPlayer p;
    private static String[] TEXT = {"Choose a card to Echo."};
    private ArrayList<AbstractCard> etherealCards = new ArrayList();

    public DelusionAction(AbstractPlayer p, int amount){
        this.p = p;
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if(this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : this.p.hand.group) {
                if (c.isEthereal) {
                    this.etherealCards.add(c);
                }
            }
            if (this.p.hand.size() == this.etherealCards.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.size() - this.etherealCards.size() == 1) {
                for (AbstractCard c : this.p.hand.group) {
                    if (!c.isEthereal) {
                        AbstractDungeon.actionManager.addToBottom(
                                new MakeEchoAction(c, this.amount));
                        this.isDone = true;
                        return;
                    }
                }
            }
            this.p.hand.group.removeAll(this.etherealCards);
            if (this.p.hand.group.size() > 1)
            {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, true);
                tickDuration();
                return;
            }
            if (this.p.hand.group.size() == 1)
            {
                returnCards();
                AbstractDungeon.actionManager.addToBottom(
                        new MakeEchoAction(this.p.hand.getTopCard(), this.amount));
                this.isDone = true;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
        {
            returnCards();
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                AbstractDungeon.actionManager.addToBottom(
                        new MakeEchoAction(c, this.amount));
                p.hand.addToTop(c);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.etherealCards) {
            this.p.hand.addToTop(c);
        }
        this.p.hand.refreshHandLayout();
    }
}
