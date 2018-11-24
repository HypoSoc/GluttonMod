package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Brace extends AbstractGluttonCard
{
    public static final String ID = "Brace";
    public static final String NAME = "Brace";
    public static final String DESCRIPTION = "Lose !M! HP. NL Gain !B! Block.";
    public static final String IMG_PATH = "cards/brace.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 5;
    private static final int BLOCK = 20;
    private static final int UPGRADE_BONUS = 5;

    public Brace()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    public AbstractCard makeCopy()
    {
        return new Brace();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeBlock(UPGRADE_BONUS);
        }
    }
}
