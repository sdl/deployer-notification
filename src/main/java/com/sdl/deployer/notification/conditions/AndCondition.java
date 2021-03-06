/**
 * Copyright (c) 2016 All Rights Reserved by the SDL Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sdl.deployer.notification.conditions;

import com.tridion.configuration.Configuration;
import com.tridion.configuration.ConfigurationException;
import com.tridion.transport.transportpackage.Item;

import java.util.ArrayList;
import java.util.List;

import static com.sdl.deployer.notification.ConfigurationLoader.loadChildConditions;

public class AndCondition implements Condition, ConditionLoader {
    private final List<Condition> conditions;

    public AndCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public AndCondition() {
        conditions = new ArrayList<>();
    }

    @Override
    public boolean eval(Item item) {
        return conditions.stream().allMatch(c -> c.eval(item));
    }

    @Override
    public Condition loadCondition(Configuration configuration) throws ConfigurationException {
        return new AndCondition(loadChildConditions(configuration));
    }

    @Override
    public String toString() {
        return "AndCondition{" +
                "conditions=" + conditions +
                '}';
    }
}
