import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.PreRestart;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.javadsl.Behaviors;

public class Actor1 {

    public static class Message {
        public final String content;
        public final ActorRef<Actor2.Message> replyTo;
        public Message(String content, ActorRef<Actor2.Message> replyTo) {
            this.content = content;
            this.replyTo = replyTo;
        }
    }
    public static Behavior<Message> create() {
        return Behaviors.setup(context -> {
            return Behaviors.receive(Message.class)
                    .onMessage(Message.class, (actorMessage) -> {
                        System.out.println("Received message: " + actorMessage.content);
                        actorMessage.replyTo.tell(new Actor2.Message("Hi from Actor1", context.getSelf()));
                        return Behaviors.same();
                    })
                    .build();
        });
    }
}
