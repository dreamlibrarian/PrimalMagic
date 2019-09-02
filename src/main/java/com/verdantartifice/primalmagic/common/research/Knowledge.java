package com.verdantartifice.primalmagic.common.research;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.verdantartifice.primalmagic.common.capabilities.IPlayerKnowledge;

import net.minecraft.util.math.MathHelper;

public class Knowledge {
    protected IPlayerKnowledge.KnowledgeType type;
    protected ResearchDiscipline discipline;
    protected int amount;
    
    protected Knowledge(@Nonnull IPlayerKnowledge.KnowledgeType type, @Nonnull ResearchDiscipline discipline, int amount) {
        this.type = type;
        this.discipline = discipline;
        this.amount = amount;
    }
    
    @Nullable
    public static Knowledge parse(@Nullable String str) {
        if (str != null) {
            String[] tokens = str.split(";");
            if (tokens.length == 3) {
                IPlayerKnowledge.KnowledgeType type;
                try {
                    type = IPlayerKnowledge.KnowledgeType.valueOf(tokens[0].toUpperCase());
                } catch (Exception e) {
                    return null;
                }
                ResearchDiscipline discipline = ResearchDisciplines.getDiscipline(tokens[1].toUpperCase());
                int amount = MathHelper.getInt(tokens[2], 0);
                if (type != null && discipline != null && amount > 0) {
                    return new Knowledge(type, discipline, amount);
                }
            }
        }
        return null;
    }
    
    @Nonnull
    public IPlayerKnowledge.KnowledgeType getType() {
        return this.type;
    }
    
    @Nonnull
    public ResearchDiscipline getDiscipline() {
        return this.discipline;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.type.name());
        builder.append(";");
        builder.append(this.discipline.getKey());
        builder.append(";");
        builder.append(this.amount);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount;
        result = prime * result + ((discipline == null) ? 0 : discipline.getKey().hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Knowledge other = (Knowledge) obj;
        if (amount != other.amount) {
            return false;
        }
        if (discipline == null) {
            if (other.discipline != null) {
                return false;
            }
        } else if (!discipline.getKey().equals(other.discipline.getKey())) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }
}