package gluttonmod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public class LuckySock extends AbstractGluttonRelic {
    public static final String ID = "LuckySock";
    private static final RelicTier TIER = RelicTier.UNCOMMON;
    private static final String IMG = "relics/luckysock.png";
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public LuckySock() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy()
    {
        return new LuckySock();
    }
}
