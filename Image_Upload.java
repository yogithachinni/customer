package task;

public class Image_Upload {
    private String verb;
    private String key;
    private int y, m, d, h, mm, s;
    private String customer_id;
    private String camera_make;
    private String camera_model;
    
    public void set_verb(String verb) {
        this.verb = verb;
        return ;
    }
    
    public String get_verb() {
        return this.verb ;
    }
    
    public void set_key(String key) {
        this.key = key ;
        return ;
    }
    
    public String get_key() {
        return this.key ;
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
    
    public void set_customer_id(String customer_id) {
        this.customer_id = customer_id;
        return ;
    }
    
    public String get_customer_id() {
        return this.customer_id;
    }
    
    public void set_camera_make(String camera_make) {
        this.camera_make = camera_make ;
        return ;
    }
    
    public String get_camera_make() {
        return this.camera_make ;
    }
    
    public void set_camera_model(String camera_model) {
        this.camera_model = camera_model;
        return ;
    }
    
    public String get_camera_model() {
        return this.camera_model;
    }
    
    public String toString() {
        String results = "";
        results += "Key : " + this.key + " Customer id : " + this.customer_id;
        results += " camera_make : " + this.camera_make + " camera_model : " + this.camera_model + "\n";
        return results;
    }
}