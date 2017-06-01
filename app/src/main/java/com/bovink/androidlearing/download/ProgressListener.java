package com.bovink.androidlearing.download;

/**
 * com.bovink.androidlearing.download
 *
 * @author bovink
 * @since 2017/6/1
 */

public interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
