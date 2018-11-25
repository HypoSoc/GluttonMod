package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FragileMight extends AbstractGluttonCard {
    public static final String ID = "FragileMight";
    public static final String NAME = "Fragile Might";
    public static final String DESCRIPTION = "Deal !D! damage. Double the number of hits for each Debuff you have.";
    public static final String EXTENDED_DESCRIPTION[] = {" NL Hits", "1 time.", "times."};
    public static final String IMG_PATH = "cards/fragilemight.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 2;
    private static final int POWER = 5;
    private static final int UPGRADE_POWER = 2;

    public FragileMight()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int timesHit = 1;
        for(AbstractPower power : AbstractDungeon.player.powers){
            if(power.type == AbstractPower.PowerType.DEBUFF){
                timesHit *= 2;
            }
        }
        for(int i=0; i<timesHit-1; i++){
            AbstractDungeon.actionManager.addToBottom(
                    new PummelDamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        }
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public AbstractCard makeCopy()
    {
        return new FragileMight();
    }

    public void applyPowers()
    {
        int timesHit = 1;
        for(AbstractPower power : AbstractDungeon.player.powers){
            if(power.type == AbstractPower.PowerType.DEBUFF){
                timesHit *= 2;
            }
        }
        super.applyPowers();
        if(timesHit == 1){
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0] + " " + EXTENDED_DESCRIPTION[1];
        }
        else{
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0] + " "
                    + timesHit + " " + EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        int timesHit = 1;
        for(AbstractPower power : AbstractDungeon.player.powers){
            if(power.type == AbstractPower.PowerType.DEBUFF){
                timesHit *= 2;
            }
        }
        if(timesHit == 1){
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0] + " " + EXTENDED_DESCRIPTION[1];
        }
        else{
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0] + " "
                    + timesHit + " " + EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_POWER);
            initializeDescription();
        }
    }
}
