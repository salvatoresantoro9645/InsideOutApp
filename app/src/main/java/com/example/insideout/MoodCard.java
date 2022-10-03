package com.example.insideout;

public class MoodCard {
    private int moodImage; //better a ImageView???
    private String moodTitle;
    private String moodDescription;

    public MoodCard(int moodImage, String moodTitle, String moodDescription){
        this.moodImage = moodImage;
        this.moodTitle = moodTitle;
        this.moodDescription = moodDescription;
    }

    public int getMoodImage() {
        return moodImage;
    }

    public String getMoodTitle() {
        return moodTitle;
    }

    public String getMoodDescription() {
        return moodDescription;
    }
}
