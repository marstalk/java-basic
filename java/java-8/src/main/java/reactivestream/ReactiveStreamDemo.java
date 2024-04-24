package reactivestream;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * jdk>=9
 * jdk9中通过Flow实现了reactive stream规范。
 */
public class ReactiveStreamDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. 发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

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
                // back-pressure core logic
                subscription.request(10);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError:" + throwable.getMessage());
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete:");
            }
        };

        // 3. build relationship with subscriber
        publisher.subscribe(subscriber);

        for (int i = 0; i < 100; i++) {
            // 4. publish item
            publisher.submit("hello reactive stream " + i);
        }

        // asynchronized

        Thread.currentThread().join();

    }
}
