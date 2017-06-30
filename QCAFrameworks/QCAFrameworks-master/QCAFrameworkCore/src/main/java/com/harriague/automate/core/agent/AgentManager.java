package com.harriague.automate.core.agent;

import com.harriague.automate.core.conf.Constants.AppType;
import com.harriague.automate.core.device.Device;
import com.harriague.automate.core.exceptions.AgentException;

/**
 * Manager of agents
 */
public class AgentManager {
    
    public static Agent lastFailedAgent;

    /**
     * Template of the agents implement package
     */
    private static final String agentImplPackageTemplate =
            "com.harriague.automate.module.%s.agent.AgentImpl";

    /**
     * Create a specific agent
     */
    public static Agent createAgent(Device device, AppType type) throws AgentException {
        try {
            return device.createAgent(type, agentImplPackageTemplate);
        } catch (Exception e) {
            throw new AgentException(e, null);
        }
    }
}
