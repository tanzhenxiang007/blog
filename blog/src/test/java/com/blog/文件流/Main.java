package com.blog.文件流;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Flow> flows = new ArrayList<Flow>();
        InputStream f1 = new FileInputStream("D:\\bishe\\blog-master\\blog\\src\\test\\java\\com\\blog\\文件流\\cs.txt");
        InputStreamReader reader = new InputStreamReader(f1);
        BufferedReader br = new BufferedReader(reader);
        br.readLine();  //读取第一行且不作处理
        String strTmp = "";
        while ((strTmp = br.readLine()) != null) {
            String[] values = strTmp.split(",");
            int flow_id = Integer.parseInt(values[0]), flow_bw = Integer.parseInt(values[1]);
            int start = Integer.parseInt(values[2]), cost = Integer.parseInt(values[3]);
            Flow flow = new Flow(flow_id, flow_bw, start, cost);
            flows.add(flow);
        }
        br.close();
        System.out.println(flows.size());
    }
}

class Flow {
    private int id; //流id
    private int bw; //流带宽
    private int start;  //起始时间
    private int cost;   //发送时间

    public Flow(int id, int bw, int start, int cost) {
        this.id = id;
        this.bw = bw;
        this.start = start;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBw() {
        return bw;
    }

    public void setBw(int bw) {
        this.bw = bw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
