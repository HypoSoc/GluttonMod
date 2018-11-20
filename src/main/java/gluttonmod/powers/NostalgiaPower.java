package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gluttonmod.GluttonMod;
import gluttonmod.actions.MakeEchoAction;

public class NostalgiaPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Nostalgia";
    public static final String NAME = "Nostalgia";
    public static final String[] DESCRIPTIONS = new String[]{ "Whenever you exhaust a non-ethereal Skill or Attack, create",
            "an echo of it.", "echoes of it."};
    public static final String IMG = "powers/nostalgia.png";

    public NostalgiaPower(AbstractCreature owner, int amount) {
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
        if(amount == 1) {
            description = DESCRIPTIONS[0] + " " + DESCRIPTIONS[1];
        }
        else{
            description = DESCRIPTIONS[0] + " " + amount + " " + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if(card.type == AbstractCard.CardType.ATTACK ||
            card.type == AbstractCard.CardType.SKILL){
            if(!card.isEthereal) {
                flash();
                AbstractDungeon.actionManager.addToBottom(new MakeEchoAction(card, this.amount));
            }
        }
    }
}

