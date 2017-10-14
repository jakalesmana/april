package com.trusindo.april.tools;

import com.trusindo.april.error.APIError;

/**
 * Created by jakalesmana on 9/12/17.
 */

public interface HTTPIntListener {
    void onError(APIError aPIError);
    void onStart();
    void onSuccess(String str);
}
