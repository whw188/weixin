package com.xzh.weixin.web.main;

public class StartupSauronWarning extends AbstractServer {

    public StartupSauronWarning(String[] anArgs) {
        super(anArgs);
    }

    public static void main(String... anArgs) throws Exception {
        try {
            new StartupSauronWarning(anArgs).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(Config config) {
    }

}
