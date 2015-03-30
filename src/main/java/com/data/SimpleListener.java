package com.data;

import org.springframework.jms.core.JmsTemplate;

/**
 * Listens to messages.
 */
public class SimpleListener {
    private JmsTemplate sender;
    private SimpleDAO simpleDAO;
    private boolean needRollback;

    public void setSender(JmsTemplate sender) {
        this.sender = sender;
    }

    public void setSimpleDAO(SimpleDAO simpleDAO) {
        this.simpleDAO = simpleDAO;
    }

    public void setNeedRollback(boolean needRollback) {
        this.needRollback = needRollback;
    }

    public void handleMessage(String message) {
        System.out.println(String.format("Received a message '%s'", message));

        simpleDAO.updateDatabase();

        sender.send(session2 -> session2.createTextMessage("YEP"));

        if (needRollback) {
            throw new RuntimeException("Rolling back");
        }
    }
}
