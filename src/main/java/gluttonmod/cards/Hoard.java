package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.powers.HoardPower;

public class Hoard extends AbstractGluttonCard
{
    private static final int GOLD = 40;

    public static final String ID = "Hoard";
    public static final String NAME = "Hoard";
    public static final String DESCRIPTION = "In !M! turns, gain " + GOLD + " Gold.";
    public static final String UPGRADE_DESCRIPTION = "Innate. NL In !M! turns, gain " + GOLD + " Gold.";
    public static final String IMG_PATH = "cards/hoard.png";

    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 4;

    public Hoard()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new HoardPower(p, this.magicNumber, GOLD), this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new Hoard();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}


