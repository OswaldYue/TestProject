package com.mgw.two.chapter18;

/**
 * 真正做事情的
 * */
class Servant implements ActiveObject {


    @Override
    public Result makeString(int count, char fileChar) {

        char[] buf = new char[count];
        for (int i = 0; i < count; i++) {
            buf[i] = fileChar;
            try {
                Thread.sleep(10);
            }catch (Exception e) {

            }
        }
        return new RealResult(new String(buf));
    }

    @Override
    public void displayString(String text) {

        try {
            System.out.println("Display:" + text);
            Thread.sleep(10);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
