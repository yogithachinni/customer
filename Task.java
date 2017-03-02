package task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import static java.util.Collections.swap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task {
    public static int sz = 1000;
    public static HashMap<String, Integer> mp;
    public static int customer_cnt, image_cnt, site_cnt, order_cnt;
    public static Customer[] cus;
    public static Image_Upload[] ima;
    public static Site_Visit[] sit;
    public static Order[] ord;
    
    public static int get_num(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i ++) 
            res = res * 10 + (s.charAt(i) - '0');
        return res;
    }
    public static boolean Ingest() throws FileNotFoundException {
        customer_cnt = image_cnt = site_cnt = order_cnt = 0;
        cus = new Customer[sz];
        ima = new Image_Upload[sz];
        sit = new Site_Visit[sz];
        ord = new Order[sz];
        mp = new HashMap<String, Integer> ();
        FileInputStream inputstream = new FileInputStream("input\\input.txt");
        Scanner sc = new Scanner(inputstream);
        String input = "";
        while (sc.hasNext()) {
            input += sc.next();
        }
        
        int ID;
        String s = new String();
        int cnt = 0;
        for (int i = 0; i < input.length(); i ++) {
            if (cnt == 0 && input.charAt(i) != '{') continue ;
            if (input.charAt(i) == '{') {
                if (cnt == 0) s = new String();
                cnt ++;
            } else if (input.charAt(i) == '}') {
                cnt --;
                if (cnt == 0) {
                    String[] pat = s.split(",");
                    String[] subpat = pat[0].split(":");
                    if (subpat[1].compareTo("CUSTOMER") == 0) {
                        String[] ss = new String[10];
                        ss = pat[1].split(":");
                        String verb = ss[1];
                        ss = pat[2].split(":");
                        String key = ss[1];
                        String dat = pat[3].substring(11);
                        ss = pat[4].split(":");
                        String last_name = ss[1];
                        ss = pat[5].split(":");
                        String adr_city = ss[1];
                        ss = pat[6].split(":");
                        String adr_state = ss[1];
                        if (verb.compareTo("NEW") == 0) {
                            if (mp.containsKey(key)) {
                                System.out.println("The customer " + key + " exists already.");
                                return false ;
                            }
                            mp.put(key, customer_cnt);
                            ID = customer_cnt ++;
                        } else {
                            if (!mp.containsKey(key)) {
                                System.out.println("The customer " + key + " does not exist in the system.");
                                return false ;
                            }
                            ID = mp.get(key);
                        }
                        cus[ID] = new Customer();
                        cus[ID].set_verb(verb);
                        cus[ID].set_key(key);
                        cus[ID].set_last_name(last_name);
                        cus[ID].set_adr_city(adr_city);
                        cus[ID].set_adr_state(adr_state);
                        String[] d = dat.split("[-:T.Z]");
                        cus[ID].set_y(get_num(d[0]));
                        cus[ID].set_m(get_num(d[1]));
                        cus[ID].set_d(get_num(d[2]));
                        cus[ID].set_h(get_num(d[3]));
                        cus[ID].set_mm(get_num(d[4]));
                        cus[ID].set_s(get_num(d[5]));
                    } else if (subpat[1].compareTo("SITE_VISIT") == 0) {
                        String[] ss = new String[10];
                        ss = pat[1].split(":");
                        String verb = ss[1];
                        ss = pat[2].split(":");
                        String key = ss[1];
                        String dat = pat[3].substring(11);
                        ss = pat[4].split(":");
                        String customer_id = ss[1];
                        String tags = pat[5].substring(5);
                        
                        if (!mp.containsKey(customer_id)) {
                            System.out.println("The customer " + customer_id + " exists already.");
                            return false ;
                        }
                        ID = site_cnt ++;
                        sit[ID] = new Site_Visit();
                        sit[ID].set_customer_id(customer_id);
                        sit[ID].set_verb(verb);
                        sit[ID].set_key(key);
                        sit[ID].set_tags(tags);
                        String[] d = dat.split("[-:T.Z]");
                        sit[ID].set_y(get_num(d[0]));
                        sit[ID].set_m(get_num(d[1]));
                        sit[ID].set_d(get_num(d[2]));
                        sit[ID].set_h(get_num(d[3]));
                        sit[ID].set_mm(get_num(d[4]));
                        sit[ID].set_s(get_num(d[5]));
                    } else if (subpat[1].compareTo("IMAGE") == 0) {
                        String[] ss = new String[10];
                        ss = pat[1].split(":");
                        String verb = ss[1];
                        ss = pat[2].split(":");
                        String key = ss[1];
                        String dat = pat[3].substring(11);
                        ss = pat[4].split(":");
                        String customer_id = ss[1];
                        ss = pat[5].split(":");
                        String camera_make = ss[1];
                        ss = pat[6].split(":");
                        String camera_model = ss[1];
                        
                        if (!mp.containsKey(customer_id)) {
                            System.out.println("The customer " + customer_id + " exists already.");
                            return false ;
                        }
                        ID = image_cnt ++;
                        ima[ID] = new Image_Upload();
                        ima[ID].set_customer_id(customer_id);
                        ima[ID].set_verb(verb);
                        ima[ID].set_key(key);
                        ima[ID].set_camera_make(camera_make);
                        ima[ID].set_camera_model(camera_model);
                        String[] d = dat.split("[-:T.Z]");
                        ima[ID].set_y(get_num(d[0]));
                        ima[ID].set_m(get_num(d[1]));
                        ima[ID].set_d(get_num(d[2]));
                        ima[ID].set_h(get_num(d[3]));
                        ima[ID].set_mm(get_num(d[4]));
                        ima[ID].set_s(get_num(d[5]));
                    } else {
                        String[] ss = new String[10];
                        ss = pat[1].split(":");
                        String verb = ss[1];
                        ss = pat[2].split(":");
                        String key = ss[1];
                        String dat = pat[3].substring(11);
                        ss = pat[4].split(":");
                        String customer_id = ss[1];
                        ss = pat[5].split(":");
                        String[] sss = ss[1].split("U");
                        double total_amount = Double.valueOf(sss[0]);
                        
                        if (!mp.containsKey(customer_id)) {
                            System.out.println("The customer " + customer_id + " exists already.");
                            return false ;
                        }
                        ID = order_cnt ++;
                        ord[ID] = new Order();
                        ord[ID].set_customer_id(customer_id);
                        ord[ID].set_verb(verb);
                        ord[ID].set_key(key);
                        ord[ID].set_total_amount(total_amount);
                        String[] d = dat.split("[-:T.Z]");
                        ord[ID].set_y(get_num(d[0]));
                        ord[ID].set_m(get_num(d[1]));
                        ord[ID].set_d(get_num(d[2]));
                        ord[ID].set_h(get_num(d[3]));
                        ord[ID].set_mm(get_num(d[4]));
                        ord[ID].set_s(get_num(d[5]));
                    }
                }
            } else if (input.charAt(i) != '\"') {
                s += input.charAt(i);
            }
        }
        return true ;
    }
    
    public static void TopXSimpleLTVCustomers(int x) {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("output\\output.txt");
            PrintStream out = new PrintStream(fout);
            for (int i = 0; i < site_cnt; i ++) {
                int ID = mp.get(sit[i].get_customer_id());
                cus[ID].up_visit();
            }   for (int i = 0; i < order_cnt; i ++) {
            int ID = mp.get(ord[i].get_customer_id());
            cus[ID].up_expenditure(ord[i].get_total_amount());
        }   for (int i = 0; i < customer_cnt; i ++) {
            cus[i].calc_ltv();
        }   int[] I = new int[1000];
        for (int i = 0; i < customer_cnt; i ++) I[i] = i;
            for (int i = 0; i < customer_cnt; i ++) for (int j = i + 1; j < customer_cnt; j ++) {
                if (cus[I[i]].get_ltv() < cus[I[j]].get_ltv()) {
                    int tmp = I[i];
                    I[i] = I[j];
                    I[j] = tmp;
                }
            }   if (customer_cnt < x) out.println("There are no " + String.valueOf(x) + " customers.");
        for (int i = 0; i < x && i < customer_cnt; i ++) out.println(cus[I[i]].toString());
            return ;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            if (Ingest() == false) {
                System.out.println("There is error in input data");
                return ;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TopXSimpleLTVCustomers(10);
    }   
}