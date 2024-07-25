import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

public class Actor2 {

    public static class Message {
        public final String content;
        public final ActorRef<Actor1.Message> replyTo;

        public Message(String content, ActorRef<Actor1.Message> replyTo) {
            this.content = content;
            this.replyTo = replyTo;
        }
    }

    public static Behavior<Message> create() {
        return Behaviors.setup(context -> {
            return Behaviors.receive(Message.class)
                    .onMessage(Message.class, (actorMessage) -> {
                        System.out.println("Received message: " + actorMessage.content);
                        actorMessage.replyTo.tell(new Actor1.Message("Hi from Actor2", context.getSelf()));
                        return Behaviors.same();
                    })
                    .build();
        });
    }
}
