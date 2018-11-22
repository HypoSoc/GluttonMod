package gluttonmod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.actions.AutotomyAction;

public class Autotomy extends AbstractGluttonCard
{
    public static final String ID = "Autotomy";
    public static final String NAME = "Autotomy";
    public static final String DESCRIPTION = "Retain. NL Exhaust all status and curse cards in your hand and gain !B! block for each. NL Exhaust.";
    public static final String IMG_PATH = "cards/autotomy.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int BLOCK = 10;
    private static final int UPGRADE_BONUS = 3;

    public Autotomy()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseBlock = BLOCK;
        AlwaysRetainField.alwaysRetain.set(this, true);
        this.retain = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new AutotomyAction(this.block));
    }

    public AbstractCard makeCopy()
    {
        return new Autotomy();
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
