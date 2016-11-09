package betacell;


public class Granule {
	public Vector3 position;//顆粒の位置
	public Vector3 microtubeVector;
	public double lifeTime;
	public int num;
	public static int  number=0;
	private int fieldNumber;//顆粒のいる領域番号（外部層のみ）
	int NumberOfMicrotube;//載っている微小管の番号
	private Vector3 outerLayerVector = new Vector3(0.0,0.0,0.0);
	Environment env;

	Granule(Environment env,int NumberOfMicrotube){
		this.env=env;
		this.NumberOfMicrotube=NumberOfMicrotube;
		this.fieldNumber = 0;
		Vector3 tmp = new Vector3();
		tmp.Assignment(InnerLayer.tubes.get(NumberOfMicrotube).normalVector);
		this.microtubeVector=tmp;
		Vector3 position = new Vector3(InnerLayer.tubes.get(NumberOfMicrotube).start.x(),InnerLayer.tubes.get(NumberOfMicrotube).start.y(),InnerLayer.tubes.get(NumberOfMicrotube).start.z());
		this.position = position;
		this.lifeTime = env.lifeTime/env.deltaT;
		number++;
		num=number;

	}


	int getFieldNumber(){
		return this.fieldNumber;
	}

	void setFieldNumber(int n){
		this.fieldNumber = n;
	}


	/**各領域内での自分の動く方向ベクトル（領域が変わるごとに設定される）**/
	void setOuterVector(Vector3 tensor){
		this.outerLayerVector.Assignment(tensor);
	}

	/**ゲッター**/
	Vector3 getOuterLayerVector(){
		return this.outerLayerVector;
	}

}
