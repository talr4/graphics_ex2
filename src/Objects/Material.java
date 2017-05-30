package Objects;


public class Material {
	
	float dr;
	float dg;
	float db;
	float sr;
	float sg;
	float sb;
	float rr;
	float rg;
	float rb;

	private float phong;
	private float transparency;
	
	
	
	public Material(float dr, float dg, float db, float sr, float sg, float sb, float rr, float rg, float rb,
			float phong, float transparency) {
		super();
		this.dr = dr;
		this.dg = dg;
		this.db = db;
		this.sr = sr;
		this.sg = sg;
		this.sb = sb;
		this.rr = rr;
		this.rb = rb;
		this.rg = rg;
		this.phong = phong;
		this.transparency = transparency;
	}
	public float getDr() {
		return dr;
	}
	public void setDr(float dr) {
		this.dr = dr;
	}
	public float getDg() {
		return dg;
	}
	public void setDg(float dg) {
		this.dg = dg;
	}
	public float getDb() {
		return db;
	}
	public void setDb(float db) {
		this.db = db;
	}
	public float getSr() {
		return sr;
	}
	public void setSr(float sr) {
		this.sr = sr;
	}
	public float getSg() {
		return sg;
	}
	public void setSg(float sg) {
		this.sg = sg;
	}
	public float getSb() {
		return sb;
	}
	public void setSb(float sb) {
		this.sb = sb;
	}
	public float getRr() {
		return rr;
	}
	public void setRr(float rr) {
		this.rr = rr;
	}
	public float getRb() {
		return rb;
	}
	public void setRb(float rb) {
		this.rb = rb;
	}
	public float getRg() {
		return rg;
	}
	public void setRg(float rg) {
		this.rg = rg;
	}
	public float getPhong() {
		return phong;
	}
	public void setPhong(float phong) {
		this.phong = phong;
	}
	public float getTransparency() {
		return transparency;
	}
	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}
	
	
	
	
	
	
}
