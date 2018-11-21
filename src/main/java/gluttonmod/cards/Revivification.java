package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.powers.RevivificationPower;

public class Revivification extends AbstractGluttonCard
{
    public static final String ID = "Revivification";
    public static final String NAME = "Revivivfication";
    public static final String DESCRIPTION = "Whenever you heal HP, heal an additional !M! HP.";
    public static final String IMG_PATH = "cards/revivification.png";

    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_BONUS = 1;

    public Revivification()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new RevivificationPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new Revivification();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}