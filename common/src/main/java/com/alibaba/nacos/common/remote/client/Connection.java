/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.common.remote.client;

import com.alibaba.nacos.api.ability.constant.AbilityKey;
import com.alibaba.nacos.api.ability.constant.AbilityStatus;
import com.alibaba.nacos.api.remote.Requester;

import java.util.Map;

/**
 * connection on client side.
 *
 * @author liuzunfei
 * @version $Id: Connection.java, v 0.1 2020年08月09日 1:32 PM liuzunfei Exp $
 */
@SuppressWarnings("PMD.AbstractClassShouldStartWithAbstractNamingRule")
public abstract class Connection implements Requester {
    
    private String connectionId;
    
    private boolean abandon = false;
    
    protected RpcClient.ServerInfo serverInfo;
    
    protected Map<String, Boolean> abilityTable;
    
    public Connection(RpcClient.ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }
    
    public String getConnectionId() {
        return connectionId;
    }
    
    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }
    
    public AbilityStatus getConnectionAbility(AbilityKey abilityKey) {
        if (abilityTable == null || !abilityTable.containsKey(abilityKey.getName())) {
            return AbilityStatus.UNKNOWN;
        }
        return  abilityTable.get(abilityKey.getName()) ? AbilityStatus.SUPPORTED : AbilityStatus.NOT_SUPPORTED;
    }
    
    public void setAbilityTable(Map<String, Boolean> abilityTable) {
        this.abilityTable = abilityTable;
    }
    
    /**
     * Getter method for property <tt>abandon</tt>.
     *
     * @return property value of abandon
     */
    public boolean isAbandon() {
        return abandon;
    }
    
    /**
     * Setter method for property <tt>abandon</tt>. connection event will be ignored if connection is abandoned.
     *
     * @param abandon value to be assigned to property abandon
     */
    public void setAbandon(boolean abandon) {
        this.abandon = abandon;
    }
    
}
