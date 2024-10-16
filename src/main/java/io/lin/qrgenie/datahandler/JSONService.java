package io.lin.qrgenie.datahandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.lin.qrgenie.Participant;

public class JSONService {
    private static final GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
    private static final Gson gson = gsonBuilder.create();

    public static String serializer(Participant participant) {
        return gson.toJson(participant);
    }
}
