package betacell;

import java.util.ArrayList;
public class Microtube {

	private Environment env;
	public Vector3 normalVector;//微小管の向きの単位ベクトル(顆粒移動の際に使用)
	public Vector3 start;//微小管の始点
	public Vector3 end;//微小管の終点
	private ArrayList<Granule> granules = new ArrayList<Granule>();//この微小管上に乗ってる顆粒たち
	public double act;


	Microtube(Environment env,Vector3 start,Vector3 end,Vector3 normalVector){
		this.start = start;
		this.end = end;
		this.env = env;
		this.normalVector = normalVector;

	}

	/**顆粒の座標をこの微小管の先頭にセット**/
	void PushGranule(Granule granule){
		Vector3 position = new Vector3(this.start.x(),this.start.y(),this.start.z());
		granule.position = position;
		this.granules.add(granule);
	}

	/**顆粒をこの微小管から排除**/
	void PopGranule(Granule granule){
		this.granules.remove(granule);
	}


}
