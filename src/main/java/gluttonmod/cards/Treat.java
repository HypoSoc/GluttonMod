package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Treat extends AbstractGluttonCard
{
    public static final String ID = "Treat";
    public static final String NAME = "Treat";
    public static final String DESCRIPTION = "Gain !B! Block. NL If this card is Exhausted, gain !M! Maximum HP.";
    public static final String IMG_PATH = "cards/treat.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int MAGIC = 2;
    private static final int UPGRADE_BONUS = 2;

    public Treat()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseBlock = BLOCK;
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    public void triggerOnExhaust()
    {
        AbstractDungeon.player.increaseMaxHp(this.magicNumber, false);
    }

    public AbstractCard makeCopy()
    {
        return new Treat();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeBlock(UPGRADE_BONUS);
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
