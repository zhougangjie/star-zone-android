package razerdp.github.com.lib.network.base;

import razerdp.github.com.lib.base.AppjsException;

/**
 * Created by liushaoming on 2016/10/27.
 * <p>
 * 封装bmob的请求
 */

public abstract class BaseRequestClient<T> {
    private int requestType;
    private boolean showDialog;
    private OnResponseListener<T> onResponseListener;

    public void execute() {
        execute(false);
    }

    public void execute(boolean showDialog) {
        this.showDialog = showDialog;
        onResponseStart(requestType);
        executeInternal(requestType, showDialog);
    }

    protected abstract void executeInternal(final int requestType, final boolean showDialog);

    protected void onResponseStart(int requestType) {
        if (onResponseListener != null) {
            onResponseListener.onStart(requestType);
        }
    }

    protected void onResponseSuccess(T response, int requestType) {
        if (onResponseListener != null) {
            onResponseListener.onSuccess(response, requestType);
        }
    }

    protected void onResponseError(AppjsException e, int requestType) {
        if (onResponseListener != null) {
            onResponseListener.onError(e, requestType);
        }
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public OnResponseListener<T> getOnResponseListener() {
        return onResponseListener;
    }

    public BaseRequestClient setOnResponseListener(OnResponseListener<T> onResponseListener) {
        this.onResponseListener = onResponseListener;
        return this;
    }
}
