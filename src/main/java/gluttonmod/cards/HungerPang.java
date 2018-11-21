package gluttonmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import gluttonmod.GluttonMod;

public class HungerPang extends CustomCard {
    public static final String ID = "HungerPang";
    public static final String NAME = "Hunger Pang";
    public static final String DESCRIPTION = "Unplayable. NL When drawn, lose 2 HP and draw another card.";
    public static final String UPGRADE_DESCRIPTION = "Unplayable. NL When drawn, lose 4 HP and draw another two cards.";
    public static final String IMG_PATH = "cards/hungerpang.png";

    private static final CardType TYPE = CardType.STATUS;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    private int actualHungerDamage = 2;
    private int cardDrawAmount = 1;
    private static int UPG_DAMAGE = 4;
    private static int UPG_DRAW = 2;

    public HungerPang() {
        super(ID, NAME, GluttonMod.getResourcePath(IMG_PATH), -2, DESCRIPTION, TYPE,
                CardColor.COLORLESS, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (p.hasRelic("Medical Kit")) {
            useMedicalKit(p);
        } else {
            AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));
        }
    }

    public void triggerWhenDrawn()
    {
        if ((AbstractDungeon.player.hasPower("Evolve")) && (!AbstractDungeon.player.hasPower("No Draw")))
        {
            AbstractDungeon.player.getPower("Evolve").flash();
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player,
                    AbstractDungeon.player.getPower("Evolve").amount));
        }
        AbstractRelic lollipop = AbstractDungeon.player.getRelic("Glutton:Lollipop");
        int damage = this.actualHungerDamage;
        if(lollipop != null){
            lollipop.flash();
            damage -= 1;
        }
        AbstractDungeon.actionManager.addToBottom(
                new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player,
                        damage, AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.cardDrawAmount));
    }

    public AbstractCard makeCopy()
    {
        return new HungerPang();
    }

    public void upgrade() {
        if (!this.upgraded)
        {
            upgradeName();
            this.actualHungerDamage = UPG_DAMAGE;
            this.cardDrawAmount = UPG_DRAW;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
