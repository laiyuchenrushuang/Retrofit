package com.ly.retrofitdemo;

/**
 * Created by ly on 2019/8/29 14:10
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class Translation {
    private int status;

    private Content content;

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setContent(Content content){
        this.content = content;
    }
    public Content getContent(){
        return this.content;
    }

    public class Content {
        private String from;

        private String to;

        private String vendor;

        private String out;

        private String ciba_use;

        private String ciba_out;

        private int err_no;

        public void setFrom(String from) {
            this.from = from;
        }

        public String getFrom() {
            return this.from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getTo() {
            return this.to;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getVendor() {
            return this.vendor;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public String getOut() {
            return this.out;
        }

        public void setCiba_use(String ciba_use) {
            this.ciba_use = ciba_use;
        }

        public String getCiba_use() {
            return this.ciba_use;
        }

        public void setCiba_out(String ciba_out) {
            this.ciba_out = ciba_out;
        }

        public String getCiba_out() {
            return this.ciba_out;
        }

        public void setErr_no(int err_no) {
            this.err_no = err_no;
        }

        public int getErr_no() {
            return this.err_no;
        }
    }
}


