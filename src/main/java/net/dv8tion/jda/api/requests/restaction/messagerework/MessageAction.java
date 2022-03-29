package net.dv8tion.jda.api.requests.restaction.messagerework;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.RestAction;

public interface MessageAction extends MessageData, RestAction
{
    MessageChannel getChannel();
    TextChannel getTextChannel();
}
