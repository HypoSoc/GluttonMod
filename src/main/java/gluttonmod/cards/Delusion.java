package gluttonmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.actions.DelusionAction;

public class Delusion extends AbstractGluttonCard
{
    public static final String ID = "Delusion";
    public static final String NAME = "Delusion";
    public static final String DESCRIPTION = "Create an Echo of a non-Ethereal card in your hand. NL Exhaust.";
    public static final String UPGRADED_DESCRIPTION = "Create two Echoes of a non-Ethereal card in your hand. NL Exhaust.";
    public static final String IMG_PATH = "cards/delusion.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 0;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC_BONUS = 1;

    public Delusion()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DelusionAction(p, this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new Delusion();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
            this.rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }
}

