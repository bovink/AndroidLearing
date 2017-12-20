package com.bovink.androidlearing.model;

import java.util.List;

/**
 * @author fox
 * @since 2017/12/20
 */

public class RecognizeResultModel {

    private int err_no;
    private String err_msg;
    private String corpus_no;
    private String sn;
    private List<String> result;

    public int getErr_no() {
        return err_no;
    }

    public void setErr_no(int err_no) {
        this.err_no = err_no;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getCorpus_no() {
        return corpus_no;
    }

    public void setCorpus_no(String corpus_no) {
        this.corpus_no = corpus_no;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
