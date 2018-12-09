package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Strain extends AbstractGluttonCard {
    public static final String ID = "Strain";
    public static final String NAME = "Strain";
    public static final String DESCRIPTION = "Deal !D! damage. Gain !M! Weak.";
    public static final String IMG_PATH = "cards/strain.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int POWER = 12;
    private static final int UPGRADE_BONUS = 5;

    public Strain()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new Strain();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
