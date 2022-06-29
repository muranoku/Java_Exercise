import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class User {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int Turn(){
		int hand = 0;
		while(hand <= 0 || hand >= 4){ //まともな手（１～３）を入力するまで繰り返す
			System.out.println("あなたの番です。");
			System.out.println("手を入力してください。");
			System.out.println("1→グー　2→チョキ　3→パー");
			System.out.print(">>");
			hand = getHand();
		}
		
		return hand;
	}
	
	int getHand(){
		String handStr = null;
		int hand = 0;
		
		try {
			handStr = br.readLine();
			hand = Integer.parseInt(handStr);
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (NumberFormatException e){ //数字以外を打ち込んだ場合
			//e.printStackTrace();
			System.out.println("それは無効(" + handStr + ")");
		}
		
		return hand;
		
	}
}

class CPU {
    int cnt = 0;
    
	//グー、チョキ、パーを順番に返すだけ。
	int Turn(){
		cnt++;
		return cnt%3 + 1;
	}
}

class Start {
    
    //始めるだけ。
	public static void main(String[] args) {
		new Game().mainLoop();
	}

}

class Game {
    //プレイヤー達のインスタンス
    User one = new User();
	CPU two = new CPU();
	
    //プレイヤー達の手
	int oneHand = -1;
	int twoHand = -1;
    
    //勝利回数
	int winCnt = 0;
	
	void mainLoop(){
		int winner = 0;  //勝利者を記録する一時変数
		
		System.out.println("※3回勝つまで終わりません。");
		System.out.println();
		
		while(winCnt<3){
			//ユーザー＝自分＝1Pに手を入力させる
			System.out.println("手を入力してください＞User");
			oneHand = one.Turn();
			showHand(oneHand);
			System.out.println();
			
			//CPU＝相手＝2Pに手を入力させる
			System.out.println("手を入力してください＞CPU");
			twoHand = two.Turn();
			showHand(twoHand);
			System.out.println();
			
			System.out.println("勝利判定…");
			winner = ifWin(oneHand,twoHand);
			System.out.println();
			
			if(winner == 1){  //勝った場合
				winCnt++;
			}
		}
		
		System.out.println("おわります。");
	}
	
	void showHand(int hand){
		switch(hand){
			case 1:
				System.out.println("グー");
				break;
			case 2:
				System.out.println("チョキ");
				break;
			case 3:
				System.out.println("パー");
				break;
			default:
				System.out.println("なにそれ？");
				break;
		}
	}
	//oneHand…自分(1P)の手、twoHand…相手(2P)の手
    //引き分け＝0、自分(1P)が勝利＝1、相手(2P)が勝利＝2
	int ifWin(int oneHand, int twoHand) {
		if(oneHand == twoHand){  //あいこ
			System.out.println("あいこ");
			return 0;
		}
		else if(oneHand%3+1 == twoHand){  //勝った場合
			System.out.println("あなたの勝ち");
			return 1;
		}
		else{  //勝ちでもあいこでもない＝負けた場合
			System.out.println("あなたの負け");
			return 2;
		}
	}
}