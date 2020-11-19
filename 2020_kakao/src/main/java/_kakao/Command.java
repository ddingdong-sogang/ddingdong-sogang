package _kakao;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Command {
    private int truck_id;
    private ArrayList<Integer> command;

    public Command(int truck_id, ArrayList<Integer> commands) {
        this.truck_id = truck_id;
        this.command = commands;
    }

    public JSONObject getJsonCommandData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("truck_id", truck_id);
            if (command != null) {
                jsonObject.put("command", command);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
