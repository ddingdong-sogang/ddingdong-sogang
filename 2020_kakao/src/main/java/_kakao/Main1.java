package _kakao;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main1 {

    ArrayList[] arrayLists = new ArrayList[50];
    ArrayList[] heights = new ArrayList[50];

    int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] a = new int[5][2];
    }

    public void b_t(int targetHeight, int currentHeight, HashSet<Integer> hashSet){
        if(targetHeight == hashSet.size()){
            bfs(hashSet);
            return;
        }


        for(int i = 0; i < heights[currentHeight].size(); i++){
            HashSet<Integer> nextHashset = new HashSet<>(hashSet);
            nextHashset.add((Integer) heights[currentHeight].get(i));
            b_t(targetHeight, currentHeight + 1, nextHashset);
        }

    }

    public void bfs(HashSet<Integer> hashSet){
        int count = 0,
                height = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while(!queue.isEmpty()){
            int qSize = queue.size();

            while(qSize-- != 0){
                int current = queue.poll();
                count++;

                for(int i = 0; i < arrayLists[current].size(); i++){
                    if(!hashSet.contains(arrayLists[current].get(i))){
                        queue.add((Integer) arrayLists[current].get(i));
                    }
                }
            }
        }
        answer = Math.min(answer, count);
        return;
    }

    public int solution(int n, int[][] edges){

        for(int i = 0; i < 50; i++) {
            arrayLists[i] = new ArrayList<Integer>();
            heights[i] = new ArrayList<Integer>();
        }

        for(int[] edge :edges) {
            arrayLists[edge[0]].add(edge[1]);
        }

        int height = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        heights[height].add(0);
        while(!q.isEmpty()){
            int qSize = q.size();
            height++;

            while(qSize-- != 0){
                int curr = q.poll();

                for(int i = 0; i < arrayLists[curr].size(); i++){
                    heights[height].add(arrayLists[curr].get(i));
                    q.add((Integer) arrayLists[curr].get(i));
                }
            }
        }



        HashSet<Integer> hashSet = new HashSet<>();
        b_t(height, 1 ,hashSet);

        return answer;
    }
}
