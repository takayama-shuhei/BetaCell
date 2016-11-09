package betacell;

import java.util.ArrayList;
import java.util.Random;

public class GranuleGeneratingSite {

	private Environment env;
	private Activation act;
	public ArrayList<Granule> generateGranules = new ArrayList<Granule>();//生成顆粒リスト（マイステップ更新する）
	/**ランダム関数使うときはそれぞれ別シードを使用しよう**/
	Random rnd = new Random(2);

	GranuleGeneratingSite(Environment env){
		this.env = env;
		this.act = new Activation(env,env.ggs_stimulation_start,env.ggs_stimulation_end,env.ggs_τ1,env.ggs_τ2);
	}

	/**顆粒生成部更新**/
	void update(int t){
		generateGranules.clear();
		int createNum=gnumber(t);
		for(int i=0;i<createNum;i++){
			generateGranules.add(generateGranule());

		}
		//Cell.allGranules.addAll(generateGranules);//細胞内顆粒リストに追加
		InnerLayer.innerLayerGranules.addAll(generateGranules);//内部層顆粒リストに追加
	}

	/*生成個数決定関数*/
	int gnumber(int t){
		if(env.testflag==1){
			//System.out.println(t+" "+1+(int)(act.getActivation(t))*6);
			return 1+(int)(act.getActivation(t))*6;

		}else{
			int x =0;
			double rate = 0.0;
			double d = 0.0;
			double v=0.0,u=0.0,w=1.0;
			rate = env.deltaT*(env.p0 + (env.delta_p*act.getActivation(t)));
			d = Math.exp(rate);
			v = d;
			while(true){
				// u = rand()/(1.0+RAND_MAX);
				while(true){
					u=rnd.nextDouble();
					if(u!=1.0)break;
				}
				w = u*v;
				if(w < 1)break;
				else {
					v = w;
					x++;
				}
			}
			return x;
		}
	}
	/**顆粒生成関数**/
	Granule generateGranule(){
		if(env.testflag==0){
			int ran = rnd.nextInt(env.numberOfMicrotube);//微小管番号
			Granule granule = new Granule(env,ran);//
			return granule;
		}else{

			Granule granule = new Granule(env,Granule.number%env.numberOfMicrotube);//微小管に順番に顆粒を入れていく
			//System.out.println(granule.NumberOfMicrotube);
			return granule;
		}
	}





}
