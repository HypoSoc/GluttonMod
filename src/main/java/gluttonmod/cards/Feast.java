package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.unique.FeastAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Feast extends AbstractGluttonCard {
    public static final String ID = "Feast";
    public static final String NAME = "Feast";
    public static final String DESCRIPTION = "Deal !D! damage to ALL enemies. NL For every non-minion enemy killed, raise your Max HP by !M!. Exhaust.";
    public static final String IMG_PATH = "cards/feast.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 3;
    private static final int MAGIC = 3;
    private static final int POWER = 10;
    private static final int UPGRADE_POWER_BONUS = 2;
    private static final int UPGRADE_MAGIC_BONUS = 1;

    public Feast()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new FeastAction(p, this.multiDamage, this.damageTypeForTurn,
                        AbstractGameAction.AttackEffect.BLUNT_HEAVY, this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new Feast();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_POWER_BONUS);
            upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
        }
    }
}
