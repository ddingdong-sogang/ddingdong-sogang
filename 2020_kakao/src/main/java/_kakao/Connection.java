package _kakao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Connection {
    private static Connection instance = null;
    private static String connType = "Content-Type";
    private static String connJson = "application/json";
    private static String connAuth = "Authorization";

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    /**
     * Get /trucks
     * Authorization: {Token}
     * Content-Type: application/type
     */

    public JSONObject trucks() {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try {
            URL url = new URL(Global.HOST_URL + Global.GET_TRUCKS);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 :: Cant");
            } else if (responseCode == 401) {
                System.out.println("401 :: X-auth-Token Header Error");
            } else if (responseCode == 500) {
                System.out.println("500 :: Server Error Please Contact");
            } else {
                // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("openConnection Error");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Not JSON Format response");
            e.printStackTrace();
        }
        return responseJson;
    }

    // PUT /simulate
    //      Authorization: {Token}
    //      Content-Type: application/type
    public JSONObject simulate(JSONArray commandArrays) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL(Global.HOST_URL + Global.PUT_SIMULATE);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("PUT");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            JSONObject commands = new JSONObject();
            commands.put("commands", commandArrays);
            System.out.println("commandArrays = " + commandArrays);
            bw.write(commands.toString());
            bw.flush();

            bw.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 :: Can't");
            } else if (responseCode == 401) {
                System.out.println("401 :: X-auth-Token Header Error");
            } else if (responseCode == 500) {
                System.out.println("500 :: Server Error Please Contact");
            } else {
                // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("openConnection Error");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Not JSON Format response");
            e.printStackTrace();
        }
        return responseJson;
    }

    // Get /locations
    //      Authorization: {Token}
    public JSONObject locations() {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL(Global.HOST_URL + Global.GET_LOCATIONS);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if (responseCode == 401) {
                System.out.println("401 :: X-Auth-Token Header Wrong");
            } else if (responseCode == 500) {
                System.out.println("500 :: Server Error Contact Us");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("not JSON FORMAT RESPONSE");
            e.printStackTrace();
        }
        return responseJson;
    }

    // GET /score
    public JSONObject score() {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        int response = 0;

        try {
            URL url = new URL(Global.HOST_URL + Global.GET_SCORE);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            responseJson = new JSONObject(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseJson;
    }

    // POST /start/{problemid}
    public String start(int problemId) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        String response = null;

        try {
            URL url = new URL(Global.HOST_URL + Global.POST_START + "?problem=" + problemId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-Auth-Token", Global.X_AUTH_TOKEN);
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) { // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
                response = responseJson.getString("auth_key");

            } else {
                response = String.valueOf(responseCode);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("response = " + response);
        return response;

    }
}
