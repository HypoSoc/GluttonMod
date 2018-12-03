package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

public class Chomp extends AbstractGluttonCard {
    public static final String ID = "Chomp";
    public static final String NAME = "Chomp";
    public static final String DESCRIPTION = "Deal !D! damage. NL Heal for unblocked damage dealt. Exhaust.";
    public static final String IMG_PATH = "cards/chomp.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 3;
    private static final int POWER = 16;
    private static final int UPGRADE_BONUS = 5;

    public Chomp()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BiteEffect(m.hb.cX,
                    m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));

        int healAmount = this.damage;
        healAmount -= m.currentBlock;
        if (healAmount > m.currentHealth) {
            healAmount = m.currentHealth;
        }
        if (healAmount > 0) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, healAmount));
        }
    }

    public AbstractCard makeCopy()
    {
        return new Chomp();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}

