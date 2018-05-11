package com.xzh.weixin.web.main;

public class StartupWeixin extends AbstractServer {

    public StartupWeixin(String[] anArgs) {
        super(anArgs);
    }

    public static void main(String... anArgs) throws Exception {
        try {
            new StartupWeixin(anArgs).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(Config config) {
    }

}
