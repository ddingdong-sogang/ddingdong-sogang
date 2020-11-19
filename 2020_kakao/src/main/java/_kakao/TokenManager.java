package _kakao;

import java.io.*;

public class TokenManager {
    private static TokenManager instance = null;
    private String token = "";
    private String fileName = "C:\\Users\\KimJaeWon\\Desktop\\2020_kakao\\src\\main\\java\\_kakao\\res\\token";

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public String getToken() {
        return this.token;
    }

    public synchronized String createToken(int problemId) {
        String token = null;
        String response = Connection.getInstance().start(problemId);

        if (response.equals("400")) {
            System.out.println("400 :: Parameter Error ");
        } else if (response.equals("401")) {
            System.out.println("401 :: X-Auth-Token Header Error");
        } else if (response.equals("500")) {
            System.out.println("500 :: Server Error Please Contact");
        } else {
            saveTokenFile(response);
            token = response;
            response = "200";
        }

        this.token = token;
        return response;
    }

    private void saveTokenFile(String token) {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
            bw.write(token);
            bw.flush();

            bw.close();
        } catch (IOException e) {
            System.out.println("?");
            e.printStackTrace();
        }
    }

    private String loadTokenFile() {
        String token = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./res/token")));
            token = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }
}
