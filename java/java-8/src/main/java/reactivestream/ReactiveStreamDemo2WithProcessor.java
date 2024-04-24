package reactivestream;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * jdk>=9
 * jdk9中通过Flow实现了reactive stream规范。
 */
public class ReactiveStreamDemo2WithProcessor {
    public static void main(String[] args) throws InterruptedException {
        // 1. 发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // 1.1 build subscription relationship with myProcessor
        MyProcessor myProcessor = new MyProcessor();
        publisher.subscribe(myProcessor);

        // 2. create a FINAL subscriber
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                // callback after subscription relationship is build
                // save the subscription
                System.out.println("on Subscribe:" + subscription);
                this.subscription = subscription;
                // after subscribe, request one item.
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("onNext:" + item);
                // tell publisher to publish next 10 item
                subscription.request(10);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError:" + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete:");
            }
        };

        // 3. build relationship
        myProcessor.subscribe(subscriber);

        for (int i = 0; i < 100; i++) {
            // 4. publish item
            publisher.submit("hello reactive stream " + i);
        }

        // asynchronized
        Thread.currentThread().join();
    }

    static class MyProcessor extends SubmissionPublisher<String> implements Flow.Subscriber<String> {
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("processor onSubscribe: " + subscription);
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            System.out.println("processor onNext: " + item);
            // middle business
            String upperCase = item.toUpperCase();

            // send to subscriber
            this.submit(upperCase);

            // back pressure core logic
            subscription.request(10);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("processor onError: " + throwable);
        }

        @Override
        public void onComplete() {
            System.out.println("processor onComplete");
        }
    }
}
