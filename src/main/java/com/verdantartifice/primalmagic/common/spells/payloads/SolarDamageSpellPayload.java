package com.verdantartifice.primalmagic.common.spells.payloads;

import java.util.Map;

import com.verdantartifice.primalmagic.common.research.CompoundResearchKey;
import com.verdantartifice.primalmagic.common.research.SimpleResearchKey;
import com.verdantartifice.primalmagic.common.sounds.SoundsPM;
import com.verdantartifice.primalmagic.common.sources.Source;
import com.verdantartifice.primalmagic.common.sources.SourceList;
import com.verdantartifice.primalmagic.common.spells.SpellPackage;
import com.verdantartifice.primalmagic.common.spells.SpellProperty;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SolarDamageSpellPayload extends AbstractDamageSpellPayload {
    public static final String TYPE = "solar_damage";
    protected static final CompoundResearchKey RESEARCH = CompoundResearchKey.from(SimpleResearchKey.parse("SPELL_PAYLOAD_SOLAR"));

    public SolarDamageSpellPayload() {
        super();
    }
    
    public SolarDamageSpellPayload(int power, int duration) {
        super(power);
        this.getProperty("duration").setValue(duration);
    }
    
    public static CompoundResearchKey getResearch() {
        return RESEARCH;
    }
    
    @Override
    protected Map<String, SpellProperty> initProperties() {
        Map<String, SpellProperty> propMap = super.initProperties();
        propMap.put("duration", new SpellProperty("duration", "primalmagic.spell.property.duration", 0, 5));
        return propMap;
    }

    @Override
    public Source getSource() {
        return Source.SUN;
    }

    @Override
    public void playSounds(World world, LivingEntity caster) {
        world.playSound(null, caster.getPosition(), SoundsPM.SUNBEAM, SoundCategory.PLAYERS, 1.0F, 1.0F + (float)(world.rand.nextGaussian() * 0.05D));
    }

    @Override
    protected float getTotalDamage(Entity target) {
        return 3.0F + this.getPropertyValue("power");
    }

    @Override
    protected String getPayloadType() {
        return TYPE;
    }

    @Override
    protected void applySecondaryEffects(RayTraceResult target, SpellPackage spell, World world, LivingEntity caster) {
        if (target != null && target.getType() == RayTraceResult.Type.ENTITY && this.getPropertyValue("duration") > 0) {
            EntityRayTraceResult entityTarget = (EntityRayTraceResult)target;
            if (entityTarget.getEntity() != null && entityTarget.getEntity() instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity)entityTarget.getEntity();
                if (entity.isEntityUndead()) {
                    entity.setFire(this.getPropertyValue("duration"));
                }
            }
        }
    }
    
    @Override
    public SourceList getManaCost() {
        return super.getManaCost().add(this.getSource(), this.getPropertyValue("duration"));
    }
}