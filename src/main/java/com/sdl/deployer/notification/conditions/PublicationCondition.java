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
import com.tridion.transport.transportpackage.Component;
import com.tridion.transport.transportpackage.Item;
import com.tridion.transport.transportpackage.Page;

public class PublicationCondition implements Condition, ConditionLoader {
    private final int publicationId;

    public PublicationCondition(int publicationId) {
        this.publicationId = publicationId;
    }

    public PublicationCondition() {
        this.publicationId = 0;
    }

    @Override
    public boolean eval(Item item) {
        if(item instanceof Component) {
            return ((Component)item).getId().getPublicationId() == publicationId;
        } else if(item instanceof Page) {
            return ((Page)item).getId().getPublicationId() == publicationId;
        }
        return false;
    }

    @Override
    public Condition loadCondition(Configuration configuration) throws ConfigurationException {
        return new PublicationCondition(configuration.getAttributeAsInt("PublicationId"));
    }

    @Override
    public String toString() {
        return "PublicationCondition{" +
                "publicationId=" + publicationId +
                '}';
    }
}
