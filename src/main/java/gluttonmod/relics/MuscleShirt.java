package gluttonmod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MuscleShirt extends AbstractGluttonRelic implements OnReceivePowerRelic {
    public static final String ID = "MuscleShirt";
    private static final RelicTier TIER = RelicTier.RARE;
    private static final String IMG = "relics/muscleshirt.png";
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int COUNT = 3;

    public MuscleShirt() {
        super(ID, IMG, TIER, SOUND);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        if(abstractPower.type == AbstractPower.PowerType.DEBUFF){
            this.counter += 1;
            if (this.counter == COUNT)
            {
                this.counter = 0;
                flash();
                this.pulse = false;
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                new StrengthPower(AbstractDungeon.player, 1), 1));
            }
            else if (this.counter == COUNT-1)
            {
                beginPulse();
                this.pulse = true;
            }
        }
        return true;
    }


    public void atBattleStart()
    {
        if (this.counter == COUNT-1)
        {
            beginPulse();
            this.pulse = true;
        }
    }

    public AbstractRelic makeCopy()
    {
        return new MuscleShirt();
    }
}
