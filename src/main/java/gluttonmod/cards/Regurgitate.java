package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Regurgitate extends AbstractGluttonCard {
    public static final String ID = "Regurgitate";
    public static final String NAME = "Regurgitate";
    public static final String DESCRIPTION = "Lose !M! Maximum HP. NL Deal !D! damage to ALL enemies.";
    public static final String IMG_PATH = "cards/regurgitate.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 1;
    private static final int POWER = 15;
    private static final int MAGIC = 3;
    private static final int UPGRADE_BONUS = 3;

    public Regurgitate()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
        this.isMultiDamage = true;
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));
        p.decreaseMaxHealth(this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn,
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public AbstractCard makeCopy()
    {
        return new Regurgitate();
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
