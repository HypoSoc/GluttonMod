package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Torment extends AbstractGluttonCard
{
    public static final String ID = "Torment";
    public static final String NAME = "Torment";
    public static final String DESCRIPTION = "Can only be played if you have lost life !M! or more times this combat. NL Deal !D! damage.";
    public static final String EXTENDED_DESCRIPTION[] = {" NL (Lost life #b", " time.)", " times.)"};
    public static final String CANT_PLAY = "I'm not really feeling it.";
    public static final String IMG_PATH = "cards/torment.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 2;
    private static final int POWER = 30;
    private static final int MAGIC = 15;
    private static final int UPGRADE_BONUS = -5;

    public Torment()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AttackEffect.SLASH_HEAVY));
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if(p.damagedThisCombat < this.magicNumber){
            this.cantUseMessage = CANT_PLAY;
            return false;
        }
        return true;
    }

    public AbstractCard makeCopy()
    {
        return new Torment();
    }

    public void applyPowers()
    {
        super.applyPowers();
        int times = AbstractDungeon.player.damagedThisCombat;
        if(times == 1) {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0] + times + EXTENDED_DESCRIPTION[1]);
        }
        else {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0] + times + EXTENDED_DESCRIPTION[2]);
        }
        initializeDescription();
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
