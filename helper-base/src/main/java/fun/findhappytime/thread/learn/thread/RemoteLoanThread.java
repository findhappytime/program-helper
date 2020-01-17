package fun.findhappytime.thread.learn.thread;


import fun.findhappytime.thread.learn.service.RemoteLoanService;

public class RemoteLoanThread extends BaseCheckThread {

    public RemoteLoanThread(int uid) {
        super(uid);
    }

    @Override
    public Boolean call() throws Exception {
        return new RemoteLoanService().checkAuth(uid);
    }
}
