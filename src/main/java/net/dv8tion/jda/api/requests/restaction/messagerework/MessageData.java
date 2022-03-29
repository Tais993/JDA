package net.dv8tion.jda.api.requests.restaction.messagerework;

import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageUpdateAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.internal.utils.Checks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public interface MessageData <T extends MessageData<?>>
{
    
    @Nonnull
    @CheckReturnValue
    T setContent(@Nullable CharSequence content);

    @Nonnull
    @CheckReturnValue
    T setTTS(boolean tts);

    @Nonnull
    @CheckReturnValue
    T setActionRow(@Nullable ItemComponent... components);
    @Nonnull
    @CheckReturnValue
    T setActionRow(@Nullable Collection<? extends ItemComponent> components);
    @Nonnull
    @CheckReturnValue
    T setActionRows(@Nullable ActionRow... rows);
    @Nonnull
    @CheckReturnValue
    T setActionRows(@Nullable Collection<? extends ActionRow> actionRows);
    @Nonnull
    @CheckReturnValue
    T setEmbeds(@Nullable MessageEmbed... embeds);
    @Nonnull
    @CheckReturnValue
    T setEmbeds(@Nullable Collection<? extends MessageEmbed> embeds);

    @Nonnull
    @CheckReturnValue
    T setFile(@Nonnull InputStream data, @Nonnull String name, @Nonnull AttachmentOption... options);
    @Nonnull
    @CheckReturnValue
    T setFile(@Nonnull byte[] data, @Nonnull String name, @Nonnull AttachmentOption... options);
    @Nonnull
    @CheckReturnValue
    T setFile(@Nonnull File file, @Nonnull String name, @Nonnull AttachmentOption... options);
    @Nonnull
    @CheckReturnValue
    T setFile(@Nonnull File file, @Nonnull AttachmentOption... options);
    @Nonnull
    @CheckReturnValue
    T setFile(@Nonnull Path path, @Nonnull String name, @Nonnull AttachmentOption... options);
    @Nonnull
    @CheckReturnValue
    T setFile(@Nonnull Path path, @Nonnull AttachmentOption... options);

    @Nonnull
    @CheckReturnValue
    T clearFiles(@Nonnull BiConsumer<String, InputStream> finalizer);
    @Nonnull
    @CheckReturnValue
    T clearFiles(@Nonnull Consumer<InputStream> finalizer);

    @Nonnull
    @CheckReturnValue
    T retainFilesById(@Nonnull Collection<Long> ids);
    @Nonnull
    @CheckReturnValue
    default T retainFilesById(@Nonnull String... ids)
    {
        Checks.notNull(ids, "IDs");
        return retainFilesById(Arrays.stream(ids)
                .map(Long::valueOf)
                .collect(Collectors.toList()));
    }
    @Nonnull
    @CheckReturnValue
    default T retainFilesById(long... ids)
    {
        Checks.notNull(ids, "IDs");
        return retainFilesById(Arrays.stream(ids)
                .boxed()
                .collect(Collectors.toList()));
    }
    @Nonnull
    @CheckReturnValue
    default T retainFiles(@Nonnull Collection<? extends Message.Attachment> attachments)
    {
        Checks.noneNull(attachments, "Attachments");
        return retainFilesById(attachments
                .stream()
                .map(Message.Attachment::getIdLong)
                .collect(Collectors.toList())
        );
    }

    @Nonnull
    @CheckReturnValue
    T apply(@Nullable Message message);

    @Nonnull
    @CheckReturnValue
    T mention(@Nonnull IMentionable... mentions);
    @Nonnull
    @CheckReturnValue
    T mention(@Nonnull Collection<? extends IMentionable> mentions);
    @Nonnull
    @CheckReturnValue
    T mentionRoles(@Nonnull String... roleIds);
    @Nonnull
    @CheckReturnValue
    T mentionRoles(@Nonnull long... roleIds);
    @Nonnull
    @CheckReturnValue
    T mentionUsers(@Nonnull String @org.jetbrains.annotations.Nullable ... userIds);
    @Nonnull
    @CheckReturnValue
    T mentionUsers(@Nonnull long... userIds);

    @Nonnull
    @CheckReturnValue
    T mentionRepliedUser(boolean mention);

    @Nonnull
    @CheckReturnValue
    T setAllowedMentions(@Nullable Collection<Message.MentionType> allowedMentions);

    @Nonnull
    @CheckReturnValue
    T clearMentions();
}
