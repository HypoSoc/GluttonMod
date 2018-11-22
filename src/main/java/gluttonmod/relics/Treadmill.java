package gluttonmod.relics;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Treadmill extends AbstractGluttonRelic {
    public static final String ID = "Treadmill";
    private static final RelicTier TIER = RelicTier.RARE;
    private static final String IMG = "relics/treadmill.png";
    private static final LandingSound SOUND = LandingSound.HEAVY;

    private static final int HEAL = 3;

    public Treadmill() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if(!card.isEthereal){
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, HEAL));
        }
    }

    public AbstractRelic makeCopy() {
        return new Treadmill();
    }
}
