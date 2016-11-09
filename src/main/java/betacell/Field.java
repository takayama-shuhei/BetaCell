package betacell;

import java.util.Random;

public class Field {

	public static int number =0;
	public int num;
	public Vector3 mainTensorR = new Vector3();//中心テンソル（極座標）
	public Vector3 mainTensorX = new Vector3();//中心テンソル（直交座標）


	public Field(Vector3 mainTensorR2) {
		this.mainTensorR = mainTensorR2;
		this.mainTensorX.Assignment(mainTensorR.Cartesian());
		number++;
		number=num;
	}//コンストラクタ


	/*顆粒ごとにテンソルを設定する*/
	public void setTensor(Granule granule,double devision){
		Random rnd = new Random();
		/**角度は度で入れる**/
		double theta= devision;//どれだけ中心からずれるのかを算出する用
		double phi = (double)rnd.nextInt(360);//ランダム
		Vector3 tensor = new Vector3(mainTensorR.x(),mainTensorR.y()+Math.toRadians(theta),mainTensorR.z());//θずれたテンソルを作る
		granule.setOuterVector(tensor.Cartesian().Rodrigues(mainTensorX.normalize(),Math.toRadians(phi)));//直交座標に変換 //φ回転させる//顆粒の動く方向として登録する
	}



	/**セッター**/
	public void setMainTensorR(double r,double theta,double phi){
		this.mainTensorR.Assignment(r, theta, phi);
	}

	public void setMainTensorX(double x,double y,double z){
		this.mainTensorX.Assignment(x, y, z);
	}




}
