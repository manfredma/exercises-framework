package manfred.exercises.framework.stability.hystrix;

class RemoteServiceTestSimulator {

    private long wait;

    RemoteServiceTestSimulator(long wait) {
        this.wait = wait;
    }

    String execute() throws InterruptedException {
        Thread.sleep(wait);
        return "Success";
    }
}