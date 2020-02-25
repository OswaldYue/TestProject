package com.mgw.two.chapter18;

/**
 *
 * {@link ActiveObject#makeString(int, char)}
 * */
public class MakeStringRequest extends MethodRequest {

    private final int count;

    private final char fileChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult,int count,char fileChar) {
        super(servant, futureResult);
        this.fileChar = fileChar;
        this.count = count;
    }


    @Override
    public void execute() {
        Result result = servant.makeString(count, fileChar);
        futureResult.setResult(result);
    }
}
