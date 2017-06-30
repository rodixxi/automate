package com.harriague.automate.core.exceptions;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.agent.AgentManager;

public class AgentException extends Exception {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception
     * 
     * @param message exception message
     */
    public AgentException(String message, Agent agent) {
        super(message);
        AgentManager.lastFailedAgent = agent;
    }

    /**
     * Create an exception
     * 
     * @param message exception message
     */
    public AgentException(Exception cause, Agent agent) {
        super(cause);
        AgentManager.lastFailedAgent = agent;
    }

    /**
     * Create an exception
     * 
     * @param message exception message
     * @param cause exception cause
     */
    public AgentException(String message, Exception cause, Agent agent) {
        super(message, cause);
        AgentManager.lastFailedAgent = agent;
    }
}
