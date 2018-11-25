package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.actions.YearnAction;

public class Yearn extends AbstractGluttonCard
{
    public static final String ID = "Yearn";
    public static final String NAME = "Yearn";
    public static final String DESCRIPTION = "Draw a card. If it is not a Status or Curse, create an Echo of it that costs 1 less. NL Exhaust.";
    public static final String UPGRADED_DESCRIPTION = "Draw a card. If it is not a Status or Curse, create an Echo of it that costs 1 less.";
    public static final String IMG_PATH = "cards/yearn.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 1;

    public Yearn()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {

        AbstractDungeon.actionManager.addToBottom(new YearnAction(this.magicNumber, 1));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
    }

    public AbstractCard makeCopy()
    {
        return new Yearn();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            if (!this.isEthereal)
                this.exhaust = false;
            this.rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }
}

