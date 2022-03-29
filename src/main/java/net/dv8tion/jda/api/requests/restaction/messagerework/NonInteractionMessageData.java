package net.dv8tion.jda.api.requests.restaction.messagerework;

public interface NonInteractionMessageData extends MessageAction
{
    void setNonce(CharSequence nonce);
}
