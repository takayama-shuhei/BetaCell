package betacell;


import java.util.ArrayList;

public class Cell {

	public static ArrayList<Granule> allGranules = new ArrayList<Granule>();	/**細胞内に存在する顆粒リスト**/
	public Environment env;
	public GranuleGeneratingSite ggs; /**顆粒生成部**/
	public InnerLayer  innerlayer;/**内部層**/
	public OuterLayer outerlayer;/**外部層**/
	public Membrane membrane;/**細胞膜**/

	public Cell(Environment env){
		this.env=env;
		GranuleGeneratingSite ggs = new GranuleGeneratingSite(env);		
		InnerLayer innerlayer = new InnerLayer(env);	
		OuterLayer outerlayer = new OuterLayer(env);
		Membrane membrane = new Membrane(env);
		this.ggs=ggs;
		this.innerlayer = innerlayer;
		this.outerlayer = outerlayer;
		this.membrane = membrane;
	}

	/**細胞内更新**/
	public void update(int t) {
		ggs.update(t);
		innerlayer.update(t);
		outerlayer.update(t);
		membrane.update(t);
	}



}
