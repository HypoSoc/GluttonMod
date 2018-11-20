package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.unique.FeedAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Feed_Glutton extends AbstractGluttonCard
{
    public static final String ID = "Feed";
    public static final String NAME = "Feed";
    public static final String DESCRIPTION = "Deal !D! damage. NL If this kills a NL non-minion enemy, NL raise your Max HP by !M!. Exhaust.";
    public static final String IMG_PATH = "cards/feed.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int MAGIC = 3;
    private static final int POWER = 10;
    private static final int UPGRADE_POWER_BONUS = 2;
    private static final int UPGRADE_MAGIC_BONUS = 1;

    public Feed_Glutton()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new FeedAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new Feed_Glutton();
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
