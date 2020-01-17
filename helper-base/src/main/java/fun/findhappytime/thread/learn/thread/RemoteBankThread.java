package fun.findhappytime.thread.learn.thread;


import fun.findhappytime.thread.learn.service.RemoteBankService;

public class RemoteBankThread extends BaseCheckThread {

    public RemoteBankThread(int uid) {
        super(uid);
    }

    @Override
    public Boolean call() throws Exception {
        return new RemoteBankService().checkCredit(uid);
    }
}
