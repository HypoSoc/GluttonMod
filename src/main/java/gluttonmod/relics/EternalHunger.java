package gluttonmod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class EternalHunger extends AbstractGluttonRelic {
    public static final String ID = "EternalHunger";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final String IMG = "relics/eternalhunger.png";
    private static final LandingSound SOUND = LandingSound.HEAVY;

    public EternalHunger() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStartPostDraw() {
        AbstractRelic lollipop = AbstractDungeon.player.getRelic("Glutton:Lollipop");
        if(AbstractDungeon.player.currentHealth > 1
                || lollipop != null) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            if(lollipop != null){
                lollipop.flash();
            }
            else {
                AbstractDungeon.actionManager.addToBottom(
                        new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 1));
            }
            AbstractDungeon.actionManager.addToBottom(
                    new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public void onUnequip() {
        RelicLibrary.bossList.removeIf(r -> r.relicId.equals("Glutton:InfiniteFamine"));
    }

    public AbstractRelic makeCopy() {
        return new EternalHunger();
    }
}
