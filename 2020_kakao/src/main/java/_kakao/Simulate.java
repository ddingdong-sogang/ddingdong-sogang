package _kakao;

import org.json.JSONObject;

public class Simulate {
    private String status;
    private int time;
    private int failRequest;
    private String totalDistance;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getFailRequest() {
        return failRequest;
    }

    public void setFailRequest(int failRequest) {
        this.failRequest = failRequest;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }
    @Override
    public String toString(){
        return "status : " + status +
                " time : " + time +
                " failed_requests_count : " + failRequest +
                " distance : " + totalDistance;
    }
}
