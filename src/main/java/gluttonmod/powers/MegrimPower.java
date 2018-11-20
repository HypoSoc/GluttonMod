package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gluttonmod.GluttonMod;

public class MegrimPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Megrim";
    public static final String NAME = "Megrim";
    public static final String[] DESCRIPTIONS = new String[]{ "Whenever you draw a card, deal #b", " damage to a random enemy."};
    public static final String IMG = "powers/megrim.png";

    public MegrimPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;

        this.img = new Texture(GluttonMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] +amount+ DESCRIPTIONS[1];
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        flash();
        AbstractDungeon.actionManager.addToBottom(new SwordBoomerangAction(
                AbstractDungeon.getMonsters().getRandomMonster(true),
                new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), 1));
    }
}
