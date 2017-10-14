package com.trusindo.april.model;

import android.net.Uri;

/**
 * Created by jakalesmana on 10/14/17.
 */

public class SurveyPicture {

    private Uri imageUri;
    private String imageDescription;

    public SurveyPicture() {
        setImageUri(null);
        setImageDescription("");
    }

    public SurveyPicture(Uri uri, String desc) {
        setImageUri(uri);
        setImageDescription(desc);
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }
}
