package betacell;


public class Activation {
	public Environment env;
	public double stimulation_start;//刺激開始時間[S]
	public double τ1;//1つ目の遅れ[S]
	public double τ2;//2つ目の遅れ[S]
	public double stimulation_end;//刺激終了時間[S]
	
	Activation(Environment env,double t_start,double t_end,double τ1,double τ2){
		this.env=env;
		this.stimulation_start =  t_start;
		this.stimulation_end = t_end;
		this.τ1 = τ1;
		this.τ2 = τ2;
	
	}
	public double getActivation(int t) {
		double tmp;
		tmp=t*env.deltaT;//引数のtはスケールがずれてるので変換
	
		if(tmp<stimulation_start){
			return 0;
		}
		else if(tmp>=stimulation_start && tmp<stimulation_start+τ1){
			return (double)(tmp-stimulation_start)/τ1;
		}
		else if(tmp>=stimulation_start+τ1 && tmp<stimulation_end ){
			return 1;
		}
		else if(tmp>=stimulation_end && tmp<stimulation_end+τ2  ){
			return 1-(double)(tmp-stimulation_end)/τ2;
		}
		else if(tmp>=stimulation_end+τ2){
			return 0;
		}
		System.out.println("おかしい　Activation");
		return 0;
	}
}
