package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class Treasure extends AbstractGluttonCard
{
    private static final int EXCHANGE = 100;

    public static final String ID = "Treasure";
    public static final String NAME = "Treasure";
    public static final String DESCRIPTION = "Gain !M! Gold. For each "
            + EXCHANGE + " Gold you have, add a random rare card to your hand. They cost 0 this turn. Exhaust.";
    public static final String EXTENDED_DESCRIPTION[] = {" NL (Add ", " card.)", " cards.)"};
    public static final String IMG_PATH = "cards/treasure.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 35;
    private static final int UPGRADE_MAGIC_BONUS = 15;

    public Treasure()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void triggerWhenDrawn() {
        int createAmount = calculateGainAmount();
        if(createAmount == 1) {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0] + createAmount + EXTENDED_DESCRIPTION[1]);
        }
        else {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0] + createAmount + EXTENDED_DESCRIPTION[2]);
        }
        initializeDescription();
    }

    public void onChangeGold(int amount) {
        int createAmount = calculateGainAmount();
        if(createAmount == 1) {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0] + createAmount + EXTENDED_DESCRIPTION[1]);
        }
        else {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0] + createAmount + EXTENDED_DESCRIPTION[2]);
        }
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(!p.hasRelic("Ectoplasm")) {
            p.gainGold(this.magicNumber);
            for (int i = 0; i < this.magicNumber; i++) {
                AbstractDungeon.effectList.add(new GainPennyEffect(p, 0, 0, p.hb.cX, p.hb.cY, true));
            }
        }
        int createAmount = p.gold / EXCHANGE;
        for(int i=0; i<createAmount; i++){
            AbstractCard c =
                    AbstractDungeon.getCard(AbstractCard.CardRarity.RARE, AbstractDungeon.cardRandomRng);
            while(c.cardID.equals("Glutton:Treasure")){
                c = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE, AbstractDungeon.cardRandomRng);
            }
            c = c.makeCopy();
            c.setCostForTurn(0);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c,1, false));
        }
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    private int calculateGainAmount() {
        AbstractPlayer p = AbstractDungeon.player;
        int gold = p.gold;
        if(!p.hasRelic("Ectoplasm")) {
            int gain = magicNumber;
            if(p.hasRelic("Glutton:LuckySock")){
                gain += (gain/4);
            }
            gold += gain;
        }
        return gold / EXCHANGE;
    }

    public AbstractCard makeCopy()
    {
        return new Treasure();
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

