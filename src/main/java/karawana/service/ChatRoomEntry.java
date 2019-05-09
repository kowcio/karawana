//package karawana.service;
//
//import karawana.entities.Group;
//import org.springframework.http.codec.ServerSentEvent;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.ReplayProcessor;
//
//public class ChatRoomEntry {
//
//    private ReplayProcessor<ServerSentEvent<Group>> replayProcessor =
//            ReplayProcessor.<ServerSentEvent<Group>>create(100);
//
//    public void onPostMessage(Group msg) {
//        ServerSentEvent<Group> event = ServerSentEvent.builder(msg)
//                .event("chat")
//                .id(String.valueOf(msg.getId())).build();
//        replayProcessor.onNext(event);
//    }
//
//    public Flux<ServerSentEvent<Group>> subscribe(String lastEventId) {
//        Integer lastId = (lastEventId != null) ? Integer.parseInt(lastEventId) : null;
//        return replayProcessor.filter(x -> lastId == null || x.data().getId()> lastId);
//    }
//}