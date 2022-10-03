package com.example.insideout;

public class ActionCard {
    private int actionImage; //better a ImageView???
    private String actionTitle;
    private String actionDescription;

    public ActionCard(int actionImage, String actionTitle, String actionDescription){
        this.actionImage = actionImage;
        this.actionTitle = actionTitle;
        this.actionDescription = actionDescription;
    }

    public int getActionImage() {
        return actionImage;
    }

    public String getActionTitle() {
        return actionTitle;
    }

    public String getActionDescription() {
        return actionDescription;
    }
}
