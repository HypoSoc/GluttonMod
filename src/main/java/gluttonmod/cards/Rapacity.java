package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class Rapacity extends AbstractGluttonCard {
    public static final String ID = "Rapacity";
    public static final String NAME = "Rapacity";
    public static final String DESCRIPTION = "Exhaust a random card. NL Gain !M! Gold. NL Exhaust..";
    public static final String UPGRADE_DESCRIPTION = "Exhaust a card. NL Gain !M! Gold. NL Exhaust.";
    public static final String IMG_PATH = "cards/rapacity.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 10;
    private static final int UPGRADE_BONUS = 2;

    public Rapacity()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 1, false));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 1, true));
        }

        if(!p.hasRelic("Ectoplasm")) {
            p.gainGold(this.magicNumber);
            for (int i = 0; i < this.magicNumber; i++) {
                AbstractDungeon.effectList.add(new GainPennyEffect(p, 0, 0, p.hb.cX, p.hb.cY, true));
            }
        }
    }

    public AbstractCard makeCopy()
    {
        return new Rapacity();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

