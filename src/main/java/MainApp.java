import akka.actor.typed.ActorSystem;
public class MainApp {
    public static void main(String[] args) {
        ActorSystem<Actor1.Message> actorSystem1 =
                ActorSystem.create(Actor1.create(), "Actor1System");
        ActorSystem<Actor2.Message> actorSystem2 =
                ActorSystem.create(Actor2.create(), "Actor2System");
        actorSystem1.tell(new Actor1.Message("Start", actorSystem2));
    }
}
