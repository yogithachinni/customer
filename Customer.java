package task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private String verb;
    private String key;
    private int y, m, d, h, mm, s;
    private String last_name;
    private String adr_city;
    private String adr_state;
    
    private double ltv;
    private double average_lifespan;
    private double expenditure;
    private int visit;
    
    Customer() {
        this.average_lifespan = 10;
        this.visit = 0;
        this.expenditure = 0;
    }
    
    public void set_verb(String verb) {
        this.verb = verb;
        return ;
    }
 
    public String get_verb() {
        return this.verb;
    }
    
    public void set_key(String key) {
        this.key = key;
        return ;
    }
    
    public String get_key() {
        return this.key;
    }
    
    public void set_y(int y) {
        this.y = y;
        return ;
    }
    
    public int get_y() {
        return this.y;
    }
    
    public void set_m(int m) {
        this.m = m;
        return ;
    }
    
    public int get_m() {
        return this.m;
    }
    
    public void set_d(int d) {
        this.d = d;
        return ;
    }
    
    public int get_d() {
        return this.d;
    }
    
    public void set_h(int h) {
        this.h = h;
        return ;
    }
    
    public int get_h() {
        return this.h;
    }
    
    public void set_mm(int mm) {
        this.mm = mm;
        return ;
    }
    
    public int get_mm() {
        return this.mm;
    }
    
    public void set_s(int s) {
        this.s = s;
        return ;
    }
    
    public int get_s() {
        return this.s;
    }
    
    public void set_last_name(String last_name) {
        this.last_name = last_name;
        return ;
    }
    
    public String get_last_name() {
        return this.last_name;
    }
    
    public void set_adr_city(String adr_city) {
        this.adr_city = adr_city;
        return ;
    }
    
    public String get_adr_city() {
        return this.adr_city;
    }
    
    public void set_adr_state(String adr_state) {
        this.adr_state = adr_state;
        return ;
    }
    
    public String get_adr_state() {
        return this.adr_state;
    }
    
    public String toString() {
        String results = "";
        results += "Key : " + this.key + " last_name : " + this.last_name;
        results += " adr_city : " + this.adr_city + " adr_state : " + this.adr_state;
        results += " LTV : " + String.valueOf(this.ltv) + "\n";
        return results;
    }
    
    public void up_visit() {
        this.visit ++;
        return ;
    }
    
    public int get_visit() {
        return this.visit;
    }
    
    public void up_expenditure(double val) {
        this.expenditure += val;
        return ;
    }
    
    public double get_expenditure() {
        return this.expenditure;
    }
    
    public long get_second(int y, int m, int d, int h, int mm, int s) {
        long ans;
        if (m < 3) {y --; m += 12;}
        ans = 365 * y + y / 4 - y / 100 + y / 400 + (153 * m + 2) / 5 + d;
        ans = ans * 24 * 3600 + h * 3600 + mm * 60 + s;
        return ans;
    }
    
    public void calc_ltv() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String current = dateFormat.format(date);
        String[] str = current.split("[/: ]");
        long start = get_second(this.y, this.m, this.d, this.h, this.mm, this.s);
        long end = get_second(Integer.valueOf(str[0]), Integer.valueOf(str[1]), Integer.valueOf(str[2]), 
                            Integer.valueOf(str[3]), Integer.valueOf(str[4]), Integer.valueOf(str[5]));
        double week = (end - start) * 1.0 / 3600 / 24 / 7;
        this.ltv = 52 * this.average_lifespan * (this.expenditure / week) * (this.visit / week);
    }
    
    public double get_ltv() {
        return this.ltv;
    }
    
    public boolean comp(int y, int m, int d, int h, int mm, int s) {
        if (this.y > y) return false ;
        if (this.y < y) return true ;
        if (this.m > m) return false ;
        if (this.m < m) return true ;
        if (this.d > d) return false ;
        if (this.d < d) return true ;
        if (this.h > h) return false ;
        if (this.h < h) return true ;
        if (this.mm > mm) return false ;
        if (this.mm < mm) return true ;
        if (this.s > s) return false ;
        return true ;
    }
}