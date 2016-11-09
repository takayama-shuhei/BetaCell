package betacell;

import java.util.ArrayList;

public class Membrane {
	private Environment env;
	private Activation act;
	public static ArrayList<Granule> Granules = new ArrayList<Granule>();//顆粒リスト
	public ArrayList<Integer> secretion = new ArrayList<Integer>();	//分泌数記録

	Membrane(Environment env){
		this.env=env;
		this.act = new Activation(env,env.ggs_stimulation_start,env.ggs_stimulation_end,env.ggs_τ1,env.ggs_τ2);
	}

	public void update(int t){
		int numberOfSecretion=0;
		//int skip = (int)(100/(env.syntaxinper0 + act.getActivation(t)*env.delta_syntaxinper));
		//System.out.println(skip);

		for(int i=0;i < Granules.size();i++){
			if(Granules.get(i).getFieldNumber() > (4426-(int)(act.getActivation(t))*100)){
			//			if(Granules.get(i).getFieldNumber() >( 4426*10-((this.act.getActivation(t)*10)*5))/10){
			numberOfSecretion++;
			Granules.remove(i);
			}
		}
		secretion.add(numberOfSecretion);
	}


}

