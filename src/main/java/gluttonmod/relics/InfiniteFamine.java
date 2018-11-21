package gluttonmod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class InfiniteFamine extends AbstractGluttonRelic {
    public static final String ID = "InfiniteFamine";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final String IMG = "relics/infinitefamine.png";
    private static final LandingSound SOUND = LandingSound.HEAVY;

    public InfiniteFamine() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStartPostDraw() {
        AbstractRelic lollipop = AbstractDungeon.player.getRelic("Glutton:Lollipop");
        int damage = 2;
        if(lollipop != null){
            damage -= 1;
        }
        if(AbstractDungeon.player.currentHealth > damage) {
            flash();
            if(lollipop != null){
                lollipop.flash();
            }
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(
                    new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, damage));
            AbstractDungeon.actionManager.addToBottom(
                    new DrawCardAction(AbstractDungeon.player, 2));
        }
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic("Glutton:EternalHunger")) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals("Glutton:EternalHunger")) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    public AbstractRelic makeCopy() {
        return new InfiniteFamine();
    }
}
