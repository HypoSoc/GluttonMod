package gluttonmod.cards;

import basemod.abstracts.CustomCard;
import gluttonmod.GluttonMod;
import gluttonmod.patches.AbstractCardEnum;

public abstract class AbstractGluttonCard extends CustomCard {
    public AbstractGluttonCard(String id, String name, String img, int cost, String rawDescription, CardType type,
                               CardRarity rarity, CardTarget target) {
        super("Glutton:" + id, name, GluttonMod.getResourcePath(img), cost, rawDescription, type,
                AbstractCardEnum.GLUTTON, rarity, target);
    }

    public void onChangeGold(int amount) {};
    public void onLoseGoldFromCard(int gold) {};
}
