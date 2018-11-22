package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShedFatAction extends AbstractGameAction
{
    private int draw;

    public ShedFatAction(int draw)
    {
        this.duration = Settings.ACTION_DUR_FAST;
        this.draw = draw;
    }

    public void update()
    {
        if (this.duration == Settings.ACTION_DUR_FAST)
        {
            int theSize = AbstractDungeon.player.hand.size();
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, theSize, false));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, theSize + draw));
        }
        tickDuration();
    }
}
