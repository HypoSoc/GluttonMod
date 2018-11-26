package gluttonmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class DamageWeakestEnemyAction
        extends AbstractGameAction
{
    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;

    public DamageWeakestEnemyAction(DamageInfo info, AbstractGameAction.AttackEffect effect)
    {
        this.info = info;
        setValues(AbstractDungeon.getMonsters().getRandomMonster(true), info); //Random monster will be ignored
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = 0.1F;
    }

    public void update()
    {
        AbstractMonster weakestMonster = null;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                if (weakestMonster == null) {
                    weakestMonster = m;
                } else if (m.currentHealth < weakestMonster.currentHealth) {
                    weakestMonster = m;
                }
            }
        }
        if(weakestMonster != null) {
            this.target = weakestMonster;
        }
        if (shouldCancelAction())
        {
            this.isDone = true;
            return;
        }
        if (this.duration == 0.1F)
        {
            this.target.damageFlash = true;
            this.target.damageFlashFrames = 4;
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
        }
        tickDuration();
        if (this.isDone)
        {
            if(this.target != null) {
                if (this.attackEffect == AbstractGameAction.AttackEffect.POISON) {
                    this.target.tint.color = Color.CHARTREUSE.cpy();
                    this.target.tint.changeColor(Color.WHITE.cpy());
                } else if (this.attackEffect == AbstractGameAction.AttackEffect.FIRE) {
                    this.target.tint.color = Color.RED.cpy();
                    this.target.tint.changeColor(Color.WHITE.cpy());
                }
                this.target.damage(this.info);
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }
                AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
            }
        }
    }
}
