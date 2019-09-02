package com.verdantartifice.primalmagic.common.commands.arguments;

import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.verdantartifice.primalmagic.common.capabilities.IPlayerKnowledge;

public class KnowledgeTypeArgument implements ArgumentType<KnowledgeTypeInput> {
    public static KnowledgeTypeArgument knowledgeType() {
        return new KnowledgeTypeArgument();
    }
    
    @Override
    public KnowledgeTypeInput parse(StringReader reader) throws CommandSyntaxException {
        KnowledgeTypeParser parser = (new KnowledgeTypeParser(reader)).parse();
        return new KnowledgeTypeInput(parser.getType());
    }

    public static <S> KnowledgeTypeInput getKnowledgeType(CommandContext<S> context, String name) {
        return context.getArgument(name, KnowledgeTypeInput.class);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String remaining = builder.getRemaining().toUpperCase();
        for (IPlayerKnowledge.KnowledgeType type : IPlayerKnowledge.KnowledgeType.values()) {
            String key = type.name().toUpperCase();
            if (key.startsWith(remaining)) {
                builder.suggest(key);
            }
        }
        return builder.buildFuture();
    }
}