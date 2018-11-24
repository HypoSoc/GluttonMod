package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GuardStance extends AbstractGluttonCard
{
    public static final String ID = "GuardStance";
    public static final String NAME = "Guard Stance";
    public static final String DESCRIPTION = "Gain !B! Block. NL Heal !M! HP. NL Exhaust.";
    public static final String IMG_PATH = "cards/guardstance.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 2;
    private static final int BLOCK = 8;
    private static final int MAGIC = 8;
    private static final int UPGRADE_BONUS = 4;

    public GuardStance()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseBlock = BLOCK;
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new GuardStance();
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
