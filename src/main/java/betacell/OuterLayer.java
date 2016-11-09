package betacell;

import java.util.ArrayList;
import java.util.List;

public class OuterLayer {

	private Environment env ;
	private Activation act;
	public static ArrayList<Granule> granules = new ArrayList<Granule>();
	public ArrayList<Field> tensorField = new ArrayList<Field>();

	OuterLayer(Environment env){
		this.env = env;
		createField();
	}

	/**外部層に顆粒を追加**/
	public void addGranule(ArrayList<Granule> newGranules){
		granules.addAll(newGranules);
	}

	/**外部層内にある全部の顆粒を動かす**/
	public void update(int t) {
        List<Granule> deadGranules = new ArrayList<>();
		for (Granule granule : granules) {
			int check = 1;
			check = checkField(granule);//自分のいる領域をチェックして
			if(check == 1)tensorField.get(granule.getFieldNumber()).setTensor(granule,env.theta);//今いる領域の規則にしたがって，この領域内での顆粒の動きテンソルを設定する(直交座標系)
			granule.position.add(granule.getOuterLayerVector());//update
			granule.lifeTime--;
			if (granule.lifeTime < 0) {
                deadGranules.add(granule);
			}
			if(granule.position.length() > env.R3){
				/** ここに待ち時間の処理 **/
				Membrane.Granules.add(granule);
                deadGranules.add(granule);
			}
		}

        for (Granule granule : deadGranules) {
            granules.remove(granule);
        }
	}

	/**顆粒の場所によって，領域番号を登録する**/
	public int checkField(Granule granule){
		int r=0,theta=0,phi=0,number=0;
		Vector3 vec = granule.position.polar();
		r = (int) ((vec.x()-6210.9) / env.rLength);
		theta = (int)(vec.y()/env.thetaAngle);
		phi = (int)(vec.z()/env.phiAngle);
		number = (int)(phi + theta*(2*Math.PI/env.phiAngle)+r*(360/env.tmpPhi*180/env.tmpTheta));

		if(granule.getFieldNumber()==0){
			granule.setFieldNumber(number);
			return 1;
		}//初期状態は更新
		if(granule.getFieldNumber() != number){
			granule.setFieldNumber(number);
			return 1;
		}//領域移動があれば更新
		else return 0;//領域移動なしは更新なし

	}//領域番号の変更があれば，１を返す．なければ０を返す．

	/**領域を作成する**/
	public void createField(){
		for(int r=0;r < Math.ceil((env.R3-env.R2)/env.rLength);r++){
			for(int theta =0;theta < Math.PI/env.thetaAngle;theta++){
				for(int phi =0;phi < 2*Math.PI/env.phiAngle;phi++){
					double thetaAngle = (env.tmpTheta*theta+env.tmpTheta/2)/180*Math.PI;//[rad]
					double phiAngle = (env.tmpPhi*phi + env.tmpPhi/2)/180*Math.PI;//[rad]
					Vector3 mainTensorR = new Vector3(env.outerSpeed,thetaAngle,phiAngle);//とりあえず中心テンソルは領域のど真ん中外向き
					Field  field = new Field(mainTensorR);
					tensorField.add(field);
				}
			}
		}
	}


}
