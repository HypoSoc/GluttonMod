package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class Tumor extends AbstractGluttonCard
{
    private static final int DRAWBACK = 1;
    public static final String ID = "Tumor";
    public static final String NAME = "Tumor";
    public static final String DESCRIPTION = "Gain " + DRAWBACK + " Weak, Frail, and Vulnerable. NL If this card is Exhausted, draw !M! cards.";
    public static final String IMG_PATH = "cards/tumor.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 0;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC_BONUS = 1;

    public Tumor()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new WeakPower(p, DRAWBACK, false), DRAWBACK));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new FrailPower(p, DRAWBACK, false), DRAWBACK));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new VulnerablePower(p, DRAWBACK, false), DRAWBACK));
    }

    public void triggerOnExhaust() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new Tumor();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
        }
    }
}



