package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class LashOut extends AbstractGluttonCard {
    public static final String ID = "LashOut";
    public static final String NAME = "Lash Out";
    public static final String DESCRIPTION = "Exhaust a random card. NL Deal !D! damage to ALL enemies.";
    public static final String UPGRADE_DESCRIPTION = "Exhaust a card. NL Deal !D! damage to ALL enemies.";
    public static final String IMG_PATH = "cards/lashout.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 1;
    private static final int POWER = 12;

    public LashOut()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 1, false));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 1, true));
        }

        AbstractDungeon.actionManager.addToBottom(
                new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(
                new VFXAction(p, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractCard makeCopy()
    {
        return new LashOut();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

