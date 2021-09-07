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

package net.dv8tion.jda.api.events.self;

import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.NotNull;

/**
 * Indicates that the name of the current user changed.
 *
 * <p>Can be used to retrieve the old name.
 *
 * <p>Identifier: {@code name}
 */
public class SelfUpdateNameEvent extends GenericSelfUpdateEvent<String>
{
    public static final String IDENTIFIER = "name";

    public SelfUpdateNameEvent(@NotNull JDA api, long responseNumber, @NotNull String oldName)
    {
        super(api, responseNumber, oldName, api.getSelfUser().getName(), IDENTIFIER);
    }

    /**
     * The old name
     *
     * @return The old name
     */
    @NotNull
    public String getOldName()
    {
        return getOldValue();
    }

    /**
     * The new name
     *
     * @return The new name
     */
    @NotNull
    public String getNewName()
    {
        return getNewValue();
    }

    @NotNull
    @Override
    public String getOldValue()
    {
        return super.getOldValue();
    }

    @NotNull
    @Override
    public String getNewValue()
    {
        return super.getNewValue();
    }
}
