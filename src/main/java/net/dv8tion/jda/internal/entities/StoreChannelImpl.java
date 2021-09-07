/*
 * Copyright 2015 Austin Keener, Michael Ritter, Florian Spieß, and the JDA contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dv8tion.jda.internal.entities;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreChannelImpl extends AbstractChannelImpl<StoreChannel, StoreChannelImpl> implements StoreChannel
{
    public StoreChannelImpl(long id, GuildImpl guild)
    {
        super(id, guild);
    }

    @Override
    public StoreChannelImpl setPosition(int rawPosition)
    {
        getGuild().getStoreChannelView().clearCachedLists();
        return super.setPosition(rawPosition);
    }

    @NotNull
    @Override
    public ChannelType getType()
    {
        return ChannelType.STORE;
    }

    @NotNull
    @Override
    public List<Member> getMembers()
    {
        return Collections.emptyList();
    }

    @Override
    public int getPosition()
    {
        List<GuildChannel> channels = new ArrayList<>(getGuild().getTextChannels());
        channels.addAll(getGuild().getStoreChannels());
        Collections.sort(channels);
        for (int i = 0; i < channels.size(); i++)
        {
            if (equals(channels.get(i)))
                return i;
        }
        throw new IllegalStateException("Somehow when determining position we never found the StoreChannel in the Guild's channels? wtf?");
    }

    @NotNull
    @Override
    public ChannelAction<StoreChannel> createCopy(@NotNull Guild guild)
    {
        throw new UnsupportedOperationException("Bots cannot create store channels");
    }

    @Override
    public String toString()
    {
        return "SC:" + getName() + '(' + getId() + ')';
    }
}
