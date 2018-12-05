package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import gluttonmod.actions.GnawingHungerAction;

public class GnawingHunger extends AbstractGluttonCard
{
    public static final String ID = "GnawingHunger";
    public static final String NAME = "Gnawing Hunger";
    public static final String DESCRIPTION = "Lose !M! HP. Deal !D! damage. When played, permanently increase the damage by 2 and HP loss by 1. Exhaust.";
    public static final String UPGRADED_DESCRIPTION = "Lose !M! HP. Deal !D! damage. When played, permanently increase the damage by 2 and HP loss by 1.";
    public static final String IMG_PATH = "cards/gnawinghunger.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int MORE_DAMAGE = 4;
    private static final int INITIAL_MAGIC = 1;
    private static final int INCREASE = 1;

    public GnawingHunger()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.misc = INITIAL_MAGIC;
        this.baseMagicNumber = this.misc;
        this.baseDamage = this.misc * 2 + MORE_DAMAGE;
        this.magicNumber = this.baseMagicNumber;

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AttackEffect.SMASH));
        AbstractDungeon.actionManager.addToBottom(new GnawingHungerAction(this.uuid, INCREASE, MORE_DAMAGE));
    }

    public AbstractCard makeCopy()
    {
        return new GnawingHunger();
    }

    public void applyPowers() {
        this.baseDamage = this.misc * 2 + MORE_DAMAGE;
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            if (!this.isEthereal)
                this.exhaust = false;
            this.rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }
}