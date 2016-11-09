package betacell;

import java.util.ArrayList;
import java.util.Random;
public class InnerLayer {
	/**微小管は0から番号をつける**/
	private Environment env;
	private Activation act;
	public static ArrayList<Granule> innerLayerGranules = new ArrayList<Granule>();//微小管リスト
	public static ArrayList<Microtube> tubes = new ArrayList<Microtube>();//微小管リスト
	/**ランダム関数使うときはそれぞれ別シードを使用しよう**/
	Random rnd = new Random(5);
	InnerLayer(Environment env){
		this.env=env;
		this.act = new Activation(env,env.inner_stimulation_start,env.inner_stimulation_end,env.inner_τ1,env.inner_τ2);
		double thetaInterval = Math.PI/env.numberOfMicrotube;
		double phiInterval = 2*Math.PI/env.numberOfMicrotube;

		if(env.testflag==1){
			/**微小管配置について，極座標で偏りなく配置した後，直交座標で始点と終点を記録**/
			int num=0;
			for(int theta =0;theta < Math.PI/env.thetaAngle;theta++){
				for(int phi =0;phi < 2*Math.PI/env.phiAngle;phi++){
					double thetaAngle = (env.tmpTheta*theta+env.tmpTheta/2)/180*Math.PI;//[rad]
					double phiAngle = (env.tmpPhi*phi + env.tmpPhi/2)/180*Math.PI;//[rad]
					Vector3 start = new Vector3();
					Vector3 end = new Vector3();
					Vector3 normal = new Vector3();
					Vector3 tmpStart = new Vector3();
					Vector3 tmpEnd = new Vector3();

					tmpStart.Assignment(env.R1,thetaAngle,phiAngle);
					start.Assignment(tmpStart.Cartesian());
					tmpEnd.Assignment(env.R2,thetaAngle,phiAngle);
					end.Assignment(tmpEnd.Cartesian());
					normal.Assignment(end);
					normal.subtract(start);
					normal = normal.normalize();//単位ベクトル取得
					Microtube tube = new Microtube(env,start,end,normal);
					tubes.add(tube);
					num++;
				}
			}
			env.numberOfMicrotube = num;
		}else{
			/**球面状に一様分布**/
			for(int i=0;i<env.numberOfMicrotube;i++){
				Vector3 start = new Vector3();
				Vector3 end = new Vector3();
				Vector3 normal = new Vector3();
				double x,y,z;
				double ran = 2*rnd.nextDouble()-1;
				double radianT = Math.toRadians(180*(2*rnd.nextDouble()-1));

				x=Math.sqrt(1-ran*ran)*Math.cos(radianT);
				y=Math.sqrt(1-ran*ran)*Math.sin(radianT);
				z=ran;
				//System.out.println(x+" "+y+" "+z);
				start.Assignment(env.R1*x,env.R1*y,env.R1*z);
				end.Assignment(env.R2*x,env.R2*y,env.R2*z);
				//		Vector3 check ;
				//		check = start.polar();
				//		check.show();
				normal.Assignment(end);
				normal.subtract(start);
				normal = normal.normalize();//単位ベクトル取得
				Microtube tube = new Microtube(env,start,end,normal);
				tubes.add(tube);
			}

		}

	}

	/**全微小管上の全顆粒を動かす
	 * @param シミュレーション時刻
	 * **/
	void update(int t){
		for(int i=0;i<innerLayerGranules.size();i++){
			innerLayerGranules.get(i).position.add(innerLayerGranules.get(i).microtubeVector.getmultiplyScalar((env.innerVelocity+env.deltaInnerVelocity*act.getActivation(t))*env.deltaT));//速度ベクトルに従って顆粒の座標を更新
			innerLayerGranules.get(i).lifeTime--;

			if(innerLayerGranules.get(i).lifeTime<0){
				innerLayerGranules.remove(i);
				System.out.println("delete");
			}
			if(innerLayerGranules.get(i).position.length() > env.R2){
				OuterLayer.granules.add(innerLayerGranules.get(i));
				innerLayerGranules.remove(i);		
			}

		}

	}

	/**n番目の微小管に顆粒を追加**/
	void pushGranule(ArrayList<Granule> granules){
		for(int i=0;i<granules.size();i++){
			tubes.get(granules.get(i).NumberOfMicrotube).PushGranule(granules.get(i));
		}
	}





}
