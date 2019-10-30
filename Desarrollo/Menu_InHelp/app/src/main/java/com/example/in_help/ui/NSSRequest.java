package com.example.in_help.ui;

class NSSRequest {
    public NSSRequest(String tx_id) {
        this.tx_id = tx_id;
    }

    public NSSRequest() {
        this.tx_id = tx_id;
    }

    public String getTx_id() {
        return tx_id;
    }

    public void setTx_id(String tx_id) {
        this.tx_id = tx_id;
    }

    @Override
    public String toString() {
        return "NSSRequest{" +
                "tx_id='" + tx_id +
                '}';
    }

    String tx_id;
}
