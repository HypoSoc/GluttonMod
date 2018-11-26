package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.actions.DamageWeakestEnemyAction;

public class Tantrum extends AbstractGluttonCard {
    public static final String ID = "Tantrum";
    public static final String NAME = "Tantrum";
    public static final String DESCRIPTION = "Deal !D! damage to a random enemy. NL If this card is Exhausted, deal !D! damage to the enemy with the least HP.";
    public static final String IMG_PATH = "cards/tantrum.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 2;
    private static final int POWER = 16;
    private static final int UPGRADE_BONUS = 5;

    public Tantrum()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public void triggerOnExhaust()
    {
        AbstractDungeon.actionManager.addToBottom(
                new DamageWeakestEnemyAction(
                        new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public AbstractCard makeCopy()
    {
        return new Tantrum();
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
