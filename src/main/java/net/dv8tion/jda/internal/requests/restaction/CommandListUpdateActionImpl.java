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

package net.dv8tion.jda.internal.requests.restaction;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

public class CommandListUpdateActionImpl extends RestActionImpl<List<Command>> implements CommandListUpdateAction
{
    private final List<CommandData> commands = new ArrayList<>();
    private final GuildImpl guild;

    public CommandListUpdateActionImpl(JDA api, GuildImpl guild, Route.CompiledRoute route)
    {
        super(api, route);
        this.guild = guild;
    }

    @NotNull
    @Override
    public CommandListUpdateAction timeout(long timeout, @NotNull TimeUnit unit)
    {
        return (CommandListUpdateAction) super.timeout(timeout, unit);
    }

    @NotNull
    @Override
    public CommandListUpdateAction addCheck(@NotNull BooleanSupplier checks)
    {
        return (CommandListUpdateAction) super.addCheck(checks);
    }

    @NotNull
    @Override
    public CommandListUpdateAction setCheck(BooleanSupplier checks)
    {
        return (CommandListUpdateAction) super.setCheck(checks);
    }

    @NotNull
    @Override
    public CommandListUpdateAction deadline(long timestamp)
    {
        return (CommandListUpdateAction) super.deadline(timestamp);
    }

    @NotNull
    @Override
    public CommandListUpdateAction addCommands(@NotNull Collection<? extends CommandData> commands)
    {
        Checks.noneNull(commands, "Command");
        Checks.check(this.commands.size() + commands.size() <= 100, "Cannot have more than 100 commands! Try using subcommands instead.");
        this.commands.addAll(commands);
        return this;
    }

    @Override
    protected RequestBody finalizeData()
    {
        DataArray json = DataArray.empty();
        json.addAll(commands);
        return getRequestBody(json);
    }

    @Override
    protected void handleSuccess(Response response, Request<List<Command>> request)
    {
        List<Command> commands = response.getArray().stream(DataArray::getObject)
                .map(obj -> new Command(api, guild, obj))
                .collect(Collectors.toList());
        request.onSuccess(commands);
    }
}
