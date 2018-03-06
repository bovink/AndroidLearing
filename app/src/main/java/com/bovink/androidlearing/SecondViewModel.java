package com.bovink.androidlearing;

/**
 * @author fox
 * @since 2018/03/06
 */

public class SecondViewModel {

    private boolean isError;

    public SecondViewModel(boolean isError) {
        this.isError = isError;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

}
