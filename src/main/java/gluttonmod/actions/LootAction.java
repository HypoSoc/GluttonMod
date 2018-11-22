package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class LootAction extends AbstractGameAction
{
    private int goldAmount;
    private DamageInfo info;
    private static final float DURATION = 0.1F;

    public LootAction(AbstractCreature target, DamageInfo info, int goldAmount)
    {
        this.info = info;
        setValues(target, info);
        this.goldAmount = goldAmount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update()
    {
        if ((this.duration == 0.1F) &&
                (this.target != null))
        {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));

            this.target.damage(this.info);
            if (((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) && (!this.target.halfDead) &&
                    (!this.target.hasPower("Minion")))
            {
                AbstractPlayer p = AbstractDungeon.player;
                p.gainGold(this.goldAmount);
                if(!p.hasRelic("Ectoplasm")) {
                    AbstractCreature m = this.target;
                    for (int i = 0; i < this.goldAmount; i++) {
                        AbstractDungeon.effectList.add(new GainPennyEffect(p, m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, true));
                    }
                }
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
}
